package com.project.comic.book.seoji;

import org.springframework.stereotype.Component;

import com.project.comic.book.IBookConnector;
import com.project.comic.book.IPageSearch;
import com.project.comic.book.NotSupportedQueryModel;

@Component
public class SeojiPageSearch implements IPageSearch {
	
	@Override
	public Object getPageData( int page_num, int page_size, Object model ) {
		if( model instanceof SeojiQueryModel )
		{
			IBookConnector conn = new SeojiBookConnector();
			SeojiQueryModel md = (SeojiQueryModel)model;
			
			md.setPage_no(page_num);
			md.setPage_size(page_size);
			
			String result = conn.setModel(model).connect();			
			
			return result;
			
		}
		else
			throw new NotSupportedQueryModel();
	}

}
