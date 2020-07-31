package com.project.comic.storebook;

import lombok.Data;

@Data
public class StoreBookDTO {
	private String sbidx;
	private int sidx;
	private String isbn13;
	private String isbn10;
	private int idx;
	private String sdate;
	private String udate;
	private int point;
	private String category;
	private String status;
	
	public StoreBookDTO(){}
	
	public StoreBookDTO(String sbidx, int sidx, String isbn13, String isbn10, int idx, String sdate, String udate,
					 int point,String category, int total, int cnt, int losttot, String status){
		this.sbidx = sbidx;
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
	
	public String makeSbidx() {
		String made = String.format( "%03d", sidx ) + isbn13 + String.format( "%03d", idx );
		return made;
	}
	
	/*public String getSbidx() {
		String sidx = String.format("%03d", this.sidx);
		String isbn = this.isbn.substring(this.isbn.length() - 14, 13);
		String idx = String.format("%03d", this.idx);
		
		return sidx + isbn + idx;
	}*/
}
