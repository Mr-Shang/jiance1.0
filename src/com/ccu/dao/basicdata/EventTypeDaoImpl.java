package com.ccu.dao.basicdata;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.basicdata.EventType;


@Repository("eventTypeDao")
@Transactional
public class EventTypeDaoImpl extends DaoSupport<EventType> implements
		EventTypeDao {
	
	@Override
	public String getTypeId(String typeName) {
		String where = "where typeName=?";
		Object[] queryParams = {typeName};
		String typeId = "";
		List<EventType> list = find(-1, -1, where, queryParams).getList();
		if(list.size() > 0){
			typeId = list.get(0).getId();
		}
		return typeId;
	}
}
