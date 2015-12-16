package com.ccu.action.basicdata;

import java.util.ArrayList;
import java.util.List;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;

import com.ccu.model.basicdata.EventType;
import com.ccu.model.basicdata.LogicGraphic;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 逻辑图Action
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("logicGraphicAction")
public class LogicGraphicAction extends BaseAction implements ModelDriven<LogicGraphic> {


	private static final long serialVersionUID = 1L;
	private LogicGraphic logicGraphic = new LogicGraphic();



	private PageModel<LogicGraphic> pageModel;
	private List<String> items = new ArrayList<String>();//接收复选框的值
	private List<EventType> eventTypes = new ArrayList<EventType>();//联网单位集合，为select传值





	/**
	 * 添加逻辑图
	 * @return String
	 * @throws Exception
	 */
	public String add() throws Exception {
		eventTypes = eventTypeDao.find(-1, -1).getList();//查询所有单位
		return INPUT;
	}
	
	/**
	 * 保存更新逻辑图
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		logicGraphic.setEventType(eventTypeDao.get(logicGraphic.getEventType().getId()));//设置所属单位
		//调用工具类计算公式结果
		eventTypeDao.saveOrUpdate(logicGraphic);
		return list();
	}
	
	/**
	 * 查询逻辑图
	 * @return String
	 * @throws Exception
	 */
	public String list() throws Exception {
		pageModel = logicGraphicDao.find(pageNo, pageSize);//查询逻辑图信息
		//totalPage = pageModel.getTotalPages();
		return LIST; //返回单位后台管理页面
	}
	
	/**
	 * 删除单个逻辑图
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		logicGraphicDao.delete(logicGraphic.getId());
		return list();
	}
	
	/**
	 * 删除多个逻辑图
	 * @return String
	 * @throws Exception
	 */

	public String deleteItems() throws Exception {
		for(String logicGraphicId:items) {
			logicGraphicDao.delete(logicGraphicId);
		}
		return list();
	}
	
	/**
	 * 编辑逻辑图
	 * @return String
	 * @throws Exception
	 */
	
	public String edit() throws Exception {
		
		logicGraphic  = logicGraphicDao.get(logicGraphic.getId());//获取设备	
		eventTypes = eventTypeDao.find(-1, -1).getList();//查询所有事件类型
		return EDIT;
	}
	
	/**
	 * 查询逻辑图条目
	 * @return String
	 * @throws Exception
	 */
	public String queryItem() throws Exception {
		
		logicGraphic = logicGraphicDao.get(items.get(0));//获取待编辑的条目
	
		eventTypes = eventTypeDao.find(-1, -1).getList();//查询所有事件类型
		return QUERY;
	}


	public LogicGraphic getModel() {
		return logicGraphic;
	}

	public PageModel<LogicGraphic> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<LogicGraphic> pageModel) {
		this.pageModel = pageModel;
	}
	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}


	public List<EventType> getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(List<EventType> eventTypes) {
		this.eventTypes = eventTypes;
	}




	public LogicGraphic getLogicGraphic() {
		return logicGraphic;
	}

	public void setLogicGraphic(LogicGraphic logicGraphic) {
		this.logicGraphic = logicGraphic;
	}
}
