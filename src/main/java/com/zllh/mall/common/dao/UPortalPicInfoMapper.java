package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.UPortalPicInfo;

public interface UPortalPicInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UPortalPicInfo record);

    int insertSelective(UPortalPicInfo record);

    UPortalPicInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UPortalPicInfo record);

    int updateByPrimaryKey(UPortalPicInfo record);
}