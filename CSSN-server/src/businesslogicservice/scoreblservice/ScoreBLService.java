package businesslogicservice.scoreblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.score.ScorePO;
import vo.score.ScoreVO;

/**
 * 接口名：ScoreBLService
 * 接口描述：Score模块提供的对外接口，包含与score.accdb有关的方法
 * @author Just Coding队
 * 
 */
public interface ScoreBLService extends Remote {
	/**
	 * 方法名：checkStuScore
	 * 方法描述：查看某学生某门课的成绩
	 * @param coNo
	 * @param stuNo
	 * @return 返回ScoreVO
	 * @throws RemoteException
	 */
	public ScoreVO checkStuScore(String coNo, String stuNo) throws RemoteException;
	
	/**
	 * 方法名：checkStuScore
	 * 方法描述：查看某学生所有课程的成绩
	 * @param stuNo
	 * @param courseList
	 * @return 返回ScoreVO列表
	 * @throws RemoteException
	 */
	public ArrayList<ScoreVO> checkStuScore(String stuNo,
			ArrayList<String> courseList) throws RemoteException;

	/**
	 * 方法名：updateScore
	 * 方法描述： 更改成绩(如果发布已经登记过成绩的学生的成绩)
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
	 * 方法名：recordScore
	 * 方法描述：登记成绩
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
	 * 方法名：checkScoreList
	 * 方法描述：查看成绩单（某一学期的成绩）
	 * @param stuNo
	 * @param grade
	 * @param semester
	 * @throws RemoteException
	 */
	public ArrayList<ScoreVO> checkScoreList(String stuNo, String grade,
			String semester) throws RemoteException;
	
	/**
	 * 方法名：checkCourseScore
	 * 方法描述：查看某老师教授的某门课程的成绩
	 * @param couId
	 * @param teacherId
	 * @return 返回ScorePO的列表
	 * @throws RemoteException
	 */
	public ArrayList<ScorePO> checkCourseScore(String couId,String teacherId) throws RemoteException;

}