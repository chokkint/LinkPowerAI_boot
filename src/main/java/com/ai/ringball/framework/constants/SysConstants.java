package com.ai.ringball.framework.constants;

public class SysConstants {
	
	


	/**
	 * 模型版本号
	 */
	public static final String SYS_VERSION = "1.0";
	
	
	/**
	 * 系統內置角色ID
	 */
	public static final String SYS_ROLE_ID_SSGI = "ROLE_SSGI";

	/**
	 * 系统默认下载Excel文件目录
	 */
	public static final String DOWNLOAD_FILE_ROOT_FOLDER = "C:/download/LinkPowerAI";

	/**
	 * 系统默认ZIP文件目录
	 */
	public static final String DOWNLOAD_FILE_ZIP_FOLDER = "C:/download/LinkPowerAI/ZIP";

	/**
	 * 系统默认上传Excel文件目录
	 */
	public static final String FTP_UPLOAD_FILE_ROOT_FOLDER = "C:/upload/LinkPowerAI";

	/**
	 * 系统默认上传Excel临时文件目录
	 */
	public static final String UPLOAD_TEMP_PATH = "C:/upload/temp";

	/**
	 * 系统默认模板文件目录
	 */
	public static final String TEMPLATE_FILE_FOLDER = "C:/template/LinkPowerAI";

	/**
	 * 用户Session Key
	 */
	public static final String USER_SESSOIN = "USER_SESSOIN";

	/**
	 * 用户IP Key
	 */
	public static final String USER_IP = "USER_IP";

	/**
	 * 补录系统系统工作日期session名称
	 */
	public static final String SYS_WORK_DATE_SESSOIN = "SYS_WORK_DATE_SESSOIN";

	public static final String USER_CONFIG = "USER_CONFIG";

	/**
	 * ODS Session Key
	 */
	public static final String ODS_SESSOIN = "ODS_SESSOIN";

	/**
	 * 用户登录 时所选可操作分行 session Key
	 */
	public static final String ORG_SESSOIN = "ORG_SESSOIN";

	/**
	 * 交易流水主键Session Key
	 */
	public static final String KEY_IDS_SESSOIN = "KEY_IDS_SESSOIN";

	/**
	 * 用户密码加密后截取起始位置
	 */
	public static final int PASSWORD_FROM = 3;

	/**
	 * 用户密码加密后截取终止位置
	 */
	public static final int PASSWORD_END = 27;

	/**
	 * 新建用户或者重置密码收后的默认密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";

	/**
	 * 系统加载时用于存储所有URL信息的Key
	 */
	public static final String ALL_URL_LIST = "ALL_URL_LIST";

	/**
	 * 系统加载时用于存储当前用户所有菜单权限信息的Key
	 */
	public static final String USER_SESSOIN_PRIVATE = "USER_SESSOIN_PRIVATE";

	/**
	 * 远程连接Shell服务器IP地址
	 */
	public static final String SSH_IP = "SSH_IP";

	/**
	 * 远程连接Shell服务器登录用户名
	 */
	public static final String SSH_USER = "SSH_USER";

	/**
	 * 远程连接Shell服务器登录密码
	 */
	public static final String SSH_PWD = "SSH_PWD";

	/**
	 * 远程连接Shell服务器执行的命令
	 */
	public static final String SSH_CMD = "SSH_CMD";

	/*
	 * ****************************************常用ERROR_CODE定义*******************
	 */
	/**
	 * 常用ERROR CODE:Session超时
	 */
	public static final String ERROR_CODE_SESSION_TIMEOUT = "SESSION_TIMEOUT";

	/**
	 * 常用ERROR CODE:没有权限
	 */
	public static final String ERROR_CODE_NO_ACCESS = "NO_ACCESS";

	/**
	 * 常用ERROR CODE:未知异常
	 */
	public static final String ERROR_CODE_UNKNOWN_EXCEPTIONS = "UNKNOWN_EXCEPTIONS";

	/**
	 * 常用ERROR CODE:操作成功
	 */
	public static final String ERROR_CODE_SUCCESS = "SUCCESS";

	/**
	 * 常用ERROR CODE:操作失败
	 */
	public static final String ERROR_CODE_ERROR = "ERROR";

	/**
	 * 常用ERROR CODE:记录已存在
	 */
	public static final String ERROR_CODE_EXISTED = "EXISTED";

	/**
	 * 常用ERROR CODE:记录已确认
	 */
	public static final String ERROR_CODE_HAS_CONFIRMED = "CONFIRMED";

	/**
	 * 常用ERROR CODE:记录超长
	 */
	public static final String ERROR_CODE_OVER_LENGTH = "OVER_LENGTH";

	/**
	 * Excel文件导入结果ERROR CODE：导入成功
	 */
	public static final String ERROR_CODE_IMPORT_FILE_SUCCESS = "SUCCESS";

	/**
	 * Excel文件导入结果ERROR CODE：导入失败
	 */
	public static final String ERROR_CODE_IMPORT_FILE_ERROR = "ERROR";

	/**
	 * 日志类型：S(系统日志)
	 */
	public static final String LOG_TYPE_SYS = "S";
	/**
	 * 日志类型：B(操作日志)
	 */
	public static final String LOG_TYPE_BIZ = "B";

	/**
	 * 常量：""(空字符串)
	 */
	public static final String CONSTANT_NULL_STRING = "";

	/**
	 * 常量：" "(空格)
	 */
	public static final String CONSTANT_KG_STRING = " ";

	/**
	 * 常量：分隔符"."(点)
	 */
	public static final String CONSTANT_SPLIT_POINT = ".";

	/**
	 * 常量：分隔符","(逗号)
	 */
	public static final String CONSTANT_SPLIT_DH = ",";

	/**
	 * 常量：分隔符";"(分号)
	 */
	public static final String CONSTANT_SPLIT_FH = ";";

	/**
	 * 常量：Y
	 */
	public static final String CONSTANT_Y = "Y";

	/**
	 * 常量：N
	 */
	public static final String CONSTANT_N = "N";

	/**
	 * 常量："0"(字符串)
	 */
	public static final String CONSTANT_0 = "0";

	/**
	 * 常量："1"(字符串)
	 */
	public static final String CONSTANT_1 = "1";

	/**
	 * 常量："2"(字符串)
	 */
	public static final String CONSTANT_2 = "2";

	/**
	 * 常量："3"(字符串)
	 */
	public static final String CONSTANT_3 = "3";

	/**
	 * 常量："4"(字符串)
	 */
	public static final String CONSTANT_4 = "4";

	/**
	 * 常量："5"(字符串)
	 */
	public static final String CONSTANT_5 = "5";

	/**
	 * 常量："6"(字符串)
	 */
	public static final String CONSTANT_6 = "6";

	/**
	 * 常量："7"(字符串)
	 */
	public static final String CONSTANT_7 = "7";

	/**
	 * 常量："8"(字符串)
	 */
	public static final String CONSTANT_8 = "8";

	/**
	 * 常量："9"(字符串)
	 */
	public static final String CONSTANT_9 = "9";

	/**
	 * 常量："10"(字符串)
	 */
	public static final String CONSTANT_10 = "10";

	/**
	 * Ajax返回信息中的数据列表属性名称:DATASET
	 */
	public static final String DATASET_KEY = "DATASET";

	/**
	 * Ajax返回信息中的JQuery EasyUI专用数据列表属性名称:rows
	 */
	public static final String ROWS_KEY = "rows";

	/**
	 * Ajax的返回代码属性名称:ERROR_CODE
	 */
	public static final String ERROR_CODE_KEY = "ERROR_CODE";

	/**
	 * Ajax的返回信息属性名称:ERROR_MESSAGE
	 */
	public static final String ERROR_MESSAGE_KEY = "ERROR_MESSAGE";

	/**
	 * Ajax的分页信息属性名称:PAGE
	 */
	public static final String PAGE_KEY = "PAGE";

	/**
	 * 常用字段拼接符:@@@@
	 */
	public static final String SPLIT_STR = "@@@@";

	/**
	 * 行号
	 */
	public static final String ROW_NUM = "EXCEL_ROW_NUM";

	/**
	 * 系统EXCEL下载限制条数系统参数名称:VATG_DOWNLOAD_COUNT_MAX
	 */
	public static final String VATG_DOWNLOAD_COUNT_MAX = "VATG_DOWNLOAD_COUNT_MAX";

	/* *********************************系统数据库直连方式************************* */

	/**
	 * 数据库直连方式：jdbc连接
	 */
	public static final String DB_CONNECT_TYPE_JDBC = "jdbc";

	/**
	 * 数据库直连方式：jndi连接
	 */
	public static final String DB_CONNECT_TYPE_JNDI = "jndi";
}
