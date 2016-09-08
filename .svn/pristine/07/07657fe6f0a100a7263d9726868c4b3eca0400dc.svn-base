package com.zllh.mall.app.service.impl;

import com.zllh.mall.app.service.IAppContractService;
import com.zllh.mall.app.service.IAppOrderService;
import com.zllh.mall.common.model.AppData;
import com.zllh.mall.common.model.MtCtr;
import com.zllh.mall.common.model.MtOrdertitle;
import com.zllh.mall.contract.service.ICtractService;
import com.zllh.mall.order.service.IOrderService;
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
public class AppOrderServiceImpl implements IAppOrderService {

    @Autowired
    private IOrderService orderService;


    @Override
    public Map<String, Object> getListData(String menuType,String mmbId, int pageNo, int pageSize) {

        Map<String, Object> returnMap = new HashMap<String, Object>();


//        String name = request.getParameter("name");
//        String orderId = request.getParameter("orderId");

       // String status = request.getParameter("status");   //订单类型  1:全部 2:采购 3:销售



        String status = "buyOrder".equals(menuType) ? "2" : "3"; // 1:全部 2:采购 3:销售

       // String executeStatus = request.getParameter("executeStatus");  //订单状态1 :执行中 2:已完成


//        String startTime = request.getParameter("startTime");
//        String endTime = request.getParameter("endTime");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", mmbId);

//        if(name!=null&&!"".equals(name)){
//            map.put("oppositeName", name);
//        }
//        if(orderId!=null&&!"".equals(orderId)){
//            map.put("ordertitleCode", orderId);
//        }
//        if(executeStatus!=null&&!"".equals(executeStatus)){
//            map.put("executeStatus", executeStatus);
//        }
//        if (startTime != null && !"".equals(startTime)) {
//            map.put("executeStartTime", startTime);
//        }
//        if (endTime != null && !"".equals(endTime)) {
//            map.put("executeEndTime", endTime);
//        }
        if (status != null && !"".equals(status)) {
            map.put("orderstatus", status);
        }
        int count = orderService.queryPendingOrderList(map).size();
        // 执行条件查询总数
        // 分页数据初始化

        // 分页数据初始化
        pageSize = pageSize == 0 ? 10 : pageSize;

        int pageStartNo = pageNo == 0 ? 0 : ((pageNo - 1) * pageSize );

        // 接收页面参数并传递给service
        map.put("startFirst", pageStartNo);
        map.put("startEnd", pageSize);
        List<MtOrdertitle> orderList = orderService.queryPendingOrderList(map);

        //封装为APP格式
        List<AppData> listData = new ArrayList<>();
        String content = "";//待办事项具体内容  订单号+订单对方
        AppData appData = null;

        for(MtOrdertitle mtOrder : orderList){
            //采购订单
            if("buyOrder".equals(menuType)){
                content = mtOrder.getOrdertitleCode()+","+mtOrder.getSellersName();
            }else if ("sellOrder".equals(menuType)){
                //销售订单
                content = mtOrder.getOrdertitleCode()+","+mtOrder.getBuyersName();
            }
            appData = new AppData(menuType,mtOrder.getId(),content,"");
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



        /*String json = ServletRequestUtils.getStringParameter(request, "param", "");
        Map<String, Object> returnMap = new HashMap<String, Object>();
        UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
        String memberId = "";
        String userId = "";
        String userName = "";
        if(userExtendBean!=null){
            userId = userExtendBean.getUserId();                                          //操作员id
            userName = userExtendBean.getUserName();                                      //操作员name
            memberId = userExtendBean.getMuser().getMmbId();                              //根据操作员信息获取当前操作员的所属会员id
        }
        //从session中获取当前登录会员信息和订单对象中的买卖双方比较，来设置订单标题表中的"status"值
        //标记是buyer或者seller
        String bos = "";
        int success = 0;
        int fail = 0;
        if(json!= null && !"".equals(json)){
            List<MtOrdertitle> mtOrder = JSON.parseArray(json, MtOrdertitle.class);
            for (MtOrdertitle orderTitle : mtOrder) {
                try {
                    if (memberId.equals(orderTitle.getBuyersId())) {
                        bos = "b";
                    } else {
                        bos = "s";
                    }
                    // 买方锁定并且卖方已签字
                    if (bos.equals("b") && orderTitle.getStatus() == 3) {
                        orderTitle.setStatus(4);
                        orderTitle.setUserId(userId);
                        orderTitle.setUserName(userName);
                        orderTitle.setLockTime(new Date());
                        // 买方锁定并且卖方未签字
                    } else if (bos.equals("b") && orderTitle.getStatus() < 2) {
                        orderTitle.setStatus(2);
                    }
                    // 卖方锁定并且买方已签字
                    if (bos.equals("s") && orderTitle.getStatus() == 2) {
                        orderTitle.setStatus(4);
                        orderTitle.setUserId(userId);
                        orderTitle.setUserName(userName);
                        orderTitle.setLockTime(new Date());
                        // 卖方锁定并且买方未签字
                    } else if (bos.equals("s") && orderTitle.getStatus() < 3) {
                        orderTitle.setStatus(3);
                    }
                    orderService.updateLockOrder(orderTitle,userId,userName);
                    success++;
                }catch(Exception e){
                    fail++;
                }
            }
            returnMap.put("msg", "订单锁定成功"+success+"条\n订单锁定失败"+fail+"条");
        }else{
            returnMap.put("msg", "订单锁定失败！");
        }
        return returnMap;

        int agreeNum = 0;

        if( agreeNum == 1){
            resultMap.put("success",true);
            resultMap.put("msg","操作成功！");
        }else{
            resultMap.put("success",false);
            resultMap.put("msg","操作失败！");
        }*/

        return  resultMap;

    }



    public Map<String,Object>  refuse(String id){

        int nextStatus = DictionaryUtil.CONTRACT_CANCELLED;//拒绝

        Map<String,Object> nextMap = new HashMap<>();

        Map<String, Object> resultMap = new HashMap();
//        if(result == 1){
//            resultMap.put("success",true);
//            resultMap.put("msg","操作成功！");
//        }else{
//            resultMap.put("success",false);
//            resultMap.put("msg","操作失败！");
//
//        }

        return resultMap;
    }

}
