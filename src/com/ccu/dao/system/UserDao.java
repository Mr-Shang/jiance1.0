package com.ccu.dao.system;

import com.ccu.dao.BaseDao;
import com.ccu.model.system.UserInfo;

public interface UserDao extends BaseDao<UserInfo> {
	public UserInfo login(String account, String password);
	public boolean isUnique(String account);
}
