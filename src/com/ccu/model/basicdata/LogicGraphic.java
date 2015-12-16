package com.ccu.model.basicdata;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;


/**
 * �߼�ͼ
 */
public class LogicGraphic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//id
	private EventType eventType;//事件类型
	private Boolean isUsed;//是否可用
	private Boolean result;//结果
	private String expression;//公式
	private String graphicName;//名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getGraphicName() {
		return graphicName;
	}
	public void setGraphicName(String graphicName) {
		this.graphicName = graphicName;
	}
}
