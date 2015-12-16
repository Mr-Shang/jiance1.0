package com.ccu.action.anxiliary;







import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;
import com.ccu.model.auxiliaryinfo.EnterUnitInfo;
import com.ccu.model.basicdata.UnitInfo;
import com.opensymphony.xwork2.ModelDriven;
/**
 *用户Action
 * @author Li Chen
 * 
 */
@Scope("prototype")
@Controller("enterUnitAction")
public class EnterUnitAction<Iterator> extends BaseAction implements ModelDriven<EnterUnitInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6122616261803982697L;
	
	
	private EnterUnitInfo enterUnit = new EnterUnitInfo();
	private PageModel<EnterUnitInfo> pageModel;
	private String enterUnit_id ;
	private UnitInfo unit = new UnitInfo();
	private PageModel<UnitInfo> pageModel_1;
	private String toJsp;
	private String unit_id;
	
	public String getList(){
		
		pageModel = enterUnitDao.find(pageNo, pageSize); //查询入住单位信息

		toJsp="WEB-INF/pages/admin/enterUnit/enterUnit_list.jsp";
		
		return "SUCCESS";
	}
	
	public String addEU(){
		
		enterUnit.setEnterTime(new Date());
		
		
		UnitInfo unitInfo = unitDao.get(unit_id);
		
		enterUnit.setUnitInfo(unitInfo);
		enterUnitDao.save(enterUnit);
		this.flag="success";
		this.message="入驻单位添加成功";
		
		toJsp="message.jsp";
		
		return "SUCCESS";
	}
	public String addEUT(){
		
		pageModel_1 = unitDao.find(pageNo, pageSize);
		
		toJsp = "/WEB-INF/pages/admin/enterUnit/addEnterUnit.jsp?target='right'";
		return "SUCCESS";
	}
	
	
	public String deleteEU(){
		
		enterUnitDao.delete(enterUnit_id);
		
		this.flag="success";
		this.message="该入驻单位删除成功";
		toJsp = "message.jsp";
		
		return "SUCCESS";
	}
	
	public String toUpdateEU(){
		
		enterUnit = enterUnitDao.get(enterUnit_id);

		toJsp = "/WEB-INF/pages/admin/enterUnit/updateEnterUnit.jsp";
		
		return "SUCCESS";
	}
	
	public String updateEU(){

		System.out.println(enterUnit.getUnitInfo() + "dddddddddddddd");
		
		enterUnit.setUnitInfo(unitDao.get("c092dc09-7143-490a-8281-c0e972eae760"));
		
		System.out.println(enterUnit.getUnitInfo() + "dddddddddwwwwwwwwwwwwwwwww" );
		
		enterUnitDao.update(enterUnit);
		
		this.flag="success";
		this.message="该入驻单位修改成功";
		toJsp = "message.jsp";
		
		return "SUCCESS";
		
	}
	
	public String checkEU(){

		enterUnit = enterUnitDao.get(enterUnit_id);

		toJsp = "/WEB-INF/pages/admin/enterUnit/checkEnterUnit.jsp";
		
		return "SUCCESS";
	}

	@Override
	public EnterUnitInfo getModel() {
		// TODO Auto-generated method stub
		return enterUnit;
	}

	public EnterUnitInfo getEnterUnit() {
		return enterUnit;
	}

	public void setEnterUnit(EnterUnitInfo enterUnit) {
		this.enterUnit = enterUnit;
	}

	public String getToJsp() {
		return toJsp;
	}

	public void setToJsp(String toJsp) {
		this.toJsp = toJsp;
	}



	public PageModel<EnterUnitInfo> getPageModel() {
		return pageModel;
	}



	public void setPageModel(PageModel<EnterUnitInfo> pageModel) {
		this.pageModel = pageModel;
	}

	public String getEnterUnit_id() {
		return enterUnit_id;
	}

	public void setEnterUnit_id(String enterUnit_id) {
		this.enterUnit_id = enterUnit_id;
	}

	public UnitInfo getUnit() {
		return unit;
	}

	public void setUnit(UnitInfo unit) {
		this.unit = unit;
	}

	public PageModel<UnitInfo> getPageModel_1() {
		return pageModel_1;
	}

	public void setPageModel_1(PageModel<UnitInfo> pageModel_1) {
		this.pageModel_1 = pageModel_1;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	
}
