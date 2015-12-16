/**
 * 测试行选中执行的回调函数
 */

function picself(picpath){
	wx_winpage(picpath,{width:500,height:500}) ;
}

function self(path){
	wx_winpage(path,{width:850,height:490}) ;
}
function testcallbacufunc(pageid,reportid,selectedTrObjArr,deselectedTrObjArr)
{
	var str=getTrObjsValue(deselectedTrObjArr);
	if(str!=null&&str!='')
	{
		alert('本次被取消选中的行：'+str);
	}
	str=getTrObjsValue(selectedTrObjArr);
	if(str!=null&&str!='')
	{
		alert('本次选中的行：'+str);
	}
	var allSelectedTrObjsArr=getListReportSelectedTrObjs(pageid,reportid);//取到所有选中的行对象
	str=getTrObjsValue(allSelectedTrObjsArr);
	if(str!=null&&str!='')
	{
		alert('被选中的所有行：'+str);
	}
}

function ry_log_beforesave(pageid,reportid,dataObjArr)
{
	if(dataObjArr!=null)
	{
		var mess='';
		var dataObjTmp;
		for(var i=0;i<dataObjArr.length;i++)
		{//遍历每条本次操作的记录
			dataObjTmp=dataObjArr[i];
			var myupdatetype=dataObjTmp['WX_TYPE'];//本条记录的更新类型，包括add、update、delete三种取值
			//alert(myupdatetype);
			 if(myupdatetype=='update')
			{//对本记录做修改操作
				 mess=dataObjTmp['name']+'修改项:';
				 if(dataObjTmp['name']!=dataObjTmp['name__old']){
					 mess=mess+'原名称'+dataObjTmp['name__old']+','+'新名称'+dataObjTmp['name']+';';
				 }
				 if(dataObjTmp['gjdq']!=dataObjTmp['gjdq__old']){
					 mess=mess+'原国籍'+dataObjTmp['gjdq__old']+','+'新国籍'+dataObjTmp['gjdq']+';';
				 }
				 if(dataObjTmp['hzh']!=dataObjTmp['hzh__old']){
					 mess=mess+'原护照号'+dataObjTmp['hzh__old']+','+'新护照号'+dataObjTmp['hzh']+';';
				 }
				 if(dataObjTmp['qzyxq']!=dataObjTmp['qzyxq__old']){
					 mess=mess+'原签证有效期'+dataObjTmp['qzyxq__old']+','+'新签证有效期'+dataObjTmp['qzyxq']+';';
				 }
			}else if(myupdatetype=='add')
			{//对本记录做添加操作
				mess=mess+dataObjTmp['name']+'|'+dataObjTmp['xb']+'|'+dataObjTmp['gjdq']+'|'+dataObjTmp['hzh']+'|'+dataObjTmp['qzyxq']+'|'+dataObjTmp['dh'];
			}
		}
		//调用框架添加自定义数据的方法传入后台保存时使用
		setCustomizeParamValue(pageid,reportid,'content',mess);
	}	
}

function getTrObjsValue(trObjsArr)
{
	if(trObjsArr==null||trObjsArr.length==0) return '';
	var str='';
	for(var i=0;i<trObjsArr.length;i++)
	{
		var tdChilds=trObjsArr[i].getElementsByTagName('TD');
		for(var j=0;j<tdChilds.length;j++)
		{
			var name=tdChilds[j].getAttribute('name');//获取当前列对应的<col/>的列名
			var value=tdChilds[j].getAttribute('value');//获取选中行的当前列的数据
			if(name&&name!='')
			{
				str=str+'[列名：'+name+'；列值：'+value+']';
			}
		}
	}
	return str;
}
function tests(datasObj){
	
	//refreshComponentDisplay(datasObj.pageid,null,true);//刷新整个页面
	 //wx_closePopupWin();
	//parent.ymPrompt.doHandler('close',null);
	parent.location.reload();
}
function getBz(datasObj){
	
	//refreshComponentDisplay(datasObj.pageid,null,true);//刷新整个页面
	//wx_closePopupWin();
	//parent.ymPrompt.doHandler('close',null);
	alert('');
}
function reloads(pageid){
	alert(pageid);
	refreshComponentDisplay(pageid,null);//刷新整个页面
}
function testTypePromptCallBack(textObj,colvaluesArr)
{
	if(textObj==null) return;
	var selectValus='';
	for(var i=0;i<colvaluesArr.length;i++)
	{
		selectValus+='[label:'+colvaluesArr[i].label+';value:'+colvaluesArr[i].value+']';
	}
	wx_alert('选中选项各列的值：'+selectValus);
	//执行此报表的搜索操作
	searchReportData('typepromptpage4','report1');
	/**
	 * 如果不想象上面一样写死要搜索的报表所在页面ID和报表本身ID（即这里的typepromptpage4和report1），可以从textObj.id中取到这两个值，因为框架为输入框分配的id中自动包括它所在的报表ID和报表所在的页面ID。
	 * 这对想写一个统一的功能非常有用，不需每个报表去写一个这种方法进行自动搜索。
	 */
}

function otherdata_clientpage1_beforesave(pageid,reportid,dataObjArr)
{
	if(dataObjArr!=null)
	{
		var mess='';
		var dataObjTmp;
		for(var i=0;i<dataObjArr.length;i++)
		{//遍历每条本次操作的记录
			dataObjTmp=dataObjArr[i];
			var myupdatetype=dataObjTmp['WX_TYPE'];//本条记录的更新类型，包括add、update、delete三种取值
			//alert(myupdatetype);
			if(myupdatetype=='delete')
			{//对本记录做删除操作
				mess=mess+'删除的部门编号：'+dataObjTmp['deptno__old'];
				mess=mess+';;;删除的部门名：'+dataObjTmp['deptname__old'];
			}else if(myupdatetype=='update')
			{//对本记录做修改操作
				mess=mess+'修改的旧部门编号：'+dataObjTmp['deptno__old']+';;;新部门编号：'+dataObjTmp['deptno'];
				mess=mess+';;;修改的旧部门名：'+dataObjTmp['deptname__old']+';;;新部门名：'+dataObjTmp['deptname'];
			}else if(myupdatetype=='add')
			{//对本记录做添加操作
				mess=mess+'添加的部门编号：'+dataObjTmp['deptno'];
				mess=mess+';;;添加的部门名：'+dataObjTmp['deptname'];
			}
		}
		//调用框架添加自定义数据的方法传入后台保存时使用
		var now=new Date();
		var yy=now.getYear();
		if(yy<1000) yy=yy+1900;
      var currentdate=yy+'-'+(now.getMonth()+1)+'-'+(now.getDay()+2)+' 00:00:00.0';
		setCustomizeParamValue(pageid,reportid,'testdate',currentdate);
		setCustomizeParamValue(pageid,reportid,'logcontent',mess);
	}	
}

function refreshreportapipage1Onload(pageid,componentid)
{
	if(pageid!='refreshreportapipage1') return;
	if(componentid==null||componentid=='refreshreportapipage1')
	{
		alert('加载完整个页面');
	}else if(componentid=='report1')
	{
		alert('加载完报表一');
	}else if(componentid=='report2')
	{
		alert('加载完报表二');
	}
}

function testGetReportColData(pageid,reportid,reporttype,columns,conditions)
{
	var datasObj=getEditableReportColValues(pageid,reportid,reporttype,columns,conditions);
	if(datasObj==null)
	{
		wx_alert('没有获取到数据');
	}else if(isArray(datasObj))
	{
		var dataObjTmp;
		for(var i=0;i<datasObj.length;i=i+1)
		{
			dataObjTmp=datasObj[i];
			var str='';
			for(var key in dataObjTmp)
			{
				str=str+' name:'+dataObjTmp[key].name+';value:'+dataObjTmp[key].value+';oldname:'+dataObjTmp[key].oldname+';oldvalue:'+dataObjTmp[key].oldvalue+'\n';
			}
			alert(str);
		}
	}else
	{
		var str='';
		for(var key in datasObj)
		{
			str=str+' name:'+datasObj[key].name+';value:'+datasObj[key].value+';oldname:'+datasObj[key].oldname+';oldvalue:'+datasObj[key].oldvalue+'\n';
		}
		alert(str);
	}
}

function scry(pageid,reportid,reporttype,columns,conditions)
{
	var datasObj=getEditableReportColValues(pageid,reportid,reporttype,columns,conditions);
	var allSelectedTrObjsArr=getListReportSelectedTrObjs(pageid,reportid);//取到所有选中的行对象
	if(datasObj==null)
	{
		wx_alert('请选择删除项');
		return;
	}else if(isArray(datasObj))
	{
		var dataObjTmp;
		var str='';
		for(var i=0;i<datasObj.length;i=i+1)
		{
			dataObjTmp=datasObj[i];
			for(var key in dataObjTmp)
			{
				str=str+dataObjTmp[key].value+',';
			}
		}
		if(str.trim()==','){
			var trs=allSelectedTrObjsArr[0];
			trs.parentNode.removeChild(trs);
			return;
		}
		str=str.substring(0,str.length-1);
		wx_winpage('ShowReport.wx?PAGEID=zzryjjsqksc&ids='+str,{width:900,height:500}) ;
	}else
	{
		var str='';
		for(var key in datasObj)
		{
			str=str+' name:'+datasObj[key].name+';value:'+datasObj[key].value+';oldname:'+datasObj[key].oldname+';oldvalue:'+datasObj[key].oldvalue+'\n';
		}
		alert(str);
	}
}

function testInvokeCallbackMethod1(datasObj)
{
	alert('针对组件'+datasObj.componentid+'调用服务器端操作');
	if(datasObj.returnValue!=null&&datasObj.returnValue!='')
	{
		alert('服务器端返回值：'+datasObj.returnValue);
	}
	var componentguid=getComponentGuidById(datasObj.pageid, datasObj.componentid);//先取到组件GUID
	var datasArr=null;
	if(WX_ALL_SAVEING_DATA!=null) datasArr =WX_ALL_SAVEING_DATA[componentguid];
	if(datasArr!=null&&datasArr.length>0)
	{
		printInvokeParamsData(datasArr);
	}else
	{
		alert('无参数');
	}
	refreshComponentDisplay(datasObj.pageid,null,true);//刷新整个页面
}

function testInvokeCallbackMethod2(xmlHttpObj,datasObj)
{
	alert(xmlHttpObj.responseText);//打印服务器端方法返回的字符串
	if(datasObj==null) return;
	var datasObjArr=convertToArray(datasObj);
	printInvokeParamsData(datasObjArr);
	refreshComponentDisplay('invokeservermethod_alonepage1',null,true);
	//window.location.href='/WabacusDemo/ShowReport.wx?PAGEID=invokeservermethod_alonepage1';//刷新一下此页面，将新插入的记录显示出来
}

function testBeforeInokeCallBackMethod1(datasArray)
{
	if(datasArray==null||datasArray.length==0) 
	{
		alert('没有取到报表数据，取消调用操作');
		return false;//没有取到报表数据，则不调用后台操作
	}
	alert('执行调用前置动作......');
	//printInvokeParamsData(datasArray);
	return true;
}

function printInvokeParamsData(datasObjArr)
{
	if(datasObjArr==null||datasObjArr.length==0) return;
	for(var i=0;i<datasObjArr.length;i++)
	{
		var str='';
		for(key in datasObjArr[i])
		{
			str=str+'参数名：'+key+'，参数值：'+datasObjArr[i][key]+'；';
		}
		if(str!='') alert(str);
	}
}

function testInvokeCallbackMethod3(datasObj)
{
	refreshComponentDisplay('invokeservermethod_sqlpage1',null,true);//刷新整个页面
}

function testInvokeCallbackMethod_autodbpage11(datasObj)
{
	alert('针对组件'+datasObj.componentid+'调用服务器端操作');
	if(datasObj.returnValue!=null&&datasObj.returnValue!='')
	{
		alert('服务器端返回值：'+datasObj.returnValue);
	}
	var componentguid=getComponentGuidById(datasObj.pageid, datasObj.componentid);//先取到组件GUID
	var datasArr=null;
	if(WX_ALL_SAVEING_DATA!=null) datasArr =WX_ALL_SAVEING_DATA[componentguid];
	if(datasArr!=null&&datasArr.length>0)
	{
		printInvokeParamsData(datasArr);
	}else
	{
		alert('无参数');
	}
	refreshComponentDisplay(datasObj.pageid,'vp1',true);//刷新vp1容器
}
function testInvokeCallbackMethod_autodbpage12(datasObj)
{
	alert('针对组件'+datasObj.componentid+'调用服务器端操作');
	if(datasObj.returnValue!=null&&datasObj.returnValue!='')
	{
		alert('服务器端返回值：'+datasObj.returnValue);
	}
	var componentguid=getComponentGuidById(datasObj.pageid, datasObj.componentid);//先取到组件GUID
	var datasArr=null;
	if(WX_ALL_SAVEING_DATA!=null) datasArr =WX_ALL_SAVEING_DATA[componentguid];
	if(datasArr!=null&&datasArr.length>0)
	{
		printInvokeParamsData(datasArr);
	}else
	{
		alert('无参数');
	}
	refreshComponentDisplay(datasObj.pageid,'vp2',true);//刷新vp2容器
}

function testInvokeCallbackMethod_autodbpage13(datasObj)
{
	alert('针对组件'+datasObj.componentid+'调用服务器端操作');
	if(datasObj.returnValue!=null&&datasObj.returnValue!='')
	{
		alert('服务器端返回值：'+datasObj.returnValue);
	}
	var componentguid=getComponentGuidById(datasObj.pageid, datasObj.componentid);//先取到组件GUID
	var datasArr=null;
	if(WX_ALL_SAVEING_DATA!=null) datasArr =WX_ALL_SAVEING_DATA[componentguid];
	if(datasArr!=null&&datasArr.length>0)
	{
		printInvokeParamsData(datasArr);
	}else
	{
		alert('无参数');
	}
	refreshComponentDisplay(datasObj.pageid,'vp3',true);//刷新vp3容器
}

function myCancelButtonEvent()
{
	alert('点击了\'取消\'按钮，执行取消事件');
}
function prentref(){
	//parent.refreshComponentDisplay('bxmxsq',null,true);
	alert()
}

function addCustomizeDataToButton(datasArr,paramsObj)
{
	if(datasArr==null||datasArr.length==0) return false;//没有传入报表数据，则中止调用
	var nameStrs='';
	for(var i=0;i<datasArr.length;i++)
	{
		var datasObj=datasArr[i];//取到存放当前记录数据的对象
		nameStrs+=datasObj['name'];//取到姓名列的值
	}
	return true;
}

function setValidateErrorMessage(paramsObj)
{
	if(paramsObj.isSuccess===true)
	{
		alert('校验成功');
	}else
	{
		alert('校验失败');
	}
	if(paramsObj.serverDataObj==null||paramsObj.serverDataObj.errormessage==null||paramsObj.serverDataObj.errormessage=='')
	{//服务器端没有返回要显示的数据
		if(paramsObj.validatetype=='onblur')
		{
			document.getElementById('deptnomessid').innerHTML='';
		}
	}else
	{
		if(paramsObj.validatetype=='onblur')
		{
			document.getElementById('deptnomessid').innerHTML="<font color='red' size='2'>"+paramsObj.serverDataObj.errormessage+"</font>";
		}else
		{
			wx_warn(paramsObj.serverDataObj.errormessage);
		}
	}
}
function testConfigLinkedChart(paramsObj)
{
	FusionCharts(paramsObj.chartid).configureLink(
		{
			swfUrl:paramsObj.customizeData.swfUrl,    
			"renderAt":paramsObj.customizeData.targetId,    
			overlayButton: { show : false }
		}
	);
}

function validateRedundantboxpagePwdInput(pageid,reportid,dataObjArr)
{
	for(var i=0;i<dataObjArr.length;i++)
	{
		dataObjTmp=dataObjArr[i];
		if(dataObjTmp['WX_TYPE']=='delete') continue;//当前是在做删除操作
		var u_passwordb=dataObjTmp['u_passwordb'];
		var u_passwordc=dataObjTmp['u_passwordc'];
		if(u_passwordb!=u_passwordc)
		{//两次输入的出生日期不一致
			wx_alert('两次新密码输入不一致，请重新输入！');
			return WX_SAVE_TERMINAT;
		}
	}
	return WX_SAVE_CONTINUE;
}


/**
 * 国籍/地区选中赋值
 * @param textObj
 * @param colValuesArr
 */
function xzgj(textObj,colValuesArr){
	var selectValus='';
	for(var i=1;i< colValuesArr.length;i++) { 
		selectValus+=colValuesArr[i].label;
	}
	document.getElementById("zzryjjsqkAdd_guid_report1_wxcol_gjdq").value=selectValus;
}
/**
 * 国籍/地区选中赋值
 * @param textObj
 * @param colValuesArr
 */
function xzdw(textObj,colValuesArr){
	var selectValus='';
	for(var i=1;i< colValuesArr.length;i++) { 
		selectValus+=colValuesArr[i].label;
	}
	textObj.value=selectValus;
}
/**
 * 护照号选中赋值姓名
 * @param textObj
 * @param colValuesArr
 */
function xzhzh(textObj,colValuesArr){
	var selectValus='';
	for(var i=1;i< colValuesArr.length;i++) { 
		selectValus+=colValuesArr[i].label;
	}
	document.getElementById("wgrqxdjbglAdd_guid_report1_wxcol_name").value=selectValus;
}

/**
 * 刷新页面（查岗）
 */
function refresh() {
	alert("返回到了指定的js中");
	refreshComponentDisplay('checkInfo',null,true);
	window.location.href='/jiance1.0/ShowReport.wx?PAGEID=checkInfo';//刷新一下此页面，将新插入的记录显示出来
}
