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
		if(!account.equals("") && !password.equals("")){//����û��������벻Ϊ��
			String where = "where account=? and password=?";//���ò�ѯ����
			Object[] queryParams = {account,password};//���ò�����������
			List<UserInfo> list = find(-1, -1, where, queryParams).getList();//ִ�в�ѯ����
			if(list != null && list.size() > 0){//���list���ϲ�Ϊ��
				return list.get(0);//����List�еĵ�һ���洢����
			}
		}
		return null;//���ؿ�ֵ
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
