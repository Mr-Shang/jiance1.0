package com.ccu.model.basicdata;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;

/**
 * ��¼��
 */
public class RecordInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//��ʶ
	private String machineCode;//�豸���
	private String alertMachine;//�����豸
	private Date alertTime;//����ʱ��
	private String alertSource;//����Դ
	private Integer port;//�˿�
	private Double signalValue;//�ź�ֵ
	private String description;//����
	private String happenPlace;//�����ص�
	private String expressionKey;//��ʽ�ؼ���
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getAlertTime() {
		return alertTime;
	}
	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}
	public String getAlertSource() {
		return alertSource;
	}
	public void setAlertSource(String alertSource) {
		this.alertSource = alertSource;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Double getSignalValue() {
		return signalValue;
	}
	public void setSignalValue(Double signalValue) {
		this.signalValue = signalValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHappenPlace() {
		return happenPlace;
	}
	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}
	public String getExpressionKey() {
		return expressionKey;
	}
	public void setExpressionKey(String expressionKey) {
		this.expressionKey = expressionKey;
	}
}
