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
	 * �鿴��ʦͳ����Ϣ
	 * 
	 * @return statisticsList
	 * @param id
	 *            ��ʦ����
	 * @throws RemoteException
	 */
	public ArrayList<StatisticsVO> checkTeacherStatistics(String id)
			throws RemoteException {

		ArrayList<StatisticsVO> statisticsList = new ArrayList<StatisticsVO>();// ÿ��vo��Ӧ����һ�ſγ̵ķ����ηֲ�
		CourseBLService courseblservice = new CouresBLServiceImpl();

		// ����courseģ��ķ����õ���ʦ���̿γ��б�
		ArrayList<CoursePO> courseList = courseblservice.getTTeacherCourse(id);
		ScoreBLService scoreblservice = new ScoreBLServiceImpl();

		for (int i = 0; i < courseList.size(); i++) {

			int excellent = 0, fine = 0, medium = 0, pass = 0, fail = 0;// �ֱ����90+,80~90,70~80,60~70,60-
			// ����score�ķ��������ط����ηֲ�60~70,70~80,80~90,90+,60-
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
				} else if (grade >= 0) {// ֻҪ����-1;
					fail++;
				} else {
				}// ��ʱ��δ�����ɼ�

			}
			statisticsList.add(new StatisticsVO(courseList.get(i).getCoName(),
					courseList.get(i).getCoID(), excellent, fine, medium,
					pass, fail));// ����VO��չʾ��ʱ��ʦ�������Ѿ�ȷ������������һ�ſγ̲���ȷ������Ҫ��vo����ӿγ���Ϣ
		}
		return statisticsList;
	}

	/**
	 * �鿴�γ�ͳ����Ϣ;���ظ��ſε�ѡ������
	 * 
	 * @param courseNo
	 *            �γ̺�
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
	 * �鿴ѧ�����ͳ������
	 * 
	 * @param stuNo
	 * @return StatisticsVO ���ص�VO���и�ѧ���޵��ĸ�ģ��ѧ�����Լ��ҿƿ�Ŀ�б�
	 */
	public StatisticsVO checkStuStatistics(String stuNo) throws RemoteException {

		StatisticsVO statisticsvo;
		ArrayList<CourseVO> courseList = new ArrayList<CourseVO>();
		ArrayList<CourseVO> failList = new ArrayList<CourseVO>();// �ҿ��б�

		CourseSelectionBLService csblservice = new CourseSelectionBLServiceImpl();
		courseList = csblservice.checkMyCourse(stuNo, "true");// ��CourseSelectionDAO�õ���ѧ��ѡ���Ŀγ�
		ScoreBLService scoreblservice = new ScoreBLServiceImpl();
		for (int i = 0; i < courseList.size(); i++) {// ���ֹҵ��ĺ͵õ�ѧ�ֵ�
			int score = -1;
			String coNo = courseList.get(i).getCoID();//
			score = (scoreblservice.checkStuScore(coNo, stuNo)).getScore();//
			if ((score >= 0) && (score < 60)) {// ���������
				failList.add(courseList.get(i));// �ҿ��б�
				courseList.remove(i);
				i--;
			} else if (score == -1) {// -1������δ�Ǽ�
				courseList.remove(i);
				i--;
			}
		}

		int majorCredit = 0;// ѧ��רҵģ���޵�ѧ��
		int openCredit = 0;// ����ѡ��ģ���޵�ѧ��
		int commonCredit = 0;// ͨ�޿γ�,����΢���ֵ�
		int liberalEducation = 0;// ͨʶ����14��ѧ��

		ArrayList<CourseVO> majorList = new ArrayList<CourseVO>();
		ArrayList<CourseVO> commonList = new ArrayList<CourseVO>();
		ArrayList<CourseVO> liberalEducationList = new ArrayList<CourseVO>();

		for (int i = 0; i < courseList.size(); i++) {// ���б��еõ�ѧ��
			switch (courseList.get(i).getModule()) {
			// ����Ҫ��Ҫ�����ж��Ƿ���14��ѧ��
			case "ͨʶͨ��":
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
			case "����ѡ��":
				openCredit += Integer.parseInt(courseList.get(i).getCredit());
				break;
			case "רҵ����":
				majorCredit += Integer.parseInt(courseList.get(i).getCredit());
				// �����������γ̺ţ�ѧ�֣��γ���
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
	 * �鿴ѧ�ּ�
	 * 
	 * @param stuNo
	 * @param grade
	 * @param semester
	 * @return
	 * @throws RemoteException 
	 */
	public StatisticsVO checkGPA(String stuNo, String grade) throws RemoteException {

		ScoreBLService scblservice = new ScoreBLServiceImpl();
		Calendar c = Calendar.getInstance();// ���Զ�ÿ��ʱ���򵥶��޸�
		ArrayList<ScoreVO> scoreList = new ArrayList<ScoreVO>();
		StatisticsVO stvo = new StatisticsVO(new ArrayList<Double>());
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		if (month > 8) {// ����֮����Բ鿴һ���
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
	 * �鿴׼��׼���γ�ͳ��
	 * 
	 * @return
	 */
	public StatisticsVO checkAccess(String stuNo, String institute) {

		// ���vo�а������еĸ�ѧ���Ŀγ�ͳ��,���ǽ�����ʦ�鿴ѧ�����ͳ�����ݵ�VO������׼��
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
