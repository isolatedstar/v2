package com.zllh.factoring.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.factoring.common.model.FacTender;

public interface FacTenderMapper {
    int deleteByPrimaryKey(String id);

    int insert(FacTender record);

    int insertSelective(FacTender record);

    FacTender selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FacTender record);

    int updateByPrimaryKey(FacTender record);
    
    public List<Map<String,Object>> getAllByUser(String user);
}