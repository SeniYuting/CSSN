package businesslogic.courseselectionbl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import po.course.CoursePO;
import po.courseselection.CourseSelectionPO;
import businesslogic.coursebl.CouresBLServiceImpl;
import businesslogic.statisticsbl.StatisticsBLServiceImpl;
import businesslogic.userbl.UserBLServiceImpl;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.statisticsblservice.StatisticsBLService;
import businesslogicservice.userblservice.UserBLService;
import data.coursedata.CourseDAO;
import data.courseselectiondata.CourseSelectionDAO;
import vo.course.CourseVO;
import vo.statistics.StatisticsVO;
import vo.user.UserVO;

public class CourseSelectionBLServiceImpl extends UnicastRemoteObject implements CourseSelectionBLService {
	private static final long serialVersionUID = 1L;
	
	Date beginDate;
	Date endDate;
	
	public CourseSelectionBLServiceImpl() throws RemoteException{	}
	
	//�鿴�Լ�ѡ�л�δѡ�е�ѡ���б�
	public ArrayList<CourseVO> checkMyCourse(String id,String semester,String state) throws RemoteException{
		CourseSelectionDAO csd = new CourseSelectionDAO();
		ArrayList<CourseVO> myCourse = new ArrayList<CourseVO>();
		ArrayList<CourseSelectionPO> myCourseSelection = csd.getCourseSelectionByStuNO(id,state);
		CourseBLService courseImpl = new CouresBLServiceImpl();
		UserBLService user = new UserBLServiceImpl();
		UserVO vo = user.getUserByIdNum(id);
		
		int grade = Integer.parseInt(vo.getGrade());
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		
		int currentSemester = -1;
		
		if(month >= 9 && month <= 12){
			currentSemester = (year - grade) * 2 + 1;
		}else if(month == 1){
			currentSemester = (year - grade) * 2 - 1;
		}else if(month >= 2 && month <= 6){
			currentSemester = (year - grade) * 2;
		}
		
		if(semester == null){
			semester = currentSemester + "";
		}
		
		
		if(myCourseSelection.size() > 0){
			for(int i = 0; i < myCourseSelection.size();i ++){
				CourseVO cv = courseImpl.checkAnyCourseInfo(myCourseSelection.get(i).getCourseNO());
				CourseSelectionPO csp = myCourseSelection.get(i);
				if(csp.getSemester().equals(semester)){
					myCourse.add(cv);
				}
			}
		}
		
		return myCourse;	

	}
	
	//ѡ��γ�
	public String chooseCourse(String courseNO,String id) throws RemoteException{
		String state = "false";
		CourseDAO cd = CourseDAO.getInstance();
		CourseSelectionDAO csd = new CourseSelectionDAO();
		ArrayList<CourseSelectionPO> myCourse = csd.getCourseSelectionByStuNO(id,"true");
		ArrayList<CourseSelectionPO> unsureCourse = csd.getCourseSelectionByStuNO(id,"false");
		
		UserBLService user = new UserBLServiceImpl();
		UserVO vo = user.getUserByIdNum(id);
		
		int grade = Integer.parseInt(vo.getGrade());
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		
		int currentSemester = -1;
		
		if(month >= 9 && month <= 12){
			currentSemester = (year - grade) * 2 + 1;
		}else if(month == 1){
			currentSemester = (year - grade) * 2 - 1;
		}else if(month >= 2 && month <= 6){
			currentSemester = (year - grade) * 2;
		}
		
		CourseSelectionPO po = new CourseSelectionPO(courseNO,id,null,state,currentSemester + "");
		
		Date date = new Date(System.currentTimeMillis());
		
		for(int i = 0;i < myCourse.size();i ++){
			String coID = myCourse.get(i).getCourseNO();
			if(coID.equals(courseNO)){
				return "���Ѿ�ѡ���˸ÿγ̣������ظ�ѡ��";
			}
		}
		
		for(int i = 0;i < unsureCourse.size();i ++){
			String coID = unsureCourse.get(i).getCourseNO();
			if(coID.equals(courseNO)){
				return "���Ѿ�ѡ���˸ÿγ̣������ظ�ѡ��";
			}
		}
		
		for(int i = 0;i < unsureCourse.size();i ++){
			CoursePO cp = cd.getCourseById(unsureCourse.get(i).getCourseNO());
			
			if(!cp.getModule().equals("ͨʶͨ��")||cp.getIsCompulsory()){
				unsureCourse.remove(i);
				i --;
			}
		}
	
		int tsNum = 0;
		for(int i = 0;i < unsureCourse.size();i ++){
			String coID = unsureCourse.get(i).getCourseNO();
			CoursePO cp = cd.getCourseById(coID);
			if(cp.getModule().equals("ͨʶͨ��") && !cp.getIsCompulsory() && cp.getInstitution().equals(vo.getInstitute())){
				tsNum ++;
			}
		}
		
		if(tsNum >= 4){
			return "���Ѿ�ѡ��4��ͨʶ���ֿΣ��޷���ѡ��";
		}

		
		if(isInTime(date) == 0){
			csd.addCourseSelection(po);
			return "ѡ����ɣ�";
		}else if(isInTime(date) == 1){
			return "�����޷�ѡ�Σ�";
		}else{
			return "ѡ����δ��ʼ��";
		}
	}
	
	//ѡ�����Σ��ȵ��ȵ�
	public String choosePELesson(String courseNO,String stuNO,String teacherNO) throws RemoteException{
		CourseBLService cs = new CouresBLServiceImpl();
	    CourseVO cv = cs.checkAnyCourseInfo(courseNO);
	    int maxNum = Integer.parseInt(cv.getNumOfStu());
	    
	    CourseSelectionDAO csd = new CourseSelectionDAO();
	    ArrayList<CourseSelectionPO> csv = csd.getCourseSelectionByCourseNO(courseNO, teacherNO);
		int currentNum = csv.size();
		
		Date today = new Date(System.currentTimeMillis());
		
		UserBLService user = new UserBLServiceImpl();
		UserVO vo = user.getUserByIdNum(stuNO);
		
		int grade = Integer.parseInt(vo.getGrade());
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		
		int currentSemester = -1;
		
		if(month >= 9 && month <= 12){
			currentSemester = (year - grade) * 2 + 1;
		}else if(month == 1){
			currentSemester = (year - grade) * 2 - 1;
		}else if(month >= 2 && month <= 6){
			currentSemester = (year - grade) * 2;
		}
		
		if(isInTime(today) == 0){
			ArrayList<CourseSelectionPO> myCourse = csd.getCourseSelectionByStuNO(stuNO, "false");

			for(int i = 0;i < myCourse.size();i ++){
				if(myCourse.get(i).getCourseNO().equals(courseNO)){
					return "���Ѿ�ѡ����������Σ������ظ�ѡ��";
				}
			}

			if(currentNum < maxNum){
				CourseSelectionPO po = new CourseSelectionPO(courseNO, stuNO, teacherNO, "true",currentSemester + "");
				csd.addCourseSelection(po);
				return "���ɹ�ѡ�ø��������Σ�";
			}else{
				return "ѡ��������������ѡ�����������Σ�";
			}
		}else if(isInTime(today) == 1){
			return "�����޷�ѡ�Σ�";
		}else{
			return "ѡ����δ��ʼ��";
		}
	}
	
	//��ѡ�γ�
	public String quitCourse(String courseNO,String id) throws RemoteException{
		CourseBLService course = new CouresBLServiceImpl();
		CourseVO cv = course.checkAnyCourseInfo(courseNO);
		Calendar cal = Calendar.getInstance();
		Date[] arrangeDate = readDate();
		cal.setTime(arrangeDate[1]);
		cal.add(Calendar.DATE, 14);
		Date allowedDate = (Date)cal.getTime();
		Date date = new Date(System.currentTimeMillis());
		CourseSelectionDAO csd = new CourseSelectionDAO();
		
		String state = csd.getState(id, courseNO);
		if(state.equals("false")){
			csd.deleteCourseSelection(courseNO,id);
			return "��ѡ�ɹ���";
		}else{
			if(!cv.getIsCompulsory()){
				if(date.after(arrangeDate[1]) && date.before(allowedDate)){
					csd.deleteCourseSelection(courseNO,id);
					return "��ѡ�ɹ���";
				}else if(date.after(allowedDate)){
					return "�����޷���ѡ��";
				}else{
					return "��������";
				}
			}else{
				return "���ſ��Ǳ��޿Σ����޷���ѡ��";
			}
		}
	}
	
	//�鿴��Ժ����γ̵�ѧ���б�
	public ArrayList<UserVO> checkAnyCourseStu(String courseNO) throws RemoteException{
		CourseSelectionDAO csd = new CourseSelectionDAO();
		ArrayList<CourseSelectionPO> stu = csd.getCourseSelectionByCourseNO(courseNO);
		ArrayList<UserVO> returnList = new ArrayList<UserVO>();
		UserBLService user = new UserBLServiceImpl();
		
		for(int i = 0;i < stu.size();i ++){
			String stuNO = stu.get(i).getStuNO();
			UserVO uv = user.getUserByIdNum(stuNO);
			returnList.add(uv);
		}
		return returnList;
	}

	//������ʦȷ��ѡ��ʱ�䷶Χ	
	public int publishChooseCourseTime(String begin, String end) throws RemoteException{
		String[] splitBegin = begin.split("-");
		String[] splitEnd = end.split("-");
		String beginYear = "20"+splitBegin[0];
		String beginMonth = splitBegin[1];
		String beginDay = splitBegin[2];
		String endYear = "20"+splitEnd[0];
		String endMonth = splitEnd[1];
		String endDay = splitEnd[2];
		
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, Integer.parseInt(beginYear));
		c1.set(Calendar.MONTH, Integer.parseInt(beginMonth));
		c1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(beginDay));
		
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, Integer.parseInt(endYear));
		c2.set(Calendar.MONTH, Integer.parseInt(endMonth));
		c2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(endDay));
		
		if(((Date)c1.getTime()).after((Date)c2.getTime()))  //��ʼʱ�����ڽ���ʱ��
			return -1;
		else{
			beginDate = (Date) c1.getTime();
			endDate = (Date) c2.getTime();
			setDate(beginYear+"-"+beginMonth+"-"+beginDay+"/"+endYear+"-"+endMonth+"-"+endDay);
		    return 1;
		}
	}
	//�鿴�Լ��γ̵�ѧ���б�
	public ArrayList<UserVO> checkMyCourseStu(String courseNO,String teacherNO) throws RemoteException{
			CourseSelectionDAO csd = new CourseSelectionDAO();
			ArrayList<CourseSelectionPO> cousepo = csd.getCourseSelectionByCourseNO(courseNO,teacherNO);
			ArrayList<UserVO> returnList = new ArrayList<UserVO>();
			UserBLServiceImpl user = new UserBLServiceImpl();
			for(int i = 0;i < cousepo.size();i ++){
				String stuNO = cousepo.get(i).getStuNO();
				UserVO uv = user.getUserByIdNum(stuNO);
				returnList.add(uv);
			}
			return returnList;
	}
	
	//�ж�ѧ��ѡ��ʱ���Ƿ��ڷ�Χ��
	public int isInTime(Date currentDate){
		Date[] date = readDate();
		if(currentDate.after(date[0])&&currentDate.before(date[1])){
			return 0;
		}else if(currentDate.before(date[0])){
			return -1;
		}else{
			return 1;
		}
	}
	
	//��txt�ļ��ж�ȡ��ʼ�ͽ���ѡ�ε�ʱ��
	public Date[] readDate(){
		Date[] d = new Date[2];
		
		try{
			File file = new File("date.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			br.close();
			String[] token = line.split("/");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			beginDate = sdf.parse(token[0]);
			endDate= sdf.parse(token[1]);
			
		    
		    d[0] = beginDate;
		    d[1] = endDate;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return d;
	}
	
	//�ѷ�����ʱ�����txt�ļ���
	public void setDate(String date){
		try{
			FileWriter fw = new FileWriter("date.txt");
			BufferedWriter br = new BufferedWriter(fw);
			br.write(date);
			br.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//������ʦ��ǩ����ѡ��ĳ�ſγ̵�ѧ���б�
	public ArrayList<UserVO> allocate(String courseNO,String maxGrade) throws RemoteException{
		CourseSelectionDAO csd = new CourseSelectionDAO();
		CourseBLService courseImpl = new CouresBLServiceImpl();
		CourseVO cv = courseImpl.checkAnyCourseInfo(courseNO);
		String module  = cv.getModule();
		int maxNum = Integer.parseInt(cv.getNumOfStu());
		CourseSelectionBLService csq = new CourseSelectionBLServiceImpl();
		ArrayList<UserVO> stuList = csq.checkAnyCourseStu(courseNO);
		ArrayList<UserVO> luckyMen = new ArrayList<UserVO>();
		ArrayList<Integer> priority = new ArrayList<Integer>();
		
		if(module.equals("ͨʶͨ��")){
			for(int i = 0;i < stuList.size();i ++){
				UserVO user = stuList.get(i);
				String grade = user.getGrade();
				StatisticsBLService stm = new StatisticsBLServiceImpl();
				StatisticsVO stv = stm.checkStuStatistics(user.getIdNum());
				int credit = stv.getLiberalEducation();
				int myPriority = judgePriority(credit,grade,maxGrade);
				priority.add(myPriority);
			}
			
			for(int i = 0;i < priority.size() - 1;i ++){
				for(int j = i;j < priority.size();j ++){
					if(priority.get(i) > priority.get(j)){
						int temp = priority.get(i);
						priority.set(i, priority.get(j));
						priority.set(j,temp);
						
						UserVO tempUser = stuList.get(i);
						luckyMen.set(i, luckyMen.get(j));
						luckyMen.set(j,tempUser);
					}
				}
			}
			for(int i = 0;i < maxNum;i ++){
				if(i>=stuList.size())
					break;  
				UserVO uv = stuList.get(i);
				luckyMen.add(uv);
				csd.setState(uv.getIdNum(), courseNO,"true");
			}
			
			return luckyMen;
		}else if(module.equals("����ѡ��")){
			ArrayList<UserVO> list = new ArrayList<UserVO>();
			ArrayList<Integer> randomList = new ArrayList<Integer>();
			boolean flag = true;
			for(int i = 0;i < maxNum/20;i ++){
				int randomNum = (int) (Math.random() * stuList.size());
			
				for(int j = 0;j < randomList.size();j ++){
					if(randomNum == randomList.get(j)){
						flag = false;
						i --;
					}
				}
				
				if(flag){
					randomList.add(randomNum);
					UserVO uv = stuList.get(randomNum);
					list.add(uv);
					csd.setState(uv.getIdNum(), courseNO,"true");
				}
			}
			return list;
		}else{
			return null;
		}
	}

	//����ѡ�����ȼ�
	public int judgePriority(int credit,String grade,String maxGrade){
		int max = Integer.parseInt(maxGrade);
		int myGrade = Integer.parseInt(grade);
		
		return credit + (max - myGrade) * 2;
	}
	
	//�鿴�Լ���ѡ���б�
		public ArrayList<CourseVO> checkMyCourse(String id,String state) throws RemoteException{
			CourseSelectionDAO csd = new CourseSelectionDAO();
			ArrayList<CourseVO> myCourse = new ArrayList<CourseVO>();
			ArrayList<CourseSelectionPO> myCourseSelection = csd.getCourseSelectionByStuNO(id,state);
			CourseBLService courseImpl = new CouresBLServiceImpl();
			
			for(int i = 0; i < myCourseSelection.size();i ++){
				CourseVO cv = courseImpl.checkAnyCourseInfo(myCourseSelection.get(i).getCourseNO());
				myCourse.add(cv);
			}
			
			return myCourse;	
		}
		

		
		
		//������ѡ�μ�¼����
		public void addCompulsoryCourseStu(String courseNO,String stuNO,String teacherNO) throws RemoteException{
			CourseSelectionDAO csd = new CourseSelectionDAO();
			UserBLService user = new UserBLServiceImpl();
			UserVO vo = user.getUserByIdNum(stuNO);
			
			int grade = Integer.parseInt(vo.getGrade());
			
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			
			int currentSemester = -1;
			
			if(month >= 9 && month <= 12){
				currentSemester = (year - grade) * 2 + 1;
			}else if(month == 1){
				currentSemester = (year - grade) * 2 - 1;
			}else if(month >= 2 && month <= 6){
				currentSemester = (year - grade) * 2;
			}
		    CourseSelectionPO po = new CourseSelectionPO(courseNO,stuNO,teacherNO,"true",currentSemester+"");
		    csd.addCourseSelection(po);
		}

}
