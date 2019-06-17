package com.otr.tea.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.otr.tea.po.User;

public class UserDaoImpl implements UserDao {
	private SqlSessionFactory sqlSessionFactory;
	
	

	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}



	@Override
	public User findUserById(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("test.findUserById", id);
		return user;
	}

}
