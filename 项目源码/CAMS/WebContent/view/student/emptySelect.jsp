<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String building=request.getParameter("building");
	String weekno=request.getParameter("weekno");
	String weekdayno=request.getParameter("weekdayno");
	String secno=request.getParameter("secno");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>教师信息查询|教师信息系统</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    
    <link rel="stylesheet" href="<%=path %>/static/css/font.css">
	<link rel="stylesheet" href="<%=path %>/static/css/xadmin.css">
	
    <script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript"src="<%=path %>/static/js/md5.min.js"></script>
    <script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/static/js/X-admin/xadmin.js"></script>
    <script type="text/javascript" src="<%=path %>/static/js/X-admin/cookie.js"></script>
</head>
<body>
	<!-- 以下隐藏input是为了实现jsp全局变量引入到js中便于layui url传值 -->
	<input type="hidden" id="building" value="<%=building%>">
	<input type="hidden" id="weekno" value="<%=weekno%>">
	<input type="hidden" id="weekdayno" value="<%=weekdayno%>">
	<input type="hidden" id="secno" value="<%=secno%>">
	<div class="x-nav">
		<span class="layui-breadcrumb">
        	<a href="classroomInfo.jsp">教室信息查询</a>
        	<a><cite>空教室查询结果</cite></a>
      	</span>
	</div>
	<table class="layui-hide" id="test" lay-filter="test"></table>

	 
	<script type="text/html" id="barDemo">
  		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="selectDay">今日课程</a>
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="selectWeek">本周课程</a>
	</script>
	              
	          
	<script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
	 
	<script>
	layui.use('table', function(){
	  var table = layui.table;
	  var building=$("#building").val()
	  var weekno=$("#weekno").val()
	  var weekdayno=$("#weekdayno").val()
	  var secno=$("#secno").val()
	  //layer.msg(building+weekno+weekdayno+secno)
	  
	  table.render({
	    elem: '#test'
	    ,url:'/CAMS/ClassroomServlet?method=showEmptyRoom&building='+building+"&weekno="+weekno+"&weekdayno="+weekdayno+"&secno="+secno
	    //,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
	    ,title: '空教室信息'
	    ,cols: [[
	      {type: 'checkbox', fixed: 'left'}
	      ,{field:'id', title:'教室编号',  fixed: 'left', unresize: true, sort: true}
	      ,{field:'building', title:'所在教学楼' , sort:true}
	      ,{field:'floor', title:'楼层' , sort:true}
	      ,{field:'seatnum', title:'座位数' , sort:true}
	      ,{field:'isstair', title:'阶梯教室'}
	      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
	    ]]
	  	,id: 'test'
	    ,page: true
	  });
	  
	  //监听行工具事件
	  table.on('tool(test)', function(obj){
	    var data = obj.data;
	    //console.log(obj)
	    if (obj.event === 'selectDay') {
			//layer.msg('name：' + data.name + ' 的查看操作');
			layer.open({
				type : 2,
				title : '今日课程信息',
				content : '/CAMS/view/student/todayRoomCourse.jsp?rid='+data.id,
				shade : 0.5,
				area : [ '600px', '450px' ],
				maxmin : true
				});
		} 
	    else if (obj.event === 'selectWeek') {
			//layer.msg('name：' + data.name + ' 的查看操作');
			layer.open({
				type : 2,
				title : '本周课程信息',
				content : '/CAMS/view/student/weekRoomCourse.jsp?rid='+data.id,
				shade : 0.5,
				area : [ '850px', '450px' ],
				maxmin : true
				});
		} 
	  });
	});
	</script>
</body>
</html>