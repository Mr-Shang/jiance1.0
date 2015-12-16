package com.ccu.action.basicdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ccu.action.BaseAction;
import com.ccu.model.PageModel;

import com.ccu.model.basicdata.MachineInfo;
import com.ccu.model.basicdata.UnitInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Action
 * @author liuzy
 *
 */

@Scope("prototype")
@Controller("machineAction")
public class MachineAction extends BaseAction {


	private static final long serialVersionUID = 1L;

	private JSONArray jsonArray = new JSONArray();
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	
	/**
	 * 返回RTU设备
	 * @return
	 * @throws Exception
	 */
	public String getRTUMchine() throws Exception{
		String where = "where machineType = ?";
		Object[] queryParams = {"RTU"};
		PageModel<MachineInfo> pageModel = machineDao.find(pageNo, 6, where, queryParams);
		if(pageModel != null){
			List<MachineInfo> list = pageModel.getList();
			int totalPages = pageModel.getTotalPages();
			for(MachineInfo machine: list){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("totalPages",totalPages);
				jsonObject.put("pageNo",pageModel.getPageNo());
				jsonObject.put("unitName",machine.getUnitInfo().getUnitName());
				jsonObject.put("machineCode",machine.getMachineCode());
				System.out.println(machine.getMachineCode());
				jsonObject.put("machineName", machine.getMachineName());
				if(machine.getIsUsed()){
					jsonObject.put("state", "联机");
				} else {
					jsonObject.put("state", "脱机");
				}
				jsonArray.add(jsonObject);
				
			}
		}
		return "json";
	}





}
