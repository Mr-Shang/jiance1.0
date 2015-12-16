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
 * ���Action
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
	 * ������
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		return list();
	}
	
	/**
	 * ��ѯ������λ
	 * @return String
	 * @throws Exception
	 */
	public String list() throws Exception {

		
		String where = "where unitType =?";
		Object[] queryParams = {"������λ"};
		pageModel = unitDao.find(pageNo, pageSize,where,queryParams);//��ѯ����
		//totalPage = pageModel.getTotalPages();
		return LIST; //���ص�λ��̨����ҳ��
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
