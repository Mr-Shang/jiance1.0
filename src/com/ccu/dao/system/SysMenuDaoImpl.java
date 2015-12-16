package com.ccu.dao.system;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.system.SysMenu;


@Repository("sysMenuDao")
@Transactional
public class SysMenuDaoImpl extends DaoSupport<SysMenu> implements SysMenuDao {

}
