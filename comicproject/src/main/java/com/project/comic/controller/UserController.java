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
import com.project.comic.kakao.KakaoAPI;
import com.project.comic.mail.MailDTO;
import com.project.comic.mail.UserMailSendService;
import com.project.comic.user.UserDTO;

@Controller
public class UserController {
	private String url = "index";

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@Autowired
	private UserMailSendService mailsender;

	@Transactional
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public ModelAndView SignUp(UserDTO userDto) {
		HashMap<String, String> mapAddr = addrSet(userDto.getAddr(), userDto.getId());
		userDto.setAddr("1");

		ModelAndView mv = new ModelAndView();

		int result = userDao.addUser(userDto);
		int resultAddr = userDao.addAddr(mapAddr);

		if (result + resultAddr < 2) {
			url = "errorPage";
		} else {
			url = "user/login";
			mv.addObject("msg", userDto.getName() + "님의 가입을 환영합니다.");
			mv.addObject("gourl", "index.do");
		}
		mv.setViewName(url);
		return mv;
	}

	@RequestMapping(value = "/signin.do", method = RequestMethod.POST)
	public ModelAndView Login(UserVO uvo, HttpServletRequest req, HttpServletResponse rsp)
			throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		req.setCharacterEncoding("utf-8");
		rsp.setCharacterEncoding("utf-8");
		rsp.setContentType("text/html; charset=utf-8");
		HashMap<String, String> mapLogin = new HashMap<String, String>();
		mapLogin.put("id", req.getParameter("id"));
		mapLogin.put("pwd", req.getParameter("pwd"));
		System.out.println(req.getParameter("id"));
		System.out.println(req.getParameter("pwd"));
		url = "user/login";
		try {
			uvo = userService.logingUser(mapLogin);

			System.out.println(uvo.toString());

			HttpSession session = req.getSession();

			session.setAttribute("idx", uvo.getUidx());
			session.setAttribute("id", uvo.getId());
			session.setAttribute("name", uvo.getName());
			session.setAttribute("point", uvo.getPoint());
			session.setAttribute("type", uvo.getType());
			session.setAttribute("isyn", uvo.getIsYn());

			session.setMaxInactiveInterval(60 * 10); // 기본 로그인 유지 시간 60초*10
			System.out.println(uvo.toString());
			mv.addObject("msg", "로그인이 정상적으로 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", "ID(Email형식) 또는 비밀번호를 확인해주세요.");
			
		}
		mv.addObject("gourl", "index.do");
		mv.setViewName(url);
		return mv;
	}

	@RequestMapping(value = "/signout.do", method = RequestMethod.GET)
	public ModelAndView SignOut(HttpServletRequest req) {

		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		
		if(session.getAttribute("token")!=null) {
			KakaoAPI kapi = new KakaoAPI();
			String check = kapi.Logout(session.getAttribute("token").toString());
			System.out.println("user controller : "+check);
		}
		session.invalidate();
		mv.addObject("msg", "로그아웃이 완료되었습니다.");
		mv.addObject("gourl", "index.do");
		
		url = "user/login";
		mv.setViewName(url);
		return mv;
		
		
		
	}

	@ResponseBody
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public String IdCheck(@RequestParam String id) {
		int count = userService.checkUserId(id);
		System.out.println("user Controller\n" + id + "\n" + count);
		
		
		return Integer.toString(count);

	}

	@ResponseBody
	@RequestMapping(value = "/findId.do", method = RequestMethod.POST)
	public String FindId(@RequestParam HashMap findIdinfo) {
		System.out.println("user Controller\n" + findIdinfo.get("name") + "\n" + findIdinfo.get("birth"));
		List result = userService.findId(findIdinfo);
		String resultId = "";
		int rcnt = 0;

		if (result.size() < 1) {
			return "0";
		} else {
			String resultreturn = "";
			for (int i = 0; i < result.size(); i++) {
				resultId = result.get(i).toString().substring(4, result.get(i).toString().length() - 1);
				rcnt = resultId.indexOf("@");
				if (rcnt < 5) {
					resultId = resultId.substring(0, rcnt / 2) + "******" + resultId.substring(rcnt - 1);
					resultreturn += resultId + " ";
				} else {
					resultId = resultId.substring(0, 3) + "******" + resultId.substring(rcnt - 1);
					resultreturn += resultId + " ";
				}

			}

			System.out.println("resultreturn:" + resultreturn);

			return resultreturn.trim();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/findPwd.do", method = RequestMethod.POST)
	public ModelAndView FindPwd(@RequestParam HashMap findPwdinfo, HttpServletRequest req) {
		System.out.println("user Controller\n" + findPwdinfo.get("name") + "\n" + findPwdinfo.get("phone"));
		ModelAndView mv = new ModelAndView();

		List result = userService.findPwd(findPwdinfo);
		String id = "";
		String pwd = "";
		String name = "";
		System.out.println("user Controller resultSize\n" + result.size());
		if (result.size() < 1) {
			mv.addObject("msg", "가입된 계정이 없습니다. 회원가입 후 진행해주세요.");
			mv.addObject("gourl", "index.do");
			url = "user/login";
		} else {
			for (int i = 0; i < result.size(); i++) {
				UserDTO userDTO = new UserDTO();
				userDTO = (UserDTO) result.get(i);
				id = userDTO.getId();
				pwd = userDTO.getPwd();
				name = userDTO.getName();
			}

			System.out.println(id + ":" + pwd + ":" + name);

			boolean mailresult = mailsender.mailSendWithUserKey(name, id, pwd, req);
			if(mailresult) {
				System.out.println("메일 발송 성공");
			}else {
				System.out.println("메일 발송 실패");
			}
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			mv.addObject("id", id.trim());
			url = "user/findpwdForm";

		}
		mv.setViewName(url);
		return mv;
	}

	@RequestMapping(value = "/myInfo.do", method = RequestMethod.GET)
	public ModelAndView MyInfo(HttpSession session) {
		System.out.println("user controller check");
		ModelAndView mv = new ModelAndView();
		int idx = (int) session.getAttribute("idx");
		String id = (String) session.getAttribute("id");
		UserVO uvo = (UserVO) userService.myInfo(id);
		if (idx < 1) {
			url = "errorPage";
		} else {
			url = "user/myInfoForm";
			mv.addObject("uvo", uvo);
		}

		url = "user/myInfoForm";
		mv.setViewName(url);
		return mv;
	}

	
	@RequestMapping(value = "/kakaologin.do", method = RequestMethod.GET)
	public String KaKaoLogin(@RequestParam("code") String code,HttpServletRequest req,HttpServletResponse rsp) throws UnsupportedEncodingException{
		System.out.println("code 값 : "+code);
		req.setCharacterEncoding("utf-8");
		rsp.setCharacterEncoding("utf-8");
		rsp.setContentType("text/html; charset=utf-8");
		KakaoAPI kapi = new KakaoAPI();
		String AccessToken = kapi.getAccessToken(code);
		HashMap userInfo =  kapi.getUserInfo(AccessToken);

		HttpSession session = req.getSession();

		session.setAttribute("id", userInfo.get("id"));//userInfo.get("id")
		session.setAttribute("name",userInfo.get("name"));//userInfo.get("name")
		session.setAttribute("point", 500);
		session.setAttribute("type", "C");
		session.setAttribute("isyn", "Y");
		session.setAttribute("token",AccessToken);
		System.out.println("여긴 유저 컨트롤러임 : "+userInfo.toString());
		
		
		return "index";
	}

	
	
	private HashMap<String, String> addrSet(String addrDto, String idDto) {
		String[] addr = addrDto.split(" ");
		String detail = ""; // addr[0] = post / addr[1] = si / addr[2] = gu / addr[3] = dong /
							// addr[4,5,6,7...] = detail
		HashMap<String, String> mapAddr = new HashMap<String, String>();
		for (int i = 4; i < addr.length; i++) {
			detail += addr[i];
		}
		addr[4] = detail;
		for (int i = 0; i < 5; i++) {
			mapAddr.put("addr" + i, addr[i]);
		}
		mapAddr.put("uid", idDto);
		return mapAddr;
	}
}
