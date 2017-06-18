package businesslogic.framebl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import businesslogicservice.frameblservice.FrameBLService;

import po.frame.FramePO;
import vo.frame.FrameVO;

import data.framedata.FrameDAO;
import dataservice.framedataservice.FrameDataService;

/**
 * The <code><b>FQServiceImpl</b></code> in the server-side contains method to
 * validate the <code>FramePO</code>.
 * 
 * @author CaoYuting
 * 
 */
public class FrameBLServiceImpl extends UnicastRemoteObject implements FrameBLService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FrameDataService fservice;

	public FrameBLServiceImpl() throws RemoteException {
		super();
		fservice = FrameDAO.getInstance();
	}

	@Override
	public ArrayList<FrameVO> getFrameList() throws RemoteException {
		ArrayList<FramePO> framePOList = fservice.getFrameList(true);
		ArrayList<FrameVO> frameVOList = new ArrayList<FrameVO>();
		for(FramePO po : framePOList){
			frameVOList.add(new FrameVO(po.getId(),po.getCourseModule(), po.getNature(), po.getCategory(),
					po.getSemester(),po.getCreditLower(),po.getCreditUpper()));
		}
		return frameVOList;
	}
	
	@Override
	public String[] getModuleNameList() throws RemoteException{
		ArrayList<String> moduleNameList = new ArrayList<String>();
		ArrayList<FrameVO> frameVOList = getFrameList();
		for(int i=0;i<frameVOList.size();i++){
			moduleNameList.add(frameVOList.get(i).getCourseModule());
		}
		
		String [] arr = new String[moduleNameList.size()];
		int i = 0;
		for(String str : moduleNameList){
			arr[i++] = str;
		}
	
		return arr;
	}
	
	@Override
	public FrameVO getFrameById(int frameId) throws RemoteException {
		FramePO framepo = fservice.getFrameById(frameId);
		FrameVO framevo = new FrameVO(framepo.getId(),framepo.getCourseModule(), framepo.getNature(),
				framepo.getCategory(),framepo.getSemester(),framepo.getCreditLower(),framepo.getCreditUpper());
		return framevo;
	}
	
	@Override
	public int addFrameVO(FrameVO vo) throws RemoteException {
		FramePO po = new FramePO(vo.getCourseModule(),vo.getNature(),vo.getCategory(),
				vo.getSemester(),vo.getCreditLower(),vo.getCreditUpper());
		if (fservice.moduleExist(po.getCourseModule(), po.getCategory())) {
			return 0;
		}
		int isCreditOK = fservice.checkCredit(po.getCreditUpper(),
				po.getCreditLower());
		if (isCreditOK != 1) {
			return isCreditOK;
		}
		fservice.addFrame(po);
		return 1;
	}
	
	@Override
	public int deleteFrameVO(int frameId) throws RemoteException {
		if (fservice.getFrameById(frameId) == null) {
			return -1;
		}
		fservice.deleteFrame(frameId);
		return 1;
	}

	@Override
	public int modifyFrameVO(int oldFrameId, FrameVO newFrameVO)
			throws RemoteException {
		if (fservice.getFrameById(oldFrameId) == null) {
			return -1;
		}
		FramePO po = new FramePO(newFrameVO.getCourseModule(),newFrameVO.getNature(),newFrameVO.getCategory(),
				newFrameVO.getSemester(),newFrameVO.getCreditLower(),
				newFrameVO.getCreditUpper());
		if(newFrameVO.getCreditLower()>newFrameVO.getCreditUpper())
			return -2;
		fservice.modifyFrame(oldFrameId, po);
		return 1;
	}
	
}


