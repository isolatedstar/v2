package com.zllh.mall.app.service.impl;

import com.zllh.mall.app.service.IAppOperateRelationService;
import com.zllh.mall.common.dao.MtMemberRelationshipMapper;
import com.zllh.mall.common.model.AppData;
import com.zllh.mall.common.model.MtMemberRelationship;
import com.zllh.mall.mmbmmanage.service.IMmbRelationshipService;
import com.zllh.utils.common.DictionaryUtil;
import org.apache.commons.lang3.StringUtils;
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
public class AppOperateRelationServiceImpl implements IAppOperateRelationService {

    @Autowired
    private IMmbRelationshipService mmbRelationshipService;

    @Autowired
    private MtMemberRelationshipMapper mmbRelationShipMapper;// 会员关系dao


    @Override
    public int getMenuNums(String menuType, String mmbId) {
        return 0;
    }

    @Override
    public Map<String, Object> getListData(String menuType ,String mmbId, int pageNo, int pageSize) {

        Map<String, Object> returnMap = new HashMap<String, Object>();
        // 查询条件
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isBlank(mmbId))
            map.put("mmbId", mmbId);
        // 执行条件查询总数(以mmbid为买方id查询总数)
        int count = mmbRelationShipMapper.queryMmbRelationships(map).size();
        // 分页数据初始化
        // 处理页码和页面记录数
        pageSize = (pageSize == 0 || pageSize < 1) ? 10 : pageSize;

        int pageStartNo = pageNo == 0 ? 0 : ((pageNo - 1) * pageSize );

        // 分页查询数据map
        map.put("startFirst", pageStartNo);
        map.put("startEnd", pageSize);
        // 执行查询得到list数据
        List<MtMemberRelationship> list = mmbRelationShipMapper.queryMmbRelationships(map);


        List <AppData> listData = new ArrayList<>();
        //处理为app所需格式
        for(MtMemberRelationship mmbRelation : list){
            //顺德向我销售
            String content = mmbRelation.getFname()+"申请"+mmbRelation.getBizType_();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            AppData appData = new AppData(menuType, mmbRelation.getId(),content,mmbRelation.getCreateTime() == null ? "" : sdf.format(mmbRelation.getCreateTime()));
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
        // 返回数据
        return returnMap;
    }

    @Override
    public Map<String, Object> getDetailData(String menuType, String id) {
        return null;
    }

    public Map<String,Object> agree( String id){

        MtMemberRelationship mmbRelationship = new MtMemberRelationship();
        mmbRelationship.setId(id);
        mmbRelationship.setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_NOMAL);
        // 执行修改状态
        boolean flag = mmbRelationshipService.updateMmbRelationship(mmbRelationship);

        Map<String, Object> resultMap = new HashMap();
        if(flag){
            resultMap.put("msg", "操作成功！");
            resultMap.put("success", true);
        }else{
            resultMap.put("msg", "操作失败！");
            resultMap.put("success",false);

        }
        return resultMap;
    }



    public Map<String,Object>  refuse(String id){

       boolean  flag = mmbRelationshipService.deleteMmbRelationshipById(id);

        Map<String, Object> resultMap = new HashMap();
        if(flag){
            resultMap.put("msg","操作成功！");
            resultMap.put("success", true);
        }else{
            resultMap.put("msg","操作失败！");
            resultMap.put("success",false);
        }

        return resultMap;
    }


}
