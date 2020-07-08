package com.project.comic.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	// 유저 포인트 업
	public int upUserPoint(UserDTO userDto, int upPoint) {
		userDto.setPoint(userDto.getPoint() + upPoint );
		return userDao.updateUser(userDto);
	}
	
	// 유저 포인트 다운
	public int downUserPoint(UserDTO userDto, int downPoint) {
		userDto.setPoint(userDto.getPoint() - downPoint );
		return userDao.updateUser(userDto);
	}

	// 유저 프로필 변경 update
	public int changedUserProfile(UserDTO userDto) {
		return userDao.updateUser(userDto);
	}
	
	// return 값 설명
	// 실패 null, 성공 userDTO
	@Transactional
	public UserVO logingUser(Map mapLogin) {
		
	     System.out.println("id: "+mapLogin.get("id"));
	     System.out.println("id: "+mapLogin.get("pwd"));
         UserVO getUser = userDao.loginUser(mapLogin); 
         
         return getUser;		
	}
	
	// id 중복 검사
	public int checkUserId(String id) {
		return userDao.checkUser(id);
	}
	
	// id 중복 검사 후 insert
	public int joinUser(UserDTO userDto) {
		int result = checkUserId(userDto.getId());
		
		if( result == 0)
			return userDao.addUser(userDto);
		else {
			System.out.println("existed id insert error");
			return 0;
		}
	}
}
