package com.ccu.action.anxiliary;







import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;
import com.ccu.model.auxiliaryinfo.EnterUnitInfo;
import com.ccu.model.auxiliaryinfo.WatchPersonInfo;
import com.ccu.model.basicdata.UnitInfo;
import com.opensymphony.xwork2.ModelDriven;
/**
 *用户Action
 * @author Li Chen
 * 
 */
@Scope("prototype")
@Controller("watchPersonAction")
public class WatchPersonAction<Iterator> extends BaseAction implements ModelDriven<WatchPersonInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private WatchPersonInfo watchPerson = new WatchPersonInfo();
	private String toJsp;
	
	private PageModel<WatchPersonInfo> pageModel;
	private String watchPerson_id ;
	
	
	
	public String getList(){
		
		pageModel = watchPersonDao.find(pageNo, pageSize);
		
		toJsp="WEB-INF/pages/admin/watchPerson/watchPerson_Alllist.jsp";
		
		return "SUCCESS";
	}
	

	
	public String addWPT(){
		
		toJsp="WEB-INF/pages/admin/watchPerson/addWatchPerson.jsp";
		
		return "SUCCESS";

	}
	
	
	public String addWP(){
		
		UnitInfo unitInfo = unitDao.get("c092dc09-7143-490a-8281-c0e972eae760");
		
		
		watchPerson.setUnitInfo(unitInfo);
		watchPersonDao.save(watchPerson);
		
		this.flag="success";
		this.message="新的值班人员添加成功";
		
		toJsp = "message.jsp";
		return "SUCCESS";
	}
	
	public String deleteWP(){
		
		watchPersonDao.delete(watchPerson_id);
		
		this.flag="success";
		this.message="值班人员删除成功";
		
		toJsp = "message.jsp";
		return "SUCCESS";
	}
	
	public String toUpdateWP(){
		
		watchPerson = watchPersonDao.get(watchPerson_id);
		
		toJsp = "/WEB-INF/pages/admin/watchPerson/updateWatchPerson.jsp";
		
		return "SUCCESS";
	}
	
	public String updateWP(){
		
		UnitInfo unitInfo = unitDao.get("c092dc09-7143-490a-8281-c0e972eae760");
		
		
		watchPerson.setUnitInfo(unitInfo);
		watchPersonDao.update(watchPerson);
		
		this.flag="success";
		this.message="值班人员修改成功";
		
		toJsp = "message.jsp";
		return "SUCCESS";
	}
	
	public String checkWP(){
		
		watchPerson = watchPersonDao.get(watchPerson_id);
		
		toJsp = "/WEB-INF/pages/admin/watchPerson/checkWatchPerson.jsp";
		
		return "SUCCESS";
		
	}
	@Override
	public WatchPersonInfo getModel() {
		// TODO Auto-generated method stub
		return watchPerson;
	}

	public WatchPersonInfo getWatchPerson() {
		return watchPerson;
	}

	public void setWatchPerson(WatchPersonInfo watchPerson) {
		this.watchPerson = watchPerson;
	}

	public String getToJsp() {
		return toJsp;
	}

	public void setToJsp(String toJsp) {
		this.toJsp = toJsp;
	}

	public PageModel<WatchPersonInfo> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<WatchPersonInfo> pageModel) {
		this.pageModel = pageModel;
	}

	public String getWatchPerson_id() {
		return watchPerson_id;
	}

	public void setWatchPerson_id(String watchPerson_id) {
		this.watchPerson_id = watchPerson_id;
	}
	
}
