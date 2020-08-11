package com.project.comic.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	// 9788954671873
	// 9788954657556
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
		List listitem = storeBookService.getManagePageList(cp, PAGE_LIST_SIZE, 1);
		int total = storeBookService.getBooksCountAll(sidx);
		String pagestr = PageMaker.makePage("/comic/store/listbook.do", total, PAGE_LIST_SIZE, PAGE_SIZE, cp);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("index");
		mv.addObject("page","store/listbook.jsp");
		mv.addObject( "listitem", listitem );
		mv.addObject("pagestr", pagestr);
		mv.addObject("include", "booksearch.jsp");
		
		return mv;
	}
	
	// 점포 도서 추가시 텍스트박스에 ISBN 검색하여 책정보 불러오기
	@RequestMapping(value="/loadbookdata.do", produces = "application/text; charset=UTF-8",
					method = RequestMethod.POST)
	@ResponseBody
	public String getBookDataByISBN(@RequestParam Map param, HttpSession session) {
		
		int sidx = 1; // session.getAttribute("sidx");
		String result = "";
		
		if( !param.get("isbn").equals("") ) 
			result = (String)storeBookService.
					loadBookDataFromSearchServer((String)param.get("isbn"));
		else if( !param.get("pk").equals("") )
			result = (String)storeBookService.
					loadBookDataForModal((String)param.get("pk"));
				
		if(result != null) 
			return result;
		
		return null;
	}
	
	// 추가하기 버튼 누루면 디비를 거쳐 추가하는 곳
	@RequestMapping(value="/register.do", produces = "application/text; charset=UTF-8",
					method = RequestMethod.POST)
	@ResponseBody
	public String registerStoreBookData(@RequestParam Map param, HttpSession session) {
		
		StoreBookDTO dto = getDTOFromParam( param, session );

		boolean result = storeBookService.add( dto,
						Integer.parseInt((String)param.get("total")) );
		
		if( result )
			return "1";
		
		return "0";
	}
	
	@RequestMapping(value="/update.do", produces = "application/text; charset=UTF-8",
			method = RequestMethod.POST )
	@ResponseBody
	public String updateStoreBookData(@RequestParam Map param, HttpSession session) {
		// 받아온 수정할 데이터를 dto에 정리
		// 서비스로 dto를 보내 update 진행
		// return 값에 따라 성공 실패 결과 return
		System.out.println(param);
		StoreBookDTO dto = getDTOFromParam( param, session );
		
		if( storeBookService.update(dto) )
			return "1";
		
		return "0";
	}
	
	@RequestMapping(value="/delete.do", produces = "application/text; charset=UTF-8",
			method = RequestMethod.POST )
	@ResponseBody
	public String deleteStoreBookData(@RequestParam Map param, HttpSession session) {
		
		// sbidx로 삭제
		if( param.containsKey("sbidx") ) {
			String sbidx = (String)param.get("sbidx");
			
			if( storeBookService.delete(sbidx) )
				return "1";
		}
		
		return "0";
	}
	
	private StoreBookDTO getDTOFromParam( Map param, HttpSession session ) {
		StoreBookDTO dto = new StoreBookDTO();
		
		// dto.setSidx( session.getAttribute("sidx") );
		dto.setSidx( 1 ); // test : 1		
		
		if( param.containsKey("isbn") ) {
			String isbn = (String)param.get("isbn");
			
			if( isbn.length() == 10 ) dto.setIsbn10( isbn );
			else if( isbn.length() == 13 ) dto.setIsbn13( isbn );
		}
		
		if( param.containsKey("pk") )
			dto.setSbidx( (String)param.get("pk") );
		
		if( param.containsKey("status") )
			dto.setStatus( (String)param.get("status") );
		
		dto.setPoint( Integer.parseInt((String)param.get("point")) );
		dto.setCategory( (String)param.get("category") );
		
		
		return dto;
	}

}
