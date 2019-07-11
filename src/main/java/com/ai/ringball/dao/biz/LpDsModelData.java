package com.ai.ringball.dao.biz;

import com.ai.ringball.framework.annotation.CommentTag;

import java.util.Date;


public class LpDsModelData {
	/**
	 * 客户ID COLUMN:CUST_ID
	 */
	@CommentTag(comment = "客户ID")
	private String custId;

	/**
	 * 数据日期 COLUMN:DATA_DATE
	 */
	@CommentTag(comment = "数据日期")
	private String dataDate;

	/**
	 * 客户来源(0:通讯录；1:微信；2:手工添加) COLUMN:SRC_FINAL
	 */
	@CommentTag(comment = "客户来源(0:通讯录；1:微信；2:手工添加)")
	private String srcFinal;

	/**
	 * 是否接触(0:未接触；1:接触中；2:已成交；3:已复购) COLUMN:IS_PHASE_CONTACT
	 */
	@CommentTag(comment = "是否接触(0:未接触；1:接触中；2:已成交；3:已复购)")
	private String isPhaseContact;

	/**
	 * 是否成交(0:未成交；1:已成交) COLUMN:IS_PHASE_DEAL
	 */
	@CommentTag(comment = "是否成交(0:未成交；1:已成交)")
	private String isPhaseDeal;

	/**
	 * 性别(M：男；F：女) COLUMN:SEX
	 */
	@CommentTag(comment = "性别(M：男；F：女)")
	private String sex;

	/**
	 * 是否结婚(0:未婚；1:已婚) COLUMN:MARRIAGE
	 */
	@CommentTag(comment = "是否结婚(0:未婚；1:已婚)")
	private String marriage;

	/**
	 * 是否有小孩(0:无子女；1:一个子女；2:两个子女) COLUMN:CHILD
	 */
	@CommentTag(comment = "是否有小孩(0:无子女；1:一个子女；2:两个子女)")
	private String child;

	/**
	 * 0:父母都在；1:父亲在；2:母亲在；3:父母不在 COLUMN:PARENT
	 */
	@CommentTag(comment = "0:父母都在；1:父亲在；2:母亲在；3:父母不在")
	private String parent;

	/**
	 * 行业 COLUMN:INDUSTRY
	 */
	@CommentTag(comment = "行业")
	private String industry;

	/**
	 * 职业 COLUMN:PROFESSION
	 */
	@CommentTag(comment = "职业")
	private String profession;

	/**
	 * 年收入(万元) COLUMN:YEARLY_INCOME
	 */
	@CommentTag(comment = "年收入(万元)")
	private Long yearlyIncome;

	/**
	 * 年支出(万元) COLUMN:YEARLY_EXPENSE
	 */
	@CommentTag(comment = "年支出(万元)")
	private Long yearlyExpense;

	/**
	 * 0:无房产；1:一套房产 COLUMN:HOUSE
	 */
	@CommentTag(comment = "0:无房产；1:一套房产")
	private String house;

	/**
	 * 0:无房屋按揭；1:有房屋按揭 COLUMN:HOUSE_LOAN
	 */
	@CommentTag(comment = "0:无房屋按揭；1:有房屋按揭")
	private String houseLoan;

	/**
	 * 0:无车子；1:一辆车子 COLUMN:CAR
	 */
	@CommentTag(comment = "0:无车子；1:一辆车子")
	private String car;

	/**
	 * 0:无车辆按揭；1:有车辆按揭 COLUMN:CAR_LOAN
	 */
	@CommentTag(comment = "0:无车辆按揭；1:有车辆按揭")
	private String carLoan;

	/**
	 * 体检次数(0:很少体检；1:每年体检；2:半年体检) COLUMN:HEALTH_CHECK_FIN
	 */
	@CommentTag(comment = "体检次数(0:很少体检；1:每年体检；2:半年体检)")
	private String healthCheckFin;

	/**
	 * 是否抽烟(0:不抽烟；1:很少抽烟；2:经常抽烟) COLUMN:SMOKE_FIN
	 */
	@CommentTag(comment = "是否抽烟(0:不抽烟；1:很少抽烟；2:经常抽烟)")
	private String smokeFin;

	/**
	 * 是否喝酒(0:不喝酒；1:很少喝酒；2:经常喝酒) COLUMN:ALCOHOL_FIN
	 */
	@CommentTag(comment = "是否喝酒(0:不喝酒；1:很少喝酒；2:经常喝酒)")
	private String alcoholFin;

	/**
	 * 0:无过往病史；1:无大病史；2:有大病史 COLUMN:SICK
	 */
	@CommentTag(comment = "0:无过往病史；1:无大病史；2:有大病史")
	private String sick;

	/**
	 * 个人征信情况（0:正常；1:灰名单；2:黑名单） COLUMN:CREDIT
	 */
	@CommentTag(comment = "个人征信情况（0:正常；1:灰名单；2:黑名单）")
	private String credit;

	/**
	 * 城市（外部数据获取） COLUMN:EXT_CITY
	 */
	@CommentTag(comment = "城市（外部数据获取）")
	private String extCity;

	/**
	 * 个推原始数据：（0-17岁；18-24岁；25-34岁；35-44岁；45岁以上） COLUMN:EXT_AGE
	 */
	@CommentTag(comment = "个推原始数据：（0-17岁；18-24岁；25-34岁；35-44岁；45岁以上）")
	private String extAge;

	/**
	 * 年龄（外部数据获取，下限） COLUMN:EXT_AGE_FROM
	 */
	@CommentTag(comment = "年龄（外部数据获取，下限）")
	private Short extAgeFrom;

	/**
	 * 年龄（外部数据获取，上限） COLUMN:EXT_AGE_TO
	 */
	@CommentTag(comment = "年龄（外部数据获取，上限）")
	private Short extAgeTo;

	/**
	 * null：无记录；0:低消费水平；1:中消费水平；2:高消费水平 COLUMN:EXT_CONSUMPTION
	 */
	@CommentTag(comment = "null：无记录；0:低消费水平；1:中消费水平；2:高消费水平")
	private String extConsumption;

	/**
	 * null：无记录；0:家里没有小学生；1:家里有小学生 COLUMN:EXT_HAS_PUPIL
	 */
	@CommentTag(comment = "null：无记录；0:家里没有小学生；1:家里有小学生")
	private String extHasPupil;

	/**
	 * null：无记录；0:家里没有中学生；1:家里有中学生 COLUMN:EXT_HAS_JUNIOR
	 */
	@CommentTag(comment = "null：无记录；0:家里没有中学生；1:家里有中学生")
	private String extHasJunior;

	/**
	 * null：无记录；0:股票交易低；1:股票交易中；2:股票交易高 COLUMN:EXT_LIKE_STOCK
	 */
	@CommentTag(comment = "null：无记录；0:股票交易低；1:股票交易中；2:股票交易高")
	private String extLikeStock;

	/**
	 * null：无记录；0:投资理财低；1:投资理财中；2:投资理财高 COLUMN:EXT_LIKE_FINANCE
	 */
	@CommentTag(comment = "null：无记录；0:投资理财低；1:投资理财中；2:投资理财高")
	private String extLikeFinance;

	/**
	 * null：无记录；0:银行业务低；1:银行业务中；2:银行业务高 COLUMN:EXT_LIKE_BANK
	 */
	@CommentTag(comment = "null：无记录；0:银行业务低；1:银行业务中；2:银行业务高")
	private String extLikeBank;

	/**
	 * null：无记录；0:信用卡低；1:信用卡中；2:信用卡高 COLUMN:EXT_LIKE_CREDITCARD
	 */
	@CommentTag(comment = "null：无记录；0:信用卡低；1:信用卡中；2:信用卡高")
	private String extLikeCreditcard;

	/**
	 * null：无记录；0:商务出差低；1:商务出差中；2:商务出差高 COLUMN:EXT_LIKE_BUSINESSTRIP
	 */
	@CommentTag(comment = "null：无记录；0:商务出差低；1:商务出差中；2:商务出差高")
	private String extLikeBusinesstrip;

	/**
	 * null：无记录；0:休闲旅游低；1:休闲旅游中；2:休闲旅游高 COLUMN:EXT_LIKE_TRAVEL
	 */
	@CommentTag(comment = "null：无记录；0:休闲旅游低；1:休闲旅游中；2:休闲旅游高")
	private String extLikeTravel;

	/**
	 * null：无记录；0:健康医疗低；1:健康医疗中；2:健康医疗高 COLUMN:EXT_LIKE_HEALTH
	 */
	@CommentTag(comment = "null：无记录；0:健康医疗低；1:健康医疗中；2:健康医疗高")
	private String extLikeHealth;

	/**
	 * null：无记录；0:育儿社区低；1:育儿社区中；2:育儿社区高 COLUMN:EXT_LIKE_CHILD
	 */
	@CommentTag(comment = "null：无记录；0:育儿社区低；1:育儿社区中；2:育儿社区高")
	private String extLikeChild;

	/**
	 * null：无记录；0:打车出行低；1:打车出行中；2:打车出行高 COLUMN:EXT_LIKE_DIDI
	 */
	@CommentTag(comment = "null：无记录；0:打车出行低；1:打车出行中；2:打车出行高")
	private String extLikeDidi;

	/**
	 * null：无记录；0:地图导航低；1:地图导航中；2:地图导航高 COLUMN:EXT_LIKE_MAP
	 */
	@CommentTag(comment = "null：无记录；0:地图导航低；1:地图导航中；2:地图导航高")
	private String extLikeMap;

	/**
	 * 微信地区（城市） COLUMN:WECHAT_CITY_FIN
	 */
	@CommentTag(comment = "微信地区（城市）")
	private String wechatCityFin;

	/**
	 * 动态发布时间 COLUMN:MOMENT_DATE
	 */
	@CommentTag(comment = "动态发布时间")
	private Date momentDate;

	/**
	 * 最近1个月发布朋友圈次数 COLUMN:WECHAT_CNT_L1M
	 */
	@CommentTag(comment = "最近1个月发布朋友圈次数")
	private Long wechatCntL1m;

	/**
	 * 关键字[孩子]相关统计 COLUMN:CHILD_EVT_CNT_L3M
	 */
	@CommentTag(comment = "关键字[孩子]相关统计")
	private Long childEvtCntL3m;

	/**
	 * 关键字[婚姻]相关统计 COLUMN:MAR_EVT_CNT_L3M
	 */
	@CommentTag(comment = "关键字[婚姻]相关统计")
	private Long marEvtCntL3m;

	/**
	 * 关键字[情感]相关统计 COLUMN:EMO_EVT_CNT_L3M
	 */
	@CommentTag(comment = "关键字[情感]相关统计")
	private Long emoEvtCntL3m;

	/**
	 * 关键字[节日]相关统计 COLUMN:FES_EVT_CNT_L3M
	 */
	@CommentTag(comment = "关键字[节日]相关统计")
	private Long fesEvtCntL3m;

	/**
	 * 通话记录数据爬取时间 COLUMN:READ_DATE
	 */
	@CommentTag(comment = "通话记录数据爬取时间")
	private Date readDate;

	/**
	 * 最近1个月通话次数 COLUMN:CONTACT_CNT_L1M
	 */
	@CommentTag(comment = "最近1个月通话次数")
	private Long contactCntL1m;

	/**
	 * 最近1个月客户主动拨入次数 COLUMN:CONTACT_INI_CNT_L1M
	 */
	@CommentTag(comment = "最近1个月客户主动拨入次数")
	private Long contactIniCntL1m;

	/**
	 * 最近1个月通话时长(分钟) COLUMN:CONTACT_LEN_L1M
	 */
	@CommentTag(comment = "最近1个月通话时长(分钟)")
	private Long contactLenL1m;

	/**
	 * 最近1个月客户主动拨入通话时长(分钟) COLUMN:CONTACT_INI_LEN_L1M
	 */
	@CommentTag(comment = "最近1个月客户主动拨入通话时长(分钟)")
	private Long contactIniLenL1m;

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

	public String getSrcFinal() {
		return srcFinal;
	}

	public void setSrcFinal(String srcFinal) {
		this.srcFinal = srcFinal;
	}

	public String getIsPhaseContact() {
		return isPhaseContact;
	}

	public void setIsPhaseContact(String isPhaseContact) {
		this.isPhaseContact = isPhaseContact;
	}

	public String getIsPhaseDeal() {
		return isPhaseDeal;
	}

	public void setIsPhaseDeal(String isPhaseDeal) {
		this.isPhaseDeal = isPhaseDeal;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Long getYearlyIncome() {
		return yearlyIncome;
	}

	public void setYearlyIncome(Long yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}

	public Long getYearlyExpense() {
		return yearlyExpense;
	}

	public void setYearlyExpense(Long yearlyExpense) {
		this.yearlyExpense = yearlyExpense;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getHouseLoan() {
		return houseLoan;
	}

	public void setHouseLoan(String houseLoan) {
		this.houseLoan = houseLoan;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getCarLoan() {
		return carLoan;
	}

	public void setCarLoan(String carLoan) {
		this.carLoan = carLoan;
	}

	public String getHealthCheckFin() {
		return healthCheckFin;
	}

	public void setHealthCheckFin(String healthCheckFin) {
		this.healthCheckFin = healthCheckFin;
	}

	public String getSmokeFin() {
		return smokeFin;
	}

	public void setSmokeFin(String smokeFin) {
		this.smokeFin = smokeFin;
	}

	public String getAlcoholFin() {
		return alcoholFin;
	}

	public void setAlcoholFin(String alcoholFin) {
		this.alcoholFin = alcoholFin;
	}

	public String getSick() {
		return sick;
	}

	public void setSick(String sick) {
		this.sick = sick;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getExtCity() {
		return extCity;
	}

	public void setExtCity(String extCity) {
		this.extCity = extCity;
	}

	public String getExtAge() {
		return extAge;
	}

	public void setExtAge(String extAge) {
		this.extAge = extAge;
	}

	public Short getExtAgeFrom() {
		return extAgeFrom;
	}

	public void setExtAgeFrom(Short extAgeFrom) {
		this.extAgeFrom = extAgeFrom;
	}

	public Short getExtAgeTo() {
		return extAgeTo;
	}

	public void setExtAgeTo(Short extAgeTo) {
		this.extAgeTo = extAgeTo;
	}

	public String getExtConsumption() {
		return extConsumption;
	}

	public void setExtConsumption(String extConsumption) {
		this.extConsumption = extConsumption;
	}

	public String getExtHasPupil() {
		return extHasPupil;
	}

	public void setExtHasPupil(String extHasPupil) {
		this.extHasPupil = extHasPupil;
	}

	public String getExtHasJunior() {
		return extHasJunior;
	}

	public void setExtHasJunior(String extHasJunior) {
		this.extHasJunior = extHasJunior;
	}

	public String getExtLikeStock() {
		return extLikeStock;
	}

	public void setExtLikeStock(String extLikeStock) {
		this.extLikeStock = extLikeStock;
	}

	public String getExtLikeFinance() {
		return extLikeFinance;
	}

	public void setExtLikeFinance(String extLikeFinance) {
		this.extLikeFinance = extLikeFinance;
	}

	public String getExtLikeBank() {
		return extLikeBank;
	}

	public void setExtLikeBank(String extLikeBank) {
		this.extLikeBank = extLikeBank;
	}

	public String getExtLikeCreditcard() {
		return extLikeCreditcard;
	}

	public void setExtLikeCreditcard(String extLikeCreditcard) {
		this.extLikeCreditcard = extLikeCreditcard;
	}

	public String getExtLikeBusinesstrip() {
		return extLikeBusinesstrip;
	}

	public void setExtLikeBusinesstrip(String extLikeBusinesstrip) {
		this.extLikeBusinesstrip = extLikeBusinesstrip;
	}

	public String getExtLikeTravel() {
		return extLikeTravel;
	}

	public void setExtLikeTravel(String extLikeTravel) {
		this.extLikeTravel = extLikeTravel;
	}

	public String getExtLikeHealth() {
		return extLikeHealth;
	}

	public void setExtLikeHealth(String extLikeHealth) {
		this.extLikeHealth = extLikeHealth;
	}

	public String getExtLikeChild() {
		return extLikeChild;
	}

	public void setExtLikeChild(String extLikeChild) {
		this.extLikeChild = extLikeChild;
	}

	public String getExtLikeDidi() {
		return extLikeDidi;
	}

	public void setExtLikeDidi(String extLikeDidi) {
		this.extLikeDidi = extLikeDidi;
	}

	public String getExtLikeMap() {
		return extLikeMap;
	}

	public void setExtLikeMap(String extLikeMap) {
		this.extLikeMap = extLikeMap;
	}

	public String getWechatCityFin() {
		return wechatCityFin;
	}

	public void setWechatCityFin(String wechatCityFin) {
		this.wechatCityFin = wechatCityFin;
	}

	public Date getMomentDate() {
		return momentDate;
	}

	public void setMomentDate(Date momentDate) {
		this.momentDate = momentDate;
	}

	public Long getWechatCntL1m() {
		return wechatCntL1m;
	}

	public void setWechatCntL1m(Long wechatCntL1m) {
		this.wechatCntL1m = wechatCntL1m;
	}

	public Long getChildEvtCntL3m() {
		return childEvtCntL3m;
	}

	public void setChildEvtCntL3m(Long childEvtCntL3m) {
		this.childEvtCntL3m = childEvtCntL3m;
	}

	public Long getMarEvtCntL3m() {
		return marEvtCntL3m;
	}

	public void setMarEvtCntL3m(Long marEvtCntL3m) {
		this.marEvtCntL3m = marEvtCntL3m;
	}

	public Long getEmoEvtCntL3m() {
		return emoEvtCntL3m;
	}

	public void setEmoEvtCntL3m(Long emoEvtCntL3m) {
		this.emoEvtCntL3m = emoEvtCntL3m;
	}

	public Long getFesEvtCntL3m() {
		return fesEvtCntL3m;
	}

	public void setFesEvtCntL3m(Long fesEvtCntL3m) {
		this.fesEvtCntL3m = fesEvtCntL3m;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Long getContactCntL1m() {
		return contactCntL1m;
	}

	public void setContactCntL1m(Long contactCntL1m) {
		this.contactCntL1m = contactCntL1m;
	}

	public Long getContactIniCntL1m() {
		return contactIniCntL1m;
	}

	public void setContactIniCntL1m(Long contactIniCntL1m) {
		this.contactIniCntL1m = contactIniCntL1m;
	}

	public Long getContactLenL1m() {
		return contactLenL1m;
	}

	public void setContactLenL1m(Long contactLenL1m) {
		this.contactLenL1m = contactLenL1m;
	}

	public Long getContactIniLenL1m() {
		return contactIniLenL1m;
	}

	public void setContactIniLenL1m(Long contactIniLenL1m) {
		this.contactIniLenL1m = contactIniLenL1m;
	}
}