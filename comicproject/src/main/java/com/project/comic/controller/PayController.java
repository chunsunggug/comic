package com.project.comic.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.project.comic.Utility;
import com.project.comic.book.BookGroupVO;
import com.project.comic.book.ISequenceSearch;
import com.project.comic.pay.PayService;
import com.project.comic.storebook.IStoreBookService;

@Controller
@RequestMapping(value="/pay")
public class PayController {

	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;
	
	@Autowired
	private PayService bookService;
	
	@Autowired
	private IStoreBookService storeBookService;
	
	@Autowired
	private PayService payService;

	// 카트 페이지
	@RequestMapping(value="/cart.do")
	public ModelAndView cart(HttpServletRequest request,
			@CookieValue(required=false, value="comiccart") String cookie_comiccart ) {
		String decoded = "";
		try {
			decoded = URLDecoder.decode(cookie_comiccart, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray json_arr = (JSONArray)Utility.JSONParse(decoded);
		JSONArray json_param = new JSONArray();
		Gson gson = new Gson();
		
		for(int i=0; i < json_arr.size(); i++) {
			JSONObject json_item = (JSONObject)json_arr.get(i);
			BookGroupVO vo = (BookGroupVO)storeBookService.getBookGroupVO(Integer.parseInt(json_item.get("sidx").toString()),
											(String)json_item.get("isbn"));
			json_param.add( (JSONObject)Utility.JSONParse(gson.toJson(vo)));
		}
		ModelAndView mv = new ModelAndView();

		mv.setViewName( "index" );
		mv.addObject( "page", "pay/cart.jsp" );
		
		if( json_param.size() != 0 ) {
			mv.addObject( "comiccart", json_param );
			mv.addObject( "tot_count", json_param.size() );
		}
		
		return mv;
	}

	@RequestMapping(value="/pay.do", method=RequestMethod.POST)
	public ModelAndView payItems(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName( "index" );
		
		if( request.getSession().getAttribute("uidx") != null ) {
		
			int result = payService.payPoint(request, response);
			
			if( result < 0 ) System.out.println("결제 실패 !!");
			
			mv.addObject( "page", "20_main.jsp");// "pay/payresult.jsp" );
		}
		
		mv.addObject( "page", "20_main.jsp");// "pay/payresult.jsp" );
		
		Cookie delete = new Cookie("comiccart", "");
		delete.setMaxAge(0);
		response.addCookie(delete);
		
		return mv;
	}
	
}
