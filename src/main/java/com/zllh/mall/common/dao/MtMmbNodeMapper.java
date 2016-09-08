package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtMmbNode;



public interface MtMmbNodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(MtMmbNode record);

    int insertSelective(MtMmbNode record);

    MtMmbNode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MtMmbNode record);

    int updateByPrimaryKey(MtMmbNode record);
    
    List<MtMmbNode> getMmbNodes(Map<String, Object> prarms);
}