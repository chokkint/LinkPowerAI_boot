package com.ai.ringball.shiro;

import com.ai.ringball.dao.sys.SysUser;
import com.ai.ringball.framework.utility.common.ThreadDataUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		/* 这里编写授权代码 */
		// String userName = principal.getPrimaryPrincipal().toString();
		String rolelist = ThreadDataUtils.getThreadUser().getRoleList();
		String[] lists = rolelist.split(",");
		Set<String> roleNames = new HashSet<String>();
		for (String role : lists) {
			roleNames.add(role);
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		if (!StringUtils.isBlank(userName)) {
			SysUser user = ThreadDataUtils.getThreadUser();
			if (user != null) {
				return new SimpleAuthenticationInfo(userName, user.getPassword(), user.getUserid());
			}
		}
		return null;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(Object principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
}
