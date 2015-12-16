package com.ccu.model.system;

// Generated 2015-5-7 9:42:43 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

/**
* 菜单表
*/
public class SysMenu implements Serializable,Comparable<SysMenu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//
	private String id;//标识
	private String menuName;//菜单名
	private String menuPath;//路径
	private String icon;//图标
	//private String menuLevel;//菜单级别
	private Integer menuPid;//父节点id
	private Integer menuMid;//节点id
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
	private Integer menuIndex;//序号
	//private String menuTarget;//目标，超链接 target 属性
	private Boolean isParent;//是否为父节点

	
	//private SysMenu parent;//父菜单
	//private Set<SysMenu> children = new HashSet<SysMenu>()//子菜单


	
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