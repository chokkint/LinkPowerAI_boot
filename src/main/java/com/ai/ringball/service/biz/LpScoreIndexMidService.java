package com.ai.ringball.service.biz;

import com.ai.ringball.dao.biz.LpMgntIndexMst;
import com.ai.ringball.dao.biz.LpMgntSuggestRuleMst;
import com.ai.ringball.dao.biz.LpScoreIndexMid;
import com.ai.ringball.framework.base.CommonBizService;
import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.db.mongo.MongoDaoImpl;
import com.ai.ringball.framework.utility.common.*;
import com.ai.ringball.mapper.biz.*;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class LpScoreIndexMidService {

	@Autowired
	private CommonBizService commonBizService;

	@Autowired
	private LpScoreIndexMidMapper lpScoreIndexMidMapper;

	@Autowired
	private LpMgntIndexMstMapper lpMgntIndexMstMapper;

	@Autowired
	private LpScoreResultMapper lpScoreResultMapper;

	@Autowired
	private LpScoreLabelMapper lpScoreLabelMapper;

	@Autowired
	private LpDsModelDataMapper lpDsModelDataMapper;

	@Autowired
	private LpActSuggestMapper lpActSuggestMapper;

	@Autowired
	private LpMgntSuggestRuleMstMapper lpSuggestRuleMstMapper;

	@Autowired
	private LpDsWechatMapper lpDsWechatMapper;

	/**
	 * true: 开放模式,生成模型数据写入到MySQL和MongoDB
	 * false:受限模式,生成数据仅写入到MySQL中,用于部分客户仅跑灵豹分和灵豹标签使用
	 */
	private static boolean isWriteToMongoDB = true;

	public List<LpScoreIndexMid> selectAllByPage(String salesId, String custId, String dataDate, PageUtils page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesId", salesId);
		map.put("custId", custId);
		map.put("dataDate", dataDate);

		if (page != null) {
			map.put("page", page);
		}

		List<LpScoreIndexMid> recordsList = new ArrayList<LpScoreIndexMid>();
		recordsList = lpScoreIndexMidMapper.selectAllByPage(map);
		return recordsList;
	}

	@Transactional
	public Map<String, Object> perHandlerSourceData(String salesId, String custId, String dataDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesId", salesId);
		map.put("custId", custId);
		map.put("dataDate", dataDate);

		/* ********Handler1:处理所有CUST_ID为'999999999'并且WECHAT_NO不是空或者''的朋友圈数据************** */
		lpDsWechatMapper.update999999999ToCustId(map);
		
		/* ********Handler2:对所有CUST_ID不是'999999999'的朋友圈数据进行去除重复处理*********************** */
		lpDsWechatMapper.distinctWechatData(map);
		
		return ResultUtils.createSuccessResult(null, null);
	}

	@Transactional
	public Map<String, Object> generateLpDsModelData(String salesId, String custId, String dataDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesId", salesId);
		map.put("custId", custId);
		map.put("dataDate", dataDate);

		// 删除已经存在的灵豹模型数据
		lpDsModelDataMapper.deleteRecords(map);

		// 生成灵豹模型数据
		lpDsModelDataMapper.insertRecords(map);

		return ResultUtils.createSuccessResult(null, null);
	}

	@Transactional
	public Map<String, Object> generateScoreIndexMidData(String salesId, String custId, String dataDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesId", salesId);
		map.put("custId", custId);
		map.put("moduleVer", SysConstants.SYS_VERSION);
		map.put("dataDate", dataDate);

		// 删除当天已经存在的数据
		lpScoreIndexMidMapper.deleteOldData(map);

		// 获取需要重跑灵豹分的指标配置信息
		List<LpMgntIndexMst> records = lpMgntIndexMstMapper.selectAllByPage(map);

		// 循环生成灵豹分指标得分情况
		for (LpMgntIndexMst record : records) {
			map.put("indexType", record.getIndexType());
			map.put("indexCode", record.getIndexCode());
			map.put("indexName", record.getIndexName());
			map.put("indexSql", record.getIndexSql());

			lpScoreIndexMidMapper.insertRecords(map);
		}

		return ResultUtils.createSuccessResult(null, null);
	}

	@Transactional
	public Map<String, Object> generateScoreResultData(String salesId, String custId, String dataDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesId", salesId);
		map.put("custId", custId);
		map.put("moduleVer", SysConstants.SYS_VERSION);
		map.put("dataDate", dataDate);

		// 更新最新的分数据状态为"不是最新状态"
		lpScoreResultMapper.update2OldData(map);

		// 生成灵豹分情况
		lpScoreResultMapper.insertRecords(map);

		// 3.1 老婆 老爸 老妈 爸爸 妈妈 姑姑 叔叔 大伯 姐姐 妹妹 大姐 小妹 姐 妹 弟弟 哥哥 大哥 哥 弟 宝贝 宝宝 亲爱
		// ；或者分组为 家里人 亲戚 家族 ；年龄小于23岁；年龄大于55岁 ，保持10分
		lpScoreResultMapper.updateScoreResult4Relatives(map);

		// 3.2 最近一个月通话次数contact_cnt_l1m>10，则灵豹分=20分
		lpScoreResultMapper.updateScoreResult4ContactCntL1m(map);

		// 3.3 如果is_PHASE_contact=3 ，即复购完成，则 灵豹分=30分
		lpScoreResultMapper.updateScoreResult4IsPhaseContact(map);

		return ResultUtils.createSuccessResult(null, null);
	}

	@Transactional
	public Map<String, Object> generateScoreLabelData(String salesId, String custId, String dataDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesId", salesId);
		map.put("custId", custId);
		map.put("dataDate", dataDate);

		// 生成灵豹标签情况
		lpScoreLabelMapper.insertRecords(map);

		return ResultUtils.createSuccessResult(null, null);
	}

	@Transactional
	public Map<String, Object> generateLpActSuggestData(String salesId, String custId, String dataDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesId", salesId);
		map.put("custId", custId);
		map.put("dataDate", dataDate);

		// 更新已经使用过的WECHAT数据状态为"灵豹眼模型分析中"
		map.put("isusedOld", SysConstants.CONSTANT_0);
		map.put("isused", SysConstants.CONSTANT_2);
		lpActSuggestMapper.updateWechatStatus(map);

		// 获取行动推荐规则配置信息
		List<LpMgntSuggestRuleMst> records = lpSuggestRuleMstMapper.selectAllByPage(map);

		// 循环生成行动推荐数据
		String keyWord = SysConstants.CONSTANT_NULL_STRING;
		String valueDuration = SysConstants.CONSTANT_NULL_STRING;
		String _likeSql = SysConstants.CONSTANT_NULL_STRING;
		List<LinkedHashMap<String, Object>> mapList = new ArrayList<LinkedHashMap<String, Object>>();

		int i = 0;
		int j = 0;
		for (LpMgntSuggestRuleMst record : records) {
			List<LinkedHashMap<String, Object>> tempMapList = new ArrayList<LinkedHashMap<String, Object>>();

			map.put("_actType", record.getActTypeId());
			map.put("_actText", record.getActText());

			// 处理到期日
			valueDuration = record.getValueDuration();
			if ("D".equals(valueDuration)) {
				map.put("_actExpireDateSql", "W.MOMENT_DATE");
			} else if ("W".equals(valueDuration)) {
				map.put("_actExpireDateSql", "DATE_ADD(W.MOMENT_DATE, INTERVAL 7 DAY)");
			} else if ("HM".equals(valueDuration)) {
				map.put("_actExpireDateSql", "DATE_ADD(W.MOMENT_DATE, INTERVAL 15 DAY)");
			} else if ("M".equals(valueDuration)) {
				map.put("_actExpireDateSql", "DATE_ADD(W.MOMENT_DATE, INTERVAL 1 MONTH)");
			}

			// 处理关键字查询条件
			i = 0;
			_likeSql = SysConstants.CONSTANT_NULL_STRING;
			keyWord = record.getKeyWord();
			String keys[] = keyWord.split("@@");
			for (String key : keys) {
				if (i != 0) {
					_likeSql += " OR ";
				}
				i++;
				if (key.contains("&&")) {
					j = 0;
					String subKeys[] = key.split("&&");
					_likeSql += "(";
					for (String subKey : subKeys) {
						if (j != 0) {
							_likeSql += " AND ";
						}
						j++;

						_likeSql += ("concat(IFNULL(W.MOMENT_TEXT, ''), IFNULL(W.MOMENT_LINK, '')) like '%" + subKey + "%'");
					}
					_likeSql += ")";
				} else {
					_likeSql += ("concat(IFNULL(W.MOMENT_TEXT, ''), IFNULL(W.MOMENT_LINK, '')) like '%" + key + "%'");
				}
			}
			map.put("_likeSql", _likeSql);

			if (isWriteToMongoDB) {
				// 获取MongoDB数据集合
				tempMapList = lpActSuggestMapper.selectMongoData(map);
				mapList.addAll(tempMapList);
			}

			lpActSuggestMapper.insertRecords(map);
		}

		if (isWriteToMongoDB) {
			// 将mongoDB数据存入到MongoDB
			if (mapList.size() > 0) {
				Properties pro = PropertyUtil.getResourceFile("jdbc/jdbc.properties");
				String dbName = pro.getProperty("mongo.dbname");
				String tableName = "LP_ACT_SUGGEST";
				this.insertMongoDBData(dbName, tableName, mapList);
			}
		}

		// 更新已经使用过的WECHAT数据状态为"已使用"
		map.put("isusedOld", SysConstants.CONSTANT_2);
		map.put("isused", SysConstants.CONSTANT_1);
		lpActSuggestMapper.updateWechatStatus(map);

		return ResultUtils.createSuccessResult(null, null);
	}

	@Transactional
	public Map<String, Object> generateScoreResultLabelData(String salesId, String custId, String dataDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesId", salesId);
		map.put("custId", custId);
		map.put("moduleVer", SysConstants.SYS_VERSION);
		map.put("dataDate", dataDate);

		// 更新最新的分数据状态为"不是最新状态"
		lpScoreResultMapper.update2OldData(map);

		// 生成灵豹分情况
		lpScoreResultMapper.insertRecords(map);

		// 3.1 老婆 老爸 老妈 爸爸 妈妈 姑姑 叔叔 大伯 姐姐 妹妹 大姐 小妹 姐 妹 弟弟 哥哥 大哥 哥 弟 宝贝 宝宝 亲爱
		// ；或者分组为 家里人 亲戚 家族 ；年龄小于23岁；年龄大于55岁 ，保持10分
		lpScoreResultMapper.updateScoreResult4Relatives(map);

		// 3.2 最近一个月通话次数contact_cnt_l1m>10，则灵豹分=20分
		lpScoreResultMapper.updateScoreResult4ContactCntL1m(map);

		// 3.3 如果is_PHASE_contact=3 ，即复购完成，则 灵豹分=30分
		lpScoreResultMapper.updateScoreResult4IsPhaseContact(map);

		// 生成灵豹标签情况
		lpScoreLabelMapper.insertRecords(map);

		if (isWriteToMongoDB) {
			// 获取MongoDB数据库名和表名
			Properties pro = PropertyUtil.getResourceFile("jdbc/jdbc.properties");
			String dbName = pro.getProperty("mongo.dbname");
			String tableName = "LP_SCORE_RESULT_LABEL";
			String tableNameLastest = "LP_SCORE_RESULT_LABEL_LASTEST";

			// 打开MongoDB连接
			MongoDaoImpl mongoDao = new MongoDaoImpl();
			MongoUtils mongoUtils = new MongoUtils();
			ServerAddress addr = commonBizService.getServerAddressObject();
			MongoClient mongoClient = mongoUtils.getMongoClient(addr, null);
			MongoDatabase mongoDataBase = mongoUtils.getMongoDataBase(mongoClient, dbName);

			// 更新已经存在的MongoDB数据status='0'
			BasicDBObject whereDoc = new BasicDBObject();
			if (salesId != null && !(SysConstants.CONSTANT_NULL_STRING.equals(salesId))) {
				whereDoc.append("saled_id", salesId);
			}
			if (custId != null && !(SysConstants.CONSTANT_NULL_STRING.equals(custId))) {
				whereDoc.append("cust_id", custId);
			}
			whereDoc.append("status", SysConstants.CONSTANT_1);
			BasicDBObject updateDoc = new BasicDBObject();
			updateDoc.append("status", SysConstants.CONSTANT_0);
			updateDoc.append("update_date", DateUtils.getDatetime(new Date()));
			// 更新灵豹分-灵豹标签表数据
			mongoDao.updateMany(mongoDataBase, tableName, whereDoc, updateDoc);
			// 清空最新灵豹分-灵豹标签表数据
			mongoDao.deleteMany(mongoDataBase, tableNameLastest, whereDoc);

			// 生成MongoDB数据
			List<LinkedHashMap<String, Object>> mapList = new ArrayList<LinkedHashMap<String, Object>>();
			mapList = lpScoreResultMapper.selectMongoData(map);

			// 将mongoDB数据存入到MongoDB
			if (mapList.size() > 0) {
				// 将修改后的新纪录插入到Collection中
				List<Document> documents = new ArrayList<Document>();
//				BasicDBObject custWhereDoc = new BasicDBObject();
//				BasicDBObject custUpdateDoc = new BasicDBObject();
				for (LinkedHashMap<String, Object> map1 : mapList) {
					Document document = new Document(this.translateBigDecimal2Decimal128(map1));
					documents.add(document);
					
					// 更新客户表中[灵豹分]字段
//					custWhereDoc.append("cust_id", document.get("cust_id"));
//					custUpdateDoc.append("score", document.get("score"));
//					mongoDao.updateOne(mongoDataBase, "LP_MGNT_CUST_MST", custWhereDoc, custUpdateDoc);
				}
				// 更新灵豹分-灵豹标签表数据
				mongoDao.insertMany(mongoDataBase, tableName, documents);
				// 更新最新灵豹分-灵豹标签表数据
				mongoDao.insertMany(mongoDataBase, tableNameLastest, documents);
			}

			// 关闭MongoDB连接
			mongoUtils.closeMongoClient(mongoDataBase, mongoClient);
		}

		return ResultUtils.createSuccessResult(null, null);
	}

	/**
	 * 功能说明:将传入的数据存储到指定的MongoDB的集合中
	 * @param dbName MongoDB数据库名
	 * @param tableName MongoDB集合名
	 * @param mapList 数据集合
	 * @return 操作结果Map
	 */
	@Transactional
	private Map<String, Object> insertMongoDBData(String dbName, String tableName, List<LinkedHashMap<String, Object>> mapList) {

		// 传入数据集合为空时,直接返回成功
		if(mapList.size() == 0){
			return ResultUtils.createSuccessResult(null, null);
		}
		
		// 打开MongoDB连接
		MongoDaoImpl mongoDao = new MongoDaoImpl();
		MongoUtils mongoUtils = new MongoUtils();
		ServerAddress addr = commonBizService.getServerAddressObject();
		MongoClient mongoClient = mongoUtils.getMongoClient(addr, null);
		MongoDatabase mongoDataBase = mongoUtils.getMongoDataBase(mongoClient, dbName);

		// 将修改后的新纪录插入到Collection中
		List<Document> documents = new ArrayList<Document>();
		for (LinkedHashMap<String, Object> map : mapList) {
			Document document = new Document(this.translateBigDecimal2Decimal128(map));
			documents.add(document);
		}
		mongoDao.insertMany(mongoDataBase, tableName, documents);

		// 关闭MongoDB连接
		mongoUtils.closeMongoClient(mongoDataBase, mongoClient);

		return ResultUtils.createSuccessResult(null, null);
	}

	/**
	 * 功能说明:转换传入Map中所有BigDecimal类型的数据为Decimal128类型
	 * @param map 传入数据
	 * @return 转换后的数据
	 */
	private LinkedHashMap<String, Object> translateBigDecimal2Decimal128(LinkedHashMap<String, Object> map) {
		for (String key : map.keySet()) {
			if (map.get(key) instanceof BigDecimal) {
				map.put(key, new Decimal128((BigDecimal) map.get(key)));
			}
		}

		return map;
	}
}
