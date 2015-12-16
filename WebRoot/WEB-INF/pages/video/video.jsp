<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>视频播放</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./js/jquery-1.7.2.js"></script> 
		<script type="text/javascript">
		$(document).ready(function(){
			var url =  '<s:property value="url"/>';
			console.log(url); 
			$.get("video/VideoServlet?command=Play&code=" + url);
 	   		/*  var vhtml = '<object id="video"';
 	    	vhtml+="<param name='mrl' value='UDP://@:5494' />";
 	    	 document.getElementById(id).innerHTML = vhtml;   */
			
});

    </script>
  </head>
  
  <body>
 <object class="vlc" type='application/x-vlc-plugin'  events='True' width="720" height="540"  id="video">
        <param name='mrl' value='UDP://@:5494' />
        <param name='volume' value='50' />
        <param name='autoplay' value='true' />
        <param name='loop' value='false' />
        <param name='fullscreen' value='false' />
   </object>
  </body>
</html>
