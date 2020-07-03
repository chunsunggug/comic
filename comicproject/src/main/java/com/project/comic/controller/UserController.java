package com.project.comic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.user.UserDTO;
import com.project.comic.user.UserDao;

@Controller
public class UserController {


	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping(value = "/signin.do",method = RequestMethod.GET)
	public ModelAndView Login() {
    	
		List<UserDTO> ls = userDao.userList();
		
		System.out.println("user list" + ls);
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("user/login");
    	return mv;
    }
	
}
