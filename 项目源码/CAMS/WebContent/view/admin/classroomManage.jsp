<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>教室信息管理|教室信息系统</title>
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
	<div class="x-nav">
      <span class="layui-breadcrumb">
        <a><cite>教室信息管理</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
	<table class="layui-hide" id="test" lay-filter="test"></table>
	 
	<script type="text/html" id="toolbarDemo">
  		<div class="layui-btn-container">
			<button class="layui-btn layui-btn-sm" lay-event="addData">添加教室</button>
  		</div>
	</script>

	 
	<script type="text/html" id="barDemo">
  		<a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="update">编辑</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
	</script>
	              
	          
	<script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
	 
	<script>
	layui.use('table', function(){
	  var table = layui.table;
	  
	  table.render({
	    elem: '#test'
	    ,url:'/CAMS/ClassroomServlet?method=showAll'
	    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
	    ,title: '教师信息表'
	    ,cols: [[
	      {type: 'checkbox', fixed: 'left'}
	      ,{field:'id', title:'教室编号',  fixed: 'left', unresize: true, sort: true}
	      ,{field:'building', title:'所在教学楼' , edit:'text'}
	      ,{field:'floor', title:'楼层' , sort:true}
	      ,{field:'seatnum', title:'座位数' , sort:true,edit:'text'}
	      ,{field:'isstair', title:'阶梯教室',edit:'text'}
	      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
	    ]]
	    ,page: true
	  });
	  
	  //头工具栏事件
	 table.on('toolbar(test)', function(obj){
	    switch(obj.event){
	      //自定义头工具栏右侧图标 - 提示
	      //添加数据
	      case 'addData':
	    	  layer.open({
	                type: 2,
	                title: '添加教室',
	                content: '/CAMS/view/admin/classroomAdd.jsp',
	                area: ['600px', '400px'],
	                shadeClose: 0.5,
	                maxmin: true,               
	            });
	      break;
	    };
	  }); 
	  //监听行工具事件
	  table.on('tool(test)', function(obj){
	    var data = obj.data;
	    //console.log(obj)
		if (obj.event === 'delete') {
			layer.confirm('确定删除该行数据?', function(index) {
			obj.del();
			$.ajax({
				type : "post",
				url : "/CAMS/ClassroomServlet?method=roomDelete&rid=" + data.id,
				dataType : "json",
				success : function(data) {
 					layer.alert("删除成功")
					window.location.replace(window.location.href)  //刷新页面表格数据
 				}
			});
			layer.close(index);
			});
 
		} 
		else if (obj.event === 'update') {
			//layer.alert('编辑行：<br>'+ JSON.stringify(data))										
			$.ajax({
				type : "post",
				data : data,
				url : "/CAMS/ClassroomServlet?method=roomEdit",
				dataType : "json",
				success : function(data) {
 					layer.alert("更新成功")
					window.location.replace(window.location.href)  //刷新页面表格数据
 				}
			});
		}  //else if结束 */
	  });
	});
	</script>
</body>
</html>