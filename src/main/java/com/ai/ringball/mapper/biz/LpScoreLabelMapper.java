package com.ai.ringball.mapper.biz;

import com.ai.ringball.dao.biz.LpScoreLabel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LpScoreLabelMapper {

    LpScoreLabel selectByPrimaryKey(String id);

    List<LpScoreLabel> selectAllByPage(Map<String, Object> map);
	
	
	/************************* New ***********************************/
	
    int insertRecords(Map<String, Object> map);
}