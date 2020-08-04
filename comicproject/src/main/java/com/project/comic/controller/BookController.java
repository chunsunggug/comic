package com.project.comic.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.comic.Utility;
import com.project.comic.book.BookService;
import com.project.comic.book.IBookSearchService;
import com.project.comic.storebook.IStoreBookService;
import com.project.comic.storebook.StoreBookDTO;

@Controller
public class BookController {

	@Autowired
	private IBookSearchService kakaoBookSearchService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private IStoreBookService storeBookService;

	// 검색페이지로 이동해서 검색결과 보여줌
	@RequestMapping(value="/search.do")
	public String bookSearch(@RequestParam( required=false ) Map map_param, HttpSession session, HttpServletRequest request) {
		
		String result = kakaoBookSearchService.bookSearch( map_param, session );
		
		if( result != null ) {
			System.out.println(result);
			request.setAttribute( "books", result );
		}
		request.setAttribute( "page", "book/booksearch.jsp" );
		
		return "index";
	}

	// 검색페이지에서 더보기를 누룬 경우
	@ResponseBody
	@RequestMapping(value="/searchmore.do", produces = "application/text; charset=UTF-8")
	public String bookSearchMore(HttpSession session) {

		String result = kakaoBookSearchService.bookSearchMore( session );

		return result;
	}
	
	
	// 목록을 누루면 들어오는 페이지의 순서
	// 대여가능 점포 목록을 보여준다
	// 점포 선택 후 책의 상세페이지로 이동
	
	// 점포를 보여주는 페이지
	@RequestMapping(value="/borrowablestoretab.do")
	public String viewStoreTab(@RequestParam Map param, HttpServletRequest request) {
		// 점포 중에서 책이 등록된 곳만 10개 가져오기
		// 테이블로 만들 수 있도록 정렬 후 페이지로 데이터 보내면서 이동

		// 10개 목록 가져오기(미구현)
		List list = bookService.getLinkDetailTabList( (String)param.get("isbn"), 3 );
		
		request.setAttribute("page", "book/borrowablestoretab.jsp");
		request.setAttribute("items", list);
		
		return "index";
	}
	
	@RequestMapping(value="/storebooklist.do")
	public String viewBorrowList(HttpServletRequest request,
			@RequestParam Map param) {
		// isbn만 받아온다
		if( !param.containsKey("isbn") )
			return null;
		
		String isbn = (String)param.get("isbn");
		List list = storeBookService.getBooksByIsbn(1, isbn);
		String result = (String)storeBookService.loadBookDataFromSearchServer(isbn);
		JSONObject json_result = (JSONObject)Utility.JSONParse(result);
		JSONArray json_documents = (JSONArray)json_result.get("documents");
		JSONObject json_book = (JSONObject)json_documents.get(0);
		
		request.setAttribute("page", "book/storebooklist.jsp");
		request.setAttribute("items", list);
		request.setAttribute("bookdata", json_book);
		request.setAttribute("detailurl", "/comic/bookdetail.do");
		return "index";
	}
	
	@RequestMapping(value="/bookdetail.do")
	public String bookDetail(HttpServletRequest request,
			@RequestParam Map param) {
		JSONObject bookdata = (JSONObject)Utility.JSONParse((String)param.get("bookdata"));
		String sbidx = (String)param.get("sbidx");
		StoreBookDTO dto = (StoreBookDTO)storeBookService.getBookByPk(sbidx);
		
		request.setAttribute("page", "book/bookdetail.jsp");
		request.setAttribute("item", dto );
		request.setAttribute("bookdata", bookdata );
		
		return "index";
	}

}
