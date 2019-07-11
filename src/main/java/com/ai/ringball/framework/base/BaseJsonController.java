package com.ai.ringball.framework.base;

import com.ai.ringball.framework.exception.NoAccessException;
import com.ai.ringball.framework.exception.SessionTimeOutException;
import com.ai.ringball.framework.utility.common.JsonUtils;
import com.ai.ringball.framework.utility.common.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public abstract class BaseJsonController {

	private static final Logger logger = LoggerFactory.getLogger(BaseJsonController.class);

	/*
	*//**
		 * 捕捉业务异常
		 * 
		 * @param ex
		 * @return
		 */
	/*
	 * @ExceptionHandler({ BaseException.class })
	 * 
	 * @ResponseStatus(value = HttpStatus.OK)
	 * 
	 * @ResponseBody protected Map<String, Object>
	 * handleBaseException(BaseException ex) { logger.error(
	 * "BaseController handleBaseException:",ex); return
	 * ResultUtils.createBaseExceptionResult(ex); }
	 * 
	 *//**
		 * 捕捉MVC校验异常
		 * 
		 * @param ex
		 * @return
		 */
	/*
	 * @ExceptionHandler({ MethodArgumentNotValidException.class })
	 * 
	 * @ResponseStatus(value = HttpStatus.OK)
	 * 
	 * @ResponseBody protected Map<String, Object>
	 * handleValidException(MethodArgumentNotValidException ex) { logger.error(
	 * "BaseController MethodArgumentNotValidException:",ex); return
	 * ResultUtils.createMethodArgumentNotValidExceptionResult(ex); }
	 * 
	 *//**
		 * 捕捉MVC校验异常
		 * 
		 * @param ex
		 * @return
		 */
	/*
	 * @ExceptionHandler({ BindException.class })
	 * 
	 * @ResponseStatus(value = HttpStatus.OK)
	 * 
	 * @ResponseBody protected Map<String, Object>
	 * handleValidException(BindException ex) { logger.error(
	 * "BaseController BindException:",ex); return
	 * ResultUtils.createBindExceptionResult(ex); }
	 * 
	 * 
	 *//**
		 * 捕捉JSON异常
		 * 
		 * @param ex
		 * @return
		 */
	/*
	 * @ExceptionHandler({ JsonProcessingException.class })
	 * 
	 * @ResponseStatus(value = HttpStatus.OK)
	 * 
	 * @ResponseBody protected Map<String, Object>
	 * handleJsonException(JsonProcessingException ex) { logger.error(
	 * "BaseController JsonProcessingException:",ex); return
	 * ResultUtils.createJsonProcessingExceptionResult(ex); }
	 * 
	 *//**
		 * 捕捉JSON异常
		 * 
		 * @param ex
		 * @return
		 *//*
		 * @ExceptionHandler({ HttpMessageConversionException.class })
		 * 
		 * @ResponseStatus(value = HttpStatus.OK)
		 * 
		 * @ResponseBody protected Map<String, Object>
		 * handleHttpMessageConversionException(HttpMessageConversionException
		 * ex) { logger.error("BaseController JsonProcessingException:",ex);
		 * return ResultUtils.createHttpMessageConversionExceptionResult(ex); }
		 */

	/**
	 * 捕捉除业务异常外其他所有异常
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(SessionTimeOutException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	protected String handleSessionException(SessionTimeOutException ex) {
		logger.error("BaseController handleSessionTimeOutException:", ex);

		return JsonUtils.parseFromObject(ResultUtils.createSessionTimeOutExceptionResult(ex));
	}

	/**
	 * 捕捉除业务异权限所有异常
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NoAccessException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	protected String handleAccessException(NoAccessException ex) {
		logger.error("BaseController handleAccessException:", ex);

		return JsonUtils.parseFromObject(ResultUtils.createNoAccessExceptionResult(ex));

	}

	/**
	 * 捕捉除业务异常外其他所有异常
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	protected String handleCommonException(Exception ex) {
		logger.error("BaseController handleCommonException:", ex);

		return JsonUtils.parseFromObject(ResultUtils.createUnknowExceptionResult(ex));
	}

}
