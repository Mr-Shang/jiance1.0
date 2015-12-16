package com.ccu.action.basicdata;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;
import com.ccu.model.basicdata.EventInfo;

import com.ccu.model.basicdata.EventType;
import com.ccu.model.basicdata.InterfaceRecord;
import com.opensymphony.xwork2.ModelDriven;



@Scope("prototype")
@Controller("interfaceStatisticalAction")
public class InterfaceStatisticalAction extends BaseAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String port;
	private String interfacePort;
	private String machineCode;
	private String interfaceMachineCode;
	public String getInterfaceMachineCode() {
		return interfaceMachineCode;
	}
	public void setInterfaceMachineCode(String interfaceMachineCode) {
		this.interfaceMachineCode = interfaceMachineCode;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	

	private Long currentTime;
	private Long oldTime;
	public Long getOldTime() {
		return oldTime;
	}
	public void setOldTime(Long oldTime) {
		this.oldTime = oldTime;
	}


	private JSONArray jsonArray = new JSONArray();
	
	public Long getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}
	
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	
	

	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getInterfacePort() {
		return interfacePort;
	}
	public void setInterfacePort(String interfacePort) {
		this.interfacePort = interfacePort;
	}





	public String load() throws Exception {
		return SUCCESS;
	}
	
	public String getInterfaceStatistical() throws Exception {
		System.out.println(currentTime);
		System.out.println(oldTime);
		
		Date currentDate = new Date(currentTime);
		Date oldDate = new Date(oldTime);
		String where = "where machineCode=? and port=? and currentTime<? and currentTime>?";
		Object[] queryParams = {interfaceMachineCode,interfacePort,currentDate,oldDate};
		List<InterfaceRecord> list = recordDao.find(-1, -1, where, queryParams).getList();
		
		for(InterfaceRecord record : list){
			JSONObject jsonObject = new JSONObject();
			Double currentValue = record.getCurrentValue();
			Double maxValue = record.getMaxValue();
			Double minValue = record.getMinValue();
			Date date = record.getCurrentTime();
			DateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String time = myFormat.format(date);
			jsonObject.put("currentValue", currentValue);
			jsonObject.put("maxValue", maxValue);
			jsonObject.put("minValue", minValue);
			jsonObject.put("time", time); 
			jsonArray.add(jsonObject);
			
		}
		return "json";
	}
	 


}
