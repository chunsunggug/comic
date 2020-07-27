package com.project.comic.naver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.comic.NotSupportedQueryModel;
import com.project.comic.book.IPageSearch;


@Component
@Qualifier("naver")
public class NaverPageSearch implements IPageSearch {

	public static final String NAVER_REQUEST_URL 	= "https://openapi.naver.com/v1/search/book.json";

	private final String NAVER_CLIENT_ID 			= "2vkrQdzjGUHhZqo4ZZ7m";
	private final String NAVER_CLIENT_SECRET 		= "H_TiP4eqKG";

	
	/*
	 * query 	: 검색어				상세검색 시 생략가능
	 * display 	: 검색 결과 출력 건수 지정 	10(default), 1000(max)
	 * start 	: 검색 시작 위치			1(default), 1000(max)
	 * sort 	: 정렬옵션				sim(유사도 default), date(출간일), count(판매량)
	 * d_titl	: 책 제목 검색			상세 검색만 해당
	 * d_auth	: 저자명 검색			상세 검색만 해당
	 * d_cont	: 목차 검색			상세 검색만 해당
	 * d_isbn	: isbn 검색			상세 검색만 해당
	 * d_publ	: 출판사 검색			상세 검색만 해당
	 * d_dafr	: 출간 시작일			상세 검색만 해당
	 * d_dato	: 출간 종료일			상세 검색만 해당
	 * d_catg	: 책 검색 카테고리		상세 검색만 해당
	 */

	@Override
	public String getPageData(int pagenum, int pagesize, Object model) {
		/*if( object instanceof NaverQueryModel ){
			NaverQueryModel model = (NaverQueryModel)object;
			String request_url;
			URL url;
			HttpURLConnection con;

			try {
				
				request_url = NAVER_REQUEST_URL + "?" + model.toURLParam();
				System.out.println("request_url : " + request_url);
				
				url = new URL( request_url );					// throw
				con = (HttpURLConnection)url.openConnection();	// throw

				con.setRequestProperty( "X-Naver-Client-Id", NAVER_CLIENT_ID );
				con.setRequestProperty( "X-Naver-Client-Secret", NAVER_CLIENT_SECRET );

				int responseCode = con.getResponseCode();		// throw

				// 데이터를 성공적으로 가져오면 json으로 파싱해서 return
				if( responseCode == HttpURLConnection.HTTP_OK ) {
					BufferedReader br = new BufferedReader( new InputStreamReader( con.getInputStream(), "utf-8" ) ); // con throw
					StringBuffer sb = new StringBuffer();

					// 결과 문자열 변수에 저장
					while( br.ready() )  			// throw
						sb.append(br.readLine());	// throw

					String result = sb.toString();

					System.out.println( "naver get data : " + result );
					return result;

				}else {
					BufferedReader br = new BufferedReader( new InputStreamReader( con.getErrorStream(), "utf-8" ) ); // con throw
					StringBuffer sb = new StringBuffer();

					while( br.ready() )  			// throw
						sb.append(br.readLine());	// throw

					System.out.println("naver get data fail code : " + responseCode);
					System.out.println(sb);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		else 
			throw new NotSupportedQueryModel();*/
		return null;
		
	}

}
