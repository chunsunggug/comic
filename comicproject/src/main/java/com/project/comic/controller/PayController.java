package com.project.comic.controller;

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

	// 북마크 페이지
	@RequestMapping(value="/cart.do")
	public ModelAndView cart(HttpServletRequest request,
			@CookieValue(required=false, value="cart") String arr_vo ) {
		JSONArray json_arr = (JSONArray)Utility.JSONParse(arr_vo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("page", "pay/cart.jsp");
		mv.addObject("cart", json_arr);
		
		return mv;
	}

	
	
}
