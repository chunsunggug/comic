package com.project.comic.book;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.comic.storebook.StoreBookDTO;

import lombok.Data;

@Data
public class BookGroupVO {
	private String category;		// 카테고리
	private String title;			// 책 제목
	private int point;				// 책 대여료
	private String status;			// 책 상태
	private int sidx;				// 점포 번호
	private String name;			// 가게 이름
	private String isbn13;			// isbn13
	private String isbn10;			// isbn10
	private String contents;		// 책소개 내용
	private String url;				// 책 상세페이지 url
	private String datetime;		// 출판날짜
	private String[] authors;		// 저자
	private String publisher;		// 출판사
	private String[] translators;	// 번역자
	private String thumbnail;		// 표지 그림
	private int total;				// 전체 책 수량(등록된 모든 수량)
	private int operating;			// 총수량(대여점에 실제로 운영중인 수량)
	private int lost;				// 분실 수량
	private int valuable;			// 현재 대여 가능수량(운영가능 책 중 대여점에서 대여를 대기중인 수량)
	private int borrowed;			// 대여중 수량
	
	public void setStoreBookDTOList(List<StoreBookDTO> dtos) {
		StoreBookDTO dto = dtos.get(0);
		
		category = dto.getCategory();
		isbn10 = dto.getIsbn10();
		isbn13 = dto.getIsbn13();
		point = dto.getPoint();
		sidx = dto.getSidx();
		name = dto.getName();
		total = dtos.size();
		
		operating = 0;
		lost = 0;
		borrowed = 0;
		
		for(StoreBookDTO tmp : dtos) {
			if( !tmp.getStatus().equals("L") ) operating++;	// 분실되지 않고 운영중이라면 +1
			else	lost++;									// 분실이라면 분실 +1
			
			if( !(tmp.getStatus().equals("S") || 
				tmp.getStatus().equals("L")) ) borrowed++;	// 대여중이라면 +1
		}
		
		valuable = operating - borrowed;	// 실제 운영중인 책 - 대여중인 책 = 대여가능 수량
		status = valuable > 0 ? "S" : "N";	// 1권이라도 있으면 입고상태, 없으면 N으로 없음 표시
		
		String isbn = isbn13 != null ? isbn13 : isbn10;
		url = "/comic/bookdetail.do?sidx=" + sidx + "&isbn=" + isbn;

	}
	
	public void setKakaoDocuments(JSONObject obj) {
		title = (String)obj.get("title");
		contents = (String)obj.get("contents");
		datetime = (String)obj.get("datetime");
		publisher = (String)obj.get("publisher");
		thumbnail = (String)obj.get("thumbnail");
		
		JSONArray json_authors = (JSONArray)obj.get("authors");
		JSONArray json_translators = (JSONArray)obj.get("translators");
		
		authors = new String[json_authors.size()];
		translators = new String[json_translators.size()];
		
		for(int i=0; i < json_authors.size(); i++)
			authors[i] = (String)json_authors.get(i);
		
		for(int j=0; j < json_translators.size(); j++)
			translators[j] = (String)json_translators.get(j);
	}

	
}
