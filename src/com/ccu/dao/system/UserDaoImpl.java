package com.ccu.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.system.UserInfo;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends DaoSupport<UserInfo> implements UserDao {

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public UserInfo login(String account, String password) {
		if(!account.equals("") && !password.equals("")){//如果用户名和密码不为空
			String where = "where account=? and password=?";//设置查询条件
			Object[] queryParams = {account,password};//设置参数对象数组
			List<UserInfo> list = find(-1, -1, where, queryParams).getList();//执行查询方法
			if(list != null && list.size() > 0){//如果list集合不为空
				return list.get(0);//返回List中的第一个存储对象
			}
		}
		return null;//返回空值
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean isUnique(String account) {
		List<UserInfo> list = getTemplate().find("from Userinfo where account = ?", account);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}

}
