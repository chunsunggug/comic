package com.project.comic.storebook;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comic.Utility;
import com.project.comic.book.IBookSearchService;

@Service
public class StoreBookSearchService implements IBookSearchService{

	@Autowired
	private IStoreBookDao storeBookDaoImpl;
	
	@Autowired
	private IBookSearchService kakaoBookSearchService;
	
	@Override
	public String bookSearch(Map map_param, HttpSession session) {
		// 카카오에서 검색해서 결과를 가져온다
		// 그 중에서 우리 디비에 저장이 되어있는 책들로만 구성해서 documents를 새로 짜서 리턴
		/*
		JSONArray json_new_documents = new JSONArray();
		String result = kakaoBookSearchService.bookSearch(map_param, session);
		JSONObject json_result = (JSONObject)Utility.JSONParse(result);
		JSONArray json_documents = (JSONArray)json_result.get("documents");
		JSONObject json_meta = (JSONObject)json_result.get("meta");
		
		do {
		for(int i=0; i < json_documents.size(); i++) {
			JSONObject json_book = (JSONObject)json_documents.get(i);
			String isbn = (String)json_book.get("isbn");
			String isbn13 = isbn.substring(11, 23);
			System.out.println("isbn13 : " + isbn13);
			
			if( storeBookDaoImpl.existStoreHasBook(isbn13) > 0 )
				json_new_documents.add(json_book);
		}
		}while( Boolean.parseBoolean((String)json_meta.get("is_end")) == false );
			
		json_result.put("documents", json_new_documents);
		
		return json_result.toJSONString();*/
		return null;
	}

	@Override
	public String bookSearchMore(HttpSession session) {
		
		return null;
	}
	
}
