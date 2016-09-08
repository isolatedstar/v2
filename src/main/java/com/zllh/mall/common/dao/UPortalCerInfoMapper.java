package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.UPortalCerInfo;

public interface UPortalCerInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UPortalCerInfo record);

    int insertSelective(UPortalCerInfo record);

    UPortalCerInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UPortalCerInfo record);

    int updateByPrimaryKey(UPortalCerInfo record);
}