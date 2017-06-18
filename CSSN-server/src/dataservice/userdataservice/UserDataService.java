package dataservice.userdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.user.UserPO;
import vo.user.Identity;

public interface UserDataService {

	/**
	 * ��������addUser
	 * ���������������û�
	 * @param   UserPO
	 * @throws RemoteException
	 */
	public boolean addUser(UserPO po);

	/**
	 * ��������deleteUser
	 * ����������ɾ���û�
	 * @param   idNum
	 * @throws RemoteException
	 */
	public void deleteUser(String idNum);

	/**
	 * ��������modifyUserInfo
	 * �����������޸��û���Ϣ
	 * @param   UserPO
	 */
	public void modifyUser(String oldIdNum, UserPO newUserPO);

	/**
	 * ��������getUserList
	 * �����������õ��û��б�
	 * @return ArrayList<UserPO>
	 */
	public ArrayList<UserPO> getUserList(boolean updatable);

	/**
	 * ��������updatePassword
	 * �����������޸�����
	 * @param   idNum
	 * @param   newPassword
	 * @throws RemoteException
	 */
	public int updatePassword(String idNum, String newPassword);

	/**
	 * check whether the name is already exist in the given identity category.
	 * 
	 * @param name
	 * @param identity
	 * @return nameExist
	 */
	public boolean nameExist(String idNum, Identity identity);

	/**
	 * check whether the idNum is already exist in the given identity category.
	 * 
	 * @param idNum
	 * @param identity
	 * @return idNumExist
	 */
	
	public UserPO getUserByIdNum(String idNum);

	/**
	 * �������� getStudentByInstitute
	 * �����������õ�����Ժϵ��ѧ��
	 * @param institute
	 * @return  ArrayList<UserPO> 
	 * 
	 */
	public ArrayList<UserPO> getStudentByInstitute(String institute);

	/**
	 * �������� getAllStudent
	 * �����������õ�����ѧ��
	 * @return  ArrayList<UserPO> 
	 */
	public ArrayList<UserPO> getAllStudent();

	/**
	 * �������� getAllTeacher
	 * �����������õ����н�ʦ
	 * @return  ArrayList<UserPO> 
	 */
	public ArrayList<UserPO> getAllTeacher();

	/**
	 * <code>check the name and password, 
	 * if the name does not exist, return value "-1",
	 * else if the password is not matched, return "-2", 
	 * else return the user's name.</code>
	 * 
	 * @param name
	 * @param password
	 * @param identity
	 * @return
	 */
	public String checkNamePasswd(String idNum, String passwd, Identity identity);

	/**
	 * �������� getUserByIdentity
	 * ����������������ݷ����û��б�
	 * @param identity
	 * @return  ArrayList<UserPO> 
	 */
	public ArrayList<UserPO> getUserByIdentity(String identity);
	
	/**
	 * �������� getTeacherByInstitute
	 * ��������������Ժϵ���ؽ�ʦ�б�
	 * @param identity
	 * @return  ArrayList<UserPO> 
	 */
	public ArrayList<UserPO> getTeacherByInstitute(String institute);

}
