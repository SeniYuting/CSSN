package businesslogic.userbl.loginController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import vo.user.login.LoginVO;

import businesslogicservice.userblservice.login.LoginBLService;

import data.userdata.UserDAO;
import dataservice.userdataservice.UserDataService;

/**
 * The <code><b>LoginImpl</b></code> in the server-side contains method to
 * validate the <code>LoginPO</code>. Assure the <code>LoginPO</code> has the
 * normal format. This class does not make format-check operation. It only
 * checks the database system and return the result of whether the database
 * system contains this <code>Name-password</code> record.
 * 
 * @author CaoYuting
 * 
 */
public class LoginImpl extends UnicastRemoteObject implements LoginBLService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The <code>UserPO Database Access Object</code>
	 */
	private UserDataService udservice;

	public LoginImpl() throws RemoteException {
		udservice = UserDAO.getInstance();
	}

	public String login(LoginVO vo) throws RemoteException {
		String name = vo.getName();
		String passwd = vo.getPassword();
		if (udservice.nameExist(name, vo.getIdentity())) {
			return udservice.checkNamePasswd(name, passwd, vo.getIdentity());
		} else {
			return "-1";
		}
	}

}
