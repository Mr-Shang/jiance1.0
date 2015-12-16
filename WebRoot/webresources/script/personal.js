function getReportColData(pageid, reportid, reporttype, columns, conditions) {
	var datasObj = getEditableReportColValues(pageid, reportid, reporttype,
			columns, conditions);
	if (datasObj == null) {
		wx_alert('没有获取到数据');
	} else if (isArray(datasObj)) {
		alert("-第一个参数--" + pageid + "-第二个参数--" + reportid + "--第三个参数--"
				+ reporttype.value + "--第四个参数--" + columns.value + "--第五个参数--"
				+ conditions);
		var dataObjTmp;
		for ( var i = 0; i < datasObj.length; i = i + 1) {
			dataObjTmp = datasObj[i];
			var str = '';
			for ( var key in dataObjTmp) {
				str = str + '查岗单位名称:' + dataObjTmp[key].value + '\n';
			}
			alert(str);
		}
	} else {
		var str = '';
		for ( var key in datasObj) {
			str = str + ' name:' + datasObj[key].name + ';value:'
					+ datasObj[key].value + ';oldname:' + datasObj[key].oldname
					+ ';oldvalue:' + datasObj[key].oldvalue + '\n';
		}
		alert(str);
	}
}