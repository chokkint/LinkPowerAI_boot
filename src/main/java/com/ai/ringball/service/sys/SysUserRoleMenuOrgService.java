package com.ai.ringball.service.sys;

import com.ai.ringball.dao.sys.SysOrganization;
import com.ai.ringball.mapper.sys.SysUserRoleMenuOrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserRoleMenuOrgService {

	@Autowired
	private SysUserRoleMenuOrgMapper sysUserRoleMenuOrgMapper;

	public List<SysOrganization> selectAllUserOrg(String userid, String roleid, String menuid, boolean needRootOrg) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysOrganization> sysOrganizationList = new ArrayList<SysOrganization>();
		map.put("userid", userid);
		map.put("roleid", roleid);
		map.put("menuid", menuid);
		if (needRootOrg) {
			map.put("hasOrgRoot", "true");
		}
		sysOrganizationList = sysUserRoleMenuOrgMapper.selectAllUserOrg(map);
		return sysOrganizationList;
	}
}
