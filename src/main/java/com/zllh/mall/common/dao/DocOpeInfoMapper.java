package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.DocOpeInfo;

public interface DocOpeInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(DocOpeInfo record);

    int insertSelective(DocOpeInfo record);

    DocOpeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DocOpeInfo record);

    int updateByPrimaryKey(DocOpeInfo record);
}