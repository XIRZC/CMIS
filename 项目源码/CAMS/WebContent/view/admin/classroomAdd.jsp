<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加课程</title>
<style>
.layui-form{
 margin: 5% 0 0 40%;
}
</style>
<link rel="stylesheet" href="<%=path %>/static/lib/layui/css/layui.css">
</head>
<body>
<form class="layui-form layui-form-pane"> 
  <div class="layui-form-item pane">
    <label class="layui-form-label">教室号</label>
    <div class="layui-input-inline">
      <input type="text" name="rid" id="rid"  autocomplete="on" class="layui-input ">
    </div>
  </div>
  <div class="layui-form-item pane">
    <label class="layui-form-label">教室座位数</label>
    <div class="layui-input-inline">
      <input type="text" name="seatnum" id="seatnum" autocomplete="on" class="layui-input ">
    </div>
  </div>
  <div class="layui-form-item pane">
    <label class="layui-form-label">是否为阶梯教室</label>
    <div class="layui-input-inline">
      <input type="text" name="isstair" id="isstair" autocomplete="on" class="layui-input ">
    </div>
  </div>
  <div class="layui-form-item">
      <button class="layui-btn"  lay-submit lay-filter="formDemo">立即提交</button>
  </div>
</form>
<script src="<%=path %>/static/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
      layui.use(['form','layer','jquery'], function() {
    	  var form = layui.form;
    	  var $$ = layui.jquery;
    	  form.on('submit(formDemo)', function(data) {
    		   $$.ajax({
    		  	type:"POST",
    		  	dataType:"json",
    		  	url:"/CAMS/ClassroomServlet?method=roomAdd",
    		  	data:data.field,
    		  })
    		  var index = parent.layer.getFrameIndex(window.name);
    		  parent.layer.close(index);//关闭弹出的子页面窗口
    		  window.parent.location.reload();//写在这里的话是只有提交成功了才刷新那个页面 
    		   return false;
    		});
});//end layui.use

    </script>
	
</body>
</html>