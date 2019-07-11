package com.ai.ringball.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogTag {

	public static final String OP_TYPE_INSERT = "0";

	public static final String OP_TYPE_UPDATE = "1";

	public static final String OP_TYPE_DELETE = "2";

	public static final String OP_TYPE_REVIEW = "3";

	public static final String OP_TYPE_CANCEL_REVIEW = "4";

	public static final String OP_TYPE_CONFIRM = "5";

	public static final String OP_TYPE_CANCEL_CONFIRM = "6";

	public static final String OP_TYPE_REVIEW_ALL = "7";

	public static final String OP_TYPE_RESET_PASSWORD = "8";

	public static final String OP_TYPE_ENABLE_USER = "9";

	public static final String OP_TYPE_UNLOCK_USER = "10";

	public static final String OP_TYPE_OTST_LEGFILE_EXTENSION = "11";

	public static final String OP_TYPE_OTST_LEGFILE_CONFIRM = "12";

	public static final String OP_TYPE_OTST_LEGFILE_EXPIRE = "13";

	public static final String OP_TYPE_OTST_LEGFILE_EXPIRE_UPDATE = "14";

	public static final String OP_TYPE_OTST_LEGFILE_BATCH_EXTENSION = "15";

	public static final String OP_TYPE_OTST_LEGFILE_BATCH_EXPIRE = "16";

	public static final String OP_TYPE_DOWNLOAD = "17";

	public static final String OP_TYPE_BASE_RATE_BATCH_DELETE = "21";
	
	public static final String OP_TYPE_VAT_FTUPLOAD="22";
	
	public static final String OP_TYPE_VAT_DAYLAST="23";
	
	public static final String OP_TYPE_VAT_CANCELOFDAYLAST="24";

	public static final String OP_TYPE_VAT_UPLOADBACK="26";
	
	public static final String OP_TYPE_VATG_UPDATE = "43";

	public static final String OP_TYPE_VATG_DELETE = "44";
	
	public static final String OP_TYPE_VATG_REVIEW = "45";
	
	public static final String OP_TYPE_VATG_DOWNLOAD = "46";
	
	public static final String OP_TYPE_VATG_UPLOAD = "47";
	
	public static final String OP_TYPE_VATG_MATCH = "48";
	
	public static final String OP_TYPE_VATG_MANUAL_INVOICE = "49";
	
	public static final String OP_TYPE_VATG_RENEW_INVOICE = "50";
	
	public static final String OP_TYPE_VATG_CONFIRM_UPDATE = "51";

	public static final String SYS_TYPE_BRANCH_USER = "机构人员管理";

	public static final String SYS_TYPE_ROLE = "角色管理";

	public static final String SYS_TYPE_OTHER = "其他系统日志";

	public static final String SYS_TYPE_ROLE_PRIVILIAGE = "角色权限管理";
	
	public static final String SYS_TYPE_USER_PRIVILIAGE = "人员权限管理";

	String logType() default "";

	String busType() default "";

	String opType() default "";

	boolean isSingle() default true;

	//业务数据所在参数中的下标
	int dataParamIndex() default 0;

	// 额外参数1所在参数中的下标
	int dataParam2Index() default 1;

	// 额外参数2所在参数中的下标
	int dataParam3Index() default 2;
	
	// 额外参数3所在参数中的下标
	int dataParam4Index() default 3;
	
	// 额外参数4所在参数中的下标
	int dataParam5Index() default 4;
	
	// 额外参数5所在参数中的下标
	int dataParam6Index() default 5;
}
