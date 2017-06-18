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
	 * ѧ���鿴�Լ�ĳһ�ſγ̵ĳɼ� ��ֻ��ѧ���鿴�γ̳ɼ�������һ������;����Ҫȥ���ò鿴�Լ���ѡ���б�
	 * 
	 * @param stuNoѧ��ѧ��
	 * @param courseNo
	 *            �γ̺�
	 * @return ScorePo����
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
		// ����Ҳ�����¼,����û�Ǽ�,����һ��Ҫ��scorepo����;��vo��Ҫ�жϣ����score��-1����ô��ʾδ����
		scorepo = new ScorePO(stuNo, courseNo, score, credit);
		return scorepo;
	}

	/**
	 * ѧ���鿴�Լ�ȫ���γ̵ĳɼ� ��ֻ��ѧ���鿴�γ̳ɼ�������һ������;����Ҫȥ���ò鿴�Լ���ѡ���б�
	 * 
	 * @param stuNoѧ��ѧ��
	 * @param courseList
	 *            �γ̺��б�
	 * @return ScorePo�����б�
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
	 * �Ǽǳɼ�
	 * 
	 * @param courseNo
	 *            �γ̺�
	 * @param teacherNo
	 *            ��ʦ����
	 * @param stuNo
	 *            ѧ��ѧ��
	 * @param score
	 *            �ɼ�
	 * @param credit
	 *            ���ſε�ѧ��
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
	 * ���ĳɼ�
	 * 
	 * @param courseNo
	 *            �γ̺�
	 * @param teacherNo
	 *            ��ʦ����
	 * @param stuNo
	 *            ѧ��ѧ��
	 * @param score
	 *            �ɼ�
	 * @param credit
	 *            ���ſε�ѧ��
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
	 * *�鿴��ʦ���̿γ̳ɼ������ݶ����ĳɼ��Լ�����Ĳ����½�scorepo���뵽arraylist�з��� �Ǽǳɼ���ʱ���Ҫ�ѽ�ʦ����Ҳ����
	 * 
	 * @param couId
	 *            �γ̺�
	 * @param teacherId
	 *            ��ʦ����
	 * @author ��˶
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
		return scoreList;// ����scorepo�б�,Ȼ��ֻ��ע�ɼ���һѡ��;����ѡ��ͳ�Ƹ������γɼ�����
	}

}
