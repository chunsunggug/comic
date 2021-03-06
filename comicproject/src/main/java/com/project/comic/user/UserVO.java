package com.project.comic.user;

import lombok.Getter;

@Getter
public class UserVO {
	
	public void setSdate(String sdate) {
		this.sdate = sdate.substring(0,sdate.indexOf("."));
	}

	private int uidx;
	private String addr;
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String birth;
	private int point;
	private String post;
	private String si;
	private String gu;
	private String dong;
	private String detail;
	private String type;
	private String isyn;
	private String sdate;
	
	public UserVO() {}
	public UserVO(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

	public UserVO(int uidx,String addr, String id, String name, String phone, String birth, int point, String post,
			String si, String gu, String dong, String detail,String type,String isyn,String sdate) {
		super();
		this.uidx = uidx;
		this.addr = addr;
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.point = point;
		this.post = post;
		this.si = si;
		this.gu = gu;
		this.dong = dong;
		this.detail = detail;
		this.type = type;
		this.isyn = isyn;
		this.sdate = sdate;
	}
	@Override 
	public String toString() {
		return "UserVO[uidx="+uidx+",addr="+addr+",id="+id+",name="+name+",phone="+phone+",birth="+birth+",point="+point+",post="+post+",si="+si+",gu="+gu+",dong="+dong+",detail="+detail+",type="+type+",isyn="+isyn+",sdate"+sdate;
	}
	
	public String getFullAddr() {
		String str =post +" "+ si + " " + gu + " " + dong + " " + detail;
		return str;
	}
	
}
