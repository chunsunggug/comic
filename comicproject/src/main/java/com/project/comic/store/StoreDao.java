package com.project.comic.store;

import java.util.List;
import java.util.Map;

public interface StoreDao {
	public StoreDTO getStore(String id);
	public StoreDTO loginStore(Map map);
	public List getStoreAll();
	public int addStore(StoreDTO storeDto);
	public int deleteStore(String id);
	public int deleteStoreAll();//주석예정
	public int updateStore(StoreDTO storeDto);
	public int updateStorePoint(StoreDTO storeDto);
	public int checkStore(String id);
}
