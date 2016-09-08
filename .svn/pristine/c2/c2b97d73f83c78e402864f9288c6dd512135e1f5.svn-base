package com.zllh.mall.app.service.impl;

import com.zllh.mall.app.service.IAppOrderService;
import com.zllh.mall.app.service.IAppSettleService;
import com.zllh.mall.common.dao.MtSettleMapper;
import com.zllh.mall.common.model.AppData;
import com.zllh.mall.common.model.MtOrdertitle;
import com.zllh.mall.common.model.MtSettle;
import com.zllh.mall.order.service.IOrderService;
import com.zllh.mall.settle.service.ISettleService;
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
public class AppSettleServiceImpl implements IAppSettleService {

    @Autowired
    private ISettleService settleService;

    @Autowired
    private MtSettleMapper mtSettleMapper;


    @Override
    public Map<String, Object> getListData(String menuType,String mmbId, int pageNo, int pageSize) {

        Map<String, Object> returnMap = new HashMap<String, Object>();

        String settleType = "getMoneySettle".equals(menuType) ? "get" : "pay";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", mmbId);
        map.put("settleType", settleType);

        int count = settleService.searchPendingSettleForApp(map).size();
        // 执行条件查询总数
        // 分页数据初始化
        pageSize = pageSize == 0 ? 10 : pageSize;
        int pageStartNo = pageNo == 0 ? 0 : ((pageNo - 1) * pageSize );
        // 接收页面参数并传递给service
        map.put("startFirst", pageStartNo);
        map.put("startEnd", pageSize);
        List<MtSettle> result = settleService.searchPendingSettleForApp(map);

        //封装为APP格式
        List<AppData> listData = new ArrayList<>();
        String content = "";//待办事项具体内容  结款单号+结款对方
        AppData appData = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(MtSettle mtSettle : result){
            //应收结款
            if("get".equals(settleType)){
                content = mtSettle.getSettleCode()+"   付款方："+mtSettle.getMmbpayName()+","+"金额："+mtSettle.getCtrMoney()+"  时间："+(mtSettle.getCtrTime()==null?"":sdf.format(mtSettle.getCtrTime()));
            }else if ("pay".equals(settleType)){
                //应付结款
                content = mtSettle.getSettleCode()+"   收款方："+mtSettle.getMmbgetName()+","+"金额："+mtSettle.getCtrMoney()+"  时间："+(mtSettle.getCtrTime()==null?"":sdf.format(mtSettle.getCtrTime()));
            }
            appData = new AppData(menuType,mtSettle.getId(),content,"");
            listData.add(appData);
        }

        returnMap.put("total", count);
        returnMap.put("listData", listData);

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

        return  resultMap;

    }



    public Map<String,Object>  refuse(String id){

        int nextStatus = DictionaryUtil.CONTRACT_CANCELLED;//拒绝

        Map<String,Object> nextMap = new HashMap<>();

        Map<String, Object> resultMap = new HashMap();

        return resultMap;
    }

}
