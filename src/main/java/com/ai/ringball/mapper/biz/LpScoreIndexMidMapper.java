package com.ai.ringball.mapper.biz;

import com.ai.ringball.dao.biz.LpScoreIndexMid;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LpScoreIndexMidMapper {

	LpScoreIndexMid selectByPrimaryKey(String id);

	List<LpScoreIndexMid> selectAllByPage(Map<String, Object> map);
	
	
	/************************* New ***********************************/
	
	int deleteOldData(Map<String, Object> map);

	int insertRecords(Map<String, Object> map);
}