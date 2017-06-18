package dataservice.plandataservice;

import java.util.ArrayList;

import po.plan.PlanPO;
import vo.plan.Course;

/**
 * �ӿ�����PlanDataService
 * �ӿ�������Planģ���ṩ�Ķ���ӿڣ�������plan.accdb�йصķ���
 * @author Just Coding��
 */
public interface PlanDataService {
	
	/**
	 * ��������checkPlan
	 * �����������鿴Ժϵ��ѧ�ƻ�
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return       
	 * @throws RemoteException
	 */
	public PlanPO checkPlan(String institute,int grade,int semester);
	
	/**
	 * ��������inputPlan
	 * �������������뱾Ժ��ѧ�ƻ�
	 * @param plan
	 * @return       
	 * @throws RemoteException
	 */
	public void inputPlan(PlanPO plan);
	
	/**
	 * ��������getOpenList
	 * �����������鿴����ѡ�޿γ��б�
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return       
	 * @throws RemoteException
	 */
	public ArrayList<Course> getOpenList(String institute,int grade,int semester);
	
	/**
	 * ��������getMajorList
	 * �����������鿴ѧ��רҵ�γ��б�
	 * @param institute
	 * @param grade
	 * @param semester
	 * @return       
	 * @throws RemoteException
	 */
	public ArrayList<Course> getMajorList(String institute,int grade,int semester);

}
