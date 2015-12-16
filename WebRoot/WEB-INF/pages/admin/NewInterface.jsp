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
    <title>水系统检测界面</title>
    
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
	<script type="text/javascript" src="<%=basePath%>webresources/script/wabacus_api.js"></script>
	<script type="text/javascript">
	var pageNo = 1;
	var totalPages;	
	function showRTUMachine(){
			newPage(pageNo);
			$("li a.afterpage").on("click",function(){
				if(pageNo == 1){
					myAlert("已经是最后一页了！！！")	;	
					return	
				}else{
					pageNo = pageNo + 1;
					newPage(pageNo);		
				}			
								
			});
			$("li a.beforepage").on("click",function(){
				if(pageNo == totalPages){
						myAlert("已经是第一页了！！！")	;		
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
							pageNo = value['pageNo'];
							totalPages = value['totalPages'];
							$("tbody#water").append("<tr id =\"waterinfo\">" 
								+ "<td>" + count + "</td>"
								+ "<td>" + unitName + "</td>"
								+ "<td>" + machineCode + "</td>"
								+ "<td>" + machineName + "</td>"
								+ "<td>" + state + "</td>"
								+ "<td><a href=\"javascript:void(0);\"  onclick=wx_winpage('ShowReport.wx?PAGEID=interfaceInfoMainPage&MachineCode='" + machineCode + "',{width:800,height:600,title:'接口信息'}')>接口信息</a></td>"
								+ "</tr>"
	        		  		);
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
	 	$(document).ready(showRTUMachine);
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
	 </script>
  </head>
  
  <body>
  	
  		<div class="panel panel-info">
   			<div class="panel-heading">
      			<h3 class="panel-title">水系统监测窗口</h3>
   			</div>
   			<table class="table table-striped table-hover table-condensed" >
   			<thead>
   			<tr>
   				<th>序号</th>    		
	  			<th>所属单位</th>
	  			<th>RTU编号</th>
	  			<th>设备名称</th>
	  			<th>设备状态</th>
	  			<th>操作</th>
	  		</tr>
	  		</thead>	
	  		<tbody id="water">
	  		
	  		</tbody>
  			</table>
			
		</div>
		 <ul class="pager">
  				<li class="previous"><a href="javascript:void(0);" class="afterpage">&larr; 上一页</a></li>
  				<li class="next"><a href="javascript:void(0);" class="beforepage">下一页 &rarr;</a></li>
		</ul>
  </body>
    
</html>
