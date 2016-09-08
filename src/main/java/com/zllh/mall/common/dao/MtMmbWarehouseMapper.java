package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.MtMmbWarehouse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MtMmbWarehouseMapper {

    int deleteByPrimaryKey(String id);

    int insert(MtMmbWarehouse record);

    int insertSelective(MtMmbWarehouse record);

    MtMmbWarehouse selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MtMmbWarehouse record);

    int updateByPrimaryKey(MtMmbWarehouse record);

    List<MtMmbWarehouse> getMmbWareHouseByMmbId(String mmbId);

    List<MtMmbWarehouse> getMmbWareHouse(Map param);

    String getAddressIdByAddress(@Param("address")String address,@Param("mmbId")String mmbId);

    int getNumByAddressAndId(@Param("address")String address,@Param("mmbId")String mmbId,@Param("id") String id);

}