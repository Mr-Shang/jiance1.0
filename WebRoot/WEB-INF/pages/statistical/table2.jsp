<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'table2.jsp' starting page</title>
    
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
	<!-- <script src="jAlert-master/src/jAlert-v3.js"></script> -->
	<script src="<%=basePath%>jAlert-master/src/jAlert-v3.min.js"></script>
	<script src="<%=basePath%>jAlert-master/src/jAlert-functions.min.js"></script> 
	<script type="text/javascript">
	var totalPages = 0;
	var pageNo = 1;
	$(document).ready(function(){	 	
		 	var month = '<s:property value="month"/>';
		 	var year = '<s:property value="year"/>';

		 	//alert(month + "/" + year);
		 	myLoad(pageNo,year,month); 
		 			 	 require.config({
            		paths: {
               			echarts: '<%=path%>/js/dist'
         			}
        	 });
        		    
        	require(
          		[
               		'echarts',
              		'echarts/chart/pie',   
              		'echarts/chart/funnel'// 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表               				 
                ],DrawEChart
              )
		 	$("li a.beforepage").on("click",function(){
		 		if (pageNo == totalPages) {
		    			myAlert("这已经是最后一页");
		    			return;
		    	} else {
		    		  pageNo = pageNo + 1;
		    		  myLoad(pageNo,year,month) ;
		    	}
		 	});
		 	$("li a.afterpage").on("click",function(){
	              if (pageNo == 1) {
		    		  myAlert("这已经是第一页");
		    		  return;
		    	  } else {
		    	  	  myLoad(pageNo,year,month) ;
		    	  }
		 	 });	
		 });
		 function DrawEChart(ec) {
               eCharts = ec;
               myChart = eCharts.init(document.getElementById('main'));  
               myChart.showLoading({  
                	text : "图表数据正在努力加载..."  
            	});                	
                var options = {
                	 title : {
        					text: '事件统计',
        					x:'center'
   					  },
					     tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					   },
					   legend: {
					        orient : 'vertical',
					        x : 'left',
					        data: ['火警','故障','启动','反馈','监管','屏蔽','消音','复位']									        
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            magicType : {
					                show: true, 
					                type: ['pie', 'funnel'],
					                option: {
					                    funnel: {
					                        x: '25%',
					                        width: '50%',
					                        funnelAlign: 'left',
					                        max: 1548
					                    }
					                }
					            },
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    series : [
					        {
					            name:'事件统计',
					            type:'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data: [{value:0,name:'火警'},
					            {value:0,name:'故障'},
					            {value:0,name:'启动'},
					            {value:0,name:'反馈'},
					            {value:0,name:'监管'},
					            {value:0,name:'屏蔽'},
					            {value:0,name:'消音'},
					            {value:0,name:'复位'},
					         ]
					        }
					    ]        	
                	};
                 	myChart.setOption(options); //先把可选项注入myChart中  
                  	myChart.hideLoading();  
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
		
		function myLoad(pageNo,year,month) {	
			$.ajax({
				url: "admin/evaluate/machineEvaluate_getUnitEventCount.action?year=" + year + "&month=" + month + "&pageNo" + pageNo,
				method:'get',
				dataType:'json',
				contentType:"application/json; charset=utf-8",
				cache:false,
				success:function(unitJSONArray){
					//alert(unitJSONArray.length);
					if(unitJSONArray.length != 0){
						$("tbody#eventinfo").empty();
						$.each(unitJSONArray,function(index,value){
							var unitId = value['unitId'];
							var unitName = value['unitName'];
							var num = index + 1;
							var count = value['count'];
							var count1 = value['count1'];
							var count2 = value['count2'];
							var count3 = value['count3'];
							var count4 = value['count4'];
							var count5 = value['count5'];
							var count6 = value['count6'];
							var count7 = value['count7'];
							var count8 = value['count8'];
							totalPages = value['totalPages'];
							var  aid = "a#" + unitId;
							$("tbody#eventinfo").append(
								"<tr>"
								+ "<td><small>" + num + "</small></td>"
								+ "<td><a id=\"" + unitId + "\" href=\"javascript:void(0);\" data-toggle=\"collapse\" data-target=\"#graphic\"><small>" + unitName + "</small></a></td>"
								+ "<td><small>" + count1 + "</small></td>"
								+ "<td><small>" + count2 + "</small></td>"
								+ "<td><small>" + count3 + "</small></td>"
								+ "<td><small>" + count4 + "</small></td>"
								+ "<td><small>" + count5 + "</small></td>"
								+ "<td><small>" + count6 + "</small></td>"
								+ "<td><small>" + count7 + "</small></td>"
								+ "<td><small>" + count8 + "</small></td>"
								+ "<td><small>" + count + "</small></td>"
								+ "</tr>"
							);  
							$(aid).on("click",function(){
								//$(aid).text();
								
								//alert(machineName);
								$.ajax({
									url: "admin/evaluate/machineEvaluate_getCountByUnit.action?year=" + year + "&month=" + month + "&unitId=	" + unitId,
									method:'get',
									dataType:'json',
									contentType:"application/json; charset=utf-8",
									cache:false,
									success:function(count2JSONArray){
										if(count2JSONArray.length > 0) {
											var options = myChart.getOption();
											$.each(count2JSONArray,function(index,value){
		                						options.legend.data[index] = value['typeName'];
		                						options.series[0].data[index].value = value['eventCount'];
		                						options.series[0].data[index].name = value['typeName'];
	                						} 	);
	                						myChart.setOption(options);    
        									myChart.hideLoading();  
	                					}
									},
									error:function(res){
										myAlert("请求数据失败","error");
									}
								});	
							});
						});		
					}
				},
				error:function(res){
					myAlert("请求数据失败","error");
				}
			});
			
		}
	
	</script>
    <style>
       #tr th{
            text-align: center;
            vertical-align: middle;
            background-color: #1c94c4;

        }
        thead th{
            background-color: #1c94c4;
            color: white;
            text-align: center;
        }
        
        #mypage{
        	margin-left: 19px;
        	margin-right: 19px;
        }
         div#main{
			height:300px;
			width:600px;
			border:1px solid #ccc;
			margin-left: auto;
			margin-right: auto;
			margin-top: 5px;
			margin-bottom: 5px;
		}
		
    </style>
  </head>
  
  <body>
  	<div id="graphic" class="collapse">
		<div id = "main">
		
		</div>
	</div>
       <table class="table table-bordered table-hover table-condensed">
       <thead>
        <tr id="tr">
            <th rowspan="2"><small>序号</small></th>
            <th rowspan="2" ><small>单位名称</small></th>
            <th colspan="8" ><small>报警类型</small></th>
            <th rowspan="2" ><small>报警总数</small></th>
        </tr>
        <tr>
            <th><small>火警</small></th> 
            <th><small>故障</small></th>
            <th><small>启动</small></th>
            <th><small>反馈</small></th>
            <th><small>监管</small></th>
            <th><small>屏蔽</small></th>
            <th><small>消音</small></th>
            <th><small>复位</small></th>
        </tr>
        </thead>
        <tbody style="text-align: center" id="eventinfo">
        </tbody>
    </table>
    <ul class="pager" id="mypage">
  					<li class="previous"><a class="afterpage"href="javascript:void(0);" >&larr; 上一页</a></li>
					<li class="next"><a class="beforepage" href="javascript:void(0);"  >下一页 &rarr;</a></li>
	</ul>
  </body>
</html>
