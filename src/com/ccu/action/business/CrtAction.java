package com.ccu.action.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;


import com.ccu.model.basicdata.UnitInfo;
import com.ccu.model.business.CrtInfo;
import com.ccu.util.StringUitl;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��ƵAction
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("crtAction")
public class CrtAction extends BaseAction  {

	private String machineCode;
	private String alertSource;
	public String src;
	



	

	public String getSrc() {
		return src;
	}






	public void setSrc(String src) {
		this.src = src;
	}






	public String getMachineCode() {
		return machineCode;
	}






	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}






	public String getAlertSource() {
		return alertSource;
	}






	public void setAlertSource(String alertSource) {
		this.alertSource = alertSource;
	}






	public String drow() throws Exception {
	
		System.out.println(machineCode);
		
		String source= new String(alertSource.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(alertSource);
		String where = "where machineCode=?";
		Object[] queryParams = {machineCode};
		CrtInfo crt = crtDao.find(-1, -1, where, queryParams).getList().get(0);
		String filePath = crt.getFilePath();
		System.out.println(filePath);
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|\\d+");//汉字正则校验
/*		Matcher m = p.matcher(source);
		String[] strArry = new String[6];
		for (int i = 0; i < 6; i++) {//0,1,2对应回路板、回路、节点
			if (m.find()) {
				strArry[i] = m.group();
				System.out.println(strArry[i] );
			}
		}*/
		String[] strs1 = source.split("回路板");
		String[] strs2 = strs1[1].split("回路");
		String[] strs3 = strs2[1].split("节点");
		String h1 =strs2[0];
		String jd = strs3[0];
		String src = "crt/CrtServlet?file=" + filePath  + "&sb=" +  machineCode  + "&h1=" + h1  +  "&jd=" +  jd;
		System.out.println(src);
		return "drow";

	}


}
