<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'machineEvaluate.jsp' starting page</title>
    
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
	
    <style>
        body{
            margin: 0px;
            padding: 0px;
        }
 		.row{
 			width:100%;
 			margin-left: 0px;
 			margin-right:0px; 
			
 		}
 		
 		.well{
 			margin-left: 0px;
 			margin-right: 0px;
 			margin-top: 0px;
 			margin-bottom: 15px;
 			
 		}
        #button{
            height: 30px;
            text-align: center;          
        }
        .form-actions{
        	margin-left: 10px;
        	text-align: center;
            line-height: 30px;
        	
        }
        #table{
            width: 100%;
			height:400px;
            background-color: snow;
            border: none;
        }
        #datepicker{
 			height: 30px;
 		}
 		#select{
 			margin-left:19px; 
 			margin-bottom: 15px;
 		}
 		.mylabel{
 			text-align: center;
 			height: 30px;
 			line-height: 30px;
 		}
 		
    </style>	
    <script type="text/javascript">
    $(document).ready(function(){
    		var myDate = new Date();
            var year = myDate.getFullYear();
            var month = myDate.getMonth() + 1; 
            var newTime  = year + "/" +  month;
            $("#datepicker" ).val(newTime);
    	    $("#datepicker" ).datepicker({     	
	          	viewMode:"years",
	          	minViewMode:"months",//months
	          	format:"yyyy/mm",
	          	autoclose: true
            });
            var table = document.getElementById("table");
            table.src =  "admin/evaluate/machineEvaluate_load1.action?year=" + year + "&month=" + month; 
            
    
    });
    
    </script>
  </head>
  
  <body>
<div id="time" class="well">
	<div class="row">
		<div class="mylabel pull-left ">
					  选择月份：
		</div>
   		<div id="date" class="center-block pull-left ">
                    <input type="text" id="datepicker"  class="form-control " placeholder="请选择月份">
    	</div>
    	<div class="form-actions center-block pull-left">
        	<button type="submit" class="btn btn-primary" id="button" onclick="getMethod()">开始提交</button>
   	 	 </div>
              
	</div>
</div>
<div id="select">
       选择评估方式：
        <label>
            <input type="radio" name="xz" id="xz1" value="以单位方式" checked="checked"/>以单位方式
        </label>
        <label>
            <input type="radio" name="xz" id="xz2" value="以设备方式"/>以设备方式
        </label>
    </div>
    <iframe  id="table">

    </iframe>

<script>
    var btn = document.getElementById("button");
    var radio1=document.getElementById("xz1");
    var radio2=document.getElementById("xz2");
    var table = document.getElementById("table");
    function getMethod(){
        var value="";
            if(radio1.checked==true){
                value=radio1.value;
            }else if(radio2.checked == true){
                value = radio2.value;
            }
        xz(value);

    }

    function xz(value) {
		var text = document.getElementById("datepicker");
        var arr = text.value.split("/");
        year = arr[0];
        month = arr[1];
        if (value == "以单位方式") {
            table.src = "admin/evaluate/machineEvaluate_load1.action?year=" + year + "&month=" + month;
        } else {
            table.src = "admin/evaluate/machineEvaluate_load2.action?year=" + year + "&month=" + month;;
        }
    }
</script>

  </body>
</html>
