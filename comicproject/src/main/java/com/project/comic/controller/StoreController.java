package com.project.comic.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.project.comic.storebook.StoreBookDTO;
import com.project.comic.storebook.StoreBookService;

@Controller
@RequestMapping(value="/store")
public class StoreController {

	// 9788959521227
	// 테스트용 isbn
	private final String SIDX = "uidx";
	private final int PAGE_LIST_SIZE = 10;
	private final int PAGE_SIZE = 10;
	
	@Autowired
	StoreBookService storeBookService; 

	private final int LISTSIZE = 15;
	
	// 점주의 도서관리 페이지 이동
	@RequestMapping(value="/listbook.do")
	public ModelAndView listBook(HttpSession session,
			@RequestParam(value="cp", defaultValue="1")int cp) {
		
		// test 상황 sidx : 1
		List listitem = storeBookService.getPageList(cp, LISTSIZE, 1);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("store/bookmanage");
		mv.addObject("listitem", listitem);
		
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
		
		String result = "";//storeBookService.getBookByIsbn(param);
		
		if(result != null)
			return result;
		
		return null;
	}
	
	// 추가하기 버튼 누루면 디비를 거쳐 추가하는 곳
	@RequestMapping(value="/register.do", produces = "application/text; charset=UTF-8",
					method = RequestMethod.POST)
	@ResponseBody
	public String registerStoreBookData(@RequestBody String param, HttpSession session) {
		
		JSONObject json_param = (JSONObject)Utility.JSONParse( param );
		StoreBookDTO dto = new StoreBookDTO();
		
		dto.setPoint( Integer.parseInt((String)json_param.get("point")) );
		dto.setIsbn( (String)json_param.get("isbn") );
		dto.setSidx(1);  // test 용 uidx 1
		//dto.setSidx( Integer.parseInt((String)session.getAttribute(SIDX)) );
		
		//if( storeBookService.add(dto) == 1 )
		//	return "1";
		//else
			return "0";
	}
}
