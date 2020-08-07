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
	
	// 해당 책을 갖고 있는 점포를 보여주는 페이지에서 아이템을 묶어서 가져오는 함수
	public List getTableList(String isbn, int itemsize) {
		// 책을 클릭하고 isbn이 넘겨졌다
		// isbn을 가져와 현재 위치에서 가까운 점포 각각에 대하여(미구현)
		// sidx와 isbn으로 책 대여 상태 여부를 10개 목록화 한다
		
		if( storeBookDaoImpl.existStoreHasBook(isbn) == 0 ) return null;
		
		List<Integer> list_sidx = storeBookDaoImpl.getStoreHasBook(isbn);
		List list = new ArrayList<AllInOneBookVO>();
		
		// 위 과정에서 점포 sidx를 정리해서 가져와야 한다
		int cycle_count = list_sidx.size() > itemsize ? itemsize : list_sidx.size();
		
		for(int i=0; i < cycle_count; i++) {
			List<StoreBookDTO> dto_list = storeBookDaoImpl.getBooksByIsbn(list_sidx.get(i), isbn);
			AllInOneBookVO vo = getContentVO( dto_list.get(0) );
			vo.setStoreBookDTOList(dto_list);
			list.add(vo);
		}
		
		return list;
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
			if( cookie.getName().equals("comiccart") ) {
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
					long sidx = (long)book.get("sidx");
					String isbn13 = (String)book.get("isbn13"); 
					
					if( sidx == vo.getSidx() && isbn13.equals( vo.getIsbn13() ) )
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
		
		Cookie cookie = new Cookie( "comiccart", encoded_arr );
		cookie.setMaxAge(1 * 60 * 60 * 24); //쿠키 유지 하루
		response.addCookie( cookie );
		
		return 1;
	}
	
	public int DeleteItemCart(HttpServletResponse response, HttpServletRequest request,
			int sidx, String isbn13) {
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("comiccart")) {
				String origin_value = cookie.getValue();
				String decoded_value = "";
				
				try {
					decoded_value = URLDecoder.decode(origin_value, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
				
				JSONArray json_arr = (JSONArray)Utility.JSONParse(decoded_value);
				System.out.println("pre json arr : " + json_arr);
				
				for( int i=0; i < json_arr.size(); i++) {
					JSONObject json_ = (JSONObject)json_arr.get(i);
					long cookie_sidx = (long)json_.get("sidx");
					String cookie_isbn13 = (String)json_.get("isbn13");
					if( cookie_sidx == sidx && cookie_isbn13.equals(isbn13) ) {
						json_arr.remove(i);
						System.out.println("json cookie 삭제");
					}
				}
				System.out.println("post json arr : " + json_arr);
				
				String encoded_value = "";
				
				try {
					encoded_value = URLEncoder.encode(json_arr.toJSONString(), "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
				
				Cookie new_cookie = new Cookie("comiccart", encoded_value);
				response.addCookie(new_cookie);
				
				return 1;
			}
		}
		return 0;
	}
}
