package com.project.comic.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.comic.order.OrderDTO.State;

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
	public List getOrdersPageByState(int sidx, int cp, int listsize, OrderDTO.State state) {
		Map param = new HashMap();
		
		param.put("sidx", sidx);
		param.put("state", state);
		param.put("start", (cp-1) * listsize );
		param.put("listsize", listsize);
		return sqlMap.selectList("getOrdersPageByState", param);
	}
	
	@Override
	public OrderDTO getDTO(int oaidx) {
		return sqlMap.selectOne("getDTO", oaidx);
	}

	@Override
	public int changeState(int oaidx, String datetype, State state) {
		Map param = new HashMap();

		param.put("oaidx", oaidx);
		param.put("datetype", datetype);
		param.put("state", state);
		
		return sqlMap.update("changeState", param);
	}
}
