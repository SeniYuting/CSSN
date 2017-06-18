package po.courseselection;

import java.io.Serializable;

public class CourseSelectionPO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String courseNO;
	private String stuNO;
	private String teacherNO;
	private String state;
	private String semester;
	
	public CourseSelectionPO(String courseNO,String stuNO,String teacherNO,String state,String semester){
		this.courseNO = courseNO;
		this.stuNO = stuNO;
		this.teacherNO = teacherNO;
		this.state = state;
		this.semester = semester;
	}
	
	public String getCourseNO(){
		return this.courseNO;
	}
	
	public String getStuNO(){
		return this.stuNO;
	}
	
	public String getTeacherNO(){
		return this.teacherNO;
	}
	
	public String getState(){
		return this.state;
	}
	
	public String getSemester(){
		return this.semester;
	}
	
	
	public String toString() {
		return courseNO  + " " + stuNO + " " + teacherNO + " " + state + " " + semester;
	}
}
