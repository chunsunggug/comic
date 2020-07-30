package com.project.comic.storebook;

import lombok.Getter;

// 점포관리 페이지에서 보여줄 책들의 데이터를 저장한 VO
@Getter
public class StoreBookVO {
	private String sbidx;
	private String thumbnail;
	private String title;
	private String authors;
	private String category;
	private int point;
	private String sdate;
	
	public StoreBookVO(String sbidx, String thumbnail, String title, String authors, 
			String category, int point, String sdate) {
		this.sbidx = sbidx;
		this.thumbnail = thumbnail;
		this.title = title;
		this.authors = authors;
		this.category = category;
		this.point = point;
		this.sdate = sdate;
	}
}
