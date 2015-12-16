<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<% String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'eventReport.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=path%>/bootstrap/css/bootstrap-datepicker.css">
	<link rel="stylesheet" href="jAlert-master/src/jAlert-v3.css">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
	<script  type="text/javascript" src="<%=path%>/bootstrap/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/esl.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dist/echarts.js"></script>
	<script type="text/javascript" src="<%=basePath%>bootstrap/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
	<script src="jAlert-master/src/jAlert-v3.js"></script>
<script type="text/javascript">
	  $(function() {
       
        });
</script>
<script type="text/javascript">
		$(document).ready(function(){
			
			var unitId = '<s:property value="unitId"/>';
			var myDate = new Date();
            var year = myDate.getFullYear();
            var month = myDate.getMonth() + 1; 
            //var month = myDate.getMonth(); 
            var time = year + " 年 " + month + " 月 ";
            var newTime  = year + "/" +  month;
          	$("#datepicker" ).val(newTime);
            $("#datepicker" ).datepicker({     	
	          	viewMode:"years",
	          	minViewMode:"months",//months
	          	format:"yyyy/mm",
	          	autoclose: true
            });
            $("#time").text(time);
           	//$("#datepicker").datepicker('setValue',newTime);
           	$.ajax({
                	type : "get",
                	async : true,
                	url : "admin/eventStatistical/eventReport_getAlertCount.action?eventUnitId=" + unitId + "&year=" + year + "&month=" + month,
                	data : {},
                	dataType : "json",
                	success : function(jsonCount) {
	                	if(jsonCount.length > 0){
	                		  $.each(jsonCount,function(index,value){
	                		  		$("#fire").text(value['count1']);
	                		  		$("#start").text(value['count2']);
	                		  		$("#jianguan").text(value['count3']);
	                		  		$("#fuwei").text(value['count4']);
	                		  		$("#guzhang").text(value['count5']);
	                		  		$("#fankui").text(value['count6']);
	                		  		$("#pingbi").text(value['count7']);
	                		  		$("#shoudong").text(value['count8']);
	                		  		$("#real").text(value['count']);
	                		  		$("#error").text(value['errorResult']);
	                		  		$("#test").text(value['testCount']);
	                		  		$("#testrate").text(value['result']);
	                		  });
	                	}    			
                    } ,
                	error : function(errorMsg) {  
                   				myAlert("图表请求数据失败啦!");  
               				 } 
                	});
               $("#setbtn").on("click",function(){
              		var text = document.getElementById("datepicker");
        			var arr = text.value.split("/");
        			year = arr[0];
        			month = arr[1];
        			 var time = year + " 年 " + month + " 月 "; 
            		$("#time").text(time);
              		$.ajax({
	                	type : "get",
	                	async : true,
	                	url : "admin/eventStatistical/eventReport_getAlertCount.action?eventUnitId=" + unitId + "&year=" + year + "&month=" + month,
	                	data : {},
	                	dataType : "json",
	                	success : function(jsonCount) {
		                	if(jsonCount.length > 0){
		                		  $.each(jsonCount,function(index,value){
		                		  		$("#fire").text(value['count1']);
		                		  		$("#start").text(value['count2']);
		                		  		$("#jianguan").text(value['count3']);
		                		  		$("#fuwei").text(value['count4']);
		                		  		$("#guzhang").text(value['count5']);
		                		  		$("#fankui").text(value['count6']);
		                		  		$("#pingbi").text(value['count7']);
		                		  		$("#shoudong").text(value['count8']);
		                		  		$("#real").text(value['count']);
		                		  		$("#error").text(value['errorResult']);
		                		  		$("#test").text(value['testCount']);
		                		  		$("#testrate").text(value['result']);
		                		  });
		                	}    			
	                    } ,
	                	error : function(errorMsg) {  
	                   				myAlert("图表请求数据失败啦!");  
	               				 } 
                	});
                	
              });
              $("#demo").on("show.bs.collapse",function(){
                	//alert("请求数据");
                	
                	var text = document.getElementById("datepicker");
                	alert(text);
                	if(text != ""){
        				var arr = text.value.split("/");
        				year = arr[0];
        				month = arr[1];
        			}
        			//var time = year + " 年 " + month + " 月 "; 
	              	var pageNo = 1;
	              	var totalPages = 0;
	              	//alert(year + "/" +  month);
	              		$.ajax({
		                	type : "get",
		                	async : true,
		                	url : "admin/eventStatistical/eventReport_getAllEvent.action?eventUnitId=" + unitId + "&year=" + year + "&month=" + month + "&pageNo=" + pageNo,
		                	data : {},
		                	dataType : "json",
		                	success : function(jsonAllInfo) {
		                		$("tbody#eventinfo").empty();
			                	if(jsonAllInfo.length > 0){
			                		  $.each(jsonAllInfo,function(index,value){
			                		  		pageNo = value['pageNo'];
			    				    		totalPages = value['totalPages'];
			    				    		var eventName = value['eventName'];
			    			        		var alertSource  = value['alertSource'];
			    			        		var description  = value['description'];
			    			        		var happenPlace  = value['happenPlace'];
			    			        		var happenTime  = value['happenTime'];
			    			        		var alertMachine = value['alertMachine'];
			    			        		var typeName = value['typeName'];
			    			        		var sum = index + 1;
			                		  		$("tbody#eventinfo").append("<tr>" 
			                		  			+ "<td class=\"text-center\"><small>" + sum + "</small></td>"
			                		  			+ "<td class=\"text-center\"><small>" + eventName + "</small></td>"
			                		  			+ "<td class=\"text-center\"><small>" + typeName + "</small></td>"
			                		  			+ "<td class=\"text-center\"><small>" + alertMachine + "</small></td>"
			                		  			+ "<td class=\"text-center\"><small>" + alertSource + "</small></td>"
			                		  			+ "<td class=\"text-center\"><small>" + description + "</small></td>"
			                		  			+ "<td class=\"text-center\"><small>" + happenPlace + "</small></td>"
			                		  			+ "<td class=\"text-center\"><small>" + happenTime + "</small></td>"
			                		  			+ "</tr>"
			                		  		);
			                		  });
			                	}    			
		                    } ,
		                	error : function(errorMsg) {  
		                   				myAlert("图表请求数据失败啦!");  
		               				 } 
	                	});
	                	$("li a.beforepage").on("click",function(){
	                			if (pageNo == totalPages) {
		    				        	myAlert("这已经是最后一页");
		    				        	return;
		    				      } else {
		    				        	pageNo = pageNo + 1;
		    				        	$.ajax({
						                	type : "get",
						                	async : true,
						                	url : "admin/eventStatistical/eventReport_getAllEvent.action?eventUnitId=" + unitId + "&year=" + year + "&month=" + month + "&pageNo=" + pageNo,
						                	data : {},
						                	dataType : "json",
						                	success : function(jsonAllInfo) {
						                		$("tbody#eventinfo").empty();
							                	if(jsonAllInfo.length > 0){
							                		  $.each(jsonAllInfo,function(index,value){
							                		  		pageNo = value['pageNo'];
							    				    		totalPages = value['totalPages'];
							    				    		var eventName = value['eventName'];
							    			        		var alertSource  = value['alertSource'];
							    			        		var description  = value['description'];
							    			        		var happenPlace  = value['happenPlace'];
							    			        		var happenTime  = value['happenTime'];
							    			        		var alertMachine = value['alertMachine'];
							    			        		var typeName = value['typeName'];
							    			        		var sum = index + 1;
							                		  		$("tbody#eventinfo").append("<tr>" 
							                		  			+ "<td class=\"text-center\"><small>" + sum + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + eventName + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + typeName + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + alertMachine + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + alertSource + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + description + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + happenPlace + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + happenTime + "</small></td>"
							                		  			+ "</tr>"
							                		  		);
							                		  });
							                	}    			
						                    } ,
						                	error : function(errorMsg) {  
						                   				myAlert("图表请求数据失败啦!");  
						               		} 
	                					});
		    				      }
	                	});
	                	$("li a.afterpage").on("click",function(){
	                			if (pageNo == 1) {
		    				        	myAlert("这已经是第一页");
		    				        	return;
		    				    } else {
		    				        	pageNo = pageNo - 1;
		    				        	$.ajax({
						                	type : "get",
						                	async : true,
						                	url : "admin/eventStatistical/eventReport_getAllEvent.action?eventUnitId=" + unitId + "&year=" + year + "&month=" + month + "&pageNo=" + pageNo,
						                	data : {},
						                	dataType : "json",
						                	success : function(jsonAllInfo) {
						                		$("tbody#eventinfo").empty();
							                	if(jsonAllInfo.length > 0){
							                		  $.each(jsonAllInfo,function(index,value){
							                		  		pageNo = value['pageNo'];
							    				    		totalPages = value['totalPages'];
							    				    		var eventName = value['eventName'];
							    			        		var alertSource  = value['alertSource'];
							    			        		var description  = value['description'];
							    			        		var happenPlace  = value['happenPlace'];
							    			        		var happenTime  = value['happenTime'];
							    			        		var alertMachine = value['alertMachine'];
							    			        		var typeName = value['typeName'];
							    			        		var sum = index + 1;
							                		  		$("tbody#eventinfo").append("<tr>" 
							                		  			+ "<td class=\"text-center\"><small>" + sum + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + eventName + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + typeName + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + alertMachine + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + alertSource + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + description + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + happenPlace + "</small></td>"
							                		  			+ "<td class=\"text-center\"><small>" + happenTime + "</small></td>"
							                		  			+ "</tr>"
							                		  		);
							                		  });
							                	}    			
						                    } ,
						                	error : function(errorMsg) {  
						                   				myAlert("图表请求数据失败啦!");  
						               		} 
	                					});
		    				    }
	                	});
              		});
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
</script>
 <style>
        body{
            
        }
        th{
            text-align: center;
            font-style: inherit;
            font-size: 14px;
        }
        td{
            text-align: center;
             font-size: 14px;
        }
        table{
            border-color: #2e6da4;
        }
        #dh{
            text-align: center;
            color: #2e6da4;
            font-size: large;
            font-style: inherit;
            padding-top: 10px;
            padding-bottom: 10px;
            
            
        }
        #bold{
            font-weight: bold;
        }
        #red{
            color: red;
        }
        #bt{
            text-align: center;
        }
 		.row{
 			width:1142px;
 			margin-left: 0px;
 			margin-right:0px; 
			
 		}
 		
 		.well{
 			margin-left: 2px;
 			margin-right: 2px;
 			margin-top: 2px;
 			margin-bottom: 2px;
 			background-color: white;
			border-width: 0px;
 		}
 		#datepicker{
 			height: 30px;
 		}
 		
 		#mybtn{
 			margin-left: 15px;
 		}
 		#setbtn{
 			height: 30px;
 			width: 65;
 		}
 		.navbar{
 		
 			margin-left: 2px;
 			margin-right: 2px;
 		}
 		
 		#unitinfo{
 			margin-left: 3px;
 			margin-right: 3px;
 			
 		}
 		#alertinfo{
 			margin-left: 3px;
 			margin-right: 3px;
 			
 		}
 		#demo{
 			margin-left: 4px;	
 			margin-right: 4px;
 		}
        
    </style>
  </head>
  
  <body>
    <div class="well">
    		<div class="row">
    		<div id="date" class="center-block pull-left ">
                    <input type="text" id="datepicker"  class="form-control " placeholder="请选择月份">
             </div>
              <div id="mybtn" class="center-block pull-left ">
					  <button value="button"  class="btn btn-info" id="setbtn" >查询</button>
			</div>
			</div>
    </div>
    <div class="navbar navbar-default">
    <div id="dh">
        火灾自动报警系统报表
    </div>
	</div>
	<div>
    <p style="font-weight:bold;margin-left:5px">
        一、单位信息
    </p>
	</div>
	<div  id="unitinfo">
   <table class="table table-bordered table-hover table-condensed">
       <tr>
           <th>单位名称</th>
           <td><s:property value="unitInfo.unitName" /></td>
           <th> 联系人</th>
           <td><s:property value="unitInfo.contactsName" /> </td>
           <th>联系点话</th>
           <td><s:property value="unitInfo.contactsTel" /> </td>
       </tr>
       <tr>
           <th>维保单位</th>
           <td><s:property value="unitInfo.repairUnit.getUnitName()"/></td>
           <th>地址</th>
           <td colspan="3" ><s:property value="unitInfo.unitAddr"/></td>
       </tr>
       <tr>
           <th>统计时间</th>
           <td colspan="5" id="time"></td>
       </tr>
   </table>
</div>
<div>
<p style="margin-left:5px">
   <span id="bold"> 二、数据统计</span>（单位：次）
</p>
</div>
<div id="alertinfo">
    <table class="table table-bordered table-hover  table-condensed">
        <tr>
            <th>火警</th>
            <td id="fire"></td>
            <th>启动</th>
            <td id="start"></td>
            <th>监管</th>
            <td id="jianguan"></td>
            <th>复位</th>
            <td id="fuwei"></td>
        </tr>
        <tr>
            <th>故障</th>
            <td id="guzhang"></td>
            <th>反馈</th>
            <td id="fankui"></td>
            <th>屏蔽</th>
            <td id="pingbi"></td>
            <th>手动火警</th>
            <td id="shoudong"></td>
        </tr>
        <tr>
            <th>真火警</th>
            <td id="real"></td>

            <th>误报率</th>
            <td id="error"></td>

            <th>测试</th>
            <td id="test"></td>
            
            <th>抽检率</th>
            <td id="testrate"></td>

        </tr>
    </table>
</div>
    <div id="demo" class="collapse">
    		<p style="margin-left:5px">
   				<span id="bold"> 三、详细信息</span>
			</p>
    		<table class="table table-striped table-hover table-condensed ">
    			<thead>
    			<tr>
	    			<th class="text-center"><small>序号</small></th>
	    			<th class="text-center"><small>事件名称</small></th>
	    			<th class="text-center"><small>事件类型</small></th>
	    			<th class="text-center"><small>报警设备</small></th>
	    			<th class="text-center"><small>报警源</small></th>
	    			<th class="text-center"><small>描述</small></th>
	    			<th class="text-center" ><small>发生地点</small></th>
	    			<th class="text-center"><small>发生时间</small></th>
    			</tr>
    			</thead>
    			<tbody  id="eventinfo" >
    			
    			</tbody>
    		</table>
    		<ul class="pager" id="mypage">
  					<li class="previous"><a class="afterpage"href="javascript:void(0);" >&larr; 上一页</a></li>
					<li class="next"><a class="beforepage" href="javascript:void(0);"  >下一页 &rarr;</a></li>
			</ul>
    </div>
<div>
    <p style="margin-left:5px">
        <span id="bold">注释：</span><br>
        1.测试数量指消防维护时传输装置工作在测试模式时上传的报警信息数量；<br>
        2.真火警数量指人工处理平台火警信息，未标注为误报的火警信息数量；<br>
        3.误报率指（已处理为误报的报警数量/已处理报警总数量）*100%；<br>
        4.抽检率（测试节点数/总节点数）*100%；
    </p>
</div>
<div id="bt">
    <button type="button" class="btn btn-primary " data-toggle="collapse" data-target="#demo" id="searchbtn">查看详细信息</button>  

</div>

  </body>
</html>
