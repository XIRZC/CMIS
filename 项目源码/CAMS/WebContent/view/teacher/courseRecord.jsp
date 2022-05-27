<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String loginId=(String)session.getAttribute("loginId");	
	String cid=request.getParameter("cid");
	System.out.println("cid:start:"+cid);
	//System.out.println("cid:start:"+cid);
	String weeksno=request.getParameter("weeksno");
	String weekeno=request.getParameter("weekeno");
	if(weeksno!=null){
		session.setAttribute("weeksno",weeksno);
		session.setAttribute("weekeno",weekeno);
	}
	else{
		weeksno=(String)session.getAttribute("weeksno");
		weekeno=(String)session.getAttribute("weekeno");
	}
	//System.out.println("cid:"+cid+",weeksno:"+weeksno+",weekeno:"+weekeno);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>课程排课记录|教室信息系统</title>
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
<%-- 	<input type="hidden" id="loginId" value="<%=loginId%>">
	<input type="hidden" id="cid" value="<%=cid%>"> --%>
	
    <input type="hidden" id="weeksno" name="weeksno" value="<%=weeksno%>">
	<input type="hidden" id="weekeno" name="weekeno" value="<%=weekeno%>"> 
	
	<div class="x-nav">
		<span class="layui-breadcrumb">
        	<a href="teachCourse.jsp">我的授课</a>
        	<a><cite>当前排课记录</cite></a>
      	</span>
      	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	
	<div class="layui-row">
     <form class="layui-form layui-col-md12 x-so" action="/CAMS/ClassroomServlet?method=arrangeCourse" method="post">
          <label class="layui-form-label">添加排课</label>  
          <input type="hidden" name="tid" id="tid" value="<%=loginId%>">
   		  <input type="hidden" name="cid" id="cid" value="<%=cid%>">  
          <div class="layui-input-inline">
            <select name="rid" id="rid">
              <option>请选择教室</option>
              <option>南1101</option>
              <option>南1102</option>
              <option>南2101</option>
              <option>南2102</option>
              <option>南2201</option>
              <option>南2202</option>
              <option>北1101</option>
              <option>北1102</option>
              <option>北1201</option>
              <option>北1202</option>
              <option>北2101</option>
              <option>北2102</option>
            </select>
          </div>
          <div class="layui-input-inline">
            <select name="type" id="type">
              <option>单周</option>
              <option>双周</option>
              <option>连续周</option>
            </select>
          </div>
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
              <option>一二节</option>
              <option>三四节</option>
              <option>五六节</option>
              <option>七八节</option>
              <option>九十节</option>
            </select>
          </div>
          <button type="submit" class="layui-btn" onclick="window.location.replace(window.location.href)">排课</button>
      </form>
      </div>
      
       <script type="text/html" id="barDemo">
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
	  </script>
	
	<div>已有排课记录</div>
	<table class="layui-hide" id="record" lay-filter="record"></table> 
	
	<div class="layui-row">
     <form class="layui-form layui-col-md12 x-so" id="emptyweek">
          <div class="layui-input-inline">
            <select name="type" id="type">
              <option>单周</option>
              <option>双周</option>
              <option>连续周</option>
            </select>
          </div>
          <button class="layui-btn" lay-filter="formDemo" lay-submit>查询</button>
      </form>
      </div>
     
	
	<div>空教室周表</div>  
	<table class="layui-hide" id="emptyroom" lay-filter="emptyroom"></table>
	
	           
	          
	<script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
	 
	<script>
	layui.use(['table','form'], function(){
	  //jquery取值
	  var tid = $("#tid").val();
	  var cid = $("#cid").val();
	  var weeksno = $("#weeksno").val();
	  var weekeno = $("#weekeno").val();
	  //alert(tid+cid+weeksno+weekeno)
	  
	  var table = layui.table;
	  var form=layui.form;
	  table.render({
	    elem: '#record'
	    ,url:'/CAMS/ClassroomServlet?method=showCourseRecord&tid='+tid+'&cid='+cid
	    //toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
	    /* ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
	      ,title: '提示'
	      ,layEvent: 'LAYTABLE_TIPS'
	      ,icon: 'layui-icon-tips'
	    }] */
	    ,title: '用户数据表'
	    ,cols: [[
	      {field:'cname', title:'课程名',  fixed: 'left', unresize: true, sort: true}
	      ,{field:'rid', title:'教室编号'}
	      ,{field:'weeksno', title:'起始周', edit:'text'}
	      ,{field:'weekeno', title:'结束周', edit:'text'}
	      ,{field:'type', title:'单/双/连续', edit:'text'}
	      ,{field:'weekdayno', title:'星期', edit:'text'}
	      ,{field:'secsno', title:'起始节次', edit:'text'}
	      ,{field:'seceno', title:'结束节次', edit:'text'}
	      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
	    ]]
	    ,page: true
	  });
	  
	//监听行工具事件
	  table.on('tool(record)', function(obj){
	    var data = obj.data;
		if (obj.event === 'delete') {
			layer.confirm('确定删除该行数据?', function(index) {
			obj.del();
			$.ajax({
				type : "post",
				url : "/CAMS/ClassroomServlet?method=arrangeDelete&cid="+cid+"&tid="+tid,
				data: data,
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
				url : "/CAMS/ClassroomServlet?method=arrangeEdit&cid="+cid+"&tid="+tid,
				dataType : "json",
				success : function(data) {
 					layer.alert("更新成功")
					window.location.replace(window.location.href)  //刷新页面表格数据
 				}
			});
		}  //else if结束 
	  });
	  
	  emptyroom=table.render({
		    elem: '#emptyroom'
		    ,url:'/CAMS/ClassroomServlet?method=showEmptyRoomWeek&weeksno='+weeksno+'&weekeno='+weekeno
		    ,where:{'type':"1"}   //默认先输出单周的空教室周表
		    ,title: '本周课程表'
		    ,cols: [[
		      {field:'secname', title:'节次/周次',  fixed: 'left', unresize: true}
		      ,{field:'one', title:'星期一' }
		      ,{field:'two', title:'星期二' }
		      ,{field:'three', title:'星期三' }
		      ,{field:'four', title:'星期四' }
		      ,{field:'five', title:'星期五' }
		    ]]
		  });
	  // 执行搜索，表格重载
	  var table_this;
	  form.on('submit(formDemo)', function(data) {
		  if (table_this != null) {
				this.where = {};  //置空where
		  }
		  emptyroom.reload( {
              url: '/CAMS/ClassroomServlet?method=showEmptyRoomWeek&weeksno='+weeksno+'&weekeno='+weekeno
              ,page: {
                  curr: 1
              }
              ,where: data.field
              ,done: function() {
  				table_this = this;
  			  }
          });
          return false;
		});
	});	//layui.use结束
	</script>
</body>
</html>