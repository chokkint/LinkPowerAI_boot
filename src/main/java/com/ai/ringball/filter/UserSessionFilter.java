package com.ai.ringball.filter;

import com.ai.ringball.dao.sys.SysUser;
import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.utility.common.SysStringUtils;
import com.ai.ringball.framework.utility.common.ThreadDataUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSessionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession();
		SysUser user = (SysUser) session.getAttribute(SysConstants.USER_SESSOIN);
		if (user != null) {
			ThreadDataUtils.setThreadUser(user);
		}
		ThreadDataUtils.setThreadIp(SysStringUtils.getIp(httpServletRequest));
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
