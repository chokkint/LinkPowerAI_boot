package com.ai.ringball.mapper.biz;

import com.ai.ringball.dao.biz.LpMgntIndexMst;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LpMgntIndexMstMapper {
    int deleteByPrimaryKey(String id);

    int insert(LpMgntIndexMst record);

    int insertSelective(LpMgntIndexMst record);

    LpMgntIndexMst selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LpMgntIndexMst record);

    int updateByPrimaryKey(LpMgntIndexMst record);

    List<LpMgntIndexMst> selectAllByPage(Map<String, Object> map);
}