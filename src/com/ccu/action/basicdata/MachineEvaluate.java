package com.ccu.action.basicdata;

import java.net.URLDecoder;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;
import com.ccu.model.basicdata.UnitInfo;
import com.ccu.util.hibernate.HibernateUtils;

/**
 * 设备评估
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller("machineEvaluate")
public class MachineEvaluate extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String month;
	private String year;
	private String machineParam;
	private String unitId;
	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	private JSONArray unitJSONArray = new JSONArray();
	private JSONArray machineJSONArray = new JSONArray();
	private JSONArray count1JSONArray = new JSONArray();
	private JSONArray count2JSONArray = new JSONArray();
	public JSONArray getCount1JSONArray() {
		return count1JSONArray;
	}

	public void setCount1JSONArray(JSONArray count1jsonArray) {
		count1JSONArray = count1jsonArray;
	}

	public JSONArray getCount2JSONArray() {
		return count2JSONArray;
	}

	public void setCount2JSONArray(JSONArray count2jsonArray) {
		count2JSONArray = count2jsonArray;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String load() throws Exception {
		return SUCCESS;
	}
	
	public String load1() throws Exception {
		System.out.println(year);
		System.out.println(month);
		return "table2";
	}
	public String load2() throws Exception {
		return "table3";
	}
	
	/**
	 * 根据设备统计图
	 * @return
	 * @throws Exception
	 */
	
	public String getCountByMachine() throws Exception {
		machineParam = URLDecoder.decode(machineParam, "UTF-8"); 
		System.out.println(machineParam);
		String[] typeArr = {"火警","故障","启动","反馈","监管","屏蔽","消音","复位"};
		for(int i = 0;i < typeArr.length;i++){
			String hqlEvent = "select count(*) from EventInfo where alertMachine=? and eventTypeId=? and year(happenTime)=? and month(happenTime)=?";
			String typeId = eventTypeDao.getTypeId(typeArr[i]);
			System.out.println(typeId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("typeName", typeArr[i]);
			Long eventCount = 0L;
			if(!typeId.equals("")){
				Object[] queryParams = {machineParam,typeId,Integer.valueOf(year),Integer.valueOf(month)};
				eventCount = (Long) eventDao.uniqueResult(hqlEvent, queryParams);	
			}  
			jsonObject.put("eventCount", eventCount);
			count1JSONArray.add(jsonObject);
		}
		return "jsonCount1";
	}
	/**
	 * 根据单位统计图
	 * @return
	 * @throws Exception
	 */
	
	public String getCountByUnit() throws Exception {
		System.out.println(unitId);
		String[] typeArr = {"火警","故障","启动","反馈","监管","屏蔽","消音","复位"};
		for(int i = 0;i < typeArr.length;i++){
			String hqlEvent = "select count(*) from EventInfo where unitId=? and eventTypeId=? and year(happenTime)=? and month(happenTime)=?";
			String typeId = eventTypeDao.getTypeId(typeArr[i]);
			System.out.println(typeId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("typeName", typeArr[i]);
			Long eventCount = 0L;
			if(!typeId.equals("")){
				Object[] queryParams = {unitId,typeId,Integer.valueOf(year),Integer.valueOf(month)};
				eventCount = (Long) eventDao.uniqueResult(hqlEvent, queryParams);	
			}  
			jsonObject.put("eventCount", eventCount);
			count2JSONArray.add(jsonObject);
		}
		return "jsonCount2";
	}
	/**
	 * 根据设备统计表
	 * @return
	 * @throws Exception
	 */
	public String getMachineEventCount() throws Exception {
		Session  session = HibernateUtils.getSession();
		session.beginTransaction();
		String hql = "select machineName,count(*) from MachineInfo where machineType=? group by machineName ";
		Query query = session.createQuery(hql).setParameter(0, "报警主机");
		Integer totalPages = (query.list().size() + 10 - 1) / 10;
		System.out.println(totalPages);
		List list = query.setFirstResult(getFirstResult(pageNo,10)).setMaxResults(10).list();
		
		session.getTransaction().commit();
		session.close();
		String[] typeArr = {"火警","故障","启动","反馈","监管","屏蔽","消音","复位"};
		for(Object o : list){
			Object[] arr = (Object[])o;
			String machineName = (String) arr[0];
			System.out.println(machineName);
			System.out.println(arr[1]);
			//String machineCount = (String) arr[1];
			//System.out.println(machineCount);
			JSONObject jsonObject = new JSONObject();
			Long count = 0L;
			String hqlEvent = "select count(*) from EventInfo where alertMachine=? and eventTypeId=? and year(happenTime)=? and month(happenTime)=?";
			for(int i = 0;i < typeArr.length;i++){
				String countName = "count" + (i + 1);
				String typeId = eventTypeDao.getTypeId(typeArr[i]);
				System.out.println(typeId);
				if(!typeId.equals("")){
					Object[] queryParams = {machineName,typeId,Integer.valueOf(year),Integer.valueOf(month)};
					Long eventCount = (Long) eventDao.uniqueResult(hqlEvent, queryParams);
					count = count + eventCount;
					jsonObject.put(countName, eventCount);
				} else {
					Long eventCount = 0L;
					jsonObject.put(countName, eventCount);
				}
			}
			jsonObject.put("count", count);
			jsonObject.put("machineName", machineName);
			jsonObject.put("machineCount", arr[1]);
			jsonObject.put("totalPages", totalPages);
			machineJSONArray.add(jsonObject);	
		}
		return "jsonMachine";
	}
	
	/**
	 * 根据单位统计表
	 * @return
	 * @throws Exception
	 */
	public String getUnitEventCount() throws Exception {
		String where = "where unitType=?";
		Object[] queryParams = {"联网单位"};
		
		PageModel<UnitInfo> pageModel = unitDao.find(pageNo, 10, where, queryParams);
		Integer totalPages = pageModel.getTotalPages();
		
		List<UnitInfo> list = pageModel.getList();
		String[] typeArr = {"火警","故障","启动","反馈","监管","屏蔽","消音","复位"};
		System.out.println(list.size());
		if(list.size() > 0){
			for(UnitInfo unit : list){
				JSONObject jsonObject = new JSONObject();
				String unitId = unit.getId(); 
				System.out.println(unitId);
				String hqlEvent = "select count(*) from EventInfo where unitId=? and eventTypeId=? and year(happenTime)=? and month(happenTime)=?";
				Long count = 0L;
				for(int i = 0;i < typeArr.length;i++){
					String countName = "count" + (i + 1);
					String typeId = eventTypeDao.getTypeId(typeArr[i]);
					System.out.println(typeId);
					if(!typeId.equals("")){
						Object[] queryParamsCount = {unitId,typeId,Integer.valueOf(year),Integer.valueOf(month)};
						Long eventCount = (Long) eventDao.uniqueResult(hqlEvent, queryParamsCount);
						System.out.println(eventCount);
						count = count + eventCount;
						jsonObject.put(countName, eventCount);
					} else {
						Long eventCount = 0L;
						jsonObject.put(countName, eventCount);
					}
				}
				
				jsonObject.put("count", count);
				jsonObject.put("unitName", unit.getUnitName());
				jsonObject.put("unitId", unitId);
				jsonObject.put("totalPages", totalPages);
				unitJSONArray.add(jsonObject);
				System.out.println(totalPages);
			}
			
		}
		return "jsonUnit";
	}
	
	private int getFirstResult(int pageNo,int maxResult){
		int firstResult = (pageNo-1) * maxResult;
		return firstResult < 0 ? 0 : firstResult;
	}

	public JSONArray getUnitJSONArray() {
		return unitJSONArray;
	}

	public void setUnitJSONArray(JSONArray unitJSONArray) {
		this.unitJSONArray = unitJSONArray;
	}

	public JSONArray getMachineJSONArray() {
		return machineJSONArray;
	}

	public void setMachineJSONArray(JSONArray machineJSONArray) {
		this.machineJSONArray = machineJSONArray;
	}

	public String getMachineParam() {
		return machineParam;
	}

	public void setMachineParam(String machineParam) {
		this.machineParam = machineParam;
	}
	
}
