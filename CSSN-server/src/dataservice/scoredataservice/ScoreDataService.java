package dataservice.scoredataservice;

import java.util.ArrayList;

import po.score.ScorePO;

/**
 * 接口名：ScoreDataService
 * 接口描述：Score模块提供的对外接口，包含与score.accdb有关的方法
 * @author Just Coding队
 * 
 */
public interface ScoreDataService {
	 /**
	  * 方法名：checkStuScore
	  * 方法描述：学生查看自己某一门课程的成绩 这只是学生查看课程成绩用例的一个步骤;首先要去调用查看自己的选课列表
	  * @param stuNo
	  * @param courseNo
	  * @return 返回ScorePO
	  */
	public ScorePO checkStuScore(String stuNo, String courseNo);
 
	 /**
	  * 方法名：checkStuScore
	  * 方法描述：学生查看自己全部课程的成绩 这只是学生查看课程成绩用例的一个步骤;首先要去调用查看自己的选课列表
	  * @param stuNo
	  * @param courseNo
	  * @return 返回ScorePO的列表
	  */
	public ArrayList<ScorePO> checkStuScore(String stuNo,ArrayList<String> courseList);


	/**
	 * 方法名：recordScore
	 * 方法描述：登记成绩
	 * @param courseNo
	 * @param teacherNo
	 * @param stuNo
	 * @param score
	 * @param credit
	 */
	public void recordScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit);

	/**
	 * 方法名：updateScore
	 * 方法描述：更新成绩
	 * @param courseNo
	 * @param teacherNo
	 * @param stuNo
	 * @param score
	 * @param credit
	 */
	public void updateScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit);

	 /**
	  * 方法名：checkCourseScore
	  * 方法描述：查看教师所教课程成绩，根据读出的成绩以及传入的参数新建scorepo加入到arraylist中返回 登记成绩的时候就要把教师工号也传入
	  * @param couId
	  * @param teacherId
	  * @return 返回ScorePO的列表
	  */
	public ArrayList<ScorePO> checkCourseScore(String couId, String teacherId);

}
