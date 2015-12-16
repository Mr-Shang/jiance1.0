package com.ccu.model.basicdata;

import java.io.Serializable;
import java.util.Date;

public class Evaluate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Double testResult;//抽检率
	private String unitId;
	private Double goodResult;//完好率
	private Date assessmentTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getTestResult() {
		return testResult;
	}
	public void setTestResult(Double testResult) {
		this.testResult = testResult;
	}

	public Double getGoodResult() {
		return goodResult;
	}
	public void setGoodResult(Double goodResult) {
		this.goodResult = goodResult;
	}
	public Date getAssessmentTime() {
		return assessmentTime;
	}
	public void setAssessmentTime(Date assessmentTime) {
		this.assessmentTime = assessmentTime;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
}
