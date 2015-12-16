<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>监测主界面未处理事件列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript" src="<%=basePath%>js/comet4j.js"></script>
	<script src="<%=basePath%>js/jquery-1.7.2.js"></script>
	<script src="<%=path%>/js/jquery.tablesorter.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>webresources/script/wabacus_api.js"></script>
	<script type="text/javascript">
		/* comt4j推送程序---不用 
		function init() {
			var en = document.getElementById('police');
			JS.Engine.on( {
				policePage : function(policePage) {//侦听一个channel
					en.innerHTML = policePage;
				}
			});
			 JS.Engine.start('connPolicePage');
	        JS.Engine.on('start',function(cId,channelList,engine){});
		} */
		
	   /**
		* 加载显示未经处理事件信息
		*/
		function showEvent() {
		//alert("执行此方法间隔1s");
			var $tbody = $("#eventData");
			var url = "admin/eventAjax_notHandleEventList.action";
			//param = {};
			$.post(url,null ,function(jsonArray) {
				$tbody.html("");
				if(jsonArray.length > 0) {
					for(var i = 0; i < jsonArray.length; i++){  
					$tbody.append("<tr><td>"+jsonArray[i].eventName+"</td><td>"+jsonArray[i].happenPlace+"</td><td>"+jsonArray[i].alertSource+"</td><td>"+jsonArray[i].happenTime+"</td><td><a  href='javascript:void(0);' onclick=wx_winpage('ShowReport.wx?PAGEID=auxiliaryInfo&UnitName="+jsonArray[i].unitName+"',{width:600,title:'辅助信息'})>" + jsonArray[i].unitName + "</a></td><td><a href='javascript:void(0);' onclick='handleEvent(\""+jsonArray[i].eventId+"\",\""+jsonArray[i].unitId+"\",\""+jsonArray[i].unitAddr+"\",\""+jsonArray[i].contactTel+"\",\""+jsonArray[i].machineCode+"\",\""+jsonArray[i].alertMachine+"\",\""+jsonArray[i].alertSource+"\",\""+jsonArray[i].alertDescription+"\",\""+jsonArray[i].happenPlace+"\",\""+jsonArray[i].eventTypeId+"\",\""+jsonArray[i].eventName+"\",\""+jsonArray[i].happenTime+"\",\""+jsonArray[i].eventDescription+"\",\""+jsonArray[i].handleUserId+"\",\""+jsonArray[i].handleDescription+"\",\""+jsonArray[i].handleTime+"\",\""+jsonArray[i].isHandle+"\",\""+jsonArray[i].isFalseReport+"\")'>处理</a></td></tr>");
			   		}
				}else {
					$tbody.html("暂无数据");
				}
			});
		}
		//<td>"+jsonArray[i].alertSource+"</td>
		//处理事件放大
		function handleEvent(eventId, unitId, unitName, unitAddr, contactTel, machineCode, alertMachine, alertSource, alertDescription, happenPlace, eventTypeId, eventName, happendTime, eventDescription, handleUserId, handleDescription, handleTime, isHandle, isFalseReport) {
			if(confirm("是否进行处理")) {
				var url = "admin/eventAjax_handleEvent.action";
			params = {
				"eventId" : eventId,
				"unitId" : unitId,
				"unitName" : unitName,
				"unitAddr" : unitAddr,
				"contactTel" : contactTel,
				"machineCode" : machineCode,
				"alertMachine" : alertMachine,
				"alertSource" : alertSource,
				"alertDescription" : alertDescription,
				"happenPlace" : happenPlace,
				"eventTypeId" : eventTypeId,
				"eventName" : eventName,
				"happendTime" : happendTime,
				"eventDescription" : eventDescription,
				"handleUserId" : handleUserId,
				"handleDescription" : handleDescription,
				"handleTime" : handleTime,
				"isHandle" : isHandle,
				"isFalseReport" : isFalseReport
			};
			$.post(url, params, function(m) {
				alert(m);
			});
			}
		 }
		
		//onload="setInterval('showEvent()', 10000)"
	 </script>
  </head>
  
  <body onload="setInterval('showEvent()', 5000)">

 
 <!-- 	<h1>消防主机远程监测窗口</h1> -->
  	<!-- 
  	<input type="button" onclick="showEvent()" value="测试"/> 
  	-->
  	<table class="maintable">
  		<tr>
  			<th>事件名称</th>
  			<th>发生地点</th>
  			<th>报警源</th>
  			<th>发生时间</th>
  			<th>所属单位</th>
  			<th>操作</th>
  		</tr>
  		<tbody id="eventData"></tbody>
  	</table>

  </body>
    
</html>
