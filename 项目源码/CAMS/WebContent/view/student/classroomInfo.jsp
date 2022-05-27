<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
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
	<div class="x-nav">
      <span class="layui-breadcrumb">
        <a><cite>教室信息查询</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="layui-row">
     <form class="layui-form layui-col-md12 x-so" action="emptySelect.jsp">
          <label class="layui-form-label">空教室查询</label>        
          <div class="layui-input-inline">
            <select name="building" id="building">
              <option>请选择教学楼</option>
              <option>南1</option>
              <option>南2</option>
              <option>北1</option>
              <option>北2</option>
            </select>
          </div>
          <input class="layui-input"  autocomplete="off" placeholder="周次" name="weekno" id="weekno">
           <div class="layui-input-inline">
            <select name="weekdayno" id="weekdayno">
              <option>请选择星期</option>
              <option>星期一</option>
              <option>星期二</option>
              <option>星期三</option>
              <option>星期四</option>
              <option>星期五</option>
            </select>
          </div>
          <div class="layui-input-inline">
            <select name="secno" id="secno">
              <option>请选择节次</option>
              <option>第一节</option>
              <option>第二节</option>
              <option>第三节</option>
              <option>第四节</option>
              <option>第五节</option>
              <option>第六节</option>
              <option>第七节</option>
              <option>第八节</option>
              <option>第九节</option>
              <option>第十节</option>
            </select>
          </div>
          <button type="submit" class="layui-btn"><i class="layui-icon">&#xe615;</i></button>
      </form>
      </div>
	<table class="layui-hide" id="test" lay-filter="test"></table>
	 
	<!-- <script type="text/html" id="toolbarDemo">
  		<div class="layui-btn-container">
			<button class="layui-btn layui-btn-sm" lay-event="addData">添加教室</button>
  		</div>
	</script>  -->

	 
	<script type="text/html" id="barDemo">
  		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="selectDay">今日课程</a>
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="selectWeek">本周课程</a>
	</script>
	              
	          
	<script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
	 
	<script>
	layui.use('table', function(){
	  var table = layui.table;
	  
	  table.render({
	    elem: '#test'
	    ,url:'/CAMS/ClassroomServlet?method=showAll'
	    //,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
	    ,title: '教师信息表'
	    ,cols: [[
	      {type: 'checkbox', fixed: 'left'}
	      ,{field:'id', title:'教室编号',  fixed: 'left', unresize: true, sort: true}
	      ,{field:'building', title:'所在教学楼' , sort:true}
	      ,{field:'floor', title:'楼层' , sort:true}
	      ,{field:'seatnum', title:'座位数' , sort:true}
	      ,{field:'isstair', title:'阶梯教室'}
	      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
	    ]]
	    ,page: true
	  });
	  
	  //头工具栏事件
	 /*  table.on('toolbar(test)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    switch(obj.event){
	      case 'getCheckData':
	        var data = checkStatus.data;
	        layer.alert(JSON.stringify(data));
	      break;
	      
	      //自定义头工具栏右侧图标 - 提示
	      //添加数据
	      case 'addData':
	    	  var index=layer.open({
	                type: 2,
	                title: '添加课程',
	                content: '/layui-demo/view/admin/course/courseAdd.jsp',
	                area: ['800px', '600px'],
	                shadeClose: 0.5,
	                maxmin: true,               
	            });
	    	  layer.full(index)
	      break;
	    };
	  }); */
	  //'/CAMS/view/admin/emptySelect.jsp?building='+building+'&weekno='+weekno+'&weekdayno='+weekdayno+'&secno='+secno,
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
		/* else if (obj.event === 'delete') {
			layer.confirm('确定删除该行数据?', function(index) {
			obj.del();
			$.ajax({
				type : "post",
				url : "/layui-demo/AdminCourseServlet?method=delete&id=" + data.id,
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
				url : "/layui-demo/AdminCourseServlet?method=update",
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