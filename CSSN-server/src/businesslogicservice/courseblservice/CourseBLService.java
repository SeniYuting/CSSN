package businesslogicservice.courseblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.course.CoursePO;
import vo.course.CourseVO;

/**
 * �ӿ�����CouresBLService
 * �ӿ�������Courseģ���ṩ�Ķ���ӿڣ�������course.accdb�йصķ���
 * @author Just Coding��
 * 
 */
public interface CourseBLService extends Remote {
 
	/**
	 * ��������publishYXCourse 
	 * ����������Ժϵ������ʦ�����γ�
	 * @param vo 
	 * @return �������-1��γ̺Ż�γ����Ѿ����ڣ�
	 * 		        �������-2��ѧ�ֲ�����Ҫ��
	 * 		        �������-3���ʱ������Ҫ��
	 * 		        �������-4�򿪿�����������Ҫ��
	 *  	        �������-5���ſγ̵�ѧ�ֹ���
	 *         �������-6���ſγ̵Ŀ�ʱ����
	 *         �������0���ģ����ѧ���Ѿ������ƻ�������
	 *         �������1�򷢲��ɹ�
	 * @throws RemoteException
	 */
	public int publishYXCourse(CourseVO vo) throws RemoteException;


	/**
	 * ��������publishJWCourse 
	 * ����������������ʦ�����γ�
	 * @param vo 
	 * @return �������-1��γ̺Ż�γ����Ѿ����ڣ�
	 * 		        �������-2��ѧ�ֲ�����Ҫ��
	 * 		        �������-3���ʱ������Ҫ��
	 * 		        �������-4�򿪿�����������Ҫ��
	 *  	        �������-5���ſγ̵�ѧ�ֹ���
	 *         �������-6���ſγ̵Ŀ�ʱ����
	 *         �������0���ģ����ѧ���Ѿ������ƻ�������
	 *         �������1�򷢲��ɹ�
	 * @throws RemoteException
	 */
	public int publishJWCourse(CourseVO vo) throws RemoteException;

	 
	/**
	 * ��������modifyCourseInfo 
	 * ����������Ժϵ������ʦ�޸Ŀγ���Ϣ
	 * @param oldCoID
	 * @param newCourseVO
	 * @return �������-2��ѧ�ֲ�����Ҫ��
	 * 		        �������-3���ʱ������Ҫ��
	 * 		        �������-4�򿪿�����������Ҫ��
	 *  	        �������-5���ſγ̵�ѧ�ֹ���
	 *         �������-6���ſγ̵Ŀ�ʱ����
	 *         �������1�򷢲��ɹ�
	 * @throws RemoteException
	 */
	public int modifyCourseInfo(String oldCoID, CourseVO newCourseVO)
			throws RemoteException;

	
	/**
	 * ��������completeCourseInfo 
	 * �����������ο���ʦ��д�γ���Ϣ
	 * @param coID
	 * @param addedVO
	 * @return �������-1�򲻴������ſγ�
	 *         �������1����д�ɹ�
	 * @throws RemoteException
	 */
	public int completeCourseInfo(String coID, CourseVO addedVO)
			throws RemoteException;
 
	/**
	 * ��������getCourseToFinishInfo 
	 * �����������ɽ�ʦ���ŵõ���ʦӦ����д��Ϣ�Ŀγ�
	 * @param teacherId
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getCourseToFinishInfo(String teacherId)
			throws RemoteException;
	 
	/**
	 * ��������getTeacherCourse 
	 * �����������ɽ�ʦ���ŵõ���ʦ���ڵĿγ�
	 * @param teacherId
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getTeacherCourse(String teacherId)
			throws RemoteException;
 
	/**
	 * ��������checkYXcourseList 
	 * ����������Ժϵ������ʦ�鿴��Ժ�γ��б�
	 * @param institution
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkYXcourseList(String institution)
			throws RemoteException;

	/**
	 * ��������checkYXcourseInfo 
	 * ����������Ժϵ������ʦ�鿴��Ժ����γ���Ϣ
	 * @param coID
	 * @param institution
	 * @return CourseVO
	 * @throws RemoteException
	 */
	public CourseVO checkYXcourseInfo(String coID, String institution)
			throws RemoteException;

	/**
	 * ��������checkAnyCourseInfo 
	 * ����������ѧ���鿴����γ���Ϣ
	 * @param coID
	 * @return CourseVO
	 * @throws RemoteException
	 */
	public CourseVO checkAnyCourseInfo(String coID) throws RemoteException;

	/**
	 * ��������getModuleCourseList 
	 * ���������� �ɿγ�ģ��õ��γ��б�
	 * @param coID
	 * @return CourseVO
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getModuleCourseList(String module)
			throws RemoteException;

	/**
	 * ��������getAllCourseList 
	 * ���������� �õ�ȫУ�Ŀγ��б�
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getAllCourseList() throws RemoteException;
 
	/**
	 * ��������checkMycourseList 
	 * �����������õ�Ժϵ������ʦ�Ѿ�����������Ϣδ��д�����Ŀγ��б�
	 * @param institution
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkMycourseList(String institution)
			throws RemoteException;
 
	/**
	 * ��������checkYXUnfinishedcourseInfo 
	 * ����������Ժϵ������ʦ�鿴δ��д������Ϣ�Ŀγ��б����Ϣ
	 * @param coID
	 * @return CourseVO
	 * @throws RemoteException
	 */
	public CourseVO checkYXUnfinishedcourseInfo(String coID)
			throws RemoteException;
 
	/**
	 * ��������getTTeacherCourse 
	 * ����������ͨ����ʦ�Ĺ��Ż����ʦ���̿γ�
	 * @param id
	 * @return ArrayList<CoursePO>
	 * @throws RemoteException
	 */
	public ArrayList<CoursePO> getTTeacherCourse(String id)
			throws RemoteException;

	/**
	 * ��������getModuleCompletedCourseList 
	 * ����������ͨ��ģ�����õ��γ��б�ÿ�ſγ̺���17�����ԣ�
	 * @param module
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getModuleCompletedCourseList(String module)
			throws RemoteException;
	
	/**
	 * ��������checkYXCompletedcourseList 
	 * ����������ͨ��Ժϵ�õ���Ժϵȫ���γ̣�ÿ�ſγ̺���17�����ԣ�
	 * @param institution
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkYXCompletedcourseList(String institution)
			throws RemoteException; 

}
