package com.ccu.dao.basicdata;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.basicdata.InterfaceRecord;


@Repository("recordDao")
@Transactional
public class RecordDaoImpl extends DaoSupport<InterfaceRecord> implements RecordDao {


}
