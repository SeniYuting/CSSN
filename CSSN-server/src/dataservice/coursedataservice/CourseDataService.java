package dataservice.coursedataservice;

import java.util.ArrayList;
import po.course.CoursePO;

/**
 * �ӿ�����CourseDataService
 * �ӿ�������Courseģ�����ݲ��ṩ�Ķ���ӿ�
 * @author Just Coding��
 * 
 */
public interface CourseDataService {
	
	/**
	 * ��������addCourse 
	 * �������������ӿγ�
	 * @param po 
	 */
	public void addCourse(CoursePO po);
	
	/**
	 * ��������deleteCourse 
	 * ����������ɾ���γ�
	 * @param coID 
	 */
	public void deleteCourse(String coID);
	
	/**
	 * ��������getCourseList 
	 * �����������õ������γ��б�
	 * @param updatable
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseList(boolean updatable);
	
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
	 * ��������getCourseById 
	 * �����������ɿγ̺Ż��һ�ſγ̣�ȫУ�����γ̣�
	 * @param coId
	 * @return CoursePO
	 */
	public CoursePO getCourseById(String coId);
	
	/**
	 * ��������getCourseByInstitution 
	 * �����������ɿ���Ժϵ��ÿγ��б�
	 * @param institution
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseByInstitution(String institution);
	
	/**
	 * ��������getCourseByIDAndIns 
	 * �����������ɿ���Ժϵ�Ϳγ̺Ż�������γ���Ϣ
	 * @param coID
	 * @param institution
	 * @return CoursePO
	 */
	public CoursePO getCourseByIDAndIns(String coID, String institution);
	
	/**
	 * ��������getCourseByTeaID 
	 * ��������������ʦ���Ż�ÿγ��б�
	 * @param teaID
	 * @return ArrayList<CoursePO> 
	 */
	public ArrayList<CoursePO> getCourseByTeaID(String teaID);
	
	/**
	 * ��������getTotalByModule 
	 * �����������ɿγ�ģ���ô˿γ�ģ����ܷ�
	 * @param module
	 * @return 
	 */
	public int getTotalByModule(String module);
	
	/**
	 * ��������getTeacherCourse 
	 * �����������ɽ�ʦ���ŵõ���ʦ���ڵĿγ�
	 * @param teacherId
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getTeacherCourse(String teacherId);
	
	/**
	 * ��������getCourseByModule 
	 * �����������ɿγ�ģ��õ��γ��б�
	 * @param module
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseByModule(String module);
	
	/**
	 * ��������getAllCourseList 
	 * �����������õ����пγ��б�
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getAllCourseList();

}
