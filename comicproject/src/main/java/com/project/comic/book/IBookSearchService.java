package com.project.comic.book;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface IBookSearchService {
	public String bookSearch(Map map_param, HttpSession session);
	public String bookSearchMore( HttpSession session);
}
