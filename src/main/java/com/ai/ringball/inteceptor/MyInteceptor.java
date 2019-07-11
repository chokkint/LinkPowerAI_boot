package com.ai.ringball.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInteceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(MyInteceptor.class);
	private String[] allowUrls;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3) throws Exception {

		String requestUrl = request.getServletPath();
		request.getSession().removeAttribute(requestUrl);
		logger.debug("==============================afterCompletion========");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestUrl = request.getServletPath();

		logger.debug("==============================preHandle========" + requestUrl);

		for (String url : allowUrls) {
			if (requestUrl.endsWith(url)) {
				return true;
			}
		}
		
//		
//		Object userObject = request.getSession().getAttribute(SysConstants.USER_SESSOIN);
//		Object orgObject = request.getSession().getAttribute(SysConstants.ORG_SESSOIN);
//
//		// 过滤用户Session和机构Session，防止非法入侵
//		if (userObject == null) {
//			throw new SessionTimeOutException();
//		}
//
//		if (!requestUrl.endsWith("selectLoginOptOrg") && !requestUrl.endsWith("changePassword")
//				&& !requestUrl.endsWith("getAllUserOrgForFilter") && !requestUrl.endsWith("updateUserDefinePassword")
//				&& !requestUrl.endsWith("prepareRequestSession")) {
//			if (orgObject == null) {
//				throw new SessionTimeOutException();
//			}
//		}
//
//		String urlid = null;
//		Object urlObject = request.getSession().getServletContext().getAttribute(SysConstants.ALL_URL_LIST);
//		Map<String, Map<String, String>> UrlMap = urlObject != null ? (Map<String, Map<String, String>>) urlObject : new HashMap<String, Map<String, String>>();
//		Map<String, String> urlMap = UrlMap.get(requestUrl);
//		if (urlMap == null)
//			return true;
//		urlid = urlMap.get("urlId");
//
//		Object userPrivateMapObject = request.getSession().getAttribute(SysConstants.USER_SESSOIN_PRIVATE);
//		Map<String, Object> userPrivateMap = userPrivateMapObject != null ? (Map<String, Object>) userPrivateMapObject : new HashMap<String, Object>();
//
//		Object obj = userPrivateMap.get(urlid);
//		if (obj instanceof SysMenuOperate) {
//			SysMenuOperate sysMenuOperate = (SysMenuOperate) obj;
//			request.setAttribute("PAGE_OPERATE_LIST", sysMenuOperate.getSysOperateList());
//		}
//		
		
		return true;
	}
}
