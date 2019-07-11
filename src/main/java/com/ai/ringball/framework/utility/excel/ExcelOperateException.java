package com.ai.ringball.framework.utility.excel;

import org.apache.log4j.Logger;

public class ExcelOperateException extends Exception {

	private static final long serialVersionUID = -5109916134001677459L;
	private static final Logger logger = Logger.getLogger(ExcelOperateException.class);
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public ExcelOperateException(String message) {
		this.message = message;
		logger.debug("ExcelOperateException：" + message);
	}

}
