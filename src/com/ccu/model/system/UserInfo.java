package com.ccu.model.system;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.ccu.model.basicdata.EventInfo;
import com.ccu.model.basicdata.UnitInfo;

/**
 * �û���
 */
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//��ʶ
	private UserInfo UpdateUser;//
	private String unitId; //unitInfo;//所属社区Id
	private UserInfo CreateUser;//�����û�
	private String account;//�ʺ�
	private String password;//����
	private String userName;//�û���
	private String userTel;//��ϵ�绰
	private String userMail;//��ϵ����
	private Date createTime;//����ʱ��
	private Date updateTime;//����ʱ��
	private Boolean isManager;//�Ƿ����Ա
	private Set<EventInfo> eventInfos = new HashSet<EventInfo>();//�¼�
	private Set<LogInfo> logInfos = new HashSet<LogInfo>();//��־
	private Role role;//
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserInfo getUpdateUser() {
		return UpdateUser;
	}
	public void setUpdateUser(UserInfo updateUser) {
		UpdateUser = updateUser;
	}
/*	public UnitInfo getUnitInfo() {
		return unitInfo;
	}
	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}*/
	public UserInfo getCreateUser() {
		return CreateUser;
	}
	public void setCreateUser(UserInfo createUser) {
		CreateUser = createUser;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	public Set<EventInfo> getEventInfos() {
		return eventInfos;
	}
	public void setEventInfos(Set<EventInfo> eventInfos) {
		this.eventInfos = eventInfos;
	}
	public Set<LogInfo> getLogInfos() {
		return logInfos;
	}
	public void setLogInfos(Set<LogInfo> logInfos) {
		this.logInfos = logInfos;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}



}
