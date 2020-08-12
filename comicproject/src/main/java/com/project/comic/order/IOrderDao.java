package com.project.comic.order;

import java.util.List;

public interface IOrderDao {
	public int addNewOrder(OrderDTO dto);
	public int getMaxOidx();
	public List getDREQOrders(int sidx);
}
