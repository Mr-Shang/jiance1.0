package com.ccu.action.system;







import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.system.LogInfo;
import com.ccu.model.system.MenuModel;
import com.ccu.model.system.Role;
import com.ccu.model.system.SysMenu;
import com.ccu.model.system.UserInfo;
import com.opensymphony.xwork2.ModelDriven;
/**
 *用户Action
 * @author Li Chen
 * 
 */
@Scope("prototype")
@Controller("userAction")
public class UserAction<Iterator> extends BaseAction implements ModelDriven<UserInfo>{
	private static final long serialVersionUID = 1L;
	//获取角色id
	private String rid;
	//创建角色对应权限集合
	public Set<SysMenu> setmenus;
	//获取所有权限集合
	public List<SysMenu> listmenus;
	//获取重新定义权限id
	public String car;
	public String login() throws Exception{
		return USER_LOGIN;
	}
	
	/**
	 * 登录 
	 * @return
	 * @throws Exception
	 */
	public String logon() throws Exception{
		//验证用户名和密码
		UserInfo loginUser = userDao.login(user.getAccount(), user.getPassword());
		if(loginUser != null){//通过验证
			session.put("admin", loginUser);//将管理员信息保存在Session对象中
			session.put("userId", loginUser.getId());//---填加的是用户id(更改)
			session.put("unitId", loginUser.getUnitId());//所属单位Id
			System.out.print(loginUser.getUnitId());
			/**
			 * 保存登录信息
			 */
			 Date loginTime=new Date();
			 java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     System.out.println( format1.format(loginTime));
			 session.put("loginTime", format1.format(loginTime));
			 System.out.println(loginTime);
			 String remoteAddr=ServletActionContext.getRequest().getRemoteAddr();
			 session.put("remoteAddr", remoteAddr);
			 LogInfo logInfo = new LogInfo();
			 logInfo.setUserInfo(loginUser);
			 logInfo.setLoginTime(loginTime);
			 logInfo.setOperateTime(loginTime);
			 logInfo.setMenuName("登录");
			 logInfo.setUserIp(remoteAddr);
			 logInfo.setExceptionMess("");
			 logDao.save(logInfo);
			 /**
			  * 获取菜单
			  */
			Set<SysMenu> sysMenus = loginUser.getRole().getSysMenus();//获取用户对应显示的菜单
			System.out.println(loginUser.getRole().getRoleName());
			Set<SysMenu> treeSysMenus = new TreeSet<SysMenu>();
			for(SysMenu sysMenu :sysMenus) {
				treeSysMenus.add(sysMenu);
			}
			
			List<MenuModel> listMM = new ArrayList<MenuModel>();
			for(SysMenu sm:treeSysMenus){
				if(sm.getIsParent()){
					MenuModel mm = new MenuModel();
					//将对应的父菜单添加到MenuModel中
					mm.setPMenu(sm.getMenuName());
					System.out.println(sm.getMenuName());
					mm.setIcon(sm.getIcon());
					//查询父菜单下的子菜单
					//String where = "where MenuPid = ?";
					//String []queryParams = {String.valueOf(sm.getMenuPid())};
					List<SysMenu> listMenu = new ArrayList<SysMenu>();
					for(SysMenu sMenu:treeSysMenus){
						if(!sMenu.getIsParent()){			
							if(sMenu.getMenuPid().equals(sm.getMenuMid())){
								listMenu.add(sMenu);
								System.out.println(sMenu.getMenuName());
							}
						}
					}
					if(listMenu!=null&&listMenu.size()>0){
						//将子菜单添加到MenuModel中
						mm.setListMenu(listMenu);
					}else{
						mm.setListMenu(null);
					}
					//将MenuModel放入list中
					listMM.add(mm);
					
				}
			}
			//将所有菜单集合放入session中
			session.put("listMM", listMM);
		}else{
			addFieldError("error1", "用户名或密码不正确！");//返回错误提示信息
			return USER_LOGIN;//返回后台登录页面
		}
		return MANAGER;//返回后台管理页面
	}
	
	public String getzTreeNodes() throws Exception {
		
		
		Set<SysMenu> treeSysMenus = (Set<SysMenu>) session.get("sysMenus");

		for(SysMenu sysMenu :  treeSysMenus) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", sysMenu.getMenuMid());
			jsonObject.put("pId", sysMenu.getMenuPid());
			jsonObject.put("name", sysMenu.getMenuName());
			jsonObject.put("isParent", sysMenu.getIsParent());
			jsonObject.put("open", true);
			jsonObject.put("url",sysMenu.getMenuPath());
			jsonObject.put("target","right");
			jsonArray.add(jsonObject);
		}
		return "json";
	}
	/**
	 * 退出
	 * @return String
	 * @throws Exception
	 */
	public String logout() throws Exception{
		System.out.println("退出系统操作");
		if(session != null && session.size() > 0){
			session.clear();
		}
		return USER_LOGIN;
	}
	
	/**
	 * urp权限动态分配
	 * */
	//权限分配
		public String  power(){
			//获取角色信息
			List<Role> roList = roleDao.find(-1, -1).getList();
			//将角色信息存入session
			session.put("roList", roList);
			return "urp";
		}
		//角色对应权限信息
		public String rPower(){

			//通过角色id获取相应角色
			Role role = roleDao.get(rid);
			session.put("role", role);
			//获取角色对应权限
			
			setmenus = role.getSysMenus();
			Set<UserInfo> setUser = role.getUsers();
			List<UserInfo> listUser = userDao.find(-1, -1).getList();
			listmenus = sysMenuDao.find(-1, -1).getList();
			session.put("setUser", setUser);
			session.put("listUser", listUser);
			session.put("setmenus", setmenus);
			session.put("listmenus", listmenus);

			return urpmain1();
		}
		//保存更改后的角色对应权限信息
		public String setpower(){
			
			//获取当前选中角色
			Role role = (Role)session.get("role");
			System.out.println(role.getRoleName());
			role.setSysMenus(null);
			roleDao.update(role);
			String[] pids = car.split(",");
			Set<SysMenu> powerSet = new HashSet<SysMenu>();
				//查询所有选中权限
				for(int i=0;i<pids.length;i++){
					if(pids[i].equals("0")) continue;
					String where ="where MenuMid=?";
					Object[]queryParams={pids[i]};
					System.out.println(sysMenuDao.find(-1, -1, where, queryParams).getList().get(0).getMenuName());
					powerSet.add(sysMenuDao.find(-1, -1, where, queryParams).getList().get(0));
				}
			role.setSysMenus(powerSet);
			roleDao.update(role);
			rid = role.getId();
			return rPower();
		}
		//动态分配用户
		public String setTea(){
			
			//获取当前选中角色
					Role role = (Role)session.get("role");
					role.setUsers(null);
					roleDao.update(role);
					String[] pids = car.split(",");
					Set<UserInfo> teaSet = new HashSet<UserInfo>();
						//查询所有选中权限
						for(int i=0;i<pids.length;i++){
							teaSet.add(userDao.get(pids[i]));
						}
					role.setUsers(teaSet);
					roleDao.update(role);
					rid = role.getId();
			return rPower();
		}
		
		public String urpright(){
			return "urpright";
		}
		public String urpcenter(){
			return "urpcenter";
		}
		public String urpleft(){
			return "urpleft";
		}
		public String urpmain1(){
			return "urpmain1";
		}
	
	// 用户对象
	private UserInfo user = new UserInfo();
	
	JSONArray jsonArray=new JSONArray();
	
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public UserInfo getModel() {
		return user;
	}
	
	/**
	 * 查询用户
	 * @return String
	 * @throws Exception
	 */
	
	public String list() throws Exception {
		
		return LIST;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public Set<SysMenu> getSetmenus() {
		return setmenus;
	}
	public void setSetmenus(Set<SysMenu> setmenus) {
		this.setmenus = setmenus;
	}
	public List<SysMenu> getListmenus() {
		return listmenus;
	}
	public void setListmenus(List<SysMenu> listmenus) {
		this.listmenus = listmenus;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}

}
