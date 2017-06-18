package businesslogicservice.planblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.plan.Course;
import vo.plan.PlanVO;

/**
 * 接口名：PlanBLService
 * 接口描述：Plan模块提供的对外接口，包含与plan.accdb有关的方法
 * @author Just Coding队
 */
public interface PlanBLService extends Remote{
	
	/**
	 * 方法名：checkPlan
	 * 方法描述：教务处老师查看院系教学计划
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return 院系教学计划
	 * @throws RemoteException
	 */
	public PlanVO checkPlan(String institute,int grade,int semester) throws RemoteException;
	
	/**
	 * 方法名：inputPlan
	 * 方法描述：院系教务老师输入院系教学计划
	 * @param plan
	 * @return
	 * @throws RemoteException
	 */
	public void inputPlan(PlanVO plan) throws RemoteException;
	
	/**
	 * 方法名：getOpenList
	 * 方法描述：根据院系名，年级，开设学期得到院系开放选修模块的课程列表
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<Course> getOpenList(String institute,int grade,int semester)throws RemoteException;
	
	/**
	 * 方法名：getMajorList
	 * 方法描述：根据院系名，年级，开设学期得到院系专业必修模块的课程列表
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<Course> getMajorList(String institute,int grade,int semester)throws RemoteException;
}
