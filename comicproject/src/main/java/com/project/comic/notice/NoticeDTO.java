package com.project.comic.notice;

import lombok.Data;

@Data
public class NoticeDTO {
	private int nidx;
	private int uidx;
	private String title;
	private String content;
	private String originimg;
	private String fakeimg;
	private String noname;
	private int nlike; 
	private int readnum;
	private int refe;
	private int lec;
	private String sdate;
	private String edate;
	
	
	@Override
	public String toString() {
		return "NoticeDTO [nidx=" + nidx + ", uidx=" + uidx + ", title=" + title + ", content=" + content + ", originimg="
				+ originimg + ", fakeimg=" + fakeimg+", noname="+noname + "]";
	}


	public NoticeDTO() {
		super();
	}


	public NoticeDTO(int uidx, String title, String content, String originimg, String fakeimg,String noname) {
		super();
		this.uidx = uidx;
		this.title = title;
		this.content = content;
		this.originimg = originimg;
		this.fakeimg = fakeimg;
		this.noname = noname;
	}
	
	
	
	
}
