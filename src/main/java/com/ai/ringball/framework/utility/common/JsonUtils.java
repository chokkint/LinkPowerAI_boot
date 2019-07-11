package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.constants.ErrorCodeConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();

	/*
	 * *********************新框架专用封装 START*****************************
	 */

	/*
	 * *********************新框架专用封装 END ***********************************
	 */
	static public String getDatagridData(List<?> dataList, PageUtils page) {
		return getData(dataList, page, "yyyy-MM-dd");
	}

	static public String getDatagridData_datetime(List<?> dataList, PageUtils page) {
		return getData(dataList, page, "yyyy-MM-dd HH:mm:ss");
	}

	static public String getDatagridData(List<?> dataList, PageUtils page, String dateFormat) {
		return getData(dataList, page, dateFormat);
	}

	static public String getData(List<?> dataList, PageUtils page, String dateFormat) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataset", dataList);
		if (page != null) {
			map.put("page", page);
			map.put("total", page.getPageCount());
		}
		map.put("rows", dataList);
		return parseFromObject(map, dateFormat);
	}

	static public String parseFromObject(Object o) {
		return parseFromObject(o, "yyyy-MM-dd");
	}

	static public String parseFromObject_datetime(Object o) {
		return parseFromObject(o, "yyyy-MM-dd HH:mm:ss");
	}

	static public String parseFromObject(Object o, String dateFormat) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
		String result = "";
		try {
			result = objectMapper.writeValueAsString(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	static public <T> T parseToObject(String string, Class<T> t) {
		ObjectMapper objectMapper = new ObjectMapper();
		T object = null;
		try {
			objectMapper.enableDefaultTyping(DefaultTyping.JAVA_LANG_OBJECT);
			object = objectMapper.readValue(string, t);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 *            准备转换的对象
	 * @return json字符串
	 * @throws Exception
	 */
	public static String beanToJson(Object obj) {
		try {
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			String errorCode = ErrorCodeConstants.MSG_DEC_FRAMEWORK_UTIL_BEAN_TO_JSON_ERROR;
			String errorMessage = PropertyConfigurer.getErrorMessage(errorCode);
		}
		return null;
	}
}
