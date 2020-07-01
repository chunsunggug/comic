package com.project.comic;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> branch 'master' of https://github.com/chunsunggug/comic.git
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.user.UserDTO;
import com.project.comic.user.UserDao;

@Controller
public class HomeController {
	

	
<<<<<<< HEAD
=======
	@Autowired
	private UserDao userDao;
	
	//private SqlSessionFactory sqlSessionFactory; factory 말고 다오 불러오기 auto가 매칭 시켜줌
>>>>>>> branch 'master' of https://github.com/chunsunggug/comic.git
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Set pathSet = request.getSession().getServletContext().getResourcePaths("/");
		System.out.println("sql 들어가기전 ");
		
		
		
		List<UserDTO> ls = userDao.userList();
		System.out.println("리스트 확인"+ls);
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		//SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
		//UserMapper userMapper = template.getMapper(UserMapper.class);
		//UserDTO userDTO = userMapper.getUser("id");
		
		//System.out.println(userDTO.getId());
		
		return mv;
	}	
}