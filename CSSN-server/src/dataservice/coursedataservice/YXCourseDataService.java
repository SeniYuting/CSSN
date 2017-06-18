package dataservice.coursedataservice;

import java.util.ArrayList;

import po.course.CoursePO;

/**
 * 接口名：YXCourseDataService
 * 接口描述：Course模块数据层提供的对外接口
 * @author Just Coding队
 * 
 */
public interface YXCourseDataService {
	
	/**
	 * 方法名：addYXCourse 
	 * 方法描述：增加课程
	 * @param po 
	 */
	public void addYXCourse(CoursePO po);
	
	/**
	 * 方法名：deleteYXCourse 
	 * 方法描述：删除课程
	 * @param coID 
	 */
	public void deleteYXCourse(String coID);
	
	/**
	 * 方法名：modifyYXCourse 
	 * 方法描述：修改课程信息
	 * @param oldCoId
	 * @param newCoursePO
	 */
	public void modifyYXCourse(String oldCoId, CoursePO newCoursePO);
	
	/**
	 * 方法名：getYXCourseList 
	 * 方法描述：得到完整课程列表（14个属性）
	 * @param updatable
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getYXCourseList(boolean updatable);
	
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
	 * 方法名：getYXCourseById 
	 * 方法描述：由课程号获得一门课程（14个属性）
	 * @param coId
	 * @return CoursePO
	 */
	public CoursePO getYXCourseById(String coId);
	
	/**
	 * 方法名：getTotalByModule 
	 * 方法描述：由课程模块获得此课程模块的总分
	 * @param module
	 * @return 
	 */
	public int getTotalByModule(String module);
	
	/**
	 * 方法名：getCourseToFinishInfo 
	 * 方法描述：由教师工号得到教师应该填写信息的课程
	 * @param teacherId
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseToFinishInfo(String teacherId);
	
	/**
	 * 方法名：getCourseByInstitution 
	 * 方法描述：得到院系教务老师已经发布的且信息未填写完整的课程列表
	 * @param institution
	 * @return ArrayList<CoursePO>
	 */
	public ArrayList<CoursePO> getCourseByInstitution(String institution);
	
	/**
	 * 方法名：getCourseById 
	 * 方法描述：由课程号获得一门课程（14个属性）
	 * @param coId
	 * @return CoursePO
	 */
	public CoursePO getCourseById(String coId);

}
