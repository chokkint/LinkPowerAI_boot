package com.ai.ringball.framework.utility.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author wangchaochao
 *
 */
public class ConvertUtils {

	// private static Logger logger =
	// LoggerFactory.getLogger(ConvertUtils.class);

	public static Map<String, Object> beanToMap(Object entity) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			Object o = null;
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			Method getMethod;
			try {
				getMethod = entity.getClass().getMethod(getMethodName, new Class[] {});
				o = getMethod.invoke(entity, new Object[] {});
			} catch (Exception e) {
				// e.printStackTrace();
			}
			if (o != null) {
				parameter.put(fieldName, o);
			}
		}
		return parameter;
	}
}
