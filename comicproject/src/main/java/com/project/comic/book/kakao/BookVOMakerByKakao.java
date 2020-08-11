
package com.project.comic.book.kakao;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.comic.Utility;
import com.project.comic.book.BookVO;
import com.project.comic.book.ISequenceSearch;
import com.project.comic.storebook.StoreBookDTO;

@Component
public class BookVOMakerByKakao {
	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;
	
	public BookVO getVO(StoreBookDTO dto) {
		BookVO vo = new BookVO();
		
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
		
		return vo;
	}
	
	public List<BookVO> getVOList(List<StoreBookDTO> dtos){
		List<BookVO> list = new ArrayList<BookVO>();
		
		for(int i=0; i < dtos.size(); i++ )
			list.add( getVO(dtos.get(i)) );
		
		return list;
	}	
}