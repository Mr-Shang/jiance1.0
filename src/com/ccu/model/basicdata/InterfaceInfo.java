package com.ccu.model.basicdata;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

/**
 * �ӿڶ����
 */
public class InterfaceInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//��ʶ
	private String machineCode;//�豸����
	private String interfaceName;//�ӿ�����
	private String interfaceType;//�ӿ�����
	private Integer port;//�˿�
	private String singnalName;//�ź�����
	private Double maxValue;//����
	private Double minValue;//����
	private Double referenceValue;//ģ�����ο�ֵ
	private Boolean isUsed;//�Ƿ����
	private String expressionKey;//��ʾ�ؼ���
	private Integer orderIndex;//�������
	private Double nomalValue;//������ƽ
	private String nomalShowName;//����״̬��ʾ��
	private String exceptShowName;//�쳣״̬��ʾ��
	private String unit;//������λ
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
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getInterfaceType() {
		return interfaceType;
	}
	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getSingnalName() {
		return singnalName;
	}
	public void setSingnalName(String singnalName) {
		this.singnalName = singnalName;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public Double getReferenceValue() {
		return referenceValue;
	}
	public void setReferenceValue(Double referenceValue) {
		this.referenceValue = referenceValue;
	}
	public boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	public String getExpressionKey() {
		return expressionKey;
	}
	public void setExpressionKey(String expressionKey) {
		this.expressionKey = expressionKey;
	}
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	public Double getNomalValue() {
		return nomalValue;
	}
	public void setNomalValue(Double nomalValue) {
		this.nomalValue = nomalValue;
	}
	public String getNomalShowName() {
		return nomalShowName;
	}
	public void setNomalShowName(String nomalShowName) {
		this.nomalShowName = nomalShowName;
	}
	public String getExceptShowName() {
		return exceptShowName;
	}
	public void setExceptShowName(String exceptShowName) {
		this.exceptShowName = exceptShowName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}



}
