package com.ai.ringball.framework.utility.common;

import com.ai.ringball.dao.sys.SysOrganization;
import com.ai.ringball.dao.sys.SysUser;

import java.util.HashMap;
import java.util.Map;

public class ThreadDataUtils {

	public static final ThreadLocal<SysUser> userThreadCache = new ThreadLocal<SysUser>();

	public static final ThreadLocal<SysOrganization> orgThreadCache = new ThreadLocal<SysOrganization>();

	public static final ThreadLocal<String> ipThreadCache = new ThreadLocal<String>();

	/**
	 * 线程数据缓存
	 */
	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

	public static SysUser getThreadUser() {
		return userThreadCache.get();
	}

	public static void setThreadUser(SysUser sysUser) {
		userThreadCache.set(sysUser);
	}

	public static SysOrganization getThreadOrg() {
		return orgThreadCache.get();
	}

	public static void setThreadOrg(SysOrganization sysOrganization) {
		orgThreadCache.set(sysOrganization);
	}

	public static String getThreadIp() {
		return ipThreadCache.get();
	}

	public static void setThreadIp(String ip) {
		ipThreadCache.set(ip);
	}

	/**
	 * 根据Key从线程数据缓存中获取数据
	 * 
	 * @param dataKey
	 * @return
	 */
	public static Object getThreadData(String dataKey) {
		Map<String, Object> map = threadLocal.get();
		if (map != null) {
			return map.get(dataKey);
		}
		return null;
	}

	/**
	 * 根据Key把数据保存到数据缓存中
	 * 
	 * @param dataKey
	 * @param data
	 */
	public static void setThreadData(String dataKey, Object data) {
		Map<String, Object> map = threadLocal.get();
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(dataKey, data);
		threadLocal.set(map);
	}
}
