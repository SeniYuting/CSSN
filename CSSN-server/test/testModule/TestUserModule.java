package testModule;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.*;

import vo.user.Identity;
import vo.user.UserVO;

import businesslogic.userbl.UserBLServiceImpl;
import junit.framework.TestCase;

//方法分别进行测试
public class TestUserModule extends TestCase {
	private UserBLServiceImpl userQImpl;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		userQImpl = new UserBLServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testAddUser() throws RemoteException {
		userQImpl.addUser(new UserVO("65432123456", "Seni", "cyt",
				Identity.Student, "软件学院", "2012"));
		UserVO vo = userQImpl.getUserByIdNum("65432123456");
		assertEquals(vo.getIdNum(), "65432123456");
		assertEquals(vo.getUserName(), "Seni");
		assertEquals(vo.getPassword(), "cyt");
		assertEquals(vo.getIdentity(), Identity.Student);
		assertEquals(vo.getInstitute(), "软件学院");
		assertEquals(vo.getGrade(), "2012");
	}
	
	@Test
	public void testModifyUserInfo() throws RemoteException {
		userQImpl.modifyUserInfo(new UserVO("65432123456", "Seni", "cyt",
				Identity.Student, "文学院", "2012"));
		UserVO vo = userQImpl.getUserByIdNum("65432123456");
		assertEquals(vo.getIdNum(), "65432123456");
		assertEquals(vo.getUserName(), "Seni");
		assertEquals(vo.getPassword(), "cyt");
		assertEquals(vo.getIdentity(), Identity.Student);
		assertEquals(vo.getInstitute(), "文学院");
		assertEquals(vo.getGrade(), "2012");
	}

	@Test
	public void testUpdatePassword() throws RemoteException {
		userQImpl.updatePassword("65432123456", "Seni");
		UserVO vo = userQImpl.getUserByIdNum("65432123456");
		assertEquals(vo.getIdNum(), "65432123456");
		assertEquals(vo.getUserName(), "Seni");
		assertEquals(vo.getPassword(), "Seni");
		assertEquals(vo.getIdentity(), Identity.Student);
		assertEquals(vo.getInstitute(), "文学院");
		assertEquals(vo.getGrade(), "2012");
	}

	@Test
	public void testUserList() throws RemoteException {
		ArrayList<UserVO> userVOList = userQImpl.getUserList();
		int getResult = 0;
		for (UserVO vo : userVOList) {
			if (vo.getIdNum().equals("65432123456")
					  &&vo.getUserName().equals("Seni")
					  &&vo.getPassword().equals("Seni")
					  &&vo.getIdentity()==Identity.Student
					  &&vo.getInstitute().equals("文学院")
					  &&vo.getGrade().equals("2012")
					)
				getResult = 1;
		}
		assertEquals(getResult, 1);
	}

	@Test
	public void testGetYXStuID() throws RemoteException {
		ArrayList<UserVO> userVOList = userQImpl.getYXStuID("文学院");
		int getResult = 0;
		for (UserVO vo : userVOList) {
			if (vo.getIdNum().equals("65432123456")
					&&vo.getUserName().equals("Seni")
					&&vo.getPassword().equals("Seni")
					&&vo.getIdentity()==Identity.Student
					&&vo.getInstitute().equals("文学院")
					&&vo.getGrade().equals("2012"))
				getResult = 1;
		}
		assertEquals(getResult, 1);
	}

	@Test
	public void testDeleteUser() throws RemoteException {
		userQImpl.deleteUser("65432123456");
		ArrayList<UserVO> userVOList = userQImpl.getUserList();
		int getResult = 0;
		for (UserVO vo : userVOList) {
			if (vo.getIdNum().equals("65432123456")
					&&vo.getUserName().equals("Seni")
					&&vo.getPassword().equals("Seni")
					&&vo.getIdentity()==Identity.Student
					&&vo.getInstitute().equals("文学院")
					&&vo.getGrade().equals("2012"))
				getResult = 1;
		}
		assertEquals(getResult, 0);
	}
}
