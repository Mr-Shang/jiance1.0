<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.wabacus.system.ReportRequest"%>
<%@ taglib uri="wabacus" prefix="wx"%>
<%
	ReportRequest rrequest=(ReportRequest)request.getAttribute("WX_REPORTREQUEST");
	String txtage=rrequest.getStringAttribute("txtage","");//取出输入框的条件值
	String txtname=rrequest.getStringAttribute("txtname","");
	txtname=txtname==null?"":txtname.trim();
%>	
工号：<wx:searchbox condition="txtgonghao"/><!-- 框架提供的输入框 -->
姓名：<wx:searchbox condition="txtname"><!-- 用户自定义输入框 -->
		 	<textarea id="txtname_id" onblur="wx_jsvalidateOnblur('searchboxpage3','report1',this,true)"><%=txtname%></textarea>		 	
	  </wx:searchbox>
	  <hr/>
	  <wx:searchbox condition="txtage"><!-- 用户自定义输入框 -->
			年龄：<input id="txtage_id" type="text"  onblur="wx_jsvalidateOnblur('searchboxpage3','report1',this,true)" value="<%=txtage%>">&nbsp;&nbsp;
	  </wx:searchbox>
	  <wx:button type="search"/>
	  <br/><br/>
	  <wx:title/>
	  <wx:data/><br/>
	  <wx:navigate/>
<hr/>
<font color="red">说明：</font><font color="blue">“姓名”、“年龄”两个查询条件的自定义输入框均实现了不允许为空的客户端校验功能</font>	  
