package com.project.comic.controller;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

=======
>>>>>>> branch 'master' of https://github.com/chunsunggug/comic.git
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.user.UserDao;
import com.project.comic.user.UserService;

@Controller
public class UserController {


	@Autowired
	private UserDao userDao;

	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/signup.do",method = RequestMethod.POST)
	public ModelAndView SignUp(UserDTO userDto) {
		
    	System.out.println("파람 확인 : "+userDto.getAddr());
		System.out.println("user Controller" );
		userDao.addUser(userDto);
		
		
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("user/login");
    	return mv;
    }
	
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
		int count =  userService.checkUserId(id);
		System.out.println("user Controller\n"+id+"\n"+count );
    	return Integer.toString(count);
    	
    }
	
}
