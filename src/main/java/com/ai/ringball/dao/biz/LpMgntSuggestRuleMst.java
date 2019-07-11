package com.ai.ringball.dao.biz;

import com.ai.ringball.framework.annotation.CommentTag;

import java.util.Date;


public class LpMgntSuggestRuleMst {
	/**
	 * UUID COLUMN:ID
	 */
	@CommentTag(comment = "UUID")
	private String id;

	/**
	 * 行动分类ID COLUMN:ACT_TYPE_ID
	 */
	@CommentTag(comment = "行动分类ID")
	private String actTypeId;

	/**
	 * 关键字(多个用@@拼接，共同满足用$$拼接) COLUMN:KEY_WORD
	 */
	@CommentTag(comment = "关键字(多个用@@拼接，共同满足用&&拼接)")
	private String keyWord;

	/**
	 * 行动模板 COLUMN:ACT_TEXT
	 */
	@CommentTag(comment = "行动模板")
	private String actText;

	/**
	 * 开始日期类型 COLUMN:VALUE_DATE
	 */
	@CommentTag(comment = "开始日期类型")
	private String valueDate;

	/**
	 * 有效时长(D:当天 W:一周 HM:半月 M:一个月) COLUMN:VALUE_DURATION
	 */
	@CommentTag(comment = "有效时长(D:当天 W:一周 HM:半月 M:一个月)")
	private String valueDuration;

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

	/**
	 * 备注 COLUMN:STATUS
	 */
	@CommentTag(comment = "状态(0:无效；1:有效)")
	private String status;

	/**
	 * 备用字段1 COLUMN:NOTE
	 */
	@CommentTag(comment = "备注")
	private String note;

	/**
	 * 备用字段2 COLUMN:NOTE1
	 */
	@CommentTag(comment = "备用字段1")
	private String note1;

	/**
	 * 备用字段3 COLUMN:NOTE2
	 */
	@CommentTag(comment = "备用字段2")
	private String note2;

	/**
	 * 备用字段4 COLUMN:NOTE3
	 */
	@CommentTag(comment = "备用字段3")
	private String note3;

	/**
	 * 备用字段5 COLUMN:NOTE4
	 */
	@CommentTag(comment = "备用字段4")
	private String note4;

	/**
	 * COLUMN:NOTE5
	 */
	@CommentTag(comment = "备用字段5")
	private String note5;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActTypeId() {
		return actTypeId;
	}

	public void setActTypeId(String actTypeId) {
		this.actTypeId = actTypeId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getActText() {
		return actText;
	}

	public void setActText(String actText) {
		this.actText = actText;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getValueDuration() {
		return valueDuration;
	}

	public void setValueDuration(String valueDuration) {
		this.valueDuration = valueDuration;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public String getNote5() {
		return note5;
	}

	public void setNote5(String note5) {
		this.note5 = note5;
	}
}