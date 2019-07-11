package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.constants.SysConstants;

import java.util.Random;


public class SysUtils {

	/**
	 * 功能：获取随机随机数
	 * 
	 * @param startNum
	 *            开始数字
	 * @param endNum
	 *            结束数字
	 * @return 随机的到的结果
	 */
	public static int Random(int startNum, int endNum) {
		Random random = new Random();
		int result = random.nextInt(endNum) % (endNum - startNum + 1) + startNum;
		return result;
	}

	/**
	 * 功能：将传入的数字前面自动补0,以达到指定的长度
	 * 
	 * @param num
	 *            需要补0的数字
	 * @param length
	 *            要达到的指定长度
	 * @return 补全0的结果（字符串）
	 */
	public static String PadLeft(int num, int length) {
		String result = String.format("%0" + length + "d", num);
		return result;
	}

	/**
	 * 功能：判断传入的参数是否为null
	 * 
	 * @param obj
	 *            需要校验的对象
	 * @return true:为null false::不为null
	 */
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：判断传入的参数是否为null或者空字符串
	 * 
	 * @param str
	 *            需要校验的字符串
	 * @return true:为null或者空字符串 false::不为null且空字符串
	 */
	public static boolean isNullOrNullString(String str) {
		if (str == null || SysConstants.CONSTANT_NULL_STRING.equals(str)) {
			return true;
		} else {
			return false;
		}
	}
}
