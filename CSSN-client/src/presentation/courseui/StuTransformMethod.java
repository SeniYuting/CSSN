package presentation.courseui;

import java.util.ArrayList;

import vo.course.CourseVO;

public class StuTransformMethod {

	/**
	 * @param args
	 */
	public Object[][] transform(ArrayList<CourseVO> courseList){
		Object[][] list = new Object[courseList.size()][8];
		for(int i = 0;i < courseList.size();i ++){
			CourseVO vo = courseList.get(i);
			String courseNO = vo.getCoID();
			String courseName = vo.getCoName();
			boolean isCompulsory = vo.getIsCompulsory();
			String credit = vo.getCredit();
			String teacher = vo.getTeacher();
			String location = vo.getLocation();
			String time = vo.getTime();
			list[i][0] = i + 1;
			list[i][1] = courseNO;
			list[i][2] = courseName;
			if(isCompulsory){
				list[i][3] = "����";
			}else{
				list[i][3] = "ѡ��";
			}
			list[i][4] = credit;
			list[i][5] = teacher;
			list[i][6] = location;
			list[i][7] = time;
		}
		
		return list;
	}
	
	public Object[][] shortTransform(ArrayList<CourseVO> course){
		Object[][] courseList = new Object[course.size()][4];
		for(int i = 0;i < course.size();i ++){
			CourseVO vo = course.get(i);
			String courseNO = vo.getCoID();
			String courseName = vo.getCoName();
			String teacher = vo.getTeacher();
			courseList[i][0] = i + 1;
			courseList[i][1] = courseNO;
			courseList[i][2] = courseName;
			courseList[i][3] = teacher;
		}
		
		return courseList;
	}
	
	public Object[][] transformThreeItem(ArrayList<CourseVO> course){
		Object[][] courseList = new Object[course.size()][4];
		for(int i = 0;i < course.size();i ++){
			CourseVO vo = course.get(i);
			String courseNO = vo.getCoID();
			String courseName = vo.getCoName();
			String credit = vo.getCredit();
			courseList[i][0] = i + 1;
			courseList[i][1] = courseNO;
			courseList[i][2] = courseName;
			courseList[i][3] = credit;
		}
		
		return courseList;
	}

}
