package com.project.comic.pay;

import java.util.List;

public interface IOrderDao {
	public int addNewOrder(List<OrderDTO> dto_list);
	public int getMaxOidx();
}
