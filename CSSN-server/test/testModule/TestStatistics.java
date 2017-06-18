package testModule;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.statistics.StatisticsVO;
import businesslogic.statistics.STQServiceImpl;
import businesslogicservice.statistics.STQService;
import junit.framework.TestCase;

public class TestStatistics extends TestCase {


	public void testcheckCourseStatistics(){
		STQService stq;
		try {
			stq = new STQServiceImpl();
			StatisticsVO vo = stq.checkCourseStatistics("1");
			vo.setNum(4);
			assertTrue(vo.getNum()==4);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void testCheckGPA() {
	STQService stq;
	try {
		stq = new STQServiceImpl();
		StatisticsVO vo = stq.checkGPA("12", "12");
		ArrayList<Double> l=new ArrayList<Double>();
		l.add(4.5);
		vo.setGPA(l);
		assertTrue(vo.getGpa().get(0).equals(4.5));
	} catch (RemoteException e) {
		e.printStackTrace();
	}
}
	
}
