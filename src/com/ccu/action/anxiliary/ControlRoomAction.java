package com.ccu.action.anxiliary;







import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;
import com.ccu.model.auxiliaryinfo.ControlRoomInfo;
import com.opensymphony.xwork2.ModelDriven;
/**
 *用户Action
 * @author Li Chen
 * 
 */
@Scope("prototype")
@Controller("controlRoomAction")
public class ControlRoomAction<Iterator> extends BaseAction implements ModelDriven<ControlRoomInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageModel<ControlRoomInfo> pageModel;
	private String toJsp;
	private String controlRoom_id;
	
	
	private ControlRoomInfo controlRoom = new ControlRoomInfo();
	
	public String getCRList(){
		
		pageModel = controlRoomDao.find(pageNo, pageSize);//查询消控室信息

		return LIST;
		
	}
	
	public String getControlRoomById(){
		
		System.out.println("22222222222222222222222222222222222222222222222222222222222222222222");
		System.out.println(controlRoomDao.get("0efabcdb-d67e-44c2-8dd1-f2230ef1c3ed").getControlRoomName().toString()+"111111111111111");
		
		return "SUCCESS";
	}
	
	
	public String deleteCR(){
		
		controlRoomDao.delete(controlRoom_id);
		
		this.flag="success";
		this.message="消控室删除成功";
		
		toJsp = "message.jsp";
		return "SUCCESS";
	}
	public String addCR(){
		
		controlRoom.setCreateTime(new Date());
		
		controlRoomDao.save(controlRoom);
		
		this.flag="success";
		this.message="消控室添加成功";
		
		toJsp = "message.jsp";
		return "SUCCESS";
	}
	
	
	public String toUpdateCR(){
		
		controlRoom = controlRoomDao.get(controlRoom_id);
		toJsp = "/WEB-INF/pages/admin/controlRoom/upDateControlRoom.jsp";
		
		return "SUCCESS";
	}
	
	public String checkCRT(){
		
		controlRoom = controlRoomDao.get(controlRoom_id);
		toJsp = "/WEB-INF/pages/admin/controlRoom/checkControlRoom.jsp";
		
		return "SUCCESS";
	}
	
	public String updateCR(){
		
		controlRoomDao.update(controlRoom);
		
		this.flag="success";
		this.message="消控室信息修改成功";
		
		toJsp = "message.jsp";
		return "SUCCESS";
		
	}
	
	
	public String addCRT(){
		toJsp = "/WEB-INF/pages/admin/controlRoom/addControlRoom.jsp?target='right'";
		return "SUCCESS";
	}

	@Override
	public ControlRoomInfo getModel() {
		// TODO Auto-generated method stub
		return controlRoom;
	}


	public ControlRoomInfo getControlRoom() {
		return controlRoom;
	}


	public void setControlRoom(ControlRoomInfo controlRoom) {
		this.controlRoom = controlRoom;
	}

	public PageModel<ControlRoomInfo> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<ControlRoomInfo> pageModel) {
		this.pageModel = pageModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getToJsp() {
		return toJsp;
	}

	public void setToJsp(String toJsp) {
		this.toJsp = toJsp;
	}

	public String getControlRoom_id() {
		return controlRoom_id;
	}

	public void setControlRoom_id(String controlRoom_id) {
		this.controlRoom_id = controlRoom_id;
	}
	
}
