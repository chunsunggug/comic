package com.project.comic.seoji;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comic.book.IBookSearchService;
import com.project.comic.book.ISequenceSearch;

@Service
public class SeojiBookSearchService implements IBookSearchService {

	@Autowired
	private ISequenceSearch seojiSequenceSearch;

	private final int PAGE_SIZE = 12;
	
	@Override
	public String bookSearch(Map map_param, HttpSession session) {
		SeojiQueryModel model = new SeojiQueryModel();
		model.setMapParam( map_param );
		model.setPage_no( 0 );
		model.setPage_size(PAGE_SIZE);
		
		String page_data = (String)seojiSequenceSearch.nextSearch(model);

		session.setAttribute( "query_model", model );
		
		return page_data;
	}

	@Override
	public String bookSearchMore( HttpSession session) {
		SeojiQueryModel model = (SeojiQueryModel)session.getAttribute("query_model");
		String page_data = (String)seojiSequenceSearch.nextSearch(model);
		
		session.setAttribute( "query_model", model );
		
		return page_data;
	}

}
