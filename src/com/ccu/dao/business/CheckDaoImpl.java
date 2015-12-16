package com.ccu.dao.business;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.business.CheckInfo;


@Repository("checkDao")
@Transactional
public class CheckDaoImpl extends DaoSupport<CheckInfo> implements CheckDao {


}
