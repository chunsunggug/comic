package com.project.comic.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.Utility;
import com.project.comic.book.BookService;
import com.project.comic.book.IBookSearchService;
import com.project.comic.storebook.IStoreBookService;

@Controller
@RequestMapping(value="/pay")
public class PayController {

	@Autowired
	private IBookSearchService kakaoBookSearchService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private IStoreBookService storeBookService;

	// 카트 페이지
	@RequestMapping(value="/cart.do")
	public ModelAndView cart(HttpServletRequest request,
			@CookieValue(required=false, value="comiccart") String arr_vo ) {
		String decoded = "";
		try {
			decoded = URLDecoder.decode(arr_vo, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray json_arr = (JSONArray)Utility.JSONParse(decoded);
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName( "index" );
		mv.addObject( "page", "pay/cart.jsp" );
		mv.addObject( "comiccart", json_arr );
		mv.addObject( "tot_count", json_arr.size() );
		
		return mv;
	}

	
	
}
