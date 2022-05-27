<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String rid=request.getParameter("rid");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>今日课程表</title>
	<link rel="stylesheet" href="<%=path %>/static/lib/layui/css/layui.css">
	<script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript"src="<%=path %>/static/js/md5.min.js"></script>
    <script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/static/js/X-admin/xadmin.js"></script>
    <script type="text/javascript" src="<%=path %>/static/js/X-admin/cookie.js"></script>
</head>
<body>
	<!-- 以下隐藏input是为了实现jsp全局变量引入到js中便于layui url传值 -->
	<input type="hidden" id="rid" value="<%=rid%>">
	<table class="layui-hide" id="test" lay-filter="test"></table>
	
	
	<script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript">
	layui.use('table', function(){
		  var table = layui.table;
		  var rid=$("#rid").val();
		  table.render({
		    elem: '#test'
		    ,url:'/CAMS/ClassroomServlet?method=showRoomCourseDay&rid='+rid
		    //,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
		    ,title: '今日课程表'
		    ,cols: [[
		      {field:'secname', title:'节数',  fixed: 'left', unresize: true,sort:true}
		      ,{field:'cname', title:'课程名' , sort:true}
		      ,{field:'tname', title:'任课教师' , sort:true}
		      ,{field:'time', title:'时间' , sort:true}
		    ]]
		  });
	})
    </script>
</body>
</html>