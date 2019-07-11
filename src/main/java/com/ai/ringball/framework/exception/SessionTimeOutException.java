package com.ai.ringball.framework.exception;

import org.apache.log4j.Logger;

public class SessionTimeOutException extends Exception {

	private static final long serialVersionUID = -5109916134001677459L;
	private static final Logger logger = Logger.getLogger(SessionTimeOutException.class);

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

	public SessionTimeOutException() {
		// super(msg);
		this.errCode = "sessionOut";
		this.errMsg = "您的登陆已经过期，即将转入登陆页面...";
		logger.debug("==============================SessionTimeOutException==================================");
	}
}
