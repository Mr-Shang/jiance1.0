package com.ccu.dao.system;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.system.LogInfo;

@Repository("logDao")
@Transactional
public class LogDaoImpl extends DaoSupport<LogInfo> implements LogInfoDao {

}
