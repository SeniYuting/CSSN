package businesslogicservice.courseblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.course.CoursePO;
import vo.course.CourseVO;

/**
 * 接口名：CouresBLService
 * 接口描述：Course模块提供的对外接口，包含与course.accdb有关的方法
 * @author Just Coding队
 * 
 */
public interface CourseBLService extends Remote {
 
	/**
	 * 方法名：publishYXCourse 
	 * 方法描述：院系教务老师发布课程
	 * @param vo 
	 * @return 如果返回-1则课程号或课程名已经存在；
	 * 		        如果返回-2则学分不符合要求
	 * 		        如果返回-3则课时不符合要求
	 * 		        如果返回-4则开课人数不符合要求
	 *  	        如果返回-5则单门课程的学分过大
	 *         如果返回-6则单门课程的课时过大
	 *         如果返回0则该模块总学分已经超过计划的上限
	 *         如果返回1则发布成功
	 * @throws RemoteException
	 */
	public int publishYXCourse(CourseVO vo) throws RemoteException;


	/**
	 * 方法名：publishJWCourse 
	 * 方法描述：教务处老师发布课程
	 * @param vo 
	 * @return 如果返回-1则课程号或课程名已经存在；
	 * 		        如果返回-2则学分不符合要求
	 * 		        如果返回-3则课时不符合要求
	 * 		        如果返回-4则开课人数不符合要求
	 *  	        如果返回-5则单门课程的学分过大
	 *         如果返回-6则单门课程的课时过大
	 *         如果返回0则该模块总学分已经超过计划的上限
	 *         如果返回1则发布成功
	 * @throws RemoteException
	 */
	public int publishJWCourse(CourseVO vo) throws RemoteException;

	 
	/**
	 * 方法名：modifyCourseInfo 
	 * 方法描述：院系教务老师修改课程信息
	 * @param oldCoID
	 * @param newCourseVO
	 * @return 如果返回-2则学分不符合要求
	 * 		        如果返回-3则课时不符合要求
	 * 		        如果返回-4则开课人数不符合要求
	 *  	        如果返回-5则单门课程的学分过大
	 *         如果返回-6则单门课程的课时过大
	 *         如果返回1则发布成功
	 * @throws RemoteException
	 */
	public int modifyCourseInfo(String oldCoID, CourseVO newCourseVO)
			throws RemoteException;

	
	/**
	 * 方法名：completeCourseInfo 
	 * 方法描述：任课老师填写课程信息
	 * @param coID
	 * @param addedVO
	 * @return 如果返回-1则不存在这门课程
	 *         如果返回1则填写成功
	 * @throws RemoteException
	 */
	public int completeCourseInfo(String coID, CourseVO addedVO)
			throws RemoteException;
 
	/**
	 * 方法名：getCourseToFinishInfo 
	 * 方法描述：由教师工号得到教师应该填写信息的课程
	 * @param teacherId
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getCourseToFinishInfo(String teacherId)
			throws RemoteException;
	 
	/**
	 * 方法名：getTeacherCourse 
	 * 方法描述：由教师工号得到教师教授的课程
	 * @param teacherId
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getTeacherCourse(String teacherId)
			throws RemoteException;
 
	/**
	 * 方法名：checkYXcourseList 
	 * 方法描述：院系教务老师查看本院课程列表
	 * @param institution
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkYXcourseList(String institution)
			throws RemoteException;

	/**
	 * 方法名：checkYXcourseInfo 
	 * 方法描述：院系教务老师查看本院任意课程信息
	 * @param coID
	 * @param institution
	 * @return CourseVO
	 * @throws RemoteException
	 */
	public CourseVO checkYXcourseInfo(String coID, String institution)
			throws RemoteException;

	/**
	 * 方法名：checkAnyCourseInfo 
	 * 方法描述：学生查看任意课程信息
	 * @param coID
	 * @return CourseVO
	 * @throws RemoteException
	 */
	public CourseVO checkAnyCourseInfo(String coID) throws RemoteException;

	/**
	 * 方法名：getModuleCourseList 
	 * 方法描述： 由课程模块得到课程列表
	 * @param coID
	 * @return CourseVO
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getModuleCourseList(String module)
			throws RemoteException;

	/**
	 * 方法名：getAllCourseList 
	 * 方法描述： 得到全校的课程列表
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getAllCourseList() throws RemoteException;
 
	/**
	 * 方法名：checkMycourseList 
	 * 方法描述：得到院系教务老师已经发布的且信息未填写完整的课程列表
	 * @param institution
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkMycourseList(String institution)
			throws RemoteException;
 
	/**
	 * 方法名：checkYXUnfinishedcourseInfo 
	 * 方法描述：院系教务老师查看未填写完整信息的课程列表的信息
	 * @param coID
	 * @return CourseVO
	 * @throws RemoteException
	 */
	public CourseVO checkYXUnfinishedcourseInfo(String coID)
			throws RemoteException;
 
	/**
	 * 方法名：getTTeacherCourse 
	 * 方法描述：通过老师的工号获得老师所教课程
	 * @param id
	 * @return ArrayList<CoursePO>
	 * @throws RemoteException
	 */
	public ArrayList<CoursePO> getTTeacherCourse(String id)
			throws RemoteException;

	/**
	 * 方法名：getModuleCompletedCourseList 
	 * 方法描述：通过模块名得到课程列表（每门课程含有17个属性）
	 * @param module
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> getModuleCompletedCourseList(String module)
			throws RemoteException;
	
	/**
	 * 方法名：checkYXCompletedcourseList 
	 * 方法描述：通过院系得到该院系全部课程（每门课程含有17个属性）
	 * @param institution
	 * @return ArrayList<CourseVO>
	 * @throws RemoteException
	 */
	public ArrayList<CourseVO> checkYXCompletedcourseList(String institution)
			throws RemoteException; 

}
