package com.project.comic.notice;

import lombok.Data;

@Data
public class NoticeDTO {
	private int nidx;
	private int uidx;
	private String title;
	private String content;
	private String img1;
	private String img2;
	private int nlike; 
	private int readnum;
	private int refe;
	private int lec;
	private String sdate;
	private String edate;
	
	
	@Override
	public String toString() {
		return "NoticeDTO [nidx=" + nidx + ", uidx=" + uidx + ", title=" + title + ", content=" + content + ", img1="
				+ img1 + ", img2=" + img2 + "]";
	}


	public NoticeDTO() {
		super();
	}


	public NoticeDTO(int uidx, String title, String content, String img1, String img2) {
		super();
		this.uidx = uidx;
		this.title = title;
		this.content = content;
		this.img1 = img1;
		this.img2 = img2;
	}
	
	
	
	
}
