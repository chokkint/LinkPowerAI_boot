package com.ai.ringball.dao.biz;

import com.ai.ringball.framework.annotation.CommentTag;
import com.ai.ringball.framework.base.BaseEntity;

import java.util.Date;

public class LpScoreIndexMid extends BaseEntity {
	/**
	 * UUID COLUMN:ID
	 */
	@CommentTag(comment = "UUID")
	private String id;

	/**
	 * 客户ID COLUMN:CUST_ID
	 */
	@CommentTag(comment = "客户ID")
	private String custId;

	/**
	 * 客户ID COLUMN:DATA_DATE
	 */
	@CommentTag(comment = "数据日期")
	private String dataDate;

	/**
	 * 灵豹分类型(0:价值分指标；1:意愿分指标) COLUMN:SCORE_TYPE
	 */
	@CommentTag(comment = "灵豹分类型(0:价值分指标；1:意愿分指标)")
	private String scoreType;

	/**
	 * 指标代码 COLUMN:SCORE_CODE
	 */
	@CommentTag(comment = "指标代码")
	private String scoreCode;

	/**
	 * 指标名称 COLUMN:SCORE_NAME
	 */
	@CommentTag(comment = "指标名称")
	private String scoreName;

	/**
	 * 指标得分 COLUMN:SCORE
	 */
	@CommentTag(comment = "指标得分")
	private Short score;

	/**
	 * 0:不是最新评分；1:最新评分 COLUMN:LATEST_FLG
	 */
	@CommentTag(comment = "0:不是最新评分；1:最新评分")
	private String latestFlg;

	/**
	 * 灵豹分模型版本号（默认1.0） COLUMN:MODULE_VER
	 */
	@CommentTag(comment = "灵豹分模型版本号（默认1.0）")
	private String moduleVer;

	/**
	 * 创建日期 COLUMN:CREATE_DATE
	 */
	@CommentTag(comment = "创建日期")
	private Date createDate;

	/**
	 * 更新日期 COLUMN:UPDATE_DATE
	 */
	@CommentTag(comment = "更新日期")
	private Date updateDate;

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

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	public String getScoreCode() {
		return scoreCode;
	}

	public void setScoreCode(String scoreCode) {
		this.scoreCode = scoreCode;
	}

	public String getScoreName() {
		return scoreName;
	}

	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
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

	@Override
	public String getEntityId() {
		return this.id;
	}
}