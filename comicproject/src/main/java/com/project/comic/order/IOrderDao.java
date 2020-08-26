package com.project.comic.order;

import java.util.List;

public interface IOrderDao {
	public int addNewOrder(OrderDTO dto);
	public int getMaxOidx();
	public OrderDTO getDTO(int oaidx);
	public List getOrdersPageByState(int sidx, int cp, int listsize, OrderDTO.State state);
	public List getOrdersPageByType(int uidx, int cp, int listsize, String type);
	public int getOrdersCountByType(int uidx, String type);
	public int changeState(int oaidx, String datetype, OrderDTO.State state);
	public int setExpDate(int oaidx, int exp);
	public int delayExpDate(int oaidx, int exp);
}
