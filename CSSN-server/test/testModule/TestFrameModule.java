package testModule;

import java.rmi.RemoteException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.*;

import vo.frame.FrameVO;
import businesslogic.framebl.FrameBLServiceImpl;

//�����ֱ���в���
public class TestFrameModule extends TestCase {
    
	private FrameBLServiceImpl frameQImpl;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		frameQImpl = new FrameBLServiceImpl();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testAddFrameVO() throws RemoteException {
		int addResult = frameQImpl.addFrameVO(new FrameVO("ͨʶͨ��","����",
				"˼���������ۿγ�","1,2,3,4,5,6",11, 16));
		assertEquals(addResult, 0);
		
		addResult = frameQImpl.addFrameVO(new FrameVO("����","����",
				"����","1,2,3,4,5,6",11, 10));
		assertEquals(addResult, -1);
		
		addResult = frameQImpl.addFrameVO(new FrameVO("����","����",
				"����","1,2,3,4,5,6",-1, 16));
		assertEquals(addResult, -2);
		
		addResult = frameQImpl.addFrameVO(new FrameVO("����","����",
				"����","1,2,3,4,5,6",11, -1));
		assertEquals(addResult, -3);
	}
	
	@Test
	public void testModifyFrameVO() throws RemoteException {
		frameQImpl.addFrameVO(new FrameVO("ͨʶͨ��","����",
				"˼���������ۿγ�","1,2,3,4,5,6",11, 16));
		int modifyResult = frameQImpl.modifyFrameVO(2, new FrameVO("ͨʶͨ��", "����",
				"˼���������ۿγ�", "1,2,3,4,5,6", 12, 16));
		assertEquals(modifyResult, 1);
		
		modifyResult = frameQImpl.modifyFrameVO(2, new FrameVO("ͨʶͨ��", "����",
				"˼���������ۿγ�", "1,2,3,4,5,6", 12, 10));
		assertEquals(modifyResult, -2);
	}
	
	@Test
	public void testGetFrameList() throws RemoteException {
		frameQImpl.addFrameVO(new FrameVO("ͨʶͨ��","����",
				"˼���������ۿγ�","1,2,3,4,5,6",11, 16));
		ArrayList<FrameVO> frameVOList = frameQImpl.getFrameList();
		int getResult = 0;
		for(FrameVO vo : frameVOList){
			if(vo.getCourseModule().equals("ͨʶͨ��"))
				getResult = 1;
		}
		assertEquals(getResult, 1);
	}
	
	@Test
	public void testDeleteFrameVO() throws RemoteException {
		frameQImpl.addFrameVO(new FrameVO("ͨʶͨ��","����",
				"˼���������ۿγ�","1,2,3,4,5,6",11, 16));
		int deleteResult = frameQImpl.deleteFrameVO(2);
		assertEquals(deleteResult, 1);
	}
}
