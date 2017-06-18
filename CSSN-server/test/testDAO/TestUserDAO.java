package testDAO;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.*;

import po.user.UserPO;
import vo.user.Identity;

import data.userdata.UserDAO;

import junit.framework.TestCase;

//�����ֱ���в���
public class TestUserDAO extends TestCase{
	UserDAO userDAO;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		userDAO = UserDAO.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		userDAO.close();
	}
	
	@Test
	public void testAddUser() throws RemoteException {
		userDAO.deleteUser("65432123456");
		userDAO.addUser(new UserPO("65432123456", "Seni", "cyt",
				Identity.Student, "���ѧԺ", "2012"));
		UserPO po = userDAO.getUserByIdNum("65432123456");
		assertEquals(po.getIdNum(), "65432123456");
		assertEquals(po.getUserName(), "Seni");
		assertEquals(po.getPassword(), "cyt");
		assertEquals(po.getIdentity(), Identity.Student);
		assertEquals(po.getInstitute(), "���ѧԺ");
		assertEquals(po.getGrade(), "2012");
	}
	
	@Test
	public void testModifyUserInfo() throws RemoteException {
		userDAO.modifyUser("65432123456",new UserPO("65432123456", "Seni", "cyt",
				Identity.Student, "��ѧԺ", "2012"));
		UserPO po = userDAO.getUserByIdNum("65432123456");
		assertEquals(po.getIdNum(), "65432123456");
		assertEquals(po.getUserName(), "Seni");
		assertEquals(po.getPassword(), "cyt");
		assertEquals(po.getIdentity(), Identity.Student);
		assertEquals(po.getInstitute(), "��ѧԺ");
		assertEquals(po.getGrade(), "2012");
	}
	
	@Test
	public void testUpdatePassword() throws RemoteException {
		userDAO.updatePassword("65432123456", "Seni");
		UserPO po = userDAO.getUserByIdNum("65432123456");
		assertEquals(po.getIdNum(), "65432123456");
		assertEquals(po.getUserName(), "Seni");
		assertEquals(po.getPassword(), "Seni");
		assertEquals(po.getIdentity(), Identity.Student);
		assertEquals(po.getInstitute(), "��ѧԺ");
		assertEquals(po.getGrade(), "2012");
	}
	
	@Test
	public void testUserList() throws RemoteException {
		ArrayList<UserPO> userPOList = userDAO.getUserList(true);
		int getResult = 0;
		for (UserPO po : userPOList) {
			if (po.getIdNum().equals("65432123456")
					&&po.getUserName().equals("Seni")
					&&po.getPassword().equals("Seni")
					&&po.getIdentity()==Identity.Student
					&&po.getInstitute().equals("��ѧԺ")
					&&po.getGrade().equals("2012"))
				getResult = 1;
		}
		assertEquals(getResult, 1);
	}
	
	@Test
	public void testGetYXStuID() throws RemoteException {
		ArrayList<UserPO> userPOList = userDAO.getStudentByInstitute("��ѧԺ");
		int getResult = 0;
		for (UserPO po : userPOList) {
			if (po.getIdNum().equals("65432123456")
					&&po.getUserName().equals("Seni")
					&&po.getPassword().equals("Seni")
					&&po.getIdentity()==Identity.Student
					&&po.getInstitute().equals("��ѧԺ")
					&&po.getGrade().equals("2012"))
				getResult = 1;
		}
		assertEquals(getResult, 1);
	}
	
	@Test
	public void testDeleteUser() throws RemoteException {
		userDAO.deleteUser("65432123456");
		ArrayList<UserPO> userPOList = userDAO.getUserList(true);
		int getResult = 0;
		for (UserPO po : userPOList) {
			if (po.getIdNum().equals("65432123456")
					&&po.getUserName().equals("Seni")
					&&po.getPassword().equals("Seni")
					&&po.getIdentity()==Identity.Student
					&&po.getInstitute().equals("��ѧԺ")
					&&po.getGrade().equals("2012"))
				getResult = 1;
		}
		assertEquals(getResult, 0);
	}
}
