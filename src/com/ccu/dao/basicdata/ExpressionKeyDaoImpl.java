package com.ccu.dao.basicdata;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;

import com.ccu.model.basicdata.ExpressionKey;


@Repository("expressionKeyDao")
@Transactional
public class ExpressionKeyDaoImpl extends DaoSupport<ExpressionKey>  implements ExpressionKeyDao {

}
