package com.project.comic.user;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSessionTemplate sqlMap;

	@Override
	public List<UserDTO> getUserAll() {
		List<UserDTO> rs = sqlMap.selectList("getUserAll");
		return rs;
	}


	@Override
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
	public int checkUser(String id) {
		int count = sqlMap.selectOne("checkUser",id);
		return count;
	}

            @Override
	public void updateUser(UserDTO userDto) {
		sqlMap.update("updateUser", userDto);
    }
}
