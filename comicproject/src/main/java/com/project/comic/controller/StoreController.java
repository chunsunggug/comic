package com.project.comic.controller;

import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.project.comic.Utility;
import com.project.comic.book.BookGroupVO;
import com.project.comic.book.BookVO;
import com.project.comic.book.ISequenceSearch;
import com.project.comic.book.kakao.BookGroupVOMakerByKakao;
import com.project.comic.book.kakao.BookVOMakerByKakao;
import com.project.comic.book.kakao.KakaoQueryModel;
import com.project.comic.order.OrderDTO;
import com.project.comic.order.OrderDTO.State;
import com.project.comic.order.OrderService;
import com.project.comic.order.OrderVO;
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
	private final int PAGE_LIST_SIZE = 10;
	private final int PAGE_SIZE = 10;
	
	@Autowired
	private IStoreBookService storeBookService; 
	
	@Autowired
	private BookVOMakerByKakao bookVOMakerByKakao;
	
	@Autowired
	private BookGroupVOMakerByKakao bookGroupVOMakerByKakao;
	
	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;
	
	@Autowired
	private OrderService orderService;
	
	// 점주의 도서관리 페이지 이동
	@RequestMapping(value="/listbook.do")
	public ModelAndView listBook(HttpSession session,
			@RequestParam(value="cp", defaultValue="1")int cp) {
		
		int sidx = (int)session.getAttribute("uidx");
		List<StoreBookDTO> list_dto = storeBookService.getPageList(cp, PAGE_LIST_SIZE, 1);
		List<BookVO> list_vo = bookVOMakerByKakao.getVOList(list_dto);
		
		int total = storeBookService.getBooksCountAll(sidx);
		String pagestr = PageMaker.makePage("/comic/store/listbook.do", total, PAGE_LIST_SIZE, PAGE_SIZE, cp);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("index");
		mv.addObject("page","store/listbook.jsp");
		mv.addObject("left_panel","");
		mv.addObject("panelidx", 5);
		mv.addObject( "listitem", list_vo );
		mv.addObject("pagestr", pagestr);
		
		return mv;
	}
	
	// 점포 도서 추가시 텍스트박스에 ISBN 검색하여 책정보 불러오기
	@RequestMapping(value="/addloadbookdata.do", produces = "application/text; charset=UTF-8",
					method = RequestMethod.POST)
	@ResponseBody
	public String getAddBookDataByISBN(@RequestParam(name="isbn") String isbn, HttpSession session) {
		
		int sidx = (int)session.getAttribute("uidx");
		BookGroupVO vo = new BookGroupVO();
		Gson gson = new Gson();
		
		if( isbn.length() == 10 || isbn.length() == 13 ) {
			// 책이 있으면 카카오 책정보랑 요금을 같이 가져가야함
			List<StoreBookDTO> list_dto = storeBookService.getBooksByIsbn(sidx, isbn);
			
			if( list_dto != null ) {
				return gson.toJson( bookGroupVOMakerByKakao.getVO( list_dto ) );
			}
			
			KakaoQueryModel model = new KakaoQueryModel();
			model.setPage(0);
			model.setTarget("isbn");
			model.setQuery(isbn);
			
			String result = (String)kakaoBookSequenceSearch.nextSearch(model);
			JSONObject json_result = (JSONObject)Utility.JSONParse(result);
			JSONArray json_documents = (JSONArray)json_result.get("documents");
			
			if( json_documents.size() == 0 )
				return null;
			
			vo.setKakaoDocuments( (JSONObject)json_documents.get(0) );
		}
	
		return gson.toJson( vo );
	}
	
	// 점포 수정/삭제시 텍스트박스에 ISBN 검색하여 책정보 불러오기
	@RequestMapping(value="/updateloadbookdata.do", produces = "application/text; charset=UTF-8",
			method = RequestMethod.POST)
	@ResponseBody
	public String getUpdateBookDataByISBN(@RequestParam(name="isbn") String isbn, HttpSession session) {

		int sidx = (int)session.getAttribute("uidx");
		Gson gson = new Gson();

		if( isbn.length() == 10 || isbn.length() == 13 ) {
			// 책이 있으면 카카오 책정보랑 요금을 같이 가져가야함
			List<StoreBookDTO> list_dto = storeBookService.getBooksByIsbn(sidx, isbn);

			if( list_dto != null ) {
				return gson.toJson( bookGroupVOMakerByKakao.getVO( list_dto ) );
			}else
				return null; // 위에서 안나왔다면 없는 번호		
		}/*
		// 나중에 관리번호 적으면 개별 도서상태 수정에 쓰일 것 지우지 마시오
		else if(isbn.length() == 16 || isbn.length() == 19) {
			// 여기서는 isbn이 아닌 기본키
			StoreBookDTO dto = (StoreBookDTO)storeBookService.getBookByPk( isbn );
			
			if( dto == null )
				return null;
			
			AllInOneBookVO vo = bookVOMakerByKakao.getVO( dto );
			
			return gson.toJson( vo );
		}*/
		
		// 여기까지 왔다면 그냥 없는번호
		return null;
	}
	
	// 추가하기 버튼 누루면 디비를 거쳐 추가하는 곳
	@RequestMapping(value="/register.do", produces = "application/text; charset=UTF-8",
					method = RequestMethod.POST)
	@ResponseBody
	public String registerStoreBookData(@RequestParam Map param, HttpSession session) {
		
		StoreBookDTO dto = new StoreBookDTO();
		dto.setCategory( (String)param.get("category") );
		dto.setIsbn10( (String)param.get("isbn") );
		dto.setIsbn13( (String)param.get("isbn") );
		dto.setPoint( Integer.parseInt((String)param.get("point")) );
		dto.setSidx((int)session.getAttribute("uidx"));
		
		int result = storeBookService.add( dto,
						Integer.parseInt((String)param.get("total")) );
		
		return result + "";
	}
	
	@RequestMapping(value="/update.do", produces = "application/text; charset=UTF-8",
			method = RequestMethod.POST )
	@ResponseBody
	public String updateStoreBookData(@RequestParam Map param, HttpSession session) {
		int result = 0;
		System.out.println(param);
		if( param.containsKey("isbn") ) {
			// isbn이 적힌 경우
			String isbn = (String)param.get("isbn"),
					category = (String)param.get("category");
			// int sidx = session.getAttribute("sidx");
			int sidx = 1, point = Integer.parseInt( (String)param.get("point") );
			
			result = storeBookService.update( sidx, isbn, point, category );
		}
		else if( param.containsKey("sbidx") ) {
			// 관리번호가 적힌 경우
			System.out.println("관리번호 들어옴");
			int sbidx = Integer.parseInt( (String)param.get("sbidx") );
			String status = (String)param.get("status");
			
			result = storeBookService.update( sbidx, status );
		}
		return "" + result;
	}
	
	@RequestMapping(value="/delete.do", produces = "application/text; charset=UTF-8",
			method = RequestMethod.POST )
	@ResponseBody
	public String deleteStoreBookData(@RequestParam Map param, HttpSession session) {
		
		// sbidx로 삭제
		if( param.containsKey("sbidx") ) {
			int sbidx = Integer.parseInt( (String)param.get("sbidx") );
			
			return "" + storeBookService.delete(sbidx);
		}
		
		return "0";
	}

	@RequestMapping(value="/deliverymanage.do")
	public ModelAndView delivery(HttpSession session,
			@RequestParam(value="cp", defaultValue="1")int cp,
			@RequestParam(value="state", defaultValue="breq") String state_) {
		int sidx = (int)session.getAttribute("uidx");
		OrderDTO.State state = Enum.valueOf(OrderDTO.State.class, state_.toUpperCase());
		String lower_case = state.toString().toLowerCase();
		List<OrderVO> vo_list = orderService.getOrdersPageByState(sidx, cp, 10, state);
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("delivery_page", "delivery" + lower_case + ".jsp"); 
		String pager = PageMaker.makePage("/comic/store/deliverymanage.do?state=" +lower_case, vo_list.size(), 10, 10, cp);
		
		mv.setViewName("index");
		mv.addObject("page","store/deliverymanage.jsp");
		mv.addObject("left_panel", "");
		mv.addObject("panelidx", 6);

		mv.addObject("order_list", vo_list);
		
		mv.addObject("pagestr", pager );
		
		return mv;
	}
	
	// 리턴값 정리
	// -1 수락요청 체크한게 아무것도 없다
	@RequestMapping(value="/breqok.do", method=RequestMethod.POST)
	@ResponseBody
	public String breqOk(@RequestBody String values) {
		JSONArray json_arr = (JSONArray)Utility.JSONParse(Utility.decodeString(values).split("=")[0]);
		
		if( json_arr.size() == 0 )
			return "-1";

		int[] oaidx = new int[json_arr.size()];
		
		for(int i=0; i<json_arr.size(); i++)
			oaidx[i] = Integer.parseInt(json_arr.get(i).toString());
		
		int result = orderService.nextStep(oaidx);
		
		return result+"";
	}
}
