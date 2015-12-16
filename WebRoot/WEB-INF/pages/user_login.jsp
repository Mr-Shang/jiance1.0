<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	    <style>
        body{
            padding: 0px;
            margin: 0px;
        }
        #header{
            width: 1440px;
            height: 216px;
            background:url("images/MangerLogin_01.jpg");
            position: relative;
        }
        #container{
            width: 1440px;
            height: 397px;
        }
        #left{
            width: 194px;
            height: 397px;
            background:url("images/MangerLogin_02.jpg");
            position: relative;
            float: left;
        }
        #left_1{
            width: 12px;
            height: 397px;
            background:url("images/MangerLogin_03.jpg");
            position: relative;
            float: left;
        }
        #main{
            float: left;
            width: 328px;
            height: 397px;
        }
        #main_01{
            width: 328px;
            height: 93px;
            background: url("images/MangerLogin_04.jpg");
            position: relative;
        }
        #main_02{
            width: 328px;
            height: 129px;
            background: url("images/MangerLogin_07.jpg");
            position: relative;
        }
        #main_03{
            width: 328px;
            height: 55px;
            background: url("images/MangerLogin_08.jpg");
            position: relative;
            text-align: center;
            color:#000;
            font-weight:bold;
            font-size:15px;
        }
        #main_04{
            width: 328px;
            height: 60px;
            background: url("images/MangerLogin_09.jpg");
            position: relative;
        }
        #main_05{
            width: 328px;
           height: 60px;
            background: url("images/MangerLogin_10.jpg");
            position: relative;
        }
        #username{
            width: 290px;
            height: 40px;
            background:none transparent scroll repeat 0% 0%;
            margin-left: 16px;
            margin-top: 16px;
            border-radius: 5px;
            text-align: center;
            font-size: 20px;
        }
        #password{
            width: 290px;
            height: 40px;
            background:none transparent scroll repeat 0% 0%;
            margin-left: 16px;
            margin-top: 15px;
            border-radius: 5px;
            text-align: center;
            font-size: 20px;
        }
        #login{
            color: white;
            font-size: 25px;
    	 background:none transparent scroll repeat 0% 0%;
            text-align: center;
            width: 290px;
            height: 40px;
            margin-left: 18px;
            margin-top: 8px;
            border: none;
            border-radius: 5px;
        	outline: 0px; 
        }
        #left_2{
            width: 48px;
            height: 397px;
            background: url("images/MangerLogin_05.jpg");
            float: left;
            position: relative;
        }
        #right{
            width: 858px;
            height: 397px;
            background: url("images/MangerLogin_06.jpg");
            position: relative;
            float: left;
        }
        #footing{
            width: 1440px;
            height: 173px;
        }
        #footing_on{
            width: 1440px;
            height: 85px;
            background: url("images/MangerLogin_11.jpg");
            position: relative;
        }
        #footing_up{
            width: 1440px;
            height: 88px;
            background: url("images/MangerLogin_12.jpg");
            position: relative;
        }
    </style>
  </head>
  
  <body>

<form action="admin/user/user_logon.action" name="dlform" method="post">
		    <div id="header"></div>
    <div id="container">
        <div id="left"></div>
        <div id="left_1"></div>
        <div id="main">
            <div id="main_01"></div>
            <div id="main_02">
                <input type="text" id="username" name="account" placeholder="username" autofocus/>
                <input type="password" id="password" name="password" placeholder="password"/>
            </div>
            <div id="main_03">
            <s:property value="errors.error1[0]"/>
            </div>
            <div id="main_04">
                <input type="button" id="login" onclick="document.dlform.submit()" value="登陆"/>
            </div>
            <div id="main_05"></div>
        </div>
        <div id="left_2"></div>
        <div id="right"></div>
    </div>
    <div id="footing">
        <div id="footing_on"></div>
        <div id="footing_up"></div>
    </div>
</form>
</html>