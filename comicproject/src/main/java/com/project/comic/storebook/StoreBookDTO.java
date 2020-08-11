package com.project.comic.storebook;

import lombok.Data;

@Data
public class StoreBookDTO {
	private int sbidx;
	private int sidx;
	private String isbn13;
	private String isbn10;
	private int idx;
	private String sdate;
	private String udate;
	private int point;
	private String category;
	private String status;
	private String name;
	
	public StoreBookDTO(){}
	
	public StoreBookDTO(int sbidx, int sidx, String isbn13, String isbn10, int idx, String sdate, String udate,
					 int point,String category, int total, int cnt, int losttot, String status, String name){
		this.sbidx = sbidx;
		this.name = name;
		this.sidx = sidx;
		this.isbn13 = isbn13;
		this.isbn10 = isbn10;
		this.sdate = sdate;
		this.udate = udate;
		this.point = point;
		this.category = category;
		this.idx = idx;
		this.status = status;
	}
	
}
