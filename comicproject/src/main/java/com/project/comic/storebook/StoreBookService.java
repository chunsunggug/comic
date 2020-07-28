package com.project.comic.storebook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StoreBookService implements IStoreBookService{

	@Autowired
	@Qualifier("impl")
	private IStoreBookDao storeBookDao;

	@Override
	public boolean add(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object loadBookDataByISBN(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByPK(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getPageList(int cp, int listsize, int sidx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBookCountAll(String sidx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBookCount(String sidx, String isbn) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	@Transactional
	public int add(StoreBookDTO dto) {
		
		// dto 에 isbn이 제대로 들어있는지 검증
		JSONObject val_result = Utility.JSONParse( getBookByIsbn(dto.getIsbn()) );
		if( !((long)val_result.get("total") == 1) ) return 0;
		// 검증 완료
		
		JSONObject item = (JSONObject)((JSONArray)val_result.get("items")).get(0);

		// db에 저장되어있는 형태로 isbn 값 수정
		dto.setIsbn((String)item.get("isbn"));
		
		int count = storeBookDao.getCount(dto);
		dto.setIdx( ++count );

		dto.setSbidx( makePKFromDTO(dto) );
		System.out.println("sbidx : " + dto.getSbidx());
		
		// isbn 받아서 책 목록에 등록
		int result = storeBookDao.add(dto);
		System.out.println("책 추가 로우 : " + result);
		
		return result;
	}
	
	// 도서 추가 시 isbn칸에 isbn쓰면 자동으로 책데이터 불러오는데 사용하는 용도
	public String getBookByIsbn(String isbn) {
		NaverQueryModel md = new NaverQueryModel();
		md.setDisplay(1);
		md.setStart(1);
		md.setDetail("d_isbn");
		md.setQuery(isbn);
		
		String result = (String)naverPageSearch.getPageData(md);
		
		if( result != null )
			return result;
			
		return null;
	}

	// 페이지별 목록 리스트
	@Transactional
	public List getPageList(int cp, int listsize, int sidx) { // 현재페이지, 페이지목록수, 점포idx
		Map datamap = new HashMap();
		datamap.put("sidx", sidx);
		datamap.put("start", (cp - 1) * listsize );
		datamap.put("size", listsize);
		
		List for_join_data = storeBookDao.getForPageJoinData(datamap);	// 페이지 별 조인할 데이터 리스트
		List complete_dto = getJoinDTO(for_join_data);	// 조인해서 완성본 dto 리턴
		
		return complete_dto;
	}
	
	public int getBookCount(int sidx){
		return storeBookDao.getStoreBookAllCount(sidx);
	}
	
	// dto에 있는 속성들을 이용해서 기본키를 만들어낸다
 	private String makePKFromDTO(StoreBookDTO dto) {
		String isbn = dto.getIsbn();
		String pre =  String.format( "%03d", dto.getSidx());
		String middle, post;
		
		middle = isbn.substring( isbn.length() - 13, isbn.length() );
		post = String.format("%03d", dto.getIdx());
		
		return pre + middle + post;
	}
	
	// StoreBookDTO 모델에서 kakao에서 가져온 데이터와 합쳐서 StoreBookKakaoDTO로 치환
	private void storeBookToKakaoDTO(StoreBookDTO from, JSONObject joinSum ,StoreBookKakaoDTO to) {
		JSONArray auths = (JSONArray)joinSum.get("authors");
		String tmp = "";
		for(int i=0; i < auths.size(); i++ ) {
			tmp += auths.get(i);
			if( i != auths.size() - 1)
				tmp += ",";
		}
		to.setAuthors(tmp);
		to.setThumbnail((String)joinSum.get("thumbnail"));
		to.setTitle((String)joinSum.get("title"));
		to.setPoint(from.getPoint());
		to.setStatus(from.getStatus());
		to.setSbidx(from.getSbidx());
		to.setSdate(from.getSdate());
	}

	private List getJoinDTO(List<StoreBookDTO> storeBookDto){
		/*List arr = new ArrayList<StoreBookKakaoDTO>();
		
		for(int i=0; i < storeBookDto.size(); i++ ) {
			StoreBookDTO dto = storeBookDto.get(i);
			JSONObject json_book = getBookByIsbn(dto.getIsbn());
 
			StoreBookKakaoDTO toDto = new StoreBookKakaoDTO();
			
			storeBookToKakaoDTO(dto, json_book, toDto);
			arr.add(toDto);
		}
		return arr;
		return null;
	}*/
}
