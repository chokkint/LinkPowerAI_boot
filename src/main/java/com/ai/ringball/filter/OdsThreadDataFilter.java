package com.ai.ringball.filter;

import com.ai.ringball.dao.sys.SysUser;
import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.utility.common.ConvertUtils;
import com.ai.ringball.framework.utility.common.PropertyConfigurer;
import com.ai.ringball.framework.utility.common.SysStringUtils;
import com.ai.ringball.framework.utility.common.ThreadDataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class OdsThreadDataFilter implements Filter {

	@Override
	public void destroy() {

	}

	private static final Logger logger = LoggerFactory.getLogger(OdsThreadDataFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		ServletContext context = httpServletRequest.getSession().getServletContext();
		ServletContext context1 = context.getContext(PropertyConfigurer.getMessage("web.server"));

		if (context1 == null) {
			logger.error("OdsThreadDataFilter context1 is null:", context1);
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>没有权限访问页面</title>");
			out.println("</head>");
			out.println("</html>");
			out.println("<body>");
			out.println("ODS端用户未登录或者已退出，请从ODS端重新登录！");
			out.println("</bidy>");
			out.println("</html>");
			return;
		}
		Object obj = null;
		if (context1 != null && !SysConstants.CONSTANT_NULL_STRING.equals(context1)) {
			try {
				Cookie cookie = getCookieByName(httpServletRequest, "userid");
				if (cookie == null) {
					throw new Exception();
				}
				HttpSession sess = (HttpSession) context1.getAttribute(SysConstants.ODS_SESSOIN + cookie.getValue());

				if (!SysStringUtils.getIp(httpServletRequest).equals(sess.getAttribute(SysConstants.USER_IP))) {
					logger.debug("OdsThreadDataFilter IP is different!");
					throw new Exception();
				}

				obj = sess.getAttribute(SysConstants.USER_SESSOIN);
				Map<String, Object> beanToMap = ConvertUtils.beanToMap(obj);
				String roleList = (String) beanToMap.get("roleList");
				if (roleList.contains(SysConstants.SYS_ROLE_ID_SSGI)) {
					logger.debug("OdsThreadDataFilter USER_SESSOIN is get OK!");
					SysUser sysUser = new SysUser();
					BeanUtils.copyProperties(obj, sysUser);
					httpServletRequest.getSession().setAttribute(SysConstants.USER_SESSOIN, sysUser);
				} else {
					throw new Exception();
				}

			} catch (Exception e) {
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>没有权限访问页面</title>");
				out.println("</head>");
				out.println("</html>");
				out.println("<body>");
				out.println("ODS端用户未登录或者已退出，请从ODS端重新登录！");
				out.println("</bidy>");
				out.println("</html>");
				return;
			}
		}
		if (obj != null) {
			ThreadDataUtils.setThreadData(SysConstants.USER_SESSOIN, obj);
		}
		ThreadDataUtils.setThreadData(SysConstants.USER_IP, SysStringUtils.getIp(httpServletRequest));
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
