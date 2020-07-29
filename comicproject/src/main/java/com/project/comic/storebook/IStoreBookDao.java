package com.project.comic.storebook;

import java.util.List;
import java.util.Map;

public interface IStoreBookDao {
	public int add(StoreBookDTO object);
	public int exist(int sidx, String isbn);
	public int exist(int sbidx);
	public List getPageList(int cp, int listsize, int sidx);
	public int getBooksCountAll(int sidx);
	public StoreBookDTO getBook(int sidx, String isbn);
	public int updateBook(StoreBookDTO dto);
	public int deleteBook(int sidx, String isbn);
	public int deleteBook(int sbidx);
}
