package com.ccu.dao.basicdata;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.ccu.dao.DaoSupport;

import com.ccu.model.basicdata.EventTest;

@Repository("eventTestDao")
@Transactional
public class EventTestDaoImpl extends DaoSupport<EventTest> implements EventTestDao{


}
