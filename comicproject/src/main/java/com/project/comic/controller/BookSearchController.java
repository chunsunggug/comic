package com.project.comic.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.comic.book.ISequenceSearch;
import com.project.comic.util.Utility;

@Controller
public class BookSearchController {
	
	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;

	public static final String ATTR_PARAM = "search_query";
	public static final String ATTR_DOCUMENTS = "documents";
	public static final String ATTR_META = "meta";
	
	// 검색페이지로 이동해서 검색결과 보여줌
	@RequestMapping(value="/search.do")
	public String bookSearch(@RequestParam Map param, HttpSession session, HttpServletRequest request) {
		
		JSONObject json_param = Utility.mapToJSON(param);
		JSONObject json_set = new JSONObject();
		json_set.put( ATTR_PARAM, json_param );
		
		// documents와 meta 정보 얻어온다
		JSONObject search_data = kakaoBookSequenceSearch.nextSearch( json_set );
		
		if( search_data != null ) {
			// 가져온 정보를 session attribute로 넘겨준다
			// 첫 데이터는 jsp에서 jstl을 이용하여 화면에 보여준다
			request.setAttribute( ATTR_DOCUMENTS, search_data.get(ATTR_DOCUMENTS) );
			initSessionAttribute( search_data, session );
		}
		
		return "search/booksearchpage";
	}

	// 검색페이지에서 더보기를 누룬 경우
	@ResponseBody
	@RequestMapping(value="/searchmore.do", produces = "application/text; charset=UTF-8")
	public String bookSearchMore(HttpSession session) {
		JSONObject json_param = new JSONObject();
		
		// session에 저장해놓은 query와 meta 정보를 가져와서 map으로 만들어 parameter로 사용
		JSONObject search_query = (JSONObject)session.getAttribute( ATTR_PARAM );
		JSONObject meta = (JSONObject)session.getAttribute( ATTR_META );
		json_param.put( ATTR_PARAM, search_query );
		json_param.put( ATTR_META, meta );
		
		JSONObject json_dataset = kakaoBookSequenceSearch.nextSearch( json_param );
		initSessionAttribute( json_dataset, session );
		
		json_dataset.remove( ATTR_PARAM );
		
		return json_dataset.toJSONString();
	}
	
	private void initSessionAttribute(JSONObject object, HttpSession session) {
		session.setAttribute( ATTR_META, object.get(ATTR_META) );
		session.setAttribute( ATTR_PARAM, object.get(ATTR_PARAM) );
	}
	
	
}
