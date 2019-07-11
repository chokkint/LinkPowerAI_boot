package com.ai.ringball.mapper.biz;

import com.ai.ringball.dao.biz.LpActSuggest;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface LpActSuggestMapper {

	int deleteByPrimaryKey(String id);

	int insert(LpActSuggest record);

	int insertSelective(LpActSuggest record);

	LpActSuggest selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(LpActSuggest record);

	int updateByPrimaryKey(LpActSuggest record);

	List<LpActSuggest> selectAllByPage(Map<String, Object> map);
	
	
	/************************* New ***********************************/

	int updateWechatStatus(Map<String, Object> map);
	
	int insertRecords(Map<String, Object> map);
	
	List<LinkedHashMap<String, Object>> selectMongoData(Map<String, Object> map);
}