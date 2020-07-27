package com.project.comic.naver;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import lombok.Data;

@Data
public class NaverQueryModel {
	
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
	
	public static final String SIM 		= "sim";
	public static final String DATE 	= "date";
	public static final String COUNT	= "count";
	public static final String[] detail_option = {	"d_titl", "d_auth", "d_cont", "d_isbn", 
													"d_publ", "d_dafr", "d_dato", "d_catg" };

	
	private String query;
	private int display 	= 10;
	private int start 		= 1; 
	private String sort		= SIM;
	private String detail;
	/*private String d_titl;
	private String d_auth;
	private String d_cont;
	private String d_isbn;
	private String d_publ;
	private String d_dafr;
	private String d_dato;
	private String d_catg;*/
	
	public String toURLParam() {
		String encoded_query = "";
		try {
			encoded_query = URLEncoder.encode(query, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url =	"query=" + encoded_query +
						"&display=" + display +
						"&start=" + start +
						"&sort=" + sort;
						/*+
						"&d_titl=" + d_titl +
						"&d_auth=" + d_auth +
						"&d_cont=" + d_cont +
						"&d_isbn=" + d_isbn +
						"&d_publ=" + d_publ +
						"&d_dafr=" + d_dafr +
						"&d_dato=" + d_dato +
						"&d_catg=" + d_catg;*/
		String det = "";
		
		for( int i=0; i < detail_option.length; i++ ) {
			if( detail.equals(detail_option[i]) )
				det = "&" + detail + "=" + encoded_query;
		}
		
		return url + det;
	}
	
	public void setByMap(Map map) {
		this.query = (String)map.get("query");
		this.sort = (String)map.get("sort");
		this.detail = (String)map.get("detail");

		if( map.get("display") != null )
			this.display = Integer.parseInt( (String)map.get("display") );
		if( map.get("start") != null )
			this.start = Integer.parseInt( (String)map.get("display") );
	}
}
