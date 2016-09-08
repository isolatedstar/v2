package com.zllh.factoring.common.dao;

import java.util.List;

import com.zllh.factoring.common.model.FacMessageStatus;


public interface FacMessageStatusMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(FacMessageStatus record);

    int insertSelective(FacMessageStatus record);

    FacMessageStatus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FacMessageStatus record);

    int updateByPrimaryKeyWithBLOBs(FacMessageStatus record);

    int updateByPrimaryKey(FacMessageStatus record);
    
    List<FacMessageStatus> findFacMessageStatusAll();
}