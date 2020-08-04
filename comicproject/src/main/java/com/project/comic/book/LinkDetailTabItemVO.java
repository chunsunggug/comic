package com.project.comic.book;

import lombok.Getter;

@Getter
public class LinkDetailTabItemVO {
	private String name;
	private int point;
	private String status;
	private int sidx;
	private String isbn;
	
	public LinkDetailTabItemVO(String name, int point, String status, int sidx,
			String isbn ) {
		this.name = name;
		this.point = point;
		this.status = status;
		this.sidx = sidx;
		this.isbn = isbn;
	}
}
