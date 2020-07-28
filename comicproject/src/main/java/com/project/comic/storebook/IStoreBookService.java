package com.project.comic.storebook;

import java.util.List;

public interface IStoreBookService {
	public boolean add(Object object);							// 책 추가
	//public boolean update(Object object);						// 책 수정
	//public boolean remove(Object object);						// 책 삭제
	public Object loadBookDataByISBN(String isbn);				// 추가시 isbn 검색으로 책정보 표시
	//public Object getByPK(String pk);							// 관리키로 책정보 불러오기 (1개)
	public List getPageList(int cp, int listsize, int sidx);	// 페이지별로 해당점포 책 가져오기
	//public List getBooks(String sidx, String isbn);				// 점포의 해당 책들 데이터
	public int getBooksCountAll(int sidx);					// 점포의 모든 책종류 수량
	//public int getBookCount(String sidx, String isbn);			// 점포의 해당 책 수량
}
