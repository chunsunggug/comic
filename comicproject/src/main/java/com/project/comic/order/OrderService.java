package com.project.comic.order;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.comic.Utility;
import com.project.comic.book.BookGroupVO;
import com.project.comic.book.kakao.KakaoSearchService;
import com.project.comic.storebook.IStoreBookDao;
import com.project.comic.storebook.IStoreBookService;
import com.project.comic.storebook.StoreBookDTO;
import com.project.comic.user.UserDao;
import com.project.comic.user.UserVO;

@Service
public class OrderService {

	@Autowired
	IStoreBookService storeBookService;
	
	@Autowired
	IStoreBookDao storeBookDaoImpl;
	
	@Autowired
	KakaoSearchService kakaoSearchService;
	
	@Autowired
	private UserDao userDaoImpl;
	
	@Autowired
	private IOrderDao orderDao;
	
	// 카트에 목록 추가하기
	public int addItemToCart(HttpServletResponse response, HttpServletRequest request,
			String isbn, int sidx) {
		// 쿠키를 전부 가져온다
		// 쿠키 중에 이미 comiccart가 있는지 찾는다
		// 있다면 sidx, isbn을 찾아서 있으면 카운트1개 추가 없으면 새로만들기
		// 쿠키 조차 없다면 jsonarray를 새로 만들어서 쿠키를 만들어서 추가한다
		
		Cookie[] cookies = request.getCookies();
		
		// vo를 쿠키에 저장하기 위해 json으로 바꾸기
		JSONObject json_item = new JSONObject();
		json_item.put("isbn", isbn);
		json_item.put("sidx", sidx);
		
		for(Cookie cookie : cookies ) {
			// comiccart 쿠키 찾기
			if( cookie.getName().equals("comiccart") ) {				
				
				JSONArray cookie_arr = (JSONArray)valueToDecodedJSON(cookie.getValue());
						
				// 같은 거 있는지 검사
				for(Object json_obj : cookie_arr) {
					JSONObject book = (JSONObject)json_obj;
					long tmp_sidx = (long)book.get("sidx");
					String tmp_isbn = (String)book.get("isbn");
					
					if( tmp_sidx == sidx && 
						tmp_isbn.equals( isbn ) )
						return 1;
				}
				
				// 같은게 없으므로
				// 바꾼 jsonArray에 추가
				cookie_arr.add( json_item );
				cookie.setPath("/comic");
				cookie.setValue( valueToEncodedString(cookie_arr.toJSONString()) );
				response.addCookie(cookie);
				
				return 1;
			}
		}
		
		// 위에서 쿠키를 못 찾았다면 쿠키를 새로 만들어서 저장
		JSONArray arr = new JSONArray();
		arr.add( json_item );
		
		Cookie cookie = new Cookie( "comiccart", 
				valueToEncodedString(arr.toJSONString()) );
		cookie.setMaxAge(1 * 60 * 60 * 24); //쿠키 유지 하루
		cookie.setPath("/comic");
		response.addCookie( cookie );
		
		return 1;
	}
	// 카트에서 목록 제거
	public int DeleteItemCart(HttpServletResponse response, HttpServletRequest request,
			int sidx, String isbn) {
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("comiccart")) {
				JSONArray json_arr = (JSONArray)valueToDecodedJSON(cookie.getValue());
				
				System.out.println("pre json arr : " + json_arr);
				
				for( int i=0; i < json_arr.size(); i++) {
					JSONObject json_ = (JSONObject)json_arr.get(i);
					long cookie_sidx = (long)json_.get("sidx");
					String cookie_isbn = (String)json_.get("isbn");
					if( cookie_sidx == sidx && cookie_isbn.equals(isbn) ) {
						json_arr.remove(i);
						System.out.println("json cookie 삭제");
					}
				}
				System.out.println("post json arr : " + json_arr);
				
				if( json_arr.size() == 0 )cookie.setMaxAge(0);
				
				cookie.setValue(valueToEncodedString(json_arr.toJSONString() ));
				cookie.setPath("/comic");
				response.addCookie(cookie);
				
				return 1;
			}
		}
		return 0;
	}

	// 실제로 결제를 진행하는 함수
	// 리턴값 정리
	// -1 유저의 포인트가 적다
	// -3 대여가능한 책이 없다
	@Transactional
	public int payPoint(String cookie_value, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int uidx = (int)session.getAttribute("uidx");
		int user_point = (int)session.getAttribute("point");
		String user_id = (String)session.getAttribute("id");
		int total_point = 0;

		JSONArray item_arr = (JSONArray)valueToDecodedJSON( cookie_value );
		List<OrderDTO> order_list = new ArrayList<OrderDTO>();
		int new_oidx = orderDao.getMaxOidx() + 1;

		// 쿠키에 저장되어있는 도서목록들 대여료 전부 더하기
		// 주문 row 만들기 위한 dto 정리
		for(int i=0; i < item_arr.size(); i++) {
			JSONObject tmp = (JSONObject)item_arr.get(i);
			int sidx = Integer.parseInt(tmp.get("sidx").toString());
			String isbn = (String)tmp.get("isbn");
			BookGroupVO vo = (BookGroupVO)storeBookService.getBookGroupVO( sidx, isbn );
			OrderDTO dto_tmp = new OrderDTO();

			total_point += vo.getPoint();

			// 주문번호, 점포책고유번호, 대여료
			dto_tmp.setOidx( new_oidx );
			dto_tmp.setPoint( vo.getPoint() );
			dto_tmp.setUidx(uidx);
			dto_tmp.setSidx(sidx);
			List<StoreBookDTO> canBorrow = storeBookDaoImpl.getBorrowableBooks(sidx, isbn);

			if( canBorrow.size() == 0 ) return -3;

			dto_tmp.setSbidx(canBorrow.get(0).getSbidx());

			// 주소 가져와서 세트
			UserVO user_vo = userDaoImpl.myInfo( user_id );
			dto_tmp.setUaddr( user_vo.getFullAddr() );
			order_list.add( dto_tmp );
		}

		// 유저 포인트가 충분하면 결제 시작
		if( user_point >= total_point) {

			// 점수 빼기
			session.setAttribute( "point", user_point - total_point);
			int result = userDaoImpl.payPoint( uidx, total_point );

			if( result == 0 ) throw new PayFailedException();	// payPoint sql 실패

			result = 0;
			for( OrderDTO dto : order_list) {
				result += orderDao.addNewOrder(dto);
				storeBookDaoImpl.updateBook(dto.getSbidx(), "W");	// 책 상태 W로 변경
			}

			if( result != order_list.size() ) throw new CreateOrderFailedException();
			
			// 쿠키 비우기
			for(int i=0; i < item_arr.size(); i++) {
				JSONObject json_ = (JSONObject)item_arr.get(i);
				int sidx = Integer.parseInt(json_.get("sidx").toString());
				String isbn = (String)json_.get("isbn");
				DeleteItemCart(response, request, sidx, isbn);
			}
			
			return result;

		}else {
			return -1;	// 유저의 포인트가 적다
		}		

	}
	
	public List<OrderVO> getOrdersPageByState(int sidx, int cp, int listsize, OrderDTO.State state){
		List<OrderDTO> dto_list = orderDao.getOrdersPageByState(sidx, cp, listsize, state);
		List<OrderVO> vo_list = new ArrayList<OrderVO>();
		
		// DTO를 VO로 바꿔준다
		if( dto_list != null) {
			for(OrderDTO dto : dto_list) {
				OrderVO vo = orderDtoToVo(dto);
				vo_list.add(vo);
			}
		}
		return vo_list;
	}
	
	public List<OrderVO> getOrdersPageByType(int sidx, int cp, int listsize, String type){
		List<OrderDTO> dto_list = orderDao.getOrdersPageByType(sidx, cp, listsize, type);
		List<OrderVO> vo_list = new ArrayList<OrderVO>();
		
		if( dto_list != null) {
			for(OrderDTO dto : dto_list) {
				OrderVO vo = orderDtoToVo(dto);
				vo_list.add(vo);
			}
		}
		
		return vo_list;
	}
	
	public int getOrdersCountByType(int uidx, String type) {
		return orderDao.getOrdersCountByType(uidx, type);
	}
	
	@Transactional
	public int reqDelay(int oaidx) {
		OrderDTO odto = orderDao.getDTO(oaidx);
		StoreBookDTO sbdto = storeBookDaoImpl.getBook( odto.getSbidx() );
		
		if ( odto.getDelcount() != sbdto.getMaxexpcount() ) {
			return orderDao.delayExpDate( oaidx, sbdto.getExpdel() );
		}
		
		return 0;
	}
	
	@Transactional
	public int nextStep(int oaidx) {
		OrderDTO odto = orderDao.getDTO(oaidx);
		
		switch( odto.getState() ) {
		case BREQ: 	
			return orderDao.changeState(oaidx, "bcdate", OrderDTO.State.BC);
		case BC: 	
			return orderDao.changeState(oaidx, "bddate", OrderDTO.State.BD);
		case BD:	
			orderDao.changeState(oaidx, "bdcdate", OrderDTO.State.BDC);
			StoreBookDTO sbdto = storeBookDaoImpl.getBook(odto.getSbidx());
				
			orderDao.setExpDate(odto.getOaidx(), sbdto.getExp());
			storeBookDaoImpl.updateBook(sbdto.getSbidx(), "B");		// 대여중B로 상태 변경
			return 1;
		case BDC:	
			break;
		case RREQ:	
			return orderDao.changeState(oaidx, "rcdate", OrderDTO.State.RC);
		case RC:	
			return orderDao.changeState(oaidx, "rddate", OrderDTO.State.RD);
		case RD:	
			return orderDao.changeState(oaidx, "rdcdate", OrderDTO.State.RDC);
		case RDC:
			break;
		}
		
		return 0;
	}
	
	@Transactional
	public int nextStep(int[] oaidx) {
		int result = 0;
		for(int oaidx_ : oaidx)
			result += nextStep(oaidx_);
		
		return result;
	}
		
	private String valueToEncodedString(String str) {
		try {
			String result = URLEncoder.encode(str, "utf-8");
			
			return result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Object valueToDecodedJSON(String str) {
		try {
			String result = URLDecoder.decode(str, "utf-8");
			Object json_result = Utility.JSONParse(result);
			return json_result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private OrderVO orderDtoToVo(OrderDTO dto) {
		OrderVO vo = new OrderVO();
		String isbn = dto.getIsbn10() != null ? dto.getIsbn10() : dto.getIsbn13();
		JSONObject json_book = kakaoSearchService.getBook(isbn);
		vo.setDTO(dto);
		vo.setKakao(json_book);
		return vo;
	}

}
