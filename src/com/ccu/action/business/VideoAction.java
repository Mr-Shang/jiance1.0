package com.ccu.action.business;

import java.util.ArrayList;

import java.util.List;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;


import com.ccu.model.basicdata.UnitInfo;
import com.ccu.model.business.VideoInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("videoAction")
public class VideoAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String unitId;
	private String url;
	private VideoInfo video = new VideoInfo(); 

	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	public String play() throws Exception{
		System.out.println(unitId);
		String where = "where unitId=?";
		Object[] queryParams = {unitId};
		VideoInfo video = videoDao.find(-1, -1, where, queryParams).getList().get(0);
		url = video.getUrl();
		System.out.println(url);
		return "play";
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	} 










}
