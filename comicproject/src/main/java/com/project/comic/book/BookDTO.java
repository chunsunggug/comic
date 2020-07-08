package com.project.comic.book;

import java.util.Date;

import lombok.Data;

@Data
public class BookDTO {
	private int bidx;
	private String name;			// 책이름
	private String contents;		// 도서소개
	private String url;				// 도서상세 url
	private String isbn;			// 국제표준 ISBN
	private Date datetime;			// 출판날짜
	private String authors;			// 저자리스트
	private String publisher;		// 출판사
	private String[] translators;	// 번역자
	private int price;				// 도서정가
	private int sale_price;			// 도서판매가
	private String thumbnail;		// 도서표지 미리보기 url
	private String status;			// 도서 판매 상태 정보
	
	public BookDTO(int bidx,String name, String contents, String url, String isbn, Date datetime,
			String authors, String publisher, String[] translators, int price, int sale_price,
			String thumbnail, String status) {
		this.bidx = bidx;
		this.name = name;
		this.contents = contents;
		this.url = url;
		this.isbn = isbn;
		this.datetime = datetime;
		this.authors = authors;
		this.publisher = publisher;
		this.translators = translators;
		this.price = price;
		this.sale_price = sale_price;
		this.thumbnail = thumbnail;
		this.status = status;
	}
}
