package com.ai.ringball.dao.sys;

import com.ai.ringball.framework.annotation.CommentTag;
import com.ai.ringball.framework.base.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Alias
 * 
 */
public class SysUser extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5119534209013135262L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// @CommentTag(comment = "用户ID")
	private String userid;

	// @CommentTag(comment = "用户分行ID")
	private String orgid;

	@CommentTag(comment = "用户名")
	private String username;

	// @CommentTag(comment = "用户密码")
	private String password;

	private BigDecimal userorder;

	@CommentTag(comment = "部门编号")
	private String departcode;

	@CommentTag(comment = "用户状态")
	private String userstatus;

	// @CommentTag(comment = "创建时间")
	private Date createtime;

	@CommentTag(comment = "更新时间")
	private Date updatetime;

	@CommentTag(comment = "部门名称")
	private String departname;

	@CommentTag(comment = "用户中文名")
	private String userrealname;

	@CommentTag(comment = "用户锁定状态")
	private String isLocked;

	@CommentTag(comment = "用户连续输错密码次数")
	private int pwdWrongCount;

	@CommentTag(comment = "最后一次更新密码时间")
	private Date lastChgPwdDate;

	@CommentTag(comment = "最后一次登录时间")
	private Date lastLoginDate;

	// @CommentTag(comment ="删除标志(0:正常 1:删除)")
	private String delFlg;

	private String logId;

	private String roleList;

	// 用户所拥有的所有角色名称(用逗号连接)
	@CommentTag(comment = "角色")
	private String rolenameAll;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getUserorder() {
		return userorder;
	}

	public void setUserorder(BigDecimal userorder) {
		this.userorder = userorder;
	}

	public String getDepartcode() {
		return departcode;
	}

	public void setDepartcode(String departcode) {
		this.departcode = departcode;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	public String getUserrealname() {
		return userrealname;
	}

	public void setUserrealname(String userrealname) {
		this.userrealname = userrealname;
	}

	public String getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}

	public int getPwdWrongCount() {
		return pwdWrongCount;
	}

	public void setPwdWrongCount(int pwdWrongCount) {
		this.pwdWrongCount = pwdWrongCount;
	}

	public Date getLastChgPwdDate() {
		return lastChgPwdDate;
	}

	public void setLastChgPwdDate(Date lastChgPwdDate) {
		this.lastChgPwdDate = lastChgPwdDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getRoleList() {
		return roleList;
	}

	public void setRoleList(String roleList) {
		this.roleList = roleList;
	}

	public String getRolenameAll() {
		return rolenameAll;
	}

	public void setRolenameAll(String rolenameAll) {
		this.rolenameAll = rolenameAll;
	}

	@Override
	public String getEntityId() {
		return this.userid;
	}
}