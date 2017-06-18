package businesslogicservice.frameblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.frame.FrameVO;

/**
 * �ӿ�����FrameBLService
 * �ӿ�������Frameģ���ṩ�Ķ���ӿڣ�������frame.accdb�йصķ���
 * @author Just Coding��
 */
public interface FrameBLService extends Remote {

	/**
	 * ��������getFrameList
	 * ����������������ʦ�鿴�����ܲ���
	 * @param 
	 * @return �����ܲ���
	 * @throws RemoteException
	 */
	public ArrayList<FrameVO> getFrameList() throws RemoteException;
	
	/**
	 * ��������getModuleNameList
	 * �����������õ������ܲ��Ե�����ģ����
	 * @param 
	 * @return ģ����
	 * @throws RemoteException
	 */
	public String[] getModuleNameList() throws RemoteException;
	
	/**
	 * ��������getFrameById
	 * ��������������frameId����ĳһ�����ܲ���
	 * @param 
	 * @return ������ظ������ܲ��ԣ���frameId��Ӧ�������ܲ��Դ���
	 *         �������null����frameId��Ӧ�������ܲ��Բ�����   
	 * @throws RemoteException
	 */
	public FrameVO getFrameById(int frameId) throws RemoteException;
	
	/**
	 * ��������addFrameVO
	 * �������������������ܲ���
	 * @param vo
	 * @return �������1����ӳɹ�
	 *         �������0����ģ���Ѵ���
	 *         �������-1��ѧ������<ѧ������
	 *         �������-2��ѧ������<0
	 *         �������-3��ѧ������<0         
	 * @throws RemoteException
	 */
	public int addFrameVO(FrameVO vo) throws RemoteException;
	
	/**
	 * ��������deleteFrameVO
	 * ����������ɾ�������ܲ���
	 * @param frameId
	 * @return �������1��ɾ���ɹ�
	 *         �������-1��frameId������        
	 * @throws RemoteException
	 */
	public int deleteFrameVO(int frameId) throws RemoteException;
	
	/**
	 * ��������modifyFrameVO
	 * �����������޸������ܲ���
	 * @param oldFrameId
	 * @param newFrameVO
	 * @return �������1���޸ĳɹ�
	 *         �������-1��oldFrameId������     
	 *         �������-2��ѧ������>ѧ������   
	 * @throws RemoteException
	 */
	public int modifyFrameVO(int oldFrameId, FrameVO newFrameVO)
			throws RemoteException;
}

