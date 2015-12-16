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
	<link rel="stylesheet" href="<%=path%>/css/style.css" media="all" />
	<style type="text/css">
		.head1{ height: 48px; position: relative; width: 100%; z-index: 1000; background-image: url(images/manage_01.jpg);}
		.head2{ height: 127px; position: relative; width: 100%; z-index: 1000; background-image: url(images/manage_02.jpg);z-index: -1}
		div#topMenu{
			float:right;
			margin-top:5px;
			margin-right:20px;
		}
		span#police{
			display: block;
	background:#eb1403;
	padding: 2px 5px;
	position: absolute;
	top: -5px;
	right: -5px;
	width: auto;
	font-size: 9px;
	border: 1px solid #222;
	border-radius: 50px;
	-webkit-border-radius: 50px;
	-moz-border-radius: 50px;
	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.3);
	-webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.3);
	-moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.3);
		}
	ul#submenu {
	position: absolute;
	left: -1px;
	top: 37px;
	border: 1px solid rgba(0, 0, 0, .3);
	background:#218EF1;
	z-index: 10000;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 05px;
	min-width: 200px;
	display: none;
}
	</style>
	<%-- <script type="text/javascript" src="<%=basePath%>js/comet4j.js"></script>
		<script src="<%=basePath%>js/jquery-1.7.2.js"></script>
		<script src="<%=path%>/js/custom.js"></script>
		<script src="<%=path%>/js/jquery.tablesorter.min.js"></script>
		<script type="text/javascript">
	
			function init() {
				var en = document.getElementById('police');
				var voiceEle = document.getElementById('voice');
				JS.Engine.on( {
					police : function(police) {//侦听一个channel
							en.innerHTML = police;
							if(police != 0) {
								voiceEle.src = '1262.mp3';
							}else {
								voiceEle.src = '';
							}
						}
					});
					
				 JS.Engine.start('conn');
			        JS.Engine.on('start',function(cId,channelList,engine){
			         	/*  
			       		  alert("start");
			        	  alert('连接已建立，连接ID为：' + cId);
			        	   */
			        });
			}
			
			</script> --%>
  </head>
  
  <body  topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" onload="init()">
  	<div>
    	<div class="head1">
    	  <div class="buttons" id="topMenu">       
		<span class="button dropdown">
			<a href="<%=basePath %>ShowReport.wx?PAGEID=monitoring" target="right">
				监测
				<span class="pip" id="police"></span>
				<embed src="" autostart="true" id="voice" hidden="true" loop="true" type="audio/mpeg">
				</a>
		</span> 
        
        
		
		 <span class="button dropdown">帮助</span>
         <span class="button dropdown">
			<a href="<%=basePath %>ShowReport.wx?PAGEID=updateUser"  target="right">用户</a>
		</span> 
        
		  <span class="button blue"><a href="<%=path%>/admin/sysMenu/user_logout.action" target="_parent">注销</a></span>	
		  </div>
    	</div>
      	<div class="head2" >
    	
    	</div> 
    </div>
  </body>
</html>
