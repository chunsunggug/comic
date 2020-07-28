package com.project.comic.book.seoji;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.project.comic.NotSupportedClass;
import com.project.comic.book.IBookConnector;

public class SeojiBookConnector implements IBookConnector {

	private SeojiQueryModel model;
	
	private final String url = "http://seoji.nl.go.kr/landingPage/SearchApi.do";

	@Override
	public IBookConnector setModel( Object object ) {
		if( object instanceof SeojiQueryModel ) {
			model = (SeojiQueryModel)object;

			model.setCert_key( "ed29e3ee5a506cb9ea3e4bd65aebb8070178a3e0d0e4b8d450df9b36f01c54e2" );
			
			return this;
		}
		else
			throw new NotSupportedClass();
	}

	@Override
	public String connect() {
		URL url;
		HttpURLConnection con;
		String request_url = this.url + "?" + model.toURLParam();
		String result;
		System.out.println(request_url);
		try {
			url = new URL( request_url );					// throw
			con = (HttpURLConnection)url.openConnection();	// throw

			int responseCode = con.getResponseCode();		// throw

			// 데이터를 성공적으로 가져오면 json으로 파싱해서 return
			if( responseCode == HttpURLConnection.HTTP_OK ) {
				BufferedReader br = new BufferedReader( new InputStreamReader( con.getInputStream(), "utf-8" ) ); // con throw
				StringBuffer sb = new StringBuffer();

				// 결과 문자열 변수에 저장
				while( br.ready() )  			// throw
					sb.append(br.readLine());	// throw

				result = sb.toString();

				System.out.println( "seoji get data : " + result );

				return result;

			}else {
				BufferedReader br = new BufferedReader( new InputStreamReader( con.getErrorStream(), "utf-8" ) ); // con throw
				StringBuffer sb = new StringBuffer();

				while( br.ready() )  			// throw
					sb.append(br.readLine());	// throw

				System.out.println("seoji get data fail code : " + responseCode);
				System.out.println(sb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
