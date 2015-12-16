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
 * �߼�ͼAction
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("logicGraphicAction")
public class LogicGraphicAction extends BaseAction implements ModelDriven<LogicGraphic> {


	private static final long serialVersionUID = 1L;
	private LogicGraphic logicGraphic = new LogicGraphic();



	private PageModel<LogicGraphic> pageModel;
	private List<String> items = new ArrayList<String>();//���ո�ѡ���ֵ
	private List<EventType> eventTypes = new ArrayList<EventType>();//������λ���ϣ�Ϊselect��ֵ





	/**
	 * ����߼�ͼ
	 * @return String
	 * @throws Exception
	 */
	public String add() throws Exception {
		eventTypes = eventTypeDao.find(-1, -1).getList();//��ѯ���е�λ
		return INPUT;
	}
	
	/**
	 * ��������߼�ͼ
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		logicGraphic.setEventType(eventTypeDao.get(logicGraphic.getEventType().getId()));//����������λ
		//���ù�������㹫ʽ���
		eventTypeDao.saveOrUpdate(logicGraphic);
		return list();
	}
	
	/**
	 * ��ѯ�߼�ͼ
	 * @return String
	 * @throws Exception
	 */
	public String list() throws Exception {
		pageModel = logicGraphicDao.find(pageNo, pageSize);//��ѯ�߼�ͼ��Ϣ
		//totalPage = pageModel.getTotalPages();
		return LIST; //���ص�λ��̨����ҳ��
	}
	
	/**
	 * ɾ�������߼�ͼ
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		logicGraphicDao.delete(logicGraphic.getId());
		return list();
	}
	
	/**
	 * ɾ������߼�ͼ
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
	 * �༭�߼�ͼ
	 * @return String
	 * @throws Exception
	 */
	
	public String edit() throws Exception {
		
		logicGraphic  = logicGraphicDao.get(logicGraphic.getId());//��ȡ�豸	
		eventTypes = eventTypeDao.find(-1, -1).getList();//��ѯ�����¼�����
		return EDIT;
	}
	
	/**
	 * ��ѯ�߼�ͼ��Ŀ
	 * @return String
	 * @throws Exception
	 */
	public String queryItem() throws Exception {
		
		logicGraphic = logicGraphicDao.get(items.get(0));//��ȡ���༭����Ŀ
	
		eventTypes = eventTypeDao.find(-1, -1).getList();//��ѯ�����¼�����
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
