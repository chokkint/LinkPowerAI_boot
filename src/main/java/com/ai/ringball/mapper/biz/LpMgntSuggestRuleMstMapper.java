package com.ai.ringball.mapper.biz;

import com.ai.ringball.dao.biz.LpMgntSuggestRuleMst;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LpMgntSuggestRuleMstMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(LpMgntSuggestRuleMst record);

    int insertSelective(LpMgntSuggestRuleMst record);

    LpMgntSuggestRuleMst selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LpMgntSuggestRuleMst record);

    int updateByPrimaryKey(LpMgntSuggestRuleMst record);

    List<LpMgntSuggestRuleMst> selectAllByPage(Map<String, Object> map);
}