package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.constants.SysConstants;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;


public class SysStringUtils {

	/**
	 * 通过加密并且截取获得的字符串
	 * 
	 * @param code
	 * @return EncryptCode
	 */
//	public static String getEncryptCode(String code) {
//		return SysStringUtils.MD5(code).substring(SysConstants.PASSWORD_FROM, SysConstants.PASSWORD_END);
//	}

	/**
	 * 功能说明:MD5加密,并且截取加密后的字符串,32位
	 * 
	 * @param inStr
	 * @return 截取后的加密字符串
	 */
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return SysConstants.CONSTANT_NULL_STRING;
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append(SysConstants.CONSTANT_0);
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 设置下载文件中文件的名称
	 * 
	 * @param filename
	 * @param request
	 * @return
	 */
	public static String encodeFilename(String filename, HttpServletRequest request) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		try {
			// IE浏览器下导出文件名转码处理
			if ((null != agent && -1 != agent.indexOf("MSIE")) // IE11以前版本浏览器信息识别码-MSIE
					|| (null != agent && -1 != agent.indexOf("Trident"))) {// IE11版本浏览器信息识别码-Trident
				String newFileName = URLEncoder.encode(filename, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}

			// 火狐,Chrome等浏览器下导出文件名转码处理
			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
				return MimeUtility.encodeText(filename, "UTF-8", "B");

			return filename;
		} catch (Exception ex) {
			return filename;
		}
	}

	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", SysConstants.CONSTANT_NULL_STRING);
	}

	public static String arrayToString(List<String> list) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			bf.append(list.get(i) + ",");
		}
		return bf.substring(0, bf.lastIndexOf(","));
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static int getStringBytesLength(String str) throws UnsupportedEncodingException {
		if (str == null){
			return 0;
		}else{
			return str.getBytes("UTF-8").length;
		}
	}
}
