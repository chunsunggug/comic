package com.project.comic.storebook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("impl")
public class StoreBookDaoImpl implements IStoreBookDao {

	@Autowired
	private SqlSessionTemplate sqlMap;

	@Override
	public int add(StoreBookDTO object) {
		return sqlMap.insert("addStoreBook", object);
	}

	@Override
	public int exist(int sidx, String isbn) {
		Map param = new HashMap();
		
		param.put("sidx", sidx);		
		param.put("isbn", isbn);
		
		return sqlMap.selectOne("existIsbn", param);
	}
	
	@Override
	public int exist(int sbidx) {		
		return sqlMap.selectOne("existSbidx", sbidx);
	}

	@Override
	public List getPageList(int cp, int listsize, int sidx) {
		Map param = new HashMap();

		param.put( "listsize", listsize );
		param.put( "sidx", sidx );
		param.put( "start", (cp - 1) * listsize );
		
		return sqlMap.selectList( "getPageList", param );
	}

	@Override
	public int getBooksCountAll(int sidx) {
		return sqlMap.selectOne("getBooksCountAll" ,sidx);
	}

	@Override
	public StoreBookDTO getBook(int sidx, String isbn) {
		Map param = new HashMap();
		
		param.put("sidx", sidx);
		param.put("isbn", isbn);
		
		return sqlMap.selectOne( "getBook", param );
	}

	@Override
	public int updateBook(StoreBookDTO dto) {
		return sqlMap.update( "updateBook", dto );
	}

	@Override
	public int deleteBook(int sidx, String isbn) {
		Map param = new HashMap();
		
		param.put("sidx", sidx);
		param.put("isbn", isbn);
	
		return sqlMap.delete( "deleteBook", param );
	}
	
	@Override
	public int deleteBook(int sbidx) {
		return sqlMap.delete( "deleteSbidx", sbidx );
	}
	
}
