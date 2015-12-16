package com.ccu.model.basicdata;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;


/**
 * �¼���
 */
public class EventInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	private String id;//��ʶ
	private String unitId;//������λ
	private String handleUserId;//������
	private String eventTypeId;//�¼�����ID
	private String unitName;//��������
	private String unitAddr;//���ŵ�ַ
	private String contactTel;//��ϵ�˵绰
	private String machineCode;//�豸����
	private String alertMachine;//�����豸
	private String alertSource;//����Դ
	private String alertDescription;//������Ϣ
	private String happenPlace;//�����ص�
	private String eventName;//�¼�����
	private String happenTime;//����ʱ��
	private String eventDescription;//�¼�˵��
	private String handleDescription;//����˵��
	private String handleTime;//����ʱ��
	private Boolean isHandle;//�Ƿ���
	private Boolean isFalseReport;//�Ƿ���
	private String handleUser;//�������˺�
	
	
	
	
	public EventInfo() {
		
	}
	
	public EventInfo(String unitId, String handleUserId, String eventTypeId,
			String unitName, String unitAddr, String contactTel,
			String machineCode, String alertMachine, String alertSource,
			String alertDescription, String happenPlace, String eventName,
			String happenTime, String eventDescription,
			String handleDescription, String handleTime, Boolean isHandle,
			Boolean isFalseReport) {
		this.unitId = unitId;
		this.handleUserId = handleUserId;
		this.eventTypeId = eventTypeId;
		this.unitName = unitName;
		this.unitAddr = unitAddr;
		this.contactTel = contactTel;
		this.machineCode = machineCode;
		this.alertMachine = alertMachine;
		this.alertSource = alertSource;
		this.alertDescription = alertDescription;
		this.happenPlace = happenPlace;
		this.eventName = eventName;
		this.happenTime = happenTime;
		this.eventDescription = eventDescription;
		this.handleDescription = handleDescription;
		this.handleTime = handleTime;
		this.isHandle = isHandle;
		this.isFalseReport = isFalseReport;
	}

	public EventInfo(String id, String unitId, String handleUserId,
			String eventTypeId, String unitName, String unitAddr,
			String contactTel, String machineCode, String alertMachine,
			String alertSource, String alertDescription, String happenPlace,
			String eventName, String happenTime, String eventDescription,
			String handleDescription, String handleTime, Boolean isHandle,
			Boolean isFalseReport) {
		this.id = id;
		this.unitId = unitId;
		this.handleUserId = handleUserId;
		this.eventTypeId = eventTypeId;
		this.unitName = unitName;
		this.unitAddr = unitAddr;
		this.contactTel = contactTel;
		this.machineCode = machineCode;
		this.alertMachine = alertMachine;
		this.alertSource = alertSource;
		this.alertDescription = alertDescription;
		this.happenPlace = happenPlace;
		this.eventName = eventName;
		this.happenTime = happenTime;
		this.eventDescription = eventDescription;
		this.handleDescription = handleDescription;
		this.handleTime = handleTime;
		this.isHandle = isHandle;
		this.isFalseReport = isFalseReport;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getHandleUserId() {
		return handleUserId;
	}
	public void setHandleUserId(String handleUserId) {
		this.handleUserId = handleUserId;
	}
	public String getEventTypeId() {
		return eventTypeId;
	}
	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
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
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getAlertMachine() {
		return alertMachine;
	}
	public void setAlertMachine(String alertMachine) {
		this.alertMachine = alertMachine;
	}
	public String getAlertSource() {
		return alertSource;
	}
	public void setAlertSource(String alertSource) {
		this.alertSource = alertSource;
	}
	public String getAlertDescription() {
		return alertDescription;
	}
	public void setAlertDescription(String alertDescription) {
		this.alertDescription = alertDescription;
	}
	public String getHappenPlace() {
		return happenPlace;
	}
	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getHappenTime() {
		return happenTime;
	}
	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getHandleDescription() {
		return handleDescription;
	}
	public void setHandleDescription(String handleDescription) {
		this.handleDescription = handleDescription;
	}
	public String getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	public Boolean getIsHandle() {
		return isHandle;
	}
	public void setIsHandle(Boolean isHandle) {
		this.isHandle = isHandle;
	}
	public Boolean getIsFalseReport() {
		return isFalseReport;
	}
	public void setIsFalseReport(Boolean isFalseReport) {
		this.isFalseReport = isFalseReport;
	}

	public String getHandleUser() {
		return handleUser;
	}

	public void setHandleUser(String handleUser) {
		this.handleUser = handleUser;
	}
}
