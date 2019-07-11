package com.ai.ringball.framework.constants;

/**
 * 系统Error Code定义<br>
 * 
 * E0000000000<br>
 * 第1位:E 固定值<br>
 * 第2位:0 预留<br>
 * 第3位:0 预留<br>
 * 第4位:0 预留<br>
 * 第5位:0 预留<br>
 * 第6位:N 系统 DEC OMS:0<br>
 * 第7位:N 模块 Framework:0 USER:1 <br>
 * 第8位:N 子模块 HTTP:0 DB:1 <br>
 * 第9-10位:NN 2位置数字 具体异常编码<br>
 * 
 * 
 * 
 * @author wangchaochao
 * 
 */
public class ErrorCodeConstants {

	public static final String BASECODE = "E00000";

	public static final String FRAMEWORK = "0";

	public static final String HTTP = "0";

	public static final String DB = "1";

	public static final String UTIL = "2";

	/**
	 * HTTP OK
	 */
	public static final String MSG_FRAMEWORK_HTTP_SUCCESS = BASECODE
			+ FRAMEWORK + HTTP + "00";

	/**
	 * HTTP Exception
	 */
	public static final String MSG_FRAMEWORK_HTTP_EXCEPTION = BASECODE
			+ FRAMEWORK + HTTP + "01";

	/**
	 * Valid Exception
	 */
	public static final String MSG_FRAMEWORK_HTTP_VALID_EXCEPTION = BASECODE
			+ FRAMEWORK + HTTP + "02";

	/**
	 * Json Exception
	 */
	public static final String MSG_FRAMEWORK_HTTP_JSON_EXCEPTION = BASECODE
			+ FRAMEWORK + HTTP + "03";

	/**
	 * Message Convert Exception
	 */
	public static final String MSG_FRAMEWORK_HTTP_MESSAGE_CONVERT_EXCEPTION = BASECODE
			+ FRAMEWORK + HTTP + "04";

	/**
	 * login need
	 */
	public static final String MSG_FRAMEWORK_HTTP_LOGIN_NEED = BASECODE
			+ FRAMEWORK + HTTP + "10";

	/**
	 * login password error
	 */
	public static final String MSG_FRAMEWORK_HTTP_LOGIN_PASSWORD_ERROR = BASECODE
			+ FRAMEWORK + HTTP + "11";

	/**
	 * login unknown account
	 */
	public static final String MSG_FRAMEWORK_HTTP_LOGIN_UNKNOWN_ACCOUNT = BASECODE
			+ FRAMEWORK + HTTP + "12";

	/**
	 * login locked account
	 */
	public static final String MSG_FRAMEWORK_HTTP_LOGIN_LOCKED_ACCOUNT = BASECODE
			+ FRAMEWORK + HTTP + "13";

	/**
	 * login unknown error
	 */
	public static final String MSG_FRAMEWORK_HTTP_LOGIN_UNKNOWN_ERROR = BASECODE
			+ FRAMEWORK + HTTP + "14";

	/**
	 * DB Result not singleton
	 */
	public static final String MSG_FRAMEWORK_DB_RESULT_NOT_SINGLETON = BASECODE
			+ FRAMEWORK + DB + "00";

	/**
	 * DB Record not exist
	 */
	public static final String MSG_FRAMEWORK_DB_RECORD_NOT_EXIST = BASECODE
			+ FRAMEWORK + DB + "01";

	/**
	 * UTIL Convert Entity List to DTO List Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_ENTITYLIST_TO_DTOLIST_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "00";

	/**
	 * UTIL Convert Map to DTO Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_MAP_TO_DTO_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "01";

	/**
	 * UTIL Convert Map to Entity Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_MAP_TO_ENTITY_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "02";

	/**
	 * UTIL Convert PageDTO to Page Error
	 */
	public static final String MSG_DEC_FRAMEWORK_UTIL_PAGEDTO_TO_PAGE_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "03";

	/**
	 * UTIL Convert Entity to DTO Error
	 */
	public static final String MSG_DEC_FRAMEWORK_UTIL_ENTITY_TO_DTO_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "04";

	/**
	 * UTIL Convert JSON to BEAN Error
	 */
	public static final String MSG_DEC_FRAMEWORK_UTIL_JSON_TO_BEAN_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "05";

	/**
	 * UTIL Convert BEAN to JSON Error
	 */
	public static final String MSG_DEC_FRAMEWORK_UTIL_BEAN_TO_JSON_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "06";

	/**
	 * UTIL Get Field Value Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_GET_FIELD_VALUE_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "07";

	/**
	 * UTIL Set Field Value Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_SET_FIELD_VALUE_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "08";

	/**
	 * UTIL Field Can not be null
	 */
	public static final String MSG_FRAMEWORK_UTIL_FIELD_NULL_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "09";

	/**
	 * UTIL Excel Import Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_EXCEL_IMPORT_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "10";

	/**
	 * UTIL Convert DTO to Entity Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_DTO_TO_ENTITY_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "11";

	/**
	 * Build Url Normal Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_BUILD_URL_NORMAL_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "12";

	/**
	 * Build Url Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_BUILD_URL_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "13";

	/**
	 * Build Param Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_BUILD_PARAM_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "14";

	/**
	 * Build Param Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_HTTP_SEND = BASECODE
			+ FRAMEWORK + UTIL + "15";

	/**
	 * UTIL Convert DTO List to Entity List Error
	 */
	public static final String MSG_FRAMEWORK_UTIL_DTOLIST_TO_ENTITYLIST_ERROR = BASECODE
			+ FRAMEWORK + UTIL + "16";
}
