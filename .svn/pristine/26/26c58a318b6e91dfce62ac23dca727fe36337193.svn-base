package com.zllh.mall.mmbmmanage.service;

import com.zllh.mall.common.model.MtMmbWarehouse;

import java.util.List;
import java.util.Map;

/**
 * Created by cfy on 2016/7/5.
 */
public interface IMmbWarehouseService {


    /**
     * 根据会员ID得到会员仓库地址
     * @param param 登陆会员ID 地址
     * @return
     */
    public  List<MtMmbWarehouse> getMmbWareHouse(Map param);


    /**
     * 根据会员ID得到会员仓库地址
     * @param mmbId 会员ID
     * @return
     */
    public  List<MtMmbWarehouse> getMmbWareHouseByMmbId(String mmbId);



    /**
     * 根据会员仓库ID得到会员仓库地址
     * @param mmbId 登陆会员ID
     * @return
     */
    public  MtMmbWarehouse getMmbWareHouseById(String mmbId);

    /**
     * 根据主键 删除会员仓库
     * @param id
     * @return
     */
    public Map<String,Object> deleteMmbWareById(String id);

    /**
     * 修改会员仓库
     * @param mmbWarehouse
     * @return
     */
    public Map<String,Object> updateMmbWareById(MtMmbWarehouse mmbWarehouse);

    /**
     * 新增会员仓库
     * @param mmbWarehouse
     * @return
     */
    public Map<String,Object> createMmbWare(MtMmbWarehouse mmbWarehouse);

    /**
     * 根据地址得到地质ID
     * @param address
     * @return
     */
    public String  getAddressIdByAddress(String address,String mmbId);

    /**
     * 验证会员地址唯一性
     * @param address
     * @return
     */
    public int getNumByAddressAndId(String address,String mmbId,String id);
}
