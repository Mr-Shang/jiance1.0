package com.ccu.dao.auxiliaryinfo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.auxiliaryinfo.EnterUnitInfo;


@Repository("enterUnitDao")
@Transactional
public class EnterUnitDaoImpl extends DaoSupport<EnterUnitInfo> implements
		EnterUnitDao {

}
