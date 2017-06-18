package businesslogicservice.scoreblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.score.ScorePO;
import vo.score.ScoreVO;

/**
 * �ӿ�����ScoreBLService
 * �ӿ�������Scoreģ���ṩ�Ķ���ӿڣ�������score.accdb�йصķ���
 * @author Just Coding��
 * 
 */
public interface ScoreBLService extends Remote {
	/**
	 * ��������checkStuScore
	 * �����������鿴ĳѧ��ĳ�ſεĳɼ�
	 * @param coNo
	 * @param stuNo
	 * @return ����ScoreVO
	 * @throws RemoteException
	 */
	public ScoreVO checkStuScore(String coNo, String stuNo) throws RemoteException;
	
	/**
	 * ��������checkStuScore
	 * �����������鿴ĳѧ�����пγ̵ĳɼ�
	 * @param stuNo
	 * @param courseList
	 * @return ����ScoreVO�б�
	 * @throws RemoteException
	 */
	public ArrayList<ScoreVO> checkStuScore(String stuNo,
			ArrayList<String> courseList) throws RemoteException;

	/**
	 * ��������updateScore
	 * ���������� ���ĳɼ�(��������Ѿ��Ǽǹ��ɼ���ѧ���ĳɼ�)
	 * @param courseNo
	 * @param teacherNo
	 * @param stuNo
	 * @param score
	 * @param credit
	 * @throws RemoteException
	 */
	public void updateScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit) throws RemoteException;

	/**
	 * ��������recordScore
	 * �����������Ǽǳɼ�
	 * @param courseNo
	 * @param teacherNo
	 * @param stuNo
	 * @param score
	 * @param credit
	 * @throws RemoteException
	 */
	public void recordScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit) throws RemoteException;

	/**
	 * ��������checkScoreList
	 * �����������鿴�ɼ�����ĳһѧ�ڵĳɼ���
	 * @param stuNo
	 * @param grade
	 * @param semester
	 * @throws RemoteException
	 */
	public ArrayList<ScoreVO> checkScoreList(String stuNo, String grade,
			String semester) throws RemoteException;
	
	/**
	 * ��������checkCourseScore
	 * �����������鿴ĳ��ʦ���ڵ�ĳ�ſγ̵ĳɼ�
	 * @param couId
	 * @param teacherId
	 * @return ����ScorePO���б�
	 * @throws RemoteException
	 */
	public ArrayList<ScorePO> checkCourseScore(String couId,String teacherId) throws RemoteException;

}