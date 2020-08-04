package com.project.comic.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comic.storebook.IStoreBookDao;
import com.project.comic.storebook.IStoreBookService;
import com.project.comic.storebook.StoreBookDTO;
import com.project.comic.user.UserDao;

@Service
public class BookService {

	@Autowired
	IStoreBookService storeBookService;
	
	@Autowired
	IStoreBookDao storeBookDaoImpl;
	
	@Autowired
	UserDao userDaoImpl;
	
	// 순서
	// 페이지 링크에 isbn넣어줘서 연결
	// 점포별 가능여부 보여줄 항목 점포이름, 대여요금, 책상태, 책보러가기 버튼
	// 상세페이지 보여줄 것 타이틀, 섬네일, 대여요금, 저자, 번역, 도서 소개, isbn
	// storebook에서 가져와야 할 것 대여가능상태, 요금, isbn
	
	public List getLinkDetailTabList(String isbn, int itemsize) {
		// 책을 클릭하고 isbn이 넘겨졌다
		// isbn을 가져와 현재 위치에서 가까운 점포 각각에 대하여(미구현)
		// sidx와 isbn으로 책 대여 상태 여부를 10개 목록화 한다
		
		List list = new ArrayList<LinkDetailTabItemVO>();
		
		for(int i=0; i < itemsize; i++)
			list.add( getLinkDetailTabItemVO( 1, isbn ) );
		
		return list;
	}
	
	public LinkDetailTabItemVO getLinkDetailTabItemVO(int sidx, String isbn) {
		LinkDetailTabItemVO vo = null;
		
		// 점포에 빌릴 수 있는 책 있는지 검사
		int result = storeBookDaoImpl.canBorrow(sidx, isbn);
		
		if( result == 0 )
			return null;
		
		// sidx로 점포 이름 가져오기 (미구현으로 생략)
		// isbn으로 StoreBook에서 검색해서 VO 만들기
		List<StoreBookDTO> dtos = storeBookDaoImpl.getBorrowableBooks(sidx, isbn);
		
		if( dtos.size() >= 1 ) {
			int minimum = ((StoreBookDTO)dtos.get(0)).getPoint();
			for( int i=1; i < dtos.size(); i++ )
				if( dtos.get(i).getPoint() < minimum )
					minimum = dtos.get(i).getPoint();
			
			vo = new LinkDetailTabItemVO("name", minimum, "S", sidx, isbn);
		}
		
		return vo;
	}
}
