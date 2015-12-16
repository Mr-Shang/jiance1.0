package com.ccu.action.basicdata;


import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;
import com.ccu.model.basicdata.Evaluate;
import com.ccu.model.basicdata.EventInfo;
import com.ccu.model.basicdata.EventType;
import com.ccu.model.basicdata.UnitInfo;




@Scope("prototype")
@Controller("eventStatisticalAction")
public class EventStatisticalAction extends BaseAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String month;
	private String unitId;
	private String year;
	private String eventUnitId;//接收统计图unitId
	private String myUnitId;//接收事件类型ajax unitId
	private String ourUnitId;//接收时间详细信息ajax unitId
	private String typeId;
	private UnitInfo unitInfo; 
	public String getMyUnitId() {
		return myUnitId;
	}

	public void setMyUnitId(String myUnitId) {
		this.myUnitId = myUnitId;
	}

	public String getEventUnitId() {
		return eventUnitId;
	}

	public void setEventUnitId(String eventUnitId) {
		this.eventUnitId = eventUnitId;
	}

	private JSONArray jsonArray = new JSONArray();//饼状图
	private JSONArray jsonTypeInfo = new JSONArray();//获取事件类型
	private JSONArray jsonEventInfo = new JSONArray();//获取每类型事件详细信息
	private JSONArray jsonCount =new JSONArray();//统计数字
	private JSONArray jsonAllInfo = new JSONArray();
	private JSONArray jsonAllCount = new JSONArray();

	public JSONArray getJsonTypeInfo() {
		return jsonTypeInfo;
	}

	public void setJsonTypeInfo(JSONArray jsonTypeInfo) {
		this.jsonTypeInfo = jsonTypeInfo;
	}

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
		return SUCCESS;
	}
	public String init() throws Exception {
		unitInfo = unitDao.get(unitId);
		return "info";
	}
	
	public String draw() throws Exception {	
		return SUCCESS;
	}
	public String getTypeInfo() throws Exception {
		System.out.println(myUnitId);
		List<EventType> listType = new ArrayList<EventType>();
		listType = eventTypeDao.find(-1,-1).getList();
		Calendar now = Calendar.getInstance();
		int nowYear = now.get(Calendar.YEAR);
		int nowMonth = now.get(Calendar.MONTH) + 1;
		for(EventType eventType:listType){
			if(eventType.getUnitInfo() == null) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("typeName",eventType.getTypeName());
				String hql = "select count(*) from EventInfo e where eventTypeId=? and unitId=? and year(e.happenTime)=? and month(e.happenTime)=?";			
				Object[] queryParamsCount = {eventType.getId(),myUnitId,nowYear,nowMonth};			
				Long count = (Long) eventDao.uniqueResult(hql, queryParamsCount);			
				jsonObject.put("count", count);
				jsonObject.put("typeId", eventType.getId());
				jsonTypeInfo.add(jsonObject);
			}
		}
		return "json";
	}
	
	public String getEventInfo() throws Exception{
		System.out.println(ourUnitId);
		System.out.println(typeId);
		Calendar now = Calendar.getInstance();
		int nowYear = now.get(Calendar.YEAR);
		int nowMonth = now.get(Calendar.MONTH) + 1;
		String where = "where unitId=? and eventTypeId=? and year(happenTime)=? and month(happenTime)=?";
		Object[] queryParams = {ourUnitId,typeId,nowYear,nowMonth};
		String desc = "desc";
		Map orderby = new HashMap();
		orderby.put("happenTime",desc);
		PageModel<EventInfo> pageModel = eventDao.find(where, queryParams, orderby, pageNo, pageSize);
		int totalPages = pageModel.getTotalPages();
		List<EventInfo> list = pageModel.getList();
		System.out.println(list.size());
		for(EventInfo eventInfo:list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("eventName", eventInfo.getEventName());
			jsonObject.put("alertSource", eventInfo.getAlertSource());
			jsonObject.put("description", eventInfo.getAlertDescription());
			
			String happenTime =  eventInfo.getHappenTime();
			String pat1 = "yyyy-MM-dd HH:mm:ss.S" ;
			 
			SimpleDateFormat sdf1 = new SimpleDateFormat(pat1) ; 
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ; 
			Date d = null ;    
		    try{    
		           d = sdf1.parse(happenTime) ;   // 将给定的字符串中的日期提取出来    
		     }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理    
		           e.printStackTrace() ;       // 打印异常信息    
		    }  
		    happenTime = sdf2.format(d);
			jsonObject.put("happenPlace", eventInfo.getHappenPlace());
			jsonObject.put("happenTime", happenTime);
			jsonObject.put("pageNo", pageNo);
			jsonObject.put("totalPages",totalPages );
			jsonEventInfo.add(jsonObject);
		}
		return "json";
	}
	
	public String getEventStatistical() throws Exception {	
		System.out.println(eventUnitId);
		System.out.println(month);
		System.out.println(year);
		List<EventType> listType = new ArrayList<EventType>();
		listType = eventTypeDao.find(-1,-1).getList();
		
		
		for(EventType eventType:listType){
			if(eventType.getUnitInfo() == null) {
				String hql = "select count(*) from EventInfo e where eventTypeId=? and unitId=? and year(e.happenTime)=? and month(e.happenTime)=?";
				Object[] queryParamsCount = {eventType.getId(),eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};			
				Long num = (Long) eventDao.uniqueResult(hql, queryParamsCount);
				System.out.println(eventType.getTypeName() + ":" + num);				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("typeName", eventType.getTypeName());
				jsonObject.put("num", num);
				jsonArray.add(jsonObject);		
			}
		}
		return "json";
	}
	
	public String getAlertCount() {
		System.out.println(eventUnitId);
		System.out.println(month);
		System.out.println(year);
		String hql1 = "select count(*) from EventInfo where eventName=? and unitId=? and year(happenTime)=? and month(happenTime)=?";
		String hql = "select count(*) from EventInfo where eventName=? and unitId=? and year(happenTime)=? and month(happenTime)=? and isFalseReport=?";
		String hql2 = "select count(*) from EventTest where unitId=? and year(happenTime)=? and month(happenTime)=?";
		Object[] queryParamsTestCount = {eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		Object[] queryParamsCount = {"火警",eventUnitId,Integer.valueOf(year),Integer.valueOf(month),false};//真火警
		Object[] queryParamsCount1 = {"火警",eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		Object[] queryParamsCount2 = {"启动",eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		Object[] queryParamsCount3 = {"监管",eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		Object[] queryParamsCount4 = {"复位",eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		Object[] queryParamsCount5 = {"故障",eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		Object[] queryParamsCount6 = {"反馈",eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		Object[] queryParamsCount7 = {"屏蔽",eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		Object[] queryParamsCount8 = {"手动",eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		Long count = (Long) eventDao.uniqueResult(hql, queryParamsCount);
		System.out.println(count);
		Long count1 = (Long) eventDao.uniqueResult(hql1, queryParamsCount1);
		System.out.println(count1);
		Long count2 = (Long) eventDao.uniqueResult(hql1, queryParamsCount2);
		System.out.println(count2);
		Long count3 = (Long) eventDao.uniqueResult(hql1, queryParamsCount3);
		System.out.println(count3);
		Long count4 = (Long) eventDao.uniqueResult(hql1, queryParamsCount4);
		System.out.println(count4);
		Long count5 = (Long) eventDao.uniqueResult(hql1, queryParamsCount5);
		System.out.println(count5);
		Long count6 = (Long) eventDao.uniqueResult(hql1, queryParamsCount6);
		System.out.println(count6);
		Long count7 = (Long) eventDao.uniqueResult(hql1, queryParamsCount7);
		System.out.println(count7);
		Long count8 = (Long) eventDao.uniqueResult(hql1, queryParamsCount8);
		System.out.println(count8);
		Long testCount = (Long) eventTestDao.uniqueResult(hql2, queryParamsTestCount);
		System.out.println(testCount);
		
		Object[] queryParams = {eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		String where =  "where unitId=? and year(assessmentTime)=? and month(assessmentTime)=?";
		List<Evaluate> list = evaluateDao.find(-1, -1,where, queryParams).getList();
		Double testResult = 0.00;
		if(list.size() > 0){
			testResult = ((Evaluate) list.get(0)).getTestResult();//抽检率
		}
		NumberFormat nt = NumberFormat.getPercentInstance();
		nt.setMinimumFractionDigits(2);
		String result = nt.format(testResult);
		System.out.println(result);
		Double errorCount = (double) (count1 - count);
		System.out.println("error" + errorCount);
		String errorResult = "0.00%";
		if(count1 > 0) {
			Double errorRate = errorCount /(double) count1;
			errorResult = nt.format(errorRate);
		}
		//double error
		
		System.out.println(errorResult);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("count", count);
		jsonObject.put("count1", count1);
		jsonObject.put("count2", count2);
		jsonObject.put("count3", count3);
		jsonObject.put("count4", count4);
		jsonObject.put("count5", count5);
		jsonObject.put("count6", count6);
		jsonObject.put("count7", count7);
		jsonObject.put("count8", count8);
		jsonObject.put("testCount", testCount);
		jsonObject.put("result", result);
		jsonObject.put("errorResult", errorResult);
		jsonCount.add(jsonObject);
		return "json";
	}
	
	public String getAllEvent() throws Exception {
		System.out.println(eventUnitId);
		System.out.println(year);
		System.out.println(month);
		String where = "where unitId=? and year(happenTime)=? and month(happenTime)=?";
		Object[] queryParams = {eventUnitId,Integer.valueOf(year),Integer.valueOf(month)};
		String desc = "desc";
		String asc = "asc";
		Map<String,String> orderby = new HashMap<String, String>();
		orderby.put("happenTime",desc);
		orderby.put("eventTypeId", asc);
		PageModel<EventInfo> pageModel = eventDao.find(where, queryParams, orderby, pageNo, 15);
		int totalPages = pageModel.getTotalPages();
		List<EventInfo> list = pageModel.getList();
		System.out.println(list.size());
		for(EventInfo eventInfo:list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("eventName", eventInfo.getEventName());
			jsonObject.put("alertSource", eventInfo.getAlertSource());
			jsonObject.put("description", eventInfo.getAlertDescription());
			jsonObject.put("alertMachine",eventInfo.getAlertMachine());
			String eventTypeId = eventInfo.getEventTypeId();
			System.out.println(eventTypeId);
			String where1 = "where id=?";
			Object[] queryParams1 = {eventTypeId};
			EventType eventType = eventTypeDao.find(-1, -1, where1, queryParams1).getList().get(0);
			jsonObject.put("typeName", eventType.getTypeName());
			System.out.println(eventType.getTypeName());
			String happenTime =  eventInfo.getHappenTime();
			System.out.println(happenTime);
			String pat1 = "yyyy-MM-dd HH:mm:ss.S" ;
			 
			SimpleDateFormat sdf1 = new SimpleDateFormat(pat1) ; 
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ; 
			Date d = null ;    
		    try{    
		           d = sdf1.parse(happenTime) ;   // 将给定的字符串中的日期提取出来    
		     }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理    
		           e.printStackTrace() ;       // 打印异常信息    
		    }  
		    happenTime = sdf2.format(d);
			jsonObject.put("happenPlace", eventInfo.getHappenPlace());
			jsonObject.put("happenTime", happenTime);
			jsonObject.put("pageNo", pageNo);
			jsonObject.put("totalPages",totalPages );
			jsonAllInfo.add(jsonObject);
		}
		
		return "jsonAll";
	}
	
	public String getStatistical() throws Exception {
		List<EventType> listType = new ArrayList<EventType>();
		listType = eventTypeDao.find(-1,-1).getList();
		for(EventType eventType:listType){
			if(eventType.getUnitInfo() == null) {
				JSONObject jsonObject = new JSONObject();
				for(int i = 0;i < 12;i++){
					String hql = "select count(*) from EventInfo e where eventTypeId=? and unitId=? and year(e.happenTime)=? and month(e.happenTime)=?";
					int month = i + 1;
					Object[] queryParamsCount = {eventType.getId(),eventUnitId,Integer.valueOf(year),month};			
					Long count = (Long) eventDao.uniqueResult(hql, queryParamsCount);
					System.out.println(i + ":" + count);	
					String title = "count" + month;
					jsonObject.put(title, count);
				}
				jsonObject.put("typeName", eventType.getTypeName());
				jsonAllCount.add(jsonObject);		
			}
		}
		return "json";
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

	public String getOurUnitId() {
		return ourUnitId;
	}

	public void setOurUnitId(String ourUnitId) {
		this.ourUnitId = ourUnitId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public JSONArray getJsonEventInfo() {
		return jsonEventInfo;
	}

	public void setJsonEventInfo(JSONArray jsonEventInfo) {
		this.jsonEventInfo = jsonEventInfo;
	}

	public UnitInfo getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}

	public JSONArray getJsonCount() {
		return jsonCount;
	}

	public void setJsonCount(JSONArray jsonCount) {
		this.jsonCount = jsonCount;
	}

	public JSONArray getJsonAllInfo() {
		return jsonAllInfo;
	}

	public void setJsonAllInfo(JSONArray jsonAllInfo) {
		this.jsonAllInfo = jsonAllInfo;
	}

	public JSONArray getJsonAllCount() {
		return jsonAllCount;
	}

	public void setJsonAllCount(JSONArray jsonAllCount) {
		this.jsonAllCount = jsonAllCount;
	}
}
