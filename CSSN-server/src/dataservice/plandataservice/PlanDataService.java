package dataservice.plandataservice;

import java.util.ArrayList;

import po.plan.PlanPO;
import vo.plan.Course;

/**
 * 接口名：PlanDataService
 * 接口描述：Plan模块提供的对外接口，包含与plan.accdb有关的方法
 * @author Just Coding队
 */
public interface PlanDataService {
	
	/**
	 * 方法名：checkPlan
	 * 方法描述：查看院系教学计划
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return       
	 * @throws RemoteException
	 */
	public PlanPO checkPlan(String institute,int grade,int semester);
	
	/**
	 * 方法名：inputPlan
	 * 方法描述：输入本院教学计划
	 * @param plan
	 * @return       
	 * @throws RemoteException
	 */
	public void inputPlan(PlanPO plan);
	
	/**
	 * 方法名：getOpenList
	 * 方法描述：查看开放选修课程列表
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return       
	 * @throws RemoteException
	 */
	public ArrayList<Course> getOpenList(String institute,int grade,int semester);
	
	/**
	 * 方法名：getMajorList
	 * 方法描述：查看学科专业课程列表
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return       
	 * @throws RemoteException
	 */
	public ArrayList<Course> getMajorList(String institute,int grade,int semester);

}
