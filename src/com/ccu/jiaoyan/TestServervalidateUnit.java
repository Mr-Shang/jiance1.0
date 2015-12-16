package com.ccu.jiaoyan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wabacus.system.ReportRequest;

public class TestServervalidateUnit
{
    public static boolean isUniqueUnitName(ReportRequest rrequest,String UnitName,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        if(UnitName==null||UnitName.trim().equals("")) return true;//为空不在这里校验，而是在isNotEmpty()中校验，所以直接返回true
        System.out.println("进行isUniqueAppCode()服务器端校验时各列的新旧值为：");
        for(Entry<String,Object> entryTmp:mValues.entrySet())
        {
            System.out.print(entryTmp.getKey()+"="+entryTmp.getValue()+";;");
        }
        System.out.println();
        UnitName=UnitName.trim();
        String oldUnitName=(String)mValues.get("UnitName__old");//取到修改前的单位名称
        if(UnitName.equals(oldUnitName)) return true;//本次没有修改UnitName，则不可能存在重复
        Connection conn=rrequest.getConnection();
        Statement stmt=null;
        try
        {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from UnitInfo where UnitName='"+UnitName+"'");
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
    public static boolean isValidUnitName(ReportRequest rrequest,String UnitName,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        lstErrorMessages.clear();//清掉配置的出错提示信息，完全由自己进行提示
        if(UnitName==null||UnitName.trim().equals(""))
        {
            rrequest.addServerValidateParams("errormessage","单位名称不能为空");
            return false;
        }
        if(!isUniqueUnitName(rrequest,UnitName,mValues,lstErrorMessages))
        {
            rrequest.addServerValidateParams("errormessage","此单位名称已经存在");
            return false;
        }
        return true;
    }
    public static boolean isUniqueUnitCode(ReportRequest rrequest,String UnitCode,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        if(UnitCode==null||UnitCode.trim().equals("")) return true;//为空不在这里校验，而是在isNotEmpty()中校验，所以直接返回true
        System.out.println("进行isUniqueAppCode()服务器端校验时各列的新旧值为：");
        for(Entry<String,Object> entryTmp:mValues.entrySet())
        {
            System.out.print(entryTmp.getKey()+"="+entryTmp.getValue()+";;");
        }
        System.out.println();
        UnitCode=UnitCode.trim();
        String oldUnitCode=(String)mValues.get("UnitCode__old");//取到修改前的单位编码
        if(UnitCode.equals(oldUnitCode)) return true;//本次没有修改UnitCode，则不可能存在重复
        Connection conn=rrequest.getConnection();
        Statement stmt=null;
        try
        {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from UnitInfo where UnitCode='"+UnitCode+"'");
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

    public static boolean isValidUnitCode(ReportRequest rrequest,String UnitCode,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        lstErrorMessages.clear();//清掉配置的出错提示信息，完全由自己进行提示
        if(UnitCode==null||UnitCode.trim().equals(""))
        {
            rrequest.addServerValidateParams("errormessage","单位编码不能为空");
            return false;
        }
        if(!isUniqueUnitCode(rrequest,UnitCode,mValues,lstErrorMessages))
        {
            rrequest.addServerValidateParams("errormessage","此单位编码已经存在");
            return false;
        }
        return true;
    }
}
