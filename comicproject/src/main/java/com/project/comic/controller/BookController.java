package com.project.comic.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	private final String KAKAO_BOOK_SEARCH_URL = "https://dapi.kakao.com/v3/search/book";
	private final String KAKAO_ID = "118237743806f276d679025f706c0e3c";
	
	@RequestMapping(value="/search.do")
	public String bookSearch(@RequestParam Map param, HttpServletRequest request) {
		
		JSONObject jsonObject = jsonObject = getBookSearchData(KAKAO_BOOK_SEARCH_URL, param);
		
		request.setAttribute("meta", jsonObject.get("meta").toString());
		request.setAttribute("documents", jsonObject.get("documents").toString());
		
		return "serch";
	}

	// parameter key 설명
	// query 	: 검색어
	// sort 	: 정렬방법 accuracy(정확도순), recency(최신순)
	// page 	: 결과 페이지 번호 1~50, 디폴트 1
	// size 	: 한페이지에 보여질 문서 수 1~50, 디폴트 10
	// target 	:검색필드제한 title, isbn, publisher, person
	// url 뒤에 parameter 붙여서 데이터 가져온 후 JSON으로 parsing해서 return
	private JSONObject getBookSearchData(String request_url, Map param){
		URL url;
		HttpURLConnection con;
		
		Iterator key_iterator = param.keySet().iterator();
		request_url += "?";
		
		// url 뒤에 parameter 붙여주는 구간
		while( key_iterator.hasNext() ){
			String key = (String)key_iterator.next();
			request_url += key + "=" + (String)param.get(key) + "&" ;
		}
		request_url = request_url.substring(0, request_url.length() - 1);
		
		try {
	
			url = new URL(request_url);						// throw
			con = (HttpURLConnection)url.openConnection();	// throw
			
			con.setRequestMethod("POST");					// throw
			con.setRequestProperty("Authorization", "KakaoAK " + KAKAO_ID);
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

			int responseCode = con.getResponseCode();		// throw
			
			// 데이터를 성공적으로 가져오면 json으로 파싱해서 return
			if( responseCode == 200 ) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); // con throw
				StringBuffer sb = new StringBuffer();
				
				while( br.ready() )  			// throw
					sb.append(br.readLine());	// throw
				
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject)parser.parse(sb.toString());	// throw
				
				return jsonObject;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
