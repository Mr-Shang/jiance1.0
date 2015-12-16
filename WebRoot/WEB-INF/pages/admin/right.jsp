<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
-->
.map{ width:100%; height:100%;}
.map #container{height:100%;}
</style>
<script>
var  highlightcolor='#d5f4fe';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=rvs6TWDsSM7vB6smRanIPIEb">
	</script>
<script language="JavaScript">
window.onload = getMsg;
window.onresize = resizeDiv;
window.onerror = function(){};
//短信提示使用(asilas添加)
var divTop,divLeft,divWidth,divHeight,docHeight,docWidth,objTimer,i = 0;
function getMsg()
{
  try{
  divTop = parseInt(document.getElementById("eMeng").style.top,10);
  divLeft = parseInt(document.getElementById("eMeng").style.left,10);
  divHeight = parseInt(document.getElementById("eMeng").offsetHeight,10);
  divWidth = parseInt(document.getElementById("eMeng").offsetWidth,10);
  docWidth = document.body.clientWidth;
  docHeight = document.body.clientHeight;
  document.getElementById("eMeng").style.top = parseInt(document.body.scrollTop,10) + docHeight + 10;// divHeight
  document.getElementById("eMeng").style.left = parseInt(document.body.scrollLeft,10) + docWidth - divWidth;
  document.getElementById("eMeng").style.visibility="visible";
  objTimer = window.setInterval("moveDiv()",10);
  }
  catch(e){}
}

function resizeDiv()
{
  i+=1;
  if(i>500) closeDiv();
  try{
  divHeight = parseInt(document.getElementById("eMeng").offsetHeight,10);
  divWidth = parseInt(document.getElementById("eMeng").offsetWidth,10);
  docWidth = document.body.clientWidth;
  docHeight = document.body.clientHeight;
  document.getElementById("eMeng").style.top = docHeight - divHeight + parseInt(document.body.scrollTop,10);
  document.getElementById("eMeng").style.left = docWidth - divWidth + parseInt(document.body.scrollLeft,10);
  }
  catch(e){}
}

function moveDiv()
{
  try
  {
  if(parseInt(document.getElementById("eMeng").style.top,10) <= (docHeight - divHeight + parseInt(document.body.scrollTop,10)))
  {
  window.clearInterval(objTimer);
  objTimer = window.setInterval("resizeDiv()",30);
  }
  divTop = parseInt(document.getElementById("eMeng").style.top,10);
  document.getElementById("eMeng").style.top = divTop - 1;
  }
  catch(e){}
}
function closeDiv()
{
  document.getElementById('eMeng').style.visibility='hidden';
  if(objTimer) window.clearInterval(objTimer);
}
</script>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
-->
</style>
<style type="text/css">
	table td
	{
		white-space: nowrap;
	}

</style>
  </head>
  
  <body>
<%-- 
   <div class="map">     	
			<div id="container">
				看到右下角的提示了吗？如果没有看到，<button onclick=location.reload();>刷新</button>一下
			</div> 
				<script type="text/javascript"> 
					var map = new BMap.Map("container");          // 创建地图实例  
					var point = new BMap.Point(125.32799229987, 43.840175236094);  // 创建点坐标  
					map.centerAndZoom(point, 15);// 初始化地图，设置中心点坐标和地图级别  
					var marker = new BMap.Marker(point);        // 创建标注    
					map.addOverlay(marker);// 将标注添加到地图中
					map.addControl(new BMap.NavigationControl());    
					map.addControl(new BMap.ScaleControl());    
					map.addControl(new BMap.OverviewMapControl());    
					map.addControl(new BMap.MapTypeControl()); 
					map.enableScrollWheelZoom();//启用地图滚轮放大缩小
				</script>   	
          </div>
          
          <DIV id=eMeng style="BORDER-RIGHT: #455690 1px solid; BORDER-TOP: #a6b4cf 1px solid; Z-INDEX:99999; LEFT: 0px; VISIBILITY: hidden; BORDER-LEFT: #a6b4cf 1px solid; WIDTH: 380px; BORDER-BOTTOM: #455690 1px solid; POSITION: absolute; TOP: 0px; HEIGHT: 316px; BACKGROUND-COLOR: #c9d3f3">
<table width="100%" border="0" cellpadding="0" style="empty-cells:show;border-collapse:colllapse;margin: 0 auto;" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">  
<TBODY>
    <TR>
      <TD colspan="4" style="FONT-WEIGHT: normal; FONT-SIZE: 12px; BACKGROUND-IMAGE: url(msgTopBg.gif); COLOR: #1f336b; PADDING-TOP: 4px;PADDING-left: 4px" Align="center" width="100%"> <h1><font color=red>短消息提示：</font></h1></TD>
      
    	
    </TR>
    <TR>
        <td width="33%" height="40" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"> 单位名称<br></span></div></td>
       	<td width="33%" height="40" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"> 事件类型<br></span></div></td>
       	<td width="33%" height="40" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"> 事件名称<br></span></div></td>
       	
    </TR>
     <s:iterator value="#session.event">
    <tr>
    	<td height="30" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><a href="<%=path%>/admin/event/event_query?eventid=<s:property value="id"/>"><s:property value="unitName"/></a></span></div></td>
    	<td height="30" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><s:property value="eventType.typeName"/></span></div></td>
    	<td height="30" bgcolor="#FFFFFF" class="STYLE6">&quot;<div align="center"><span class="STYLE19"><s:property value="eventName"/></span></div></td>
    </tr>
    </s:iterator>
    <tr>
    	<td colspan="3" align="center">
    	<input type="button" style="width:100px;height:30px;" value="C R T">
    		<input type="button" style="width:100px;height:30px;" value="打开视频">
    	</td>
    </tr>
  </TBODY>
  </table>
</DIV>        --%>    
	<iframe height="100%" width="100%" border="0" executeResult="true" frameborder="0" src="ShowReport.wx?PAGEID=monitoring"></iframe>
  </body>
</html>
