package data.courseselectiondata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataservice.courseselectiondataservice.CourseSelectionDataService;
import po.courseselection.CourseSelectionPO;

public class CourseSelectionDAO implements CourseSelectionDataService {
	private Connection connection;

	public CourseSelectionDAO() {
		try {
			/* load the driver to the JVM */
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};"
				+ " DBQ=./data/CourseSelection.accdb";
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addCourseSelection(CourseSelectionPO po) {
		String sql = "INSERT INTO CourseSelection VALUES ('" + po.getCourseNO()
				+ "','" + po.getStuNO() + "','" + po.getTeacherNO() + "','"
				+ po.getState() + "','" + po.getSemester() + "')";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCourseSelection(String courseNO, String stuNO) {
		String sql = "DELETE FROM CourseSelection WHERE CourseNO ='" + courseNO
				+ "' and stuNO='" + stuNO + "'";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CourseSelectionPO> getCourseSelectionByStuNO(String stuNO,
			String state) {
		String sql = "SELECT * FROM CourseSelection WHERE stuNO = '" + stuNO
				+ "' and state = '" + state + "'";

		ArrayList<CourseSelectionPO> returnList = new ArrayList<CourseSelectionPO>();

		try {
			Statement statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String courseNO = rs.getString(1);
				String teacherNO = rs.getString(3);
				String semester = rs.getString(5);
				CourseSelectionPO po = new CourseSelectionPO(courseNO, stuNO,
						teacherNO, state,semester);
				returnList.add(po);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnList;
	}

	public ArrayList<CourseSelectionPO> getCourseSelectionByCourseNO(
			String courseNO, String teacherNO) {
		// 没有问题
		String sql = "SELECT * FROM CourseSelection WHERE courseNO = '"
				+ courseNO + "' and teacherNO = '" + teacherNO
				+ "' and state = true";
		ArrayList<CourseSelectionPO> returnList = new ArrayList<CourseSelectionPO>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String stuNO = rs.getString(2);
				String state = rs.getString(4);
				String semester = rs.getString(5);
				CourseSelectionPO po = new CourseSelectionPO(courseNO, stuNO,
						teacherNO, state,semester);
				returnList.add(po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}

	public ArrayList<CourseSelectionPO> getCourseSelectionByCourseNO(
			String courseNO) {
		String sql = "SELECT * FROM CourseSelection WHERE courseNO ='"
				+ courseNO + "' and state = 'true'";

		ArrayList<CourseSelectionPO> returnList = new ArrayList<CourseSelectionPO>();

		try {
			Statement statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = statement.executeQuery(sql);
			rs.beforeFirst();
			while (rs.next()) {
				String stuNO = rs.getString(2);
				String teacherNO = rs.getString(3);
				String state = rs.getString(4);
				String semester = rs.getString(5);
				CourseSelectionPO po = new CourseSelectionPO(courseNO, stuNO,
						teacherNO, state,semester);
				returnList.add(po);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnList;
	}

	public String getState(String stuNO, String courseNO) {
		String sql = "SELECT * FROM CourseSelection WHERE stuNO = '" + stuNO
				+ "' and courseNO = '" + courseNO + "'";
		String state = "";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				state = rs.getString(4);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return state;
	}

	public void setState(String stuNO, String courseNO, String state) {
		String sql = "update CourseSelection set state = '" + state
				+ "' where stuNO = '" + stuNO + "' and courseNO = '" + courseNO
				+ "'";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
