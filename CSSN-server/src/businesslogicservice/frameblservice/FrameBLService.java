package businesslogicservice.frameblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.frame.FrameVO;

/**
 * 接口名：FrameBLService
 * 接口描述：Frame模块提供的对外接口，包含与frame.accdb有关的方法
 * @author Just Coding队
 */
public interface FrameBLService extends Remote {

	/**
	 * 方法名：getFrameList
	 * 方法描述：教务处老师查看整体框架策略
	 * @param 
	 * @return 整体框架策略
	 * @throws RemoteException
	 */
	public ArrayList<FrameVO> getFrameList() throws RemoteException;
	
	/**
	 * 方法名：getModuleNameList
	 * 方法描述：得到整体框架策略的所有模块名
	 * @param 
	 * @return 模块名
	 * @throws RemoteException
	 */
	public String[] getModuleNameList() throws RemoteException;
	
	/**
	 * 方法名：getFrameById
	 * 方法描述：根据frameId返回某一整体框架策略
	 * @param 
	 * @return 如果返回该整体框架策略，该frameId对应的整体框架策略存在
	 *         如果返回null，该frameId对应的整体框架策略不存在   
	 * @throws RemoteException
	 */
	public FrameVO getFrameById(int frameId) throws RemoteException;
	
	/**
	 * 方法名：addFrameVO
	 * 方法描述：输入整体框架策略
	 * @param vo
	 * @return 如果返回1，添加成功
	 *         如果返回0，该模块已存在
	 *         如果返回-1，学分上限<学分下限
	 *         如果返回-2，学分上限<0
	 *         如果返回-3，学分下限<0         
	 * @throws RemoteException
	 */
	public int addFrameVO(FrameVO vo) throws RemoteException;
	
	/**
	 * 方法名：deleteFrameVO
	 * 方法描述：删除整体框架策略
	 * @param frameId
	 * @return 如果返回1，删除成功
	 *         如果返回-1，frameId不存在        
	 * @throws RemoteException
	 */
	public int deleteFrameVO(int frameId) throws RemoteException;
	
	/**
	 * 方法名：modifyFrameVO
	 * 方法描述：修改整体框架策略
	 * @param oldFrameId
	 * @param newFrameVO
	 * @return 如果返回1，修改成功
	 *         如果返回-1，oldFrameId不存在     
	 *         如果返回-2，学分下限>学分上限   
	 * @throws RemoteException
	 */
	public int modifyFrameVO(int oldFrameId, FrameVO newFrameVO)
			throws RemoteException;
}

