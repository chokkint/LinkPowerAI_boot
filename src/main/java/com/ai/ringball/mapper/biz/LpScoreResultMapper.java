package com.ai.ringball.mapper.biz;

import com.ai.ringball.dao.biz.LpScoreResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface LpScoreResultMapper {

	LpScoreResult selectByPrimaryKey(String id);

	List<LpScoreResult> selectAllByPage(Map<String, Object> map);

	/************************* New ***********************************/

	int update2OldData(Map<String, Object> map);

	int insertRecords(Map<String, Object> map);

	int updateScoreResult4Relatives(Map<String, Object> map);

	int updateScoreResult4ContactCntL1m(Map<String, Object> map);

	int updateScoreResult4IsPhaseContact(Map<String, Object> map);

	List<LinkedHashMap<String, Object>> selectMongoData(Map<String, Object> map);
}