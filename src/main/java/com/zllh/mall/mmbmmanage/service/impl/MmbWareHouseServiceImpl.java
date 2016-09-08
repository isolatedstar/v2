package com.zllh.mall.mmbmmanage.service.impl;

import com.zllh.mall.common.dao.MtMmbWarehouseMapper;
import com.zllh.mall.common.model.MtMmbWarehouse;
import com.zllh.mall.mmbmmanage.service.IMmbWarehouseService;
import com.zllh.utils.common.UUIDCreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cfy on 2016/7/5.
 */

@Service
public class MmbWareHouseServiceImpl implements IMmbWarehouseService{

    @Autowired
    private MtMmbWarehouseMapper mtMmbWarehouseMapper;


    //查询会员仓库
    @Override
    public List<MtMmbWarehouse> getMmbWareHouse(Map param) {

        List<MtMmbWarehouse> mmbWarehouseList = new ArrayList<>();
        mmbWarehouseList = mtMmbWarehouseMapper.getMmbWareHouse(param);

        return mmbWarehouseList;
    }

    @Override
    public List<MtMmbWarehouse> getMmbWareHouseByMmbId(String mmbId) {
        return mtMmbWarehouseMapper.getMmbWareHouseByMmbId(mmbId);
    }

    //新增会员仓库
    @Override
    public Map<String, Object> createMmbWare(MtMmbWarehouse mmbWarehouse) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        int result = mtMmbWarehouseMapper.insert(mmbWarehouse);

        if(result > 0){
            resultMap.put("successMsg","创建会员仓库成功!");
        }else{
            resultMap.put("errorMsg","创建会员仓库失败!");
        }
        return resultMap;
    }

    @Override
    public String getAddressIdByAddress(String address,String mmbId) {
        return mtMmbWarehouseMapper.getAddressIdByAddress(address, mmbId);
    }

    @Override
    public int getNumByAddressAndId(String address, String mmbId,String id) {
        return mtMmbWarehouseMapper.getNumByAddressAndId(address, mmbId,id);
    }

    @Override
    public MtMmbWarehouse getMmbWareHouseById(String id) {
        return mtMmbWarehouseMapper.selectByPrimaryKey(id);
    }

    //删除会员仓库
    @Override
    public Map<String, Object> deleteMmbWareById(String id) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        int result = mtMmbWarehouseMapper.deleteByPrimaryKey(id);
        if(result > 0){
            resultMap.put("successMsg","删除会员仓库成功!");
        }else{
            resultMap.put("errorMsg","删除会员仓库失败!");
        }

        return resultMap;
    }

    //修改会员仓库
    @Override
    public Map<String, Object> updateMmbWareById(MtMmbWarehouse mmbWarehouse) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        int result = mtMmbWarehouseMapper.updateByPrimaryKey(mmbWarehouse);


        if(result > 0){
            resultMap.put("successMsg","修改会员仓库成功!");
        }else{
            resultMap.put("errorMsg","修改会员仓库失败!");
        }

        return resultMap;
    }


}
