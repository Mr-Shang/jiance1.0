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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap/css/bootstrap.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.js"></script>

	<script type="text/javascript">
		var pageNo = 1;
		var totalPages;
		function showEvent(){
			$.ajax({
				method : 'get',
				async : true,
				url : "admin/eventAjax_notHandleEventList.action",
				data : {},
				dataType : 'json',
				contentType:"application/json;charset=utf-8",
				cache:false,
				success : function(jsonArray) {
					if(jsonArray.length > 0){
	    				$.each(jsonArray,function(index,value){
	    					var eventId = value['eventId'];
							var eventName = value['eventName'];
							var happenPlace = value['happenPlace'];
							var alertSource = value['alertSource'];
							var alertDescription = value['alertDescription'];
							var unitName = value['unitName'];
							var happendTime = value['happendTime'];
							var count = index + 1;
							pageNo = value['pageNo'];
							totalPages = value['totalPages'];
							$("table").append("<tr>" 
								+ "<td>" + count + "</td>"
								+ "<td><a href=\"javascript:void(0);\" onclick=wx_winpage('ShowReport.wx?PAGEID=auxiliaryInfo&UnitName="+ unitName +"',{width:600,title:'辅助信息'})>" + unitName + "</a></td>"
								+ "<td>" + eventName + "</td>"
								+ "<td>" + alertSource + "</td>"
								+ "<td>" + alertDescription + "</td>"
								+ "<td>" + happenPlace + "</td>"
								+ "<td>" + happendTime + "</td>"
								+ "<td><a  href=\"javascript:void(0)\" class=\"" + eventId + "\">处理</a></td>"
								+ "</tr>"
	        		  		);
							var myclass = "a." + eventId;
							$("myclass").on("click",function(){
								if(confirm("是否进行处理")) {
									$.ajax({
										type : "get",
					        			async : true,
					        			contentType:"application/json;charset=utf-8",
					    				cache:false,
					        			url : "admin/eventAjax_handleEvent.action?eventId=" + eventId,
					        			data : {},
					        			dataType : "json",
					        			success : function(returnParam){
					        				alert(returnParam);
					        			},
					        			error : function(errorMsg) {  
					           				 alert("数据请求失败啦!");  
					       				} 
									});
								}
							});
							
							
						});
					} else {
						$("table").append("<tr>暂无数据</tr>");
					}       			
				} ,
				error : function(errorMsg) {  
	   				 alert("数据请求失败啦!");  
				} ,
				complete:function(){
					setTimeout(function(){showEvent();},5000);
				}
						
			});
			$("li a.afterpage").on("click",function(){
				if(pageNo == 1){
					
				}else{
					pageNo = pageNo - 1;
					newPage(pageNo);		
				}
				
			});
			$("li a.beforepage").on("click",function(){
				if(pageNo == totalPages){
					
				}else{
					pageNo = pageNo + 1;
					newPage(pageNo);		
				}
				
			});
			
		}
		
		function newPage(pageNo){
			$.ajax({
				method : 'get',
				async : true,
				url : "admin/eventAjax_notHandleEventList.action?pageNo=" + pageNo,
				data : {},
				dataType : 'json',
				contentType:"application/json;charset=utf-8",
				cache:false,
				success : function(jsonArray) {
					if(jsonArray.length > 0){
	    				$.each(jsonArray,function(index,value){
	    					var eventId = value['eventId'];
							var eventName = value['eventName'];
							var happenPlace = value['happenPlace'];
							var alertSource = value['alertSource'];
							var alertDescription = value['alertDescription'];
							var unitName = value['unitName'];
							var happendTime = value['happendTime'];
							var count = index + 1;
							pageNo = value['pageNo'];
							totalPages = value['totalPages'];
							$("table").append("<tr>" 
								+ "<td>" + count + "</td>"
								+ "<td><a href=\"javascript:void(0);\" onclick=wx_winpage('ShowReport.wx?PAGEID=auxiliaryInfo&UnitName="+ unitName +"',{width:600,title:'辅助信息'})>" + unitName + "</a></td>"
								+ "<td>" + eventName + "</td>"
								+ "<td>" + alertSource + "</td>"
								+ "<td>" + alertDescription + "</td>"
								+ "<td>" + happenPlace + "</td>"
								+ "<td>" + happendTime + "</td>"
								+ "<td><a  href=\"javascript:void(0)\" class=\"" + eventId + "\">处理</a></td>"
								+ "</tr>"
	        		  		);
							var myclass = "a." + eventId;
							$("myclass").on("click",function(){
								if(confirm("是否进行处理")) {
									$.ajax({
										type : "get",
					        			async : true,
					        			contentType:"application/json;charset=utf-8",
					    				cache:false,
					        			url : "admin/eventAjax_handleEvent.action?eventId=" + eventId,
					        			data : {},
					        			dataType : "json",
					        			success : function(returnParam){
					        				alert(returnParam);
					        			},
					        			error : function(errorMsg) {  
					           				 alert("数据请求失败啦!");  
					       				} 
									});
								}
							});
						});
					} else {
						$("table").append("<tr>暂无数据</tr>");
					}       			
				} ,
				error : function(errorMsg) {  
	   				 alert("数据请求失败啦!");  
				}
						
			});
		}
	 	$(document).ready(showEvent);
	 
	 </script>
  </head>
  
  <body>
  	
  		<div class="panel panel-info">
   			<div class="panel-heading">
      			<h3 class="panel-title">消防主机监测窗口</h3>
   			</div>
   			<table class="table table-striped table-hover table-condensed">
   				<th>序号</th>    		
	  			<th>所属单位</th>
	  			<th>事件名称</th>
	  			<th>报警源</th>
	  			<th>描述</th>
	  			<th>发生地点</th>
	  			<th>发生时间</th>	
	  			<th>操作</th>	
  			</table>
  			<ul class="pager">
  				<li class="previous"><a href="javascript:void(0);" class="afterpage">&larr; Older</a></li>
  				<li class="next"><a href="javascript:void(0);" class="beforepage">Newer &rarr;</a></li>
			</ul>
		</div>
		
  </body>
    
</html>
