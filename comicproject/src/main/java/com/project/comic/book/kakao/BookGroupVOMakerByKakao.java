package com.project.comic.book.kakao;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.comic.Utility;
import com.project.comic.book.BookGroupVO;
import com.project.comic.book.ISequenceSearch;
import com.project.comic.storebook.StoreBookDTO;

@Component
public class BookGroupVOMakerByKakao {
	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;
	
	public BookGroupVO getVO(List<StoreBookDTO> dto) {
		BookGroupVO vo = new BookGroupVO();
		
		KakaoQueryModel model = new KakaoQueryModel();
		model.setPage(0);
		model.setQuery(dto.get(0).getIsbn13());
		model.setSize(1);
		model.setPage(1);
		model.setTarget("isbn");

		String result = (String)kakaoBookSequenceSearch.nextSearch(model);
		JSONObject book = (JSONObject)( (JSONArray)( (JSONObject)Utility.JSONParse(result)).get("documents") ).get(0);

		vo.setKakaoDocuments(book);
		vo.setStoreBookDTOList(dto);
		
		return vo;
	}
	
		
}



