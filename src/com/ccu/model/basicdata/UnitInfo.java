package com.ccu.model.basicdata;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.ccu.model.auxiliaryinfo.ControlRoomInfo;
import com.ccu.model.auxiliaryinfo.EnterUnitInfo;
import com.ccu.model.auxiliaryinfo.WatchPersonInfo;
import com.ccu.model.business.CheckInfo;
import com.ccu.model.business.CrtInfo;
import com.ccu.model.business.VideoInfo;
import com.ccu.model.system.UserInfo;

/**
 *��λ��
 */
public class UnitInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//��ʶ
	private UserInfo updateUser;//������
	

	private String unitCode;//
	private String unitName;//
	private String unitAddr;//单位地址
	private String organization;//
	private String enterpriseType;//
	private String unitType;//
	private String contactsName;//负责人
	private String contactsTel;//
	private String contactsMail;//联系人邮箱
	private String coordinate;//
	private String manageName;//
	private String manageTel;//
	private Date joinTime;//
	private String nodeNum;//
	private Boolean isRemove;//
	private Date updateTime;//
	private String legalPersonName;//
	private String legalPersonTel;//
	private String headName;//联系人
	private String headTel;//联系人电话
	private String unitSite;//坐标
	private UnitInfo calledUnit; //接警单位
	private UnitInfo monitoringUnit;//监控单位
	private UnitInfo repairUnit;//维保单位
	private Set<UnitInfo> socialUnits = new HashSet<UnitInfo>();//
	private Set<CrtInfo> crtInfos = new HashSet<CrtInfo>();//CRT
	private Set<WatchPersonInfo> watchPersonInfos = new HashSet<WatchPersonInfo>();//
	private Set<EnterUnitInfo> enterUnitInfos = new HashSet<EnterUnitInfo>();//
	//private Set<UserInfo> userInfos = new HashSet<UserInfo>();//�û�
	private Set<CheckInfo> checkInfos = new HashSet<CheckInfo>();//���
	private Set<EventInfo> eventInfos = new HashSet<EventInfo>();//�¼�
	private Set<EventType> eventTypes = new HashSet<EventType>();//�¼�����
	private Set<VideoInfo> videoInfos = new HashSet<VideoInfo>();//�����Ƶ
	private Set<ControlRoomInfo> controlRoomInfos = new HashSet<ControlRoomInfo>();//�����
	private Set<MachineInfo> machineInfos = new HashSet<MachineInfo>();//�豸 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitAddr() {
		return unitAddr;
	}
	public void setUnitAddr(String unitAddr) {
		this.unitAddr = unitAddr;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEnterpriseType() {
		return enterpriseType;
	}
	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getContactsName() {
		return contactsName;
	}
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	public String getContactsTel() {
		return contactsTel;
	}
	public void setContactsTel(String contactsTel) {
		this.contactsTel = contactsTel;
	}
	public String getContactsMail() {
		return contactsMail;
	}
	public void setContactsMail(String contactsMail) {
		this.contactsMail = contactsMail;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getManageName() {
		return manageName;
	}
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	public String getManageTel() {
		return manageTel;
	}
	public void setManageTel(String manageTel) {
		this.manageTel = manageTel;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public String getNodeNum() {
		return nodeNum;
	}
	public void setNodeNum(String nodeNum) {
		this.nodeNum = nodeNum;
	}
	public Boolean getIsRemove() {
		return isRemove;
	}
	public void setIsRemove(Boolean isRemove) {
		this.isRemove = isRemove;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getLegalPersonName() {
		return legalPersonName;
	}
	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}
	public String getLegalPersonTel() {
		return legalPersonTel;
	}
	public void setLegalPersonTel(String legalPersonTel) {
		this.legalPersonTel = legalPersonTel;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getHeadTel() {
		return headTel;
	}
	public void setHeadTel(String headTel) {
		this.headTel = headTel;
	}
	public Set<CrtInfo> getCrtInfos() {
		return crtInfos;
	}
	public void setCrtInfos(Set<CrtInfo> crtInfos) {
		this.crtInfos = crtInfos;
	}
	public Set<WatchPersonInfo> getWatchPersonInfos() {
		return watchPersonInfos;
	}
	public void setWatchPersonInfos(Set<WatchPersonInfo> watchPersonInfos) {
		this.watchPersonInfos = watchPersonInfos;
	}
	public Set<EnterUnitInfo> getEnterUnitInfos() {
		return enterUnitInfos;
	}
	public void setEnterUnitInfos(Set<EnterUnitInfo> enterUnitInfos) {
		this.enterUnitInfos = enterUnitInfos;
	}
/*	public Set<UserInfo> getUserInfos() {
		return userInfos;
	}
	public void setUserInfos(Set<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}*/
	public Set<CheckInfo> getCheckInfos() {
		return checkInfos;
	}
	public void setCheckInfos(Set<CheckInfo> checkInfos) {
		this.checkInfos = checkInfos;
	}
	public Set<EventInfo> getEventInfos() {
		return eventInfos;
	}
	public void setEventInfos(Set<EventInfo> eventInfos) {
		this.eventInfos = eventInfos;
	}
	public Set<EventType> getEventTypes() {
		return eventTypes;
	}
	public void setEventTypes(Set<EventType> eventTypes) {
		this.eventTypes = eventTypes;
	}
	public Set<VideoInfo> getVideoInfos() {
		return videoInfos;
	}
	public void setVideoInfos(Set<VideoInfo> videoInfos) {
		this.videoInfos = videoInfos;
	}
	public Set<ControlRoomInfo> getControlRoomInfos() {
		return controlRoomInfos;
	}
	public void setControlRoomInfos(Set<ControlRoomInfo> controlRoomInfos) {
		this.controlRoomInfos = controlRoomInfos;
	}
	public Set<MachineInfo> getMachineInfos() {
		return machineInfos;
	}
	public void setMachineInfos(Set<MachineInfo> machineInfos) {
		this.machineInfos = machineInfos;
	}
	public UserInfo getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(UserInfo updateUser) {
		this.updateUser = updateUser;
	}

	public UnitInfo getCalledUnit() {
		return calledUnit;
	}
	public void setCalledUnit(UnitInfo calledUnit) {
		this.calledUnit = calledUnit;
	}
	public UnitInfo getMonitoringUnit() {
		return monitoringUnit;
	}
	public void setMonitoringUnit(UnitInfo monitoringUnit) {
		this.monitoringUnit = monitoringUnit;
	}
	public Set<UnitInfo> getSocialUnits() {
		return socialUnits;
	}
	public void setSocialUnits(Set<UnitInfo> socialUnits) {
		this.socialUnits = socialUnits;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public UnitInfo getRepairUnit() {
		return repairUnit;
	}
	public void setRepairUnit(UnitInfo repairUnit) {
		this.repairUnit = repairUnit;
	}
	public String getUnitSite() {
		return unitSite;
	}
	public void setUnitSite(String unitSite) {
		this.unitSite = unitSite;
	}


}
