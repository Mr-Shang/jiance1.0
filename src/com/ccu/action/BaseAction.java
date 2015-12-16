package com.ccu.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;


import com.ccu.dao.auxiliaryinfo.ControlRoomDao;
import com.ccu.dao.auxiliaryinfo.EnterUnitDao;
import com.ccu.dao.auxiliaryinfo.WatchPersonDao;
import com.ccu.dao.basicdata.EvaluateDao;
import com.ccu.dao.basicdata.EventDao;
import com.ccu.dao.basicdata.EventTestDao;
import com.ccu.dao.basicdata.EventTypeDao;
import com.ccu.dao.basicdata.InterfaceDao;
import com.ccu.dao.basicdata.LogicGraphicDao;
import com.ccu.dao.basicdata.MachineDao;
import com.ccu.dao.basicdata.RecordDao;
import com.ccu.dao.basicdata.UnitDao;
import com.ccu.dao.business.CheckDao;
import com.ccu.dao.business.CrtDao;
import com.ccu.dao.business.VideoDao;
import com.ccu.dao.system.LogInfoDao;
import com.ccu.dao.system.RoleDao;
import com.ccu.dao.system.SysMenuDao;
import com.ccu.dao.system.UserDao;
import com.ccu.model.system.UserInfo;

import com.opensymphony.xwork2.ActionSupport;
/**
 * ����Action��������Action�ĸ���
 * @author Li Chen
 */
public class BaseAction extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Integer[] ids;
	protected int pageNo = 1;
	protected int pageSize = 3;
	
	public static final String LIST = "list";
	public static final String EDIT = "edit";
	public static final String ADD = "add";
	public static final String SELECT = "select";
	public static final String QUERY = "query";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static final String INDEX = "index";
	public static final String MAIN = "main";
	public static final String MANAGER = "manager";
	public static final String TOP = "top";
	public static final String REG = "reg";
	public static final String USER_LOGIN = "userLogin";
	public static final String CUSTOMER_LOGIN = "customerLogin";
	public static final String LOGOUT = "logout";

	protected String flag;
	protected String message;
	
	
	// ��ȡ�û�id
	// ��ȡ����Ա����
	public UserInfo getLoginUser(){
		if(session.get("admin") != null){
			return (UserInfo) session.get("admin");
		}
		return null;
	}
	
	// ע��Dao
	@Autowired
	protected UserDao userDao;	//�û�
	@Autowired 
	protected LogInfoDao logDao;//��־
	@Autowired
	protected RoleDao roleDao;//��ɫ
	@Autowired
	protected SysMenuDao sysMenuDao;//�˵�
	@Autowired 
	protected ControlRoomDao controlRoomDao;//������
	@Autowired
	protected EnterUnitDao enterUnitDao;//��פ��λ
	@Autowired
	protected UnitDao unitDao;//��λ
	@Autowired 
	protected WatchPersonDao watchPersonDao;//ֵ����
	@Autowired
	protected CheckDao checktDao;//���
	@Autowired
	protected CrtDao crtDao;//CRT
	@Autowired 
	protected EventDao eventDao;//�¼�
	@Autowired
	protected EventTypeDao eventTypeDao;//�¼�����
	@Autowired
	protected InterfaceDao interfaceDao;//�ӿ�
	@Autowired 
	protected MachineDao machineDao;//�豸
	@Autowired
	protected LogicGraphicDao logicGraphicDao ;//�߼�ͼ
	@Autowired 
	protected RecordDao recordDao;//��¼
	@Autowired
	protected VideoDao videoDao;//��Ƶ
	@Autowired 
	protected EvaluateDao evaluateDao;//�������
	@Autowired
	protected EventTestDao eventTestDao;//�����¼�
	
	
	
	// Map���͵�request
	protected Map<String, Object> request;
	// Map���͵�session
	protected Map<String, Object> session;
	// Map���͵�application
	protected Map<String, Object> application;
	
	@Override
	public void setRequest(Map<String, Object> request) {
		// ��ȡMap���͵�request��ֵ
		this.request = request;
	}
	@Override
	public void setApplication(Map<String, Object> application) {
		// ��ȡMap���͵�application��ֵ
		this.application = application;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// ��ȡMap���͵�session��ֵ
		this.session = session;
	}
	
	// ������
	public String index() throws Exception {
		return INDEX;
	}
	public String manager() throws Exception {
		return MANAGER;
	}
	public String main() throws Exception {
		return MAIN;
	}
	public String add() throws Exception {
		return ADD;
	}
	public String select() throws Exception {
		return SELECT;
	}
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String center()  throws Exception {
		return "center";
	}
	public String top() throws Exception {
		return TOP;
	}
	public String left() throws Exception {
		return LEFT;
	}
	public String right() throws Exception {
		return RIGHT;
	}
	
	public String down() throws Exception{
		return "down";
	}
	public String reg() throws Exception{
		return REG;
	}
	public String query() throws Exception{
		return QUERY;
	}
	// getter��settter����
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
