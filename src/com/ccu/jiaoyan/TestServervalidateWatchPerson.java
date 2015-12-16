package com.ccu.jiaoyan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wabacus.system.ReportRequest;

public class TestServervalidateWatchPerson
{
    public static boolean isUniquePersonId(ReportRequest rrequest,String PersonId,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        if(PersonId==null||PersonId.trim().equals("")) return true;//为空不在这里校验，而是在isNotEmpty()中校验，所以直接返回true
        System.out.println("进行isUniqueAppCode()服务器端校验时各列的新旧值为：");
        for(Entry<String,Object> entryTmp:mValues.entrySet())
        {
            System.out.print(entryTmp.getKey()+"="+entryTmp.getValue()+";;");
        }
        System.out.println();
        PersonId=PersonId.trim();
        String oldPersonId=(String)mValues.get("PersonId__old");//取到修改前的身份证
        if(PersonId.equals(oldPersonId)) return true;//本次没有修改PersonId，则不可能存在重复
        Connection conn=rrequest.getConnection();
        Statement stmt=null;
        try
        {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from WatchPersonInfo where PersonId='"+PersonId+"'");
            rs.next();
            int count=rs.getInt(1);
            rs.close();
            return count==0;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }finally
        {
            try
            {
                if(stmt!=null) stmt.close();
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static boolean isValidPersonId(ReportRequest rrequest,String PersonId,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        lstErrorMessages.clear();//清掉配置的出错提示信息，完全由自己进行提示
        if(PersonId==null||PersonId.trim().equals(""))
        {
            rrequest.addServerValidateParams("errormessage","身份证不能为空");
            return false;
        }
        if(!isUniquePersonId(rrequest,PersonId,mValues,lstErrorMessages))
        {
            rrequest.addServerValidateParams("errormessage","此身份证号已经存在");
            return false;
        }
        return true;
    }
    public static boolean isUniqueWorkCard(ReportRequest rrequest,String WorkCard,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        if(WorkCard==null||WorkCard.trim().equals("")) return true;//为空不在这里校验，而是在isNotEmpty()中校验，所以直接返回true
        System.out.println("进行isUniqueAppCode()服务器端校验时各列的新旧值为：");
        for(Entry<String,Object> entryTmp:mValues.entrySet())
        {
            System.out.print(entryTmp.getKey()+"="+entryTmp.getValue()+";;");
        }
        System.out.println();
        WorkCard=WorkCard.trim();
        String oldWorkCard=(String)mValues.get("WorkCard__old");//取到修改前的上岗证
        if(WorkCard.equals(oldWorkCard)) return true;//本次没有修改WorkCard，则不可能存在重复
        Connection conn=rrequest.getConnection();
        Statement stmt=null;
        try
        {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from WatchPersonInfo where WorkCard='"+WorkCard+"'");
            rs.next();
            int count=rs.getInt(1);
            rs.close();
            return count==0;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }finally
        {
            try
            {
                if(stmt!=null) stmt.close();
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static boolean isValidWorkCard(ReportRequest rrequest,String WorkCard,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        lstErrorMessages.clear();//清掉配置的出错提示信息，完全由自己进行提示
        if(WorkCard==null||WorkCard.trim().equals(""))
        {
            rrequest.addServerValidateParams("errormessage","上岗证不能为空");
            return false;
        }
        if(!isUniqueWorkCard(rrequest,WorkCard,mValues,lstErrorMessages))
        {
            rrequest.addServerValidateParams("errormessage","此上岗证号已经存在");
            return false;
        }
        return true;
    }
}
