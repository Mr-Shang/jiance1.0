<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.STYLE7 {font-size: 12}

-->
</style>

<script>
var  highlightcolor='#eafcd5';
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
<script type="text/javascript">

function getc(){
		<s:iterator value="#session.setUser">
			document.getElementById("<s:property value="id"/>").checked=true;	
		  </s:iterator>		
	}
	function sel(){
		var dd = document.getElementsByName("teaids");
		var str="";
		for(var n=0; n<dd.length; n++){
			if(dd[n].checked==true){
			str+=dd[n].value+",";	
		}
		}
		window.parent.location.href='<%=path%>/admin/admin_setTea.action?car='+str+'&target=cc';
	}
</script>
 	 
 	
</head>

<body onload="getc()">
<br/>
<center>
	<h1>用户动态分配</h1>
</center>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      当前选中角色：${session.role.roleName}
<input type="button" value="保存更改" onclick="sel()"/>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
                      <td width="5%" height="18" background="<%=path%>/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"></div></td>
            <td width="12%" height="18" background="<%=path%>/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
            <td width="12%" height="18" background="<%=path%>/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">用户姓名</div></td>
            <td width="8%" height="18" background="<%=path%>/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">用户账号</div></td>

          </tr>
          <s:iterator value="#session.listUser" status="st">
          <tr>
            <td  bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><input type="checkbox" id="<s:property value="id"/>" name="teaids" value="<s:property value="id"/>"/></div></td>
            <td  bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><s:property value="#st.getIndex()+1"/></div></td>
            <td  bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="userName"/></div></td>
            <td  bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="account"/></div></td>
            </tr>
          </s:iterator>
          
         
        </table></td>
        <td width="9" background="<%=path%>/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>      
        </table></td>
        <td width="14"><img src="<%=path%>/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
   
</body>
</html>
