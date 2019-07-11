
package com.ai.ringball.controller.sys;

import com.ai.ringball.dao.sys.SysUser;
import com.ai.ringball.framework.base.CommonBizService;
import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.utility.common.JsonUtils;
import com.ai.ringball.framework.utility.common.ResultUtils;
import com.ai.ringball.framework.utility.common.ThreadDataUtils;
import com.ai.ringball.shiro.UserRealm;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@SessionAttributes({ SysConstants.USER_SESSOIN })

@Controller
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);

//	@Autowired
//	private SysOrganizationService sysOrganizationService;
    @Autowired
    private CommonBizService commonBizService;

	@RequestMapping(value = "/prepareRequestSession", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String prepareRequestSession(String orgid, HttpServletRequest request) throws ParseException {

		// 向Session中存储可操作分行的相关信息
//		SysOrganization sysOrganization = sysOrganizationService.getOrgByPrimaryKey(orgid);
//		request.getSession().setAttribute(SysConstants.ORG_SESSOIN, sysOrganization);

		SysUser user = ThreadDataUtils.getThreadUser();

		Subject currentUser = SecurityUtils.getSubject();

		try {
			System.out.println("-------------VAT开票系统登录信息校验---------------");

			RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
			UserRealm usm = (UserRealm) rsm.getRealms().iterator().next();
			usm.clearAllCachedAuthorizationInfo();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			currentUser.login(token);

		} catch (UnknownAccountException uae) {

			logger.error("username wasn't in the system.");

		} catch (IncorrectCredentialsException ice) {

			logger.error("password didn't match.");

		} catch (LockedAccountException lae) {

			logger.error("LockedAccountException condition.");

		} catch (AuthenticationException ae) {

			logger.error("unexpected condition.");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

        // 获取系统当前工作日
//        String date = commonBizService.getOdsBranchBusiDt(sysOrganization.getOrgcode());
//        request.getSession().setAttribute(SysConstants.SYS_WORK_DATE_SESSOIN, date);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = ResultUtils.createSuccessResult(null, null);
		return JsonUtils.parseFromObject(resultMap);
	}

	@RequestMapping(value = "/isSessionAlive", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String isSessionAlive(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = ResultUtils.createSuccessResult(null, null);
		return JsonUtils.parseFromObject(resultMap);
	}
}
