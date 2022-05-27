<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String type=request.getParameter("type");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>空教室周表</title>
	<link rel="stylesheet" href="<%=path %>/static/lib/layui/css/layui.css">
	<script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript"src="<%=path %>/static/js/md5.min.js"></script>
    <script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/static/js/X-admin/xadmin.js"></script>
    <script type="text/javascript" src="<%=path %>/static/js/X-admin/cookie.js"></script>
    <style type="text/css">
        {# 设置table每一行的height #}
        .layui-table-cell {
            height: auto;
            line-height: 60px;
        }
    </style>
</head>
<body>
<div>空教室周表</div>  
	<!-- 以下隐藏input是为了实现jsp全局变量引入到js中便于layui url传值 -->
	<input type="hidden" id="type" value="<%=type%>">
	<table class="layui-hide" id="emptyroom" lay-filter="emptyroom"></table>         
	          
	<script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
	 
	<script>
	layui.use('table', function(){
	  //jquery取值
	  var type = $("#type").val();
	  var table = layui.table;
	  
	  table.render({
		    elem: '#emptyroom'
		    ,url:'/CAMS/ClassroomServlet?method=showEmptyRoomWeek'
		    ,title: '本周课程表'
		    ,cols: [[
		      {field:'secname', title:'节次/周次',  fixed: 'left', unresize: true,sort:true}
		      ,{field:'one', title:'星期一' }
		      ,{field:'two', title:'星期二' }
		      ,{field:'three', title:'星期三' }
		      ,{field:'four', title:'星期四' }
		      ,{field:'five', title:'星期五' }
		    ]]
		  });
	});	//layui.use结束
	</script>
</body>
</html>