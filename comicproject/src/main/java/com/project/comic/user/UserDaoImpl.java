package com.project.comic.user;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSessionTemplate sqlMap;

	@Override
	public List getUserAll() {
		List rs = sqlMap.selectList("getUserAll");
		return rs;
	}

	public UserDTO getUser(String id) {
		UserDTO userDto = sqlMap.selectOne("getUser", id);
		return userDto;
	}

	@Override
	public void deleteUser(String id) {
		sqlMap.delete("deleteUser");
	}
	
	@Override
	public void deleteUserAll() {
		sqlMap.delete("deleteUserAll");
	}


	@Override
	public void addUser(UserDTO userDto) {
		sqlMap.insert("addUser", userDto);
	}

            @Override
	public void updateUser(UserDTO userDto) {
		sqlMap.update("updateUser", userDto);
            }
}
