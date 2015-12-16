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
    
    <title>My JSP 'right.jsp' starting page</title>
    
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

<style type="text/css">
	body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
	div#allMap{
		width:75%;
		height:100%;
		float:left; 
	}
	div#list{
		width:25%;
		height:100%;
		float:right;
		padding-left:5px;
		
		padding-right:5px;
	}
	div#search{
		margin-top:5px;
		height:25%;
	}
	div#unit{
		height:57%;
		margin-bottom: 5px;
	}
	
	div#main{
		margin-top: 15px
	}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Q3q20lCChEWuOKr63nlyWZos"></script>

 </head>
  <body >
  	<div id="allMap"></div>
  	<script type="text/javascript">
	$(document).ready(function(){
			var pageNo;
			var totalPages;
			// 百度地图API功能
			var map = new BMap.Map("allMap");    // 创建Map实例
			//var point = new BMap.Point(125.154978,42.915268 );
			map.centerAndZoom("辽源",15);  // 初始化地图,设置中心点坐标和地图级别
			map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
			map.setCurrentCity("辽源");          // 设置地图显示的城市 此项是必须设置的
			var top_left_navigation = new BMap.NavigationControl(); //比例尺控件
			var bottom_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});//缩放
			map.addControl(bottom_left_control);        
			map.addControl(top_left_navigation);     
			
			map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
			//var myIcon = new BMap.Icon("images/alert_1.png",new BMap.Size(25,25));
			//alert(objArr.length);
			var data_info = [];
    		$.ajax({
    			type : "get",
    			async :true,
    			url : "admin/unitMap/unitMap_getMapData.action",
    			contentType:"application/json; charset=utf-8",
    			cache:false,
    			data : {},
    			dataType : "json",
    			success : function(jsonArray) {
    				if(jsonArray.length > 0) {
	    				$.each(jsonArray,function(index,value){
	    					var site =  value['site'];
	    					var name = '单位名称：' + value['unitName'] + '<br>';
	    					var addr = '地址：' + value['addr'] + '<br>';
	    					var header = '联系人：' + value['header'] + '<br>';
	    					var tel = '联系电话：' +  value['tel'] + '<br>';
	    					var isHave = value['isHave'];
	    					var info = name + addr + header + tel;
	    					//alert(info);
	    					var str = site.split(',');	
	    					var x = parseFloat(str[0]);
	    					var y = parseFloat(str[1]);
	    					var myData = [x,y,info,isHave];
	    					data_info[index] = myData;
	    				});
	    				for(var i=0;i<data_info.length;i++){
	    					var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注{icon:myIcon}
	    					var content = data_info[i][2];
	    					map.addOverlay(marker);               // 将标注添加到地图中
	    					if(data_info[i][3] == 1){
	    						marker.setAnimation(BMAP_ANIMATION_BOUNCE);
	    					}
	    					addClickHandler(content,marker);
	    				}
    				}
    			} ,
    			error : function(errorMsg) {  
					myAlert("请求地图数据失败");
    			}
    		});
			function addClickHandler(content,marker){
				marker.addEventListener("click",function(e){
					openInfo(content,e);}
				);
			}
			function openInfo(content,e){
				var opts = {
					  width : 260,     // 信息窗口宽度
					  height: 120,     // 信息窗口高度
					  title :  '单位信息', // 信息窗口标题
					  enableMessage:true//设置允许信息窗发送短息
				};
				var p = e.target;
				var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
				var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
				map.openInfoWindow(infoWindow,point); //开启信息窗口
			}
			$("#searchBtn").on("click",function(){
				var unitName = $("#unitName").val();
				if(unitName != ""){
					var param = encodeURI(encodeURI(unitName)); 
					//alert(areaName);
					$.ajax({
						type : "get",
						async : false,
						url : "admin/unitMap/getAreaSite_getSite.action?unitName=" + param,
						data : {},
						dataType : "json",
						success : function(siteJSONArray) {
							//alert(siteJSONArray.length);
							if(siteJSONArray.length != 0){
								$.each(siteJSONArray,function(index,value){
									var site = value['site'];
									var str = site.split(',');	
									var x = parseFloat(str[0]);
									var y = parseFloat(str[1]);
									var new_point = new BMap.Point(x,y);
									var marker = new BMap.Marker(new_point); 
									map.addOverlay(marker);
									map.panTo(new_point); 
									marker.setAnimation(BMAP_ANIMATION_BOUNCE);				
									setTimeout(function(){map.removeOverlay(marker);},3000);
								});   
							} else {
								myAlert("没有匹配的单位");
							}      			
						} ,
						error : function(errorMsg) {  
							myAlert("地图数据请求失败");
						} 
					});
				} else {
					myAlert("请输入检索信息！！！");	
				}
			});
			$.ajax({
					type : "get",
					async : true,
					url : "admin/unitMap/getUnitName_getName.action",
					data : {},
					dataType : "json",
					success : function(nameJSONArray) {
						//alert(nameJSONArray.length);
						$("tr.unit").remove();
						if(nameJSONArray.length != 0){
							$.each(nameJSONArray,function(index,value){
								var unitName = value['unitName'];
								pageNo =  parseInt(value['pageNo']) ;	
								
								totalPages = parseInt(value['totalPages']) ;	
								var num = index + 1;
								
								$("table").append("<tr class=\"unit\"><td><small >" + num + "</small></td><td><small ><a id=\"mainLink\" href=\"javascript:void(0);\">" + unitName + "</a></small></td></tr>");
							}); 
							$("small a").on("click",function(){
								var unitName = $(this).text();					
								var param = encodeURI(encodeURI(unitName)); 
								$.ajax({
									type : "get",
									async : true,
									url : "admin/unitMap/getUnitSite_getSite.action?unitName=" + param,
									data : {},
									dataType : "json",
									success : function(siteJSONArray) {
										if(siteJSONArray != null){
											$.each(siteJSONArray,function(index,value){
												var site = value['site'];
												var str = site.split(',');	
												var x = parseFloat(str[0]);
												var y = parseFloat(str[1]);
												var new_point = new BMap.Point(x,y);
												var marker = new BMap.Marker(new_point); 
												map.addOverlay(marker);
												map.panTo(new_point); 
												marker.setAnimation(BMAP_ANIMATION_BOUNCE);				
												setTimeout(function(){map.removeOverlay(marker);},3000);
											});   
										} else {
											$.jAlert({
												 'title': '提示信息',
												 'content': '没有匹配的单位！！！',
												 'theme': 'default',
												 'size': 'xsm',
												 'btns': [
												          {'text':'确定', 'closeAlert':true }
												         
												       ]
											});
										}
										     			
									} ,
									error : function(errorMsg) {  
										$.jAlert({
											 'title': '提示信息',
											 'content': '地图数据请求失败',
											 'theme': 'default',
											 'size': 'xsm',
											 'btns': [
											          {'text':'确定', 'closeAlert':true }
											         
											       ]
										});
									} 
								});
							});	
							$("li a.afterpage").on("click",function(){
								//alert(pageNo);
								if (pageNo == 1) {
									$.jAlert({
										 'title': '提示信息',
										 'content': '这已经是第一页',
										 'theme': 'default',
										 'size': 'xsm',
										 'btns': [
										          {'text':'确定', 'closeAlert':true }
										         
										       ]
									});
									return;
								} else {
									pageNo = pageNo - 1;
									$.ajax({
										type : "get",
										async : true,
										url : "admin/unitMap/getUnitName_getName.action?pageNo=" + pageNo,
										data : {},
										dataType : "json",
										success : function(nameJSONArray) {
											$("tr.unit").remove();
											//alert(nameJSONArray.length);
											if(nameJSONArray.length != 0){
												$.each(nameJSONArray,function(index,value){
													var unitName = value['unitName'];
													pageNo =  parseInt(value['pageNo']);	
													var num = index + 1;
													
													$("table").append("<tr class=\"unit\"><td><small >" + num + "</small></td><td><small ><a class=\"mainLink\" href=\"javascript:void(0);\">" + unitName + "</a></small></td></tr>");
												}); 
												$("a.mainLink").on("click",function(){
													var unitName = $(this).text();							
													var param = encodeURI(encodeURI(unitName)); 
													$.ajax({
														type : "get",
														async : true,
														url : "admin/unitMap/getUnitSite_getSite.action?unitName=" + param,
														data : {},
														dataType : "json",
														success : function(siteJSONArray) {
															if(siteJSONArray != null){
																$.each(siteJSONArray,function(index,value){
																	var site = value['site'];
																	var str = site.split(',');	
																	var x = parseFloat(str[0]);
																	var y = parseFloat(str[1]);
																	var new_point = new BMap.Point(x,y);
																	var marker = new BMap.Marker(new_point); 
																	map.addOverlay(marker);
																	map.panTo(new_point); 
																	marker.setAnimation(BMAP_ANIMATION_BOUNCE);				
																	setTimeout(function(){map.removeOverlay(marker);},3000);
																});   
															} else {
																$.jAlert({
																	 'title': '提示信息',
																	 'content': '没有匹配的单位！！！',
																	 'theme': 'default',
																	 'size': 'xsm',
																	 'btns': [
																	          {'text':'确定', 'closeAlert':true }
																	         
																	       ]
																});
															}
															     			
														} ,
														error : function(errorMsg) {  
															$.jAlert({
																 'title': '提示信息',
																 'content': '地图数据请求失败',
																 'theme': 'default',
																 'size': 'xsm',
																 'btns': [
																          {'text':'确定', 'closeAlert':true }
																         
																       ]
															});
														} 
													});
												});	
											} else {
												$.jAlert({
													 'title': '提示信息',
													 'content': '没有单位！！！',
													 'theme': 'default',
													 'size': 'xsm',
													 'btns': [
													          {'text':'确定', 'closeAlert':true }
													         
													       ]
												});
											}      			
										} ,
										error : function(errorMsg) {  
											$.jAlert({
												 'title': '提示信息',
												 'content': '地图数据请求失败',
												 'theme': 'default',
												 'size': 'xsm',
												 'btns': [
												          {'text':'确定', 'closeAlert':true }
												         
												       ]
											});
										} 
									});
								}
								
							});
							$("li a.beforepage").on("click",function(){
								//alert(pageNo);
								if(pageNo == totalPages ) {
									$.jAlert({
										 'title': '提示信息',
										 'content': '这已经是第一页',
										 'theme': 'default',
										 'size': 'xsm',
										 'btns': [
										          {'text':'确定', 'closeAlert':true }
										         
										       ]
									});
									return;
								} else {
									
									pageNo = pageNo + 1;
									//alert(pageNo);
									$.ajax({
										type : "get",
										async : true,
										url : "admin/unitMap/getUnitName_getName.action?pageNo=" + pageNo,
										data : {},
										dataType : "json",
										success : function(nameJSONArray) {
											$("tr.unit").remove();
											//alert(nameJSONArray.length);
											if(nameJSONArray.length != 0){
												
												$.each(nameJSONArray,function(index,value){
													var unitName = value['unitName'];
													pageNo =  parseInt(value['pageNo']);	
													var num = index + 1;
													
													$("table").append("<tr class=\"unit\"><td><small >" + num + "</small></td><td><small ><a id=\"mainLink\" href=\"javascript:void(0);\">" + unitName + "</a></small></td></tr>");
												}); 
												$("a").on("click",function(){
													var unitName = $(this).text();
													
													var param = encodeURI(encodeURI(unitName)); 
													$.ajax({
														type : "get",
														async : true,
														url : "admin/unitMap/getUnitSite_getSite.action?unitName=" + param,
														data : {},
														dataType : "json",
														success : function(siteJSONArray) {
															if(siteJSONArray != null){
																$.each(siteJSONArray,function(index,value){
																	var site = value['site'];
																	var str = site.split(',');	
																	var x = parseFloat(str[0]);
																	var y = parseFloat(str[1]);
																	var new_point = new BMap.Point(x,y);
																	var marker = new BMap.Marker(new_point); 
																	map.addOverlay(marker);
																	map.panTo(new_point); 
																	marker.setAnimation(BMAP_ANIMATION_BOUNCE);				
																	setTimeout(function(){map.removeOverlay(marker);},3000);
																});   
															} else {
																$.jAlert({
																	 'title': '提示信息',
																	 'content': '没有匹配的单位！！！',
																	 'theme': 'default',
																	 'size': 'xsm',
																	 'btns': [
																	          {'text':'确定', 'closeAlert':true }
																	         
																	       ]
																});
															}
															     			
														} ,
														error : function(errorMsg) {  
															$.jAlert({
																 'title': '提示信息',
																 'content': '地图数据请求失败',
																 'theme': 'default',
																 'size': 'xsm',
																 'btns': [
																          {'text':'确定', 'closeAlert':true }
																         
																       ]
															});
														} 
													});
												});	
											} else {
												$.jAlert({
													 'title': '提示信息',
													 'content': '没有单位！！！',
													 'theme': 'default',
													 'size': 'xsm',
													 'btns': [
													          {'text':'确定', 'closeAlert':true }
													         
													       ]
												});
											}      			
										} ,
										error : function(errorMsg) {  
											$.jAlert({
												 'title': '提示信息',
												 'content': '地图数据请求失败',
												 'theme': 'default',
												 'size': 'xsm',
												 'btns': [
												          {'text':'确定', 'closeAlert':true }
												         
												       ]
											});
										} 
									});
								}
							});
						} else {
							$.jAlert({
								 'title': '提示信息',
								 'content': '没有单位！！！',
								 'theme': 'default',
								 'size': 'xsm',
								 'btns': [
								          {'text':'确定', 'closeAlert':true }
								         
								       ]
							});
						}      			
					} ,
					error : function(errorMsg) {  
						$.jAlert({
							 'title': '提示信息',
							 'content': '地图数据请求失败',
							 'theme': 'default',
							 'size': 'xsm',
							 'btns': [
							          {'text':'确定', 'closeAlert':true }
							         
							       ]
						});
					} 
					
				});
				function myAlert(msg){
					$.jAlert({
						 'title': '提示信息',
						 'content':msg,
						 'theme': 'default',
						 'size': 'xsm',
						 'btns': [
						          {'text':'确定', 'closeAlert':true }
						         
						       ]
					});
				}
		
		function afterPage(){
			alert(pageNo);
			
			//var pageNo = parseInt( strPageNo);
			//var pageNo = pageModel.pageNo;
			//alert(pageNo);
		}
	});

</script>
	<div id="list"  >
		
		
		<div class="panel panel-default" id="search">
			<div class="panel-heading">
      			<h3 class="panel-title"> 搜索栏</h3>
   			</div>
   			<div class="panel-body" id="main">
      			<div id="mainSearch" class="pull-left">
      				<input type="text" class="form-control" id="unitName" placeholder="请输入单位名称" >
      			</div>
      			<div id="btnSearch" class="pull-right">
      				<button type="button" class="btn btn-info"  id="searchBtn" >搜索</button>
   				</div>
   			</div>
   		</div>
   		<div class="panel  panel-default" id="unit">
   			<div class="panel-heading"><h3 class="panel-title">单位列表</h3></div>
   		
   			<table class="table">
   			
      			<th><small>序号</small></th>
      			<th><small>单位名称</small></th>	
   			</table>  			 	
		</div>
		<ul class="pager" id="mypage">
  					<li class="previous"><a class="afterpage"href="javascript:void(0);" >&larr; 上一页</a></li>
					<li class="next"><a class="beforepage" href="javascript:void(0);"  >下一页 &rarr;</a></li>
				</ul>
		
   	</div>
   	<script type="text/javascript">
   		
   	
   	
   	
   	</script>
</body>
</html>
