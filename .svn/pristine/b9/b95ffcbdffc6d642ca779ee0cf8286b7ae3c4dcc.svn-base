package com.zllh.mall.app.service;


import java.util.Map;

/**
 * Created by CFY on 2016/8/29.
 */
public interface IAppSettleService {

    /**
     * 获取 列表数据
     * @param menuType  目录类型
     * @param pageNo    当前页
     * @param pageSize  每页行数
     * @return
     */
    public  Map<String,Object> getListData(String menuType, String mmbId, int pageNo, int pageSize);

    /**
     * 获取 详情数据
     * @param menuType 目录类型
     * @param id  点击行ID
     * @return
     */
    public  Map<String,Object> getDetailData(String menuType, String id);




    /**
     * 同意操作
     * @param menuType  菜单类型
     * @param id   待办ID
     * @param address  仓库地址
     * @param bankAccountCode 账号编码
     * @return bankAccountName 账号名称
     */

    public Map<String,Object> agree(String menuType, String id, String address, String bankAccountCode, String bankAccountName);

    /**
     * 拒绝操作
     * @param id   	ID
     * @return
     */
    public Map<String,Object>  refuse(String id);


}
