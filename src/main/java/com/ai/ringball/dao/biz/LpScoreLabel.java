package com.ai.ringball.dao.biz;

import com.ai.ringball.framework.annotation.CommentTag;

import java.util.Date;


public class LpScoreLabel {
    /**
     * UUID COLUMN:ID
     */
    @CommentTag(comment ="UUID")
    private String id;

    /**
     * 灵豹分ID COLUMN:SCORE_ID
     */
    @CommentTag(comment ="灵豹分ID")
    private String scoreId;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL1
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel1;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL2
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel2;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL3
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel3;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL4
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel4;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL5
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel5;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL6
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel6;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL7
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel7;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL8
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel8;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL9
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel9;

    /**
     * 灵豹分打分关键标签 COLUMN:SCORE_LABEL10
     */
    @CommentTag(comment ="灵豹分打分关键标签")
    private String scoreLabel10;

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

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getScoreLabel1() {
        return scoreLabel1;
    }

    public void setScoreLabel1(String scoreLabel1) {
        this.scoreLabel1 = scoreLabel1;
    }

    public String getScoreLabel2() {
        return scoreLabel2;
    }

    public void setScoreLabel2(String scoreLabel2) {
        this.scoreLabel2 = scoreLabel2;
    }

    public String getScoreLabel3() {
        return scoreLabel3;
    }

    public void setScoreLabel3(String scoreLabel3) {
        this.scoreLabel3 = scoreLabel3;
    }

    public String getScoreLabel4() {
        return scoreLabel4;
    }

    public void setScoreLabel4(String scoreLabel4) {
        this.scoreLabel4 = scoreLabel4;
    }

    public String getScoreLabel5() {
        return scoreLabel5;
    }

    public void setScoreLabel5(String scoreLabel5) {
        this.scoreLabel5 = scoreLabel5;
    }

    public String getScoreLabel6() {
        return scoreLabel6;
    }

    public void setScoreLabel6(String scoreLabel6) {
        this.scoreLabel6 = scoreLabel6;
    }

    public String getScoreLabel7() {
        return scoreLabel7;
    }

    public void setScoreLabel7(String scoreLabel7) {
        this.scoreLabel7 = scoreLabel7;
    }

    public String getScoreLabel8() {
        return scoreLabel8;
    }

    public void setScoreLabel8(String scoreLabel8) {
        this.scoreLabel8 = scoreLabel8;
    }

    public String getScoreLabel9() {
        return scoreLabel9;
    }

    public void setScoreLabel9(String scoreLabel9) {
        this.scoreLabel9 = scoreLabel9;
    }

    public String getScoreLabel10() {
        return scoreLabel10;
    }

    public void setScoreLabel10(String scoreLabel10) {
        this.scoreLabel10 = scoreLabel10;
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