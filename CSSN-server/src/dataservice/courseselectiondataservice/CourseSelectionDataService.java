package dataservice.courseselectiondataservice;

import java.util.ArrayList;

import po.courseselection.CourseSelectionPO;

/**
 * �ӿ�����CourseSelectionDataService
 * �ӿ�������CourseSelectionģ���ṩ�Ķ���ӿڣ�������courseSelection.accdb�йصķ���
 * @author Just Coding��
 * 
 */
public interface CourseSelectionDataService {
	/**
	 * ��������addCourseSelection
	 * �������������ѡ�μ�¼
	 * @param po
	 */
	public void addCourseSelection(CourseSelectionPO po);

	/**
	 * ��������deleteCourseSelection
	 * ����������ɾ��ѡ�μ�¼
	 * @param courseNO 
	 * @param stuNO
	 */
	public void deleteCourseSelection(String courseNO, String stuNO);

	/**
	 * ��������getCourseSelectionByStuNO
	 * ������������ѧ��ѧ�Ż�ø�ѧ����ѡ���б�
	 * @param stuNO
	 * @param state
	 * @return ���ظ�ѧ����ѡ���б�
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionByStuNO(String stuNO,String state);

	/**
	 * ��������getCourseSelectionByCourseNO
	 * �����������ɿγ̺źͽ�ʦ���ŵõ�ѡ��ÿγ̵�ѧ���б�
	 * @param courseNO
	 * @param teacherNO
	 * @return ��������������ѧ���б�
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionByCourseNO(String courseNO, String teacherNO); 


	/**
	 * ��������getCourseSelectionByCourseNO
	 * �����������ɿγ̺ŵõ�ѡ��ÿγ̵�ѧ���б�
	 * @param courseNO
	 * @return ��������������ѧ���б�
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionByCourseNO(
			String courseNO);

	/**
	 * ��������getState
	 * �������������ĳλѧ��ĳ�ſγ̼�ʱ��ѡ��״̬����ѡûѡ�У�
	 * @param stuNO
	 * @param courseNO
	 * @return ��ѧ���ÿγ̼�ʱ��ѡ��״̬
	 */
	public String getState(String stuNO,String courseNO);

	/**
	 * ��������setState
	 * ��������������ĳλѧ����ĳ�ſγ̵�ѡ��״̬Ϊѡ��
	 * @param stuNO
	 * @param courseNO
	 * @param state
	 */
	public void setState(String stuNO,String courseNO,String state);
}
