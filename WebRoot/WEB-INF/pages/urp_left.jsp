<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=path%>/css/admin.css" type="text/css" rel="stylesheet">
<SCRIPT language=javascript>
	function expand(el)
	{
		childObj = document.getElementById("child" + el);

		if (childObj.style.display == 'none')
		{
			childObj.style.display = 'block';
		}
		else
		{
			childObj.style.display = 'none';
		}
		return;
	}
</SCRIPT>
<script type="text/javascript">
	function roname(){
		var role=document.getElementById("roid").value;
        var role_span=document.getElementById("spanroleid");	
        if(role==""){
        	role_span.innerHTML="<font color='red'>*请输入角色名称</font>";
        		return false;
        	}else{
        		role_span.innerHTML="<font color='green'>*角色可用</font>";
        	return true;
        }
	}
	function valiAll(){
		return roname();
		}
</script>
</HEAD>
<BODY background=<%=path%>/images/menu_bg.jpg>
<br/>
<TABLE  cellSpacing=0 cellPadding=0 width=170 
 border=0>
 <tr>
 	<td align="center"><b><h2>角色列表</h2></b></td>
 </tr>
<s:iterator value="#session.roList">	
  <TR>
    <TD vAlign=top align=middle>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR><TD height=10></TD></TR>
      </TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=22>
          <TD style="PADDING-LEFT: 30px" background=<%=path%>/images/menu_bt.jpg><A 
            class=menuParent onclick=expand(1) target="cc"
            href="<%=path%>/admin/admin_rPower.action?rid=<s:property value="id"/>"><s:property value="roleName"/></A>
            </TD>
        </TR>
      </TABLE>
     </TD>
   </TR>
 </s:iterator>

</TABLE>
</BODY>
</HTML>
