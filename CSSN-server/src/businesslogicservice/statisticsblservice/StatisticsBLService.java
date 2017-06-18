package businesslogicservice.statisticsblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.statistics.StatisticsVO;

public interface StatisticsBLService extends Remote{
	
	/**
	 * 方法名：checkTeacherStatistics
	 * 方法描述：查看教师统计信息
	 * @param id
	 * @return ArrayList<StatisticsVO>
	 */
	public ArrayList<StatisticsVO> checkTeacherStatistics(String id) throws RemoteException;
	
	/**
	 * 方法名：checkCourseStatistics
	 * 方法描述：查看课程统计信息
	 * @param courseNO
	 * @return ArrayList<StatisticsVO>
	 */
	public StatisticsVO checkCourseStatistics(String courseNo) throws RemoteException;
	
	/**
	 * 方法名：checkStuStatistics
	 * 方法描述：查看学生审核统计数据
	 * @param stuNO
	 * @return StatisticsVO
	 */
	public StatisticsVO checkStuStatistics(String stuNo)throws RemoteException;
	
	/**
	 * 方法名：checkGPA
	 * 方法描述：查看学分绩
	 * @param stuNO
	 * @return StatisticsVO
	 */
	public StatisticsVO checkGPA(String stuNo,String grade)throws RemoteException;
	
	/**
	 * 方法名：checkAccess
	 * 方法描述：查看准入准出统计
	 * @param stuNO
	 * @param institution
	 * @return StatisticsVO
	 */
	public StatisticsVO checkAccess(String stuNO,String institute)throws RemoteException;
	
}
