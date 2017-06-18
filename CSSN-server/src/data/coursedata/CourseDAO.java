package data.coursedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dataservice.coursedataservice.CourseDataService;
import po.course.*;

/**
 * <b><code>Course Database Access Object</code></b> is the class to manage the
 * database file course.accdb
 * 
 * @author cy
 */
public class CourseDAO implements CourseDataService {

	private static CourseDAO courseDAO;
	private Connection connection;

	private CourseDAO() {
		try {
			/* load the driver to the JVM */
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};"
				+ " DBQ=./data/course.accdb";
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static CourseDAO getInstance() {
		if (courseDAO == null) {
			courseDAO = new CourseDAO();
		}
		return courseDAO;
	}

	public void addCourse(CoursePO po) {
		String sql = null;
		if (po.getIsCompulsory()) {
			sql = "INSERT INTO course VALUES ('" + po.getCoID() + "','"
					+ po.getCoName() + "', true,'" + po.getCredit() + "','"
					+ po.getHour() + "','" + po.getModule() + "','"
					+ po.getTeaID() + "','" + po.getTeacher() + "','"
					+ po.getSemester() + "','" + po.getGrade() + "','"
					+ po.getInstitution() + "','" + po.getNumOfStu() + "', '"
					+ po.getTime() + "','" + po.getLocation() + "','"
					+ po.getDescription() + "','" + po.getTextbook() + "','"
					+ po.getReference() + "')";
		} else {
			sql = "INSERT INTO course VALUES ('" + po.getCoID() + "','"
					+ po.getCoName() + "',false,'" + po.getCredit() + "','"
					+ po.getHour() + "','" + po.getModule() + "','"
					+ po.getTeaID() + "','" + po.getTeacher() + "','"
					+ po.getSemester() + "','" + po.getGrade() + "','"
					+ po.getInstitution() + "','" + po.getNumOfStu() + "', '"
					+ po.getTime() + "','" + po.getLocation() + "','"
					+ po.getDescription() + "','" + po.getTextbook() + "','"
					+ po.getReference() + "')";
		}
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCourse(String coID) {
		String sql = "DELETE FROM COURSE WHERE ID ='" + coID + "'";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CoursePO> getCourseList(boolean updatable) {
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String sql = "SELECT * FROM COURSE";
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
				String description = rs.getString(15);
				String textbook = rs.getString(16);
				String reference = rs.getString(17);

				CoursePO po = new CoursePO(coID, coName, isCompulsory, credit,
						hour, module, teaID, teacher, semester, grade,
						institution, numOfStu, time, location, description,
						textbook, reference);
				courseList.add(po);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

	public boolean isExist(String coID, String coName) {
		boolean isExist = false;
		String sql = "SELECT * FROM COURSE where coID='" + coID
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

	public CoursePO getCourseById(String coId) {
		CoursePO po = null;
		String sql = "SELECT * FROM COURSE where coID='" + coId + "'";
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
				String description = rs.getString(15);
				String textbook = rs.getString(16);
				String reference = rs.getString(17);
				po = new CoursePO(coID, coName, isCompulsory, credit, hour,
						module, teaID, teacher, semester, grade, institution,
						numOfStu, time, location, description, textbook,
						reference);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return po;
	}

	public ArrayList<CoursePO> getCourseByInstitution(String institution) {
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String sql = "SELECT * FROM COURSE where institution='" + institution
				+ "'";
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
				String numOfStu = rs.getString(12);
				String time = rs.getString(13);
				String location = rs.getString(14);
				String description = rs.getString(15);
				String textbook = rs.getString(16);
				String reference = rs.getString(17);

				CoursePO po = new CoursePO(coID, coName, isCompulsory, credit,
						hour, module, teaID, teacher, semester, grade,
						institution, numOfStu, time, location, description,
						textbook, reference);
				courseList.add(po);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

	public CoursePO getCourseByIDAndIns(String coID, String institution) {

		ArrayList<CoursePO> courseList = getCourseList(true);
		for (CoursePO po : courseList) {
			if (po.getCoID().equals(coID)
					&& (po.getInstitution().equals(institution) || po
							.getInstitution().equals("所有院系"))) {
				return po;
			}
		}
		return null;
	}

	public ArrayList<CoursePO> getCourseByTeaID(String teaID) {
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String sql = "SELECT * FROM COURSE where teaID='" + teaID + "'";
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
				String teacher = rs.getString(8);
				String semester = rs.getString(9);
				String grade = rs.getString(10);
				String institution = rs.getString(11);
				String numOfStu = rs.getString(12);
				String time = rs.getString(13);
				String location = rs.getString(14);
				String description = rs.getString(15);
				String textbook = rs.getString(16);
				String reference = rs.getString(17);
				courseList.add(new CoursePO(coID, coName, isCompulsory, credit,
						hour, module, teaID, teacher, semester, grade,
						institution, numOfStu, time, location, description,
						textbook, reference));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}

	public int getTotalByModule(String module) {
		int total = 0;
		ArrayList<CoursePO> courseList = getCourseByModule(module);
		for (CoursePO po : courseList) {
			if (po.getModule().equals(module)) {
				total = total + Integer.parseInt(po.getCredit());
			}
		}
		return total;
	}

	public ArrayList<CoursePO> getTeacherCourse(String teacherId) {
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String sql = "select * from course where teaID='" + teacherId + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				courseList.add(new CoursePO(resultset.getString(1), resultset
						.getString(2), resultset.getInt(4), resultset
						.getString(10), resultset.getString(9)));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

	public ArrayList<CoursePO> getCourseByModule(String module) {
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String sql = "SELECT * FROM COURSE where module='" + module + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String coID = rs.getString(1);
				String coName = rs.getString(2);
				boolean isCompulsory = rs.getBoolean(3);
				String credit = rs.getString(4);
				String hour = rs.getString(5);
				String teaID = rs.getString(7);
				String teacher = rs.getString(8);
				String semester = rs.getString(9);
				String grade = rs.getString(10);
				String institution = rs.getString(11);
				String numOfStu = rs.getString(12);
				String time = rs.getString(13);
				String location = rs.getString(14);
				String description = rs.getString(15);
				String textbook = rs.getString(16);
				String reference = rs.getString(17);
				courseList.add(new CoursePO(coID, coName, isCompulsory, credit,
						hour, module, teaID, teacher, semester, grade,
						institution, numOfStu, time, location, description,
						textbook, reference));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;

	}

	public ArrayList<CoursePO> getAllCourseList() {
		ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
		String sql = "SELECT * FROM COURSE";
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
				String description = rs.getString(15);
				String textbook = rs.getString(16);
				String reference = rs.getString(17);

				CoursePO po = new CoursePO(coID, coName, isCompulsory, credit,
						hour, module, teaID, teacher, semester, grade,
						institution, numOfStu, time, location, description,
						textbook, reference);
				courseList.add(po);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}
}
