package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.MtCtrCtg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MtCtrCtgMapper {
    int insert(MtCtrCtg record);

    int insertSelective(MtCtrCtg record);

    List<MtCtrCtg> getGoodsListByContractId(String contractId);

    int deleteCtrCtgByContractId(String contractId);
}