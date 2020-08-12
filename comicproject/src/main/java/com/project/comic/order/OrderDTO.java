package com.project.comic.order;

import lombok.Data;

@Data
public class OrderDTO {
	public static String STATE_DREQ = "DREQ";
	public static String STATE_DC = "DC";
	public static String STATE_DD = "DD";
	public static String STATE_B = "B";
	public static String STATE_RREQ = "RREQ";
	public static String STATE_RC = "RC";
	public static String STATE_RD = "RD";
	public static String STATE_C = "C";
	
	private int oaidx;			// 주문 db 기본키
	private int oidx;			// 주문번호
	private int sbidx;			// 책 idx
	private int uidx;			// 유저 idx
	private int sidx;			// 점포 idx
	private String state;		// 배송 상태	DREQ:대여 요청, DC:대여요청확인, DD:대여배송중, B:대여중,
								// 			RREQ:반납 요청, RC:반납요청확인, RD:반납배송중, C:완료
	private String dreqdate;	// 대여요청 시간
	private String dcdate;		// 대여요청확인 시간
	private String dddate;		// 대여배송 시작시간
	private String bdate;		// 대여배송 완료시간
	private String rreqdate;	// 반납요청 시간
	private String rcdate;		// 반납요청확인 시간
	private String rddate;		// 반납배송시작 시간
	private String cdate;		// 반납배송완료 시간
	private String odate;		// 주문등록 시간
	private int point;			// 대여료
	private String uaddr;		// 유저 주소
	private String isbn10;		// 조인해서 가져오는 isbn
	private String isbn13;		// 
}
