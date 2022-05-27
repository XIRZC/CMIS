package daobase;

import java.util.List;

import vo.attendclass;
import vo.classroom;

public interface AttendClassDaoBase {
	List<attendclass> selectRoomCourseDay(String rid,int weekno,int weekdayno);  //根据教室号查今日课程表
	List<attendclass> selectRoomCourseWeek(String rid,int weekno);  //根据教室号查本周课程表
	
	List<classroom> selectWeekEmptyRoom(int type,int weeksno,int weekeno,int weekdayno,int secno);  //查询单/双/连续周条件下的某课程周范围下空教室表
	
	//看该条
	List<classroom> selectEmptyRoom(String building,int weekno,int weekdayno,int secno);   //查询给定教学楼，周序号，星期号，节数查询空教室
	
	List<attendclass> selectCourseRecord(String tid,String cid);   //查看某教师某课程已有的排课记录
	void add(attendclass ac);			//给指定老师所教的课排教室
	void update(attendclass ac); 		//给指定老师所教的课修改排教室的信息
	void delete(attendclass ac);		//给指定老师所教的课删除排教室的信息
}
