package com.project.comic.store;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreDaoImpl implements StoreDao {

	@Autowired
	private SqlSessionTemplate sqlMap;
	
	@Override
	public StoreDTO getStore(String id) {
		return sqlMap.selectOne("getStore", id);
	}

	@Override
	public StoreDTO loginStore(Map map) {
		return sqlMap.selectOne("loginStore",map);
	}

	@Override
	public List<StoreDTO> getStoreAll() {
		return sqlMap.selectList("getStoreAll");
	}

	@Override
	public int addStore(StoreDTO storeDto) {
		return sqlMap.insert("addStore", storeDto);
	}

	@Override
	public int deleteStore(String id) {
		return sqlMap.delete("deleteStore", id);
	}

	@Override
	public int deleteStoreAll() {
		return sqlMap.delete("deleteStoreAll");
	}

	@Override
	public int updateStore(StoreDTO storeDto) {
		return sqlMap.update("updateStore", storeDto);
	}

	@Override
	public int updateStorePoint(StoreDTO storeDto) {
		return sqlMap.update("updateStorePoint", storeDto);
	}

	@Override
	public int checkStore(String id) {
		return sqlMap.selectOne("checkStore", id);
	}

}
