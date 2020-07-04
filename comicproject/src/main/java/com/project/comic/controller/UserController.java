package com.project.comic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.user.UserDao;
import com.project.comic.user.UserService;
import com.project.comic.user.UserDTO;

@Controller
public class UserController {


	@Autowired
	private UserDao userDao;

	
	@Autowired
	private UserService userService;
	
	@Transactional
	@RequestMapping(value = "/signup.do",method = RequestMethod.POST)
	public ModelAndView SignUp(UserDTO userDto) {
		HashMap mapAddr = addrSet(userDto.getAddr());
		userDto.setAddr("1");
		
		ModelAndView mv = new ModelAndView();
		
		String url = "";
		int result = userDao.addUser(userDto);
		int resultAddr = userDao.addAddr(mapAddr);
		
		if(result+resultAddr<2) {
			url = "errorPage";
		}else {
			url = "user/login";
			mv.addObject("msg", userDto.getName()+"님의 가입을 환영합니다.");
			mv.addObject("gourl", "index.do");
		}
    	mv.setViewName("errorPage");
    	return mv;
    }
	
	@RequestMapping(value = "/signin.do",method = RequestMethod.POST)
	public ModelAndView Login() { 
		System.out.println("user Controller " );
    	
		
		ModelAndView mv = new ModelAndView();
    	return mv;
    }
	
	@ResponseBody
	@RequestMapping(value = "/idCheck.do",method = RequestMethod.POST)
	public String IdCheck(@RequestParam String id) {
		int count =  userService.checkUserId(id);
		System.out.println("user Controller\n"+id+"\n"+count );
    	return Integer.toString(count);
    	
    }
	
	
	private HashMap addrSet(String addrDto) {
		String[] addr = addrDto.split(" ");
		addr[4]="";
		HashMap mapAddr = new HashMap();
		for(int i = 4;i<addr.length;i++) {
			addr[4]+=addr[i];
		}
		for(int i=0;i<5;i++) {
			mapAddr.put("addr"+i, addr[i]);
		}
		
		return mapAddr;
		
		
		
		
	}
}
