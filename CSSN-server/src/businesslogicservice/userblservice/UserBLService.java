package businesslogicservice.userblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.user.UserPO;
import vo.user.UserVO;

/**
 * The User Interface. It contains methods to query the user database system.
 * And transfer UserVO to the client.
 * 
 * @author CaoYuting
 * 
 */
public interface UserBLService extends Remote {

	/**
	 * ��������getUserList
	 * �����������õ��û��б�
	 * @return ArrayList<UserVO>
	 */
	public ArrayList<UserVO> getUserList() throws RemoteException;

	/**
	 * ��������getUserByIdNum
	 * �������������ݹ��ŵõ��û�������Ϣ
	 * @param idNum
	 * @return ����UserVO
	 * @throws RemoteException
	 */
	public UserVO getUserByIdNum(String idNum) throws RemoteException;

	/**
	 * ��������getAllTeacher
	 * �����������õ����н�ʦ
	 * @return  ArrayList<UserVO>
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getAllTeacher() throws RemoteException;


	/**
	 * ��������getYXStuID
	 * �����������õ�Ժϵѧ���б�
	 * @param   institution
	 * @return  ArrayList<UserVO>
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getYXStuID(String institution)
			throws RemoteException;

	/**
	 * ��������deleteUser
	 * ����������ɾ���û�
	 * @param   idNum
	 * @throws RemoteException
	 */
	public void deleteUser(String idNum) throws RemoteException;

	/**
	 * ��������updatePassword
	 * �����������޸�����
	 * @param   idNum
	 * @param   newPassword
	 * @throws RemoteException
	 */
	public void updatePassword(String idNum, String newPassword)
			throws RemoteException;

	/**
	 * ��������addUser
	 * ���������������û�
	 * @param   UserVO
	 * @throws RemoteException
	 */
	public boolean addUser(UserVO vo) throws RemoteException;

	/**
	 * ��������modifyUserInfo
	 * �����������޸��û���Ϣ
	 * @param   UserVO
	 * @throws RemoteException
	 */
	public void modifyUserInfo(UserVO vo) throws RemoteException;
	
	/**
	 *��������getAllStuId
	 *�����������õ�����ѧ��ѧ��
	 *@return ArrayList<UserPO>
	 *@throw RemoteException
	 */
	public ArrayList<UserPO> getAllStuID() throws RemoteException;
	
	/**
	 *��������getYXStuID2
	 *�����������õ�Ժϵѧ��
	 *@param  institution
	 *@param  String newPassword
	 *@return ArrayList<UserPO>
	 *@throw RemoteException
	 */
	public ArrayList<UserPO> getYXStuID2(String institution) throws RemoteException;
	
	/**
	 * ��������getYXTeacher
	 * ��������������Ժϵ�õ�Ժϵ������ʦ
	 * @param Institution
	 * @return ArrayList<UserVO>����Ժϵ������ʦ�б�
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getYXTeacher(String institution) throws RemoteException;

}
