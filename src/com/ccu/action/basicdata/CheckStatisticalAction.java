package com.ccu.action.basicdata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.basicdata.UnitInfo;





@Scope("prototype")
@Controller("checkStatisticalAction")
public class CheckStatisticalAction extends BaseAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String month;
	private String unitId;
	private String year;
	private String checkUnitId;
	private UnitInfo unit ;
	

	


	private JSONArray jsonArray = new JSONArray();


	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	public String load() throws Exception {
		System.out.println(unitId);
		setUnit(unitDao.get(unitId));
		return SUCCESS;
	}
	
	public String getCheckStatistical() throws Exception {
		
		UnitInfo unitInfo = unitDao.get(checkUnitId);
		String hql1 =  "select count(*) from CheckInfo where unitInfo=? and year(answerTime)=? and month(answerTime)=?";
		Object[] queryParamsCount1 = {unitInfo,Integer.valueOf(year),Integer.valueOf(month)};	
		String hql2 =  "select count(*) from CheckInfo where unitInfo=? and year(answerTime)=? and month(answerTime)=? and isAnswer=?";
		Object[] queryParamsCount2 = {unitInfo,Integer.valueOf(year),Integer.valueOf(month),true};	
		Object[] queryParamsCount3 = {unitInfo,Integer.valueOf(year),Integer.valueOf(month),false};	
		Long count = (Long) eventDao.uniqueResult(hql1, queryParamsCount1);
		Long answerCount = (Long) eventDao.uniqueResult(hql2, queryParamsCount2);
		Long noCount = (Long) eventDao.uniqueResult(hql2, queryParamsCount3);
		String time = year + "年" + month + "月";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("count", count);
		jsonObject.put("answerCount", answerCount);
		jsonObject.put("noCount", noCount);
		jsonObject.put("time", time);
		jsonArray.add(jsonObject);
		return "json";
	}
	public UnitInfo getUnit() {
		return unit;
	}

	public void setUnit(UnitInfo unit) {
		this.unit = unit;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCheckUnitId() {
		return checkUnitId;
	}

	public void setCheckUnitId(String checkUnitId) {
		this.checkUnitId = checkUnitId;
	}

}
