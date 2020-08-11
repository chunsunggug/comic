package com.project.comic.storebook;

import java.util.List;
import java.util.Map;

public interface IStoreBookDao {
	public int add(StoreBookDTO object);						// dto로 추가
	public List getPageList(int cp, int listsize, int sidx);	// 페이지 리스트 가져오기
	public int getBooksCountAll(int sidx);						// 점포 모든 책 수량
	public int getBookCount(int sidx, String isbn);				// 점포 해당책 수량
	public List getBorrowableBooks(int sidx, String isbn);		// 빌릴 수 있는 책들 가져오기
	public List getBooksByIsbn(int sidx, String isbn);			// isbn으로 책검색 가져오기
	public StoreBookDTO getBook(int sbidx);					// 관리번호 책 데이터 가져오기
	public int updateBook(StoreBookDTO dto);					// dto로 책 정보 수정
	public int updateAllBook(int sidx, String isbn,
			int point, String category); 						// 일괄수정
	public int updateBook(int sbidx, String status);			// 상태 바꾸기
	public int deleteBook(int sbidx);							// 책 지우기
	public int getBookMaximumIdx(int sidx, String isbn);		// 점포 해당 책 인덱스 최대수
	public int canBorrow(int sidx, String isbn);				// 점포에서 책 빌릴 수 있는지 검사
	public int existStoreHasBook(String isbn);					// 책을 가지고있는 점포가 있는지 검사
	public List getStoreHasBook(String isbn);					// 책 등록되어있는 점포들 점포번호 가져오기
}
