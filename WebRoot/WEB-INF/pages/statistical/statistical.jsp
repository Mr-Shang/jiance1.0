<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'eventStatistical.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- <link rel="shortcut icon" href="../asset/ico/favicon.png"> -->
	<%-- <link rel="stylesheet" href="<%=path%>/css/jquery-ui.css"> --%>
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
	$(document).ready(function(){
        $( "#datepicker" ).datepicker({
          viewMode:"years",
          minViewMode:"months",
          format:"yyyy/mm",
          autoclose: true
        });
     });
    </script>
	<script type="text/javascript">
                	var unitId = '<s:property value="unitId"/>';
                	var myDate = new Date();
                	var year = myDate.getFullYear();
                	//var month = myDate.getMonth() + 1; 
                 	var myChart;
                	var eCharts;
                    require.config({
            			paths: {
               				echarts: '<%=path%>/js/dist'
         				}
        		    });
        		    
        		    require(
          				  [
               				 'echarts',
              			     'echarts/chart/bar',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表   
              			   	 'echarts/chart/stack', 
                         ],DrawEChart
                    ); 
                    function DrawEChart(ec) {
                    	eCharts = ec;
                    	myChart = eCharts.init(document.getElementById('main'));  
                    	var objArr = getChartData();
                    	var objArray = [];
                    	var arrName=[];
                    	
                    	$.each(objArr,function(index,value){
                						arrName.push(value['typeName']);
                						var obj =new Object();
                						var arrValue = new Array;
                						arrValue[0] = value['count1'];
                						arrValue[1] = value['count2'];
                						arrValue[2] = value['count3'];
                						arrValue[3] = value['count4'];
                						arrValue[4] = value['count5'];
                						arrValue[5] = value['count6'];
                						arrValue[6] = value['count7'];
                						arrValue[7] = value['count8'];
                						arrValue[8] = value['count9'];
                						arrValue[9] = value['count10'];
                						arrValue[10] = value['count11'];
                						arrValue[11] = value['count12'];
                						obj.name =  value['typeName'];
                						obj.type = 'bar';
                						obj.stack = '总量';
                						obj. itemStyle =  { normal: {label : {show: true, position: 'insideRight'}}};
                						obj.data = arrValue;
                						objArray.push(obj);
                		} 	);
          				myChart.showLoading({  
                			text : "图表数据正在努力加载..."  
            			});                	
                		var options = {
                		 title : {
        					text: '事件统计',
   						 },
					     tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					        	type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					     },
					     legend: {
					        data: arr//  '直接访问','邮件营销','联盟广告','视频广告'										        
					     },
					     toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            orient: 'vertical',
					            x: 'right',
					            y: 'center',
					            //magicType : {
					                //show: true, 
					                //type: ['line', 'bar']
					           // },
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					             {
					                 type : 'category',
					                 data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
					             }
					         ],
					         yAxis : [
					             {
					                 type : 'value'
					             }
					         ],
					    series :objArray
					    /*[
					        {
					            name:'事件统计',
					            type:'bar',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data: objArray
					        }
					    ]*/        	
                	};
                 	myChart.setOption(options); //先把可选项注入myChart中  
                  	myChart.hideLoading();  
                }              
                </script>
                <script type="text/javascript">
                	function getChartData() {
                		var objArr = [];
                		//通过AJAX获取数据
                		$.ajax({
                			type : "get",
                			async : false,
                			url : "admin/eventStatistical/statistical_getStatistical.action?eventUnitId=" + unitId + "&year=" + year,
                			data : {},
                			dataType : "json",
                			success : function(jsonArray) {
                				if(jsonArray.length > 0){
                					objArr = jsonArray;
                					}  else {
                    					myAlert("该单位本月无事件发生");  
                    					objArr = jsonArray;
                    				}          			
                			} ,
                			error : function(errorMsg) {  
                   				myAlert("图表请求数据失败啦!");  
               				 } 
                		});
                		return objArr;
                	};
                    function myRefresh(){
        				year = document.getElementById("datepicker");
        				//var arr = text.value.split("/");
        				//year = arr[0];
        				//month = arr[1];
        				alert(year);
        				//alert(month);
        				var objArr = getChartData();
                    	var options = myChart.getOption();
        				$.each(objArr,function(index,value){
        					var arrValue = new Array;
    						arrValue[0] = value['count1'];
    						arrValue[1] = value['count2'];
    						arrValue[2] = value['count3'];
    						arrValue[3] = value['count4'];
    						arrValue[4] = value['count5'];
    						arrValue[5] = value['count6'];
    						arrValue[6] = value['count7'];
    						arrValue[7] = value['count8'];
    						arrValue[8] = value['count9'];
    						arrValue[9] = value['count10'];
    						arrValue[10] = value['count11'];
    						arrValue[11] = value['count12'];
                			options.legend.data[index] = value['typeName'];
                			options.series[index].name=value['typeName'];
                			options.series[index].type = 'bar';
                			options.series[index].stack = '总量';
                			options.series[index].itemStyle =  { normal: {label : {show: true, position: 'insideRight'}}};
                			options.series[index].data = arrValue;
                		} 	);
                		myChart.setOption(options);    
        				myChart.hideLoading();  
   					 }    
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
	<style type="text/css">
		div#main{
			height:400px;
			width:750px;
			border:1px solid #ccc;
			margin-left: auto;
			margin-right: auto;
			margin-top: 10px
		}
		div#down{
			margin-top: 15px;
		}
		ul#mytab{
			margin-top:5px; 
			margin-left:5px;
			margin-right:5px 
		}
		div#myTabContent{
			margin-left:5px;
			margin-right:5px;

		}
		#datepicker{
			height: 34px;
			
			margin-top: auto;	
			margin-bottom: auto;
		}
		#date{
			float:left;
			margin-left:20px;
		}
		#mybtn{
			margin-left:5px;
			float: left;
		}
		.mypanel{
			margin-top: 10px;
		}
		.mytext{
			color: white;
		}
		.myimage{
			
			color: white;
		}
	
	</style>
	
  </head>
  
  <body>
  	<ul class="nav nav-tabs"  id="mytab">
  		<li class="active"><a href="#graphic" data-toggle="tab">统计数据</a></li>
  		<li ><a href="#alarmInfo" class="info" data-toggle="tab">报警信息</a></li>		
  	</ul>
  	<div id="myTabContent"  class="tab-content ">
  	<div id="graphic" role="tabpanel"  class="tab-pane fade  in active">
    
                <div id="main"></div>
    		
                <div id= "down"  >
                	<div id="date">
                     <input type="text" id="datepicker"  class="form-control" placeholder="请选择月份">
                     </div>
                     <div id="mybtn">
					  <button value="button"  class="btn btn-default" onclick="myRefresh()">刷新</button>
					  </div>
                </div>    
               
          
  	  </div>
  	<div id="alarmInfo" role="tabpanel" class="tab-pane fade ">
	  		<div class="panel-group mypanel" id="accordion">  		
	  		</div>
	  	
	  </div>
    	
  	  	
	</div>
	
  </body>
</html>
