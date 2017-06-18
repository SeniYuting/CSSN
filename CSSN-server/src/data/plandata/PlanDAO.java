package data.plandata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataservice.plandataservice.PlanDataService;

import po.plan.PlanPO;
import vo.plan.Course;

public class PlanDAO implements PlanDataService {

	private Connection connection;

	public PlanDAO() {
		try {
			/* load the driver to the JVM */
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};"
				+ " DBQ=./data/plan.accdb";
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查看院系教学计划
	 * 
	 * @param institute
	 *            院系名称
	 * @param grade
	 *            年级
	 * @param semester
	 *            学期
	 * @return PlanPO对象
	 */
	public PlanPO checkPlan(String institute, int grade, int semester) {

		Statement statement;
		ResultSet result;
		PlanPO plan;
		String openCourse = null;
		String majCourse = null;
		ArrayList<Course> openList = new ArrayList<Course>();
		ArrayList<Course> majorList = new ArrayList<Course>();
		String sql = "select * from plan where Institute='" + institute
				+ "' and Grade='" + grade + "' and Semester='" + semester + "'";
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) {// 这个while循环必不可少
				openCourse = result.getString("open");
				majCourse = result.getString("major");// 数据库中的格式是
														// 课程名1_学分;课程名2_学分;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] openStr = openCourse.split(";");// 这是字符串表示的通识课程
		int openNum = openStr.length;// 通识课数目
		String[] majStr = majCourse.split(";");
		int majNum = majStr.length;

		for (int i = 0; i < openNum; i++) {
			String courName = openStr[i].split("_")[0];
			int credit = Integer.parseInt(openStr[i].split("_")[1]);
			openList.add(new Course(courName, credit, "open"));
		}
		for (int i = 0; i < majNum; i++) {
			String courName = majStr[i].split("_")[0];
			int credit = Integer.parseInt(majStr[i].split("_")[1]);
			majorList.add(new Course(courName, credit, "major"));
		}

		plan = new PlanPO(institute, grade, semester, openList, majorList);
		return plan;
	}

	/**
	 * 输入本院教学计划
	 * 
	 * @param plan
	 */
	public void inputPlan(PlanPO plan) {

		String institute = plan.getInstitute();
		int grade = plan.getGrade();
		int semester = plan.getSemester();
		ArrayList<Course> openList = plan.getOpenList();
		ArrayList<Course> majorList = plan.getMajorList();
		String openStr = openList.get(0).getName() + "_"
				+ openList.get(0).getCredit();// 初始化,避免";"重复的问题
		String majorStr = majorList.get(0).getName() + "_"
				+ majorList.get(0).getCredit();// 准备写入数据库
		for (int i = 1; i < openList.size(); i++) {
			openStr = openStr + ";" + openList.get(i).getName() + "_"
					+ openList.get(i).getCredit();
		}
		for (int i = 0; i < majorList.size(); i++) {
			majorStr = majorStr + majorList.get(0).getName() + "_"
					+ majorList.get(0).getCredit();
		}
		try {
			Statement statement = connection.createStatement();
			String sql3 = "insert into plan values ('" + institute + "','"
					+ grade + "','" + semester + "','" + openStr + "','"
					+ majorStr + "')";
			statement.executeUpdate(sql3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Course> getOpenList(String institute, int grade,
			int semester) {
		ArrayList<Course> openList = new ArrayList<Course>();
		String sql = "select * from plan";
		String open = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String institute1 = rs.getString(1);
				int grade1 = rs.getInt(2);
				int semester1 = rs.getInt(3);
				String open1 = rs.getString(4);
				if (institute1.equals(institute) && grade1 == grade
						&& semester1 == semester)
					open = open1;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (open != null) {
			String split[] = open.split(";");
			for (int i = 0; i < split.length; i++) {
				String split2[] = split[i].split("_");
				String name = split2[0];
				int credit = Integer.parseInt(split2[1]);
				Course c = new Course(name, credit);
				openList.add(c);
			}
		}
		return openList;
	}

	public ArrayList<Course> getMajorList(String institute, int grade,
			int semester) {
		ArrayList<Course> majorList = new ArrayList<Course>();
		String sql = "select * from plan";
		String major = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String institute1 = rs.getString(1);
				int grade1 = rs.getInt(2);
				int semester1 = rs.getInt(3);
				String major1 = rs.getString(5);
				if (institute1.equals(institute) && grade1 == grade
						&& semester1 == semester)
					major = major1;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (major != null) {
			String split[] = major.split(";");
			for (int i = 0; i < split.length; i++) {
				String split2[] = split[i].split("_");
				String name = split2[0];
				int credit = Integer.parseInt(split2[1]);
				Course c = new Course(name, credit);
				majorList.add(c);
			}
		}
		return majorList;
	}

}
