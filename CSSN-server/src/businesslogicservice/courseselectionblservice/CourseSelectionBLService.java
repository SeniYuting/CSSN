package businesslogicservice.courseselectionblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.course.CourseVO;
import vo.user.UserVO;

/**
 * �ӿ�����CourseSelectionBLService
 * �ӿ�������CourseSelectionģ���ṩ�Ķ���ӿڣ�������courseSelection.accdb�йصķ���
 * @author Just Coding��
 * 
 */
public interface CourseSelectionBLService extends Remote{
	/**
	 * ��������checkMyCourse 
	 * �����������鿴�ҵĿγ��б�
	 * @param id
	 * @param semester
	 * @param state
	 * @return ���������������ҵĿγ��б�
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkMyCourse(String id,String semester,String state) throws RemoteException;
	
	/**
	 * ��������quitCourse
	 * ������������ѡ�γ�
	 * @param courseNO 
	 * @param id
	 * @return ������ѡ���
	 * @throws RemoteException
	 */
	public String quitCourse(String courseNO,String id)  throws RemoteException;

	/**
	 * ��������chooseCourse 
	 * ����������ѡ��γ�
	 * @param courseNO
	 * @param id
	 * @return ����ѡ�ν��
	 * @throws RemoteException
	 */
	public String chooseCourse(String courseNO,String id) throws RemoteException;
	
	/**
	 * ��������choosePELesson 
	 * ����������ѡ�����Σ��ȵ��ȵ�
	 * @param courseNO
	 * @param stuNO
	 * @param teacherNO
	 * @return ��������ѡ�ν��
	 * @throws RemoteException
	 */
	public String choosePELesson(String courseNO,String stuNO,String teacherNO) throws RemoteException;

	/**
	 * ��������checkAnyCourseStu
	 * �����������鿴����γ̵�ѡ��ѧ���б�
	 * @param courseNO 
	 * @return ��������������ѧ���б�
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> checkAnyCourseStu(String courseNO) throws RemoteException;

	/**
	 * ��������checkMyCourseStu
	 * �����������鿴�Լ��γ̵�ѡ��ѧ���б�
	 * @param courseNO
	 * @param teacherNO
	 * @return ��������������ѧ���б�
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> checkMyCourseStu(String courseNO,String teacherNO) throws RemoteException;

	/**
	 * ��������publishChooseTime 
	 * ����������������ʦ����ѡ��ʱ��
	 * @param begin
	 * @param end
	 * @return �������-1��ʼʱ�����ڽ���ʱ�䣻
	 *         �������1�򷢲�ѡ��ʱ��ɹ�
	 * @throws RemoteException
	 */
	public int publishChooseCourseTime(String begin, String end) throws RemoteException;

	/**
	 * ��������allocate 
	 * ����������������ʦ��ǩ
	 * @param courseNO 
	 * @param maxGrade
	 * @return ������ǩ��ѧ���б�
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> allocate(String courseNO,String maxGrade) 
			throws RemoteException;
	
	/**
	 * ��������checkMyCourse 
	 * �����������鿴�ҵĿγ��б�
	 * @param id
	 * @param state
	 * @return �������������Ŀγ��б�
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkMyCourse(String id,String state) throws RemoteException;
	
	/**
	 * ��������addCompulsoryCourseStu 
	 * �����������Զ���ӱ��޿ε�ѡ��ѧ���б�
	 * @param courseNO
	 * @param stuNO
	 * @param teacherNO
	 * @throws RemoteException
	 */
	public void addCompulsoryCourseStu(String courseNO,String stuNO,String teacherNO) throws RemoteException;
}
