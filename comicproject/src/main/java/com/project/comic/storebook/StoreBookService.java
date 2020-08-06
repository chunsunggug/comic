package com.project.comic.storebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.comic.NotSupportedClass;
import com.project.comic.Utility;
import com.project.comic.book.AllInOneBookVO;
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
	public boolean add(Object object) {
		if( !(object instanceof StoreBookDTO) )
			throw new NotSupportedClass();

		// 전체적인 순서
		// 가져온 파라미터 dto로 형변환
		// 가져온 isbn으로 카카오를 통해 책 데이터 가져오기
		// 제대로된 isbn으로 검색이 됐거나 책이 없는지 카카오 검색결과 1인지 검사
		// 통과되면 DB에 몇개있는지 검사
		// 검사결과 수 +1로 dto 완성해서 추가
		
		// dto 에 isbn이 제대로 들어있는지 검증
		StoreBookDTO dto = (StoreBookDTO)object;
		JSONObject val_result = null;
		
		// 카카오에서 isbn으로 검색결과 가져오기
		if( dto.getIsbn10() != null ) 
			val_result = (JSONObject)Utility.JSONParse( (String)loadBookDataFromSearchServer(dto.getIsbn10()) );
		else if( dto.getIsbn13() != null ) 
			val_result = (JSONObject)Utility.JSONParse( (String)loadBookDataFromSearchServer(dto.getIsbn13()) );
		
		JSONObject meta = (JSONObject)val_result.get("meta");
		JSONArray result_documents = (JSONArray)val_result.get("documents");
		
		// 제대로 된 ISBN으로 검색했거나 책이 없는지 검사
		if( (long)meta.get("total_count") != 1 ) {
			System.out.println("ISBN이 잘못입력되었거나 책이 없습니다");
			throw new NoExistBook();
		}
		JSONObject result_book = (JSONObject)result_documents.get(0);
		// 검증 완료
		
		// 카카오에서 ISBN13과 ISBN10 가져오기
		String isbn13, isbn10;
		isbn10 = ((String)result_book.get("isbn")).substring(0,10);
		isbn13 = ((String)result_book.get("isbn")).substring(11, 24);
		dto.setIsbn10(isbn10);
		dto.setIsbn13(isbn13);
		
		// 책 개수 가져오기
		int count = storeBookDao.getBookCount(dto.getSidx(), isbn13);
		if( count != 0 )
			count = storeBookDao.getBookMaximumIdx( dto.getSidx(), isbn13 );
		dto.setIdx( count + 1 );
		dto.setSbidx( dto.makeSbidx() );
		
		int result = storeBookDao.add(dto);
		System.out.println("책 추가 로우 : " + result);

		if( result == 1 )
			return true;
		
		throw new FailedAddStoreBook();
	}
	
	@Transactional
	@Override
	public boolean add(Object object, int count) {
		if( !(object instanceof StoreBookDTO) )
			throw new NotSupportedClass();
		
		for( int i=0; i < count; i++ )
			add( object );
		
		return true;
	}
	
	@Override
	public Object loadBookDataFromSearchServer(String isbn) {
		KakaoQueryModel md = new KakaoQueryModel();
		md.setQuery(isbn);
		md.setPage(0);
		String result = (String)kakaoBookSequenceSearch.nextSearch(md);		
		
		System.out.println(result);
		return result;
	}
	
	@Override
	public Object loadBookDataForModal(String pk) {
		String isbn = pk.substring(3, 16);
		String result = (String)loadBookDataFromSearchServer(isbn);
		StoreBookDTO dto = storeBookDao.getBook( pk );
		
		JSONObject json_result = (JSONObject)Utility.JSONParse(result);
		json_result.put( "category", dto.getCategory() );
		json_result.put( "point", dto.getPoint() );
		json_result.put( "status", dto.getStatus() );
		
		AllInOneBookVO vo = new AllInOneBookVO();
		JSONObject doc = (JSONObject)Utility.JSONParse(
				( (JSONObject)
						( (JSONArray)json_result.get("documents") ).get(0) )
				.toJSONString()
				);
		vo.setKakaoDocuments(doc);
		vo.setStoreBookDTO(dto);
		
		return vo;
	}

	@Transactional
	@Override
	public List getManagePageList(int cp, int listsize, int sidx) {
		// 순서
		// 점포관리에서 보여줄 페이지당 list를 StoreBookDTO로 가져온다
		// 가져온 DTO에서 ISBN13을 카카오검색을 이용한 조인으로 뷰에서 보여줄 데이터VO 리스트로 만들어준다
		// 리스트 결과를 return한다
		
		// StoreBookDTO로 DB에서 가져오는 과정
		List<StoreBookDTO> storebook_result = storeBookDao.getPageList( cp, listsize, sidx );
		List list_result = new ArrayList<StoreBookManagePageVO>();
		
		// 가져온 DTO를 이용하여 카카오 검색을 통해 VO로 수정
		for( StoreBookDTO dto : storebook_result ) {
			KakaoQueryModel model = new KakaoQueryModel();
			model.setPage( 0 );
			model.setQuery( dto.getIsbn13() );
			model.setTarget( "isbn" );
			
			String result = (String)kakaoBookSequenceSearch.nextSearch( model );
			JSONObject json_result = (JSONObject)Utility.JSONParse(result);
			JSONArray documents = (JSONArray)json_result.get("documents");
			JSONObject json_book = (JSONObject)documents.get(0);
			JSONArray json_authors = (JSONArray)json_book.get("authors");
			String authors = "";
			
			for( int i=0; i < json_authors.size(); i++ )
				if(i != json_authors.size() - 1 )
					authors += (String)json_authors.get(i) + ",";
				else
					authors += (String)json_authors.get(i);
			
			StoreBookManagePageVO vo = new StoreBookManagePageVO( dto.getSbidx(), (String)json_book.get("thumbnail"),
					(String)json_book.get("title"), authors, dto.getCategory(), 
					dto.getPoint(), dto.getSdate());
			
			list_result.add(vo);
		}
		
		return list_result;
	}
	
	@Override
	public int getBooksCountAll(int sidx) {
		return storeBookDao.getBooksCountAll(sidx);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		if( !(object instanceof StoreBookDTO) )
			throw new NotSupportedClass();
		
		// update의 경우 홈페이지에 기본키를 보여주고 있기때문에 dto에 기본키가 있다 그러므로 기본키로 검색한다
		// 받은 변수를 dto로 형변환 후 기본키로 점포에 저장이 되어있는지 검사
		// 있으면 데이터 가져와서 수정할 데이터(point,category,status) set해준 후 수정
		
		StoreBookDTO dto = (StoreBookDTO)object;
		
		// 책이 있는지 검사
		StoreBookDTO update_dto = storeBookDao.getBook( dto.getSbidx() );
		
		if( update_dto == null ) return false;
		
		// 수정할 항목만 수정
		update_dto.setCategory( dto.getCategory() );
		update_dto.setPoint( dto.getPoint() );
		update_dto.setStatus( dto.getStatus() );
		
		int result = storeBookDao.updateBook( update_dto );
		
		System.out.println("수정 된 로우 : " + result);
		
		if( result != 1 )
			return false;
		
		return true;
	}
	
	// 기본키로 삭제
	@Override
	public boolean delete(String sbidx) {
		int result = storeBookDao.deleteBook(sbidx);
		System.out.println("result : " + result);
		if( result != 1 )
			return false;

		return true;
	}

	@Override
	public List getBooksByIsbn(int sidx, String isbn) {
		List list = storeBookDao.getBooksByIsbn(sidx, isbn);
		return list;
	}

	@Override
	public Object getBookByPk(String pk) {
		StoreBookDTO dto = storeBookDao.getBook(pk);
		return dto;
	}
}
