package com.project.comic.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class Utility {
	private static JSONParser jsonParser = new JSONParser();
	private static Gson gson = new Gson();
	
	public static String decodeString(String str) {
		try {
			String param = URLDecoder.decode( str, "utf-8");
			
			return param;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject mapToJSON( Map map ) {
		Iterator iterator = map.keySet().iterator();
		JSONObject json = new JSONObject();

		while( iterator.hasNext() ) {
			String key = (String)iterator.next();
			json.put(key, map.get(key) );
		}

		return json;
	}
	
	public static Object JSONParse(String str) {
		try {
			JSONObject returnV = (JSONObject)jsonParser.parse(str);
			return returnV;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String objectToJson(Object object) {
		return gson.toJson(object);
	}
}
