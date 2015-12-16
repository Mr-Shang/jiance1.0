package com.ccu.dao.basicdata;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.basicdata.Evaluate;

@Repository("evaluateDao")
@Transactional
public class EvaluateDaoImpl extends DaoSupport<Evaluate> implements EvaluateDao {

}
