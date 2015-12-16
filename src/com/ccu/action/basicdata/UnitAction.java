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
 * 单位Action
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("unitAction")
public class UnitAction extends BaseAction implements ModelDriven<UnitInfo> {


	private static final long serialVersionUID = 1L;
	private UnitInfo unitInfo = new UnitInfo();
	private PageModel<UnitInfo> pageModel;
	private List<String> items = new ArrayList<String>();//接收复选框的值
	private List<UnitInfo> calledUnits = new ArrayList<UnitInfo>();
	private List<UnitInfo> monitoringUnits = new ArrayList<UnitInfo>();
	private List<String> unitTypes = new ArrayList<String>();
	private Boolean flag;


	/**
	 * 添加单位
	 * @return String
	 * @throws Exception
	 */
	public String add() throws Exception {
		String where = "where unitType =?";
		Object[] queryParams1 = {"接警单位"};//设置接警用户查询条件
		calledUnits = unitDao.find(-1, -1,where,queryParams1).getList();
		Object[] queryParams2 = {"监控单位"};//设置监控用户查询条件
		monitoringUnits = unitDao.find(-1, -1,where,queryParams2).getList();
		unitTypes.add("- -  单 位 类 型  - -");
		unitTypes.add("联网单位");
		unitTypes.add("监控单位");
		unitTypes.add("接警单位");
		flag = true;
		return INPUT;
	}
	
	/**
	 * 保存更新单位
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		if( unitInfo.getUnitType().equals("接警单位") || unitInfo.getUnitType().equals("监控单位")) {
			unitInfo.setCalledUnit(null);
			unitInfo.setMonitoringUnit(null);
		}
		unitDao.saveOrUpdate(unitInfo);
		return list();
	}
	
	/**
	 * 查询单位
	 * @return String
	 * @throws Exception
	 */
	public String list() throws Exception {
		pageModel = unitDao.find(pageNo, pageSize);//查询单位信息
		//totalPage = pageModel.getTotalPages();
		return LIST; //返回单位后台管理页面
	}
	
	/**
	 * 删除单个单位
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		if(unitInfo.getUnitType() == "接警单位") {//取消与接警单位的关联
			String where = "calledUnit = ?";
			Object[] queryParams = {unitInfo};
			List<UnitInfo> socialUnits = unitDao.find(-1, -1, where, queryParams).getList();
			for(UnitInfo socialUnit:socialUnits ){
				socialUnit.setCalledUnit(null);
			}
		} 
		if (unitInfo.getUnitType() == "监控单位") {//取消监控单位的关联
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
	 * 删除多个单位
	 * @return String
	 * @throws Exception
	 */

	public String deleteItems() throws Exception {
		for(String unitId:items) {
			UnitInfo unit = unitDao.get(unitId);
			if(unit.getUnitType() == "接警单位") {
				String where = "calledUnit = ?";
				Object[] queryParams = {unit};
				List<UnitInfo> socialUnits = unitDao.find(-1, -1, where, queryParams).getList();
				for(UnitInfo socialUnit:socialUnits ){
					socialUnit.setCalledUnit(null);
				}
			} else if(unit.getUnitType() == "监控单位") {
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
	 * 编辑单位
	 * @return String
	 * @throws Exception
	 */
	
	public String edit() throws Exception {
		
		unitInfo = unitDao.get(unitInfo.getId());//获取单位
		if(unitInfo.getUnitType().equals("联网单位")) {//单位类型排序
			unitTypes.add("联网单位");
			unitTypes.add("监控单位");
			unitTypes.add("接警单位");
		}
		if(unitInfo.getUnitType().equals("监控单位")) {
			unitTypes.add("监控单位");
			unitTypes.add("联网单位");
			unitTypes.add("接警单位");
		}
		if(unitInfo.getUnitType().equals("接警单位")) {
			unitTypes.add("接警单位");
			unitTypes.add("联网单位");
			unitTypes.add("监控单位");
		}
		if (unitInfo.getUnitType().equals("监控单位") || unitInfo.getUnitType().equals("接警单位")) {//设置前台显示标志
			flag = false;
		} else flag = true;
		String where = "where unitType =?";
		Object[] queryParams1 = {"接警单位"};//设置接警用户查询条件
		calledUnits = unitDao.find(-1, -1,where,queryParams1).getList();
		Object[] queryParams2 = {"监控单位"};//设置监控用户查询条件
		monitoringUnits = unitDao.find(-1, -1,where,queryParams2).getList();
		return EDIT;
	}
	
	/**
	 * 编辑单位条目
	 * @return String
	 * @throws Exception
	 */
	public String queryItem() throws Exception {
		
		unitInfo = unitDao.get(items.get(0));//获取待编辑的条目
		if(unitInfo.getUnitType().equals("联网单位")) {
			unitTypes.add("联网单位");
			unitTypes.add("监控单位");
			unitTypes.add("接警单位");
		}
		if(unitInfo.getUnitType().equals("监控单位")) {
			unitTypes.add("监控单位");
			unitTypes.add("联网单位");
			unitTypes.add("接警单位");
		}
		if(unitInfo.getUnitType().equals("接警单位")) {
			unitTypes.add("接警单位");
			unitTypes.add("联网单位");
			unitTypes.add("监控单位");
		}
		if (unitInfo.getUnitType().equals("监控单位") || unitInfo.getUnitType().equals("接警单位")) {
			flag = false;
		} else flag = true;
		String where = "where unitType =?";
		Object[] queryParams1 = {"接警单位"};//设置接警用户查询条件
		calledUnits = unitDao.find(-1, -1,where,queryParams1).getList();
		Object[] queryParams2 = {"监控单位"};//设置监控用户查询条件
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
