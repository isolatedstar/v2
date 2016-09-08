package com.zllh.mall.app.service.impl;

import com.zllh.mall.app.service.IAppRelationService;
import com.zllh.mall.common.dao.MtMemberRelationshipMapper;
import com.zllh.mall.common.model.AppData;
import com.zllh.mall.common.model.MtMemberRelationship;
import com.zllh.mall.mmbmmanage.service.IMmbRelationshipService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CFY on 2016/8/31.
 */
@Service
public class AppRelationServiceImpl implements IAppRelationService {

    @Autowired
    private IMmbRelationshipService mmbRelationshipService;

    @Autowired
    private MtMemberRelationshipMapper mmbRelationShipMapper;// 会员关系dao


    @Override
    public Map<String, Object> getListData(String menuType,String mmbId, int pageNo, int pageSize) {

        Map<String, Object> returnMap = new HashMap<String, Object>();

        List<MtMemberRelationship> listData = null;
        int count = 0;

        List <AppData> listAppData = new ArrayList<>();

        if("myConcern".equals(menuType)){

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mmbId", mmbId);

            listData = mmbRelationshipService.queryMmbRelationShipsByGrade(map);

            count = listData == null ? 0 : listData.size();

            //处理为app所需格式
            for(MtMemberRelationship mmbr : listData){
                AppData appData = new AppData(menuType, mmbr.getId(),mmbr.getRelaMmbId(), mmbr.getFname(),mmbr.getRelaGrade());

                listAppData.add(appData);
            }

        }else {

            // buyer :采购合作会员  seller:销售合作会员
            menuType = (menuType == null || "".equals(menuType.trim()) ? "buyer" : menuType); //如果协议类型为空 则默认为采购协议

            // 获取当前会员id以及点击的合作类型
            //0:买 1：卖
            String type = "buyer".equals(menuType) ? "0" : "1";

            Map<String, Object> map = new HashMap<String, Object>();

            // 不为空查询数据返回界面
            if (!StringUtils.isBlank(mmbId) && !StringUtils.isBlank(type)) {
                map.put("mmbId", mmbId);
                map.put("bizType", Integer.parseInt(type));
                listData = mmbRelationshipService.queryMmbRelationShipForBuy(map);
            }


            // 分页查询数据map
            count = listData == null ? 0 : listData.size();

            // 分页数据初始化
            pageSize = pageSize == 0 ? 10 : pageSize;

            int pageStartNo = pageNo == 0 ? 0 : ((pageNo - 1) * pageSize );

            // 接收页面参数并传递给service
            map.put("startFirst", pageStartNo);
            map.put("startEnd", pageSize);

            listData = mmbRelationshipService.queryMmbRelationShipForBuy(map);

            //处理为app所需格式
            AppData appData;
            for(MtMemberRelationship mmbr : listData){
                if( "buyer".equals(menuType)){
                    appData = new AppData(menuType, mmbr.getId(),mmbr.getRelaMmbId(), mmbr.getFname(),"relation");
                }else{
                    appData = new AppData(menuType, mmbr.getId(),mmbr.getMmbId(), mmbr.getFname(),"relation");
                }

                listAppData.add(appData);
            }

        }


        returnMap.put("total", count);
        returnMap.put("listData", listAppData);
        returnMap.put("menuType", menuType);

        if(listAppData != null && listAppData.size() > 0){
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



    public Map<String, Object> changeConcernGrade(String id,String grade) {

        Map<String, Object> changeMap = new HashMap<>();
        // 获取当前会员id,关系会员id,以及关注等级
        // 封装待修改数据
        MtMemberRelationship mmbRela = new MtMemberRelationship();
        mmbRela.setId(id);

        if(Integer.valueOf(grade)> 5 || Integer.valueOf(grade) < 0){

            changeMap.put("success",false);
            changeMap.put("msg","关注等级不合法!");
        }else{
            mmbRela.setRelaGrade(Integer.valueOf(grade));
            // 调用service，执行修改
            int result = mmbRelationShipMapper.updateMmbRelationship(mmbRela);
            if (result == 1) {
                changeMap.put("success",true);
                changeMap.put("msg","调整等级成功!");
            }else{
                changeMap.put("success",false);
                changeMap.put("msg","调整等级失败!");
            }
        }

        return changeMap;

    }


    public Map<String, Object> toUpgradeOperation(String mmbId,String relaMmbId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取mmbid,relaMmbId待存入
        //通过mmbid以及relaMmbId查询买卖关系返回map
        map = this.mmbRelationshipService.upMmbRelaToBizOperation(mmbId, relaMmbId);

        if(map!=null && map.size()>0){
            map.put("success",true);
            map.put("msg","获取数据成功!");
        }else{
            map.put("success",false);
            map.put("msg","获取数据失败!");
        }

        return map;
    }


    public Map<String, Object> stopBizRelationShip(String mmbId,String relaMmbId,String type) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = mmbRelationshipService.stopBizRelationShip(mmbId, relaMmbId, type);
        if("0".equals(result)){
            resultMap.put("success",true);
            resultMap.put("msg","停用成功!");
        }else{
            resultMap.put("success",false);
            resultMap.put("msg","停用失败!");
        }

        return resultMap;
    }

    public Map<String, Object> openBizRelationShip(String mmbId,String relaMmbId,String type) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        //获取参数
        String result = mmbRelationshipService.openBizRelationShip(mmbId, relaMmbId, type);
        if("0".equals(result)){
            resultMap.put("success",true);
            resultMap.put("msg","启用成功!待对方审批通过后即可使用！");
        }else{
            resultMap.put("success",false);
            resultMap.put("msg","启用失败!");
        }

        return resultMap;
    }

    public Map<String, Object> lowerToConcernOperation(String mmbId,String relaMmbId) {

        Map<String, Object> lowerMap = new HashMap<String, Object>();

        // 获取mmbid,relaMmbId
        // 降级为关注其实就是将保存的两条业务合作关系数据删除
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("mmbId", mmbId);
        map.put("relaMmbId", relaMmbId);

        //降级为仅关注 直接修改 关系类型为 0（关注）和关注等级为1级
        int flag = mmbRelationshipService.lowerToConcernOperation(map);

        if (flag == 1) {
            lowerMap.put("success",true);
            lowerMap.put("msg","降级为仅关注成功!");
        }else{
            lowerMap.put("success",false);
            lowerMap.put("msg","降级为仅关注失败!");
        }

        return lowerMap;
    }

    public Map<String, Object>  cancelConcern(String id){

        //直接删除会员关系 记录
        boolean flag = mmbRelationshipService.deleteMmbRelationshipById(id);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        //获取参数
        if(flag){
            resultMap.put("success",true);
            resultMap.put("msg", "取消关注成功!");
        }else{
            resultMap.put("success",false);
            resultMap.put("msg", "取消关注失败!");
        }

        return  resultMap;
    }
}
