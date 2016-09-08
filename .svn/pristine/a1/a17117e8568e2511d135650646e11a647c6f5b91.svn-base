package com.zllh.mall.app.service;


import com.zllh.common.common.model.model_extend.RoleExtendBean;

import java.util.List;
import java.util.Map;

/**
 * Created by CFY on 2016/8/29.
 */
public interface IAppContractService {

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
     * @param menuType  菜单类型
     * @param id   待办ID
     * @param address  仓库地址
     * @param bankAccountCode 账号编码
     * @return bankAccountName 账号名称
     */

    public Map<String,Object> agree(String menuType,String id,  String address, String bankAccountCode, String bankAccountName);

    /**
     * 拒绝操作
     * @param id   	ID
     * @return
     */
    public Map<String,Object>  refuse(String id);

    /**
     * app登录成功后从sessionID中取出role列表 然后查询pub_role_app_menu_rel 得到app_menu权限列表
     * @param roles
     * @return
     */
    List<String> getAppMenusByRoles(Map roles);


}
