package com.project.comic.order;

import lombok.Data;

@Data
public class OrderDTO {
	public static enum State {BREQ,BC,BD,BDC,RREQ,RC,RD,RDC };
	
	private int oaidx;			// 주문 db 기본키
	private int oidx;			// 주문번호
	private int sbidx;			// 책 idx
	private int uidx;			// 유저 idx
	private int sidx;			// 점포 idx
	private State state;		// 배송 상태	BREQ:대여 요청, BC:대여요청확인, BD:대여배송중, BDC:대여중,
								// 			RREQ:반납 요청, RC:반납요청확인, RD:반납배송중, CDC:완료
	private String breqdate;	// 대여요청 시간
	private String bcdate;		// 대여요청확인 시간
	private String bddate;		// 대여배송 시작시간
	private String bdcdate;		// 대여배송 완료시간
	private String rreqdate;	// 반납요청 시간
	private String rcdate;		// 반납요청확인 시간
	private String rddate;		// 반납배송시작 시간
	private String rdcdate;		// 반납배송완료 시간
	private String odate;		// 주문등록 시간
	private int point;			// 대여료
	private String uaddr;		// 유저 주소
	private String isbn10;		// 조인해서 가져오는 isbn
	private String isbn13;		// 
}
