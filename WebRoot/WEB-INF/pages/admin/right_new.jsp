<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'right.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="jAlert-master/src/jAlert-v3.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
	<script src="<%=basePath%>jAlert-master/src/jAlert-v3.min.js"></script>
	<script src="<%=basePath%>jAlert-master/src/jAlert-functions.min.js"></script>  
	<script type="text/javascript">
		var pageNoWater = 1;
		var totalPagesWater = 0;
		var pageNoEvent = 1;
		var totalPagesEvent = 0;
		$(document).ready(function(){
			showEvent(); 
			load(pageNoWater);
			$("li a.afterpagewater").on("click",function(){
				if(pageNoWater == totalPagesWater){
					myAlert("已经是最后一页了！！！")	;	
					return	
				}else{
					pageNoWater = pageNoWater + 1;
					load(pageNoWater);		
				}			
								
			});
			$("li a.beforepagewater").on("click",function(){
				if(pageNoWater == 1 ){
						myAlert("已经是第一页了！！！")	;		
				}else{
					pageNoWater = pageNoWater - 1;
					load(pageNoWater);		
				}
								
			});	
			$("li a.beforepage").on("click",function(){
				if(pageNoEvent == 1){
					myAlert("已经是第一页了！！！")	;	
				}else{
					pageNoEvent = pageNoEvent - 1;
					newPage(pageNoEvent);		
				}
				
			});
			$("li a.afterpage").on("click",function(){
				if(pageNoEvent == totalPagesEvent){
					myAlert("已经是最后一页了！！！")	;	
				}else{
					pageNoEvent = pageNoEvent + 1;
					newPage(pageNoEvent);		
				}
				
			});		
		});
		
		function load(pageNo){
			$.ajax({
				method : 'get',
				async : true,
				url : "admin/machine_getRTUMchine.action?pageNo=" + pageNo,
				data : {},
				dataType : 'json',
				contentType:"application/json;charset=utf-8",
				cache:false,
				success : function(jsonArray) {
					if(jsonArray.length > 0){
						$("tbody#water").empty();
	    				$.each(jsonArray,function(index,value){
	    					var unitName = value['unitName'];
							var machineCode = value['machineCode'];
							var machineName = value['machineName'];
							var state = value['state'];
							var count = index + 1;
							pageNoWater = value['pageNo'];
							totalPagesWater = value['totalPages'];
							$("tbody#water").append("<tr>" 
								+ "<td class=\"text-center\"><small >" + count + "</small></td>"
								+ "<td class=\"text-center\"><small >" + unitName + "</small></td>"
								+ "<td class=\"text-center\"><small >" + machineCode + "</small></small></td>"
								+ "<td class=\"text-center\"><small >" + machineName + "</small></td>"
								+ "<td class=\"text-center\"><small >" + state + "</small></td>"
								+ "<td class=\"text-center\"><small ><a href= \"javascript:void(0);\" class=\"link" + count + "\" >接口信息</a></small ></td>"
								+ "</tr>"
	        		  		);
	        		  		
	        		  		var aclass = "a.link" + count;
	        		  		 $(aclass).alertOnClick({
		              			 'iframe': "ShowReport.wx?PAGEID=interfaceInfoMainPage&MachineCode=" + machineCode,
		              			 'iframeHeight':600,
					 			 'theme': 'default',
								 'size': 'lg',
	               			 });
	        		  		
						});
					} else {
						$("tbody#water").append("<tr>暂无数据</tr>");
					}       			
				} ,
				error : function(errorMsg) {  
	   				 alert("数据请求失败啦!");  
				}		
			});
		}	
		function showEvent(){
			$.ajax({
				method : 'get',
				async : true,
				url : "admin/eventAjax_notHandleEventList.action?" ,
				data : {},
				dataType : 'json',
				contentType:"application/json;charset=utf-8",
				cache:false,
				success : function(jsonArray) {
					if(jsonArray.length > 0){
						$("tbody#Event").empty();
	    				$.each(jsonArray,function(index,value){
	    					var eventId = value['eventId'];
							var eventName = value['eventName'];
							var happenPlace = value['happenPlace'];
							var alertSource = value['alertSource'];
							var alertDescription = value['alertDescription'];
							var unitName = value['unitName'];
							var happenTime = value['happenTime'];
							var count = index + 1;
							pageNoEvent = value['pageNo'];
							totalPagesEvent = value['totalPages'];
							$("tbody#event").append("<tr>" 
								+ "<td class=\"text-center\"><a href=\"javascript:void(0);\"><small >" + unitName + "</a></small></td>"
								+ "<td class=\"text-center\"><small >" + eventName + "</small></td>"
								+ "<td class=\"text-center\"><small >" + alertSource + "</small></td>"
								+ "<td class=\"text-center\"><small >" + alertDescription + "</small></td>"
								+ "<td class=\"text-center\"><small >" + happenPlace + "</small></td>"
								+ "<td class=\"text-center\"><small >" + happenTime + "</small></td>"
								+ "<td class=\"text-center\"><a  href=\"javascript:void(0)\" class=\"" + eventId + "\"><small >处理</small></a></td>"
								+ "</tr>"
	        		  		);
							var myclass = "a." + eventId;
							$(myclass).on("click",function(){
								myConfirm(function(){
  									$.ajax({
										type : "get",
					        			async : true,
					        			contentType:"application/json;charset=utf-8",
					    				cache:false,
					        			url : "admin/eventAjax_handleEvent.action?eventId=" + eventId,
					        			data : {},
					        			dataType : "json",
					        			success : function(returnParam){
					        				myAlert(returnParam);
					        			},
					        			error : function(errorMsg) {  
					           				 alert("数据请求失败啦!");  
					       				} 
									});
								}
								) ;        
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
						$("tbody#Event").empty();
	    				$.each(jsonArray,function(index,value){
	    					var eventId = value['eventId'];
							var eventName = value['eventName'];
							var happenPlace = value['happenPlace'];
							var alertSource = value['alertSource'];
							var alertDescription = value['alertDescription'];
							var unitName = value['unitName'];
							var happenTime = value['happenTime'];
							var count = index + 1;
							pageNoEvent = value['pageNo'];
							totalPagesEvent = value['totalPages'];
							$("tbody#event").append("<tr>" 
								+ "<td class=\"text-center\"><a href=\"javascript:void(0);\"><small >" + unitName + "</a></small></td>"
								+ "<td class=\"text-center\"><small >" + eventName + "</small></td>"
								+ "<td class=\"text-center\"><small >" + alertSource + "</small></td>"
								+ "<td class=\"text-center\"><small >" + alertDescription + "</small></td>"
								+ "<td class=\"text-center\"><small >" + happenPlace + "</small></td>"
								+ "<td class=\"text-center\"><small >" + happenTime + "</small></td>"
								+ "<td class=\"text-center\"><a  href=\"javascript:void(0)\" class=\"<small >" + eventId + "\">处理</small></a></td>"
								+ "</tr>"
	        		  		);
							var myclass = "a." + eventId;
							$(myclass).on("click",function(){
								myConfirm(function(){
  									$.ajax({
										type : "get",
					        			async : true,
					        			contentType:"application/json;charset=utf-8",
					    				cache:false,
					        			url : "admin/eventAjax_handleEvent.action?eventId=" + eventId,
					        			data : {},
					        			dataType : "json",
					        			success : function(returnParam){
					        				myAlert(returnParam);
					        			},
					        			error : function(errorMsg) {  
					           				 alert("数据请求失败啦!");  
					       				} 
									});
								}) ;        
							});
						});
					} else {
						$("tbody#event").append("<tr>暂无数据</tr>");
					}       			
				} ,
				error : function(errorMsg) {  
	   				 myAlert("数据请求失败啦!");  
				}
						
			});
		}
		function myAlert(msg) {
				$.jAlert({
					 'title': '提示信息',
					 'content': msg,
					 'theme': 'default',
					 'size': 'xsm',
					 'btns': [
					          {'text':'确定', 'closeAlert':true }
					       ]
				});
		}
		
		function myConfirm(clickOk,clickNot){
			$.jAlert({	
				 'title': '提示信息',
				 'type':'confirm',
				 'theme': 'default',
				 'size': 'xsm',
				'confirmQuestion':'确定处理？',
				'confirmBtnText': '确定',
				'denyBtnText': '取消',
				'confirmAutofocus': '.confirmBtn', //confirmBtn or denyBtn
				'onConfirm':clickOk,
				'onDeny':clickNot
			});
		}
		

	</script>
	<style type="text/css">
		.myinfo{
			margin-left: 5px;
			margin-right: 5px;
			height:95%;

		}
		#list{
			height:70%;

		}
		#info{
			height:70%;
		}
	 	.mypage{
	 	margin-left: 5px;
	 	margin-right: 5px;
	 	margin-top: 15px;
	 
	 }
	</style>
	
</head>

<body>
	<div style="height: 40%;width: 100%">
		<iframe height="100%" width="100%" style="border: 0" src="ShowReport.wx?PAGEID=mainMonitoring"></iframe>
	</div>
	<div style="height: 60%;width: 100%">
		<div style="height:100%;width:50%;float: left;">
		<div class="panel panel-danger myinfo" >
   			<div class="panel-heading">
      			<h3 class="panel-title">消防主机监测窗口</h3>
   			</div>
   			<div id="info">
   			<table class="table table-striped table-hover table-condensed">
   			<thead>
   			<tr>
	  			<th class="text-center"><small >所属单位</small></th>
	  			<th class="text-center"><small >事件名称</small></th>
	  			<th class="text-center"><small >报警源</small></th>
	  			<th class="text-center"><small >描述</small></th>
	  			<th class="text-center"><small >发生地点</small></th>
	  			<th class="text-center"><small >发生时间</small></th>	
	  			<th class="text-center"><small >操作</small></th>	
	  		</tr>
	  		</thead>
	  		<tbody id ="event"></tbody>
  			</table>
  			</div>

  			<ul class="pager mypage" >
  				<li class="previous"><a href="javascript:void(0);" class="beforepage">&larr; 上一页</a></li>
  				<li class="next"><a href="javascript:void(0);" class="afterpage">下一页 &rarr;</a></li>
			</ul>
		</div>
		</div>
		<div style="height:100%;width:50%;float: left;">
		<div class="panel panel-info myinfo" >
   			<div class="panel-heading">
      			<h3 class="panel-title">水系统监测窗口</h3>
   			</div>
   			<div  id="list" >
   			<table class="table table-striped table-hover table-condensed">
   			<thead>
   			<tr>
   				<th class="text-center"><small >序号</small></th>    		
	  			<th class="text-center"><small >所属单位</small></th>
	  			<th class="text-center"><small>RTU编号</small></th>
	  			<th class="text-center" ><small>设备名称</small></th>
	  			<th class="text-center"><small>设备状态</small></th>
	  			<th class="text-center"><small>操作</small></th>
	  		</tr>
	  		</thead>	
	  		<tbody id="water">
	  		
	  		</tbody>
  			</table>
			</div>
			<ul class="pager mypage" >
  				<li class="previous"><a href="javascript:void(0);" class="beforepagewater">&larr; 上一页</a></li>
  				<li class="next"><a href="javascript:void(0);" class="afterpagewater">下一页 &rarr;</a></li>
		</ul>
		

		</div>
		 
		</div>
	</div>
	
</body>
</html>
