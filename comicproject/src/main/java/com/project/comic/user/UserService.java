package com.project.comic.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 유저 포인트 업
	public int upUserPoint(UserDTO userDto, int upPoint) {
		userDto.setPoint(userDto.getPoint() + upPoint);
		return userDao.updateUser(userDto);
	}

	// 유저 포인트 다운
	public int downUserPoint(UserDTO userDto, int downPoint) {
		userDto.setPoint(userDto.getPoint() - downPoint);
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

		System.out.println("id: " + mapLogin.get("id"));
		System.out.println("pwd: " + mapLogin.get("pwd"));
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

		if (result == 0)
			return userDao.addUser(userDto);
		else {
			System.out.println("existed id insert error");
			return 0;
		}
	}

	public List findId(Map findIdinfo) {
		return userDao.findId(findIdinfo);
	}

	public List findPwd(HashMap findPwdinfo) {
		return userDao.findPwd(findPwdinfo);
	}

	public UserVO myInfo(String id) {
		return userDao.myInfo(id);
	}
//회원가입 정보 유효성 검사
	public boolean CheckValude(UserDTO udto) {
		boolean check = false;
		System.out.println("유효성 검사 안 : "+udto.toString());
		
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		
		String pwPattern = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^*+=-])(?=.*[0-9]).{8,25}$"; //
		
		String Phone = "^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$"; //
		
		String Birth = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(udto.getId());
		
		if (m.matches()) {	check = true;	}else {System.out.println("check id : false"); return false;}
		p = Pattern.compile(pwPattern);
		m = p.matcher(udto.getPwd());
		if (m.matches()) {	check = true;	}else {System.out.println("check pwd : false");return false;}
		p = Pattern.compile(Phone);
		m = p.matcher(udto.getPhone());
		if (m.matches()) {	check = true;	}else {System.out.println("check phone : false");return false;}
		p = Pattern.compile(Birth);
		m = p.matcher(udto.getBirth());
		if (m.matches()) {	check = true;	}else {System.out.println("check birth : false");return false;}
		
		return check;

	}
}
