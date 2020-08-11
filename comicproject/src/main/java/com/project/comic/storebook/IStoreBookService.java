package com.project.comic.storebook;

import java.util.List;

public interface IStoreBookService {
	public int add(Object object);								// 책 추가
	public int add(Object object, int count);					// 책 여러권 추가						
	public int update(Object object);							// 책 수정
	public int update(int sidx, String isbn,
			int point, String category); 						// 똑같은 책 대여료 카테고리 수정
	public int update(int pk, String status);					// 책 상태 변경
	public int delete(int sbidx);								// 기본키로 책 삭제
	public List getPageList(int cp, int listsize, int sidx);	// 페이지별로 해당점포 책 가져오기
	public int getBooksCountAll(int sidx);						// 점포의 모든 책종류 수량
	public List getBooksByIsbn(int sidx, String isbn);			// 점포의 해당 책들만 가져오기
	public Object getBookByPk(int sbidx);						// 기본키로 책 가져오기
	public Object getBookVO(int sbidx);							// 책 하나 VO로 만들어서 가져오기
	public Object getBookGroupVO(int sidx, String isbn);		// 묶음책 VO로 만들어서 가져오기
}
