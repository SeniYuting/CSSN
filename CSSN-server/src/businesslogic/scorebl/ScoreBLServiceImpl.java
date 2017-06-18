package businesslogic.scorebl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.score.ScorePO;
import data.scoredata.ScoreDAO;
import vo.course.CourseVO;
import vo.score.ScoreVO;
import businesslogic.courseselectionbl.CourseSelectionBLServiceImpl;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.scoreblservice.ScoreBLService;

public class ScoreBLServiceImpl extends UnicastRemoteObject implements ScoreBLService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScoreBLServiceImpl() throws RemoteException {
	}

	public ScoreVO checkStuScore(String coNo, String stuNo) throws RemoteException{
		ScoreVO scorevo;
		ScorePO scorepo;
		ScoreDAO scoredao = new ScoreDAO();
		scorepo = scoredao.checkStuScore(stuNo,coNo);
		scorevo = new ScoreVO(scorepo.getStuNO(), scorepo.getCourseNO(),
				scorepo.getScore(),scorepo.getCredit());
		return scorevo;
	}

	public ArrayList<ScoreVO> checkStuScore(String stuNo,
			ArrayList<String> courseList) throws RemoteException{
		// TODO Auto-generated method stub
		ScoreDAO scoredao = new ScoreDAO();
		ArrayList<ScorePO> polist = scoredao.checkStuScore(stuNo, courseList);
		ArrayList<ScoreVO> volist = new ArrayList<ScoreVO>();
		for (int i = 0; i < polist.size(); i++) {
			volist.add(new ScoreVO(polist.get(i).getStuNO(), polist.get(i)
					.getCourseNO(), polist.get(i).getScore(),polist.get(i).getCredit()));
		}
		return volist;
	}

	//���ĳɼ�(��������Ѿ��Ǽǹ��ɼ���ѧ���ĳɼ�)
		public void updateScore(String courseNo, String teacherNo, String stuNo,
				int score,int credit) throws RemoteException{
			ScoreDAO scoredao = new ScoreDAO();
			scoredao.updateScore(courseNo, teacherNo, stuNo, score, credit);
		}
	
	@Override
	public void recordScore(String courseNo, String teacherNo, String stuNo,
			int score,int credit) throws RemoteException{
		ScoreDAO scoredao = new ScoreDAO();
		scoredao.recordScore(courseNo, teacherNo, stuNo, score,credit);
	}
	
	/**
	 * �鿴�ɼ���
	 * 
	 * @param stuNoѧ��ѧ��
	 * @param gradeѧ�����꼶
	 * @param semester����ѧ��;����ӽ�����
	 * @return VO
	 */
	public ArrayList<ScoreVO> checkScoreList(String stuNo, String grade,
			String semester) throws RemoteException{
		ArrayList<ScoreVO> scoreList=new ArrayList<ScoreVO>();
		try {
			CourseSelectionBLService csmservice = new CourseSelectionBLServiceImpl();
			ArrayList<CourseVO> AllCourseList = csmservice.checkMyCourse(stuNo,"true");// �õ����пγ�
			ArrayList<CourseVO> courseList = new ArrayList<CourseVO>();// ɸѡ����
			for (int i = 0; i < AllCourseList.size(); i++) {
				if (AllCourseList.get(i).getGrade().equals(grade)
						&& (AllCourseList.get(i).getSemester().equals(semester))) {//�ҵ������꼶��ѧ�ڵĿγ�
					courseList.add(AllCourseList.get(i));
				}
			}
			for(int i=0;i<courseList.size();i++){
				scoreList.add(checkStuScore(courseList.get(i).getCoID(), stuNo));
				
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return scoreList;
	}
	
	public ArrayList<ScorePO> checkCourseScore(String couId, String teacherId) throws RemoteException{
		// TODO Auto-generated method stub
		ArrayList<ScorePO> scoreList=new ScoreDAO().checkCourseScore(couId, teacherId);
		return scoreList;
	}

}
