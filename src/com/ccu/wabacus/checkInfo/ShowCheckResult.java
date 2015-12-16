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
 * 查询显示查岗结果
 * @author CCU
 *
 */
public class ShowCheckResult implements IServerAction {

	@Override
	public String executeServerAction(final HttpServletRequest request,
			HttpServletResponse reponse, final List<Map<String, String>> lstData) {
		final Connection conn=Config.getInstance().getDataSource("").getConnection();//取配置的默认数据源的连接
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		final HttpSession session = request.getSession();
		
		Runnable runnable = new Runnable() {
			public void run() {
				/**
				 * 1.将页面信息传递过来 
				 * 2.将部门名称存放到request中  session  jdbc
				 * 3.将页面值插入到查岗表中
				 * 4.利用线程计时返回报表中--进行查询显示
				 */
				// ------- code for task to run
				try{
						PreparedStatement pstmt=null;
						ResultSet rs = null ;
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						String sql = "select * from CheckInfo where UnitId = '"+session.getAttribute("")+"'";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();
						while(rs.next()) {
							Map<String, Object> map = new HashMap<String,Object>();
							for(int i = 0; i < rsmd.getColumnCount(); i++ ){
								String key = rsmd.getColumnName(i+1);
								Object value = rs.getObject(key);
								map.put(key, value);
							}
							list.add(map);
						}
	                    pstmt.close();
				}catch(Exception e) {
					System.out.println("查岗失败");
					e.printStackTrace();
				}
				// ------- ends here
			}
		};
		return "SUCCESS_CHECK";
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
