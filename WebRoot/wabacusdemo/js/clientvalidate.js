
/**
 * 验证是否是合法的年龄
 */
function isValidAge(strAge, boxObj,paramsObj) {
	var age = null;
	try {
		age = parseInt(strAge);
	}
	catch (e) {
		return false;
	}
	if (age <= 0 || age >= 200) {
		return false;
	}
	return true;
}

function isNotEmpty2(value,boxObj,paramsObj)
{
	printMethodParams(paramsObj.isNotEmpty2_methodparams);//在配置文件中传入的参数对象
	printBussinessData(paramsObj.datasObj);
	return isNotEmpty(value,boxObj,paramsObj);
}

function isNumeric2(value,boxObj,paramsObj)
{
	printMethodParams(paramsObj.isNumeric2_methodparams);//在配置文件中传入的参数对象
	printBussinessData(paramsObj.datasObj);
	return isNumeric(value,boxObj,paramsObj);
}

/**
 * 打印在配置文件中为校验方法指定的参数
 */
function printMethodParams(paramsObj)
{
	if(paramsObj==null) return;
	var str='';
	for(var key in paramsObj)
	{
		str+=key+'='+paramsObj[key]+';';
	}
	if(str.length>0) alert('配置文件传入的参数为：'+str);
}

function printBussinessData(datasObj)
{
	if(datasObj==null) return;
	var str='';
	for(var key in datasObj)
	{
		str+=key+'='+datasObj[key]+';';
	}
	if(str.length>0) alert('业务数据参数为：'+str);
}