<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String loginName=(String)session.getAttribute("loginName");
%>
<!DOCTYPE html>
<html>
<head>
	<title>教室信息系统 </title>
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
		<!-- 顶部开始 -->
		<div class="container">
			<div class="logo">
				<a href="index.jsp">教室信息系统</a>
			</div>
			<div class="left_open">
				<i title="展开左侧栏" class="iconfont">&#xe699;</i>
			</div>
			<ul class="layui-nav right" lay-filter="">
				<li class="layui-nav-item">
					<a href="javascript:;">
						管理员admin
					</a>
					<dl class="layui-nav-child">
						<!-- 二级菜单 -->
						<dd>
							<a onclick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a>
						</dd>
						<dd>
							<a href="<%=path %>/index.jsp">退出登录</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item to-index">
					<a href="index.jsp">首页</a>
				</li>
			</ul>

		</div>
		<!-- 顶部结束 -->
		<!-- 中部开始 -->
		<!-- 左侧菜单开始 -->
		<div class="left-nav">
			<div id="side-nav">
				<ul id="nav">
					<li>
						<a href="javascript:;">
							<i class="iconfont">&#xe6b8;</i>
							<cite>功能</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="classroomManage.jsp">
									<i class="iconfont">&#xe6a7;</i>
									<cite>教室信息管理</cite>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<!-- <div class="x-slide_left"></div> -->
		<!-- 左侧菜单结束 -->
		<!-- 右侧主体开始 -->
		<div class="page-content">
			<div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
				<ul class="layui-tab-title">
					<li class="home"><i class="layui-icon">&#xe68e;</i>首页</li>
				</ul>
				<div class="layui-tab-content">

					<div class="layui-tab-item layui-show">
						<iframe src='welcome.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div class="page-content-bg"></div>
		<!-- 右侧主体结束 -->
		<!-- 中部结束 -->
	</body>
</html>