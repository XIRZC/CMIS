package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;


import daobase.ClassroomDaoBase;
import daobase.AttendClassDaoBase;
import dao.ClassroomDao;
import dao.AttendClassDao;
import vo.classroom;
import vo.attendclass;
import util.DateUtil;
import util.StrUtil;
import vo.classcourse;
import vo.weekcourse;

public class ClassroomServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("showAll".equalsIgnoreCase(method)) { 	//显示所有教室信息
			showAll(request,response);
		}
		else if("showRoomCourseDay".equalsIgnoreCase(method)) {  //显示教室今日课程表
			showRoomCourse(request,response,"day");
		}
		else if("showRoomCourseWeek".equalsIgnoreCase(method)) {  //显示教室本周课程表
			showRoomCourse(request,response,"week");
		}
		else if("showEmptyRoom".equalsIgnoreCase(method)) {  //显示空教室
			showEmptyRoom(request,response);
		}
		else if("showCourseRecord".equalsIgnoreCase(method)) {  //显示课程排课记录
			showCourseRecord(request,response);
		}
		else if("showEmptyRoomWeek".equalsIgnoreCase(method)) {  //显示一周空教室的集合表
			showRoomCourse(request,response,"weekunion");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		doGet(request, response);
		if("arrangeCourse".equalsIgnoreCase(method)) {  //添加排课
			arrangeCourse(request,response);
		}
		else if("arrangeEdit".equalsIgnoreCase(method)) {  //修改排课
			arrangeEdit(request,response);
		}
		else if("arrangeDelete".equalsIgnoreCase(method)) {  //删除排课
			arrangeDelete(request,response);
		}
		else if("roomAdd".equalsIgnoreCase(method)) {  //添加教室
			roomAdd(request,response);
		}
		else if("roomDelete".equalsIgnoreCase(method)) {  //删除教室
			roomDelete(request,response);
		}
		else if("roomEdit".equalsIgnoreCase(method)) {  //更新教室
			roomEdit(request,response);
		}
	}
	
	private void roomAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		classroom r=new classroom(request.getParameter("rid"),request.getParameter("seatnum"),request.getParameter("isstair"));
		ClassroomDaoBase dao=new ClassroomDao();
		dao.add(r);
		response.sendRedirect("/CAMS/view/admin/classroomManage.jsp");
	}

	private void roomDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rid=request.getParameter("rid");
		ClassroomDaoBase dao=new ClassroomDao();
		dao.delete(rid);
		response.sendRedirect("/CAMS/ClassroomServlet?method=showAll");
	}

	private void roomEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		classroom r=new classroom(request.getParameter("id"),request.getParameter("seatnum"),request.getParameter("isstair"));
		ClassroomDaoBase dao=new ClassroomDao();
		System.out.println(request.getParameter("rid")+request.getParameter("seatnum")+request.getParameter("isstair"));
		dao.update(r);
		response.sendRedirect("/CAMS/ClassroomServlet?method=showAll");
	}
	
	private void arrangeCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attendclass ac=new attendclass(StrUtil.weekname2id(request.getParameter("weekdayno")),StrUtil.typename2id(request.getParameter("type")),
				StrUtil.secsname2id(request.getParameter("secno")),StrUtil.secsname2id(request.getParameter("secno"))+1,
				request.getParameter("rid"),request.getParameter("cid"),request.getParameter("tid"));
		AttendClassDaoBase dao=new AttendClassDao();
		dao.add(ac);
		response.sendRedirect("/CAMS/view/teacher/courseRecord.jsp?cid="+request.getParameter("cid"));
	}
	
	private void arrangeEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attendclass ac=new attendclass(Integer.parseInt(request.getParameter("weekdayno")),Integer.parseInt(request.getParameter("type")),
				Integer.parseInt(request.getParameter("secsno")),Integer.parseInt(request.getParameter("seceno")),
				request.getParameter("rid"),request.getParameter("cid"),request.getParameter("tid"));
		AttendClassDaoBase dao=new AttendClassDao();
		dao.update(ac);
		response.sendRedirect("/CAMS/view/teacher/courseRecord.jsp?cid="+request.getParameter("cid"));
	}
	
	private void arrangeDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attendclass ac=new attendclass(Integer.parseInt(request.getParameter("weekdayno")),Integer.parseInt(request.getParameter("type")),
				Integer.parseInt(request.getParameter("secsno")),Integer.parseInt(request.getParameter("seceno")),
				request.getParameter("rid"),request.getParameter("cid"),request.getParameter("tid"));
		AttendClassDaoBase dao=new AttendClassDao();
		dao.delete(ac);
		response.sendRedirect("/CAMS/view/teacher/courseRecord.jsp?cid="+request.getParameter("cid"));
	}
	
	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String rid=request.getParameter("rid");
		String building=request.getParameter("building");
		
		System.out.println("rid:"+rid+",building:"+building);
		
		List<classroom> list=new ArrayList<>();
		ClassroomDaoBase dao=new ClassroomDao();
		if(rid!=""&&rid!=null) {
			System.out.println("1");
			classroom r=dao.selectById(rid);
			if(r!=null)
				list.add(r);
		}
		else if(building!=""&&building!=null) {
			System.out.println("2");
			list=dao.selectByBuilding(building);
		}
		else {
			System.out.println("3");
			list=dao.selectAll();
		}
		// 把集合转换成json
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		String str = "{\"code\":0,\"msg\":\"\",\"count\":" + 12 + ",\"data\":" + jsonArray.toString() + "}";
		response.getWriter().write(str);
		request.setAttribute("all", list);
	}
	
	private void showCourseRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		AttendClassDaoBase dao=new AttendClassDao();
		String cid=request.getParameter("cid");
		String tid=request.getParameter("tid");
		List<attendclass> list = dao.selectCourseRecord(tid,cid);
		// 把集合转换成json
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		String str = "{\"code\":0,\"msg\":\"\",\"count\":" + 12 + ",\"data\":" + jsonArray.toString() + "}";
		response.getWriter().write(str);
		request.setAttribute("all", list);
	}
	
	private void showRoomCourse(HttpServletRequest request, HttpServletResponse response,String type) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		//获取当前日期月份、日期、周几、相对校历第几周
		int month=DateUtil.getmonth();
		int day=DateUtil.getday();
		int weekdayno=DateUtil.getweekdayno();
		int weekno=DateUtil.getweekno();
		
		String rid=request.getParameter("rid");
		AttendClassDaoBase dao=new AttendClassDao();
		
		System.out.println("月份："+month+"，日期："+day+"，星期："+weekdayno+"，第"+weekno+"周"+",rid:"+rid);
		if(type=="day") {
			List<attendclass> list_attend = dao.selectRoomCourseDay(rid,weekno,weekdayno);
			List<classcourse> list_course=new ArrayList<>();
			int added[]=new int[11];
			for(int i=1;i<=10;i++) {
				for(attendclass ac:list_attend) {
					int secsno=ac.getSecsno();
					int seceno=ac.getSeceno();
					String cname=ac.getCname();
					String tname=ac.getTname();
					if(secsno==i) {
						for(int j=secsno;j<=seceno;j++) {
							classcourse cc=new classcourse();
							cc.setCname(cname);
							cc.setTname(tname);
							cc.setSecno(i);
							cc.setTime(StrUtil.sec2time[j]);
							cc.setSecname(StrUtil.sec2name[j]);
							added[j]=1;
							list_course.add(cc);
						}
						i+=1;
						break;
					}
				}
			}
			for(int i=1;i<=10;i++) {
				if(added[i]!=1) {
					classcourse cc=new classcourse();
					cc.setSecno(i);
					cc.setTime(StrUtil.sec2time[i]);
					cc.setSecname(StrUtil.sec2name[i]);
					list_course.add(cc);
				}
			}
			Collections.sort(list_course); 
			// 把集合转换成json
			JSONArray jsonArray = JSONArray.fromObject(list_course);
			System.out.println(jsonArray.toString());
			String str = "{\"code\":0,\"msg\":\"\",\"count\":" + 12 + ",\"data\":" + jsonArray.toString() + "}";
			response.getWriter().write(str);
			request.setAttribute("all", list_course);
		}
		else if(type=="week") {
			List<attendclass> list_attend = dao.selectRoomCourseWeek(rid,weekno);
			List<weekcourse> list_course=new ArrayList<>();
			
			for(int secno=1;secno<=9;secno+=2) { //对所有的节次进行遍历
				weekcourse wc=new weekcourse();
				wc.setSecno(secno);
				wc.setSecname(StrUtil.secs2name[secno/2+1]);
				for(attendclass ac:list_attend) {
					if(ac.getSecsno()!=secno)  //非当前节
						continue;
					
					int forweekdayno=ac.getWeekdayno();
					String cname=ac.getCname();
					String tname=ac.getTname();
					wc.setWeekCourse(forweekdayno, cname+"("+tname+")");
				}
				list_course.add(wc);
			}
			
			// 把集合转换成json
			JSONArray jsonArray = JSONArray.fromObject(list_course);
			System.out.println(jsonArray.toString());
			String str = "{\"code\":0,\"msg\":\"\",\"count\":" + 12 + ",\"data\":" + jsonArray.toString() + "}";
			response.getWriter().write(str);
			request.setAttribute("all", list_course);
		}
		else if(type=="weekunion") {
			String Type=request.getParameter("type");
			String weeksno=request.getParameter("weeksno");
			String weekeno=request.getParameter("weekeno");
			//System.out.println("______"+Type+"_"+StrUtil.typename2id(Type)+"_"+Integer.parseInt(weeksno)+"_"+Integer.parseInt(weekeno));
			
			List<weekcourse> list_weekemptyroom=new ArrayList<>();
			for(int secno=1;secno<=9;secno+=2) { //对所有的节次进行遍历
				weekcourse wc=new weekcourse();
				wc.setSecno(secno);
				wc.setSecname(StrUtil.secs2name[secno/2+1]);
				for(int wdayno=1;wdayno<=5;wdayno++) {  //对所有的星期进行遍历
					String emptyall="";
					List<classroom> list_classroom=dao.selectWeekEmptyRoom(StrUtil.typename2id(Type),Integer.parseInt(weeksno),Integer.parseInt(weekeno),wdayno,secno);  //单、双、连续周参数下的一个week表显示所有空教室;
					int num=0;
					for(classroom rc:list_classroom) {
						num++;
						emptyall+=rc.getId()+"&nbsp";
						if(num%3==0)
							emptyall+="<br>";
					}
					wc.setWeekCourse(wdayno, emptyall);
				}
				list_weekemptyroom.add(wc);
			}
			
			// 把集合转换成json
			JSONArray jsonArray = JSONArray.fromObject(list_weekemptyroom);
			System.out.println(jsonArray.toString());
			String str = "{\"code\":0,\"msg\":\"\",\"count\":" + 12 + ",\"data\":" + jsonArray.toString() + "}";
			response.getWriter().write(str);
			request.setAttribute("all", list_weekemptyroom);
		}
	}
	
	private void showEmptyRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String building=request.getParameter("building");
		String weekno=request.getParameter("weekno");
		String weekdayno=request.getParameter("weekdayno");
		String secno=request.getParameter("secno");
		//System.out.println("星期号"+StrUtil.weekname2id(weekdayno));
		AttendClassDaoBase dao=new AttendClassDao();
		List<classroom> list = dao.selectEmptyRoom(building,Integer.parseInt(weekno),StrUtil.weekname2id(weekdayno),StrUtil.secname2id(secno));
		// 把集合转换成json
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		String str = "{\"code\":0,\"msg\":\"\",\"count\":" + 12 + ",\"data\":" + jsonArray.toString() + "}";
		response.getWriter().write(str);
		request.setAttribute("all", list);
	}
}
