package com.project.comic.user;

import java.util.List;

public interface UserDao {
	public List getUserAll();
	public UserDTO getUser(String id);
	public void deleteUser(String id);
	public void deleteUserAll();
	public void addUser(UserDTO userDto);
	public int checkUser(String id);
}
