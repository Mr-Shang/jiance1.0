package com.ccu.model.business;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;

import com.ccu.model.basicdata.UnitInfo;

/**
 * 查岗表
 */
public class CheckInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//标识
	private UnitInfo unitInfo;//所属单位
	private String checkPersonName;//查岗人
	private Date checkTime;//查岗时间
	private String unitName;//单位名称
	private String unitAddr;//单位地址
	private String headName;//责任人
	private Date answerTime;//应答时间
	private Boolean isAnswer;//是否应答
	private Date joinTime;//入网时间
	private Integer nodeNum;//总结点数
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
	public String getCheckPersonName() {
		return checkPersonName;
	}
	public void setCheckPersonName(String checkPersonName) {
		this.checkPersonName = checkPersonName;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
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
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public Date getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	public boolean getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(Boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	public Integer getNodeNum() {
		return nodeNum;
	}
	public void setNodeNum(Integer nodeNum) {
		this.nodeNum = nodeNum;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}



}
