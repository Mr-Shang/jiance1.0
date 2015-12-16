package com.ccu.wabacus.checkInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccu.model.system.UserInfo;
import com.wabacus.config.Config;
import com.wabacus.config.component.IComponentConfigBean;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.serveraction.IServerAction;

/**
 * 定时查岗应答（人工查看）
 * 
 * @author CCU
 * 
 */
public class CheckAllAnswer implements IServerAction {

	@Override
	public String executeServerAction(final HttpServletRequest request,
			HttpServletResponse reponse, final List<Map<String, String>> lstData) {
		HttpSession session = request.getSession();
		String type = "";
		for(Map<String,String> mParams:lstData){
			String updatetype=mParams.get("type");
            if(updatetype!=null){
            	session.setAttribute("type", updatetype);
            	type=(String) session.getAttribute("type");
            }else{
            	type=(String) session.getAttribute("type");
            }
		}
		
		if(type!=null){
			System.out.println(type);
		final Connection conn = Config.getInstance().getDataSource("").getConnection();//取配置的默认数据源的连接
		final UserInfo userInfo = (UserInfo)request.getSession().getAttribute("admin");//获取当前登录的吗用户名--作为查岗人填入查岗表中
		//final long timeInterval = 5 * 1000;//设置查岗应答时间（5分钟=300 * 1000）
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		PreparedStatement pstMachine = null;
		ResultSet rsMachine = null;		
		String sqlMachine = "select m.MachineCode,m.UnitId,u.UnitName from MachineInfo m,UnitInfo u where m.MachineType='传输装置' and u.id = UnitId;"; 
		String machineCode = "";
		String unitId = "";
		String unitName = "";
		String checkPersonName = userInfo.getUserName();
		String checkTime = sdf.format(new Date());
		System.out.println(checkTime);
		try {
			pstMachine = conn.prepareStatement(sqlMachine);
			rsMachine = pstMachine.executeQuery();
			while(rsMachine.next()) {
				machineCode = rsMachine.getString("MachineCode");
				unitId = rsMachine.getString("UnitId");
				unitName = rsMachine.getString("UnitName");
				System.out.println(machineCode);
				System.out.println(unitId);
				System.out.println(unitName);
				PreparedStatement pstCheck = null;
				conn.setAutoCommit(false);
				try{
					pstCheck = conn.prepareStatement("insert into CheckInfo(CheckPersonName,CheckTime,UnitId,UnitName,IsAnswer,IsSend,MachineCode,CheckType,Sum) values(?,?,?,?,?,?,?,?,?)");
					pstCheck.setString(1, checkPersonName);
					pstCheck.setString(2, checkTime);
					pstCheck.setString(3, unitId);
					pstCheck.setString(4, unitName);
					pstCheck.setString(5, "false");
					pstCheck.setString(6, "false");
					pstCheck.setString(7, machineCode);
					pstCheck.setString(8, type);
					pstCheck.setInt(9, 0);
					pstCheck.executeUpdate();
					conn.commit();
					//session.setAttribute("CheckTime", checkTime);//将当前的查岗时间添加到session中座位回显应答的条件
					session.removeAttribute("type");
				} catch (Exception e) {
					conn.rollback();
					e.printStackTrace();
				} finally {
					try {
						if(pstCheck != null) pstCheck.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查岗失败");
		} finally {
			try {
				if(pstMachine != null) pstMachine.close();
				if(rsMachine != null) rsMachine.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
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
