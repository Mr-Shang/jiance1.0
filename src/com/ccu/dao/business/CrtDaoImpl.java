package com.ccu.dao.business;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.business.CrtInfo;


@Repository("crtDao")
@Transactional
public class CrtDaoImpl extends DaoSupport<CrtInfo> implements CrtDao {

}
