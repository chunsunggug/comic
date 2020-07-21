package com.project.comic.storebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.comic.book.ISequenceSearch;
import com.project.comic.controller.BookSearchController;

@Service
public class StoreBookService {

	@Autowired
	@Qualifier("impl")
	private IStoreBookDao storeBookDao;
	
	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;
	
	@Transactional
	public int add(StoreBookDTO dto) {
		
		int count = storeBookDao.getCount(dto);
		dto.setIdx( ++count );	

		dto.setSbidx(makePKFromDTO(dto));
		
		// isbn 받아서 책 목록에 등록
		int result = storeBookDao.add(dto);
		System.out.println("책 추가 로우 : " + result);
		
		return result;
	}
	
	// 도서 추가 시 isbn칸에 isbn쓰면 자동으로 책데이터 불러오는데 사용하는 용도
	public JSONObject getBookByIsbn(String isbn) {
		JSONObject json_group = new JSONObject();
		JSONObject json_query = new JSONObject();
		
		// isbn이 10자리, 13자리 2개 나눠서 가능한한 13자리로 받도록한다
		String[] isbn1013 = isbn.split(" ");
		if( isbn1013.length == 1) isbn = isbn1013[0];
		else
			isbn = isbn1013[1];
		
		json_query.put("query", isbn );
		json_query.put("target", "isbn");
		json_group.put(BookSearchController.ATTR_PARAM, json_query);
		
		JSONObject value_set = (JSONObject)kakaoBookSequenceSearch.nextSearch(json_group);

		JSONArray documents = (JSONArray)value_set.get( BookSearchController.ATTR_DOCUMENTS );

		if(documents.size() == 0)
			return null;
		
		return (JSONObject)documents.get(0);
	}

	// 페이지별 목록 리스트
	@Transactional
	public List getPageList(int cp, int listsize, int sidx) { // 현재페이지, 페이지목록수, 점포idx
		Map datamap = new HashMap();
		datamap.put("sidx", sidx);
		datamap.put("start", (cp - 1) * listsize );
		datamap.put("size", listsize);
		
		List for_join_data = storeBookDao.getForPageJoinData(datamap);	// 페이지 별 조인할 데이터 리스트
		List complete_dto = getJoinDTO(for_join_data);	// 조인해서 완성본 dto 리턴
		
		return complete_dto;
	}
	
	public int getBookCount(int sidx){
		return storeBookDao.getStoreBookAllCount(sidx);
	}
	// dto에 있는 속성들을 이용해서 기본키를 만들어낸다
 	private String makePKFromDTO(StoreBookDTO dto) {
		String[] isbnsplit = dto.getIsbn().split(" ");
		String pre =  String.format("%03d", dto.getSidx());
		String middle, post;
		
		if(isbnsplit.length == 1) middle = isbnsplit[0];
		else
			middle = isbnsplit[1];
		
		post = String.format("%03d", dto.getIdx());
		
		return pre + middle + post;
	}
	
	// StoreBookDTO 모델에서 kakao에서 가져온 데이터와 합쳐서 StoreBookKakaoDTO로 치환
	private void storeBookToKakaoDTO(StoreBookDTO from, JSONObject joinSum ,StoreBookKakaoDTO to) {
		JSONArray auths = (JSONArray)joinSum.get("authors");
		String tmp = "";
		for(int i=0; i < auths.size(); i++ ) {
			tmp += auths.get(i);
			if( i != auths.size() - 1)
				tmp += ",";
		}
		to.setAuthors(tmp);
		to.setThumbnail((String)joinSum.get("thumbnail"));
		to.setTitle((String)joinSum.get("title"));
		to.setPoint(from.getPoint());
		to.setStatus(from.getStatus());
		to.setSbidx(from.getSbidx());
		to.setSdate(from.getSdate());
	}

	private List getJoinDTO(List<StoreBookDTO> storeBookDto){
		List arr = new ArrayList<StoreBookKakaoDTO>();
		
		for(int i=0; i < storeBookDto.size(); i++ ) {
			StoreBookDTO dto = storeBookDto.get(i);
			JSONObject json_book = getBookByIsbn(dto.getIsbn());
 
			StoreBookKakaoDTO toDto = new StoreBookKakaoDTO();
			
			storeBookToKakaoDTO(dto, json_book, toDto);
			arr.add(toDto);
		}
		return arr;
	}
}
