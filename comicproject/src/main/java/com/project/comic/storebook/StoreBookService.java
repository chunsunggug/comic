package com.project.comic.storebook;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.comic.NotSupportedClass;
import com.project.comic.Utility;
import com.project.comic.book.BookGroupVO;
import com.project.comic.book.BookVO;
import com.project.comic.book.ISequenceSearch;
import com.project.comic.book.NoExistBook;
import com.project.comic.book.kakao.KakaoQueryModel;

@Service
public class StoreBookService implements IStoreBookService{

	@Autowired
	@Qualifier("impl")
	private IStoreBookDao storeBookDao;

	@Autowired
	private ISequenceSearch kakaoBookSequenceSearch;
	
	@Transactional
	@Override
	public int add(Object object) {
		if( !(object instanceof StoreBookDTO) )
			throw new NotSupportedClass();

		// 전체적인 순서
		// 가져온 파라미터 dto로 형변환
		// 가져온 isbn으로 카카오를 통해 책 데이터 가져오기
		// 제대로된 isbn으로 검색이 됐거나 책이 없는지 카카오 검색결과 1인지 검사
		// 통과되면 DB에 몇개있는지 검사
		// 검사결과 수 +1로 dto 완성해서 추가
		
		// dto에 isbn이 제대로 들어있는지 검증
		StoreBookDTO dto = (StoreBookDTO)object;
		KakaoQueryModel model = new KakaoQueryModel();
		model.setPage(0);
		model.setTarget("isbn");
		
		// 카카오에서 isbn으로 검색결과 가져오기
		if( dto.getIsbn10() != null ) 
			model.setQuery( dto.getIsbn10() );
		else
			model.setQuery( dto.getIsbn13() );

		String kakao_result = (String)kakaoBookSequenceSearch.nextSearch(model);
		JSONObject json_kakao = (JSONObject)Utility.JSONParse(kakao_result);
		JSONArray json_documents = (JSONArray)json_kakao.get("documents");
		JSONObject json_book = (JSONObject)json_documents.get(0);
		JSONObject meta = (JSONObject)json_kakao.get("meta");
		
		// 제대로 된 ISBN으로 검색했거나 책이 없는지 검사
		if( (long)meta.get("total_count") == 0 ) {
			System.out.println("ISBN이 잘못입력되었거나 책이 없습니다");
			throw new NoExistBook();
		}
		// 검증 완료
		
		// 카카오에서 ISBN13과 ISBN10 가져오기
		String[] split = ((String)json_book.get("isbn")).split(" ");
		dto.setIsbn10(split[0]);
		dto.setIsbn13(split[1]);
		
		// 책 개수 가져오기
		int index = storeBookDao.getBookCount(dto.getSidx(), split[1]);
		
		if( index != 0 )
			index = storeBookDao.getBookMaximumIdx( dto.getSidx(), split[1] );
		
		dto.setIdx( index + 1 );
		
		int result = storeBookDao.add( dto );
		System.out.println("책 추가 로우 : " + result);

		return result;
	}
	
	@Transactional
	@Override
	public int add(Object object, int count) {
		if( !(object instanceof StoreBookDTO) )
			throw new NotSupportedClass();
		
		int result = 0;
		
		for( int i=0; i < count; i++ )
			result += add( object );
		
		return result;
	}
	
	@Transactional
	@Override
	public List<StoreBookDTO> getPageList(int cp, int listsize, int sidx) {
		List<StoreBookDTO> storebook_result = storeBookDao.getPageList( cp, listsize, sidx );
		return storebook_result;
	}
	
	@Override
	public int getBooksCountAll(int sidx) {
		return storeBookDao.getBooksCountAll(sidx);
	}

	@Transactional
	@Override
	public int update(Object object) {
		if( !(object instanceof StoreBookDTO) )
			throw new NotSupportedClass();
		
		// update의 경우 홈페이지에 기본키를 보여주고 있기때문에 dto에 기본키가 있다 그러므로 기본키로 검색한다
		// 받은 변수를 dto로 형변환 후 기본키로 점포에 저장이 되어있는지 검사
		// 있으면 데이터 가져와서 수정할 데이터(point,category,status) set해준 후 수정
		
		StoreBookDTO dto = (StoreBookDTO)object;
		
		// 책이 있는지 검사
		StoreBookDTO update_dto = storeBookDao.getBook( dto.getSbidx() );
		
		if( update_dto == null ) return 0;
		
		// 수정할 항목만 수정
		update_dto.setCategory( dto.getCategory() );
		update_dto.setPoint( dto.getPoint() );
		update_dto.setStatus( dto.getStatus() );
		
		int result = storeBookDao.updateBook( update_dto );
		
		System.out.println("수정 된 로우 : " + result);
		
		return result;
	}
	
	// 기본키로 삭제
	@Override
	public int delete(int sbidx) {
		int result = storeBookDao.deleteBook(sbidx);
		System.out.println("result : " + result);
		
		return result;
	}

	@Override
	public List<StoreBookDTO> getBooksByIsbn(int sidx, String isbn) {
		int count = storeBookDao.getBookCount(sidx, isbn);
		
		if( count != 0 )
			return storeBookDao.getBooksByIsbn(sidx, isbn);
		
		return null;
	}

	@Override
	public Object getBookByPk(int sbidx) {
		StoreBookDTO dto = storeBookDao.getBook(sbidx);
		return dto;
	}

	@Override
	public int update(int sidx, String isbn, int point, String category) {
		return storeBookDao.updateAllBook(sidx, isbn, point, category);
	}

	@Override
	public int update(int sbidx, String status) {
		return storeBookDao.updateBook(sbidx, status);
	}

	@Override
	public Object getBookVO(int sbidx) {
		StoreBookDTO dto = (StoreBookDTO)getBookByPk(sbidx);
		BookVO vo = new BookVO();
		
		if( dto != null ) {
			vo.setStoreBookDTO(dto);
			KakaoQueryModel md = new KakaoQueryModel();
			md.setPage(0);
			md.setTarget("isbn");
			
			if( dto.getIsbn13() != null )
				md.setQuery(dto.getIsbn13());
			else
				md.setQuery(dto.getIsbn10());
			
			String result = (String)kakaoBookSequenceSearch.nextSearch(md);
			JSONObject json_result = (JSONObject)Utility.JSONParse(result);
			JSONArray json_documents = (JSONArray)json_result.get("documents");
			JSONObject json_book = (JSONObject)json_documents.get(0);
			
			vo.setKakaoDocuments(json_book);
			
			return vo;
		}
		
		return null;
	}

	@Override
	public Object getBookGroupVO(int sidx, String isbn) {
		List<StoreBookDTO> dtolist = getBooksByIsbn(sidx, isbn);
		BookGroupVO vo = new BookGroupVO();
		
		if( dtolist != null) {
			vo.setStoreBookDTOList(dtolist);
			StoreBookDTO dto = dtolist.get(0);
			KakaoQueryModel md = new KakaoQueryModel();
			md.setPage(0);
			md.setTarget("isbn");
			
			if( dto.getIsbn13() != null )
				md.setQuery(dto.getIsbn13());
			else
				md.setQuery(dto.getIsbn10());
			
			String result = (String)kakaoBookSequenceSearch.nextSearch(md);
			JSONObject json_result = (JSONObject)Utility.JSONParse(result);
			JSONArray json_documents = (JSONArray)json_result.get("documents");
			JSONObject json_book = (JSONObject)json_documents.get(0);
			
			vo.setKakaoDocuments(json_book);
			
			return vo;
		}
		
		return null;
	}
}
