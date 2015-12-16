package com.ccu.dao.system;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.system.Role;

@Repository("roleDao")
@Transactional
public class RoleDaoImpl extends DaoSupport<Role> implements RoleDao {

}
