package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.BusAreaTree;





public interface BusAreaTreeMapper {
    int deleteByPrimaryKey(String id);

    int insert(BusAreaTree record);

    int insertSelective(BusAreaTree record);

    BusAreaTree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BusAreaTree record);

    int updateByPrimaryKey(BusAreaTree record);
    //条件查询出一条书库
    BusAreaTree serachOne(Map<String, Object> params);
    //条件查询出多条数据
    List<BusAreaTree> searchMore(Map<String, Object> params);
    //根据diyuiId 查询出所属的省级
    BusAreaTree selectByChildId(String id);
}