/* 
 * Copyright (C) 2010-2013 星星<349446658@qq.com>
 * 
 * This file is part of Wabacus 
 * 
 * Wabacus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.wabacusdemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wabacus.system.ReportRequest;
import com.wabacus.system.dataset.select.common.AbsCommonDataSetValueProvider;

public class TestGetCityOptionsJavaDataSource extends AbsCommonDataSetValueProvider
{

    public List<Map<String,String>> getLstSelectBoxOptions(ReportRequest rrequest,Map<String,String> mParentInputboxValues)
    {
        List<Map<String,String>> lstResults=new ArrayList<Map<String,String>>();
        if(mParentInputboxValues==null||mParentInputboxValues.size()==0) return lstResults;//依赖的父输入框没有选中数据，则本子选择框也不显示选项
        String province=mParentInputboxValues.entrySet().iterator().next().getValue();
        if(province==null||province.trim().equals("")) return lstResults;//依赖的父输入框没有选中数据，则本子选择框也不显示选项
        String sql="select distinct city from tbl_area where province like '%"+province+"%'";
        try
        {
            Connection conn=rrequest.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            Map<String,String> mOptionTmp=null;
            while(rs.next())
            {
                mOptionTmp=new HashMap<String,String>();
                mOptionTmp.put("label",rs.getString("city"));
                mOptionTmp.put("value",rs.getString("city"));
                lstResults.add(mOptionTmp);
            }            
            rs.close();
            stmt.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<Map<String,String>>();
        }
        return lstResults;
    }
    
    public Map<String,String> getAutoCompleteColumnsData(ReportRequest arg0,Map<String,String> arg1)
    {
        return null;
    }

    public List<Map<String,String>> getDynamicColGroupDataSet(ReportRequest arg0)
    {
        return null;
    }

    public List<Map<String,String>> getLstTypePromptOptions(ReportRequest arg0,String arg1)
    {
        return null;
    }

}

