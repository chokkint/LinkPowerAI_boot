package com.ai.ringball.mapper.sys;

import com.ai.ringball.dao.sys.SysOrganization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserRoleMenuOrgMapper {
	
	List<SysOrganization> selectAllUserOrg(Map<String, Object> map);
}