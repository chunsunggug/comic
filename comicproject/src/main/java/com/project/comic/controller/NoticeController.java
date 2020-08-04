package com.project.comic.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.store.StoreDao;
import com.project.comic.user.UserDao;

@Controller
public class NoticeController {


	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/noticeList.do", method = RequestMethod.GET)
	public String NoticeList(HttpServletRequest req) {
		
		req.setAttribute("page", "notice/listNotice.jsp");

		return "index";
	}	
	
	@RequestMapping(value = "/addNotice.do", method = RequestMethod.GET)
	public ModelAndView NoticeAdd(HttpServletRequest req,HttpSession session) {
		
		req.setAttribute("page", "notice/listNotice.jsp");
		
		return null;
	}	
	
}
