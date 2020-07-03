package com.project.comic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.user.UserDTO;
import com.project.comic.user.UserDao;

@Controller
public class UserController {


	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping(value = "/signin.do",method = RequestMethod.POST)
	public ModelAndView Login() {
    	
		
		System.out.println("user Controller" );
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("user/login");
    	return mv;
    }
	
	@ResponseBody
	@RequestMapping(value = "/idCheck.do",method = RequestMethod.POST)
	public String IdCheck(@RequestParam String id) {
    	
		
		
		int count =  userDao.checkUser(id);
		System.out.println("user Controller\n"+id+"\n"+count );
    	return Integer.toString(count);
    	
    }
	
}
