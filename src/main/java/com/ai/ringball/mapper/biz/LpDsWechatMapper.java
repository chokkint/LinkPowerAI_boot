package com.ai.ringball.mapper.biz;

import com.ai.ringball.dao.biz.LpDsWechat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LpDsWechatMapper {

	List<LpDsWechat> selectAllByPage(Map<String, Object> map);

	/************************* New ***********************************/

	int update999999999ToCustId(Map<String, Object> map);

	int distinctWechatData(Map<String, Object> map);
}