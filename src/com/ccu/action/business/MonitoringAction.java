package com.ccu.action.business;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;


/**
 * 
 * @author liuzy
 *
 */
@Scope("prototype")
@Controller("monitoringAction")
public class MonitoringAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String show() throws Exception {
		return SUCCESS;
	}

}
