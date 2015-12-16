package com.ccu.pageInterceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.wabacus.config.Config;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.intercept.AbsPageInterceptor;

public class ErroeInterfadePageInterceptor extends AbsPageInterceptor {
	public void doEnd(ReportRequest rrequest){
		Date operateTime1=new Date();
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operateTime=format1.format(operateTime1);
		String userid= rrequest.getRequest().getSession().getAttribute("userId").toString();
		String remoteAddr=rrequest.getRequest().getSession().getAttribute("remoteAddr").toString();
	    String logintime=rrequest.getRequest().getSession().getAttribute("loginTime").toString();
		//String opt= rrequest.getRequest().getAttribute("operateTime").toString();
		String menuName=rrequest.getPagebean().getId();
//		String pageid=rrequest.getPagebean().getId();
//		System.out.println(menuName);
//		System.out.println(pageid);

	 
		if(menuName.equalsIgnoreCase("ErrorInterface")){
			System.out.println("执行了if语句！");
			menuName="水系统记录";
		}else{
			System.out.println("没执行if语句");
		}
	 
		final Connection conn=Config.getInstance().getDataSource("").getConnection();
		PreparedStatement  pstmt =  null;
		try{
			
			 String sql="insert into LogInfo(UserId,LoginTime,MenuName,OperateTime,UserIp) values(?,?,?,?,?)";
			 pstmt= conn.prepareStatement(sql);
			 pstmt.setString(1,userid);
             pstmt.setString(2, logintime);
             pstmt.setString(3, menuName);
             pstmt.setString(4, operateTime);
             pstmt.setString(5, remoteAddr);
             pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
	}

}
