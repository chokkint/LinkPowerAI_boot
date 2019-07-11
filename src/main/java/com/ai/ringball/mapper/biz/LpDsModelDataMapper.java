package com.ai.ringball.mapper.biz;

import com.ai.ringball.dao.biz.LpDsModelData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LpDsModelDataMapper {

	List<LpDsModelData> selectAllByPage(Map<String, Object> map);
	
	
	/************************* New ***********************************/
	
	int deleteRecords(Map<String, Object> map);
	
	int insertRecords(Map<String, Object> map);
}