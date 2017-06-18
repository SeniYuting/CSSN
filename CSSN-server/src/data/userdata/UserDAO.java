package data.userdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataservice.userdataservice.UserDataService;

import po.user.UserPO;
import vo.user.Identity;

/**
 * <b><code>User Database Access Object</code></b> is the class to manage the
 * database file user.accdb
 * 
 * @author CaoYuting
 */
public class UserDAO implements UserDataService{

	private static UserDAO userDAO;

	private Connection connection;

	UserDAO() {
		try {
			/* load the driver to the JVM */
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};"
				+ " DBQ=./data/user.accdb";
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static UserDAO getInstance() {
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}

	/**
	 * add a new <code><b>UserPO</b></code> to the database
	 * 
	 * @param po
	 *            the new userPO
	 */
	public boolean addUser(UserPO po) {
		
		boolean nameExist=nameExist(po.getIdNum(),po.getIdentity());
		
		if(nameExist){
			return nameExist;
		}
		
		String sql = "INSERT INTO User VALUES ('" + po.getIdNum() + "','"
				+ po.getUserName() + "','" + po.getPassword() + "','"
				+ po.getIdentity().ordinal() + "','" + po.getInstitute()
				+ "','" + po.getGrade() + "')";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nameExist;
	}

	/**
	 * delete a <code><b>UserPO</b></code> record from the database according to
	 * idNum
	 * 
	 * @param idNum
	 */
	public void deleteUser(String idNum) {
		String sql = "DELETE FROM User WHERE idNum = '" + idNum + "'";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * modify the database <code><b>UserPO</b></code>, use the newUserPO to
	 * substitute the oldUserPO.
	 * 
	 * @param oldIdNum
	 * @param newUserPO
	 */
	public void modifyUser(String oldIdNum, UserPO newUserPO) {
		String sql = "UPDATE User SET identity = '"
				+ newUserPO.getIdentity().ordinal() + "', userName = '"
				+ newUserPO.getUserName() + "',institute = '"
				+ newUserPO.getInstitute() + "', grade = '"
				+ newUserPO.getGrade() + "' WHERE idNum = '" + oldIdNum + "'";

		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get the user list
	 * 
	 * @param updatable
	 *            if true, this method will invoke updateUserList first to
	 *            ensure the data is latest
	 * @return userList
	 */

	public ArrayList<UserPO> getUserList(boolean updatable) {
		ArrayList<UserPO> userList = new ArrayList<UserPO>();
		try {
			String sql = "select * from user order by idNum";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String idNum = rs.getString(1);
				String name = rs.getString(2);
				String passwd = rs.getString(3);
				Identity identity = Identity.values()[rs.getInt(4)];
				String institute = rs.getString(5);
				String grade = rs.getString(6);
				userList.add(new UserPO(idNum, name, passwd, identity,
						institute, grade));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	/**
	 * invoke the is method to change password
	 * 
	 * @param idNum
	 * @param newPassword
	 * @return
	 */
	public int updatePassword(String idNum, String newPassword) {
		String sql = "UPDATE User SET userPassword = '" + newPassword
				+ "' WHERE idNum = '" + idNum + "'";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * check whether the name is already exist in the given identity category.
	 * 
	 * @param name
	 * @param identity
	 * @return nameExist
	 */
	// businesslogic.user包的UMServiceImpl类的addUserPO用
	public boolean nameExist(String idNum, Identity identity) {
		String id = null;
		boolean nameExist = false;
		if (identity.equals(Identity.JWTeacher)) {
			id = "0";
		} else if (identity.equals(Identity.YXTeacher)) {
			id = "1";
		} else if (identity.equals(Identity.RKTeacher)) {
			id = "2";
		} else if (identity.equals(Identity.Student)) {
			id = "3";
		} else if (identity.equals(Identity.Administrator)) {
			id = "4";
		}
		String sql = "select * from user where idNum='" + idNum
				+ "' and identity='" + id + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				nameExist = true;
				return true;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nameExist;
	}

	/**
	 * get a <code><b>UserPO</b></code> by the given idNum
	 * 
	 * @param idNum
	 * @param identity
	 * @return if the user is not available, this method will return </b>
	 *         <code>null</code></b>
	 */
	// businesslogic.user包的UMServiceImpl类的deleteUserPO用
	public UserPO getUserByIdNum(String idNum) {
		UserPO po = null;
		String sql = "select * from user where idNum='" + idNum + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				return (new UserPO(resultset.getString(1),
						resultset.getString(2), resultset.getString(3),
						Identity.values()[resultset.getInt(4)],
						resultset.getString(5), resultset.getString(6)));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return po;
	}

	/**
	 * get all students from the given institute
	 * 
	 * @return
	 * 
	 */
	public ArrayList<UserPO> getStudentByInstitute(String institute) {
		ArrayList<UserPO> studentsList = new ArrayList<UserPO>();
		String sql = "select * from user where Institute='" + institute + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				if (Identity.values()[resultset.getInt(4)] == Identity.Student)
					studentsList.add(new UserPO(resultset.getString(1),
							resultset.getString(2), resultset.getString(3),
							Identity.Student, resultset.getString(5), resultset
									.getString(6)));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentsList;
	}

	/**
	 * get all students
	 * 
	 * @return
	 * 
	 */
	public ArrayList<UserPO> getAllStudent() {
		ArrayList<UserPO> studentsList = new ArrayList<UserPO>();
		String sql = "select * from user";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String idNum = rs.getString(1);
				String name = rs.getString(2);
				String passwd = rs.getString(3);
				Identity identity = Identity.values()[rs.getInt(4)];
				String institute = rs.getString(5);
				String grade = rs.getString(6);
				if(identity==Identity.Student)
				studentsList.add(new UserPO(idNum, name, passwd, Identity.Student, institute, grade));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentsList;
	}

	public ArrayList<UserPO> getAllTeacher() {
		ArrayList<UserPO> teacherList = new ArrayList<UserPO>();
		String sql = "select * from user";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String idNum = rs.getString(1);
				String name = rs.getString(2);
				String passwd = rs.getString(3);
				Identity identity = Identity.values()[rs.getInt(4)];
				String institute = rs.getString(5);
				if (identity == Identity.RKTeacher)
					teacherList.add(new UserPO(idNum, name, passwd,
							Identity.RKTeacher, institute));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherList;
	}

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
	// businesslogic.login的LoginImpl类的Login用
	public String checkNamePasswd(String idNum, String passwd, Identity identity) {
		String id = null;
		if (identity.equals(Identity.JWTeacher)) {
			id = "0";
		} else if (identity.equals(Identity.YXTeacher)) {
			id = "1";
		} else if (identity.equals(Identity.RKTeacher)) {
			id = "2";
		} else if (identity.equals(Identity.Student)) {
			id = "3";
		} else if (identity.equals(Identity.Administrator)) {
			id = "4";
		}
		String sql = "select * from user where idNum='" + idNum
				+ "' and identity='" + id + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				if (id.equals("1")) {
					if (passwd.equals(resultset.getString(3))) {
						return resultset.getString(2) + ";"
								+ resultset.getString(5);
					}
				} else {
					if (passwd.equals(resultset.getString(3))) {
						return resultset.getString(2);
					}
				}

			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "-2";
	}

	// 返回特定身份的用户
	public ArrayList<UserPO> getUserByIdentity(String identity) {
		ArrayList<UserPO> userList = new ArrayList<UserPO>();
		if (identity.equals("-1")) {
			userList = getUserList(true);
		} else {
			ArrayList<UserPO> studentsList = new ArrayList<UserPO>();
			String sql = "select * from user where identity='" + identity + "'";
			try {
				Statement statement = connection.createStatement();
				ResultSet resultset = statement.executeQuery(sql);
				while (resultset.next()) {
					studentsList.add(new UserPO(resultset.getString(1),
							resultset.getString(2), resultset.getString(3),
							Identity.values()[resultset.getInt(4)], resultset
									.getString(5), resultset.getString(6)));
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return studentsList;
		}
		return userList;
	}
	
	public ArrayList<UserPO> getTeacherByInstitute(String institute) {
		ArrayList<UserPO> teachersList = new ArrayList<UserPO>();
		String sql = "select * from user where Institute='" + institute + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				if (Identity.values()[resultset.getInt(4)] == Identity.RKTeacher)
					teachersList.add(new UserPO(resultset.getString(1),
							resultset.getString(2), resultset.getString(3),
							Identity.RKTeacher, resultset.getString(5), resultset
									.getString(6)));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teachersList;
	}
	
}
