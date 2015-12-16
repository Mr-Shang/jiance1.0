package com.ccu.police;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccu.dao.basicdata.EventDao;
import com.ccu.dao.basicdata.ExpressionKeyDao;
import com.ccu.model.basicdata.EventInfo;
import com.ccu.model.basicdata.ExpressionKey;


public class EventAnalysis {
	
	@Autowired
	protected ExpressionKeyDao expressionKeyDao;
	@Autowired
	protected EventDao eventDao; 
	
	public String analysis() {
		String eventName = "";
		List<ExpressionKey> list = expressionKeyDao.find(-1, -1).getList();
		if(list.size() > 0) {
			for(ExpressionKey key : list){
				//String 
			}
			
		}
		return eventName;
	}
}
