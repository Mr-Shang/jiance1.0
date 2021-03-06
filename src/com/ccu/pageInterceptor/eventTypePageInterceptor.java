package com.ccu.pageInterceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.wabacus.config.Config;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.intercept.AbsPageInterceptor;

public class eventTypePageInterceptor extends AbsPageInterceptor {
	/*public void doStart(ReportRequest rrequest){
		Date operateTime1=new Date();
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String operateTime=format1.format(operateTime1);
		rrequest.getRequest().setAttribute("operateTime", operateTime);
		
		//eventInfoPageInterceptor eventInfoPageInterceptor=new eventInfoPageInterceptor();
		String userid= rrequest.getRequest().getSession().getAttribute("userId").toString();
		String remoteAddr=rrequest.getRequest().getSession().getAttribute("remoteAddr").toString();
	    String logintime=rrequest.getRequest().getSession().getAttribute("loginTime").toString();
	    
	    System.out.println(logintime);
	    
		final Connection conn=Config.getInstance().getDataSource("").getConnection();
		PreparedStatement  pstmt =  null;
		try{
			 String sql="insert into LogInfo(UserId,LoginTime,OperateTime,UserIp) values(?,?,?,?)";
			 pstmt= conn.prepareStatement(sql);
			 pstmt.setString(1,userid);
             pstmt.setString(2, logintime);
             pstmt.setString(3, operateTime);
             //pstmt.setString(4, "0000-00-00 00：00:00");
             pstmt.setString(4, remoteAddr);
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
	  }*/
	
	public void doEnd(ReportRequest rrequest){
	
		//String userid= rrequest.getRequest().getSession().getAttribute("userId").toString();
		
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

		if(menuName.equalsIgnoreCase("eventType")){
			menuName="事件类型管理";
		}
		else{
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
