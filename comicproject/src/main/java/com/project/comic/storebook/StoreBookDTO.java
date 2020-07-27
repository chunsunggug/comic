package com.project.comic.storebook;

import lombok.Data;

@Data
public class StoreBookDTO {
	private String sbidx;
	private int sidx;
	private String isbn;
	private int idx;
	private String sdate;
	private String lostdate;
	private int lostuidx;
	private String status;
	private int point;
	
	public StoreBookDTO(){}
	
	public StoreBookDTO(String sbidx, int sidx, String isbn, int idx, String sdate, String lostdate,
						int lostuidx, String status, int point){
		this.sbidx = sbidx;
		this.sidx = sidx;
		this.isbn = isbn;
		this.idx = idx;
		this.sdate = sdate;
		this.lostdate = lostdate;
		this.lostuidx = lostuidx;
		this.status = status;
		this.point = point;
	}
	
	/*public String getSbidx() {
		String sidx = String.format("%03d", this.sidx);
		String isbn = this.isbn.substring(this.isbn.length() - 14, 13);
		String idx = String.format("%03d", this.idx);
		
		return sidx + isbn + idx;
	}*/
}
