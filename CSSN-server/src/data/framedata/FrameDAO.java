package data.framedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import businesslogic.framebl.FrameBLServiceImpl;

import dataservice.framedataservice.FrameDataService;

import po.frame.FramePO;

/**
 * <b><code>Frame Database Access Object</code></b> is the class to manage the
 * database file frame.accdb
 * 
 * @author CaoYuting
 */
public class FrameDAO implements FrameDataService {

	private static FrameDAO frameDAO;

	private Connection connection;

	private FrameDAO() {
		try {
			/* load the driver to the JVM */
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};"
				+ " DBQ=./data/frame.accdb";
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static FrameDAO getInstance() {
		if (frameDAO == null) {
			frameDAO = new FrameDAO();
		}
		return frameDAO;
	}

	/**
	 * add a new <code><b>FramePO</b></code> to the database.
	 * 
	 * @param po
	 *            the new framePO
	 */
	public void addFrame(FramePO po) {
		int id = generateNewFrameId();
		String sql = "INSERT INTO Frame VALUES ('" + id + "','"
				+ po.getCourseModule() + "','" + po.getNature() + "','"
				+ po.getCategory() + "','" + po.getSemester() + "','"
				+ po.getCreditLower() + "','" + po.getCreditUpper() + "')";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		po.setId(id);
	}
	
	/**
	 * delete a <code><b>FramePO</b></code> record from the database according
	 * frameId.
	 * 
	 * @param frameId
	 */
	public void deleteFrame(int frameId) {
		String sql = "DELETE FROM Frame WHERE ID = " + frameId;
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * modify the database <code><b>FramePO</b></code>, use the newFramePO to
	 * substitute the oldFramePO, notice : this method should not change the
	 * frameId.
	 * 
	 * @param oldFrameId
	 * @param newFramePO
	 */
	public void modifyFrame(int oldFrameId, FramePO newFramePO) {
		String sql = "UPDATE Frame SET courseModule = '"
				+ newFramePO.getCourseModule() + "', nature = '"
				+ newFramePO.getNature() + "', category = '"
				+ newFramePO.getCategory() + "', semester = '"
				+ newFramePO.getSemester() + "', creditLower = '"
				+ newFramePO.getCreditLower() + "', creditUpper = '"
				+ newFramePO.getCreditUpper() + "' WHERE ID = " + oldFrameId;

		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get the frame list
	 * 
	 * @param updatable
	 *            if true, this method will invoke updateFrameList first to
	 *            ensure the data is latest.
	 * @return frameList
	 */
	public ArrayList<FramePO> getFrameList(boolean updatable) {
		ArrayList<FramePO> frameList = new ArrayList<FramePO>();
		String sql = "SELECT * FROM Frame";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String courseModule = rs.getString(2);
				String nature = rs.getString(3);
				String category = rs.getString(4);
				String semester = rs.getString(5);
				int creditLower = rs.getInt(6);
				int creditUpper = rs.getInt(7);
				FramePO po = new FramePO(id, courseModule, nature, category,
						semester, creditLower, creditUpper);
				frameList.add(po);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return frameList;
	}

	/**
	 * check whether the category is already exist in the given courseModule.
	 * 
	 * @param courseModule
	 * @param category
	 * @see FrameBLServiceImpl --addFramePO
	 * @return courseModuleExist
	 */
	public boolean moduleExist(String courseModule, String category) {
		boolean courseModuleExist = false;
		String sql = "SELECT * FROM frame WHERE courseModule = '" + courseModule
				+ "' and category = '" + category + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				courseModuleExist=true;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return courseModuleExist;
	}
	
	/**
	 * get the creditUpper by the given courseModule.
	 * 
	 * @param courseModule
	 * @see CouresBLServiceImpl
	 * @return creditUpper
	 */
	
	public int getModuleCreditUpper(String courseModule){
		int creditUpper = -1;
		String sql = "SELECT * FROM Frame";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String courseModule1 = rs.getString(2);
				int creditUpper1 = rs.getInt(7);
				if(courseModule.equals(courseModule1))
					creditUpper = creditUpper1;
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return creditUpper;
	}
	
	/**
	 * get a <code><b>FramePO</b></code> by the given frameId
	 * 
	 * @param frameId
	 * @see FMServiceImpl --deleteFramePO
	 * @return if the frameId is not available, this method will return </b>
	 *         <code>null</code></b>
	 */
	public FramePO getFrameById(int frameId) {
		String sql = "SELECT * FROM Frame";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String courseModule = rs.getString(2);
				String nature = rs.getString(3);
				String category = rs.getString(4);
				String semester = rs.getString(5);
				int creditLower = rs.getInt(6);
				int creditUpper = rs.getInt(7);
				if(id==frameId)
					 return new FramePO(id, courseModule, nature, category, semester, creditLower, creditUpper);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <code>check the credit,
	 * if the creditUpper < creditLower, return value -1,
	 * else if the creditLower < 0, return -2,
	 * else if the creditUpper < 0, return -3
	 * else return 1 </code>
	 * 
	 * @param creditLower
	 * @param creditUpper
	 * @see FMServiceImpl --addFramePO
	 * @return result
	 */
	public int checkCredit(int creditUpper, int creditLower) {
		int result = 1;
		if (creditUpper < creditLower)
			result = -1;
		if (creditLower < 0)
			result = -2;
		if (creditUpper < 0)
			result = -3;
		return result;
	}
	
	/**
	 * help to generate a new available frame id.
	 * 
	 * @return the next available id.
	 */
	private int generateNewFrameId() {
		ArrayList<FramePO> frameList  = new ArrayList<FramePO>();
		frameList = getFrameList(true);
		for (int i = 1; i <= frameList.size(); i++) {
			boolean occupied = false;
			for (FramePO po : frameList) {
				if (po.getId() == i) {
					occupied = true;
					break;
				}
			}
			if (!occupied) {
				return i;
			}
		}
		return frameList.size() + 1;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}