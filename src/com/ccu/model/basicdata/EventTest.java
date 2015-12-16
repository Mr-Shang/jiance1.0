package com.ccu.model.basicdata;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;


/**
 * 事件表
 */
public class EventTest implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String id;//标识
	private String unitId;//所属单位
	private Date happenTime;//处理时间
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
	public Date getHappenTime() {
		return happenTime;
	}
	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

}
