package com.project.comic.kakao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.project.comic.NotSupportedQueryModel;
import com.project.comic.book.IBookConnector;

public class KakaoBookConnector implements IBookConnector{

	private final String KAKAO_BOOK_SEARCH_URL = "https://dapi.kakao.com/v3/search/book";
	private final String KAKAO_ID = "118237743806f276d679025f706c0e3c";

	private KakaoQueryModel model;

	@Override
	public IBookConnector setModel(Object object) {
		if(object instanceof KakaoQueryModel) {
			model = (KakaoQueryModel)object;

			return this;
		}else
			throw new NotSupportedQueryModel();
	}

	@Override
	public String connect() {
		URL url;
		HttpURLConnection con;
		String request_url = KAKAO_BOOK_SEARCH_URL + "?" + model.toURLParam();

		System.out.println(request_url);
		
		try {
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

				String result = sb.toString();	// throw

				System.out.println("kakao get data success");

				return result;
			}else {
				BufferedReader br = new BufferedReader( new InputStreamReader( con.getErrorStream(), "utf-8" ) ); // con throw
				StringBuffer sb = new StringBuffer();

				while( br.ready() )  			// throw
					sb.append(br.readLine());	// throw

				System.out.println("kakao get data fail code : " + responseCode);
				System.out.println(sb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
