package com.project.comic.kakao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import com.project.comic.book.AbstractQueryModel;

import lombok.Data;

@Data
public class KakaoQueryModel extends AbstractQueryModel {

	private String query = "";
	private String sort;
	private int page= 1;
	private int size = 12;
	private String target;
	
	public String toURLParam() {
		String param = "query=" + query + "&size=" + size +
				"&page=" + page;
		
		if( sort != null ) param += "&sort=" + sort; 
		if( target != null ) param += "&target=" + target;
		
		return param;
	}

	@Override
	public void setMapParam(Map map_param) {
		if( map_param.containsKey("query") )	query = (String)map_param.get("query");
		if( map_param.containsKey("sort") )		sort = (String)map_param.get("sort");
		if( map_param.containsKey("page") )		page = (int)map_param.get("page");
		if( map_param.containsKey("size") )		size = (int)map_param.get("size");
		if( map_param.containsKey("target") )	target = (String)map_param.get("target");
		
		try {
			if( query != null )
				query = (String)URLEncoder.encode( query.replaceAll(" ", "+"), "utf-8" );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
