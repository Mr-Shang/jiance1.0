package com.ccu.model.system;

import java.io.Serializable;
import java.util.List;


public class MenuModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//父菜单名称
	private String pMenu;
	//父菜单下的子菜单集合
	private List<SysMenu> listMenu;
	private String icon;
	public String getPMenu() {
		return pMenu;
	}
	public void setPMenu(String pMenu) {
		this.pMenu = pMenu;
	}
	public List<SysMenu> getListMenu() {
		return listMenu;
	}
	public void setListMenu(List<SysMenu> listMenu) {
		this.listMenu = listMenu;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
}
