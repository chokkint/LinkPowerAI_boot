package com.ai.ringball.inteceptor;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AutoStartServlet extends HttpServlet {

	private static final long serialVersionUID = 8403624290337225646L;
	private static final Logger logger = Logger.getLogger(AutoStartServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
		logger.debug("init url list...");
		setSystemUrl(getServletContext());
	}

	public static void setSystemUrl(ServletContext servletContext) {

		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//		SysUrlService sysUrlService = (SysUrlService) context.getBean("sysUrlService");

//		Map<String, Map<String, String>> UrlMap = new HashMap<String, Map<String, String>>();
//		List<SysUrl> sysUrlList = sysUrlService.selectAll();
//		Map<String, String> map = null;
//		for (SysUrl sysUrl : sysUrlList) {
//			map = new HashMap<String, String>();
//			map.put("urlId", sysUrl.getUrlid());
//			map.put("urlName", sysUrl.getUrlname());
//			UrlMap.put(sysUrl.getUrllink(), map);
//		}
//
//		servletContext.setAttribute(SysConstants.ALL_URL_LIST, UrlMap);
	}
}
