package com.project.comic.book.seoji;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import com.project.comic.book.AbstractQueryModel;

import lombok.Data;

@Data
public class SeojiQueryModel extends AbstractQueryModel {

	private String cert_key;			// 인증키	(required)
	private String result_style = "json";// json,xml (required)
	private int page_no = 1;			// 현재 쪽번호(페이지 1부터 시작) (required)
	private int page_size = 12;			// 쪽당 출력건수 (required)
	private String isbn;				// ISBN
	//private String set_isbn;			// SET ISBN
	//private String ebook_yn;			// 전자책여부 Y,N
	private String title;				// 본표제
	//private String start_publish_date;	// 발행예정일 시작(8자리, yyyymmdd)
	//private String end_publish_date;	// 발행예정일 끝(8자리, yyyymmdd)
	//private String cip_yn;				// CIP 신청여부 Y,N
	//private String deposit_yn;			// 납본유무 Y, N
	//private String series_title;		// 총서명
	private String publisher;			// 발행처명
	private String author;				// 저자
	private String order_by;			// 정렬방식 ASC , DESC
	private String sort;				// 정렬 PUBLISH_PREDATE, INPUT_DATE,
										// INDEX_TITLE, INDEX_PUBLISHER
	
	// html에서 target 속성으로 isbn, title, publisher, author를 묶고
	// order_by 와 sort는 옵션을 따로두고 그안에 각각의 값으로 나눈다
	
	public String toURLParam() {
		String url = "cert_key=" + cert_key +
					 "&result_style=" + result_style +
					 "&page_no=" + page_no +
					 "&page_size=" + page_size;
					 
		url += isbn != null ? "&isbn=" + isbn : "";
		url += title != null ? "&title=" + title : "";
		url += publisher != null ? "&publisher=" + publisher : "";
		url += author != null ? "&author=" + author : "";
		url += order_by != null ? "&order_by=" + order_by : "";
		url += sort != null ? "&sort=" + sort : "";
		
		return url;
	}
	
	public void setMapParam( Map map_param ) {
		String target = (String)map_param.get("target");
		String order = (String)map_param.get("order_by");
		String sort = (String)map_param.get("sort");
		String query = (String)map_param.get("query");
		
		try {
			query = (String)URLEncoder.encode( query.replaceAll(" ", "+"), "utf-8" );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if( map_param.containsKey("target") ) {
			if( target.equals("title") ) title = query;
			else if( target.equals("isbn") ) isbn = query;
			else if( target.equals("publisher") ) publisher = query;
			else if( target.equals("author") ) author = query;
		}
		
		if( map_param.containsKey("order_by") ) {
			order_by = order; 
		}
		
		if( map_param.containsKey("sort") )
			this.sort = sort;
	}
}
