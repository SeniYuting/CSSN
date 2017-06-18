package JavaRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import businesslogic.coursebl.CouresBLServiceImpl;
import businesslogic.courseselectionbl.CourseSelectionBLServiceImpl;
import businesslogic.framebl.FrameBLServiceImpl;
import businesslogic.planbl.PlanBLServiceImpl;
import businesslogic.scorebl.ScoreBLServiceImpl;
import businesslogic.statisticsbl.StatisticsBLServiceImpl;
import businesslogic.userbl.UserBLServiceImpl;
import businesslogic.userbl.loginController.LoginImpl;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.frameblservice.FrameBLService;
import businesslogicservice.planblservice.PlanBLService;
import businesslogicservice.scoreblservice.ScoreBLService;
import businesslogicservice.statisticsblservice.StatisticsBLService;
import businesslogicservice.userblservice.UserBLService;
import businesslogicservice.userblservice.login.LoginBLService;

public class RMIFactoryImpl extends UnicastRemoteObject implements RMIFactory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RMIFactoryImpl() throws RemoteException {
		super();
	}
	
	public CourseBLService getCQService() throws RemoteException{
		CourseBLService cqservice=new CouresBLServiceImpl();
		return cqservice;
	}
	
	public CourseSelectionBLService getCSQService() throws RemoteException{
		CourseSelectionBLService csqservice=new CourseSelectionBLServiceImpl();
		return csqservice;
	}
	
	public FrameBLService getFQService()throws RemoteException{
		FrameBLService fqservice=new FrameBLServiceImpl();
		return fqservice;
	}
	
	public LoginBLService getLoginService() throws RemoteException{
		LoginBLService lgservice=new LoginImpl();
		return lgservice;
	}
	
	public PlanBLService getPLQService()throws RemoteException{
		PlanBLService plqservice=new PlanBLServiceImpl();
		return plqservice;
	}
	
	public ScoreBLService getSCQService()throws RemoteException{
		ScoreBLService scqservice=new ScoreBLServiceImpl();
		return scqservice;
	}
	
	public StatisticsBLService getSTQService()throws RemoteException{
		StatisticsBLService stqservice=new StatisticsBLServiceImpl();
		return stqservice;
	}
	
	public UserBLService getUQService()throws RemoteException{
		UserBLService uqservice=new UserBLServiceImpl();
		return uqservice;
	}
}
