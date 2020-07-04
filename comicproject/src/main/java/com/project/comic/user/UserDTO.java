package com.project.comic.user;

import java.util.Date;

import lombok.Data;


@Data
public class UserDTO {
	private int uidx;
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String birth;
	private String addr;
	private int point;
	private Date sdate;
	private Date edate;
	private String type;
	private String isyn;
	
	public UserDTO() {}
	
	public UserDTO(int uidx,String id, String pwd, String name, String phone, String birth,String addr, int point, Date sdate,
			Date edate, String type, String isyn) {
		super();
		this.uidx=uidx;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.addr = addr;
		this.point = point;
		this.sdate = sdate;
		this.edate = edate;
		this.type = type;
		this.isyn = isyn;
	}
	
}
