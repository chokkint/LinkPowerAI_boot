package com.ai.ringball.framework.utility.common;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * property加载器,通过Spring容器扩展点实现
 * 
 * @author wangchaochao
 *
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {

	private static Map<String, String> ctxPropertiesMap;

	private static final Logger logger = LoggerFactory.getLogger(PropertyConfigurer.class);

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		setContexProperty(props);
	}

	public static void setContexProperty(Properties props) {
		if (ctxPropertiesMap == null) {
			ctxPropertiesMap = new HashMap<String, String>();
		}
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			if (ctxPropertiesMap.containsKey(keyStr)) {
				String oldValue = ctxPropertiesMap.get(keyStr);
				logger.warn("property [key:" + keyStr + ",value:" + oldValue + "] override by [key:" + keyStr + ",value:" + value + "]");
			}
			ctxPropertiesMap.put(keyStr, value);

		}
	}

	public static String getErrorMessage(String code) {
		String message = ctxPropertiesMap.get(code);
		if (message == null) {
			logger.warn("property [key:" + code + "] not exist");
		}
		return code + ":" + message;
	}

	public static String getMessage(String code) {
		String message = ctxPropertiesMap.get(code);
		if (message == null) {
			logger.warn("property [key:" + code + "] not exist");
		}
		return message;
	}

	public static String getErrorMessage(String code, Object... args) {
		String message = ctxPropertiesMap.get(code);
		if (message == null) {
			logger.warn("property [key:" + code + "] not exist");
		}
		return code + ":" + MessageFormat.format(message, args);
	}

}