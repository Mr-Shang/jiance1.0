<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/base/base.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath%>js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		/* function loadSmallWindow2(obj){
			//var obj = document.getElementById("aHandle");
			var div = document.getElementById("div");
			div.style.display = "";
			div.style.top = obj.offsetTop + obj.clientHeight + 2;
			div.style.left= obj.offsetLeft;
			document.all("btnOk").onclick = function(){
			onSelectSex(obj,div);
			};
		}

		function onSelectSex(obj,divObj){
			var sel = document.all("selSex");
			obj.value = sel.value;
			alert("您所选择的数据是：" + sel.value);
			divObj.style.display = "none";
		}
		 */
		function test() {
			 var name=prompt("请输入您的名字","");
			 //alert(name);
			//window.showModalDialog('html1.html','','dialogWidth=400px;dialogHeight=300px;status=no;help=no;scollbar=no');
			//window.open ('html1.html','newwindow','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
		}
	</script>
  </head>
  
  <body>
   <table class="maintable">
  		<tr>
  			<th>事件名称</th>
  			<th>发生地点</th>
  			<th>发生时间</th>
  			
  			<th>操作</th>
  		</tr>
  		<tr>
  			<td>名称1</td>
  			<td>地点1</td>
  			<td>时间1</td>
  			<td style="display: none;">单位1</td>
  			<td><input type="button" id="aHandle" onclick="loadSmallWindow2(this)" value="处理"/><div id="div" style="width:150px;height=90px;display:none;background-color:#ffffee;boder-collapse:collapse;border-color:#000000;border-style:solid;border-width:1px;position:absolute;z-index:100"><select name = "selSex"><option id="man" value = "男">男</option><option id="woman" value = "女">女</option></select><input type="button"  name = "btnOk" value = "确定"></div></td>
  		</tr>
  		<!-- <tr>
  			<td>名称2</td>
  			<td>地点2</td>
  			<td>时间2</td>
  			<td style="display: none;">单位2</td>
  			<td><input type="button" id="aHandle" onclick="loadSmallWindow2(this)" value="处理"/><div id="div" style="width:150px;height=90px;display:none;background-color:#ffffee;boder-collapse:collapse;border-color:#000000;border-style:solid;border-width:1px;position:absolute;z-index:100"><select name = "selSex"><option id="man" value = "男">男</option><option id="woman" value = "女">女</option></select><input type="button"  name = "btnOk" value = "确定"></div></td>
  		</tr>
  		<tr>
  			<td>名称3</td>
  			<td>地点3</td>
  			<td>时间3</td>
  			<td style="display: none;">单位3</td>
  			<td><input type="button" id="aHandle" onclick="loadSmallWindow2(this)" value="处理"/><div id="div" style="width:150px;height=90px;display:none;background-color:#ffffee;boder-collapse:collapse;border-color:#000000;border-style:solid;border-width:1px;position:absolute;z-index:100"><select name = "selSex"><option id="man" value = "男">男</option><option id="woman" value = "女">女</option></select><input type="button"  name = "btnOk" value = "确定"></div></td>
  		</tr> -->
  	</table>
  </body>
</html>
