package dataservice.coursedataservice;

import java.util.ArrayList;

import po.course.CoursePO;

/**
 * �ӿ�����YXCourseDataService
 * �ӿ�������Courseģ�����ݲ��ṩ�Ķ���ӿ�
 * @author Just Coding��
 * 
 */
public interface YXCourseDataService {
	
	/**
	 * ��������addYXCourse 
	 * �������������ӿγ�
	 * @param po 
	 */
	public void addYXCourse(CoursePO po);
	
	/**
	 * ��������deleteYXCourse 
	 * ����������ɾ���γ�
	 * @param coID 
	 */
	public void deleteYXCourse(String coID);
	
	/**
	 * ��������modifyYXCourse 
	 * �����������޸Ŀγ���Ϣ
	 * @param oldCoId
	 * @param newCoursePO
	 */
	public void modifyYXCourse(String oldCoId, CoursePO newCoursePO);
	
	/**
	 * ��������getYXCourseList 
	 * �����������õ������γ��б�14�����ԣ�
	 * @param updatable
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getYXCourseList(boolean updatable);
	
	/**
	 * ��������isExist 
	 * �����������жϿγ̺Ż�γ����Ƿ��Ѿ��ڿγ��б��д���
	 * @param coID
	 * @param coName
	 * @return �������true���Ѿ�����
	 *         �������false�򲻴���
	 */
	public boolean isExist(String coID, String coName);
	
	/**
	 * ��������getYXCourseById 
	 * �����������ɿγ̺Ż��һ�ſγ̣�14�����ԣ�
	 * @param coId
	 * @return CoursePO
	 */
	public CoursePO getYXCourseById(String coId);
	
	/**
	 * ��������getTotalByModule 
	 * �����������ɿγ�ģ���ô˿γ�ģ����ܷ�
	 * @param module
	 * @return 
	 */
	public int getTotalByModule(String module);
	
	/**
	 * ��������getCourseToFinishInfo 
	 * �����������ɽ�ʦ���ŵõ���ʦӦ����д��Ϣ�Ŀγ�
	 * @param teacherId
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseToFinishInfo(String teacherId);
	
	/**
	 * ��������getCourseByInstitution 
	 * �����������õ�Ժϵ������ʦ�Ѿ�����������Ϣδ��д�����Ŀγ��б�
	 * @param institution
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseByInstitution(String institution);
	
	/**
	 * ��������getCourseById 
	 * �����������ɿγ̺Ż��һ�ſγ̣�14�����ԣ�
	 * @param coId
	 * @return CoursePO
	 */
	public CoursePO getCourseById(String coId);

}
