package data.scoredata;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import dataservice.scoredataservice.ScoreDataService;
import po.score.ScorePO;

public class ScoreDAO implements ScoreDataService {

	Connection connection;

	public ScoreDAO() {
		try {
			/* load the driver to the JVM */
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};"
				+ " DBQ=./data/score.accdb";
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 学生查看自己某一门课程的成绩 这只是学生查看课程成绩用例的一个步骤;首先要去调用查看自己的选课列表
	 * 
	 * @param stuNo学生学号
	 * @param courseNo
	 *            课程号
	 * @return ScorePo对象
	 */

	public ScorePO checkStuScore(String stuNo, String courseNo) {
		ScorePO scorepo;
		int score = -1;
		int credit = 0;
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from score where courseNo='" + courseNo
					+ "' and stuNo='" + stuNo + "'";
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				score = resultset.getInt("score");
				credit = resultset.getInt("credit");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 如果找不到记录,就是没登记,但是一样要把scorepo返回;在vo里要判断；如果score是-1，那么显示未发布
		scorepo = new ScorePO(stuNo, courseNo, score, credit);
		return scorepo;
	}

	/**
	 * 学生查看自己全部课程的成绩 这只是学生查看课程成绩用例的一个步骤;首先要去调用查看自己的选课列表
	 * 
	 * @param stuNo学生学号
	 * @param courseList
	 *            课程号列表
	 * @return ScorePo对象列表
	 */
	public ArrayList<ScorePO> checkStuScore(String stuNo,
			ArrayList<String> courseList) {
		ArrayList<ScorePO> scoreList = new ArrayList<ScorePO>();
		for (int i = 0; i < courseList.size(); i++) {
			int score = -1;
			try {
				Statement statement = connection.createStatement();
				String sql = "select * from score where courseNo='"
						+ courseList.get(i) + "' and stuNo='" + stuNo + "'";
				ResultSet resultset = statement.executeQuery(sql);
				while (resultset.next()) {
					score = resultset.getInt("score");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scoreList.add(new ScorePO(stuNo, courseList.get(i), score));
		}
		return scoreList;
	}

	/**
	 * 登记成绩
	 * 
	 * @param courseNo
	 *            课程号
	 * @param teacherNo
	 *            教师工号
	 * @param stuNo
	 *            学生学号
	 * @param score
	 *            成绩
	 * @param credit
	 *            该门课的学分
	 */
	public void recordScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit) {
		try {
			Statement statement = connection.createStatement();
			String sql3 = "insert into score (courseNo,teacherNo,stuNo,score,credit) values ('"+ courseNo
					+ "','"+ teacherNo+ "','"+ stuNo+ "','"+ score + "','" + credit + "')";
			statement.execute(sql3);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更改成绩
	 * 
	 * @param courseNo
	 *            课程号
	 * @param teacherNo
	 *            教师工号
	 * @param stuNo
	 *            学生学号
	 * @param score
	 *            成绩
	 * @param credit
	 *            该门课的学分
	 */

	public void updateScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit) {
		String sql = "UPDATE score SET score ='" + score + "' where courseNo='"
				+ courseNo + "' and stuNo='" + stuNo + "'";
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * *查看教师所教课程成绩，根据读出的成绩以及传入的参数新建scorepo加入到arraylist中返回 登记成绩的时候就要把教师工号也传入
	 * 
	 * @param couId
	 *            课程号
	 * @param teacherId
	 *            教师工号
	 * @author 刘硕
	 */

	public ArrayList<ScorePO> checkCourseScore(String couId, String teacherId) {
		ArrayList<ScorePO> scoreList = new ArrayList<ScorePO>();
		try {
			Statement statement = connection.createStatement();
			String sql3 = "select * from score where courseNo='" + couId
					+ "' and teacherNo='" + teacherId + "'";
			ResultSet resultset = statement.executeQuery(sql3);
			while (resultset.next()) {
				int score = resultset.getInt(4);// getInt("score");
				String stuNo = resultset.getString(3);
				scoreList.add(new ScorePO(stuNo, couId, score));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scoreList;// 返回scorepo列表,然后只关注成绩这一选项;遍历选择，统计个分数段成绩人数
	}

}
