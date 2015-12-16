package com.ccu.jiaoyan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wabacus.system.ReportRequest;

public class TestServervalidateControlRoom
{
    public static boolean isUniqueControlRoomCode(ReportRequest rrequest,String ControlRoomCode,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        if(ControlRoomCode==null||ControlRoomCode.trim().equals("")) return true;//为空不在这里校验，而是在isNotEmpty()中校验，所以直接返回true
        System.out.println("进行isUniqueAppCode()服务器端校验时各列的新旧值为：");
        for(Entry<String,Object> entryTmp:mValues.entrySet())
        {
            System.out.print(entryTmp.getKey()+"="+entryTmp.getValue()+";;");
        }
        System.out.println();
        ControlRoomCode=ControlRoomCode.trim();
        String oldControlRoomCode=(String)mValues.get("ControlRoomCode__old");//取到修改前的消控室编号
        if(ControlRoomCode.equals(oldControlRoomCode)) return true;//本次没有修改ControlRoomCode，则不可能存在重复
        Connection conn=rrequest.getConnection();
        Statement stmt=null;
        try
        {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from ControlRoomInfo where ControlRoomCode='"+ControlRoomCode+"'");
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
    public static boolean isValidControlRoomCode(ReportRequest rrequest,String ControlRoomCode,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        lstErrorMessages.clear();//清掉配置的出错提示信息，完全由自己进行提示
        if(ControlRoomCode==null||ControlRoomCode.trim().equals(""))
        {
            rrequest.addServerValidateParams("errormessage","消控室编号不能为空");
            return false;
        }
        if(!isUniqueControlRoomCode(rrequest,ControlRoomCode,mValues,lstErrorMessages))
        {
            rrequest.addServerValidateParams("errormessage","此消控室编号已经存在");
            return false;
        }
        return true;
    }
    public static boolean isUniqueControlRoomName(ReportRequest rrequest,String ControlRoomName,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        if(ControlRoomName==null||ControlRoomName.trim().equals("")) return true;//为空不在这里校验，而是在isNotEmpty()中校验，所以直接返回true
        System.out.println("进行isUniqueAppCode()服务器端校验时各列的新旧值为：");
        for(Entry<String,Object> entryTmp:mValues.entrySet())
        {
            System.out.print(entryTmp.getKey()+"="+entryTmp.getValue()+";;");
        }
        System.out.println();
        ControlRoomName=ControlRoomName.trim();
        String oldControlRoomName=(String)mValues.get("ControlRoomName__old");//取到修改前的消控室名称
        if(ControlRoomName.equals(oldControlRoomName)) return true;//本次没有修改ControlRoomName，则不可能存在重复
        Connection conn=rrequest.getConnection();
        Statement stmt=null;
        try
        {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from ControlRoomInfo where ControlRoomName='"+ControlRoomName+"'");
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

    public static boolean isValidControlRoomName(ReportRequest rrequest,String ControlRoomName,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        lstErrorMessages.clear();//清掉配置的出错提示信息，完全由自己进行提示
        if(ControlRoomName==null||ControlRoomName.trim().equals(""))
        {
            rrequest.addServerValidateParams("errormessage","消控室名称不能为空");
            return false;
        }
        if(!isUniqueControlRoomName(rrequest,ControlRoomName,mValues,lstErrorMessages))
        {
            rrequest.addServerValidateParams("errormessage","此消控室名称已经存在");
            return false;
        }
        return true;
    }
}
