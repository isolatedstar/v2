package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.DocInfo;
import com.zllh.mall.common.model.DocInfoExample;
import org.apache.ibatis.annotations.Param;

public interface DocInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(DocInfo record);

    int insertSelective(DocInfo record);

    DocInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DocInfo record, @Param("example") DocInfoExample example);

    int updateByExample(@Param("record") DocInfo record, @Param("example") DocInfoExample example);

    int updateByPrimaryKeySelective(DocInfo record);

    int updateByPrimaryKey(DocInfo record);
}