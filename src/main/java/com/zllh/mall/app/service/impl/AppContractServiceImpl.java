package com.zllh.mall.app.service.impl;

import com.zllh.common.common.model.model_extend.RoleExtendBean;
import com.zllh.mall.app.service.IAppContractService;
import com.zllh.mall.common.dao.MtCtrMapper;
import com.zllh.mall.common.model.AppData;
import com.zllh.mall.common.model.MtCtr;
import com.zllh.mall.contract.service.ICtractService;
import com.zllh.utils.common.DictionaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CFY on 2016/8/31.
 */
@Service
public class AppContractServiceImpl implements IAppContractService {

    @Autowired
    private ICtractService contractService;

    @Autowired
    private MtCtrMapper mtCtrMapper;

    @Override
    public int getMenuNums(String menuType, String mmbId) {
        return 0;
    }

    @Override
    public Map<String, Object> getListData(String menuType,String mmbId, int pageNo, int pageSize) {

        Map<String, Object> returnMap = new HashMap<String, Object>();


        int pendingStatus  = 0; //能查询到的待审批协议状态

        menuType = (menuType == null || "".equals(menuType.trim()) ? "buyContract" : menuType); //如果协议类型为空 则默认为采购协议

        //待审批协议页面
        pendingStatus = "buyContract".equals(menuType)? 2 : 1;//采购协议 则查询状态为“卖家确认” 销售协议则查询状态为“买家确认"

        // 分页查询数据map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mmbId", mmbId);
        map.put("type", "buyContract".equals(menuType) ? 1 : 2);
        map.put("pendingStatus", pendingStatus);

        int count = contractService.queryContracts(map).size();

        // 分页数据初始化
        pageSize = pageSize == 0 ? 10 : pageSize;
        int pageStartNo = pageNo == 0 ? 0 : ((pageNo - 1) * pageSize );

        // 接收页面参数并传递给service
        map.put("startFirst", pageStartNo);
        map.put("startEnd", pageSize);

        List<MtCtr> list = contractService.queryContracts(map);

        List <AppData> listData = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //处理为app所需格式
        for(MtCtr mtCtr : list){
            String bizType = "buyContract".equals(menuType) ?  "采购":"销售";
            //顺德申请采购关系
            String content = mtCtr.getMmbName()+"申请"+bizType+"协议"+","+mtCtr.getContractTitle();


            AppData appData = new AppData(menuType, mtCtr.getId(),content,mtCtr.getOperateTime() == null ? "" : sdf.format(mtCtr.getOperateTime()));

            listData.add(appData);
        }

        returnMap.put("total", count);
        returnMap.put("listData", listData);
        returnMap.put("menuType", menuType);

        if(listData != null && listData.size() > 0){
            returnMap.put("success",true);
            returnMap.put("msg","获取数据成功!");
        }else{
            returnMap.put("success",false);
            returnMap.put("msg","获取数据失败!");
        }

        return returnMap;
    }

    @Override
    public Map<String, Object> getDetailData(String menuType, String id) {
        return null;
    }

    public Map<String,Object> agree( String menuType,String id,  String address, String bankAccountCode, String bankAccountName){

        Map<String,Object> resultMap = new HashMap<>();

        String contractType = "buyContract".equals(menuType) ? "1" : "2"; // "1" 采购协议 2：销售协议
        resultMap.put("id",id);
        resultMap.put("address",address);
        resultMap.put("bankAccountCode",bankAccountCode);
        resultMap.put("bankAccountName",bankAccountName);
        resultMap.put("contractType",contractType);
        resultMap.put("nextStatus",DictionaryUtil.CONTRACT_BOTH_CONFIRM);//双方都同意

        int agreeNum = contractService.agreeContract(resultMap);

        if( agreeNum == 1){
            resultMap.put("success",true);
            resultMap.put("msg","操作成功！");
        }else{
            resultMap.put("success",false);
            resultMap.put("msg","操作失败！");
        }

        return  resultMap;

    }



    public Map<String,Object>  refuse(String id){

        int nextStatus = DictionaryUtil.CONTRACT_CANCELLED;//拒绝

        Map<String,Object> nextMap = new HashMap<>();
        nextMap.put("id",id);
        nextMap.put("nextStatus",nextStatus);
        int result = contractService.updateContractToNextStatus(nextMap);

        Map<String, Object> resultMap = new HashMap();
        if(result == 1){
            resultMap.put("success",true);
            resultMap.put("msg","操作成功！");
        }else{
            resultMap.put("success",false);
            resultMap.put("msg","操作失败！");

        }

        return resultMap;
    }

    @Override
    public List<String> getAppMenusByRoles(Map roles) {
        return mtCtrMapper.getAppMenusByRoles(roles);
    }

}
