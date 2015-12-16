package com.ccu.model.auxiliaryinfo;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;

import com.ccu.model.basicdata.UnitInfo;

/**
 * 消控室
 */
public class ControlRoomInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//标识
	private UnitInfo unitInfo;//所属单位
	private String controlRoomCode;//消控室编号
	private String controlRoomName;//消控室名称
	private Date createTime;//建立时间
	private String roomAddr;//消控室所在地
	private String headName;//负责人
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UnitInfo getUnitInfo() {
		return unitInfo;
	}
	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}
	public String getControlRoomCode() {
		return controlRoomCode;
	}
	public void setControlRoomCode(String controlRoomCode) {
		this.controlRoomCode = controlRoomCode;
	}
	public String getControlRoomName() {
		return controlRoomName;
	}
	public void setControlRoomName(String controlRoomName) {
		this.controlRoomName = controlRoomName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRoomAddr() {
		return roomAddr;
	}
	public void setRoomAddr(String roomAddr) {
		this.roomAddr = roomAddr;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}


}
