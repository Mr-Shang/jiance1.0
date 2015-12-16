package com.ccu.reportInterceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.wabacus.config.Config;
import com.wabacus.config.component.application.report.ReportBean;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.component.application.report.configbean.editablereport.AbsEditableReportEditDataBean;
import com.wabacus.system.dataset.update.action.AbsUpdateAction;
import com.wabacus.system.dataset.update.action.rationaldb.AbsRationalDBUpdateAction;
import com.wabacus.system.intercept.AbsInterceptorDefaultAdapter;

public class reportInterceptor extends AbsInterceptorDefaultAdapter {

	@Override
	public int doSavePerAction(ReportRequest rrequest, ReportBean rbean,
			Map<String, String> mRowData, Map<String, String> mParamValues,
			AbsUpdateAction action, AbsEditableReportEditDataBean editbean) {
		// TODO Auto-generated method stub
		
		final Connection conn=Config.getInstance().getDataSource("").getConnection();
		PreparedStatement  pstmt =  null;
		
		Date operatetime=new Date();
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operateTime=format1.format(operatetime);
		String userId=rrequest.getRequest().getSession().getAttribute("userId").toString();
		String loginTime=rrequest.getRequest().getSession().getAttribute("loginTime").toString();
		String userIp=rrequest.getRequest().getSession().getAttribute("remoteAddr").toString();
		String pageid=rrequest.getPagebean().getId();  
			
		  String menuName = "";
		  if(action instanceof AbsRationalDBUpdateAction)
	        {
				  //当前执行的脚本是配置的SQL语句或存储过程
				String sql1=((AbsRationalDBUpdateAction)action).getSqlsp();
				System.out.println(sql1);
				String sql=sql1.substring(0, 6);
	            System.out.println(sql);
	            menuName=sql+" to "+pageid;
	            System.out.println(menuName);
	            }
				  try { 
					String mySQL="insert into LogInfo(UserId,LoginTime,MenuName,OperateTime,UserIp) values (?,?,?,?,?)";
					pstmt=conn.prepareStatement(mySQL);
					pstmt.setString(1, userId);
					pstmt.setString(2, loginTime);
					pstmt.setString(3,menuName);
					pstmt.setString(4,operateTime);
					pstmt.setString(5, "我的IP");
					System.out.println(menuName);
					pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
		        
				  
				
//		        if(mRowData!=null)//mRowData中存放了本条记录各列的值
//		        {
//		            Iterator itKeys=mRowData.keySet().iterator();
//		            while(itKeys.hasNext())
//		            {
//		                String key=(String)itKeys.next();
//		                String value=(String)mRowData.get(key);
//		                System.out.print("["+key+"="+value+"]");
//		            }
//		            System.out.println();
//		        }
//		        if(mParamValues!=null)//存放了相对于本条记录的在<insert/>或<update/>或<delete/>中定义的变量值
//		        {
//		            System.out.print("各变量的值为：");
//		            Iterator itKeys=mParamValues.keySet().iterator();
//		            while(itKeys.hasNext())
//		            {
//		                String key=(String)itKeys.next();
//		                String value=(String)mParamValues.get(key);
//		                System.out.print("["+key+"="+value+"]");
//		            }
//		            System.out.println();
//		        }
		        return super.doSavePerAction(rrequest,rbean,mRowData,mParamValues,action,editbean);
	}
}
	


