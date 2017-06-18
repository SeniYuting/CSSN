package businesslogic.userbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.user.UserPO;
import vo.user.UserVO;
import businesslogicservice.userblservice.UserBLService;
import data.userdata.UserDAO;
import dataservice.userdataservice.UserDataService;

/**
 * The <code><b>UQServiceImpl</b></code> in the server-side contains method to
 * validate the <code>UserPO</code>. And transfer the <code>UserPO</code> to the
 * <code>UserVO</code>.
 * 
 * @author CaoYuting
 * 
 */
public class UserBLServiceImpl extends UnicastRemoteObject implements UserBLService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDataService usdservice;

	/**
	 * ��������UserBLServiceImpl
	 * �������������췽��
	 * @throws RemoteException
	 */
	public UserBLServiceImpl() throws RemoteException {
		super();
		usdservice = UserDAO.getInstance();
	}

	/**
	 * ��������getUserList
	 * �����������õ��û��б�
	 * @return ArrayList<UserVO>
	 */
	public ArrayList<UserVO> getUserList() throws RemoteException {
		ArrayList<UserPO> userPOList = usdservice.getUserList(true);
		ArrayList<UserVO> userVOList = new ArrayList<UserVO>();
		for (UserPO po : userPOList) {
			userVOList.add(new UserVO(po.getIdNum(), po.getUserName(), po
					.getPassword(), po.getIdentity(), po.getInstitute(), po
					.getGrade()));
		}
		return userVOList;
	}

	/**
	 * ��������getUserByIdNum
	 * �����������ο���ʦ��д�γ���Ϣ
	 * @param idNum
	 * @return ����UserVO
	 * @throws RemoteException
	 */
	public UserVO getUserByIdNum(String idNum) throws RemoteException {
		UserPO userpo = usdservice.getUserByIdNum(idNum);
		UserVO uservo = new UserVO(userpo.getIdNum(), userpo.getUserName(),
				userpo.getPassword(), userpo.getIdentity(),
				userpo.getInstitute(), userpo.getGrade());
		return uservo;
	}

	/**
	 * ��������getAllTeacher
	 * �����������õ����н�ʦ
	 * @return  ArrayList<UserVO>
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getAllTeacher() {
		ArrayList<UserVO> teacherVOList = new ArrayList<UserVO>();
		ArrayList<UserPO> teacherPOList = usdservice.getAllTeacher();
		for (int i = 0; i < teacherPOList.size(); i++) {
			teacherVOList
					.add(new UserVO(teacherPOList.get(i).getIdNum(),
							teacherPOList.get(i).getUserName(), teacherPOList
									.get(i).getPassword(), teacherPOList.get(i)
									.getIdentity()));
		}
		return teacherVOList;
	}

	/**
	 * ��������getYXStuID
	 * �����������õ�Ժϵѧ���б�
	 * @param   institution
	 * @return  ArrayList<UserVO>
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getYXStuID(String institution)
			throws RemoteException {
		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		ArrayList<UserPO> userpoList = usdservice
				.getStudentByInstitute(institution);

		for (int i = 0; i < userpoList.size(); i++) {
			UserPO user = userpoList.get(i);
			userList.add(new UserVO(user.getIdNum(), user.getUserName(), user
					.getPassword(), user.getIdentity(), user.getInstitute(),
					user.getGrade()));
		}
		return userList;
	}

	/**
	 * ��������deleteUser
	 * ����������ɾ���û�
	 * @param   idNum
	 * @throws RemoteException
	 */
	public void deleteUser(String idNum) throws RemoteException {
		usdservice.deleteUser(idNum);
	}

	/**
	 * ��������updatePassword
	 * �����������޸�����
	 * @param   idNum
	 * @param   newPassword
	 * @throws RemoteException
	 */
	public void updatePassword(String idNum, String newPassword)
			throws RemoteException {
		usdservice.updatePassword(idNum, newPassword);
	}

	/**
	 * ��������addUser
	 * ���������������û�
	 * @param   UserVO
	 * @throws RemoteException
	 */
	public boolean addUser(UserVO vo) throws RemoteException {
		UserPO po = new UserPO(vo.getIdNum(), vo.getUserName(),
				vo.getPassword(), vo.getIdentity(), vo.getInstitute(),
				vo.getGrade());
		if(usdservice.addUser(po)){
			return false;//��������û���������0;
		}
		return true;
	}
	
	/**
	 * ��������modifyUserInfo
	 * �����������޸��û���Ϣ
	 * @param   UserVO
	 * @throws RemoteException
	 */
	public void modifyUserInfo(UserVO vo) throws RemoteException {
		usdservice.modifyUser(vo.getIdNum(),
				new UserPO(vo.getIdNum(), vo.getUserName(), vo.getPassword(),
						vo.getIdentity(), vo.getInstitute(), vo.getGrade()));
	}
	
	/**
	 *��������getAllStuId
	 *�����������õ�����ѧ��ѧ��
	 *@return ArrayList<UserPO>
	 *@throw RemoteException
	 */
	public ArrayList<UserPO> getAllStuID() throws RemoteException {
		return usdservice.getAllStudent();
	}
	
	/**
	 *��������getYXStuID2
	 *�����������õ�Ժϵѧ��
	 *@param  institution
	 *@param  String newPassword
	 *@return ArrayList<UserPO>
	 *@throw RemoteException
	 */
	public ArrayList<UserPO> getYXStuID2(String institution) throws RemoteException {
		return usdservice.getStudentByInstitute(institution);
	}

	public ArrayList<UserVO> getYXTeacher(String institution) throws RemoteException {
		ArrayList<UserPO> userpoList = usdservice.getTeacherByInstitute(institution);
		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		
		for (int i = 0; i < userpoList.size(); i++) {
			UserPO user = userpoList.get(i);
			userList.add(new UserVO(user.getIdNum(), user.getUserName()));
		}
		return userList;
	}


}
