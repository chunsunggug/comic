package com.project.comic.storebook;

import lombok.Data;

@Data
public class StoreBookKakaoDTO {
	private String sbidx;
	private String thumbnail;
	private String title;
	private String authors;
	private String genre;
	private int point;
	private String sdate;
	private String status;
	
	public StoreBookKakaoDTO() {}
	public StoreBookKakaoDTO(String sbidx, String thumbnail, String title, String authors, String genre,
							int point, String sdate, String status) {
		this.sbidx=sbidx;
		this.thumbnail=thumbnail;
		this.title=title;
		this.authors=authors;
		this.genre=genre;
		this.point=point;
		this.sdate=sdate;
		this.status=status;
	}
}
