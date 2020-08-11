package com.project.comic.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.comic.book.AllInOneBookVO;
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
	
	
	// 책을 누루면 들어오는 페이지의 순서
	// 대여가능 점포 목록을 보여준다
	// 점포를 클릭하면 자세히 페이지로 넘어간다
	
	// 점포를 보여주는 페이지
	@RequestMapping(value="/borrowablestoretab.do")
	public String viewStoreTab(@RequestParam(name="isbn") String isbn13, HttpServletRequest request) {
		// 점포 중에서 책이 등록된 곳만 10개 가져오기
		// 테이블로 만들 수 있도록 정렬 후 페이지로 데이터 보내면서 이동
		
		// 3개 목록 가져오기(미구현)
		List list = bookService.getTableList( isbn13, 3 );
		
		request.setAttribute("page", "book/borrowablestoretab.jsp");
		request.setAttribute("items", list);
		
		return "index";
	}
	
	// 점포를 선택하고 넘어오는 페이지
	// 점포의 해당책을 전부 보여준다
	/*@RequestMapping(value="/storebooklist.do")
	public String viewBorrowList(HttpServletRequest request,
			@RequestParam(name="isbn") String isbn,
			@RequestParam(name="sidx") int sidx ) {
		
		List<StoreBookDTO> list = storeBookService.getBooksByIsbn(sidx, isbn);
		List<AllInOneBookVO> vo_list = bookService.getContentsVOList(list);
		
		request.setAttribute("page", "book/storebooklist.jsp");
		request.setAttribute("items", vo_list);
		
		return "index";
	}*/
	
	// 식별된 하나의 책을 자세히 페이지에서 보여준다
	// 완벽한 하나의 책을 분류해서 보여주는 페이지
	/*@RequestMapping(value="/bookdetail.do")
	public String bookDetail(HttpServletRequest request,
			@RequestParam(name="pk") String sbidx) {
		
		StoreBookDTO dto = (StoreBookDTO)storeBookService.getBookByPk(sbidx);
		AllInOneBookVO vo = bookService.getContentVO(dto);
		
		request.setAttribute("page", "book/bookdetail.jsp");
		request.setAttribute("item", vo );

		return "index";
	}*/

	// 점포의 해당 책을 자세히 페이지에서 보여준다
	// sidx, isbn으로만 분류된 책의 페이지
	@RequestMapping(value="/bookdetail.do")
	public String bookDetail(HttpServletRequest request,
			@RequestParam(name="isbn") String isbn13,
			@RequestParam(name="sidx") int sidx) {
		
		List<StoreBookDTO> list = storeBookService.getBooksByIsbn(sidx, isbn13);
		AllInOneBookVO vo = bookService.getContentVO(list.get(0));
		vo.setStoreBookDTOList(list);
		
		request.setAttribute("page", "book/bookdetail.jsp");
		request.setAttribute("item", vo );

		return "index";
	}

	// 카트쿠키 저장
	@ResponseBody
	@RequestMapping(value="/additemtocart.do")
	public String addItemToCart(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(name="isbn") String isbn13,
			@RequestParam(name="sidx") int sidx) {
		
		List<StoreBookDTO> list = storeBookService.getBooksByIsbn(sidx, isbn13);
		AllInOneBookVO vo = bookService.getContentVO(list.get(0));
		vo.setStoreBookDTOList(list);
		
		if( bookService.addItemToCart(response, request, vo) == -1) return "0";

		return "1";
	}
	
	// 카트쿠키 삭제
	@ResponseBody
	@RequestMapping(value="/deletecartitem.do")
	public String deleteCartItem(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(name="isbn") String isbn13,
			@RequestParam(name="sidx") int sidx) {
		
		if( bookService.DeleteItemCart(response, request, sidx, isbn13 ) == 0 ) {
			return "0";
		}

		return "1";
	}
}