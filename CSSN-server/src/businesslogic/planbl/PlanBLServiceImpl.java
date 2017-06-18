package businesslogic.planbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import data.plandata.PlanDAO;
import dataservice.plandataservice.PlanDataService;

import po.plan.PlanPO;

import vo.plan.Course;
import vo.plan.PlanVO;

import businesslogicservice.planblservice.PlanBLService;

public class PlanBLServiceImpl extends UnicastRemoteObject implements PlanBLService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanDataService pldservice;

	public PlanBLServiceImpl() throws RemoteException {
		super();
		pldservice = new PlanDAO();
	}

	public PlanVO checkPlan(String institute, int grade, int semester)
			throws RemoteException {
		PlanPO planpo = pldservice.checkPlan(institute, grade, semester);
		PlanVO planvo = new PlanVO(planpo.getInstitute(), planpo.getGrade(),
				planpo.getSemester(), planpo.getOpenList(),
				planpo.getMajorList());
		return planvo;
	}

	public void inputPlan(PlanVO planvo) throws RemoteException {
		PlanPO planpo = new PlanPO(planvo.getInstitute(), planvo.getGrade(),
				planvo.getSemester(), planvo.getOpenList(),
				planvo.getMajorList());
		pldservice.inputPlan(planpo);
	}

	public ArrayList<Course> getOpenList(String institute, int grade,
			int semester) {
		return pldservice.getOpenList(institute, grade, semester); // 返回为空，则不存在匹配项
	}

	public ArrayList<Course> getMajorList(String institute, int grade,
			int semester) {
		return pldservice.getMajorList(institute, grade, semester); // 返回为空，则不存在匹配项
	}
}
