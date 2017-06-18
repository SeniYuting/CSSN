package dataservice.scoredataservice;

import java.util.ArrayList;

import po.score.ScorePO;

/**
 * �ӿ�����ScoreDataService
 * �ӿ�������Scoreģ���ṩ�Ķ���ӿڣ�������score.accdb�йصķ���
 * @author Just Coding��
 * 
 */
public interface ScoreDataService {
	 /**
	  * ��������checkStuScore
	  * ����������ѧ���鿴�Լ�ĳһ�ſγ̵ĳɼ� ��ֻ��ѧ���鿴�γ̳ɼ�������һ������;����Ҫȥ���ò鿴�Լ���ѡ���б�
	  * @param stuNo
	  * @param courseNo
	  * @return ����ScorePO
	  */
	public ScorePO checkStuScore(String stuNo, String courseNo);
 
	 /**
	  * ��������checkStuScore
	  * ����������ѧ���鿴�Լ�ȫ���γ̵ĳɼ� ��ֻ��ѧ���鿴�γ̳ɼ�������һ������;����Ҫȥ���ò鿴�Լ���ѡ���б�
	  * @param stuNo
	  * @param courseNo
	  * @return ����ScorePO���б�
	  */
	public ArrayList<ScorePO> checkStuScore(String stuNo,ArrayList<String> courseList);


	/**
	 * ��������recordScore
	 * �����������Ǽǳɼ�
	 * @param courseNo
	 * @param teacherNo
	 * @param stuNo
	 * @param score
	 * @param credit
	 */
	public void recordScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit);

	/**
	 * ��������updateScore
	 * �������������³ɼ�
	 * @param courseNo
	 * @param teacherNo
	 * @param stuNo
	 * @param score
	 * @param credit
	 */
	public void updateScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit);

	 /**
	  * ��������checkCourseScore
	  * �����������鿴��ʦ���̿γ̳ɼ������ݶ����ĳɼ��Լ�����Ĳ����½�scorepo���뵽arraylist�з��� �Ǽǳɼ���ʱ���Ҫ�ѽ�ʦ����Ҳ����
	  * @param couId
	  * @param teacherId
	  * @return ����ScorePO���б�
	  */
	public ArrayList<ScorePO> checkCourseScore(String couId, String teacherId);

}
