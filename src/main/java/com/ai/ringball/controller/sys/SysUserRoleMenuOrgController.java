
package com.ai.ringball.controller.sys;

import com.ai.ringball.dao.sys.SysOrganization;
import com.ai.ringball.framework.base.BaseJsonController;
import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.utility.common.JsonUtils;
import com.ai.ringball.framework.utility.common.ResultUtils;
import com.ai.ringball.service.sys.SysUserRoleMenuOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes({ SysConstants.USER_SESSOIN })

@Controller
public class SysUserRoleMenuOrgController extends BaseJsonController {

	@Autowired
	private SysUserRoleMenuOrgService sysUserRoleMenuOrgService;

	/**
	 * 功能：防止非法入侵系统专用获取用户权限列表接口
	 * 
	 * @param userid
	 * @param menuid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAllUserOrgForFilter", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getAllUserOrgForFilter(String userid, String menuid, HttpServletRequest request) {
		List<SysOrganization> sysOrganizationList = new ArrayList<SysOrganization>();
		sysOrganizationList = sysUserRoleMenuOrgService.selectAllUserOrg(userid, null, menuid, false);
		
		return JsonUtils.parseFromObject(ResultUtils.createSuccessResult(sysOrganizationList, null));
	}
}
