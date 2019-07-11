package com.ai.ringball.framework.utility.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.util.JSON;

public class JsonStrToMap {

	/**
	 * 功能说明:json字符串转化为map
	 * 
	 * @param jsonString
	 *            json字符串
	 * @return map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonStrToMap(String jsonString) {

		// 反序列化 把json 转化为对象
		Object parseObj = JSON.parse(jsonString);

		// 把对象转化为map
		Map<String, Object> map = (HashMap<String, Object>) parseObj;
		return map;
	}

	/**
	 * 功能说明:json字符串转化为List
	 * 
	 * @param jsonString
	 *            json字符串
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> jsonStrToMapList(String jsonString) {

		// 反序列化 把json 转化为对象
		Object parseObj = JSON.parse(jsonString);

		// 把对象转化为List
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) parseObj;
		return mapList;
	}
}