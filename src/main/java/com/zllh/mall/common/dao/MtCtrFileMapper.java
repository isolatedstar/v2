package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.MtCtrFile;

public interface MtCtrFileMapper {
    int insert(MtCtrFile record);

    int insertSelective(MtCtrFile record);
}