package com.project.comic.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.project.comic.user.UserVO;
import com.project.comic.user.UserDTO;

@Controller
public class UserController {
	private String url = "index";

	@Autowired
	private UserDao userDao;

	
	@Autowired
	private UserService userService;
	
	@Transactional
	@RequestMapping(value = "/signup.do",method = RequestMethod.POST)
	public ModelAndView SignUp(UserDTO userDto) {
		HashMap<String,String> mapAddr = addrSet(userDto.getAddr(),userDto.getId());
		userDto.setAddr("1");
		
		ModelAndView mv = new ModelAndView();
		
		
		int result = userDao.addUser(userDto);
		int resultAddr = userDao.addAddr(mapAddr);
		
		if(result+resultAddr<2) {
			url = "errorPage";
		}else {
			url = "user/login";
			mv.addObject("msg", userDto.getName()+"님의 가입을 환영합니다.");
			mv.addObject("gourl", "index.do");
		}
    	mv.setViewName(url);
    	return mv;
    }
	
	@RequestMapping(value = "/signin.do",method = RequestMethod.POST)
	public ModelAndView Login(UserVO uvo,HttpServletRequest req,HttpServletResponse rsp) throws UnsupportedEncodingException { 
		req.setCharacterEncoding("utf-8");
		rsp.setCharacterEncoding("utf-8");
		rsp.setContentType("text/html; charset=utf-8");
        
    	HashMap<String, String> mapLogin = new HashMap<String, String>();
    	mapLogin.put("id",req.getParameter("id"));
    	mapLogin.put("pwd",req.getParameter("pwd"));
    	
    		
    	ModelAndView mv = new ModelAndView();
        
    	try {
        	uvo = userService.logingUser(mapLogin);
        	 
        	HttpSession session = req.getSession();
        	 
             session.setAttribute("id",uvo.getId());
             session.setAttribute("name",uvo.getName());
             session.setAttribute("point",uvo.getPoint());
             session.setAttribute("type",uvo.getType());
             session.setAttribute("isyn",uvo.getIsYn());
             
             
             session.setMaxInactiveInterval(60*10); //기본 로그인 유지 시간 60초*10
        	url="index";
        }catch (Exception e) {
        	e.printStackTrace();
        	url = "user/login";
       		mv.addObject("msg","ID(Email형식) 또는 비밀번호를 확인해주세요.");
           	mv.addObject("gourl", "index.do");	
		}
    	
		mv.setViewName(url);
    	return mv;
    }
	
	@RequestMapping(value = "/signout.do",method = RequestMethod.GET)
	public ModelAndView SignOut(HttpServletRequest req) {
		
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		session.invalidate();
		
    	mv.setViewName(url);
    	return mv;
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/idCheck.do",method = RequestMethod.POST)
	public String IdCheck(@RequestParam String id) {
		int count =  userService.checkUserId(id);
		System.out.println("user Controller\n"+id+"\n"+count );
    	return Integer.toString(count);
    	
    }
	
	
	private HashMap<String,String> addrSet(String addrDto,String idDto) {
		String[] addr = addrDto.split(" "); 
		addr[4]="";
		HashMap<String,String> mapAddr = new HashMap<String,String>();
		for(int i = 4;i<addr.length;i++) {
			addr[4]+=addr[i];
		}
		for(int i=0;i<5;i++) {
			mapAddr.put("addr"+i, addr[i]);
		}
		mapAddr.put("uid",idDto);
		return mapAddr;
		
		
		
		
	}
}
