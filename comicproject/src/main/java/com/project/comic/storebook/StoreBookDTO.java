package com.project.comic.storebook;

import lombok.Data;

@Data
public class StoreBookDTO {
	private int sbidx;
	private int sidx;
	private String isbn13;
	private String isbn10;
	private String sdate;
	private int point;
	private String category;
	private int total;
	private int cnt;
	private int losttot;
	
	public StoreBookDTO(){}
	
	public StoreBookDTO(int sbidx, int sidx, String isbn13, String isbn10, String sdate,
					 int point,String category, int total, int cnt, int losttot){
		this.sbidx = sbidx;
		this.sidx = sidx;
		this.isbn13 = isbn13;
		this.isbn10 = isbn10;
		this.sdate = sdate;
		this.point = point;
		this.category = category;
		this.total = total;
		this.cnt = cnt;
		this.losttot = losttot;
	}
	
	/*public String getSbidx() {
		String sidx = String.format("%03d", this.sidx);
		String isbn = this.isbn.substring(this.isbn.length() - 14, 13);
		String idx = String.format("%03d", this.idx);
		
		return sidx + isbn + idx;
	}*/
}
