package dataservice.framedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.frame.FramePO;

/**
 * �ӿ�����FrameDataService
 * �ӿ�������Frameģ���ṩ�Ķ���ӿڣ�������frame.accdb�йصķ���
 * @author Just Coding��
 */
public interface FrameDataService {
	
	/**
	 * ��������addFrame
	 * �������������������ܲ���
	 * @param po
	 * @return       
	 * @throws RemoteException
	 */
	public void addFrame(FramePO po);
	
	/**
	 * ��������deleteFrame
	 * ����������ɾ�������ܲ���
	 * @param frameId
	 * @return       
	 * @throws RemoteException
	 */
	public void deleteFrame(int frameId);
	
	/**
	 * ��������modifyFrame
	 * �����������޸������ܲ���
	 * @param oldFrameId
	 * @param newFramePO
	 * @return       
	 * @throws RemoteException
	 */
	public void modifyFrame(int oldFrameId, FramePO newFramePO);
	
	/**
	 * ��������getFrameList
	 * �����������鿴�����ܲ���
	 * @param updatable
	 * @return       
	 * @throws RemoteException
	 */
	public ArrayList<FramePO> getFrameList(boolean updatable);
	
	/**
	 * ��������moduleExist
	 * ��������������ģ�����Ϳγ�����жϸ�ģ���Ƿ����
	 * @param courseModule
	 * @param category
	 * @see FrameBLServiceImpl
	 * @return       
	 * @throws RemoteException
	 */
	public boolean moduleExist(String courseModule, String category);
	
	/**
	 * ��������getModuleCreditUpper
	 * ��������������ģ�����õ�ģ������
	 * @param courseModule
	 * @see CouresBLServiceImpl
	 * @return creditUpper   
	 * @throws RemoteException
	 */
	public int getModuleCreditUpper(String courseModule);
	
	/**
	 * ��������getFrameById
	 * ��������������frameId���������ܲ���
	 * @param frameId
	 * @see FMServiceImpl
	 * @return ������ظ������ܲ��ԣ���frameId��Ӧ�������ܲ��Դ���
	 *         �������null����frameId��Ӧ�������ܲ��Բ�����    
	 * @throws RemoteException
	 */
	public FramePO getFrameById(int frameId);
	
	/**
	 * ��������checkCredit
	 * ��������������creditUpper��creditLower����ѧ���Ƿ���Ϲ��
	 * @param creditUpper
	 * @param creditLower
	 * @see FMServiceImpl
	 * @return �������1��ѧ�ַ���Ҫ�� 
	 *         �������-1��ѧ������<ѧ������
	 *         �������-2��ѧ������<0
	 *         �������-3��ѧ������<0        
	 * @throws RemoteException
	 */
	public int checkCredit(int creditUpper, int creditLower);

}