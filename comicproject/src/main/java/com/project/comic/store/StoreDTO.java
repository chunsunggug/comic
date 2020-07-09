package com.project.comic.store;

import java.util.Date;

import lombok.Data;

@Data
public class StoreDTO {
	private int sidx;
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private int point;
	private String img;
	private String sdate;
	private String edate;
	private String isyn;
	private String birth;
	
	public StoreDTO() {}
	
	public StoreDTO(String id, String pwd, String name, String phone, int point, String img,
			String sdate, String edate, String isyn, String birth ) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.point = point;
		this.img = img;
		this.sdate = sdate;
		this.edate = edate;
		this.birth = birth;
		this.isyn = isyn;
	}
	
	public StoreDTO(int sidx, String id, String pwd, String name, String phone, int point, String img,
			String sdate, String edate, String isyn, String birth ) {
		this(id,pwd,name,phone,point,img,sdate,edate,birth,isyn);
		this.sidx = sidx;
	}
}
