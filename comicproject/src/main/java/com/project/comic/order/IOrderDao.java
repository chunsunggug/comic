package com.project.comic.order;

import java.util.List;

public interface IOrderDao {
	public int addNewOrder(OrderDTO dto);
	public int getMaxOidx();
	public OrderDTO getDTO(int oaidx);
	public List getOrdersPageByState(int sidx, int cp, int listsize, OrderDTO.State state);
	public int changeState(int oaidx, String datetype, OrderDTO.State state);
}
