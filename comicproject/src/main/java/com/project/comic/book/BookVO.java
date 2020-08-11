package com.project.comic.book;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.comic.storebook.StoreBookDTO;

import lombok.Data;

@Data
public class BookVO {
	private int sbidx;			// storebook 테이블 기본키
	private int idx;				// 책 식별
	private String udate;			// 업뎃 날짜
	private String category;		// 카테고리
	private String title;			// 책 제목
	private int point;				// 책 대여료
	private String status;			// 책 상태
	private int sidx;				// 점포 번호
	private String name;			// 점포 이름
	private String isbn13;			// isbn13
	private String isbn10;			// isbn10
	private String contents;		// 책소개 내용
	private String datetime;		// 출판날짜
	private String[] authors;		// 저자
	private String publisher;		// 출판사
	private String[] translators;	// 번역자
	private String thumbnail;		// 표지 그림
	private String sdate;			// 점포에서 책 등록날짜
	
	public void setStoreBookDTO(StoreBookDTO dto) {
		category = dto.getCategory();
		isbn10 = dto.getIsbn10();
		isbn13 = dto.getIsbn13();
		point = dto.getPoint();
		idx = dto.getIdx();
		sbidx = dto.getSbidx();
		sdate = dto.getSdate();
		sidx = dto.getSidx();
		status = dto.getStatus();
		udate = dto.getUdate();
		name = dto.getName();
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
