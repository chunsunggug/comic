package com.project.comic.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.comic.book.BookService;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	
	@Autowired
	private BookService bookService;

	// 검색페이지로 이동해서 검색결과 보여줌
	@RequestMapping(value="/search.do")
	public String bookSearch(@RequestParam Map param, HttpSession session, HttpServletRequest request) {
		
		// service를 통해서 documents와 meta 정보 얻어온다
		JSONObject search_data = bookService.getJSONBookSearchData( bookService.mapToJSON(param), session);
		
		if( search_data != null ) {
			request.setAttribute( "documents", search_data.get("documents") );
			request.setAttribute( "meta", search_data.get("meta") );
		}
		
		return "book/booksearchpage";
	}

	// 검색페이지에서 더보기를 누룬 경우
	@ResponseBody
	@RequestMapping(value="/searchmore.do", produces = "application/text; charset=UTF-8")
	public String bookSearchMore(HttpSession session) {
		JSONObject json_meta_documents = bookService.getJSONBookSearchDataMore( session );
		
		return json_meta_documents.toJSONString();
	}
	
	// 점주의 도서관리 페이지 이동
	@RequestMapping(value="/listbook.do")
	public String listBook() {
		return "book/bookmanage";
	}
}
