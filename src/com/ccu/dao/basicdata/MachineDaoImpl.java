package com.ccu.dao.basicdata;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.basicdata.MachineInfo;


@Repository("machineDao")
@Transactional
public class MachineDaoImpl extends DaoSupport<MachineInfo> implements
		MachineDao {

}
