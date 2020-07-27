package com.project.comic.storebook;

import java.util.List;

public interface IStoreBookService {
	public boolean add(Object object);
	public boolean update(Object object);
	public boolean remove(Object object);
	public Object getByISBN(String isbn);
	public Object getByPK(String pk);
	public List getPageList(int cp, int listsize, int sidx);
	public int getBookCountAll(String sidx);
	public int getBookCount(String sidx, String isbn);
}
