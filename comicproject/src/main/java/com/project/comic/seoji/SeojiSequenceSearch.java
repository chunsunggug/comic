package com.project.comic.seoji;

import org.springframework.stereotype.Component;

import com.project.comic.NotSupportedQueryModel;
import com.project.comic.book.IBookConnector;
import com.project.comic.book.ISequenceSearch;

@Component
public class SeojiSequenceSearch implements ISequenceSearch {

	@Override
	public Object nextSearch(Object model) {
		if( model instanceof SeojiQueryModel )
		{
			IBookConnector conn = new SeojiBookConnector();
			SeojiQueryModel md = (SeojiQueryModel)model;
			String result;
			
			md.setPage_no( md.getPage_no() + 1 );
			
			result = conn.setModel(model).connect();

			return result;
		}else
			throw new NotSupportedQueryModel();
	}

}
