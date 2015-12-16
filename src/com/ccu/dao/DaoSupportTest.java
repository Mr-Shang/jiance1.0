package com.ccu.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ccu.model.PageModel;
import com.ccu.model.system.UserInfo;

/**
 * ������
 * @author Li Chen
 *
 */
public class DaoSupportTest {
	private DaoSupport<UserInfo> daoSupport;//DAO
	@Before
	public void setUp() throws Exception {//��ʼ������������ִ�еķ���
		daoSupport = new DaoSupport<UserInfo>();
	}
	@After
	public void tearDown() throws Exception {//�����������ִ�еķ���
		daoSupport=null;//���ٶ���
	}
	@Test
	public void testFindIntInt() {
		Map<String, String> orderby = new HashMap<String, String>(1);//����Map����
		orderby.put("createTime", "desc");//���ð�����ʱ�䵹������
		PageModel<UserInfo> pageModel = daoSupport.find(1, 3);//ִ�в�ѯ����
		assertNotNull("�޷���ȡ���ݣ�",pageModel);//�ж�find()�����Ƿ�ɹ���ѯ��ֵ
	}
}
