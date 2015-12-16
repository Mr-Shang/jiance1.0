package com.ccu.jiaoyan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wabacus.system.ReportRequest;

public class TestServervalidateMachine
{
    public static boolean isUniqueMachineCode(ReportRequest rrequest,String MachineCode,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        if(MachineCode==null||MachineCode.trim().equals("")) return true;//为空不在这里校验，而是在isNotEmpty()中校验，所以直接返回true
        System.out.println("进行isUniqueAppCode()服务器端校验时各列的新旧值为：");
        for(Entry<String,Object> entryTmp:mValues.entrySet())
        {
            System.out.print(entryTmp.getKey()+"="+entryTmp.getValue()+";;");
        }
        System.out.println();
        MachineCode=MachineCode.trim();
        String oldMachineCode=(String)mValues.get("MachineCode__old");//取到修改前的设备编码
        if(MachineCode.equals(oldMachineCode)) return true;//本次没有修改MachineCode，则不可能存在重复
        Connection conn=rrequest.getConnection();
        Statement stmt=null;
        try
        {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from MachineInfo where MachineCode='"+MachineCode+"'");
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

    public static boolean isValidMachineCode(ReportRequest rrequest,String MachineCode,Map<String,Object> mValues,List<String> lstErrorMessages)
    {
        lstErrorMessages.clear();//清掉配置的出错提示信息，完全由自己进行提示
        if(MachineCode==null||MachineCode.trim().equals(""))
        {
            rrequest.addServerValidateParams("errormessage","设备编码不能为空");
            return false;
        }
        if(!isUniqueMachineCode(rrequest,MachineCode,mValues,lstErrorMessages))
        {
            rrequest.addServerValidateParams("errormessage","此设备编码已经存在");
            return false;
        }
        return true;
    }
}
