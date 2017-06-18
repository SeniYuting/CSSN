package po.plan;
import java.util.ArrayList;

import vo.plan.Course;

public class PlanPO{
	
	private String institute;//
	private int grade;//
	private int semester;//
	private ArrayList<Course> openList;//开放选修列表
	private ArrayList<Course> majorList;//专业课程列表
	
	public PlanPO(String institute,int grade,int semester
			,ArrayList<Course> openList,ArrayList<Course> majorList){
		this.institute=institute;
		this.grade=grade;
		this.semester=semester;
		this.openList=openList;
		this.majorList=majorList;
	}
	
	public String getInstitute(){
		return institute;
	}
	
	public int getGrade(){
		return grade;
	}
	
	public int getSemester(){
		return semester;
	}
	
	public ArrayList<Course> getOpenList(){
		return openList;
	}
	
	public ArrayList<Course> getMajorList(){
		return majorList;
	}

}
