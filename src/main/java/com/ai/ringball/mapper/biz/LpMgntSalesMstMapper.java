package com.ai.ringball.mapper.biz;

import com.ai.ringball.dao.biz.LpMgntSalesMst;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LpMgntSalesMstMapper {
    int deleteByPrimaryKey(String id);

    int insert(LpMgntSalesMst record);

    int insertSelective(LpMgntSalesMst record);

    LpMgntSalesMst selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LpMgntSalesMst record);

    int updateByPrimaryKey(LpMgntSalesMst record);

    List<LpMgntSalesMst> selectAllByPage(Map<String, Object> map);
}