package com.ai.ringball.dao.biz;

import com.ai.ringball.framework.annotation.CommentTag;

import java.util.Date;


public class LpScoreResult {
    /**
     * UUID COLUMN:ID
     */
    @CommentTag(comment ="UUID")
    private String id;

    /**
     * 客户ID COLUMN:CUST_ID
     */
    @CommentTag(comment ="客户ID")
    private String custId;

    /**
     * 灵豹分 COLUMN:SCORE
     */
    @CommentTag(comment ="灵豹分")
    private Short score;

    /**
     * 0:不是最新评分；1:最新评分 COLUMN:LATEST_FLG
     */
    @CommentTag(comment ="0:不是最新评分；1:最新评分")
    private String latestFlg;

    /**
     * 灵豹分模型版本号（默认1.0） COLUMN:MODULE_VER
     */
    @CommentTag(comment ="灵豹分模型版本号（默认1.0）")
    private String moduleVer;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    public String getLatestFlg() {
        return latestFlg;
    }

    public void setLatestFlg(String latestFlg) {
        this.latestFlg = latestFlg;
    }

    public String getModuleVer() {
        return moduleVer;
    }

    public void setModuleVer(String moduleVer) {
        this.moduleVer = moduleVer;
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
}