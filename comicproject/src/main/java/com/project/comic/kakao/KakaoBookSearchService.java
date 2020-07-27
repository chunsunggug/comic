package com.project.comic.kakao;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comic.Utility;
import com.project.comic.book.IBookSearchService;
import com.project.comic.book.ISequenceSearch;

@Service
public class KakaoBookSearchService implements IBookSearchService {

	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;
	
	@Override
	public String bookSearch(Map map_param, HttpSession session) {
		KakaoQueryModel model = new KakaoQueryModel();
		model.setMapParam( map_param );
		model.setPage(0);
		
		String result = (String)kakaoBookSequenceSearch.nextSearch( model );
		
		JSONObject json_result = (JSONObject)Utility.JSONParse(result);
		JSONArray json_documents = (JSONArray)json_result.get("documents");
		JSONObject json_meta = (JSONObject)json_result.get("meta");
		
		session.setAttribute("query_model", model);
		session.setAttribute("meta", json_meta);
		session.setAttribute("documents", json_documents);
		
		return result;
	}

	@Override
	public String bookSearchMore(HttpSession session) {
		KakaoQueryModel model = (KakaoQueryModel)session.getAttribute("query_model");
		JSONObject json_meta = (JSONObject)session.getAttribute("meta");
		
		if( session.getAttribute("documents") != null ) 
			session.removeAttribute( "documents" );
		
		if( (boolean)json_meta.get("is_end") )
			return null;
		
		String result = (String)kakaoBookSequenceSearch.nextSearch(model);
		
		JSONObject json_result = (JSONObject)Utility.JSONParse(result);
		json_meta = (JSONObject)json_result.get("meta");
		
		session.setAttribute("query_model", model);
		session.setAttribute("meta", json_meta);
		
		return result;
	}

}
