<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String loginId=(String)session.getAttribute("loginId");		//session取学生端用户名即sid
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>我教的课程|教务管理系统</title>
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
	<input type="hidden" id="loginId" value="<%=loginId%>">
	<div class="x-nav">
      <span class="layui-breadcrumb">
        <a><cite>我的授课</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
	<table class="layui-hide" id="test" lay-filter="test"></table>   
	
	<script type="text/html" id="barDemo">
  		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="select">查看排课情况</a>
	</script>           
	          
	<script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
	 
	<script>
	layui.use(['table','jquery'], function(){
	  //jquery取值
	  var loginId = $("#loginId").val();
	  
	  var table = layui.table;  
	  var $$=layui.jquery;
	  table.render({
	    elem: '#test'
	    ,url:'/CAMS/TeacherTCServlet?method=showCourse&tid='+loginId
	    //toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
	    /* ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
	      ,title: '提示'
	      ,layEvent: 'LAYTABLE_TIPS'
	      ,icon: 'layui-icon-tips'
	    }] */
	    ,title: '用户数据表'
	    ,cols: [[
	      {field:'cid', title:'课程号',  fixed: 'left', unresize: true, sort: true}
	      ,{field:'cname', title:'课程名'}
	      ,{field:'type', title:'课程类型'}
	      ,{field:'weeksno', title:'起始周'}
	      ,{field:'weekeno', title:'结束周'}
	      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
	    ]]
	    ,page: true
	  });
	  
	  //监听行工具事件
	  table.on('tool(test)', function(obj){
		var data=obj.data
	    var urlval='/CAMS/view/teacher/courseRecord.jsp?cid='+data.cid+'&weeksno='+data.weeksno+'&weekeno='+data.weekeno
 			$$.post(urlval,{},
 				function(data,status){
 					window.location.href = urlval;
 			});
	  });	//table.on结束
	});	//layui.use结束
	</script>
</body>
</html>