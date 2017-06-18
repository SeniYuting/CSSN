package data.coursedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dataservice.coursedataservice.YXCourseDataService;
import po.course.CoursePO;


public class YXCourseDAO implements YXCourseDataService{

	private static YXCourseDAO YXcourseDAO;
	private Connection connection;
	
	private YXCourseDAO() {
		try {
			/* load the driver to the JVM */
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};"
				+ " DBQ=./data/yxcourse.accdb";
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static YXCourseDAO getInstance() {
		if (YXcourseDAO == null) {
			YXcourseDAO = new YXCourseDAO();
		}
		return YXcourseDAO;
	}

	// 增加院系课程
	public void addYXCourse(CoursePO po) {
		String sql = "INSERT INTO YXCOURSE VALUES ('" + po.getCoID() + "','"
				+ po.getCoName() + "'," + po.getIsCompulsory() + ",'"
				+ po.getCredit() + "','" + po.getHour() + "','"
				+ po.getModule() + "','" + po.getTeaID() + "','"
				+ po.getTeacher() + "','" + po.getSemester()+ "','" + po.getGrade()+ "','"
				+ po.getInstitution()+ "','" + po.getNumOfStu()+ "','" + po.getTime() + "','"
				+ po.getLocation() + "')";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 删除院系课程
	public void deleteYXCourse(String coID) {
		String sql="delete from yxcourse where coID='"+coID+"'";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 修改院系课程
	public void modifyYXCourse(String oldCoId, CoursePO newCoursePO) {
		deleteYXCourse(oldCoId);
		addYXCourse(newCoursePO);
	}


	// 返回院系课程列表（14个属性）
	public ArrayList<CoursePO> getYXCourseList(boolean updatable) {
	
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String sql = "SELECT * FROM yxcourse";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String coID = rs.getString(1);
				String coName = rs.getString(2);
				boolean isCompulsory = rs.getBoolean(3);
				String credit = rs.getString(4);
				String hour = rs.getString(5);
				String module = rs.getString(6);
				String teaID = rs.getString(7);
				String teacher = rs.getString(8);
				String semester = rs.getString(9);
				String grade = rs.getString(10);
				String institution = rs.getString(11);
				String numOfStu = rs.getString(12);
				String time = rs.getString(13);
				String location = rs.getString(14);

				CoursePO po = new CoursePO(coID, coName, isCompulsory, credit,
						hour, module, teaID, teacher, semester, grade,
						institution, numOfStu, time, location);
				courseList.add(po);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
		
	}


	// 判断课程号或课程名是否已经在院系课程列表中存在
	public boolean isExist(String coID, String coName) {
		boolean isExist = false;
		String sql = "SELECT * FROM YXCOURSE where coID='" + coID
				+ "' and coName='" + coName + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				isExist = true;
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isExist;
	}

	// 由课程号获得一门课程（14个属性）
	public CoursePO getYXCourseById(String coId) {
		CoursePO po = null;
		String sql = "SELECT * FROM yxcourse where coID='" + coId + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String coID = rs.getString(1);
				String coName = rs.getString(2);
				boolean isCompulsory = rs.getBoolean(3);
				String credit = rs.getString(4);
				String hour = rs.getString(5);
				String module = rs.getString(6);
				String teaID = rs.getString(7);
				String teacher = rs.getString(8);
				String semester = rs.getString(9);
				String grade = rs.getString(10);
				String institution = rs.getString(11);
				String numOfStu = rs.getString(12);
				String time = rs.getString(13);
				String location = rs.getString(14);
				po = new CoursePO(coID, coName, isCompulsory, credit, hour,
						module, teaID, teacher, semester, grade, institution,
						numOfStu, time, location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return po;
	}

	// 由课程模块获得此课程模块的总分
	public int getTotalByModule(String module) {
		int total = 0;
		String sql = "SELECT * FROM yxcourse where module='" + module + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String credit = rs.getString(4);
				total=total+Integer.parseInt(credit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	// 由教师工号得到教师应该填写信息的课程
	public ArrayList<CoursePO> getCourseToFinishInfo(String teacherId) {
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String sql = "select * from yxcourse where teaID='" + teacherId + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				courseList.add(new CoursePO(resultset.getString(1), resultset
						.getString(2), resultset.getInt(4),resultset.getString(10),resultset.getString(9)));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}
	
	//得到院系教务老师已经发布的且信息未填写完整的课程列表
	public ArrayList<CoursePO> getCourseByInstitution(String institution) {
		ArrayList<CoursePO>courseList= getYXCourseList(true);
		ArrayList<CoursePO> partcourseList=new ArrayList<CoursePO>();
		for (CoursePO po : courseList) {
			if (po.getInstitution().equals(institution)) {
				CoursePO cp = new CoursePO(po.getCoID(), po.getCoName());
				partcourseList.add(cp);
			}
		}
		return partcourseList;
	}
	
	// 由课程号获得一门课程（14个属性）
		public CoursePO getCourseById(String coId) {
			return getYXCourseById(coId);
		}


}
