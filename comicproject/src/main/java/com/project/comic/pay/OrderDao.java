package com.project.comic.pay;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDao implements IOrderDao{

	@Autowired
	private SqlSessionTemplate sqlMap;
	
	@Override
	public int addNewOrder(List<OrderDTO> dto_list) {
		int result = 0;
		for(OrderDTO dto : dto_list ) {
			result += sqlMap.insert( "addNewOrder", dto );
		}
		return result;
	}

	@Override
	public int getMaxOidx() {
		return sqlMap.selectOne("getMaxOidx");
	}

}
