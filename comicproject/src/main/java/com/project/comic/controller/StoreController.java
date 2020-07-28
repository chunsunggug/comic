package com.project.comic.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.comic.Utility;
import com.project.comic.page.PageMaker;
import com.project.comic.storebook.IStoreBookService;
import com.project.comic.storebook.StoreBookDTO;

@Controller
@RequestMapping(value="/store")
public class StoreController {

	// 9788959521227
	// 테스트용 isbn
	private final String SIDX = "uidx";
	private final int PAGE_LIST_SIZE = 10;
	private final int PAGE_SIZE = 10;
	
	@Autowired
	IStoreBookService storeBookService; 
	
	// 점주의 도서관리 페이지 이동
	@RequestMapping(value="/listbook.do")
	public ModelAndView listBook(HttpSession session,
			@RequestParam(value="cp", defaultValue="1")int cp) {
		
		// test 상황 sidx : 1
		int sidx = 1;
		List listitem = storeBookService.getPageList(cp, PAGE_LIST_SIZE, 1);
		int total = storeBookService.getBooksCountAll(sidx);
		String pagestr = PageMaker.makePage("/store/listbook.do", total, PAGE_LIST_SIZE, PAGE_SIZE, cp);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("store/bookmanage");
		mv.addObject( "listitem", listitem );
		mv.addObject("pagestr", pagestr);
		
		return mv;
	}
	
	// 점포 도서 추가시 텍스트박스에 ISBN 검색하여 책정보 불러오기
	@RequestMapping(value="/loadbookdata.do", produces = "application/text; charset=UTF-8",
					method = RequestMethod.POST)
	@ResponseBody
	public String getBookByISBN(@RequestBody String param) {
		param = Utility.decodeString(param);

		// 끝에 이상하게 =문자가 같이 넘어온다 잘라주자
		param = param.replaceAll("=", "");
		
		String result = (String)storeBookService.loadBookDataByISBN(param);
		
		if(result != null)
			return result;
		
		return null;
	}
	
	// 추가하기 버튼 누루면 디비를 거쳐 추가하는 곳
	@RequestMapping(value="/register.do", produces = "application/text; charset=UTF-8",
					method = RequestMethod.POST)
	@ResponseBody
	public String registerStoreBookData(@RequestParam Map param, HttpSession session) {
		
		StoreBookDTO dto = new StoreBookDTO();
		String isbn = (String)param.get("isbn");
		
		if( isbn.length() == 10 ) dto.setIsbn10( isbn );
		else if( isbn.length() == 13 ) dto.setIsbn13( isbn );
		
		//dto.setSidx((int)json_book.get("sidx"));
		dto.setSidx( 1 ); // test : 1
		dto.setPoint( Integer.parseInt((String)param.get("point")) );
		dto.setCategory( (String)param.get("category") );
		dto.setTotal( Integer.parseInt((String)param.get("total")) );
		dto.setCnt( dto.getTotal() );

		boolean result = storeBookService.add(dto);
		
		if( result )
			return "1";
		
		return "0";
	}
}
