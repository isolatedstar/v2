package com.zllh.mall.app.service;


import java.util.Map;

/**
 * Created by CFY on 2016/8/29.
 */
public interface IAppOperateRelationService {

    /**
     * 得到目录数量
     * @param menuType
     * @param mmbId
     * @return
     */
    public int getMenuNums(String menuType, String mmbId);
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
     * @param id
     * @param
     * @return
     */

    public Map<String,Object> agree(String id);

    /**
     * 拒绝操作
     * @param id   	ID
     * @return
     */
    public Map<String,Object>  refuse(String id);




}
