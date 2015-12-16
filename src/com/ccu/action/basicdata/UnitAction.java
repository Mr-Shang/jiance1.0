package com.ccu.action.basicdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;

import com.ccu.model.basicdata.UnitInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��λAction
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("unitAction")
public class UnitAction extends BaseAction implements ModelDriven<UnitInfo> {


	private static final long serialVersionUID = 1L;
	private UnitInfo unitInfo = new UnitInfo();
	private PageModel<UnitInfo> pageModel;
	private List<String> items = new ArrayList<String>();//���ո�ѡ���ֵ
	private List<UnitInfo> calledUnits = new ArrayList<UnitInfo>();
	private List<UnitInfo> monitoringUnits = new ArrayList<UnitInfo>();
	private List<String> unitTypes = new ArrayList<String>();
	private Boolean flag;


	/**
	 * ��ӵ�λ
	 * @return String
	 * @throws Exception
	 */
	public String add() throws Exception {
		String where = "where unitType =?";
		Object[] queryParams1 = {"�Ӿ���λ"};//���ýӾ��û���ѯ����
		calledUnits = unitDao.find(-1, -1,where,queryParams1).getList();
		Object[] queryParams2 = {"��ص�λ"};//���ü���û���ѯ����
		monitoringUnits = unitDao.find(-1, -1,where,queryParams2).getList();
		unitTypes.add("- -  �� λ �� ��  - -");
		unitTypes.add("������λ");
		unitTypes.add("��ص�λ");
		unitTypes.add("�Ӿ���λ");
		flag = true;
		return INPUT;
	}
	
	/**
	 * ������µ�λ
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		if( unitInfo.getUnitType().equals("�Ӿ���λ") || unitInfo.getUnitType().equals("��ص�λ")) {
			unitInfo.setCalledUnit(null);
			unitInfo.setMonitoringUnit(null);
		}
		unitDao.saveOrUpdate(unitInfo);
		return list();
	}
	
	/**
	 * ��ѯ��λ
	 * @return String
	 * @throws Exception
	 */
	public String list() throws Exception {
		pageModel = unitDao.find(pageNo, pageSize);//��ѯ��λ��Ϣ
		//totalPage = pageModel.getTotalPages();
		return LIST; //���ص�λ��̨����ҳ��
	}
	
	/**
	 * ɾ��������λ
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		if(unitInfo.getUnitType() == "�Ӿ���λ") {//ȡ����Ӿ���λ�Ĺ���
			String where = "calledUnit = ?";
			Object[] queryParams = {unitInfo};
			List<UnitInfo> socialUnits = unitDao.find(-1, -1, where, queryParams).getList();
			for(UnitInfo socialUnit:socialUnits ){
				socialUnit.setCalledUnit(null);
			}
		} 
		if (unitInfo.getUnitType() == "��ص�λ") {//ȡ����ص�λ�Ĺ���
			String where = "monitoringUnit = ?";
			Object[] queryParams = {unitInfo};
			List<UnitInfo> socialUnits = unitDao.find(-1, -1, where, queryParams).getList();
			for(UnitInfo socialUnit:socialUnits ){
				socialUnit.setCalledUnit(null);
			}
		} 
		unitDao.delete(unitInfo.getId());
		return list();
	}
	
	/**
	 * ɾ�������λ
	 * @return String
	 * @throws Exception
	 */

	public String deleteItems() throws Exception {
		for(String unitId:items) {
			UnitInfo unit = unitDao.get(unitId);
			if(unit.getUnitType() == "�Ӿ���λ") {
				String where = "calledUnit = ?";
				Object[] queryParams = {unit};
				List<UnitInfo> socialUnits = unitDao.find(-1, -1, where, queryParams).getList();
				for(UnitInfo socialUnit:socialUnits ){
					socialUnit.setCalledUnit(null);
				}
			} else if(unit.getUnitType() == "��ص�λ") {
				String where = "monitoringUnit = ?";
				Object[] queryParams = {unit};
				List<UnitInfo> socialUnits = unitDao.find(-1, -1, where, queryParams).getList();
				for(UnitInfo socialUnit:socialUnits ){
					socialUnit.setCalledUnit(null);
				}
			} 
			unitDao.delete(unitId);
		}
		return list();
	}
	
	/**
	 * �༭��λ
	 * @return String
	 * @throws Exception
	 */
	
	public String edit() throws Exception {
		
		unitInfo = unitDao.get(unitInfo.getId());//��ȡ��λ
		if(unitInfo.getUnitType().equals("������λ")) {//��λ��������
			unitTypes.add("������λ");
			unitTypes.add("��ص�λ");
			unitTypes.add("�Ӿ���λ");
		}
		if(unitInfo.getUnitType().equals("��ص�λ")) {
			unitTypes.add("��ص�λ");
			unitTypes.add("������λ");
			unitTypes.add("�Ӿ���λ");
		}
		if(unitInfo.getUnitType().equals("�Ӿ���λ")) {
			unitTypes.add("�Ӿ���λ");
			unitTypes.add("������λ");
			unitTypes.add("��ص�λ");
		}
		if (unitInfo.getUnitType().equals("��ص�λ") || unitInfo.getUnitType().equals("�Ӿ���λ")) {//����ǰ̨��ʾ��־
			flag = false;
		} else flag = true;
		String where = "where unitType =?";
		Object[] queryParams1 = {"�Ӿ���λ"};//���ýӾ��û���ѯ����
		calledUnits = unitDao.find(-1, -1,where,queryParams1).getList();
		Object[] queryParams2 = {"��ص�λ"};//���ü���û���ѯ����
		monitoringUnits = unitDao.find(-1, -1,where,queryParams2).getList();
		return EDIT;
	}
	
	/**
	 * �༭��λ��Ŀ
	 * @return String
	 * @throws Exception
	 */
	public String queryItem() throws Exception {
		
		unitInfo = unitDao.get(items.get(0));//��ȡ���༭����Ŀ
		if(unitInfo.getUnitType().equals("������λ")) {
			unitTypes.add("������λ");
			unitTypes.add("��ص�λ");
			unitTypes.add("�Ӿ���λ");
		}
		if(unitInfo.getUnitType().equals("��ص�λ")) {
			unitTypes.add("��ص�λ");
			unitTypes.add("������λ");
			unitTypes.add("�Ӿ���λ");
		}
		if(unitInfo.getUnitType().equals("�Ӿ���λ")) {
			unitTypes.add("�Ӿ���λ");
			unitTypes.add("������λ");
			unitTypes.add("��ص�λ");
		}
		if (unitInfo.getUnitType().equals("��ص�λ") || unitInfo.getUnitType().equals("�Ӿ���λ")) {
			flag = false;
		} else flag = true;
		String where = "where unitType =?";
		Object[] queryParams1 = {"�Ӿ���λ"};//���ýӾ��û���ѯ����
		calledUnits = unitDao.find(-1, -1,where,queryParams1).getList();
		Object[] queryParams2 = {"��ص�λ"};//���ü���û���ѯ����
		monitoringUnits = unitDao.find(-1, -1,where,queryParams2).getList();
		return QUERY;
	}
	public UnitInfo getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}

	public UnitInfo getModel() {
		return unitInfo;
	}

	public PageModel<UnitInfo> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<UnitInfo> pageModel) {
		this.pageModel = pageModel;
	}
	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}


	public List<UnitInfo> getCalledUnits() {
		return calledUnits;
	}

	public void setCalledUnits(List<UnitInfo> calledUnits) {
		this.calledUnits = calledUnits;
	}

	public List<UnitInfo> getMonitoringUnits() {
		return monitoringUnits;
	}

	public void setMonitoringUnits(List<UnitInfo> monitoringUnits) {
		this.monitoringUnits = monitoringUnits;
	}

	public List<String> getUnitTypes() {
		return unitTypes;
	}

	public void setUnitTypes(List<String> unitTypes) {
		this.unitTypes = unitTypes;
	}
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
}
