package com.project.comic.pay;

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
import com.project.comic.book.ISequenceSearch;
import com.project.comic.storebook.IStoreBookDao;
import com.project.comic.storebook.IStoreBookService;
import com.project.comic.storebook.StoreBookDTO;
import com.project.comic.user.UserDao;
import com.project.comic.user.UserVO;

@Service
public class PayService {

	@Autowired
	IStoreBookService storeBookService;
	
	@Autowired
	IStoreBookDao storeBookDaoImpl;
	
	@Autowired
	ISequenceSearch kakaoBookSequenceSearch;
	
	@Autowired
	private UserDao userDaoImpl;
	
	@Autowired
	private IOrderDao orderDao;
	
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
		response.addCookie( cookie );
		
		return 1;
	}
	
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
				
				Cookie new_cookie = new Cookie("comiccart",
						valueToEncodedString(json_arr.toJSONString()) );
				response.addCookie(new_cookie);
				
				return 1;
			}
		}
		return 0;
	}

	// 실제로 결제를 진행하는 함수
	// 리턴값 정리
	// -1 유저의 포인트가 적다
	// -2 결제 목록을 가져올 쿠키가 없다 즉, 정상적인 접근이 아니다
	// -3 대여가능한 책이 없다
	@Transactional
	public int payPoint(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int uidx = (int)session.getAttribute("uidx");
		int user_point = (int)session.getAttribute("point");
		String user_id = (String)session.getAttribute("id");
		int total_point = 0;
		System.out.println("uidx : "+uidx);
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			// 카트 쿠키를 가려낸다
			if( cookie.getName().equals("comiccart")) {
				JSONArray item_arr = (JSONArray)valueToDecodedJSON(cookie.getValue());
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
					List<StoreBookDTO> canBorrow = storeBookDaoImpl.getBorrowableBooks(sidx, isbn);
					
					if( canBorrow.size() == 0 ) return -3;
					
					dto_tmp.setSbidx(canBorrow.get(0).getSbidx());
					
					// 주소 가져와서 세트
					UserVO user_vo = userDaoImpl.myInfo( user_id );
					dto_tmp.setUaddr( user_vo.getFullAddr() );
					order_list.add( dto_tmp );
				}
				
				total_point += 1000; // 택배비
				
				// 유저 포인트가 충분하면 결제 시작
				if( user_point >= total_point) {
					
					int result = userDaoImpl.payPoint( uidx, total_point );

					if( result == 0 ) throw new PayFailedException();	// payPoint sql 실패
					
					result = orderDao.addNewOrder(order_list);
					
					if( result != order_list.size() ) throw new CreateOrderFailedException();
					
					// 점수 빼기
					session.setAttribute( "point", user_point - total_point);
					
					// 쿠키 비우기
					item_arr.clear();
					cookie.setValue(valueToEncodedString(item_arr.toJSONString()));
					response.addCookie(cookie);
					
					return result;

				}else {
					return -1;	// 유저의 포인트가 적다
				}		
			}
		}
		return -2; // 결제 목록을 가져올 쿠키가 없다
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
}
