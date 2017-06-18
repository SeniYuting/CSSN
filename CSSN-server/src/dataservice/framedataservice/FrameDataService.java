package dataservice.framedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.frame.FramePO;

/**
 * 接口名：FrameDataService
 * 接口描述：Frame模块提供的对外接口，包含与frame.accdb有关的方法
 * @author Just Coding队
 */
public interface FrameDataService {
	
	/**
	 * 方法名：addFrame
	 * 方法描述：输入整体框架策略
	 * @param po
	 * @return       
	 * @throws RemoteException
	 */
	public void addFrame(FramePO po);
	
	/**
	 * 方法名：deleteFrame
	 * 方法描述：删除整体框架策略
	 * @param frameId
	 * @return       
	 * @throws RemoteException
	 */
	public void deleteFrame(int frameId);
	
	/**
	 * 方法名：modifyFrame
	 * 方法描述：修改整体框架策略
	 * @param oldFrameId
	 * @param newFramePO
	 * @return       
	 * @throws RemoteException
	 */
	public void modifyFrame(int oldFrameId, FramePO newFramePO);
	
	/**
	 * 方法名：getFrameList
	 * 方法描述：查看整体框架策略
	 * @param updatable
	 * @return       
	 * @throws RemoteException
	 */
	public ArrayList<FramePO> getFrameList(boolean updatable);
	
	/**
	 * 方法名：moduleExist
	 * 方法描述：根据模块名和课程类别判断该模块是否存在
	 * @param courseModule
	 * @param category
	 * @see FrameBLServiceImpl
	 * @return       
	 * @throws RemoteException
	 */
	public boolean moduleExist(String courseModule, String category);
	
	/**
	 * 方法名：getModuleCreditUpper
	 * 方法描述：根据模块名得到模块上限
	 * @param courseModule
	 * @see CouresBLServiceImpl
	 * @return creditUpper   
	 * @throws RemoteException
	 */
	public int getModuleCreditUpper(String courseModule);
	
	/**
	 * 方法名：getFrameById
	 * 方法描述：根据frameId返回整体框架策略
	 * @param frameId
	 * @see FMServiceImpl
	 * @return 如果返回该整体框架策略，该frameId对应的整体框架策略存在
	 *         如果返回null，该frameId对应的整体框架策略不存在    
	 * @throws RemoteException
	 */
	public FramePO getFrameById(int frameId);
	
	/**
	 * 方法名：checkCredit
	 * 方法描述：根据creditUpper、creditLower返回学分是否符合规格
	 * @param creditUpper
	 * @param creditLower
	 * @see FMServiceImpl
	 * @return 如果返回1，学分符合要求 
	 *         如果返回-1，学分上限<学分下限
	 *         如果返回-2，学分上限<0
	 *         如果返回-3，学分下限<0        
	 * @throws RemoteException
	 */
	public int checkCredit(int creditUpper, int creditLower);

}