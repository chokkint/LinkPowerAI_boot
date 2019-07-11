package com.ai.ringball.dao.biz;

import com.ai.ringball.framework.annotation.CommentTag;

import java.util.Date;


public class LpMgntSalesMst {
    /**
     * ID COLUMN:ID
     */
    @CommentTag(comment ="ID")
    private String id;

    /**
     * 登录ID，一般为手机号 COLUMN:LOGIN_ID
     */
    @CommentTag(comment ="登录ID，一般为手机号")
    private String loginId;

    /**
     * 业务员名称 COLUMN:USER_NM
     */
    @CommentTag(comment ="业务员名称")
    private String userNm;

    /**
     * 登录密码 COLUMN:LOGIN_PWD
     */
    @CommentTag(comment ="登录密码")
    private String loginPwd;

    /**
     * 最近登录日期 COLUMN:LAST_LOGIN
     */
    @CommentTag(comment ="最近登录日期")
    private Date lastLogin;

    /**
     * 头像(BASE64编码) COLUMN:HEAD_IMG
     */
    @CommentTag(comment ="头像(BASE64编码)")
    private String headImg;

    /**
     * 手机号码 COLUMN:TEL_NO
     */
    @CommentTag(comment ="手机号码")
    private String telNo;

    /**
     * 微信号 COLUMN:WECHAT_NO
     */
    @CommentTag(comment ="微信号")
    private String wechatNo;

    /**
     * 简介(500个英文或者100个汉字) COLUMN:PROFILE
     */
    @CommentTag(comment ="简介(500个英文或者100个汉字)")
    private String profile;

    /**
     * 性别(M:女 F:男) COLUMN:SEX
     */
    @CommentTag(comment ="性别(M:女 F:男)")
    private String sex;

    /**
     * 生日 COLUMN:BIRTHDAY
     */
    @CommentTag(comment ="生日")
    private Date birthday;

    /**
     * 所在城市 COLUMN:LOCATION_CITY
     */
    @CommentTag(comment ="所在城市")
    private String locationCity;

    /**
     * 详细地址 COLUMN:LOCATION_DETAIL
     */
    @CommentTag(comment ="详细地址")
    private String locationDetail;

    /**
     * 所属公司 COLUMN:COMPANY
     */
    @CommentTag(comment ="所属公司")
    private String company;

    /**
     * 所属部门 COLUMN:DEPARTMENT
     */
    @CommentTag(comment ="所属部门")
    private String department;

    /**
     * 职位名称 COLUMN:POSITION_TITLE
     */
    @CommentTag(comment ="职位名称")
    private String positionTitle;

    /**
     * 开始工作日期 COLUMN:WORK_DATE_START
     */
    @CommentTag(comment ="开始工作日期")
    private Date workDateStart;

    /**
     * 员工号 COLUMN:USER_SN
     */
    @CommentTag(comment ="员工号")
    private String userSn;

    /**
     * 创建日期 COLUMN:CREATE_DATE
     */
    @CommentTag(comment ="创建日期")
    private Date createDate;

    /**
     * 更新日期 COLUMN:UPDATE_DATE
     */
    @CommentTag(comment ="更新日期")
    private Date updateDate;

    /**
     * 0:无效；1:有效 COLUMN:STATUS
     */
    @CommentTag(comment ="0:无效；1:有效")
    private String status;

    /**
     * 0:未使用；1:使用 COLUMN:ISUSED
     */
    @CommentTag(comment ="0:未使用；1:使用")
    private String isused;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public Date getWorkDateStart() {
        return workDateStart;
    }

    public void setWorkDateStart(Date workDateStart) {
        this.workDateStart = workDateStart;
    }

    public String getUserSn() {
        return userSn;
    }

    public void setUserSn(String userSn) {
        this.userSn = userSn;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsused() {
        return isused;
    }

    public void setIsused(String isused) {
        this.isused = isused;
    }
}