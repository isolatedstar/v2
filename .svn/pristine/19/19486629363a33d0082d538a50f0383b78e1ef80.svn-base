package com.zllh.factoring.common.dao;

import java.util.HashMap;
import java.util.List;

import com.zllh.factoring.common.model.FacMessage;

public interface FacMessageMapper {
   
    int deleteByPrimaryKey(String id);

    int insert(FacMessage record);

    int insertSelective(FacMessage record);

    FacMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FacMessage record);

    int updateByPrimaryKeyWithBLOBs(FacMessage record);

    int updateByPrimaryKey(FacMessage record);

    List<FacMessage> findMessageCheckList(HashMap<String, Object> param);

    int findMessageCheckListCount(HashMap<String, Object> paramMap);
}