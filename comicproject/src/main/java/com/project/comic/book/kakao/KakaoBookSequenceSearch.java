package com.project.comic.book.kakao;

import org.springframework.stereotype.Component;

import com.project.comic.book.IBookConnector;
import com.project.comic.book.ISequenceSearch;
import com.project.comic.book.NotSupportedQueryModel;

@Component
public class KakaoBookSequenceSearch implements ISequenceSearch {

	// parameter key 설명
	// query 	: 검색어
	// sort 	: 정렬방법 accuracy(정확도순), recency(최신순)
	// page 	: 결과 페이지 번호 1~50, 디폴트 1
	// size 	: 한페이지에 보여질 문서 수 1~50, 디폴트 10
	// target 	: 검색필드제한 title, isbn, publisher, person
	// url 뒤에 parameter 붙여서 데이터 가져온 후 JSON으로 parsing해서 return
	@Override
	public Object nextSearch(Object model) {
		if(model instanceof KakaoQueryModel) {
			IBookConnector conn = new KakaoBookConnector();
			KakaoQueryModel md = (KakaoQueryModel)model;
			String result;

			md.setPage( md.getPage() + 1 );

			result = conn.setModel(md).connect();

			return result;
		}
		else
			throw new NotSupportedQueryModel();
	}
}
