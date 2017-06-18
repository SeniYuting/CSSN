package po.course;

import java.io.Serializable;

public class CoursePO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String coID;
	String coName;
	boolean isCompulsory;
	String credit;
	String hour;
	String module;
	String teaID;
	String teacher;
	String semester;
	String grade;
	String institution;
	String numOfStu;
	String time;
	String location;
	String description;
	String textbook;
	String reference;

	// 无参构造函数
	public CoursePO() {}

	// 教务处老师发布完整信息
	// 课程号_课程名_课程性质_学分_课时_所属模块_任课老师工号_任课老师_开设学期_开设年级_开设院系_开课人数_上课时间__上课地点_课程描述_教材_参考书目
	public CoursePO(String coID, String coName, boolean isCompulsory,
			String credit, String hour, String module, String teaID,
			String teacher,String semester, String grade, String institution, String numOfStu,String time,
			String location, String description, String textbook,
			String reference) {
		this.coID = coID;
		this.coName = coName;
		this.isCompulsory = isCompulsory;
		this.credit = credit;
		this.hour = hour;
		this.module = module;
		this.teaID = teaID;
		this.teacher = teacher;
		this.semester=semester;
		this.grade = grade;
		this.institution = institution;
		this.numOfStu=numOfStu;
		this.time = time;
		this.location = location;
		this.description = description;
		this.textbook = textbook;
		this.reference = reference;
	}

	// 为了返回课程简单信息列表
	public CoursePO(String coID, String coName) {
		this.coID = coID;
		this.coName = coName;
	}

	//给任课教师返回课程
	public CoursePO(String coID, String coName,int credit) {
		this.coID = coID;
		this.coName = coName;
		this.credit=credit+"";
	}
	
	public CoursePO(String coID, String coName,int credit,String grade,String semester) {
		this.coID = coID;
		this.coName = coName;
		this.credit=credit+"";
		this.grade=grade;
		this.semester=semester;
	}
	
	// 院系教务老师发布部分信息
	// 课程号_课程名_课程性质_学分_课时_所属模块_任课老师工号_任课老师_开设学期_开设年级_开设院系_开课人数_上课时间__上课地点
	public CoursePO(String coID, String coName, boolean isCompulsory,
			String credit, String hour, String module, String teaID,
			String teacher, String semester,String grade, String institution, String numOfStu,String time,
			String location) {
		this.coID = coID;
		this.coName = coName;
		this.isCompulsory = isCompulsory;
		this.credit = credit;
		this.hour = hour;
		this.module = module;
		this.teaID = teaID;
		this.teacher = teacher;
		this.semester=semester;
		this.grade = grade;
		this.institution = institution;
		this.numOfStu=numOfStu;
		this.time = time;
		this.location = location;
	}

	// 任课老师填写课程信息时补充的信息
	// 课程描述_教材_参考书目
	public CoursePO(String description, String textbook, String reference) {
		this.description = description;
		this.textbook = textbook;
		this.reference = reference;
	}

	// get方法
	public String getCoID() {
		return coID;
	}

	public String getCoName() {
		return coName;
	}

	public boolean getIsCompulsory() {
		return isCompulsory;
	}

	public String getCredit() {
		return credit;
	}

	public String getHour() {
		return hour;
	}

	public String getModule() {
		return module;
	}

	public String getTeaID() {
		return teaID;
	}

	public String getTeacher() {
		return teacher;		
	}
	public String getSemester(){
		return semester;
	}
	public String getNumOfStu() {
		return numOfStu;
	}
	public String getTime() {
		return time;
	}

	public String getLocation() {
		return location;
	}

	public String getGrade() {
		return grade;
	}

	public String getInstitution() {
		return institution;
	}

	public String getDescription() {
		return description;
	}

	public String getTextbook() {
		return textbook;
	}

	public String getReference() {
		return reference;
	}

	// set方法
	void setCoID(String coID) {
		this.coID = coID;
	}

	void setCoName(String coName) {
		this.coName = coName;
	}

	void setIsCompulsory(boolean isCompulsory) {
		this.isCompulsory = isCompulsory;
	}

	void setCredit(String credit) {
		this.credit = credit;
	}

	void setHour(String hour) {
		this.hour = hour;
	}

	void setModule(String module) {
		this.module = module;
	}

	void setTeaID(String teaID) {
		this.teaID = teaID;
	}

	void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	void setTime(String time) {
		this.time = time;
	}

	void setLocation(String location) {
		this.location = location;
	}

	void setGrade(String grade) {
		this.grade = grade;
	}

	void setInstitution(String institution) {
		this.institution = institution;
	}

	void setDescription(String description) {
		this.description = description;
	}

	void setTextbook(String textbook) {
		this.textbook = textbook;
	}

	void setReference(String reference) {
		this.reference = reference;
	}
	
	public String toString() {
		return coID + " " + coName + " " + isCompulsory + " "
				+ credit + " " + hour + " " + module+ " " + teaID+ " " + teacher
				+ " " + grade+ " " + institution+ " " + numOfStu +" "+ time+ " " + location
				+ " " + description+ " " + textbook+ " " + reference;
	}
}
