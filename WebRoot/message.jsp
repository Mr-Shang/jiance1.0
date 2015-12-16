<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>信息提示窗口</title>
		<script type="text/javascript">
		//重置浏览器窗口
		window.resizeTo(500,400);
		
		function toCloseWin() {
			if (window.opener != undefined) {
				//window.opener的值在谷歌浏览器下面不为空，在IE/火狐下面是未定义，由此判断是否是谷歌浏览器 
				window.opener.returnValue = '<s:property value="flag"/>'; 
				//下面是在其他的地方摘录的
				//谷歌浏览器下给返回值赋值的方法window.opener.close(); 
				//这里必须关闭一次，否则执行下面的window.close()无法关闭弹出窗口，因为谷歌浏览器下弹出窗口是个新的window 
			} else {
				window.returnValue = '<s:property value="flag"/>'; //这种赋值方法兼容IE/火狐，但不支持谷歌浏览器 
			}
			window.close();
		}
		//关闭窗体的红色X按钮，监听窗口关闭事件
		window.onunload = function unload(){ 
			toCloseWin();
        }
		</script>

	</head>

	<body>
		<div align="center" style="margin-top: 100px;">
			<h2>
				<s:property value="message" />
			</h2>
			<input type="button" value="关闭窗口" style="width: 200px; height: 50px;"
				onclick="toCloseWin()">
		</div>
	</body>
</html>
