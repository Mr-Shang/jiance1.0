package com.ccu.wabacus.firetest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.wabacus.config.Config;
import com.wabacus.config.component.IComponentConfigBean;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.serveraction.IServerAction;

public class RemoveTestAnswer  implements IServerAction {


	@Override
	public String executeServerAction(final HttpServletRequest request,
			HttpServletResponse reponse, final List<Map<String, String>> lstData) {
		final Connection conn = Config.getInstance().getDataSource("").getConnection();//取配置的默认数据源的连接
		//final UserInfo userInfo = (UserInfo)request.getSession().getAttribute("admin");//获取当前登录的吗用户名--作为查岗人填入查岗表中
		//final long timeInterval = 5 * 1000;//设置查岗应答时间（5分钟=300 * 1000）
		final Date date = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String currentTime = sdf.format(date);//当前时间
		final String unitName = lstData.get(0).get("UnitName");//获取页面传递的部门名
		final String unitId = lstData.get(0).get("UnitId");//获取页面单位所属ID
		final HttpSession session = request.getSession();
		Statement st = null;
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!unitName.trim().equals("") && unitName != null){
			try {	
				st = conn.createStatement();
				String sql = "update UnitInfo set IsRemove=0 where id = '"+unitId+"'";				
				st.executeUpdate(sql);
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					if(st != null) st.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			session.setAttribute("currentTime", currentTime);//将当前的时间添加到session中座位回显应答的条件	
			
		}
		return "成功完成";
	}

	@Override
	public String executeSeverAction(ReportRequest arg0,
			IComponentConfigBean arg1, List<Map<String, String>> lstData,
			Map<String, String> mCustomizedData) {
		System.out
				.println("调用executeSeverAction(ReportRequest rrequest,IComponentConfigBean ccbean,List<Map<String,String>> lstData)方法...");
		// rrequest.getWResponse().getMessageCollector().success("调用服务器端方法成功");//向前台提示一条信息，这里还可以终止后续处理
		printLstParams(lstData);// 打印客户端传过来的参数
		return "调用成功!!!";
	}

	private void printLstParams(List<Map<String, String>> lstData) {
		if (lstData == null || lstData.size() == 0) {
			System.out.println("无参数");
		} else {
			System.out.print("客户端传入参数列表：：：");
			for (Map<String, String> mParams : lstData) {
				StringBuffer paramBuf = new StringBuffer();
				for (Entry<String, String> entryTmp : mParams.entrySet()) {
					paramBuf.append("[paramname:" + entryTmp.getKey()
							+ ";paramvalue:" + entryTmp.getValue() + "]");
				}
				System.out.print("{" + paramBuf.toString() + "};");
			}
			System.out.println(" ");
		}
	}



}
