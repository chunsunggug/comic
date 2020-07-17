package com.project.comic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/store")
public class StoreController {


	// 점주의 도서관리 페이지 이동
	@RequestMapping(value="/listbook.do")
	public String listBook() {
		return "book/bookmanage";
	}
}
