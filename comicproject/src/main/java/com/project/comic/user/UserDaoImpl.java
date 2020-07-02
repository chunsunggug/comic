package com.project.comic.user;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class UserDaoImpl implements UserDao {

	private SqlSessionTemplate sqlMap;
	
	
	public UserDaoImpl(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}


	@Override
	public List userList() {
		List rs = sqlMap.selectList("userList");
		return rs;
	}

}
