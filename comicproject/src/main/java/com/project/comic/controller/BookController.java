package com.project.comic.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	private final String KAKAO_BOOK_SEARCH_URL = "https://dapi.kakao.com/v3/search/book";
	private final String KAKAO_ID = "118237743806f276d679025f706c0e3c";

	private final String[] param_keyarr = {"query", "sort", "page", "size", "target"};
	private final String[] param_keydefault = {"", "accuracy", "1", "12", ""};

	// 검색페이지로 이동해서 검색결과 보여줌
	@RequestMapping(value="/search.do")
	public String bookSearch(@RequestParam Map param, HttpSession session) {
		
		
		JSONObject param_group = getBookSearchData( mapToJSON(param) ); // 폼에서 전달받은 데이터로 검색

		if( param_group != null )
			setSessionParam(session, param_group);
		else {

		}

		return "book/booksearchpage";
	}

	// 검색페이지에서 더보기를 누룬 경우
	@ResponseBody
	@RequestMapping(value="/searchmore.do", produces = "application/text; charset=UTF-8")
	public String bookSearchMore(HttpSession session) {
		JSONObject json_param = (JSONObject)session.getAttribute("searchparam");	// 폼에서 가져온 데이터
		JSONObject json_meta = (JSONObject)session.getAttribute("meta");			// 가지고 있던 카카오 meta 데이터
		JSONObject json_group = null;												// 카카오 서버에서 받아온 데이터 넣을 변수

		System.out.println("받아온 param : " + json_param.toJSONString());

		// 현재페이지가 끝 페이지가 아니면 계속
		if( !(boolean)json_meta.get("is_end") ) {
			// 다음 페이지로 올려줌
			json_param.put( "page", Integer.parseInt((String)json_param.get("page")) + 1 + "" );
			System.out.println("다음 페이지 param : " + json_param.toJSONString());

			json_group = getBookSearchData( json_param );

			// 새로 가져온 페이지 데이터들 세션에 저장
			setSessionParam( session, json_group );
		}else
			return null;
		
		return json_group.toString();
	}

	// parameter key 설명
	// query 	: 검색어
	// sort 	: 정렬방법 accuracy(정확도순), recency(최신순)
	// page 	: 결과 페이지 번호 1~50, 디폴트 1
	// size 	: 한페이지에 보여질 문서 수 1~50, 디폴트 10
	// target 	:검색필드제한 title, isbn, publisher, person
	// url 뒤에 parameter 붙여서 데이터 가져온 후 JSON으로 parsing해서 return
	private JSONObject getBookSearchData( JSONObject param ){
		URL url;
		HttpURLConnection con;
		String request_url = KAKAO_BOOK_SEARCH_URL.toString();

		// key default 초기화
		for(int i=0; i < param_keyarr.length; i++) 
			if( param.get(param_keyarr[i]) == null ) param.put(param_keyarr[i], param_keydefault[i] );

		Iterator key_iterator = param.keySet().iterator();
		request_url += "?";

		try {
			// url 뒤에 parameter 붙여주는 구간
			while( key_iterator.hasNext() ){
				String key = (String)key_iterator.next();
				String encoded_param = URLEncoder.encode((String)param.get(key), "utf-8");
				request_url += key + "=" + encoded_param + "&";
			}
			request_url = request_url.substring(0, request_url.length() - 1);

			System.out.println(request_url);
			url = new URL(request_url);						// throw
			con = (HttpURLConnection)url.openConnection();	// throw

			con.setRequestMethod("POST");					// throw
			con.setRequestProperty("Authorization", "KakaoAK " + KAKAO_ID);

			int responseCode = con.getResponseCode();		// throw

			// 데이터를 성공적으로 가져오면 json으로 파싱해서 return
			if( responseCode == HttpURLConnection.HTTP_OK ) {
				BufferedReader br = new BufferedReader( new InputStreamReader( con.getInputStream(), "utf-8" ) ); // con throw
				StringBuffer sb = new StringBuffer();

				while( br.ready() )  			// throw
					sb.append(br.readLine());	// throw

				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject)parser.parse( sb.toString() );	// throw
				jsonObject.put("searchparam", param);

				System.out.println("kakao get data success");
				return jsonObject;
			}else {
				BufferedReader br = new BufferedReader( new InputStreamReader( con.getErrorStream(), "utf-8" ) ); // con throw
				StringBuffer sb = new StringBuffer();

				while( br.ready() )  			// throw
					sb.append(br.readLine());	// throw
				System.out.println("kakao get data fail code : " + responseCode);
				System.out.println(sb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	private JSONObject mapToJSON( Map map ) {
		Iterator iterator = map.keySet().iterator();
		JSONObject json = new JSONObject();

		while( iterator.hasNext() ) {
			String key = (String)iterator.next();
			json.put(key, map.get(key) );
		}

		return json;
	}

	private void setSessionParam(HttpSession session, JSONObject object) {
		session.setAttribute("meta", object.get("meta"));
		session.setAttribute("documents", object.get("documents"));
		session.setAttribute("searchparam", object.get("searchparam"));
	}
}
