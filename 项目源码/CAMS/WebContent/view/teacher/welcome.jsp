<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html>
<html class="x-admin-sm">
    <head>
        <meta charset="UTF-8">
        <title>教师欢迎页面</title>
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
    <div class="x-body">
        <fieldset class="layui-elem-field layui-field-title">
		  <legend>欢迎使用教室信息系统-教师端</legend>
		  <div class="layui-field-box">
		    欢迎教师teacher的使用
		  </div>
		</fieldset>
	</div>
    </body>
</html>