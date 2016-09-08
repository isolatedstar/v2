package com.zllh.mall.app.service.impl;

import com.zllh.common.common.model.model_extend.RoleExtendBean;
import com.zllh.mall.app.controller.ElementApp;
import com.zllh.mall.app.service.*;
import com.zllh.mall.common.dao.MtCtrMapper;
import com.zllh.mall.common.model.AppData;
import com.zllh.mall.common.model.MtMmbBankAccount;
import com.zllh.mall.common.model.MtMmbWarehouse;
import com.zllh.mall.mmbmmanage.service.IMmbBankAccountService;
import com.zllh.mall.mmbmmanage.service.IMmbWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by CFY on 2016/8/29.
 */
@Service
public class AppServiceImpl implements IAppService {

    @Autowired
    private IMmbBankAccountService mmbBankAccountService;//银行账号

    @Autowired
    private IMmbWarehouseService mmbWarehouseService; //收发货地址

    @Autowired
    private IAppRelationService appRelationService;

    @Autowired
    private IAppOperateRelationService appOperateRelationService;

    @Autowired
    private IAppContractService appContractService;

    @Autowired
    private IAppOrderService appOrderService;

    @Autowired
    private IAppSettleService appSettleService;

    @Autowired
    private MtCtrMapper mtCtrMapper;

    @Override
    public Map<String, Object> getMenuData(String parentMenuType, String mmbId, List<RoleExtendBean> roleList) {


        List<AppData> menuList = new ArrayList<AppData>();
        Map<String, Object> menuMap = new HashMap<>();

        AppData appData = null;
        String menuType = "";
        String menuTpeDesc = "";
        int menuNums = 0; //根据 getListData 返回的map中的total赋值。

      /*  String[] allSchedules = {"operateRelation", "buyContract", "sellContract", "buyOrder", "sellOrder", "getMoneySettle", "sendMoneySettle"};
        String[] allRelations = { "myConcern", "buyer", "seller"};*/

        Map<String,Object> param = new HashMap<>();
        param.put("roles",roleList);
        param.put("parentMenu",parentMenuType);

        List<String> appMenus = null;

        appMenus= appContractService.getAppMenusByRoles(param);

        if(appMenus !=null){
            for(String menu : appMenus){
                menuType = menu;
                menuTpeDesc = ElementApp.getMenuDescByType(menuType);

                menuNums = getListData(menuType, mmbId, 0, 0).get("total") == null ? 0 : Integer.parseInt(getListData(menuType, mmbId, 0, 0).get("total").toString());

                appData = new AppData(menuType, menuTpeDesc,menuNums);

                menuList.add(appData);

            }
        }

        menuMap.put("Obj", menuList);

        if (menuList != null && menuList.size() > 0) {
            menuMap.put("success", true);
            menuMap.put("msg", "获取目录数据成功!");
        } else {
            menuMap.put("success", false);
            if(appMenus == null || appMenus.size() ==0){
                menuMap.put("msg", "您没有相应权限!");
            }else{
                menuMap.put("msg", "schedule".equals(parentMenuType)? "获取待办事宜目录数据失败!":"获取待关系会员目录数据失败!");
            }
        }

        return menuMap;
    }

    @Override
    public int getMenuNums(String menuType, String mmbId) {

        Map<String, Object> numMap = getListData(menuType, mmbId, 0, 0);
        if (numMap != null && numMap.get("total") != null) {
            return Integer.parseInt(numMap.get("total").toString());
        } else {
            return 0;
        }

    }

    /**
     * @param menuType  目录类型
     * @param mmbId     当前会员ID
     * @param pageNo    当前页
     * @param pageSize  每页行数
     * @return
     */

    /**
     * menuType ---- menuDesc对应关系
     * --待办事宜 --
     * operateRelation	待确认合作关系
     * buyContract	待审采购协议
     * sellContract	待审销售协议
     * buyOrder	待签采购订单
     * sellOrder	待签销售订单
     * getMoneySettle	待确认应收账款
     * sendMoneySettle	待确认应付账款
     * <p/>
     * ----会员目录---
     * myConcern	我关注的会员
     * buyer	    采购合作会员
     * seller	    销售合作会员
     */

    @Override
    public Map<String, Object> getListData(String menuType, String mmbId, int pageNo, int pageSize) {

        Map<String, Object> listMap = new HashMap<>();


        switch (menuType){

            case "operateRelation" :
                listMap = appOperateRelationService.getListData(menuType, mmbId, pageNo, pageSize);
                break;
            case "buyContract" : case "sellContract" :
                listMap = appContractService.getListData(menuType, mmbId, pageNo, pageSize);
                break;
            case "buyOrder" : case "sellOrder" :
                listMap = appOrderService.getListData(menuType, mmbId, pageNo, pageSize);
                break;
            case "getMoneySettle" : case "payMoneySettle" :
                listMap = appSettleService.getListData(menuType, mmbId, pageNo, pageSize);
                break;
            case "myConcern" :case "buyer" :case "seller" :
                listMap = appRelationService.getListData(menuType, mmbId, pageNo, pageSize);
                break;
            default:

                break;

        }


        return listMap;
    }

    @Override
    public Map<String, Object> getDetailData(String menuType, String id) {
        return null;
    }

    @Override
    public Map<String, Object> getBankAndAddressList(String menuType, String mmbId) {

        List<MtMmbWarehouse> addressList = new ArrayList<>(); //收发货地址
        List<MtMmbBankAccount> bankList = new ArrayList<>(); //收付款账号

        String bankAccountNo = "";//银行账号
        String addressName = "";//地址

        addressList = mmbWarehouseService.getMmbWareHouseByMmbId(mmbId);
        bankList = mmbBankAccountService.getMmbBankAccountByMmbId(mmbId);

        List<AppData> appAddressList = new ArrayList<>(); //收发货地址
        List<AppData> appBankList = new ArrayList<>(); //收付款账号

        for(MtMmbWarehouse address : addressList){
            AppData appData = new AppData(address.getAreaId(),address.getAddress());
            appAddressList.add(appData);
        }

        for(MtMmbBankAccount bank : bankList){
            AppData appData = new AppData(bank.getAccountno(),bank.getAccountname());
            appBankList.add(appData);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("addressList", appAddressList);
        resultMap.put("bankList", appBankList);

        return resultMap;

    }

    @Override
    public Map<String, Object> agree(String menuType, String id, String address, String bankAccountCode, String bankAccountName) {

        Map<String, Object> agreeMap = new HashMap<>();


        switch (menuType){

            case "operateRelation" :
                agreeMap = appOperateRelationService.agree(id);
                break;
            case "buyContract" : case "sellContract" :
                agreeMap = appContractService.agree( menuType,  id,  address,  bankAccountCode,  bankAccountName);
                break;

            default:

                break;

        }


        return agreeMap;
    }

    @Override
    public Map<String, Object> refuse(String menuType, String id) {

        Map<String, Object> refuseMap = new HashMap<>();


        switch (menuType){

            case "operateRelation" :
                refuseMap = appOperateRelationService.refuse(id);
                break;
            case "buyContract" : case "sellContract" :
                refuseMap = appContractService.refuse(id);
                break;

            default:

                break;

        }


        return refuseMap;
    }

    @Override
    public Map<String, Object> getAppVersion() {

        Map<String,Object> resultMap  = mtCtrMapper.getAppVersion();

        if(resultMap.get("version")  == null || resultMap.get("version")  == ""){
            resultMap.put("success",false);
            resultMap.put("msg","获取版本号失败！");
        }else{
            resultMap.put("success",true);
            resultMap.put("msg","获取版本号成功！");
        }

        return resultMap;
    }

}
