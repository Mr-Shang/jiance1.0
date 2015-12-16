package com.ccu.wabacus.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wabacus.config.Config;
import com.wabacus.config.component.IComponentConfigBean;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.serveraction.IServerAction;

public class Test implements IServerAction
{
	
    public String executeSeverAction(ReportRequest rrequest,IComponentConfigBean ccbean,List<Map<String,String>> lstData,Map<String,String> mCustomizedData)
    {
        
        double testcount1 = 0.0;
    	double testresult1=0.0;
    	double nodenum = 0.0;
    	String unitid = "";
    	String unitname = "";
    	String headname = "";
    	String headtel = "";
    	String time = "";
    	int currentyear = 0;
    	int currentmonth = 0;
    	
    	//获取系统当前时间
    	Date dt=new Date();
    	SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
    	time=matter1.format(dt);
    	//对每个时间域单独修改，获得年、月
    	Calendar c = Calendar.getInstance();
    	currentyear = c.get(Calendar.YEAR);
    	currentmonth = c.get(Calendar.MONTH) + 1;
    	
        if(lstData==null||lstData.size()==0)
        {
            System.out.println("无参数");
        }else
        {	//统计对应单位的测试次数
            for(Map<String,String> mParams:lstData)
            {
                for(Entry<String,String> entryTmp:mParams.entrySet())
                {
                	if(entryTmp.getKey().equals("Count") && entryTmp.getValue().equals("0")){
                		 testcount1 = testcount1;
                	}else if(entryTmp.getKey().equals("Count")){
                		testcount1 = testcount1 + 1 ;
                	}
                	if(entryTmp.getKey().equals("UnitId")){
                		 unitid = entryTmp.getValue();
                	}
                }
            }
            
            //查询数据库获取对应单位信息（主要是节点总数）
            String sql1 = "select * from UnitInfo where id='"+unitid+"'";
            //查询数据库测试时间的年月，用来判断是否为插入、更新操作
            String sql2 = "select * from Evaluate where year(AssessmentTime)="+currentyear+" and month(AssessmentTime)="+currentmonth+" and UnitId='"+unitid+"'";                      
            Connection conn=Config.getInstance().getDataSource("").getConnection();//取配置的默认数据源的连接           
            //计算抽检率结果
            PreparedStatement pstmtUnit = null;
            ResultSet rsUnit = null;
            try{
            	pstmtUnit=conn.prepareStatement(sql1);
                rsUnit = pstmtUnit.executeQuery();
                if(rsUnit.next()){
                	unitname = rsUnit.getString("UnitName");
                	headname = rsUnit.getString("HeadName");
                	headtel = rsUnit.getString("HeadTel");
                	nodenum = Double.parseDouble(rsUnit.getString("NodeNum"));
                }
                testresult1 = testcount1/nodenum;
            }catch(Exception e){
            	e.printStackTrace();
            }       
            //将计算抽检率结果等信息存入数据库
            PreparedStatement pstmt=null;
            PreparedStatement pstmtDate = null;
            ResultSet rsDate = null;
            try{
            	pstmtDate=conn.prepareStatement(sql2);
                rsDate = pstmtDate.executeQuery();
                if(rsDate.next()){
                	try{
            			conn.setAutoCommit(false);
            			pstmt=conn.prepareStatement("update Evaluate set TestResult='"+testresult1+"',UnitName='"+unitname+"',HeadName='"+headname+"',HeadTel='"+headtel+"',AssessmentTime='"+time+"' where year(AssessmentTime)="+currentyear+" and month(AssessmentTime)="+currentmonth+" and UnitId='"+unitid+"'");
        				pstmt.executeUpdate();
                        conn.commit();
                        pstmt.close();
                	}catch(Exception e2){
                    	try {
            				conn.rollback();
            			}catch (SQLException e3) {
            				e3.printStackTrace();
            				}
                        e2.printStackTrace();
                    	}
                }else{
                	try{
            			conn.setAutoCommit(false);
            			pstmt=conn.prepareStatement("insert into Evaluate(TestResult,UnitName,HeadName,HeadTel,AssessmentTime,UnitId) values(?,?,?,?,?,?)");
        				pstmt.setDouble(1,testresult1);
                        pstmt.setString(2,unitname);
                        pstmt.setString(3,headname);
                        pstmt.setString(4,headtel);
                        pstmt.setString(5,time);
                        pstmt.setString(6,unitid);
                        pstmt.executeUpdate();
                        conn.commit();                     
                        pstmt.close();
                	}catch(Exception e2){
                    	try {
            				conn.rollback();
            			}catch (SQLException e3) {
            				e3.printStackTrace();
            				}
                        e2.printStackTrace();
                    	}
                }
            }catch(Exception e1){
            	e1.printStackTrace();
            }                                
        }
        String result= unitid+"*"+testresult1;
        return result;
    }

	@Override
	public String executeServerAction(HttpServletRequest arg0,
			HttpServletResponse arg1, List<Map<String, String>> arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}

