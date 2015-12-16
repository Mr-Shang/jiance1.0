<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'unitMap.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Q3q20lCChEWuOKr63nlyWZos"></script>
 <script type="text/javascript">
   $(document).ready(function(){
    	var site = '<s:property value="unitInfo.unitSite"/>';
    	var unitName = '单位名称：' + '<s:property value="unitInfo.unitName"/>' +  '<br>';
    	var addr = '单位地址：' + '<s:property value="unitInfo.unitAddr"/>' +  '<br>';
    	var headName = '联系人：' + '<s:property value="unitInfo.headName"/>' +  '<br>';
    	var headTel = '联系人电话：' + '<s:property value="unitInfo.headTel"/>' +  '<br>';
    	var info = unitName + addr + headName + headTel;
    	var str = site.split(',');	
		var x = parseFloat(str[0]);
		var y = parseFloat(str[1]);
		var map = new BMap.Map("allMap");    // 创建Map实例
		var point = new BMap.Point(x,y);
		map.centerAndZoom(point, 15);  // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
		map.setCurrentCity("辽源");          // 设置地图显示的城市 此项是必须设置的
		map.panTo(point);     
		var top_left_navigation = new BMap.NavigationControl(); //比例尺控件
		var bottom_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});//缩放
		map.addControl(bottom_left_control);        
		map.addControl(top_left_navigation);     
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);  
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		setTimeout(function(){marker.setAnimation(null);},3000);
		var opts = {
				  width : 280,     // 信息窗口宽度
				  height: 150,     // 信息窗口高度
				  title :  '单位信息', // 信息窗口标题
				  enableMessage:true//设置允许信息窗发送短息
		};
		var infoWindow = new BMap.InfoWindow(info, opts);  // 创建信息窗口对象 
		marker.addEventListener("click", function(){          
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	});
		
    });
    </script>
	<style type="text/css">
		div#allMap{
		width:100%;
		height:100%;
	}
	</style>
  </head>
  
  <body>
  	
    <div id="allMap"></div>
 
  </body>
</html>
