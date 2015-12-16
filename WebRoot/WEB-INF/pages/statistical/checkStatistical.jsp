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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=path%>/bootstrap/css/bootstrap-datepicker.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
	<script  type="text/javascript" src="<%=path%>/bootstrap/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>bootstrap/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
	 <style>
	     #main{
	     	margin-top:2px; 
	     	
	     }
	     #mytable{
	     	margin-bottom: 0px;
	     }
         #dh-1{
             text-align: center;
             color: #e8e8e8;
             font-size: large;
             padding-top: 5px;
             padding-bottom: 5px;
        }
        #black{
            background-color: #080808;
            
        }
        #blue{
            background-color: #2e6da4;
            height:75px;
            
        }
        
        #onetd{
        	text-align:center;
        	vertical-align:middle;
        	width: 190px;
        	border-right-width: 0px;
        }
        
        #twotd{
        	vertical-align:middle;
        	border-left-width: 0px;
        }
        
        #mybtn{
        	height: 30px;
        	width: 65px;
        }
        #datepicker{
        	width: 180px;
        	height: 30px;
        }
        	
        #p{
            color: #f5f5f5;
        }
        #pc{
            color: #f5f5f5;
        }
        #tb{
           
           
            text-align: center;
        }
        th{
            background-color: #c0c0c0;
            font-weight: bold;
            color:#2e6da4;
            text-align: center;
        }
    </style>
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
    	//alert(unitId);
    	$(document).ready(function(){
    		var myDate = new Date();
            var year = myDate.getFullYear();
            var month = myDate.getMonth() + 1; 
            $.ajax({
                	type : "get",
                	async : true,
                	url : "admin/checkStatistical/checkStatistical_getCheckStatistical.action?checkUnitId=" + unitId + "&year=" + year + "&month=" + month,
                	data : {},
                		dataType : "json",
                		success : function(jsonArray) {
                			if(jsonArray.length > 0){
                					$("tbody.second").empty();
                					$.each(jsonArray,function(index,value){
                						var time = value['time'];
                						var count = value['count'];
                						var answerCount = value['answerCount'];
                						var noCount = value['noCount'];
                						$("tbody.second").append("<tr>"
                					        + "<td class=\"text-center\">" + time + "</td>"
                							+ "<td class=\"text-center\">" + count + "</td>"
                							+ "<td class=\"text-center\">" + answerCount + "</td>"
                							+ "<td class=\"text-center\">" + noCount + "</td>"
                							+ "</tr>"
                						);
                					});
                			}         			
                	} ,
                	error : function(errorMsg) {  
                   			myAlert("请求数据失败啦!");  
               		} 
              });
              $("#mybtn").on("click",function(){
              		var text = document.getElementById("datepicker");
        			var arr = text.value.split("/");
        			year = arr[0];
        			month = arr[1];
              		$.ajax({
                	type : "get",
                	async : true,
                	url : "admin/checkStatistical/checkStatistical_getCheckStatistical.action?checkUnitId=" + unitId + "&year=" + year + "&month=" + month,
                	data : {},
                		dataType : "json",
                		success : function(jsonArray) {
                			if(jsonArray.length > 0){
                					$("tbody.second").empty();
                					$.each(jsonArray,function(index,value){
                						var time = value['time'];
                						var count = value['count'];
                						var answerCount = value['answerCount'];
                						var noCount = value['noCount'];
                						$("tbody.second").append("<tr>"
                					        + "<td class=\"text-center\">" + time + "</td>"
                							+ "<td class=\"text-center\">" + count + "</td>"
                							+ "<td class=\"text-center\">" + answerCount + "</td>"
                							+ "<td class=\"text-center\">" + noCount + "</td>"
                							+ "</tr>"
                						);
                					});
                			}         			
                	} ,
                	error : function(errorMsg) {  
                   			myAlert("请求数据失败啦!");  
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
	
  </head>
  
  <body>
  <div id="main">
  	<div >
    <table class="table table-bordered table-hover" id="mytable">
        <tr id="black">
            <td colspan="2"><div id="dh-1"><s:property value="unit.unitName"/></div></td>
        </tr>
        <tr id="blue">
            <td id="onetd" valign="middle">
	              <input type="text" id="datepicker" class="form-control" >
	         </td>
	         <td id="twotd">
				  <button value="button" class="btn btn-info " id="mybtn">查询</button>
            </td>
            
        </tr>
    </table>
</div>
<div id="tb">
    <table class="table table-bordered table-hover" id="">
    	<thead>
        <tr>
            <th>时间</th>
            <th>查岗次数</th>
            <th>回应次数</th>
            <th>未回应次数</th>
        </tr>
        </thead>
        <tbody class="second">
        
        </tbody>
    </table>
  </div>
</div>


	
	               
	
	  	

	
  </body>
</html>
