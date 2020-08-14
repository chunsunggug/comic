package com.project.comic.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.project.comic.Utility;
import com.project.comic.book.BookGroupVO;
import com.project.comic.order.OrderService;
import com.project.comic.order.OrderVO;
import com.project.comic.page.PageMaker;
import com.project.comic.storebook.IStoreBookService;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	private IStoreBookService storeBookService;
	
	@Autowired
	private OrderService orderService;
	

	// 카트 페이지
	@RequestMapping(value="/cart.do")
	public ModelAndView cart(HttpServletRequest request,
			@CookieValue(required=false, value="comiccart", defaultValue="") String cookie_comiccart ) {
		String decoded = "";
		ModelAndView mv = new ModelAndView();

		mv.setViewName( "index" );
		mv.addObject( "page", "pay/cart.jsp" );

		if( !cookie_comiccart.equals("") ) {
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

			if( json_param.size() != 0 ) {
				mv.addObject( "cartlist", json_param );
				mv.addObject( "tot_count", json_param.size() );
			}
		}
		
		return mv;
	}

	@RequestMapping(value="/pay.do", method=RequestMethod.POST)
	public ModelAndView payItems(HttpServletRequest request, HttpServletResponse response,
			@CookieValue(required=false, value="comiccart", defaultValue="") String cookie_comiccart) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName( "index" );
		
		mv.addObject( "page", "pay/cart.jsp");// "pay/payresult.jsp" );
		
		if( request.getSession().getAttribute("uidx") != null &&
			cookie_comiccart != "" ) {
		
			int result = orderService.payPoint(cookie_comiccart, response, request);
			
			if( result < 0 ) {
				System.out.println("결제 실패 !! : " + result);
				mv.addObject( "page", "20_main.jsp");// "pay/payresult.jsp" );
			}
		}
		
		return mv;
	}

}
