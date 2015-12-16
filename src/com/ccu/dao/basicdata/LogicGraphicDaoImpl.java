package com.ccu.dao.basicdata;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.basicdata.LogicGraphic;


@Repository("logicGraphicDao")
@Transactional
public class LogicGraphicDaoImpl extends DaoSupport<LogicGraphic> implements
		LogicGraphicDao {

}
