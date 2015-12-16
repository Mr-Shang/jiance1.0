package com.ccu.action.basicdata;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;

import com.ccu.model.basicdata.InterfaceInfo;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Controller("interFaceAction")
public class InterfaceAction extends BaseAction implements ModelDriven<InterfaceInfo>{
	

	private static final long serialVersionUID = -651238970246055041L;

	private InterfaceInfo interfaceInfo = new InterfaceInfo();

	private PageModel<InterfaceInfo> pageModel;

	private String items;
	

	//�ӿ���Ϣ��ѯ
	public String list() throws Exception{
		System.out.println("�����ѯ����");
		pageModel = interfaceDao.find(pageNo, pageSize);
		return LIST;
	}
	//�ӿ���Ϣ���
	public String add() throws Exception{
		
		return INPUT;
	}
	//�ӿ���Ϣ����
	public String save() throws Exception{
		System.out.println(interfaceInfo.getIsUsed());
		System.out.println(interfaceInfo.getInterfaceName());
		interfaceDao.saveOrUpdate(interfaceInfo);
		return list();
	}
	
	//�ӿڱ༭
	public String edit() throws Exception{
		
		System.out.println();
		interfaceInfo = interfaceDao.get(interfaceInfo.getId());
		return EDIT;
	}
	
	//�ӿڱ༭
	public String delete() throws Exception{
		
		System.out.println("ɾ������");
		interfaceDao.delete(interfaceInfo.getId());
		
		return list();
	}
	
	

	@Override
	public InterfaceInfo getModel() {
		// TODO Auto-generated method stub
		return interfaceInfo;
	}
	public PageModel<InterfaceInfo> getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel<InterfaceInfo> pageModel) {
		this.pageModel = pageModel;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public InterfaceInfo getInterfaceInfo() {
		return interfaceInfo;
	}
	public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}
	
	
}
