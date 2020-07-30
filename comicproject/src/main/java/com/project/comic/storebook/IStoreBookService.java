package com.project.comic.storebook;

import java.util.List;

public interface IStoreBookService {
	public boolean add(Object object);							// 책 추가
	public boolean add(Object object, int count);				// 책 여러권 추가						
	public boolean update(Object object);						// 책 수정
	public boolean delete(String sbidx);						// 기본키로 책 삭제
	public Object loadBookDataFromSearchServer(String isbn);	// 추가시 검색서버로 isbn 검색으로 책정보 표시
	public Object loadBookDataForModal(String isbn);			// 수정/삭제 모달에 보여줄 데이터
	//public Object getByPK(String pk);							// 관리키로 책정보 불러오기 (1개)
	public List getPageList(int cp, int listsize, int sidx);	// 페이지별로 해당점포 책 가져오기
	public int getBooksCountAll(int sidx);						// 점포의 모든 책종류 수량
	//public int getBookCount(String sidx, String isbn);		// 점포의 해당 책 수량
}
