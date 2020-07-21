package com.project.comic.kakao;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
 
 
@Service
public class KakaoAPI {

	 public String getAccessToken (String authorize_code) {
	        String access_Token = "";
	        String refresh_Token = "";
	        String reqURL = "https://kauth.kakao.com/oauth/token";
	        
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            
	            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            
	            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=118237743806f276d679025f706c0e3c");
	            sb.append("&redirect_uri=http://localhost:8080/comic/kakaologin.do");
	            sb.append("&code=" + authorize_code);
	            bw.write(sb.toString());
	            bw.flush();
	            
	            //    결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : " + responseCode);
	 
	            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println("response body : " + result);
			
			  // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject;
	    		
				try {
					jsonObject = (JSONObject) jsonParser.parse(result);
					access_Token = (String)jsonObject.get("access_token");
					refresh_Token = (String)jsonObject.get("refresh_token");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  
	    		
	    		
			  
			 
	            System.out.println("access_token : " + access_Token);
	            System.out.println("refresh_token : " + refresh_Token);
	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	        
	        return access_Token;
	    }

	 
	 
	public HashMap getUserInfo(String access_Token) {
		
//	    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
	    HashMap<String, Object> userInfo = new HashMap<>();
	    String reqURL = "https://kapi.kakao.com/v2/user/me";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        
	        //    요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8" ));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("response body : " + result);
	        
	        
	     // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JSONParser jsonParser = new JSONParser(); // JsonParser parser = new JsonParser();
            try {
            
            	JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
				
            	JSONObject properties = (JSONObject) jsonObject.get("properties"); 
            	
            	Object nick = properties.get("nickname");
				String longId = Long.toString((long) jsonObject.get("id")); 
				
				System.out.println("nick:"+nick.toString());
				System.out.println("id:"+longId);

				userInfo.put("name", nick.toString());
			    userInfo.put("id", longId);
				
            } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  

	     
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    System.out.println("check now is kakao api in");
	    return userInfo;
	}
	  public String Logout(String autorize_code) {
	        final String RequestUrl = "https://kauth.kakao.com/oauth/logout?client_id=118237743806f276d679025f706c0e3c&logout_redirect_uri=http://localhost:8080/comic/index.do";
	 
	        try {
		        URL url = new URL(RequestUrl);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        
		        //    요청에 필요한 Header에 포함될 내용
		        conn.setRequestProperty("Authorization", "Bearer " + autorize_code);
		        
		        int responseCode = conn.getResponseCode();
		        System.out.println("responseCode : " + responseCode);
		        
		        return "success";
		   	 	        
		    } catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		        return "fail";
		   	    
		    }
	 
	 
	    }


	
	
	
}
