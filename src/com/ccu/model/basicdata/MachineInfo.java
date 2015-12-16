package com.ccu.model.basicdata;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;


/**
 * 设备表
 */
public class MachineInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//标识
	private UnitInfo unitInfo;//所属单位
	private String machineCode;//设备编号
	private String machineName;//设备名称
	private String machineType;//设备类型
	private Integer machineIndex;//设备序号
	private String maker;//制造商
	private String machineVersion;//设备型号
	private Boolean isUsed;//是否可用
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
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public Integer getMachineIndex() {
		return machineIndex;
	}
	public void setMachineIndex(Integer machineIndex) {
		this.machineIndex = machineIndex;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getMachineVersion() {
		return machineVersion;
	}
	public void setMachineVersion(String machineVersion) {
		this.machineVersion = machineVersion;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
}
