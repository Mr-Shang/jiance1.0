<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'right_new.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--<link type="text/css" rel="stylesheet" href="css/style_left.css" /> -->
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<style type="text/css">
<!--
body {
	margin: 0;
	padding: 0;
	overflow-x: hidden;
}

html,body {
	height: 100%;
}

img {
	border: none;
}

* {
	font-family: '微软雅黑';
	font-size: 12px;
	color: #626262;
}

dl,dt,dd {
	display: block;
	margin: 0;
}

a {
	text-decoration: none;
}

/*#bg{background-image:url(images/content/dotted.png);}*/
div.main {
	width: 100%;
	height: 100%;
	margin: auto;
	background-image: url(images/manage_04.jpg);
	height: 100%;
}

/*left*/
.leftsidebar_box {
	width: 180px;
	overflow: visible !important;
	margin-left: auto;
	margin-right: auto;
	padding-top: 30px;
}

.leftsidebar_box dt.menu {
	margin-top: 20px;
	background-repeat: no-repeat;
	height: 35px;
	border-bottom-color: ffc90e;
	border-bottom-style: outset;
	border-bottom-width: 1px;
	position: relative;
	line-height: 35px;
	cursor: pointer;
}

.leftsidebar_box dt a.mainlink {
	float: left;
	margin-left: 10px;
	height: 35px;
	color: #fff228;
	font-size: 17px;
	font-family: "微软雅黑";
	font-weight: bold;
	line-height: 35px;
	cursor: pointer;
	background-repeat: no-repeat;
}

.leftsidebar_box dd {
	line-height: 20px;
	padding-left: 60px;
	padding-top: 15px;
}

.leftsidebar_box dd a.sublink {
	line-height: 20px;
	font-size: 14px;
	color: #e6e6e6;
	font-family: "微软雅黑";
	vertical-align: center;
}

.leftsidebar_box dt img.arrow {
	float: left;
	margin-top: 15px;
	margin-left: 15px;
	vertical-align: middle;
}

.leftsidebar_box dt img.menuimg {
	float: left;
}
/*
.system_log dt{background-image:url(images/01.png)}
.custom dt{background-image:url(images/02.png)}
.channel dt{background-image:url(images/03.png)}
.app dt{background-image:url(images/04.png)}
.cloud dt{background-image:url(images/05.png)}
*/
.leftsidebar_box dl dd:last-child {
	padding-bottom: 10px;
}
</style>


</head>

<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0"
	marginwidth="0" marginheight="0">

	<div class="main">
		<div class="leftsidebar_box">
			<s:iterator value="#session.listMM">
				<dl class="mainmenu">
					<dt class="menu"  onClick="changeImage() ">
						<img class="menuimg"
							src="<s:property	value="icon" escape="false" />"> <a
							class="mainlink"> <s:property value="pMenu" /> </a> 
							<img class="arrow" src="images/left/select_xl01.png">
					</dt>
					<s:iterator value="listMenu">
						<dd class="first_dd">
							<a class="sublink"  href= "<%=basePath%><s:property value="menuPath"/>"  target="right">
								<s:property value="menuName" /> </a>
						</dd>
					</s:iterator>
				</dl>
			</s:iterator>
		</div>
	</div>


	<script type="text/javascript">
		//$(".leftsidebar_box dt").css({"background-color":"#3992d0"});
		
		$(".leftsidebar_box dt img.arrow").attr("src",
				"images/left/select_xl01.png");
		$(function() {
			$(".leftsidebar_box dd").hide();
			$(".leftsidebar_box dt.menu").click(
					function() {
				
						//$(".leftsidebar_box dt").css({"background-color":"#3992d0"})
						//$(this).css({"background-color": "#317eb4"});
						$(this).parent().find('dd').removeClass("menu_chioce");
						$(".leftsidebar_box dt img.arrow").attr("src",
								"images/left/select_xl01.png");
						$(this).parent().find('img.arrow').attr("src",
								"images/left/select_xl.png");
						$(".menu_chioce").slideUp();
						$(this).parent().find('dd').slideToggle();
						$(this).parent().find('dd').addClass("menu_chioce");
					});
		});
	</script>

</body>
</html>
