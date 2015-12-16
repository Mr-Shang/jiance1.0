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
	
	<script>
        $(function() {
        $( "#datepicker" ).datepicker({
        	
            // altField: "#alternate",   //将选择的日期输出到id为alternate的控件中
          //  altFormat: "MM, yy",    //输出的格式 
          viewMode:"years",
          minViewMode:"months",
          format:"yyyy/mm",
          	autoclose: true
            });
        });
    </script>
	<script type="text/javascript">
                	var unitId = '<s:property value="unitId"/>';
                	/* alert(unitId); */
                	/* var month = $("#theme-select").find("option:selected").text(); */
                	var myDate = new Date();
                	var year = myDate.getFullYear();
                	var month = myDate.getMonth() + 1; 
                	/* alert(year);
                	alert(month); */
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
              			     'echarts/chart/pie',   
              			     'echarts/chart/funnel'// 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表               				 
                         ],DrawEChart
                    ); 
                    function DrawEChart(ec) {
                    	eCharts = ec;
                    	myChart = eCharts.init(document.getElementById('main'));  
                    	var objArr = getChartData();
                    	var objArray = [];
                    	var arr=[];
                    	$.each(objArr,function(index,value){
                						arr.push(value['typeName']);
                						var obj =new Object();
                						obj.value  = value['num'];
                						obj.name =  value['typeName'];
                						objArray.push(obj);
                		} 	);
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
					        data: arr//  '直接访问','邮件营销','联盟广告','视频广告'										        
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
					            data: objArray
					        }
					    ]        	
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
                			url : "admin/eventStatistical/eventStatistical_getEventStatistical.action?eventUnitId=" + unitId + "&year=" + year + "&month=" + month,
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
        				var text = document.getElementById("datepicker");
        				var arr = text.value.split("/");
        				year = arr[0];
        				month = arr[1];
        				//alert(year);
        				//alert(month);
        				var objArr = getChartData();
                    	var options = myChart.getOption();
        				$.each(objArr,function(index,value){
                						options.legend.data[index] = value['typeName'];
                						options.series[0].data[index].value = value['num'];
                						options.series[0].data[index].name = value['typeName'];
                		} 	);
                		myChart.setOption(options);    
        				myChart.hideLoading();  
   					 }             
                </script>
     <script type="text/javascript">
     $(document).ready(function(){
    	 
			$("li a.info").on("click",function(){
				var pageNo;
				var totalPages;
				var stateArr = [];
				var colorArr = ["red","maroon","purple","lime","green","orange","fuchsia","blue","gray"];
				//alert("你好" + unitId);
				$.ajax({
					type : "get",
        			async : true,
        			url : "admin/eventStatistical/eventType_getTypeInfo.action?myUnitId=" + unitId,
        			data : {},
        			dataType : "json",
        			success : function(jsonTypeInfo) {
        				$("div.panel").remove();
        				if(jsonTypeInfo.length > 0){
        				//alert(jsonTypeInfo.length);
	        				$.each(jsonTypeInfo,function(index,value){
	        					stateArr[index] = 0;
	    						var count = value['count'];
	    						var typeName = value['typeName'];
	    						var typeId = value['typeId'];	
	    						
	    						$("div.mypanel").append("<div class=\"panel panel-default\" >"
	            			  			+ "<div class=\"panel-heading \" id=\"myhead" +index + "\"  >"
	            		  				+ "<h4 class=\"panel-title\">"
	            		  				+ "<a class=\"mylink" + index + " \" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#" + typeId + "\" >"		
	            	               		+ "<strong>"
	            	               		+ "<span class=\"mytext \">类型:</span>"
	            	               		+ "<span class=\"mytext \">" + typeName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>"
	            	               		+ "<span class=\"mytext \">数量:</span>" 
	            	               		+ "<span class=\"mytext \">"+ count + "</span>"
	            	               		+ "<span class=\" glyphicon glyphicon-plus\" id=\"myimage" + index + "\"></span>"
	            	               		+ "</strong>"
	            	               		+ "</a>"
	            		  				+ "</h4>"
	            		  			    + "</div>"
	            		  			    + "<div class=\"panel-collapse collapse\" id=\"" + typeId + "\">"
	            		  			    + "<table class=\"table table-striped table-condensed  \">"
	            		  			    + "<thead class=\"mythead\">"
	            		  			    + "<tr>"
	            		  			    + "<th class=\"text-center\"><small>序号</small></th>"
	            		      			+ "<th  class=\"text-center\"><small>事件名称</small></th>"
	            		      			+ "<th  class=\"text-center\"><small>报警源</small></th>"
	            		      			+ "<th  class=\"text-center\"><small>描述</small></th>"
	            		      			+ "<th  class=\"text-center\" ><small>发生地点</small></th>"
	            		      			+ "<th  class=\"text-center\"><small>发生时间</small></th>"
	            		      			+ "</tr>"
	            		      			+ "</thead>"
	            		      			+ "<tbody>"
	            		      			+ "</tbody>"
	            		      			+ "</table>"
	            		      			+ "<ul class=\"pager\" id=\"mypage\">"
	            	  					+  "<li class=\"previous\"><a class=\"afterpage" + index + "\" href=\"javascript:void(0);\" >&larr; 上一页</a></li>"
	            						+ "<li class=\"next\"><a class=\"beforepage" + index + "\" href=\"javascript:void(0);\"  >下一页 &rarr;</a></li>"
	            					    + "</ul>"
	            					    +"</div>"
	            					    +"</div>"
	            		  		); 
	    						
	    						var sid = "#myimage" + index;
	    						$(sid).css({"color":"white","float":"right"});
	    						var pid = "div#myhead" + index;
	    						var did = "#" +  typeId;
	    						$(pid).css({"background-color":colorArr[index]});
	    						var str = "a.mylink" + index; //面目a标签
	    						 
	    						$(str).css({"text-decoration":"none"});
	    						$(did).on("hide.bs.collapse",function(){
	    							$(sid).removeClass("glyphicon-minus");
    								$(sid).addClass("glyphicon-plus");
	    						});
	    						
	    						//$(str).on("click",function(){
	    							
	    						$(did).on("show.bs.collapse",function(){
	    							//if(stateArr[index] == 0) {
	    								$(sid).removeClass("glyphicon-plus");
	    								$(sid).addClass("glyphicon-minus");
		    							$.ajax({
		    								type : "get",
		    			        			async : true,
		    			        			url : "admin/eventStatistical/eventInfo_getEventInfo.action?ourUnitId=" + unitId + "&typeId=" + typeId,
		    			        			data : {},
		    			        			dataType : "json",
		    			        			success : function(jsonEventInfo){
		    			        				$("tbody").empty();
		    			        				//alert(jsonEventInfo.length);
		    			        				if(jsonEventInfo.length > 0){
		    			        					$.each(jsonEventInfo,function(index,value){
		    			        						pageNo = value['pageNo'];
		    				    						totalPages = value['totalPages'];
		    			        						var eventName = value['eventName'];
		    			        						var alertSource  = value['alertSource'];
		    			        						var description  = value['description'];
		    			        						var happenPlace  = value['happenPlace'];
		    			        						var happenTime  = value['happenTime'];
		    			        						var num = index +1;
		    			        						$("tbody").append("<tr>"
		    			        							+ "<td class=\"text-center\"><small>" + num + "</small></td>"
		    			        							+ "<td class=\"text-center\"><small>" + eventName + "</small></td>"
		    			        							+ "<td class=\"text-center\"><small>" + alertSource + "</small></td>"
		    			        							+ "<td class=\"text-center\"><small>" + description + "</small></td>"
		    			        							+ "<td class=\"text-center\"><small>" + happenPlace + "</small></td>"
		    			        							+ "<td class=\"text-center\"><small>" + happenTime + "</small></td>"
		    			        							+ "</tr");		    			        						
		    			        					});
		    			        					stateArr[index] = 1;
		    			        					//alert(pageNo);
		    			    						var  afterPage = "li a.afterpage" + index;//翻页a标签
		    			    						var beforePage = "li a.beforepage" + index;
		    			    						$(afterPage).on("click",function(){
		    				        					if (pageNo == 1) {
		    				        						myAlert("这已经是第一页");
		    				        						return;
		    				        					} else {
		    				        						pageNo = pageNo - 1;
		    				        						$.ajax({
		    				        							type : "get",
		    				    			        			async : true,
		    				    			        			url : "admin/eventStatistical/eventInfo_getEventInfo.action?ourUnitId=" + unitId + "&typeId=" + typeId + "&pageNo=" + pageNo,
		    				    			        			data : {},
		    				    			        			dataType : "json",
		    				    			        			success : function(jsonEventInfo){
		    				    			        				$("tbody").empty();
		    				    			        				//alert(jsonEventInfo.length);
		    				    			        				if(jsonEventInfo.length > 0){
		    				    			        					$.each(jsonEventInfo,function(index,value){
		    				    			        						pageNo = value['pageNo'];
		    				    			        						var eventName = value['eventName'];
		    				    			        						var alertSource  = value['alertSource'];
		    				    			        						var description  = value['description'];
		    				    			        						var happenPlace  = value['happenPlace'];
		    				    			        						var happenTime  = value['happenTime'];
		    				    			        						var num = index +1;
		    				    			        						$("tbody").append("<tr>"
		    				    			        							+ "<td class=\"text-center\"><small>" + num + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + eventName + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + alertSource + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + description + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + happenPlace + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + happenTime + "</small></td>"
		    				    			        							+ "</tr");	
		    				    			        					});
		    				    			        				}
		    				    			        			},
		    				    			        			error : function(errorMsg) {  
		    				    			           				 myAlert("数据请求失败啦!");  
		    				    			       				} ,
		    				        						});
		    				        					}
		    				        				});
		    			    						$(beforePage).on("click",function(){
		    				        					if (pageNo == totalPages) {
		    				        						myAlert("这已经是最后一页");
		    				        						return;
		    				        					} else {
		    				        						pageNo = pageNo + 1;
		    				        						$.ajax({
		    				        							type : "get",
		    				    			        			async : true,
		    				    			        			url : "admin/eventStatistical/eventInfo_getEventInfo.action?ourUnitId=" + unitId + "&typeId=" + typeId + "&pageNo=" + pageNo,
		    				    			        			data : {},
		    				    			        			dataType : "json",
		    				    			        			success : function(jsonEventInfo){
		    				    			        				$("tbody").empty();
		    				    			        				//alert(jsonEventInfo.length);
		    				    			        				if(jsonEventInfo.length > 0){
		    				    			        					$.each(jsonEventInfo,function(index,value){
		    				    			        						pageNo = value['pageNo'];
		    				    			        						var eventName = value['eventName'];
		    				    			        						var alertSource  = value['alertSource'];
		    				    			        						var description  = value['description'];
		    				    			        						var happenPlace  = value['happenPlace'];
		    				    			        						var happenTime  = value['happenTime'];
		    				    			        						var num = index +1;
		    				    			        						$("tbody").append("<tr>"
		    				    			        							+ "<td class=\"text-center\"><small>" + num + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + eventName + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + alertSource + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + description + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + happenPlace + "</small></td>"
		    				    			        							+ "<td class=\"text-center\"><small>" + happenTime + "</small></td>"
		    				    			        							+ "</tr");	
		    				    			        					});
		    				    			        				}
		    				    			        			},
		    				    			        			error : function(errorMsg) {  
		    				    			           				 myAlert("数据请求失败啦!");  
		    				    			       				} ,
		    				        						});
		    				        					}
		    				        				});
		    			        				}
		    			        				
		    			        			},
		    			        			error : function(errorMsg) {  
		    			           				 myAlert("数据请求失败啦!");  
		    			       				} 
		    							
		    							});
	    							//} else {
	    								//stateArr[index] = 0;
	    								//$(sid).removeClass("glyphicon-minus");
	    								//$(sid).addClass("glyphicon-plus");
	    							//}
	    						});

	    					});
	        				
        				} 
        				//objArr = jsonTypeInfo;        			
        			} ,
        			error : function(errorMsg) {  
           				 myAlert("数据请求失败啦!");  
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
