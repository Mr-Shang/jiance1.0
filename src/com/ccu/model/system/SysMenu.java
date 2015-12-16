package com.ccu.model.system;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

/**
* �˵���
*/
public class SysMenu implements Serializable,Comparable<SysMenu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//
	private String id;//��ʶ
	private String menuName;//�˵���
	private String menuPath;//·��
	private String icon;//ͼ��
	//private String menuLevel;//�˵�����
	private Integer menuPid;//���ڵ�id
	private Integer menuMid;//�ڵ�id
	public Integer getMenuPid() {
		return menuPid;
	}
	public void setMenuPid(Integer menuPid) {
		this.menuPid = menuPid;
	}
	public Integer getMenuMid() {
		return menuMid;
	}
	public void setMenuMid(Integer menuMid) {
		this.menuMid = menuMid;
	}
	private Integer menuIndex;//���
	//private String menuTarget;//Ŀ�꣬������ target ����
	private Boolean isParent;//�Ƿ�Ϊ���ڵ�

	
	//private SysMenu parent;//���˵�
	//private Set<SysMenu> children = new HashSet<SysMenu>()//�Ӳ˵�


	
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

//	public String getMenuLevel() {
//		return menuLevel;
//	}
//	public void setMenuLevel(String menuLevel) {
//		this.menuLevel = menuLevel;
//	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getMenuIndex() {
		return menuIndex;
	}
	public void setMenuIndex(Integer menuIndex) {
		this.menuIndex = menuIndex;
	}
	public int compareTo(SysMenu o) {
		return this.menuIndex - o.menuIndex  ;
	}
//	public String getMenuTarget() {
//		return menuTarget;
//	}
//	public void setMenuTarget(String menuTarget) {
//		this.menuTarget = menuTarget;
//	}
}