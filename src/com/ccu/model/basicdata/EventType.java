package com.ccu.model.basicdata;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * 事件类型
 */
public class EventType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//id
	private UnitInfo unitInfo;
	private String typeName;
	private Set<EventInfo> eventInfos = new HashSet<EventInfo>();
	private Set<LogicGraphic> logicGraphics = new HashSet<LogicGraphic>();
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Set<EventInfo> getEventInfos() {
		return eventInfos;
	}
	public void setEventInfos(Set<EventInfo> eventInfos) {
		this.eventInfos = eventInfos;
	}
	public Set<LogicGraphic> getLogicGraphics() {
		return logicGraphics;
	}
	public void setLogicGraphics(Set<LogicGraphic> logicGraphics) {
		this.logicGraphics = logicGraphics;
	}


}
