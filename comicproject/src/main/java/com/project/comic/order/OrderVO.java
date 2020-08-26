package com.project.comic.order;

import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONObject;

import lombok.Data;

@Data
public class OrderVO {
	private int oaidx;			// 주문 db 기본키
	private int oidx;			// 주문번호
	private int sbidx;			// 책 idx
	private int uidx;			// 유저 idx
	private OrderDTO.State state;// 배송 상태	BREQ:대여 요청, BC:대여요청확인, BD:대여배송중, BDC:대여중,
								// 			RREQ:반납 요청, RC:반납요청확인, RD:반납배송중, RDC:완료
	private String breqdate;	// 대여요청 시간
	private String bcdate;		// 대여요청확인 시간
	private String bddate;		// 대여배송 시작시간
	private String bdcdate;		// 대여배송 완료시간
	private String rreqdate;	// 반납요청 시간
	private String rcdate;		// 반납요청확인 시간
	private String rddate;		// 반납배송시작 시간
	private String rdcdate;		// 반납배송완료 시간
	private String odate;		// 주문등록 시간
	private String expdate;		// 반납예정일
	private int point;			// 대여료
	private String uaddr;		// 유저 주소
	private String reqtype;		// 요청구분
	
	private String title;		// 도서명
	private String thumbnail;	// 커버그림 url
	private int delcount;		// 연장횟수
	private int deldate;		// 연체일수
	
	public void setDTO(OrderDTO dto) {
		oaidx = dto.getOaidx();
		oidx = dto.getOidx();
		sbidx = dto.getSbidx();
		uidx = dto.getUidx();
		state = dto.getState();
		breqdate = dto.getBreqdate();
		bcdate = dto.getBcdate();
		bddate = dto.getBddate();
		bdcdate = dto.getBdcdate();
		rreqdate = dto.getRreqdate();
		rcdate = dto.getRcdate();
		rddate = dto.getRddate();
		rdcdate = dto.getRdcdate();
		odate = dto.getOdate();
		point = dto.getPoint();
		uaddr = dto.getUaddr();
		expdate = dto.getExpdate();
		delcount = dto.getDelcount();
		
		try {
			if( expdate != null ) {
				Calendar day1 = Calendar.getInstance();
				Calendar day2 = Calendar.getInstance();
				
				String[] expset = expdate.split("-");
				
				day1.set(Integer.parseInt(expset[0]),
						Integer.parseInt(expset[1]),
						Integer.parseInt(expset[2]) );
				
				Long diff = (day2.getTimeInMillis() - day1.getTimeInMillis()) / (24*60*60*1000);
				deldate = diff < 0 ? 0 : diff.intValue() + 31;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		switch(state) {
		case BDC:
		case BREQ:
		case BC:
		case BD:
			reqtype="B";
			break;
		case RDC:
		case RC:
		case RD:
		case RREQ:
			reqtype="R";
			break;
		}
	}
	
	public void setKakao(JSONObject json_kakao) {
		title = (String)json_kakao.get("title");
		thumbnail = (String)json_kakao.get("thumbnail");
	}
}
