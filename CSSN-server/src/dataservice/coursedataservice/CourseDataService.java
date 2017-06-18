package dataservice.coursedataservice;

import java.util.ArrayList;
import po.course.CoursePO;

/**
 * 接口名：CourseDataService
 * 接口描述：Course模块数据层提供的对外接口
 * @author Just Coding队
 * 
 */
public interface CourseDataService {
	
	/**
	 * 方法名：addCourse 
	 * 方法描述：增加课程
	 * @param po 
	 */
	public void addCourse(CoursePO po);
	
	/**
	 * 方法名：deleteCourse 
	 * 方法描述：删除课程
	 * @param coID 
	 */
	public void deleteCourse(String coID);
	
	/**
	 * 方法名：getCourseList 
	 * 方法描述：得到完整课程列表
	 * @param updatable
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseList(boolean updatable);
	
	/**
	 * 方法名：isExist 
	 * 方法描述：判断课程号或课程名是否已经在课程列表中存在
	 * @param coID
	 * @param coName
	 * @return 如果返回true则已经存在
	 *         如果返回false则不存在
	 */
	public boolean isExist(String coID, String coName);
	
	/**
	 * 方法名：getCourseById 
	 * 方法描述：由课程号获得一门课程（全校完整课程）
	 * @param coId
	 * @return CoursePO
	 */
	public CoursePO getCourseById(String coId);
	
	/**
	 * 方法名：getCourseByInstitution 
	 * 方法描述：由开设院系获得课程列表
	 * @param institution
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseByInstitution(String institution);
	
	/**
	 * 方法名：getCourseByIDAndIns 
	 * 方法描述：由开设院系和课程号获得完整课程信息
	 * @param coID
	 * @param institution
	 * @return CoursePO
	 */
	public CoursePO getCourseByIDAndIns(String coID, String institution);
	
	/**
	 * 方法名：getCourseByTeaID 
	 * 方法描述：由老师工号获得课程列表
	 * @param teaID
	 * @return ArrayList<CoursePO> 
	 */
	public ArrayList<CoursePO> getCourseByTeaID(String teaID);
	
	/**
	 * 方法名：getTotalByModule 
	 * 方法描述：由课程模块获得此课程模块的总分
	 * @param module
	 * @return 
	 */
	public int getTotalByModule(String module);
	
	/**
	 * 方法名：getTeacherCourse 
	 * 方法描述：由教师工号得到教师教授的课程
	 * @param teacherId
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getTeacherCourse(String teacherId);
	
	/**
	 * 方法名：getCourseByModule 
	 * 方法描述：由课程模块得到课程列表
	 * @param module
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseByModule(String module);
	
	/**
	 * 方法名：getAllCourseList 
	 * 方法描述：得到所有课程列表
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getAllCourseList();

}
