package com.ccu.model.auxiliaryinfo;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;

import com.ccu.model.basicdata.UnitInfo;

/**
 * ������
 */
public class ControlRoomInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//��ʶ
	private UnitInfo unitInfo;//������λ
	private String controlRoomCode;//�����ұ��
	private String controlRoomName;//����������
	private Date createTime;//����ʱ��
	private String roomAddr;//���������ڵ�
	private String headName;//������
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
