<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单选择</title>
<script type="text/javascript" src="<%=path%>/js/wtree.js"></script>
<link href="<%=path%>/css/dtree1.css" rel="stylesheet" type="text/css"></link>
<style type="text/css">
	#allpower{float:left;
                width:400px; border:1px solid #F00;height:500px}
    #divoperator{
    			                float:left;
                width:50px;
                padding:60px 5px;
            }
    
    #divoperator input[type="button"]{
                margin:10px 0;
                float:left
            }
	#mypower{float:left;
                width:200px;
                border:1px solid #F00;height:300px}
</style>
</head>
<body onload="getc()">
<center>
	<h1>菜单动态分配</h1>
</center>
当前选中角色：${session.role.roleName}
<div id="allpower"></div>
<script type="text/javascript">
	function sel(){
		var selids=d.getCheckedNodes();
		var str="";
		for(var n=0; n<selids.length; n++){
			str+=selids[n]+",";
		}
		window.parent.location.href='<%=path%>/admin/admin_setpower.action?car='+str+'&target=cc';
	}
	function getc(){
		<s:iterator value="#session.setmenus">	
		d.co(<s:property value="menuMid"/>).checked=true;
		   			</s:iterator>
		
				
	}
	var d = new dTree('d','<%=path%>/images/menu/');
	d.config.folderLinks=true;
	d.config.useCookies=false;
	d.config.check=true;
	
		d.add(0,-1,'系统菜单',"javascript:;",'提示');
	
		<s:iterator value="#session.listmenus">				
   		  	d.add(<s:property value="menuMid"/>,<s:property value="menuPid"/>,'<s:property value="menuName"/>','','','right');	
   			</s:iterator>
		 
	document.getElementById('allpower').innerHTML = d;
	var funcs = eval("("+"{funcs:[{menudm:'0'},{menudm:'100'},{menudm:'790'},{menudm:'800'},{menudm:'810'}]}"+")");
	for(var n=0; n<funcs.funcs.length;n++){
		d.co(funcs.funcs[n].menudm).checked=true;
	}
</script>

<input type="button" value="保存更改" onclick="sel()"/>
</body>
</html>