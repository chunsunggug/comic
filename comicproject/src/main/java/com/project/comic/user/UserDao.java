package com.project.comic.user;

import java.util.List;
import java.util.Map;

public interface UserDao {
	public List getUserAll();
	public UserDTO getUser(String id);
	public int deleteUser(int uidx);
	public int deleteUserAll();
	public int addUser(UserDTO userDto);
	public int updateUser(UserDTO userDto);
	public int checkUser(String id);
	public int addAddr(Map addr);
	public UserVO loginUser(Map login);
	public List findId(Map findIdinfo);
	public List findPwd(Map findPwdinfo);
	public UserVO myInfo(String id);
	public int deleteCancleUser(String id);
	
}
