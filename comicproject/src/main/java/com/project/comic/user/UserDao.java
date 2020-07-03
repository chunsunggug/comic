package com.project.comic.user;

import java.util.List;

public interface UserDao {
	public List getUserAll();
	public UserDTO getUser(String id);
	public int deleteUser(String id);
	public int deleteUserAll();
	public int addUser(UserDTO userDto);
	public int updateUser(UserDTO userDto);
	public int checkUser(String id);
}
