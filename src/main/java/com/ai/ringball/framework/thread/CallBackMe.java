package com.ai.ringball.framework.thread;

import java.util.Map;

/**
 * 
 * 项目名称：TempCode <br>
 * <br>
 * 
 * 类名称：CallBack3 <br>
 * <br>
 * 
 * 创建人：Chokkint <br>
 * <br>
 * 
 * 创建时间：2018-10-1 下午11:06:19 <br>
 * <br>
 * 
 * 版本：1.0 <br>
 * <br>
 * 
 * 功能描述：回调接口，类似Hibernate中的 HibernateCallback<T> 这个类一样.
 */
public interface CallBackMe<T> {
	T call(String orgCode, Map.Entry<String, Map<String, Object>> entry);
}