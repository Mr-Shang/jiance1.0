package com.ccu.action;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller("indexAction")
public class IndexAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Override
	public String execute() throws Exception {
		//执行查询主页信息的方法
		
		
		
		
		return SUCCESS;
	}
	
	
}
