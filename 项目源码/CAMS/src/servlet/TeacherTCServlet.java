package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.TeachCourseDao;
import daobase.TeachCourseDaoBase;
import net.sf.json.JSONArray;
import vo.teachcourse;

public class TeacherTCServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("showCourse".equalsIgnoreCase(method)) {  //查某一个教师的所有授课表
			showCourse(request,response);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	public void showCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		List<teachcourse> list=null;
		TeachCourseDaoBase dao=new TeachCourseDao();
		String tid=request.getParameter("tid");
		list= dao.selectByTid(tid);

		// 把集合转换成json
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		String str = "{\"code\":0,\"msg\":\"\",\"count\":" + 12 + ",\"data\":" + jsonArray.toString() + "}";
		response.getWriter().write(str);
		request.setAttribute("all", list);
	}
}
