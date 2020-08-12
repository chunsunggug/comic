package com.project.comic.book.kakao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comic.Utility;
import com.project.comic.book.ISequenceSearch;

@Service
public class KakaoSearchService implements ISequenceSearch{
	
	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;
	
	@Override
	public Object nextSearch(Object model) {
		return kakaoBookSequenceSearch.nextSearch(model);
	}
	
	public JSONObject getBook(String isbn) {
		KakaoQueryModel md = new KakaoQueryModel();
		md.setPage( 0 );
		md.setTarget("isbn");
		md.setQuery(isbn);
		
		String result = (String)kakaoBookSequenceSearch.nextSearch(md);
		JSONObject json_result = (JSONObject)Utility.JSONParse(result);
		JSONArray json_documents = (JSONArray)json_result.get("documents");
		
		return (JSONObject)json_documents.get(0);
	}
}
