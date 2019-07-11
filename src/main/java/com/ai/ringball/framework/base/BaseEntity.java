package com.ai.ringball.framework.base;

/**
 * 所有POJO的基类
 * 
 * @author wangchaochao
 * 
 */
public abstract class BaseEntity {

	/**
	 * 功能：获得当前Entity的ID(String), 如果是多字段联合主键，需要吧字段拼接成字符串返回
	 * 
	 * @return ID
	 */
	public abstract String getEntityId();

}
