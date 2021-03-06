package com.project.comic.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.store.StoreDao;
import com.project.comic.user.UserDao;

@Controller
public class IndexController {
	
	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Set pathSet = request.getSession().getServletContext().getResourcePaths("/");
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		return mv;
	}	
	
	
}
