package com.project.comic.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.comic.book.IBookSearchService;

@Controller
public class BookController {

	@Autowired
	private IBookSearchService kakaoBookSearchService;

	// 검색페이지로 이동해서 검색결과 보여줌
	@RequestMapping(value="/search.do")
	public String bookSearch(@RequestParam( required=false ) Map map_param, HttpSession session, HttpServletRequest request) {
		
		String result = kakaoBookSearchService.bookSearch( map_param, session );
		
		if( result != null ) {
			System.out.println(result);
			request.setAttribute( "books", result );
		}
		request.setAttribute( "page", "search/booksearch.jsp" );
		
		return "index";
	}

	// 검색페이지에서 더보기를 누룬 경우
	@ResponseBody
	@RequestMapping(value="/searchmore.do", produces = "application/text; charset=UTF-8")
	public String bookSearchMore(HttpSession session) {

		String result = kakaoBookSearchService.bookSearchMore( session );

		return result;
	}
	
	// 목록을 누루면 들어오는 상세페이지
	@RequestMapping(value="/bookdetail.do")
	public String bookDetail(@RequestParam Map param) {
		
		return "";
	}

}
