package com.zllh.mall.app.service;


import com.zllh.common.common.model.model_extend.RoleExtendBean;

import java.util.List;
import java.util.Map;

/**
 * Created by CFY on 2016/8/29.
 */
public interface IAppService {

    /**
     * 根据菜单类型 获取菜单目录及其对应个数
     * @param menuType
     * @return
     */
    public Map<String,Object> getMenuData(String menuType,String mmbId, List<RoleExtendBean> roleList);

    /**
     * 得到目录数量
     * @param menuType
     * @param mmbId
     * @return
     */
    public int getMenuNums(String menuType,String mmbId);
    /**
     * 获取 列表数据
     * @param menuType  目录类型
     * @param pageNo    当前页
     * @param pageSize  每页行数
     * @return
     */
    public  Map<String,Object> getListData(String menuType,String mmbId,int pageNo,int pageSize);

    /**
     * 获取 详情数据
     * @param menuType 目录类型
     * @param id  点击行ID
     * @return
     */
    public  Map<String,Object> getDetailData(String menuType ,String id);

    /**
     * 点击同意按钮时根据当前用户得到 银行账号列表,仓库地址列表 供同意功能使用
     * @param menuType
     * @param mmbId
     * @return
     */
    public Map<String,Object> getBankAndAddressList(String menuType ,String mmbId);


    /**
     * 同意待办
     * @param menuType  菜单类型
     * @param id   待办ID
     * @param address  仓库地址
     * @param bankAccountCode 账号编码
     * @return bankAccountName 账号名称
     * @return
     */
    public  Map<String,Object> agree(String menuType,String id,  String address, String bankAccountCode, String bankAccountName);

    /**
     * 拒绝待办
     * @param menuType
     * @param id
     * @return
     */
    public  Map<String,Object> refuse(String menuType,String id);

    /**
     * 得到app版本号 并更新
     * @return
     */
    Map<String,Object> getAppVersion();

}
