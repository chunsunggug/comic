package com.project.comic.book;

public interface IBookManager {
	public void add(BookDTO bookdto);
	public void delete(BookDTO bookdto);
	public void update(BookDTO bookdto);
	public void get(Object object);
}
