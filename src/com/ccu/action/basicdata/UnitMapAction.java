package com.ccu.action.basicdata;

import java.net.URLDecoder;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;

import com.ccu.model.basicdata.EventInfo;
import com.ccu.model.basicdata.UnitInfo;



@Scope("prototype")
@Controller("unitMapAction")
public class UnitMapAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String unitId;
	private UnitInfo unitInfo;
	private String unitName;
	private JSONArray jsonArray = new JSONArray();
	private JSONArray siteJSONArray = new JSONArray();
	private JSONArray nameJSONArray = new JSONArray();

	public JSONArray getSiteJSONArray() {
		return siteJSONArray;
	}

	public void setSiteJSONArray(JSONArray siteJSONArray) {
		this.siteJSONArray = siteJSONArray;
	}

	public JSONArray getNameJSONArray() {
		return nameJSONArray;
	}

	public void setNameJSONArray(JSONArray nameJSONArray) {
		this.nameJSONArray = nameJSONArray;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	public String load() throws Exception {
		unitInfo = unitDao.get(unitId);
		System.out.print(unitInfo.getUnitSite());
		return SUCCESS;
	}
	
	public String draw() throws Exception{
		return "info";
	}
	
	public String getMapData() throws Exception {
		String where = "where unitType=?";
		Object[] queryParams = {"联网单位"};
		
		List<UnitInfo> list = unitDao.find(-1, -1,where,queryParams).getList();
		System.out.println(list.size());
		if(list.size() > 0){
			for(UnitInfo unitInfo:list){
				String unitId = unitInfo.getId();
				String whereEvent = "where unitId = ? and isHandle =?";
				Object[] queryParamsEvent = {unitId,false};
				List<EventInfo> events = eventDao.find(-1,-1,whereEvent, queryParamsEvent).getList();
				int isHave = 0;
				if(events.size()>0){
					isHave = 1;
				} else {
					isHave = 0;
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("isHave", isHave);
				jsonObject.put("unitName", unitInfo.getUnitName());
				jsonObject.put("addr", unitInfo.getUnitAddr());
				jsonObject.put("header", unitInfo.getHeadName());
				jsonObject.put("tel", unitInfo.getHeadTel());
				jsonObject.put("site", unitInfo.getUnitSite());
				System.out.println(unitInfo.getUnitSite());
				jsonArray.add(jsonObject);
			}
		}
		return "json";
	}
	
	public String getSite() throws Exception {
		String where = "where unitName like ?";
		setUnitName(URLDecoder.decode(unitName, "UTF-8")); 
		Object[] queryParams = {"%" + unitName + "%"};
		System.out.println(unitName);
		List list = unitDao.find(-1, -1, where, queryParams).getList();
		System.out.println(list.size());
		if(list.size() > 0){
			UnitInfo unit = (UnitInfo) list.get(0);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("site", unit.getUnitSite());
			siteJSONArray.add(jsonObject);
		}
		//System.out.println(siteJSONArray);
		return "json";
	}
	
	public String getName() throws Exception  {
		System.out.println(pageNo);
		String where = "where unitType=?";
		Object[] queryParams = {"联网单位"};		
		PageModel<UnitInfo> pageModel = unitDao.find(pageNo,pageSize,where,queryParams);
		List<UnitInfo> list = pageModel.getList();
		int totalPages = pageModel.getTotalPages();
		for(UnitInfo unitInfo:list){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("unitName", unitInfo.getUnitName());
			jsonObject.put("pageNo", pageModel.getPageNo());
			jsonObject.put("totalPages", totalPages);
			nameJSONArray.add(jsonObject);
		}
		return "json";
	}
	public UnitInfo getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
