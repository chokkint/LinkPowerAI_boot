package com.ai.ringball.dao.biz;

import com.ai.ringball.framework.annotation.CommentTag;

import java.util.Date;


public class LpActSuggest {
	/**
	 * 行动类ID COLUMN:ID
	 */
	@CommentTag(comment = "行动类ID")
	private String id;

	/**
	 * 业务员ID COLUMN:SALES_ID
	 */
	@CommentTag(comment = "业务员ID")
	private String salesId;

	/**
	 * 客户ID COLUMN:CUST_ID
	 */
	@CommentTag(comment = "客户ID")
	private String custId;

	/**
	 * 行动类别ID COLUMN:ACT_TYPE
	 */
	@CommentTag(comment = "行动类别ID")
	private String actType;

	/**
	 * COLUMN:ACT_GENERATE_DATE
	 */
	@CommentTag(comment = "ACT_GENERATE_DATE")
	private Date actGenerateDate;

	/**
	 * COLUMN:ACT_EXPIRE_DATE
	 */
	@CommentTag(comment = "ACT_EXPIRE_DATE")
	private Date actExpireDate;

	/**
	 * 行动事件描述（按关键字对应，如：XX女士刚添了一名可爱的女宝宝，买个母婴用品去当面祝贺下吧） COLUMN:ACT_DESCRIPTION
	 */
	@CommentTag(comment = "行动事件描述（按关键字对应，如：XX女士刚添了一名可爱的女宝宝，买个母婴用品去当面祝贺下吧）")
	private String actDescription;

	/**
	 * 如果事件类型为社交-转发朋友圈，此处存放对应的文章标题 COLUMN:ACT_ARTICLE_TITLE
	 */
	@CommentTag(comment = "如果事件类型为社交-转发朋友圈，此处存放对应的文章标题")
	private String actArticleTitle;

	/**
	 * 如果事件类型为社交-转发朋友圈，此处存放对应的文章链接 COLUMN:ACT_ARTICLE_LINK
	 */
	@CommentTag(comment = "如果事件类型为社交-转发朋友圈，此处存放对应的文章链接")
	private String actArticleLink;

	/**
	 * 行动事件行程的原始动态数据类型（跟LP_DS_SRC_TYPE相关联） COLUMN:ACT_SOURCE_TYPE
	 */
	@CommentTag(comment = "行动事件行程的原始动态数据类型（跟LP_DS_SRC_TYPE相关联）")
	private String actSourceType;

	/**
	 * 行动事件行程的原始动态数据ID（跟不同类型的爬取数据表关联） COLUMN:ACT_SOURCE_CONTENT
	 */
	@CommentTag(comment = "行动事件行程的原始动态数据ID（跟不同类型的爬取数据表关联）")
	private String actSourceContent;

	/**
	 * COLUMN:CREATE_DATE
	 */
	@CommentTag(comment = "CREATE_DATE")
	private Date createDate;

	/**
	 * COLUMN:UPDATE_DATE
	 */
	@CommentTag(comment = "UPDATE_DATE")
	private Date updateDate;

	/**
	 * COLUMN:ISUSED
	 */
	@CommentTag(comment = "ISUSED")
	private String isused;

	/**
	 * 0:无效；1:有效 COLUMN:STATUS
	 */
	@CommentTag(comment = "0:无效；1:有效")
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}

	public Date getActGenerateDate() {
		return actGenerateDate;
	}

	public void setActGenerateDate(Date actGenerateDate) {
		this.actGenerateDate = actGenerateDate;
	}

	public Date getActExpireDate() {
		return actExpireDate;
	}

	public void setActExpireDate(Date actExpireDate) {
		this.actExpireDate = actExpireDate;
	}

	public String getActDescription() {
		return actDescription;
	}

	public void setActDescription(String actDescription) {
		this.actDescription = actDescription;
	}

	public String getActArticleTitle() {
		return actArticleTitle;
	}

	public void setActArticleTitle(String actArticleTitle) {
		this.actArticleTitle = actArticleTitle;
	}

	public String getActArticleLink() {
		return actArticleLink;
	}

	public void setActArticleLink(String actArticleLink) {
		this.actArticleLink = actArticleLink;
	}

	public String getActSourceType() {
		return actSourceType;
	}

	public void setActSourceType(String actSourceType) {
		this.actSourceType = actSourceType;
	}

	public String getActSourceContent() {
		return actSourceContent;
	}

	public void setActSourceContent(String actSourceContent) {
		this.actSourceContent = actSourceContent;
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

	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}