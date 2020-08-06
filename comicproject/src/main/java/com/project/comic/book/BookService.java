package com.project.comic.book;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.project.comic.Utility;
import com.project.comic.book.kakao.KakaoQueryModel;
import com.project.comic.storebook.IStoreBookDao;
import com.project.comic.storebook.IStoreBookService;
import com.project.comic.storebook.StoreBookDTO;

@Service
public class BookService {

	@Autowired
	IStoreBookService storeBookService;
	
	@Autowired
	IStoreBookDao storeBookDaoImpl;
	
	@Autowired
	ISequenceSearch kakaoBookSequenceSearch;
	
	// 순서
	// 페이지 링크에 isbn넣어줘서 연결
	// 점포별 가능여부 보여줄 항목 점포이름, 대여요금, 책상태, 책보러가기 버튼
	// 상세페이지 보여줄 것 타이틀, 섬네일, 대여요금, 저자, 번역, 도서 소개, isbn
	// storebook에서 가져와야 할 것 대여가능상태, 요금, isbn
	
	public List getTableList(String isbn, int itemsize) {
		// 책을 클릭하고 isbn이 넘겨졌다
		// isbn을 가져와 현재 위치에서 가까운 점포 각각에 대하여(미구현)
		// sidx와 isbn으로 책 대여 상태 여부를 10개 목록화 한다
		
		List list = new ArrayList<AllInOneBookVO>();
		
		for(int i=0; i < itemsize; i++)
			list.add( getTableItemVO( 1, isbn ) );
		
		return list;
	}
	
	public AllInOneBookVO getTableItemVO(int sidx, String isbn) {
		AllInOneBookVO vo = new AllInOneBookVO();
		
		// 점포에 빌릴 수 있는 책 있는지 검사
		int result = storeBookDaoImpl.canBorrow(sidx, isbn);
		
		if( result == 0 )
			throw new NoExistBook();
		
		// sidx로 점포 이름 가져오기 (미구현으로 생략)
		// isbn으로 StoreBook에서 검색해서 VO 만들기
		List<StoreBookDTO> dtos = storeBookDaoImpl.getBorrowableBooks(sidx, isbn);
		StoreBookDTO dto;
		
		if(dtos.size() == 0)
			throw new NoExistBook();
		
		dto = dtos.get(0);
		
		// 대여료 제일 싼거 찾기
		if( dtos.size() >= 2 ) {
			for( int i=1; i < dtos.size(); i++ )
				if( dtos.get(i).getPoint() < dto.getPoint() )
					dto = dtos.get(i);
		}
		
		// 책 정보 가져오기
		KakaoQueryModel model = new KakaoQueryModel();
		model.setPage(0);
		model.setQuery(isbn);
		model.setTarget("isbn");
		String ka_result = (String)kakaoBookSequenceSearch.nextSearch(model);
		JSONObject doc = (JSONObject)( (JSONArray)
				( (JSONObject)Utility.JSONParse(ka_result) )
				.get("documents") )
				.get(0);
		
		vo.setKakaoDocuments(doc);
		vo.setStoreBookDTO(dto);
		
		return vo;
	}
	
	public List<AllInOneBookVO> getContentsVOList(List<StoreBookDTO> dtos){
		List<AllInOneBookVO> list = new ArrayList<AllInOneBookVO>();
		
		for(int i=0; i < dtos.size(); i++ )
			list.add( getContentVO(dtos.get(i)) );
		
		return list;
	}
	
	public AllInOneBookVO getContentVO(StoreBookDTO dto) {
		AllInOneBookVO vo = new AllInOneBookVO();
		
		KakaoQueryModel model = new KakaoQueryModel();
		model.setPage(0);
		model.setQuery(dto.getIsbn13());
		model.setSize(1);
		model.setPage(1);
		model.setTarget("isbn");

		String result = (String)kakaoBookSequenceSearch.nextSearch(model);
		JSONObject book = (JSONObject)( (JSONArray)( (JSONObject)Utility.JSONParse(result)).get("documents") ).get(0);

		vo.setKakaoDocuments(book);
		vo.setStoreBookDTO(dto);
		
		System.out.println(vo);
		
		return vo;
	}
	
	public int addItemToCart(HttpServletResponse response, HttpServletRequest request,
			AllInOneBookVO vo) {
		// 쿠키를 전부 가져온다
		// 쿠키 중에 이미 bookmark가 있는지
		// 그리고 있다면 이미 해당 vo가 있는지 sbidx로 검사
		// 없다면 vo를 추가
		// 쿠키 조차 없다면 jsonarray를 새로 만들어서 쿠키를 만들어서 추가한다
		
		Cookie[] cookies = request.getCookies();
		
		// vo를 쿠키에 저장하기 위해 json으로 바꾸기
		Gson gson = new Gson();
		String gson_vo = gson.toJson(vo);
		JSONObject json_vo = (JSONObject)Utility.JSONParse(gson_vo);
		
		for(Cookie cookie : cookies ) {
			// bookmark 쿠키 찾기
			if( cookie.getName().equals("cart") ) {
				String cookie_value = cookie.getValue();
				String decoded_cookie = "";
				
				// 찾은 쿠키 decoding
				try {
					decoded_cookie = URLDecoder.decode( cookie_value, "utf-8" );
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return -1;
				}
				// decoding 한 문자열로 json으로 바꾸기
				JSONArray cookie_arr = (JSONArray)Utility.JSONParse(decoded_cookie);

				// 같은 거 있는지 검사
				for(Object json_obj : cookie_arr) {
					JSONObject book = (JSONObject)json_obj;
					String sbidx = (String)book.get("sbidx");
					
					if( sbidx.equals( vo.getSbidx() ) )
						return 1;
				}
				
				// 같은게 없으므로
				// 바꾼 jsonArray에 json으로 만든 vo 추가
				cookie_arr.add( json_vo );
				
				String encoded_value = "";
				try {
					encoded_value = URLEncoder.encode(cookie_arr.toJSONString(),"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
				cookie.setValue( encoded_value );
				response.addCookie(cookie);
				
				return 1;
			}
		}
		
		// 위에서 쿠키를 못 찾았다면 쿠키를 새로 만들어서 저장
		JSONArray arr = new JSONArray();
		arr.add( json_vo );
		
		String encoded_arr = "";
		
		try {
			encoded_arr = URLEncoder.encode( arr.toJSONString(), "utf-8" );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		Cookie cookie = new Cookie( "cart", encoded_arr );
		cookie.setMaxAge(1 * 60 * 60 * 24); //쿠키 유지 하루
		response.addCookie( cookie );
		
		return 1;
	}
}
