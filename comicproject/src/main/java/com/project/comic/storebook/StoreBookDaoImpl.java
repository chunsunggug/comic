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
	public int exist(Map param) {
		return sqlMap.selectOne("existIsbn", param);
	}

	@Override
	public List getPageList(Map param) {
		return sqlMap.selectList( "getPageList", param );
	}

	@Override
	public int getBooksCountAll(int sidx) {
		return sqlMap.selectOne("getBooksCountAll" ,sidx);
	}
}
