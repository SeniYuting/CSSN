package businesslogicservice.statisticsblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.statistics.StatisticsVO;

public interface StatisticsBLService extends Remote{
	
	/**
	 * ��������checkTeacherStatistics
	 * �����������鿴��ʦͳ����Ϣ
	 * @param id
	 * @return ArrayList<StatisticsVO>
	 */
	public ArrayList<StatisticsVO> checkTeacherStatistics(String id) throws RemoteException;
	
	/**
	 * ��������checkCourseStatistics
	 * �����������鿴�γ�ͳ����Ϣ
	 * @param courseNO
	 * @return ArrayList<StatisticsVO>
	 */
	public StatisticsVO checkCourseStatistics(String courseNo) throws RemoteException;
	
	/**
	 * ��������checkStuStatistics
	 * �����������鿴ѧ�����ͳ������
	 * @param stuNO
	 * @return StatisticsVO
	 */
	public StatisticsVO checkStuStatistics(String stuNo)throws RemoteException;
	
	/**
	 * ��������checkGPA
	 * �����������鿴ѧ�ּ�
	 * @param stuNO
	 * @return StatisticsVO
	 */
	public StatisticsVO checkGPA(String stuNo,String grade)throws RemoteException;
	
	/**
	 * ��������checkAccess
	 * �����������鿴׼��׼��ͳ��
	 * @param stuNO
	 * @param institution
	 * @return StatisticsVO
	 */
	public StatisticsVO checkAccess(String stuNO,String institute)throws RemoteException;
	
}
