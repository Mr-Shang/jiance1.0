
package com.ccu.action.basicdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ccu.model.basicdata.EventInfo;

import com.ccu.model.basicdata.UnitInfo;
import com.ccu.model.system.UserInfo;
//import com.sun.faces.facelets.tag.jstl.core.SetHandler;


/**
 * 事件Action
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("eventAction")
public class EventAction extends BaseAction {


	private static final long serialVersionUID = 1L;


	

	private String eventCount;
	public String getEventCount() {
		return eventCount;
	}

	public void setEventCount(String eventCount) {
		this.eventCount = eventCount;
	}

	/**事件名称*/

	private String eventId;
	private String returnParam;
	JSONArray jsonArray=new JSONArray();

	/**
	 * 查询事件
	 * @return String
	 * @throws Exception
	 */
	public String notHandleEventList() throws Exception {
		String where = "where IsHandle = ?";//
		Map<String,String> orderby = new HashMap<String,String>();
		String asc = "desc";
		orderby.put("happenTime",asc);
		Object[] queryParams = {false};//
		List<EventInfo> eventList = new ArrayList<EventInfo>();
		PageModel<EventInfo> pageModel = eventDao.find(where, queryParams, orderby, pageNo,6);
		if(pageModel != null) {
			int totalPages = pageModel.getTotalPages();
			eventList = pageModel.getList();//查询事件信息
			for(EventInfo event : eventList) {
				UnitInfo unit = unitDao.get(event.getUnitId());
				if(!unit.getIsRemove()){
					String happenTime =  event.getHappenTime();
					//System.out.println(happenTime);
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
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("eventName", event.getEventName());
					jsonObject.put("happenPlace", event.getHappenPlace());
					jsonObject.put("happenTime", happenTime);
					jsonObject.put("unitName", event.getUnitName());
					jsonObject.put("eventId", event.getId());
					jsonObject.put("alertSource", event.getAlertSource());
					jsonObject.put("alertDescription", event.getAlertDescription());
					jsonObject.put("machineCode", event.getMachineCode());
					jsonObject.put("pageNo", pageModel.getPageNo());
					jsonObject.put("totalPages", totalPages);
					jsonArray.add(jsonObject);
				}
			}
		}
		return "json"; //返回列表页面
	}
	
	/**
	 * 事件处理action
	 * @return
	 */
	public String handleEvent() {
		try {			
			EventInfo eventInfo = eventDao.get(eventId);
			System.out.println(eventInfo.getEventName());
			eventInfo.setIsHandle(true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ; 
			Date date = new Date();
			String handleTime = sdf.format(date);
			eventInfo.setHandleTime(handleTime);
			UserInfo user = (UserInfo) session.get("admin");
			eventInfo.setHandleUserId(user.getId());
			eventInfo.setHandleUser(user.getAccount());
			eventDao.saveOrUpdate(eventInfo);
			returnParam = "操作成功";
		}catch(Exception e) {
			returnParam = "操作失败";
			e.printStackTrace();
		}
		return "handleEvent";
	}

	public String getCount() throws Exception {
		
		String hql = "select count(*) from EventInfo where IsHandle = ?";
		Object[] queryParams = {false};			
		
		Long count = (Long) eventDao.uniqueResult(hql, queryParams);	
		eventCount = String.valueOf(count);
		return "count";
	}




	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	



	public String getReturnParam() {
		return returnParam;
	}

	public void setReturnParam(String returnParam) {
		this.returnParam = returnParam;
	}


	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}






}

