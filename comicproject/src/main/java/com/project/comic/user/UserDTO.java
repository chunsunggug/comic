package com.project.comic.user;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
public class UserDTO {
	private String id;
	private String pwd;
	private String name;
	private String addr;
	private String phone;
	private String birth;
	private int point;
	private Date sdate;
	private Date edate;
	private String type;
	private String isyn;
	private String post;
	
	public UserDTO() {}
	
	public UserDTO(String id, String pwd, String name, String addr, String phone, String birth, int point, Date sdate,
			Date edate, String type, String isyn, String post) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.addr = addr;
		this.phone = phone;
		this.birth = birth;
		this.point = point;
		this.sdate = sdate;
		this.edate = edate;
		this.type = type;
		this.isyn = isyn;
		this.post = post;
	}
	
}
