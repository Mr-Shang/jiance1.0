package com.ccu.dao.basicdata;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.basicdata.UnitInfo;


@Repository("unitDao")
@Transactional
public class UnitDaoImpl extends DaoSupport<UnitInfo> implements UnitDao {

}
