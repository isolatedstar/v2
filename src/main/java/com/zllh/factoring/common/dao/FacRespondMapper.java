package com.zllh.factoring.common.dao;

import com.zllh.factoring.common.model.FacRespond;

public interface FacRespondMapper {
    int deleteByPrimaryKey(String id);

    int insert(FacRespond record);

    int insertSelective(FacRespond record);

    FacRespond selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FacRespond record);

    int updateByPrimaryKey(FacRespond record);

}