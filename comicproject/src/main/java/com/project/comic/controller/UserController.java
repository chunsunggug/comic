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
             System.out.println(uvo.toString());
         	
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
	

	@ResponseBody
	@RequestMapping(value = "/findId.do",method = RequestMethod.POST)
	public String FindId(@RequestParam HashMap findIdinfo) {
		System.out.println("user Controller\n"+findIdinfo.get("name")+"\n"+findIdinfo.get("birth"));
		List result =  userService.findId(findIdinfo);
		String resultId="";
		int rcnt=0;

		
		if(result.size()<1) {
			return "0";
		}else {
			String resultreturn="";
			for(int i =0;i<result.size();i++) {
				resultId = result.get(i).toString().substring(4,result.get(i).toString().length()-1);
				rcnt = resultId.indexOf("@");
				if(rcnt<5) {
					resultId = resultId.substring(0,rcnt/2)+"******"+resultId.substring(rcnt-1);
					resultreturn+=resultId+" ";
				}else {
					resultId = resultId.substring(0,3)+"******"+resultId.substring(rcnt-1);
					resultreturn+=resultId+" ";
				}
				
			}
			
			System.out.println("resultreturn:"+resultreturn);
			
	    	return resultreturn.trim();
		}
    }
	
	@ResponseBody
	@RequestMapping(value = "/findPwd.do",method = RequestMethod.POST)
	public ModelAndView FindPwd(@RequestParam HashMap findPwdinfo,HttpServletRequest req) {
		System.out.println("user Controller\n"+findPwdinfo.get("name")+"\n"+findPwdinfo.get("phone"));
		ModelAndView mv = new ModelAndView();
		
		List result =  userService.findPwd(findPwdinfo);
		String id = "";
		String pwd = "";
		if(result.size()>1) {
			for(int i=0;i<result.size();i++) {
				UserDTO userDTO = new UserDTO();
				userDTO = (UserDTO) result.get(i);
				id += userDTO.getId()+" ";
			}
			HttpSession session = req.getSession();
       	 
            session.setAttribute("id",id);
			mv.addObject("id",id.trim());
			url="user/findpwdForm";
		}else {
			mv.addObject("msg","가입된 계정이 없습니다. 회원가입 후 진행해주세요.");
			mv.addObject("gourl","index");
			url="/comic/user/login";
		}
		mv.setViewName(url);
		return mv;
    }
	
	private HashMap<String,String> addrSet(String addrDto,String idDto) {
		String[] addr = addrDto.split(" "); 
		String detail = ""; //addr[0] = post / addr[1] = si / addr[2] = gu / addr[3] = dong / addr[4,5,6,7...] = detail
		HashMap<String,String> mapAddr = new HashMap<String,String>();
		for(int i = 4;i<addr.length;i++) {
			detail+=addr[i];
		}
		addr[4] = detail;
		for(int i=0;i<5;i++) {
			mapAddr.put("addr"+i, addr[i]);
		}
		mapAddr.put("uid",idDto);
		return mapAddr;
	}
}
