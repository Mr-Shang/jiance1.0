package com.ccu.wabacus.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wabacus.config.Config;
import com.wabacus.config.component.IComponentConfigBean;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.serveraction.IServerAction;

public class Test2 implements IServerAction
{
    public String executeSeverAction(ReportRequest rrequest,IComponentConfigBean ccbean,List<Map<String,String>> lstData,Map<String,String> mCustomizedData)
    {
        if(lstData==null||lstData.size()==0)
        {
            System.out.println("无参数");
        }else
        {
            String str = mCustomizedData.get("str");
            String no[] = str.split("[+]");
            String unitid = no[0];
            double count2 = Double.parseDouble(no[1]);
            double count3 = 0.0;
            double testresult2=0.0;
            double testresult3 = 0.0;
            double testresult = 0.0;
        	double nodenum = 0.0;
        	double unitnodenum = 0.0;
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
            
        	//用list辅助查询不同报警源所发生故障的总次数
            List<String> list = new LinkedList<String>();
            for(Map<String,String> mParams:lstData)
            {
                for(Entry<String,String> entryTmp:mParams.entrySet())
                {
                	if(entryTmp.getKey().equals("AlertSource")){
                		if(list.contains(entryTmp.getValue())){
                			list = list;
                		}else{
                			list.add(entryTmp.getValue());
                		}
                	}
                }
            }
            count3 = list.size();
            
            //查询数据库获取对应单位信息（主要是节点总数）
            String sql1 = "select * from UnitInfo where id='"+unitid+"'";
            String sql2 = "select count=count(*) from InterfaceInfo where UnitId='"+unitid+"'";
            Connection conn=Config.getInstance().getDataSource("").getConnection();//取配置的默认数据源的连接           
            //计算（报警故障）完好率
            PreparedStatement pstmtUnit1 = null;
            ResultSet rsUnit1 = null;
            try{
            	pstmtUnit1=conn.prepareStatement(sql1);
                rsUnit1 = pstmtUnit1.executeQuery();
                if(rsUnit1.next()){
                	nodenum = Double.parseDouble(rsUnit1.getString("NodeNum"));
                }
                testresult2 = (nodenum-count3)/nodenum*0.5;
            }catch(Exception e){
            	e.printStackTrace();
            }
            //计算（水系统）完好率
            PreparedStatement pstmtUnit2 = null;
            ResultSet rsUnit2 = null;
            try{
            	pstmtUnit2=conn.prepareStatement(sql2);
                rsUnit2 = pstmtUnit2.executeQuery();
                if(rsUnit2.next()){
                	unitnodenum = Double.parseDouble(rsUnit2.getString("count"));
                }
                testresult3 = (unitnodenum-count2)/unitnodenum*0.5;
            }catch(Exception e){
            	e.printStackTrace();
            }
            //计算完好率
            testresult = testresult2 + testresult3;
            //将计算完好率结果等信息存入数据库
            PreparedStatement pstmt=null;
            try{
            	conn.setAutoCommit(false);
            	pstmt=conn.prepareStatement("update Evaluate set GoodResult='"+testresult+"' where year(AssessmentTime)="+currentyear+" and month(AssessmentTime)="+currentmonth+" and UnitId='"+unitid+"'");
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
        rrequest.getWResponse().getMessageCollector().success("完好率计算完成！");//向前台提示一条信息，这里还可以终止后续处理
        return "调用成功!!!";
    }
    
	@Override
	public String executeServerAction(HttpServletRequest arg0,
			HttpServletResponse arg1, List<Map<String, String>> arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}

