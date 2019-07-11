package com.ai.ringball.framework.exception;

/**
 * DEC系统组所有异常的基类,DEC系统组内所有已知业务异常都应该继承自该类
 * 
 * @author wangchaochao
 * 
 */
public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 异常Code
	 */
	private String code;

	/**
	 * 构建新异常
	 * 
	 * @param message
	 */
	public BaseException(String message) {
		super(message);
	}

	/**
	 * 根据case构建新异常
	 * 
	 * @param message
	 * @param e
	 */
	public BaseException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * 构建新异常
	 * 
	 * @param message
	 */
	public BaseException(String code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * 根据case构建新异常
	 * 
	 * @param message
	 * @param e
	 */
	public BaseException(String code, String message, Throwable e) {
		super(message, e);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
