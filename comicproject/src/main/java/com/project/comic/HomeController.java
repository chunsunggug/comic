package com.project.comic;


import java.util.List;
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
	

	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Set pathSet = request.getSession().getServletContext().getResourcePaths("/");
		
		List<UserDTO> ls = userDao.userList();
		System.out.println(""+ls);
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		
		return mv;
	}	
}