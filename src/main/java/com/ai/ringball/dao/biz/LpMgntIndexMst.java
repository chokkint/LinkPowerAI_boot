package com.ai.ringball.dao.biz;

import com.ai.ringball.framework.annotation.CommentTag;
import com.ai.ringball.framework.base.BaseEntity;
import java.util.Date;

public class LpMgntIndexMst extends BaseEntity {
	/**
	 * UUID COLUMN:ID
	 */
	@CommentTag(comment = "UUID")
	private String id;

	/**
	 * 灵豹分类型(0:价值分指标；1:意愿分指标) COLUMN:INDEX_TYPE
	 */
	@CommentTag(comment = "灵豹分类型(0:价值分指标；1:意愿分指标)")
	private String indexType;

	/**
	 * 指标代码 COLUMN:INDEX_CODE
	 */
	@CommentTag(comment = "指标代码")
	private String indexCode;

	/**
	 * 指标名称 COLUMN:INDEX_NAME
	 */
	@CommentTag(comment = "指标名称")
	private String indexName;

	/**
	 * 统计SQL(主表为LP_DS_MODEL_DATA,别名为M) COLUMN:INDEX_SQL
	 */
	@CommentTag(comment = "统计SQL(主表为LP_DS_MODEL_DATA,别名为M)")
	private String indexSql;

	/**
	 * 创建时间 COLUMN:CREATE_DATE
	 */
	@CommentTag(comment = "创建时间")
	private Date createDate;

	/**
	 * 更新时间 COLUMN:UPDATE_DATE
	 */
	@CommentTag(comment = "更新时间")
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndexType() {
		return indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getIndexSql() {
		return indexSql;
	}

	public void setIndexSql(String indexSql) {
		this.indexSql = indexSql;
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