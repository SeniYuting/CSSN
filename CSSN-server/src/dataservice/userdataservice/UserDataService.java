package dataservice.userdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.user.UserPO;
import vo.user.Identity;

public interface UserDataService {

	/**
	 * 方法名：addUser
	 * 方法描述：增加用户
	 * @param   UserPO
	 * @throws RemoteException
	 */
	public boolean addUser(UserPO po);

	/**
	 * 方法名：deleteUser
	 * 方法描述：删除用户
	 * @param   idNum
	 * @throws RemoteException
	 */
	public void deleteUser(String idNum);

	/**
	 * 方法名：modifyUserInfo
	 * 方法描述：修改用户信息
	 * @param   UserPO
	 */
	public void modifyUser(String oldIdNum, UserPO newUserPO);

	/**
	 * 方法名：getUserList
	 * 方法描述：得到用户列表
	 * @return ArrayList<UserPO>
	 */
	public ArrayList<UserPO> getUserList(boolean updatable);

	/**
	 * 方法名：updatePassword
	 * 方法描述：修改密码
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
	 * 方法名： getStudentByInstitute
	 * 方法描述：得到给定院系的学生
	 * @param institute
	 * @return  ArrayList<UserPO> 
	 * 
	 */
	public ArrayList<UserPO> getStudentByInstitute(String institute);

	/**
	 * 方法名： getAllStudent
	 * 方法描述：得到所有学生
	 * @return  ArrayList<UserPO> 
	 */
	public ArrayList<UserPO> getAllStudent();

	/**
	 * 方法名： getAllTeacher
	 * 方法描述：得到所有教师
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
	 * 方法名： getUserByIdentity
	 * 方法描述：根据身份返回用户列表
	 * @param identity
	 * @return  ArrayList<UserPO> 
	 */
	public ArrayList<UserPO> getUserByIdentity(String identity);
	
	/**
	 * 方法名： getTeacherByInstitute
	 * 方法描述：根据院系返回教师列表
	 * @param identity
	 * @return  ArrayList<UserPO> 
	 */
	public ArrayList<UserPO> getTeacherByInstitute(String institute);

}
