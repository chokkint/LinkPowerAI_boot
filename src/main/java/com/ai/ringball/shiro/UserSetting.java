package com.ai.ringball.shiro;

import com.ai.ringball.framework.base.BaseJsonController;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class UserSetting extends FormAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(BaseJsonController.class);

	/*
	 * 主要是针对登入成功的处理方法。对于请求头是AJAX的之间返回JSON字符串。
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {// 不是ajax请求
			issueSuccessRedirect(request, response);
		} else {
			httpServletResponse.setCharacterEncoding("UTF-8");
			PrintWriter out = httpServletResponse.getWriter();
			out.println("{success:true,message:'登入成功'}");
			out.flush();
			out.close();
		}
		return false;
	}

	/**
	 * 主要是处理登入失败的方法
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {// 不是ajax请求
			setFailureAttribute(request, e);
			return true;
		}
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String message = e.getClass().getSimpleName();
			if ("IncorrectCredentialsException".equals(message)) {
				out.println("{success:false,message:'密码错误'}");
			} else if ("UnknownAccountException".equals(message)) {
				out.println("{success:false,message:'账号不存在'}");
			} else if ("LockedAccountException".equals(message)) {
				out.println("{success:false,message:'账号被锁定'}");
			} else {
				out.println("{success:false,message:'未知错误'}");
			}
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	/**
	 * 所有请求都会经过的方法。
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if (logger.isTraceEnabled()) {
					logger.trace("Login submission detected.  Attempting to execute login.");
				}
				// if ("XMLHttpRequest"
				// .equalsIgnoreCase(((HttpServletRequest) request)
				// .getHeader("X-Requested-With"))) {// 不是ajax请求
				// String vcode = request.getParameter("vcode");
				// HttpServletRequest httpservletrequest = (HttpServletRequest)
				// request;
				// String vvcode = (String) httpservletrequest
				// .getSession()
				// .getAttribute(
				// com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
				// if (vvcode == null || "".equals(vvcode)
				// || !vvcode.equals(vcode)) {
				// response.setCharacterEncoding("UTF-8");
				// PrintWriter out = response.getWriter();
				// out.println("{success:false,message:'验证码错误'}");
				// out.flush();
				// out.close();
				// return false;
				// }
				// }
				return executeLogin(request, response);
			} else {
				if (logger.isTraceEnabled()) {
					logger.trace("Login page view.");
				}
				// allow them to see the login page ;)
				return true;
			}
		} else {
			if (logger.isTraceEnabled()) {
				logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " + "Authentication url [" + getLoginUrl() + "]");
			}
			if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {// 不是ajax请求
				// saveRequestAndRedirectToLogin(request, response);
				return true;
			} else {
				response.setCharacterEncoding("UTF-8");
				// Map<String, Object> resultMap = new HashMap<String,
				// Object>();
				// String errorCode =
				// ErrorCodeConstants.MSG_FRAMEWORK_SESSIONTIMEOUT_EXCEPTION;
				// resultMap.put("errorCode", errorCode);
				//// resultMap.put(ERROR_MESSAGE,
				//// PropertyConfigurer.getErrorMessage(errorCode));
				// resultMap.put("errorMessage",
				// "登陆已经过期，即将重新登陆！");
				// resultMap.put("data", null);
				// String test= JsonUtils.parseFromObject(resultMap);
				// PrintWriter out = response.getWriter();
				// out.println(test);
				// out.flush();
				// out.close();
				// *****************moidfy by cjq 20160921***********
				// PrintWriter out = response.getWriter();
				// StringBuilder builder = new StringBuilder();
				// HttpServletRequest httpServletRequest = (HttpServletRequest)
				// request;
				// String uri = httpServletRequest.getRequestURI();
				// uri.substring(0, uri.indexOf("/", 1));
				// builder.append("<script type=\"text/javascript\"
				// charset=\"UTF-8\">");
				// builder.append("alert(\"页面过期，请重新登录\");");
				// builder.append("window.top.location.href=\"");
				// builder.append(uri.substring(0, uri.indexOf("/",
				// 1))+"/jsp/login");
				// builder.append("\";</script>");
				// out.print(builder.toString());
				// out.flush();
				// out.close();
				// return true;
			}
		}
		return false;
	}

}
