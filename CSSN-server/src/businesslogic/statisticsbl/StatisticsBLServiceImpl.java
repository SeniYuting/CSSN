package businesslogic.statisticsbl;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

import po.course.CoursePO;
import po.score.ScorePO;
import businesslogic.coursebl.CouresBLServiceImpl;
import businesslogic.courseselectionbl.CourseSelectionBLServiceImpl;
import businesslogic.scorebl.ScoreBLServiceImpl;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.scoreblservice.ScoreBLService;
import businesslogicservice.statisticsblservice.StatisticsBLService;
import vo.course.CourseVO;
import vo.score.ScoreVO;
import vo.statistics.StatisticsVO;
import vo.user.UserVO;

public class StatisticsBLServiceImpl extends UnicastRemoteObject implements StatisticsBLService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatisticsBLServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查看教师统计信息
	 * 
	 * @return statisticsList
	 * @param id
	 *            教师工号
	 * @throws RemoteException
	 */
	public ArrayList<StatisticsVO> checkTeacherStatistics(String id)
			throws RemoteException {

		ArrayList<StatisticsVO> statisticsList = new ArrayList<StatisticsVO>();// 每个vo对应所教一门课程的分数段分布
		CourseBLService courseblservice = new CouresBLServiceImpl();

		// 调用course模块的方法得到教师所教课程列表
		ArrayList<CoursePO> courseList = courseblservice.getTTeacherCourse(id);
		ScoreBLService scoreblservice = new ScoreBLServiceImpl();

		for (int i = 0; i < courseList.size(); i++) {

			int excellent = 0, fine = 0, medium = 0, pass = 0, fail = 0;// 分别代表90+,80~90,70~80,60~70,60-
			// 调用score的方法，返回分数段分布60~70,70~80,80~90,90+,60-
			ArrayList<ScorePO> scoreList = scoreblservice.checkCourseScore(
					courseList.get(i).getCoID(), id);
			for (int j = 0; j < scoreList.size(); j++) {
				int grade = scoreList.get(j).getScore();
				if (grade >= 90) {
					excellent++;
				} else if ((grade >= 80) && (grade < 90)) {
					fine++;
				} else if ((grade >= 70) && (grade < 80)) {
					medium++;
				} else if ((grade >= 60) && (grade < 70)) {
					pass++;
				} else if (grade >= 0) {// 只要不是-1;
					fail++;
				} else {
				}// 此时还未公布成绩

			}
			statisticsList.add(new StatisticsVO(courseList.get(i).getCoName(),
					courseList.get(i).getCoID(), excellent, fine, medium,
					pass, fail));// 返回VO给展示层时教师姓名等已经确定而具体是哪一门课程并不确定于是要在vo中添加课程信息
		}
		return statisticsList;
	}

	/**
	 * 查看课程统计信息;返回该门课的选课人数
	 * 
	 * @param courseNo
	 *            课程号
	 * @throws RemoteException
	 */
	public StatisticsVO checkCourseStatistics(String courseNo)
			throws RemoteException {
		CourseSelectionBLService csblservice = new CourseSelectionBLServiceImpl();
		ArrayList<UserVO> list = csblservice.checkAnyCourseStu(courseNo);
		int num = list.size();
		StatisticsVO statistics = new StatisticsVO(courseNo, num);
		return statistics;
	}

	/**
	 * 查看学生审核统计数据
	 * 
	 * @param stuNo
	 * @return StatisticsVO 返回的VO中有该学生修到的各模块学分数以及挂科科目列表
	 */
	public StatisticsVO checkStuStatistics(String stuNo) throws RemoteException {

		StatisticsVO statisticsvo;
		ArrayList<CourseVO> courseList = new ArrayList<CourseVO>();
		ArrayList<CourseVO> failList = new ArrayList<CourseVO>();// 挂科列表

		CourseSelectionBLService csblservice = new CourseSelectionBLServiceImpl();
		courseList = csblservice.checkMyCourse(stuNo, "true");// 从CourseSelectionDAO得到该学生选到的课程
		ScoreBLService scoreblservice = new ScoreBLServiceImpl();
		for (int i = 0; i < courseList.size(); i++) {// 区分挂掉的和得到学分的
			int score = -1;
			String coNo = courseList.get(i).getCoID();//
			score = (scoreblservice.checkStuScore(coNo, stuNo)).getScore();//
			if ((score >= 0) && (score < 60)) {// 如果不及格
				failList.add(courseList.get(i));// 挂科列表
				courseList.remove(i);
				i--;
			} else if (score == -1) {// -1表明还未登记
				courseList.remove(i);
				i--;
			}
		}

		int majorCredit = 0;// 学科专业模块修得学分
		int openCredit = 0;// 开放选修模块修得学分
		int commonCredit = 0;// 通修课程,包括微积分等
		int liberalEducation = 0;// 通识教育14个学分

		ArrayList<CourseVO> majorList = new ArrayList<CourseVO>();
		ArrayList<CourseVO> commonList = new ArrayList<CourseVO>();
		ArrayList<CourseVO> liberalEducationList = new ArrayList<CourseVO>();

		for (int i = 0; i < courseList.size(); i++) {// 从列表中得到学分
			switch (courseList.get(i).getModule()) {
			// 这里要不要单独判断是否是14个学分
			case "通识通修":
				if (courseList.get(i).getIsCompulsory()) {
					commonCredit += Integer.parseInt(courseList.get(i)
							.getCredit());
					commonList.add(new CourseVO(courseList.get(i).getCoID(),
							Integer.parseInt(courseList.get(i).getCredit()),
							courseList.get(i).getCoName()));
				} else {
					liberalEducation += Integer.parseInt(courseList.get(i)
							.getCredit());
					liberalEducationList.add(new CourseVO(courseList.get(i)
							.getCoID(), Integer.parseInt(courseList.get(i)
							.getCredit()), courseList.get(i).getCoName()));
				}
				break;
			case "开放选修":
				openCredit += Integer.parseInt(courseList.get(i).getCredit());
				break;
			case "专业核心":
				majorCredit += Integer.parseInt(courseList.get(i).getCredit());
				// 三个参数：课程号，学分，课程名
				majorList.add(new CourseVO(courseList.get(i).getCoID(), Integer
						.parseInt(courseList.get(i).getCredit()), courseList
						.get(i).getCoName()));
				break;
			}
		}

		statisticsvo = new StatisticsVO(stuNo, majorCredit, openCredit,
				commonCredit, liberalEducation, failList, majorList,
				commonList, liberalEducationList);
		return statisticsvo;
	}

	/**
	 * 查看学分绩
	 * 
	 * @param stuNo
	 * @param grade
	 * @param semester
	 * @return
	 * @throws RemoteException 
	 */
	public StatisticsVO checkGPA(String stuNo, String grade) throws RemoteException {

		ScoreBLService scblservice = new ScoreBLServiceImpl();
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		ArrayList<ScoreVO> scoreList = new ArrayList<ScoreVO>();
		StatisticsVO stvo = new StatisticsVO(new ArrayList<Double>());
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		if (month > 8) {// 八月之后可以查看一年的
			for (int i = 0; i < year - Integer.parseInt(grade); i++) {
				scoreList = scblservice
						.checkScoreList(stuNo, grade, i + 1 + "");
				double scoreNum = 0;
				double creditNum = 0;
				for (int j = 0; j < scoreList.size(); j++) {
					scoreNum = scoreNum + scoreList.get(j).getScore()
							* scoreList.get(j).getCredit();
					creditNum = creditNum + scoreList.get(j).getCredit();
				}
				double d=0;
				if(creditNum!=0){
					d = scoreNum / creditNum;
				}
				else{
					d=0;
				}
				
				d = d / 20;
				stvo.getGpa().add(d);
			}
		} else {
			for (int i = 0; i < year - Integer.parseInt(grade) - 1; i++) {
				scoreList = scblservice.checkScoreList(stuNo, grade, i + "");
				scoreList = scblservice.checkScoreList(stuNo, grade, i + "");
				double scoreNum = 0;
				double creditNum = 0;
				for (int j = 0; j < scoreList.size(); j++) {
					scoreNum = scoreNum + scoreList.get(j).getScore()
							* scoreList.get(j).getCredit();
					creditNum = creditNum + scoreList.get(j).getCredit();
				}
				double d = scoreNum / creditNum;
				stvo.getGpa().add(d);
			}
		}
		return stvo;
	}

	/**
	 * 查看准入准出课程统计
	 * 
	 * @return
	 */
	public StatisticsVO checkAccess(String stuNo, String institute) {

		// 这个vo中包括所有的该学生的课程统计,就是教务处老师查看学生审核统计数据的VO；还有准出
		StatisticsVO vo1;
		CourseSelectionBLService csm;
		ScoreBLService scm;
		StatisticsVO VO = null;

		try {
			vo1 = checkStuStatistics(stuNo);
			csm = new CourseSelectionBLServiceImpl();
			scm = new ScoreBLServiceImpl();
			ArrayList<CourseVO> allCourse = csm.checkMyCourse(stuNo, "true");
			ArrayList<CourseVO> courseList = new ArrayList<CourseVO>();
			for (int i = 0; i < allCourse.size(); i++) {
				if (allCourse.get(i).getInstitution().equals(institute)
						&& ((scm.checkStuScore(allCourse.get(i).getCoID(),stuNo)).getScore())>= 60) {
					courseList.add(allCourse.get(i));
				}
			}
			int creditNum = 0;
			for (int j = 0; j < courseList.size(); j++) {
				creditNum = creditNum
						+ Integer.parseInt(courseList.get(j).getCredit());
			}
			VO = new StatisticsVO(vo1, courseList, creditNum);

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return VO;
	}

}
