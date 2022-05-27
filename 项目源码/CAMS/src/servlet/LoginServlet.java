package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daobase.*;
import dao.*;
import vo.*;
import net.sf.json.JSONArray;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("username");
		String pwd=request.getParameter("password");
		String role=request.getParameter("role");
		//System.out.println(id+";"+pwd+";"+role+";"+("admin".equals(role))); 
		String path=request.getContextPath();
		response.setContentType("text/html;charset=UTF-8");
		if("teacher".equals(role)) {  //教师端登陆
			TeacherDaoBase dao=new TeacherDao();
			Teacher t=dao.select(id);
			//System.out.println(pwd.equals(u.getPwd()));
			//创建session对象
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("loginId", t.getId());
            session.setAttribute("loginName", t.getTitle()+":"+t.getName());
			if(pwd.equals(t.getPwd())) {  //登录到主页
				response.sendRedirect("view/teacher/index.jsp");
			}
			else {   //账号或密码错误
				PrintWriter out = response.getWriter();
				out.print("<script charset='utf-8'>alert('账号或密码错误，请重新登录'); window.location='index.jsp' </script>");
				out.flush();
				out.close();
			}
		}
		else if("student".equals(role)) {  //学生端登陆
			UsrDaoBase dao=new UsrDao();
			usr u=dao.select(id);
			//System.out.println(pwd.equals(u.getPwd()));
			if(pwd.equals(u.getPwd())) {  //登录到主页
				response.sendRedirect("view/student/index.jsp");
			}
			else {   //账号或密码错误
				PrintWriter out = response.getWriter();
				out.print("<script charset='utf-8'>alert('账号或密码错误，请重新登录'); window.location='index.jsp' </script>");
				out.flush();
				out.close();
			}
		}
		else if("admin".equals(role)) {  //管理员端登陆
			UsrDaoBase dao=new UsrDao();
			usr u=dao.select(id);
			//System.out.println(pwd.equals(u.getPwd()));
			if(pwd.equals(u.getPwd())) {  //登录到主页
				response.sendRedirect("view/admin/index.jsp");
			}
			else {   //账号或密码错误
				PrintWriter out = response.getWriter();
				out.print("<script charset='utf-8'>alert('账号或密码错误，请重新登录'); window.location='index.jsp' </script>");
				out.flush();
				out.close();
			}
		}
		else {   //账号或密码错误
			PrintWriter out = response.getWriter();
			out.print("<script charset='utf-8'>alert('账号或密码错误，请重新登录'); window.location='index.jsp' </script>");
			out.flush();
			out.close();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
