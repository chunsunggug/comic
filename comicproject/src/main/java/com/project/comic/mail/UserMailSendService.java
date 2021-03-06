package com.project.comic.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserMailSendService {
	@Autowired
	private JavaMailSender mailSender;
	

	
	
	// 회원가입 발송 이메일(인증키 발송)
		public boolean mailSendWithUserKey(Map<String, String> mailInfo) {
			
			MimeMessage mail = mailSender.createMimeMessage();
			
			String name = mailInfo.get("name");
			String id = mailInfo.get("id");
			String mailcode = mailInfo.get("mailcode");
			
			String htmlStr = "<h2>안녕하세요.<br>집에서 편하게 빌리는 방빠닥 코믹스입니다.!</h2><br><br>" 
					+ "<h3>" + name + "님</h3>" + "<p>아이디 : "+id+"<br>" 
					+ "우측 인증을 클릭해주세요 :  "+"<a href='http://localhost:8080/comic/emailConfirm.do?userEmail="+id+"&&mailcode="+mailcode+"'>인증"+"</a></p>"
					+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		/*
		 * "<a href='http://localhost/user/emailConfirm?userEmail=" + vo.getUserEmail()
		 * + "&key=" + key + "' target='_blenk'>이메일 인증 확인</a>");
		 */
			
			
			try {
				mail.setSubject("[본인인증] 방빠닥 코믹스의 인증메일입니다", "utf-8");
				mail.setText(htmlStr, "utf-8", "html");
				mail.addRecipient(RecipientType.TO, new InternetAddress(id));
				mailSender.send(mail);
				return true; 
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	
		// 비밀번호 찾기 이메일
		public boolean mailSendWithUserKey(String name,String id, String pwd, HttpServletRequest request) {
			
			MimeMessage mail = mailSender.createMimeMessage();
			
			String htmlStr = "<h2>안녕하세요.<br>집에서 편하게 빌리는 방빠닥 코믹스입니다.!</h2><br><br>" 
					+ "<h3>" + name + "님</h3>" + "<p>아이디 : "+id+"<br>" 
					+ "비밀번호 : "+pwd+"</p>"
					+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
			
			
			
			try {
				mail.setSubject("[본인인증] 방빠닥 코믹스의 비밀번호 확인 메일입니다", "utf-8");
				mail.setText(htmlStr, "utf-8", "html");
				mail.addRecipient(RecipientType.TO, new InternetAddress(id));
				mailSender.send(mail);
				return true; 
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	
}
