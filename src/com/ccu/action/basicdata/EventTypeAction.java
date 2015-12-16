package com.ccu.action.basicdata;

import java.util.ArrayList;
import java.util.List;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;

import com.ccu.model.basicdata.EventType;
import com.ccu.model.basicdata.UnitInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * EVentAction
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("eventTypeAction")
public class EventTypeAction extends BaseAction implements ModelDriven<EventType> {


	private static final long serialVersionUID = 1L;
	private EventType eventType = new EventType();

	private PageModel<EventType> pageModel;
	
	List<EventType> list = new ArrayList<EventType>();
	JSONArray jsonArray=new JSONArray();
	public List<EventType> getList() {
		return list;
	}




	public void setList(List<EventType> list) {
		this.list = list;
	}




	public JSONArray getJsonArray() {
		return jsonArray;
	}




	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}




	/**
	 * 基本事件类型
	 * @return String
	 * @throws Exception
	 */
	public String getType() throws Exception {
		System.out.println("hi");		
		list= eventTypeDao.find(-1, -1).getList();
		for(EventType eventType : list) {
				if(eventType.getUnitInfo()  ==  null) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("typeName", eventType.getTypeName());
					jsonArray.add(jsonObject);
				}
		}
		System.out.println(jsonArray.size());
		return "json"; 
	}
	



	public EventType getModel() {
		return eventType;
	}

	public PageModel<EventType> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<EventType> pageModel) {
		this.pageModel = pageModel;
	}




	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}



}
