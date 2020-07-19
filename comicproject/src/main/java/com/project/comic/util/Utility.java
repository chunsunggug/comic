package com.project.comic.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utility {
	private static JSONParser jsonParser = new JSONParser();
	
	public static String decodeString(String str) {
		try {
			String param = URLDecoder.decode( str, "utf-8");
			if( param.length() < 13 ) param = param.substring(0,10);
			else param = param.substring(0, 13);
			
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
}
