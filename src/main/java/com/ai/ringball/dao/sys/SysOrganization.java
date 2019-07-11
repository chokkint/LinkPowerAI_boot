package com.ai.ringball.dao.sys;

import java.io.Serializable;
import java.util.Date;

public class SysOrganization implements Serializable {

	private static final long serialVersionUID = -5119534209013135962L;

	private String orgid;

	private String orgname;

	private String orgdescription;

	private String orgcode;

	private String orgadress;

	private String orgcontacts;

	private String orgphoneno;

	private String orglevel;

	private String orgparentid;

	private String orgstatus;

	private Date createtime;

	private Date updatetime;

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgdescription() {
		return orgdescription;
	}

	public void setOrgdescription(String orgdescription) {
		this.orgdescription = orgdescription;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgadress() {
		return orgadress;
	}

	public void setOrgadress(String orgadress) {
		this.orgadress = orgadress;
	}

	public String getOrgcontacts() {
		return orgcontacts;
	}

	public void setOrgcontacts(String orgcontacts) {
		this.orgcontacts = orgcontacts;
	}

	public String getOrgphoneno() {
		return orgphoneno;
	}

	public void setOrgphoneno(String orgphoneno) {
		this.orgphoneno = orgphoneno;
	}

	public String getOrglevel() {
		return orglevel;
	}

	public void setOrglevel(String orglevel) {
		this.orglevel = orglevel;
	}

	public String getOrgparentid() {
		return orgparentid;
	}

	public void setOrgparentid(String orgparentid) {
		this.orgparentid = orgparentid;
	}

	public String getOrgstatus() {
		return orgstatus;
	}

	public void setOrgstatus(String orgstatus) {
		this.orgstatus = orgstatus;
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
}