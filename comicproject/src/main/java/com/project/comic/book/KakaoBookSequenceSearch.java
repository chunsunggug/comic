package com.project.comic.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.project.comic.util.Utility;

@Component
public class KakaoBookSequenceSearch implements ISequenceSearch {
	
	private final String KAKAO_BOOK_SEARCH_URL = "https://dapi.kakao.com/v3/search/book";
	private final String KAKAO_ID = "118237743806f276d679025f706c0e3c";

	private final String[] param_keyarr = { "query", "sort", "page", "size", "target" };
	private final String[] param_keydefault = { "", "accuracy", "1", "12", "" };

	// parameter key 설명
	// query 	: 검색어
	// sort 	: 정렬방법 accuracy(정확도순), recency(최신순)
	// page 	: 결과 페이지 번호 1~50, 디폴트 1
	// size 	: 한페이지에 보여질 문서 수 1~50, 디폴트 10
	// target 	: 검색필드제한 title, isbn, publisher, person
	// url 뒤에 parameter 붙여서 데이터 가져온 후 JSON으로 parsing해서 return
	@Override
	public JSONObject nextSearch(JSONObject param) {
		URL url;
		HttpURLConnection con;
		String request_url = KAKAO_BOOK_SEARCH_URL.toString();
		
		try {

			JSONObject meta = (JSONObject)param.get("meta");
			JSONObject query = (JSONObject)param.get("search_query");

			// key default 초기화
			for( int i=0; i < param_keyarr.length; i++ ) {
				if( query.get( param_keyarr[i] ) == null )
					query.put( param_keyarr[i], param_keydefault[i] );
				else if( i == 2 ) { // page
					//	page + 1 update
					if( meta != null && !(boolean)meta.get("is_end") ) {
						int page = Integer.parseInt( (String)query.get( param_keyarr[i] ) ) ;
						query.put( param_keyarr[i], ++page + "" );
					}
				}
			}
			
			Iterator key_iterator = query.keySet().iterator();
			request_url += "?";

			// url 뒤에 key값에 따라 parameter 붙여주는 구간
			while( key_iterator.hasNext() ){
				String key = (String)key_iterator.next();
				String encoded_param = URLEncoder.encode( (String)query.get(key), "utf-8" );
				request_url += key + "=" + encoded_param + "&";
			}
			request_url = request_url.substring( 0, request_url.length() - 1 );

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

				// meta 와 document를 받아옴
				while( br.ready() )  			// throw
					sb.append(br.readLine());	// throw

				JSONObject json_dataset = (JSONObject)Utility.JSONParse( sb.toString() );	// throw
				json_dataset.put( "search_query", query );
				
				System.out.println("kakao get data success");

				return json_dataset;
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
}
