<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="jAlert-master/src/jAlert-v3.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap/css/bootstrap.css">
	
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
	<script src="jAlert-master/src/jAlert-v3.js"></script>
	<script type="text/javascript">
		function fetch(){
			$.ajax({
				url:"admin/eventCount_getCount.action",
				method:'get',
				dataType:'json',
				contentType:"application/json; charset=utf-8",
				cache:false,
				success:function(eventCount){
					$("#police").text(eventCount);
				},
				error:function(res){
					myAlert("请求数据失败","error");
				},
				complete:function() {
						setTimeout(function(){fetch();},5000);
				}
			});
		}
		$(document).ready(fetch);
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
	<style type="text/css">
		.head1{ height: 48px; position: relative; width: 100%; z-index: 1000; background-image: url(images/manage_01.jpg);}
		.head2{ height: 127px; position: relative; width: 100%; z-index: 1000; background-image: url(images/manage_02.jpg);z-index: -1}
		#mymenu{
			margin-top: 5px;
			margin-right: 20px;
		}
		.btn{
			height:40px ;
			width:60px;
			font-size: 14px;
			color: white;
			font-weight: bold;
		}
		.btn-new{
		 	background-color: #494D4F
		}
		.btn:HOVER{
			background: orange;
		} 
		
		.btn:FOCUS{
			background-color: orange
		}

		.btn:ACTIVE{
			background-color:orange ;
		}
		.btn:FIRST-CHILD
		
		#police{
			background-color: red;
			border-radius: 50px;
			top:-13px;

	
			font-size: 8px;
		}
		
	</style>
	
  </head>
  
  <body  topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" >
  		<div class="head1">
  		<div class="pull-right" id="mymenu">
  		<div class="btn-group" >
  			 <button type="button" class="btn dropdown-toggle btn-new" id="dropdownMenu1"  data-toggle="dropdown" >
  				<span class="badge pull-right" id="police">0</span>
  				监测
  				
  			 </button>
			 <ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu1">
			      <li role="presentation">
			         <a role="menuitem" tabindex="-1" href="<%=basePath %>admin/admin_right.action" target="right">监控主页</a>
			      </li>
			      <li role="presentation">
			         <a role="menuitem" tabindex="-1" href="<%=basePath %>ShowReport.wx?PAGEID=EventNoInfo" target="right">新事件处理</a>
			      </li>
			      <li role="presentation" class="divider"></li>	
			      <li role="presentation">
			         <a role="menuitem" tabindex="-1" href="<%=basePath %>admin/unitMap/unitMap_draw.action" target="right">单位地图</a>
			      </li>    		
			  </ul>
		</div>
		<div class="btn-group">
			  <button type="button" class="btn btn-new dropdown-toggle" id="dropdownMenu2"  data-toggle="dropdown">
  				 帮助
  			 </button>
  		</div>

		<div class="btn-group">
  			 <button type="button" class="btn btn-new dropdown-toggle" id="dropdownMenu4"  data-toggle="dropdown">
  				  综合
  			 </button>
  			 <ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu3">
			      <li role="presentation">
			         <a role="menuitem" tabindex="-1" href="<%=basePath %>ShowReport.wx?PAGEID=weibao" target="right">单位评估</a>
			      </li>
			      <li role="presentation">
			         <a role="menuitem" tabindex="-1" href="<%=basePath %>ShowReport.wx?PAGEID=viewassessmentresults"   target="right">查看评估结果</a>
			      </li>
			      <li role="presentation" class="divider"></li>
			       <li role="presentation">
			         <a role="menuitem" tabindex="-1" href="<%=basePath%>admin/evaluate/machineEvaluate_load.action" target="right">消防主机评估</a>
			      </li>	      		
			  </ul>
		</div>
		  <div class="btn-group">
  			 <button type="button" class="btn  btn-primary dropdown-toggle" id="dropdownMenu3"  data-toggle="dropdown">
  				 用户
  			 </button>
  			 <ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu3">
			      <li role="presentation">
			         <a role="menuitem" tabindex="-1" href="<%=basePath %>ShowReport.wx?PAGEID=updateuserinfo" target="right">修改密码</a>
			      </li>
			      <li role="presentation">
			         <a role="menuitem" tabindex="-1" href="<%=basePath %>ShowReport.wx?PAGEID=updateUser" target="right">查看用户资料</a>
			      </li>	
			      <li role="presentation" class="divider"></li>
			       <li role="presentation">
			         <a role="menuitem" tabindex="-1" href="<%=basePath%>admin/user_logout.action"  target="_parent">退出</a>
			      </li>	    	
			  </ul>
		</div>
		</div>
		</div>
		<div class="head2">
			
		</div>
  </body>
</html>
