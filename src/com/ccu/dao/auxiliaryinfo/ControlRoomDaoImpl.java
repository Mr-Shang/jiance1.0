package com.ccu.dao.auxiliaryinfo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.auxiliaryinfo.ControlRoomInfo;

@Repository("controlRoomDao")
@Transactional
public class ControlRoomDaoImpl extends DaoSupport<ControlRoomInfo> implements
		ControlRoomDao {

}
