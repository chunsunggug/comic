package com.project.comic;


import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.user.UserDTO;
import com.project.comic.user.UserDao;

@Controller
public class HomeController {
	

	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Set pathSet = request.getSession().getServletContext().getResourcePaths("/");
		
		List<UserDTO> ls = userDao.getUserAll();
		UserDTO getuserDto = userDao.getUser("id2");
		getuserDto.setId("id10");
		userDao.addUser(getuserDto);
		userDao.deleteUserAll();
		
		System.out.println(ls.get(0).getName());
		System.out.println(getuserDto);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		
		return mv;
	}	
}