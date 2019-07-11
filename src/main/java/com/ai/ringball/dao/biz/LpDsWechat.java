package com.ai.ringball.dao.biz;

import com.ai.ringball.framework.annotation.CommentTag;

import java.util.Date;


public class LpDsWechat {
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
	 * 微信号 COLUMN:WECHAT_NO
	 */
	@CommentTag(comment = "微信号")
	private String wechatNo;

	/**
	 * 爬取时间 COLUMN:READ_DATE
	 */
	@CommentTag(comment = "爬取时间")
	private Date readDate;

	/**
	 * 动态发布时间 COLUMN:MOMENT_DATE
	 */
	@CommentTag(comment = "动态发布时间")
	private Date momentDate;

	/**
	 * 动态文本内容 COLUMN:MOMENT_TEXT
	 */
	@CommentTag(comment = "动态文本内容")
	private String momentText;

	/**
	 * 动态转发文章的链接 COLUMN:MOMENT_LINK
	 */
	@CommentTag(comment = "动态转发文章的链接")
	private String momentLink;

	/**
	 * 动态图片内容（九宫格） COLUMN:MOMENT_IMG1
	 */
	@CommentTag(comment = "动态图片内容（九宫格）")
	private String momentImg1;

	/**
	 * 动态图片内容（九宫格） COLUMN:MOMENT_IMG2
	 */
	@CommentTag(comment = "动态图片内容（九宫格）")
	private String momentImg2;

	/**
	 * 动态图片内容（九宫格） COLUMN:MOMENT_IMG3
	 */
	@CommentTag(comment = "动态图片内容（九宫格）")
	private String momentImg3;

	/**
	 * 动态图片内容（九宫格） COLUMN:MOMENT_IMG4
	 */
	@CommentTag(comment = "动态图片内容（九宫格）")
	private String momentImg4;

	/**
	 * 动态图片内容（九宫格） COLUMN:MOMENT_IMG5
	 */
	@CommentTag(comment = "动态图片内容（九宫格）")
	private String momentImg5;

	/**
	 * 动态图片内容（九宫格） COLUMN:MOMENT_IMG6
	 */
	@CommentTag(comment = "动态图片内容（九宫格）")
	private String momentImg6;

	/**
	 * 动态图片内容（九宫格） COLUMN:MOMENT_IMG7
	 */
	@CommentTag(comment = "动态图片内容（九宫格）")
	private String momentImg7;

	/**
	 * 动态图片内容（九宫格） COLUMN:MOMENT_IMG8
	 */
	@CommentTag(comment = "动态图片内容（九宫格）")
	private String momentImg8;

	/**
	 * 动态图片内容（九宫格） COLUMN:MOMENT_IMG9
	 */
	@CommentTag(comment = "动态图片内容（九宫格）")
	private String momentImg9;

	/**
	 * 0:无效；1:有效 COLUMN:STATUS
	 */
	@CommentTag(comment = "0:无效；1:有效")
	private String status;

	/**
	 * 0:未使用；1:使用; 2灵豹眼分析中 COLUMN:ISUSED
	 */
	@CommentTag(comment = "0:未使用；1:使用; 2灵豹眼分析中")
	private String isused;

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

	public String getWechatNo() {
		return wechatNo;
	}

	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Date getMomentDate() {
		return momentDate;
	}

	public void setMomentDate(Date momentDate) {
		this.momentDate = momentDate;
	}

	public String getMomentText() {
		return momentText;
	}

	public void setMomentText(String momentText) {
		this.momentText = momentText;
	}

	public String getMomentLink() {
		return momentLink;
	}

	public void setMomentLink(String momentLink) {
		this.momentLink = momentLink;
	}

	public String getMomentImg1() {
		return momentImg1;
	}

	public void setMomentImg1(String momentImg1) {
		this.momentImg1 = momentImg1;
	}

	public String getMomentImg2() {
		return momentImg2;
	}

	public void setMomentImg2(String momentImg2) {
		this.momentImg2 = momentImg2;
	}

	public String getMomentImg3() {
		return momentImg3;
	}

	public void setMomentImg3(String momentImg3) {
		this.momentImg3 = momentImg3;
	}

	public String getMomentImg4() {
		return momentImg4;
	}

	public void setMomentImg4(String momentImg4) {
		this.momentImg4 = momentImg4;
	}

	public String getMomentImg5() {
		return momentImg5;
	}

	public void setMomentImg5(String momentImg5) {
		this.momentImg5 = momentImg5;
	}

	public String getMomentImg6() {
		return momentImg6;
	}

	public void setMomentImg6(String momentImg6) {
		this.momentImg6 = momentImg6;
	}

	public String getMomentImg7() {
		return momentImg7;
	}

	public void setMomentImg7(String momentImg7) {
		this.momentImg7 = momentImg7;
	}

	public String getMomentImg8() {
		return momentImg8;
	}

	public void setMomentImg8(String momentImg8) {
		this.momentImg8 = momentImg8;
	}

	public String getMomentImg9() {
		return momentImg9;
	}

	public void setMomentImg9(String momentImg9) {
		this.momentImg9 = momentImg9;
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
}