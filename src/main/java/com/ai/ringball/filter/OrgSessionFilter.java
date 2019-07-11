package com.ai.ringball.filter;

import com.ai.ringball.dao.sys.SysOrganization;
import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.utility.common.SysStringUtils;
import com.ai.ringball.framework.utility.common.ThreadDataUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrgSessionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession();
		String url = httpServletRequest.getRequestURI();
		if (url.endsWith("login") || url.endsWith("selectLoginOptOrg")
				|| url.endsWith("changePassword")
				|| url.endsWith("getAllUserOrgForFilter")
				|| url.endsWith("updateUserDefinePassword")
				|| url.endsWith("prepareRequestSession")
				|| !(url.indexOf("/images/") < 0)
				|| !(url.indexOf("/css/") < 0) || !(url.indexOf("/js/") < 0)) {
		} else {
			SysOrganization orgObject = (SysOrganization) session.getAttribute(SysConstants.ORG_SESSOIN);
			if (orgObject != null) {
				ThreadDataUtils.setThreadOrg(orgObject);
			}
		}

		ThreadDataUtils.setThreadIp(SysStringUtils.getIp(httpServletRequest));
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
