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
	 * 方法名：getUserList
	 * 方法描述：得到用户列表
	 * @return ArrayList<UserVO>
	 */
	public ArrayList<UserVO> getUserList() throws RemoteException;

	/**
	 * 方法名：getUserByIdNum
	 * 方法描述：根据工号得到用户具体信息
	 * @param idNum
	 * @return 返回UserVO
	 * @throws RemoteException
	 */
	public UserVO getUserByIdNum(String idNum) throws RemoteException;

	/**
	 * 方法名：getAllTeacher
	 * 方法描述：得到所有教师
	 * @return  ArrayList<UserVO>
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getAllTeacher() throws RemoteException;


	/**
	 * 方法名：getYXStuID
	 * 方法描述：得到院系学生列表
	 * @param   institution
	 * @return  ArrayList<UserVO>
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getYXStuID(String institution)
			throws RemoteException;

	/**
	 * 方法名：deleteUser
	 * 方法描述：删除用户
	 * @param   idNum
	 * @throws RemoteException
	 */
	public void deleteUser(String idNum) throws RemoteException;

	/**
	 * 方法名：updatePassword
	 * 方法描述：修改密码
	 * @param   idNum
	 * @param   newPassword
	 * @throws RemoteException
	 */
	public void updatePassword(String idNum, String newPassword)
			throws RemoteException;

	/**
	 * 方法名：addUser
	 * 方法描述：增加用户
	 * @param   UserVO
	 * @throws RemoteException
	 */
	public boolean addUser(UserVO vo) throws RemoteException;

	/**
	 * 方法名：modifyUserInfo
	 * 方法描述：修改用户信息
	 * @param   UserVO
	 * @throws RemoteException
	 */
	public void modifyUserInfo(UserVO vo) throws RemoteException;
	
	/**
	 *方法名：getAllStuId
	 *方法描述：得到所有学生学号
	 *@return ArrayList<UserPO>
	 *@throw RemoteException
	 */
	public ArrayList<UserPO> getAllStuID() throws RemoteException;
	
	/**
	 *方法名：getYXStuID2
	 *方法描述：得到院系学生
	 *@param  institution
	 *@param  String newPassword
	 *@return ArrayList<UserPO>
	 *@throw RemoteException
	 */
	public ArrayList<UserPO> getYXStuID2(String institution) throws RemoteException;
	
	/**
	 * 方法名：getYXTeacher
	 * 方法描述：根据院系得到院系教务老师
	 * @param Institution
	 * @return ArrayList<UserVO>返回院系教务老师列表
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getYXTeacher(String institution) throws RemoteException;

}
