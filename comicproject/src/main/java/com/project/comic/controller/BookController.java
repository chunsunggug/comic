package com.project.comic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.comic.Utility;
import com.project.comic.book.BookGroupVO;
import com.project.comic.book.ISequenceSearch;
import com.project.comic.book.kakao.KakaoQueryModel;
import com.project.comic.order.OrderService;
import com.project.comic.storebook.IStoreBookService;
import com.project.comic.storebook.StoreBookDTO;

@Controller
public class BookController {

	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;
	
	@Autowired
	private OrderService payService;
	
	@Autowired
	private IStoreBookService storeBookService;

	// 검색페이지로 이동해서 검색결과 보여줌
	@RequestMapping(value="/search.do")
	public String bookSearch(@RequestParam( required=false ) Map map_param, HttpSession session, HttpServletRequest request) {
		KakaoQueryModel model = new KakaoQueryModel();
		model.setPage(0);
		model.setQuery((String)map_param.get("query"));
		model.setTarget((String)map_param.get("target"));
		
		String result = (String)kakaoBookSequenceSearch.nextSearch(model);
		
		if( result != null ) {
			System.out.println(result);
			JSONObject json_result = (JSONObject)Utility.JSONParse(result);
			session.setAttribute("documents", json_result.get("documents") );
			session.setAttribute("meta", json_result.get("meta") );
			session.setAttribute("model", model);
		}
		
		request.setAttribute( "page", "book/booksearch.jsp" );
		
		return "index";
	}

	// 검색페이지에서 더보기를 누룬 경우
	@ResponseBody
	@RequestMapping(value="/searchmore.do", produces = "application/text; charset=UTF-8")
	public String bookSearchMore(HttpSession session) {

		KakaoQueryModel model = (KakaoQueryModel)session.getAttribute("model");
		
		String result = (String)kakaoBookSequenceSearch.nextSearch(model);

		return result;
	}

	// 책을 누루면 들어오는 페이지의 순서
	// 대여가능 점포 목록을 보여준다
	// 점포를 클릭하면 자세히 페이지로 넘어간다
	
	// 점포목록 보여주는 페이지
	@RequestMapping(value="/borrowablestoretab.do")
	public String viewStoreTab(@RequestParam(name="isbn") String isbn, HttpServletRequest request) {
		List<BookGroupVO> vo_list = new ArrayList<BookGroupVO>();
		List<StoreBookDTO> dto_list = storeBookService.getBooksByIsbn( 1, isbn );
		
		if( dto_list != null ) { 
			BookGroupVO vo = new BookGroupVO();
			vo.setStoreBookDTOList(dto_list);
			vo_list.add( vo );
			System.out.println(vo.toString());
		}
		
		request.setAttribute( "page", "book/borrowablestoretab.jsp" );
		request.setAttribute( "list_items", vo_list );
		
		return "index";
	}
	
	// 점포의 해당 책을 자세히 페이지에서 보여준다
	// sidx, isbn으로만 분류된 책의 페이지
	@RequestMapping(value="/bookdetail.do")
	public String bookDetail(HttpServletRequest request,
			@RequestParam(name="isbn") String isbn,
			@RequestParam(name="sidx") int sidx) {
		
		BookGroupVO vo = (BookGroupVO)storeBookService.getBookGroupVO( sidx, isbn );
		
		request.setAttribute("page", "book/bookdetail.jsp");
		request.setAttribute("item", vo );

		return "index";
	}

	// 카트쿠키 저장
	@ResponseBody
	@RequestMapping(value="/additemtocart.do")
	public String addItemToCart(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(name="isbn") String isbn,
			@RequestParam(name="sidx") int sidx) {
		
		BookGroupVO vo = (BookGroupVO)storeBookService.getBookGroupVO(sidx, isbn);
		
		if( payService.addItemToCart(response, request, isbn, sidx) == -1) return "0";

		return "1";
	}
	
	// 카트쿠키 삭제
	@ResponseBody
	@RequestMapping(value="/deletecartitem.do")
	public String deleteCartItem(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(name="isbn") String isbn,
			@RequestParam(name="sidx") int sidx) {
		
		if( payService.DeleteItemCart(response, request, sidx, isbn ) == 0 ) {
			return "0";
		}

		return "1";
	}
}