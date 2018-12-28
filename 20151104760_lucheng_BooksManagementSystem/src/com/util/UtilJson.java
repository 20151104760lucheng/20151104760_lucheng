package com.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * json操作类
 * 
 * 
 */
public class UtilJson {
	/**
	 * 将Json对象转换成Map
	 * 
	 * @param jsonObject
	 *            json对象
	 * @return Map对象
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> toMap(String jsonString)
			throws JSONException {
		Map<String, String> result = new HashMap<String, String>();
		if (!UtilValiDate.isEmpty(jsonString)) {
			return result;
		}
		JSONObject jsonObject = new JSONObject(jsonString);
		Iterator<String> iterator = jsonObject.keys();
		String key = null;
		String value = null;
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = jsonObject.getString(key);
			result.put(key, value);

		}
		return result;
	}


	/**
	 * 将map转换成json
	 * @param mapStr
	 * @return String
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public static String mapToJson(Map mapStr) throws JSONException {
		JSONObject jall = new JSONObject();  
		jall.put("map", mapStr);//Map转换成Json 
		String jsonStr =  jall.toString();
		jsonStr = jsonStr.substring(jsonStr.indexOf(":")+1,jsonStr.length()-1);
		return jsonStr;
	}
}
