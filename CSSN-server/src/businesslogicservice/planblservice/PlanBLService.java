package businesslogicservice.planblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.plan.Course;
import vo.plan.PlanVO;

/**
 * �ӿ�����PlanBLService
 * �ӿ�������Planģ���ṩ�Ķ���ӿڣ�������plan.accdb�йصķ���
 * @author Just Coding��
 */
public interface PlanBLService extends Remote{
	
	/**
	 * ��������checkPlan
	 * ����������������ʦ�鿴Ժϵ��ѧ�ƻ�
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return Ժϵ��ѧ�ƻ�
	 * @throws RemoteException
	 */
	public PlanVO checkPlan(String institute,int grade,int semester) throws RemoteException;
	
	/**
	 * ��������inputPlan
	 * ����������Ժϵ������ʦ����Ժϵ��ѧ�ƻ�
	 * @param plan
	 * @return
	 * @throws RemoteException
	 */
	public void inputPlan(PlanVO plan) throws RemoteException;
	
	/**
	 * ��������getOpenList
	 * ��������������Ժϵ�����꼶������ѧ�ڵõ�Ժϵ����ѡ��ģ��Ŀγ��б�
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<Course> getOpenList(String institute,int grade,int semester)throws RemoteException;
	
	/**
	 * ��������getMajorList
	 * ��������������Ժϵ�����꼶������ѧ�ڵõ�Ժϵרҵ����ģ��Ŀγ��б�
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<Course> getMajorList(String institute,int grade,int semester)throws RemoteException;
}
