package com.ccu.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 设备action
 * @author CCU
 *
 */
public class MachineAction extends ActionSupport {

	private static final long serialVersionUID = 7923846023028727836L;
	
	private String param;
	public String demo() {
		System.out.println("测试");
		param = "设备故障数";
		return "demo";
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	
	}

}
