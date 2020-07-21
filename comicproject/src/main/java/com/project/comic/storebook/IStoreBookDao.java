package com.project.comic.storebook;

import java.util.List;
import java.util.Map;

public interface IStoreBookDao {
	public int add(StoreBookDTO object);
	public StoreBookDTO get(String sbidx);
	public StoreBookDTO get(String isbn, int idx,int sidx);
	public int getCount(StoreBookDTO object);
	public int getStoreBookAllCount(int sidx);
	public List getForPageJoinData(Map map);
}
