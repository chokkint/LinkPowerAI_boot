package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.constants.ErrorCodeConstants;
import com.ai.ringball.framework.constants.SysConstants;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller 返回数据结构定义:<br>
 * {"ERROR_CODE":"SUCCESS","ERROR_MESSAGE":"ok","DATASET":object}<br>
 * object为对象类型的json,详见各接口注释
 */
public class ResultUtils {

	/**
	 * json code字段
	 */
	public static final String ERROR_CODE = "errorCode";

	/**
	 * json message字段
	 */
	public static final String ERROR_MESSAGE = "errorMessage";

	public static Map<String, Object> createResult() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorCode = ErrorCodeConstants.MSG_FRAMEWORK_HTTP_SUCCESS;
		resultMap.put(ERROR_CODE, converErrorCode(errorCode));
		// resultMap.put(ERROR_MESSAGE,
		// PropertyConfigurer.getErrorMessage(errorCode));
		resultMap.put(ERROR_MESSAGE, "成功");
		return resultMap;
	}

	/**
	 * HTTP协议controller接口调用成功,正常返回.
	 * 样例:{"ERROR_CODE":"SUCCESS","ERROR_MESSAGE":"ok","DATASET":object,"PAGE":
	 * object}
	 */
	public static Map<String, Object> createResult(String errorCode, String errorMessage, Object resultInfo, PageUtils page) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(SysConstants.ERROR_CODE_KEY, errorCode);
		resultMap.put(SysConstants.ERROR_MESSAGE_KEY, errorMessage);
		resultMap.put(SysConstants.DATASET_KEY, resultInfo);
		resultMap.put(SysConstants.PAGE_KEY, page);
		return resultMap;
	}

	public static Map<String, Object> createSuccessResult(Object resultInfo, PageUtils page) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(SysConstants.ERROR_CODE_KEY, SysConstants.ERROR_CODE_SUCCESS);
		resultMap.put(SysConstants.ERROR_MESSAGE_KEY, "操作成功!");
		resultMap.put(SysConstants.DATASET_KEY, resultInfo);
		resultMap.put(SysConstants.PAGE_KEY, page);
		return resultMap;
	}

	public static Map<String, Object> createErrorResult(Object resultInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(SysConstants.ERROR_CODE_KEY, SysConstants.ERROR_CODE_ERROR);
		resultMap.put(SysConstants.ERROR_MESSAGE_KEY, "操作出错,请稍后尝试或者联系管理员!");
		resultMap.put(SysConstants.DATASET_KEY, resultInfo);
		return resultMap;
	}

	public static Map<String, Object> createSessionTimeOutExceptionResult(Throwable throwable) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(SysConstants.ERROR_CODE_KEY, SysConstants.ERROR_CODE_SESSION_TIMEOUT);
		resultMap.put(SysConstants.ERROR_MESSAGE_KEY, "登陆已经过期，即将重新登陆!");
		return resultMap;
	}

	public static Map<String, Object> createNoAccessExceptionResult(Throwable throwable) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(SysConstants.ERROR_CODE_KEY, SysConstants.ERROR_CODE_NO_ACCESS);
		resultMap.put(SysConstants.ERROR_MESSAGE_KEY, "对不起,您没有权限操作此链接!");
		return resultMap;
	}

	public static Map<String, Object> createUnknowExceptionResult(Throwable throwable) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(SysConstants.ERROR_CODE_KEY, SysConstants.ERROR_CODE_UNKNOWN_EXCEPTIONS);
		resultMap.put(SysConstants.ERROR_MESSAGE_KEY, "系统出现未知异常,请联系管理员!");
		resultMap.put(SysConstants.DATASET_KEY, ExceptionUtils.getFullStackTrace(throwable));
		return resultMap;
	}

	/**
	 * HTTP协议controller接口调用成功,构建Dec异常返回json. 样例:
	 * 
	 * @param pageDTO
	 * @return
	 */
	/*
	 * public static Map<String, Object> createBaseExceptionResult(
	 * BaseException baseException) { Map<String, Object> resultMap = new
	 * HashMap<String, Object>(); resultMap.put(ERROR_CODE,
	 * converErrorCode(baseException.getCode())); resultMap.put(ERROR_MESSAGE,
	 * baseException.getMessage()); return resultMap; }
	 * 
	 * public static Map<String, Object> createBindExceptionResult(
	 * BindException bindException) { Map<String, Object> resultMap = new
	 * HashMap<String, Object>(); String errorCode =
	 * ErrorCodeConstants.MSG_FRAMEWORK_HTTP_VALID_EXCEPTION; BindingResult
	 * result = bindException.getBindingResult(); List<FieldError> fes =
	 * result.getFieldErrors(); StringBuilder sb = new StringBuilder(); if (fes
	 * != null && fes.size() > 0) { boolean flag = false; for (FieldError fe :
	 * fes) { if (flag) { sb.append(","); } sb.append("[");
	 * sb.append(fe.getCode()); sb.append("]");
	 * sb.append(fe.getDefaultMessage()); flag = true; } }
	 * resultMap.put(ERROR_CODE, converErrorCode(errorCode));
	 * resultMap.put(ERROR_MESSAGE, sb.toString()); return resultMap; }
	 * 
	 * public static Map<String, Object>
	 * createMethodArgumentNotValidExceptionResult(
	 * MethodArgumentNotValidException methodArgumentNotValidException) {
	 * Map<String, Object> resultMap = new HashMap<String, Object>(); String
	 * errorCode = ErrorCodeConstants.MSG_FRAMEWORK_HTTP_VALID_EXCEPTION;
	 * BindingResult result = methodArgumentNotValidException
	 * .getBindingResult(); List<FieldError> fes = result.getFieldErrors();
	 * String checkMsg = fes.get(0).getDefaultMessage(); String checkCode =
	 * fes.get(0).getCode(); resultMap.put(ERROR_CODE,
	 * converErrorCode(errorCode)); resultMap.put(ERROR_MESSAGE, "[" + checkCode
	 * + "]:" + checkMsg); return resultMap; }
	 * 
	 * public static Map<String, Object> createJsonProcessingExceptionResult(
	 * JsonProcessingException jsonProcessingException) { Map<String, Object>
	 * resultMap = new HashMap<String, Object>(); String errorCode =
	 * ErrorCodeConstants.MSG_FRAMEWORK_HTTP_JSON_EXCEPTION;
	 * resultMap.put(ERROR_CODE, converErrorCode(errorCode));
	 * resultMap.put(ERROR_MESSAGE, jsonProcessingException.getMessage());
	 * resultMap.put(DATA,
	 * ExceptionUtils.getFullStackTrace(jsonProcessingException)); return
	 * resultMap; }
	 * 
	 * public static Map<String, Object>
	 * createHttpMessageConversionExceptionResult(
	 * HttpMessageConversionException httpMessageConversionException) {
	 * Map<String, Object> resultMap = new HashMap<String, Object>(); String
	 * errorCode =
	 * ErrorCodeConstants.MSG_FRAMEWORK_HTTP_MESSAGE_CONVERT_EXCEPTION;
	 * resultMap.put(ERROR_CODE, converErrorCode(errorCode));
	 * resultMap.put(ERROR_MESSAGE,
	 * httpMessageConversionException.getMessage()); resultMap.put(DATA,
	 * ExceptionUtils .getFullStackTrace(httpMessageConversionException));
	 * return resultMap; }
	 */

	public static Map<String, Object> createLoginNeedResult() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorCode = ErrorCodeConstants.MSG_FRAMEWORK_HTTP_LOGIN_NEED;
		resultMap.put(ERROR_CODE, converErrorCode(errorCode));
		// resultMap.put(ERROR_MESSAGE,
		// PropertyConfigurer.getErrorMessage(errorCode));
		resultMap.put(ERROR_MESSAGE, "待定");
		return resultMap;
	}

	public static Integer converErrorCode(String errorCode) {
		return Integer.valueOf(errorCode.substring(1, errorCode.length()));
	}

	public static Map<String, Object> createAuthenticationExceptionResult(AuthenticationException authenticationException) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorCode = null;
		if (authenticationException instanceof IncorrectCredentialsException) {
			errorCode = ErrorCodeConstants.MSG_FRAMEWORK_HTTP_LOGIN_PASSWORD_ERROR;
		} else if (authenticationException instanceof UnknownAccountException) {
			errorCode = ErrorCodeConstants.MSG_FRAMEWORK_HTTP_LOGIN_UNKNOWN_ACCOUNT;
		} else if (authenticationException instanceof LockedAccountException) {
			errorCode = ErrorCodeConstants.MSG_FRAMEWORK_HTTP_LOGIN_LOCKED_ACCOUNT;
		} else {
			errorCode = ErrorCodeConstants.MSG_FRAMEWORK_HTTP_LOGIN_UNKNOWN_ERROR;
		}
		resultMap.put(ERROR_CODE, converErrorCode(errorCode));
		resultMap.put(ERROR_MESSAGE, PropertyConfigurer.getErrorMessage(errorCode));
		return resultMap;
	}
}
