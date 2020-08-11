package com.project.comic.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
		UserDTO userDTO = (UserDTO) sqlMap.selectOne("getUser", id);
		return userDTO;
	}

	@Override
	public int deleteUser(int uidx) {
		System.out.println("dao impl id : "+uidx);
		return sqlMap.update("deleteUser",uidx);
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
		int count = sqlMap.selectOne("checkUser", id);
		return count;
	}

	@Override
	public int updateUser(UserDTO userDto) {
		return sqlMap.update("updateUser", userDto);
    }

	@Override
	public int addAddr(Map addr) {
		return sqlMap.insert("addAddr", addr);
	}

	@Override
	public UserVO loginUser(Map login) {
		UserVO userVO = sqlMap.selectOne("loginUser", login);
		return userVO;
	}

	@Override
	public List findId(Map findIdinfo) {
		return sqlMap.selectList("findId",findIdinfo);
	}

	@Override
	public List findPwd(Map findPwdinfo) {
		return sqlMap.selectList("findPwd",findPwdinfo);
	}

	@Override
	public UserVO myInfo(String id) {
		return sqlMap.selectOne("myInfo",id);
	}

	@Override
	public int deleteCancleUser(String id) {
		return sqlMap.update("deleteCancleUser", id);
	}

	@Override
	public int parserMail(Map mail) {
		return sqlMap.update("mailCheck", mail);
	}

	@Override
	public int MailConfirm(HashMap<String, String> mailInfo) {
		return sqlMap.update("mailConfirm", mailInfo);
	}

	@Override
	public int payPoint(int uidx, int point) {
		Map param = new HashMap();
		param.put("uidx", uidx);
		param.put("point", point);
		
		return sqlMap.update("payPoint", param);
	}

}
