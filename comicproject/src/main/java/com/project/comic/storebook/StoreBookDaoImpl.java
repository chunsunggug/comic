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
	public int getBookCount(int sidx, String isbn) {
		Map param = new HashMap();
		
		param.put( "sidx", sidx );
		param.put( "isbn", isbn );
		
		return sqlMap.selectOne( "getBookCount", param );
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
	public StoreBookDTO getBook(String sbidx) {		
		return sqlMap.selectOne( "getBook", sbidx );
	}

	@Override
	public int updateBook(StoreBookDTO dto) {
		return sqlMap.update( "updateBook", dto );
	}

	@Override
	public int deleteBook(String sbidx) {
		return sqlMap.delete( "deleteBook", sbidx );
	}

	@Override
	public int getBookMaximumIdx(int sidx, String isbn) {
		Map param = new HashMap();

		param.put( "sidx", sidx );
		param.put( "isbn", isbn );
		
		return sqlMap.selectOne( "getBookMaximumIdx", param );
	}

	@Override
	public int canBorrow(int sidx, String isbn) {
		Map param = new HashMap();
		
		param.put( "sidx",sidx );
		param.put( "isbn", isbn );
		
		return sqlMap.selectOne( "canBorrow", param );
	}

	@Override
	public List getBorrowableBooks(int sidx, String isbn) {
		Map param = new HashMap();
		
		param.put( "sidx", sidx );
		param.put( "isbn", isbn );
		
		return sqlMap.selectList( "getBorrowableBooks", param );
	}

	@Override
	public List getBooksByIsbn(int sidx, String isbn) {
		Map param = new HashMap();
		
		param.put( "sidx", sidx );
		param.put( "isbn", isbn );
		
		return sqlMap.selectList( "getBooksByIsbn", param );
	}
	
}
