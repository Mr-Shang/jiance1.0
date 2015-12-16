package com.ccu.dao.basicdata;



import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;

import com.ccu.model.basicdata.EventInfo;

@Repository("eventDao")
@Transactional
public class EventDaoImpl extends DaoSupport<EventInfo> implements EventDao {
	


}
