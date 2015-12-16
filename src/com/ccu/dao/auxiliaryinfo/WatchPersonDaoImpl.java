package com.ccu.dao.auxiliaryinfo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.auxiliaryinfo.WatchPersonInfo;


@Repository("watchPersonDao")
@Transactional
public class WatchPersonDaoImpl extends DaoSupport<WatchPersonInfo> implements
		WatchPersonDao {

}
