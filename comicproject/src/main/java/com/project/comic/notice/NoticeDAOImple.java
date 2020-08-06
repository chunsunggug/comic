package com.project.comic.notice;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.comic.notice.NoticeDAO;

@Component
public class NoticeDAOImple implements NoticeDAO {

	@Autowired
	private SqlSessionTemplate sqlMap;
	
	@Override
	public int addNotice(NoticeDTO ndto) {
		return sqlMap.insert("addNotice", ndto);
	}

}
