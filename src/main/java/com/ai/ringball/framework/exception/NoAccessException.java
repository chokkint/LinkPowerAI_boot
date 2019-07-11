package com.ai.ringball.framework.exception;

import org.apache.log4j.Logger;

public class NoAccessException extends Exception {

	private static final long serialVersionUID = -5109916134001677459L;
	private static final Logger logger = Logger.getLogger(NoAccessException.class);

	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public NoAccessException() {
		this.errCode = "NoAccess";
		this.errMsg = "您没有权限访问此链接...";
		logger.debug("==============================NoAccessException==================================");
	}

}
