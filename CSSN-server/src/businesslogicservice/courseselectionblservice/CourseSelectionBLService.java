package businesslogicservice.courseselectionblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.course.CourseVO;
import vo.user.UserVO;

/**
 * 接口名：CourseSelectionBLService
 * 接口描述：CourseSelection模块提供的对外接口，包含与courseSelection.accdb有关的方法
 * @author Just Coding队
 * 
 */
public interface CourseSelectionBLService extends Remote{
	/**
	 * 方法名：checkMyCourse 
	 * 方法描述：查看我的课程列表
	 * @param id
	 * @param semester
	 * @param state
	 * @return 返回满足条件的我的课程列表
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkMyCourse(String id,String semester,String state) throws RemoteException;
	
	/**
	 * 方法名：quitCourse
	 * 方法描述：退选课程
	 * @param courseNO 
	 * @param id
	 * @return 返回退选结果
	 * @throws RemoteException
	 */
	public String quitCourse(String courseNO,String id)  throws RemoteException;

	/**
	 * 方法名：chooseCourse 
	 * 方法描述：选择课程
	 * @param courseNO
	 * @param id
	 * @return 返回选课结果
	 * @throws RemoteException
	 */
	public String chooseCourse(String courseNO,String id) throws RemoteException;
	
	/**
	 * 方法名：choosePELesson 
	 * 方法描述：选体育课，先到先得
	 * @param courseNO
	 * @param stuNO
	 * @param teacherNO
	 * @return 返回体育选课结果
	 * @throws RemoteException
	 */
	public String choosePELesson(String courseNO,String stuNO,String teacherNO) throws RemoteException;

	/**
	 * 方法名：checkAnyCourseStu
	 * 方法描述：查看任意课程的选课学生列表
	 * @param courseNO 
	 * @return 返回满足条件的学生列表
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> checkAnyCourseStu(String courseNO) throws RemoteException;

	/**
	 * 方法名：checkMyCourseStu
	 * 方法描述：查看自己课程的选课学生列表
	 * @param courseNO
	 * @param teacherNO
	 * @return 返回满足条件的学生列表
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> checkMyCourseStu(String courseNO,String teacherNO) throws RemoteException;

	/**
	 * 方法名：publishChooseTime 
	 * 方法描述：教务处老师发布选课时间
	 * @param begin
	 * @param end
	 * @return 如果返回-1则开始时间晚于结束时间；
	 *         如果返回1则发布选课时间成功
	 * @throws RemoteException
	 */
	public int publishChooseCourseTime(String begin, String end) throws RemoteException;

	/**
	 * 方法名：allocate 
	 * 方法描述：教务处老师抽签
	 * @param courseNO 
	 * @param maxGrade
	 * @return 返回中签的学生列表
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> allocate(String courseNO,String maxGrade) 
			throws RemoteException;
	
	/**
	 * 方法名：checkMyCourse 
	 * 方法描述：查看我的课程列表
	 * @param id
	 * @param state
	 * @return 返回满足条件的课程列表
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkMyCourse(String id,String state) throws RemoteException;
	
	/**
	 * 方法名：addCompulsoryCourseStu 
	 * 方法描述：自动添加必修课的选课学生列表
	 * @param courseNO
	 * @param stuNO
	 * @param teacherNO
	 * @throws RemoteException
	 */
	public void addCompulsoryCourseStu(String courseNO,String stuNO,String teacherNO) throws RemoteException;
}
