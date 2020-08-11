package com.project.comic.pay;

import lombok.Data;

@Data
public class OrderDTO {
	private int oaidx;
	private int oidx;
	private int sbidx;
	private int uidx;
	private String sdate;
	private String odate;
	private String edate;
	private String state;
	private int point;
	private String uaddr;
}
