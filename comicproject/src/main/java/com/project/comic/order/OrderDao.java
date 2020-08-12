package com.project.comic.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDao implements IOrderDao{

	@Autowired
	private SqlSessionTemplate sqlMap;
	
	@Override
	public int addNewOrder(OrderDTO dto) {
		return sqlMap.insert( "addNewOrder", dto );
	}

	@Override
	public int getMaxOidx() {
		return sqlMap.selectOne("getMaxOidx");
	}

	@Override
	public List getDREQOrders(int sidx) {
		return sqlMap.selectList("getDREQOrders", sidx);
	}

}
