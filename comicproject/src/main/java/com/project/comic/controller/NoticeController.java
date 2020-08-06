package com.project.comic.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.comic.notice.NoticeDTO;
import com.project.comic.notice.NoticeService;

@Controller
public class NoticeController {
	String jsp = ".jsp";

	@Autowired
	private NoticeService nservice;
	
	
	@RequestMapping(value = "/noticeList.do", method = RequestMethod.GET)
	public String NoticeList(HttpServletRequest req) {
		
		req.setAttribute("page", "notice/listNotice"+jsp);

		return "index";
	}	
	
	@RequestMapping(value = "/addNoticeForm.do", method = RequestMethod.GET)
	public String NoticeAddForm(HttpServletRequest req,HttpSession session) {
		if(session.getAttribute("uidx")==null) {
			req.setAttribute("page", "user/login"+jsp);
			req.setAttribute("msg", "로그인 후 이용 바랍니다.");
			req.setAttribute("gourl", "index.do");
			
			return "index";
		}else {
			req.setAttribute("page", "notice/addNoticeForm"+jsp);
			return "index";
		}
		
	}	
	@RequestMapping(value = "/addNotice.do", method = RequestMethod.POST)
	public String NoticeAdd(MultipartHttpServletRequest files,HttpServletRequest req,HttpSession session,NoticeDTO ndto) {
		System.out.println("notice cntl");
		if(session.getAttribute("uidx")==null) {
			req.setAttribute("page", "user/login"+jsp);
			req.setAttribute("msg", "로그인 후 이용 바랍니다.");
			req.setAttribute("gourl", "index.do");
			
			return "index";
		}else {
			nservice.AddNotice(files, req, session, ndto);
			
			req.setAttribute("page", "notice/listNotice"+jsp);
			return "index";
		}
	}	
	
}
