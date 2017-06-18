package testDAO;

import org.junit.*;

import po.frame.FramePO;
import data.framedata.FrameDAO;
import junit.framework.TestCase;

//�����ֱ���в���
public class TestFrameDAO extends TestCase {
	
	private FrameDAO frameDAO;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		frameDAO = FrameDAO.getInstance();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		frameDAO.close();
	}

	@Test
	public void testAddFrame() {
		frameDAO.deleteFrame(1);
		FramePO po = new FramePO("רҵ����", "����", "רҵ���Ŀ�", "1,2,3,4", 12, 16);
		frameDAO.addFrame(po);
		assertEquals(frameDAO.getFrameById(1).getId(), 1);
		assertEquals(frameDAO.getFrameById(1).getCourseModule(), "רҵ����");
		assertEquals(frameDAO.getFrameById(1).getNature(), "����");
		assertEquals(frameDAO.getFrameById(1).getCategory(), "רҵ���Ŀ�");
		assertEquals(frameDAO.getFrameById(1).getSemester(), "1,2,3,4");
		assertEquals(frameDAO.getFrameById(1).getCreditLower(), 12);
		assertEquals(frameDAO.getFrameById(1).getCreditUpper(), 16);
	}
	
	@Test
	public void testModifyFrame() {
		frameDAO.deleteFrame(1);
		FramePO po = new FramePO("רҵ����", "����", "רҵ���Ŀ�", "1,2,3,4", 12, 16);
		frameDAO.addFrame(po);
		FramePO newPO = new FramePO("רҵ����", "����", "רҵ���Ŀ�", "1,2,3,4", 12, 18);
		frameDAO.modifyFrame(1, newPO);
		assertEquals(frameDAO.getFrameById(1).getId(), 1);
		assertEquals(frameDAO.getFrameById(1).getCourseModule(), "רҵ����");
		assertEquals(frameDAO.getFrameById(1).getNature(), "����");
		assertEquals(frameDAO.getFrameById(1).getCategory(), "רҵ���Ŀ�");
		assertEquals(frameDAO.getFrameById(1).getSemester(), "1,2,3,4");
		assertEquals(frameDAO.getFrameById(1).getCreditLower(), 12);
		assertEquals(frameDAO.getFrameById(1).getCreditUpper(), 18);
		
	}
		
	@Test
	public void testGetFrameById() {
		frameDAO.deleteFrame(1);
		FramePO po = new FramePO("רҵ����", "����", "רҵ���Ŀ�", "1,2,3,4", 12, 16);
		frameDAO.addFrame(po);
		assertEquals(frameDAO.getFrameById(1).getId(), 1);
		assertEquals(frameDAO.getFrameById(1).getCourseModule(), "רҵ����");
		assertEquals(frameDAO.getFrameById(1).getNature(), "����");
		assertEquals(frameDAO.getFrameById(1).getCategory(), "רҵ���Ŀ�");
		assertEquals(frameDAO.getFrameById(1).getSemester(), "1,2,3,4");
		assertEquals(frameDAO.getFrameById(1).getCreditLower(), 12);
		assertEquals(frameDAO.getFrameById(1).getCreditUpper(), 16);
	}
}
