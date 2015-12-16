package com.ccu.dao.basicdata;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.basicdata.InterfaceInfo;


@Repository("interfaceDao")
@Transactional
public class InterfaceDaoImpl extends DaoSupport<InterfaceInfo> implements
		InterfaceDao {

}
