package com.project.comic.storebook;

import java.util.List;
import java.util.Map;

public interface IStoreBookDao {
	public int add(StoreBookDTO object);
	public int exist(Map param);
	public List getPageList(Map param);
	public int getBooksCountAll(int sidx);
}
