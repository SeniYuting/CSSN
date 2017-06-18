package dataservice.courseselectiondataservice;

import java.util.ArrayList;

import po.courseselection.CourseSelectionPO;

/**
 * 接口名：CourseSelectionDataService
 * 接口描述：CourseSelection模块提供的对外接口，包含与courseSelection.accdb有关的方法
 * @author Just Coding队
 * 
 */
public interface CourseSelectionDataService {
	/**
	 * 方法名：addCourseSelection
	 * 方法描述：添加选课记录
	 * @param po
	 */
	public void addCourseSelection(CourseSelectionPO po);

	/**
	 * 方法名：deleteCourseSelection
	 * 方法描述：删除选课记录
	 * @param courseNO 
	 * @param stuNO
	 */
	public void deleteCourseSelection(String courseNO, String stuNO);

	/**
	 * 方法名：getCourseSelectionByStuNO
	 * 方法描述：由学生学号获得该学生的选课列表
	 * @param stuNO
	 * @param state
	 * @return 返回该学生的选课列表
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionByStuNO(String stuNO,String state);

	/**
	 * 方法名：getCourseSelectionByCourseNO
	 * 方法描述：由课程号和教师工号得到选择该课程的学生列表
	 * @param courseNO
	 * @param teacherNO
	 * @return 返回满足条件的学生列表
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionByCourseNO(String courseNO, String teacherNO); 


	/**
	 * 方法名：getCourseSelectionByCourseNO
	 * 方法描述：由课程号得到选择该课程的学生列表
	 * @param courseNO
	 * @return 返回满足条件的学生列表
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionByCourseNO(
			String courseNO);

	/**
	 * 方法名：getState
	 * 方法描述：获得某位学生某门课程即时的选课状态（即选没选中）
	 * @param stuNO
	 * @param courseNO
	 * @return 该学生该课程即时的选课状态
	 */
	public String getState(String stuNO,String courseNO);

	/**
	 * 方法名：setState
	 * 方法描述：设置某位学生的某门课程的选课状态为选中
	 * @param stuNO
	 * @param courseNO
	 * @param state
	 */
	public void setState(String stuNO,String courseNO,String state);
}
