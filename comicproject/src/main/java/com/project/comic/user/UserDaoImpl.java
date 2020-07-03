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
	public int deleteUser(String id) {
		return sqlMap.delete("deleteUser");
	}
	
	@Override
	public int deleteUserAll() {
		return sqlMap.delete("deleteUserAll");
	}


	@Override
	public int addUser(UserDTO userDto) {
		return sqlMap.insert("addUser", userDto);
	}


	@Override
	public int checkUser(String id) {
		int count = sqlMap.selectOne("checkUser",id);
		return count;
	}

    @Override
	public int updateUser(UserDTO userDto) {
		return sqlMap.update("updateUser", userDto);
    }
}
