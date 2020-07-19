package com.project.comic.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.comic.book.ISequenceSearch;
import com.project.comic.util.Utility;

@Controller
@RequestMapping(value="/store")
public class StoreController {

	// 9788959521227
	// 테스트용 isbn
	
	@Autowired
	ISequenceSearch kakaoBookSequenceSearch;

	// 점주의 도서관리 페이지 이동
	@RequestMapping(value="/listbook.do")
	public String listBook() {
		return "store/bookmanage";
	}
	
	// 점포 도서 추가시 ISBN 검색하여 책정보 불러오기
	@RequestMapping(value="/loadbookdata.do", produces = "application/text; charset=UTF-8",
					method = RequestMethod.POST)
	@ResponseBody
	public String getBookByISBN(@RequestBody String param) {
		
		param = Utility.decodeString( param );
		
		JSONObject json_param = new JSONObject();
		JSONObject search_query = new JSONObject();
		search_query.put( "target", "isbn" );
		search_query.put( "query", param );
		json_param.put( BookSearchController.ATTR_PARAM, search_query );
		
		// json group으로 만들어서 보내주어야한다
		JSONObject value_set = (JSONObject)kakaoBookSequenceSearch.nextSearch( json_param );
		JSONObject book = null;
		if( value_set != null ) {
			book = (JSONObject)((JSONArray)value_set.get("documents")).get(0);
			
			return book.toJSONString();
		}
		
		return null;
	}
	
	@RequestMapping(value="/register.do", method = RequestMethod.POST)
	@ResponseBody
	public String registerStoreBookData(@RequestBody String param) {
		param = Utility.decodeString( param );
		JSONObject json_param = (JSONObject)Utility.JSONParse(param);
		System.out.println(json_param);
		return "";
	}
	
	
}
