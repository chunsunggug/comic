package com.project.comic.storebook;

import java.util.List;
import java.util.Map;

public interface IStoreBookDao {
	public int add(StoreBookDTO object);						// dto로 추가
	public List getPageList(int cp, int listsize, int sidx);	// 페이지 리스트 가져오기
	public int getBooksCountAll(int sidx);						// 점포 모든 책 수량
	public int getBookCount(int sidx, String isbn);				// 점포 해당책 수량
	public StoreBookDTO getBook(String sbidx);					// 관리번호 책 데이터 가져오기
	public int updateBook(StoreBookDTO dto);					// dto로 책 정보 수정
	public int deleteBook(String sbidx);						// 책 지우기
	public int getBookMaximumIdx(int sidx, String isbn);		// 점포 해당 책 인덱스 최대수
}
