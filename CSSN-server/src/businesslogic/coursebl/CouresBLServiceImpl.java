package businesslogic.coursebl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import businesslogic.courseselectionbl.CourseSelectionBLServiceImpl;
import businesslogic.userbl.UserBLServiceImpl;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.userblservice.UserBLService;
import data.coursedata.CourseDAO;
import data.coursedata.YXCourseDAO;
import data.framedata.FrameDAO;
import po.course.CoursePO;
import po.user.UserPO;
import vo.course.CourseVO;

/**
 * �����ƣ�CouresBLServiceImpl 
 * ��������CourseBLService�ӿڵ�ʵ����
 * @author user
 * 
 */
public class CouresBLServiceImpl extends UnicastRemoteObject implements
		CourseBLService {

	private static final long serialVersionUID = 1L;

	private CourseDAO courseDAO;
	private YXCourseDAO YXcourseDAO;
	FrameDAO fd = FrameDAO.getInstance();
	CourseSelectionBLService csm = new CourseSelectionBLServiceImpl();
	UserBLService uq = new UserBLServiceImpl();

	
	public CouresBLServiceImpl() throws RemoteException {
		super();
		courseDAO = CourseDAO.getInstance();
		YXcourseDAO = YXCourseDAO.getInstance();
	}


	public int publishYXCourse(CourseVO vo) throws RemoteException {
		String s4=vo.getCredit();
		String s5=vo.getHour();
		String s12=vo.getNumOfStu();
		if (courseDAO.isExist(vo.getCoID(), vo.getCoName())
				|| YXcourseDAO.isExist(vo.getCoID(), vo.getCoName())) {
			return -1;// �γ̺Ż�γ����Ѿ�����
		} else if(!isNumber(s4)||Integer.parseInt(s4) <= 0){
			return -2;//ѧ�ֲ�����Ҫ��
		}
		else if(!isNumber(s5)||Integer.parseInt(s5) <= 0){
			return -3;//��ʱ������Ҫ��
		}
		else if(!isNumber(s12)||Integer.parseInt(s12) <= 0){
			return -4;//��������������Ҫ��
		}
		else if(Integer.parseInt(s4) > 10){
			return -5;//���ſγ̵�ѧ�ֹ���
		}
		else if(Integer.parseInt(s5) > 50){
			return -6;//���ſγ̵Ŀ�ʱ����
		}else {
			int total = courseDAO.getTotalByModule(vo.getModule())
					+ YXcourseDAO.getTotalByModule(vo.getModule())
					+ Integer.parseInt(vo.getCredit());

			int upper = fd.getModuleCreditUpper(vo.getModule());
			if (total > upper) {
				return 0;// ��ģ����ѧ���Ѿ������ƻ�������
			} else {
				CoursePO po = new CoursePO(vo.getCoID(), vo.getCoName(),
						vo.getIsCompulsory(), vo.getCredit(), vo.getHour(),
						vo.getModule(), vo.getTeaID(), vo.getTeacher(),
						vo.getSemester(), vo.getGrade(), vo.getInstitution(),
						vo.getNumOfStu(), vo.getTime(), vo.getLocation());
				YXcourseDAO.addYXCourse(po); //�ӵ�Ժϵ��ʦ�α�
				return 1;// �����ɹ�
			}
		}
	}


	public int publishJWCourse(CourseVO vo) throws RemoteException {
		String s4=vo.getCredit();
		String s5=vo.getHour();
		String s12=vo.getNumOfStu();
		if (courseDAO.isExist(vo.getCoID(), vo.getCoName())
				|| YXcourseDAO.isExist(vo.getCoID(), vo.getCoName())) { 
			return -1;// �γ̺Ż�γ����Ѿ�����
		} else if(!isNumber(s4)||Integer.parseInt(s4) <= 0){
			return -2;//ѧ�ֲ�����Ҫ��
		}
		else if(!isNumber(s5)||Integer.parseInt(s5) <= 0){
			return -3;//��ʱ������Ҫ��
		}
		else if(!isNumber(s12)||Integer.parseInt(s12) <= 0){
			return -4;//��������������Ҫ��
		}
		else if(Integer.parseInt(s4) > 10){
			return -5;//���ſγ̵�ѧ�ֹ���
		}
		else if(Integer.parseInt(s5) > 50){
			return -6;//���ſγ̵Ŀ�ʱ����
		}else {
			int total = courseDAO.getTotalByModule(vo.getModule())
					+ YXcourseDAO.getTotalByModule(vo.getModule())
					+ Integer.parseInt(vo.getCredit());
			int upper = fd.getModuleCreditUpper(vo.getModule());
			if (total > upper) {
				return 0;// ��ģ����ѧ���Ѿ������ƻ�������
			} else {
				CoursePO po = new CoursePO(vo.getCoID(), vo.getCoName(),
						vo.getIsCompulsory(), vo.getCredit(), vo.getHour(),
						vo.getModule(), vo.getTeaID(), vo.getTeacher(),
						vo.getSemester(), vo.getGrade(), vo.getInstitution(),
						vo.getNumOfStu(), vo.getTime(), vo.getLocation(),
						vo.getDescription(), vo.getTextbook(),
						vo.getReference());
				courseDAO.addCourse(po); //�ӵ�Ժϵ��ʦ�α�
				if (vo.getIsCompulsory() == true) {
					ArrayList<UserPO> IDList = uq.getAllStuID();
					for (UserPO up : IDList) {
						csm.addCompulsoryCourseStu(vo.getCoID(), up.getIdNum(),
								vo.getTeaID());// �����޿ε�ѡ�μ�¼����Ժϵѧ���Ŀγ��б���ȥ
					}
				}
				return 1;// �����ɹ�
			}
		}
	}

	 
	public int modifyCourseInfo(String oldCoID, CourseVO newCourseVO)
			throws RemoteException {
		String s4=newCourseVO.getCredit();
		String s5=newCourseVO.getHour();
		String s12=newCourseVO.getNumOfStu();
		if(!isNumber(s4)||Integer.parseInt(s4) <= 0){
			return -2;//ѧ�ֲ�����Ҫ��
		}
		else if(!isNumber(s5)||Integer.parseInt(s5) <= 0){
			return -3;//��ʱ������Ҫ��
		}
		else if(!isNumber(s12)||Integer.parseInt(s12) <= 0){
			return -4;//��������������Ҫ��
		}
		else if(Integer.parseInt(s4) > 10){
			return -5;//���ſγ̵�ѧ�ֹ���
		}
		else if(Integer.parseInt(s5) > 50){
			return -6;//���ſγ̵Ŀ�ʱ����
		}else{
		CoursePO po = new CoursePO(newCourseVO.getCoID(),
				newCourseVO.getCoName(), newCourseVO.getIsCompulsory(),
				newCourseVO.getCredit(), newCourseVO.getHour(),
				newCourseVO.getModule(), newCourseVO.getTeaID(),
				newCourseVO.getTeacher(), newCourseVO.getSemester(),
				newCourseVO.getGrade(), newCourseVO.getInstitution(),
				newCourseVO.getNumOfStu(), newCourseVO.getTime(),
				newCourseVO.getLocation());
		YXcourseDAO.modifyYXCourse(oldCoID, po); // �޸ĳɹ�
		return 1;
		}
	}

	public int completeCourseInfo(String coID, CourseVO addedVO)
			throws RemoteException {
		CoursePO partPO = YXcourseDAO.getYXCourseById(coID);
		if (partPO == null) {
			return -1;// ���������ſγ�
		} else {
			YXcourseDAO.deleteYXCourse(coID);
			CoursePO cp = new CoursePO(partPO.getCoID(), partPO.getCoName(),
					partPO.getIsCompulsory(), partPO.getCredit(),
					partPO.getHour(), partPO.getModule(), partPO.getTeaID(),
					partPO.getTeacher(), partPO.getSemester(),
					partPO.getGrade(), partPO.getInstitution(),
					partPO.getNumOfStu(), partPO.getTime(),
					partPO.getLocation(), addedVO.getDescription(),
					addedVO.getTextbook(), addedVO.getReference());
			courseDAO.addCourse(cp);
			if (cp.getIsCompulsory() == true) {
				ArrayList<UserPO> IDList = uq.getYXStuID2(cp.getInstitution());
				for (UserPO up : IDList) {
					csm.addCompulsoryCourseStu(cp.getCoID(), up.getIdNum(),
							cp.getTeaID());// �����޿ε�ѡ�μ�¼����Ժϵѧ���Ŀγ��б���ȥ
				}
			}
			return 1; // ��д�ɹ�
		}
	}

	
	public ArrayList<CourseVO> checkYXcourseList(String institution)
			throws RemoteException {
		ArrayList<CoursePO> POcourseList = courseDAO
				.getCourseByInstitution(institution);
		ArrayList<CourseVO> VOcourseList = new ArrayList<CourseVO>();
		for (CoursePO po : POcourseList) {
			CourseVO cv = new CourseVO(po.getCoID(), po.getCoName());
			VOcourseList.add(cv);
		}
		return VOcourseList;
	}
 
	public CourseVO checkYXcourseInfo(String coID, String institution)
			throws RemoteException {
		CoursePO cp = courseDAO.getCourseByIDAndIns(coID, institution);
		CourseVO cv = new CourseVO(cp.getCoID(), cp.getCoName(),
				cp.getIsCompulsory(), cp.getCredit(), cp.getHour(),
				cp.getModule(), cp.getTeaID(), cp.getTeacher(),
				cp.getSemester(), cp.getGrade(), cp.getInstitution(),
				cp.getNumOfStu(), cp.getTime(), cp.getLocation(),
				cp.getDescription(), cp.getTextbook(), cp.getReference());
		return cv;
	}

	public CourseVO checkYXUnfinishedcourseInfo(String coID)
			throws RemoteException {
		CoursePO cp = YXcourseDAO.getCourseById(coID);
		CourseVO cv = new CourseVO(cp.getCoID(), cp.getCoName(),
				cp.getIsCompulsory(), cp.getCredit(), cp.getHour(),
				cp.getModule(), cp.getTeaID(), cp.getSemester(),
				cp.getTeacher(), cp.getGrade(), cp.getInstitution(),
				cp.getNumOfStu(), cp.getTime(), cp.getLocation());
		return cv;
	}

	public CourseVO checkAnyCourseInfo(String coID) throws RemoteException {
		CoursePO cp = courseDAO.getCourseById(coID);
		CourseVO cv = new CourseVO(cp.getCoID(), cp.getCoName(),
				cp.getIsCompulsory(), cp.getCredit(), cp.getHour(),
				cp.getModule(), cp.getTeaID(), cp.getTeacher(),
				cp.getSemester(), cp.getGrade(), cp.getInstitution(),
				cp.getNumOfStu(), cp.getTime(), cp.getLocation(),
				cp.getDescription(), cp.getTextbook(), cp.getReference());
		return cv;
	}

	public ArrayList<CourseVO> getCourseToFinishInfo(String teacherId)
			throws RemoteException {
		ArrayList<CoursePO> coursePO = YXcourseDAO
				.getCourseToFinishInfo(teacherId);
		ArrayList<CourseVO> courseVO = new ArrayList<CourseVO>();
		for (int i = 0; i < coursePO.size(); i++) {
			courseVO.add(new CourseVO(coursePO.get(i).getCoID(), Integer
					.parseInt(coursePO.get(i).getCredit()), coursePO.get(i)
					.getCoName(), coursePO.get(i).getGrade(), coursePO.get(i)
					.getSemester()));
		}
		return courseVO;
	}

	public ArrayList<CourseVO> getTeacherCourse(String teacherId)
			throws RemoteException {
		ArrayList<CoursePO> coursePO = courseDAO.getTeacherCourse(teacherId);
		ArrayList<CourseVO> courseVO = new ArrayList<CourseVO>();
		for (int i = 0; i < coursePO.size(); i++) {
			courseVO.add(new CourseVO(coursePO.get(i).getCoID(), Integer
					.parseInt(coursePO.get(i).getCredit()), coursePO.get(i)
					.getCoName(), coursePO.get(i).getGrade(), coursePO.get(i)
					.getSemester()));
		}
		return courseVO;
	}

	public ArrayList<CourseVO> getModuleCourseList(String module)
			throws RemoteException {
		ArrayList<CoursePO> POcourseList = courseDAO.getCourseByModule(module);
		ArrayList<CourseVO> VOcourseList = new ArrayList<CourseVO>();
		for (CoursePO po : POcourseList) {
			CourseVO cv = new CourseVO(po.getCoID(), po.getCoName());
			VOcourseList.add(cv);
		}
		return VOcourseList;
	}

	public ArrayList<CourseVO> getAllCourseList() throws RemoteException {
		ArrayList<CoursePO> POcourseList = courseDAO.getAllCourseList();
		ArrayList<CourseVO> VOcourseList = new ArrayList<CourseVO>();
		for (CoursePO po : POcourseList) {
			CourseVO cv = new CourseVO(po.getCoID(), po.getCoName());
			VOcourseList.add(cv);
		}
		return VOcourseList;
	}

	public ArrayList<CourseVO> checkMycourseList(String institution)
			throws RemoteException {
		ArrayList<CoursePO> POcourseList = YXcourseDAO
				.getCourseByInstitution(institution);
		ArrayList<CourseVO> VOcourseList = new ArrayList<CourseVO>();
		for (CoursePO po : POcourseList) {
			CourseVO cv = new CourseVO(po.getCoID(), po.getCoName());
			VOcourseList.add(cv);
		}
		return VOcourseList;
	}

	public ArrayList<CoursePO> getTTeacherCourse(String id)
			throws RemoteException {
		return courseDAO.getCourseByTeaID(id);
	}

	public ArrayList<CourseVO> getModuleCompletedCourseList(String module)
			throws RemoteException {
		ArrayList<CoursePO> POcourseList = courseDAO.getCourseByModule(module);
		ArrayList<CourseVO> VOcourseList = new ArrayList<CourseVO>();
		for (CoursePO po : POcourseList) {
			CourseVO cv = new CourseVO(po.getCoID(), po.getCoName(),
					po.getIsCompulsory(), po.getCredit(), po.getHour(),
					po.getModule(), po.getTeaID(), po.getTeacher(),
					po.getSemester(), po.getGrade(), po.getInstitution(),
					po.getNumOfStu(), po.getTime(), po.getLocation(),
					po.getDescription(), po.getTextbook(), po.getReference());
			VOcourseList.add(cv);
		}
		return VOcourseList;
	}

	public ArrayList<CourseVO> checkYXCompletedcourseList(String institution)
			throws RemoteException {
		ArrayList<CoursePO> POcourseList = courseDAO
				.getCourseByInstitution(institution);
		ArrayList<CourseVO> VOcourseList = new ArrayList<CourseVO>();
		for (CoursePO po : POcourseList) {
			CourseVO cv = new CourseVO(po.getCoID(), po.getCoName(),
					po.getIsCompulsory(), po.getCredit(), po.getHour(),
					po.getModule(), po.getTeaID(), po.getTeacher(),
					po.getSemester(), po.getGrade(), po.getInstitution(),
					po.getNumOfStu(), po.getTime(), po.getLocation(),
					po.getDescription(), po.getTextbook(), po.getReference());
			VOcourseList.add(cv);
		}
		return VOcourseList;
	}
	
	public boolean isNumber(String s) {
		boolean isNumber = true;
		char ch[] = s.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] < '0' || ch[i] > '9') {
				isNumber = false;
			}
		}
		return isNumber;
	}

}