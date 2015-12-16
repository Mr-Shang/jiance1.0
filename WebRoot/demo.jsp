<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>±¨¾¯Demo</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function policeDemo() {
			/* var voiceEle = document.getElementById("voice");
			//voiceEle.setAttribute("src", "admin/fire_police.mp3");
			//window.setTimeout(removePoliceVoice(), 5000);
			voiceEle.src = "admin/fire_police.mp3"; */
			
		}
		
		function removePoliceVoice() {
			window.setTimeout(removePoliceVoice(), 10000);
			alert("--");
			/* var voiceEle = document.getElementById("voice");
			//alert(voiceEle);
			voiceEle.src = ""; */
		}
	</script>
  </head>
  
  <body onload="policeDemo()">
    <div style="background-color:#FF0000">
		<embed autostart="true" src="" id="voice" hidden="true" loop="true" type="audio/mpeg" />
    </div>
  </body>
</html>
