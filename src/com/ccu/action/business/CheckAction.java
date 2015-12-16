package com.ccu.action.business;

import java.util.ArrayList;

import java.util.List;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;


import com.ccu.model.basicdata.UnitInfo;
import com.ccu.model.business.CheckInfo;
import com.ccu.model.business.VideoInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 查岗Action
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("checkAction")
public class CheckAction extends BaseAction implements ModelDriven<CheckInfo> {


	private static final long serialVersionUID = 1L;
	private CheckInfo checkInfo = new CheckInfo();
	private PageModel<UnitInfo> pageModel;







	
	/**
	 * 保存查岗
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		return list();
	}
	
	/**
	 * 查询联网单位
	 * @return String
	 * @throws Exception
	 */
	public String list() throws Exception {

		
		String where = "where unitType =?";
		Object[] queryParams = {"联网单位"};
		pageModel = unitDao.find(pageNo, pageSize,where,queryParams);//查询联网
		//totalPage = pageModel.getTotalPages();
		return LIST; //返回单位后台管理页面
	}
	

	

	public CheckInfo getModel() {
		return checkInfo;
	}


	public CheckInfo getCheckInfo() {
		return checkInfo;
	}

	public void setCheckInfo(CheckInfo checkInfo) {
		this.checkInfo = checkInfo;
	}





	public PageModel<UnitInfo> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<UnitInfo> pageModel) {
		this.pageModel = pageModel;
	}





}
