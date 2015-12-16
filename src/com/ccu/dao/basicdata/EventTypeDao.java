package com.ccu.dao.basicdata;

import com.ccu.dao.BaseDao;
import com.ccu.model.basicdata.EventType;

public interface EventTypeDao extends BaseDao<EventType> {
	public String getTypeId(String typeName);
}
