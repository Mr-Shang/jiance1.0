package com.ccu.model.auxiliaryinfo;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;

import com.ccu.model.basicdata.UnitInfo;

/**
 *��פ��λ
 */
public class EnterUnitInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//��ʶ
	private UnitInfo unitInfo;//������λ
	private String companyName;//��˾����
	private Date enterTime;//��פʱ��
	private String legalPersonName;//����
	private String legalPersonTel;//�绰
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
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
}
