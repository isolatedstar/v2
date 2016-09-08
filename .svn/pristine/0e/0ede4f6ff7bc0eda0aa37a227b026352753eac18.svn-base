package com.zllh.mall.app.service;


import java.util.Map;

/**
 * Created by CFY on 2016/8/29.
 */
public interface IAppRelationService {

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
     * 调整会员关注等级
     * @param id  关注记录ID
     * @param grade 新关注等级
     */
    public Map<String, Object> changeConcernGrade(String id,String grade);


    /**
     * 点击升级至业务合作页面
     * @param mmbId 当前会员ID
     * @param relaMmbId 关系会员ID
     * @return
     */

    public Map<String, Object> toUpgradeOperation(String mmbId,String relaMmbId);

    /**
     * 停用合作关系
     * @param mmbId 当前会员ID
     * @param relaMmbId 关系会员ID
     * @param type 合作关系类型 1”: 采购 “2”: 销售
     * @return
     */
    public Map<String, Object> stopBizRelationShip(String mmbId,String relaMmbId,String type);

    /**
     * 启用合作关系
     * @param mmbId 当前会员ID
     * @param relaMmbId 关系会员ID
     * @param type 合作关系类型 1”: 采购 “2”: 销售
     * @return
     */
    public Map<String, Object> openBizRelationShip(String mmbId,String relaMmbId,String type);

    /**
     *  降级为关注
     * @param mmbId 当前会员ID
     * @param relaMmbId 关系会员ID
     * @return
     */
    public Map<String, Object> lowerToConcernOperation(String mmbId,String relaMmbId);

    /**
     * 取消关注
     * @param id 关系记录ID
     * @return
     */
    public Map<String, Object>  cancelConcern(String id);

}
