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
	public StoreBookDTO get(String sbidx) {
		return sqlMap.selectOne("getStoreBookBySbidx", sbidx);
	}
	
	@Override
	public StoreBookDTO get(String isbn, int idx,int sidx) {
		Map map = new HashMap();
		map.put("isbn", isbn);
		map.put("idx", idx);
		map.put("sidx", sidx);
		return sqlMap.selectOne("getStoreBookByIsbnIdx", map);
	}

	@Override
	public int getCount(StoreBookDTO object) {
		return sqlMap.selectOne("getStoreBookCount", object);
	}

	@Override
	public int getStoreBookAllCount(int sidx) {
		return sqlMap.selectOne("getStoreBookAllCount", sidx);
	}

	@Override
	public List getForPageJoinData(Map map) {
		return sqlMap.selectList("getForPageJoinData", map);
	}
}
