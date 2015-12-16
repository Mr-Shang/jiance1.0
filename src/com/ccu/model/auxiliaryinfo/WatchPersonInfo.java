package com.ccu.model.auxiliaryinfo;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

import com.ccu.model.basicdata.UnitInfo;

/**
 * 值班人员表
 */
public class WatchPersonInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//标识
	private UnitInfo unitInfo;//所属单位
	private String watchPersonName;//姓名
	private String contactTel;//联系电话
	private String personId;//身份证
	private String workCard;//上岗证
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
	public String getWatchPersonName() {
		return watchPersonName;
	}
	public void setWatchPersonName(String watchPersonName) {
		this.watchPersonName = watchPersonName;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getWorkCard() {
		return workCard;
	}
	public void setWorkCard(String workCard) {
		this.workCard = workCard;
	}
}
