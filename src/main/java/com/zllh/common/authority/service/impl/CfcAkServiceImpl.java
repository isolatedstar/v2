package com.zllh.common.authority.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.common.authority.service.CfcAkService;
import com.zllh.mall.common.dao.MtSettleMapper;
import com.zllh.mall.common.enums.PayGetTypeEnum;
import com.zllh.mall.common.enums.StatusSignEnum;
import com.zllh.mall.common.model.MtSettle;
import com.zllh.utils.base.Utils;

@Service
public class CfcAkServiceImpl implements CfcAkService {
    
    @Autowired
    private MtSettleMapper mtSettleMapper;
    
    @Override
    public String executeCfcakHandle(String signedContent, String signature, String opertion) {
        
        JSONObject json = JSONObject.fromObject(opertion);
        try {
            boolean bool = Utils.cfcUtil.rsaP7VerifyDettach(signature, signedContent);
            if(!bool) return "验签失败请重试！";
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int flag = json.getInt("flag");
        String userId = json.getString("userId");
        String settleId = json.getString("settleId");
        MtSettle sel = mtSettleMapper.selectByPrimaryKey(settleId);
        if(sel==null) return " 结款单号有误！";
        if(StatusSignEnum.SIGN.getValue().equals(sel.getStatusSign())) return "结款单已签成功无需重复签名！";
        if(StatusSignEnum.PAYSIGN.getValue().equals(sel.getStatusSign())&&Integer.valueOf(flag).equals(PayGetTypeEnum.PAY.getValue())) return "贵方已经签章无需重复签章！";
        if(StatusSignEnum.GETSIGN.getValue().equals(sel.getStatusSign())&&Integer.valueOf(flag).equals(PayGetTypeEnum.GET.getValue())) return "贵方已经签章无需重复签章！";
        
        MtSettle settle = new MtSettle();
        settle.setId(settleId);
        if(sel.getSignInfo()==null||"".equals(sel.getSignInfo())){
            if(PayGetTypeEnum.PAY.getValue().equals(Integer.valueOf(flag))){
                settle.setStatusSign(StatusSignEnum.PAYSIGN.getValue());
                settle.setPayInfowithsign(signature);
                settle.setPayOperator(userId);
            }else{
                settle.setStatusSign(StatusSignEnum.GETSIGN.getValue());
                settle.setGetInfowithsign(signature);
                settle.setGetOperator(userId);
            }
            settle.setSignInfo(signedContent);
        }else{
            
            boolean bol = true;
            try {
                if(StatusSignEnum.PAYSIGN.getValue().equals(sel.getStatusSign())){
                    bol = Utils.cfcUtil.rsaP7VerifyDettach(sel.getPayInfowithsign(), sel.getSignInfo());
                }
                if(StatusSignEnum.GETSIGN.getValue().equals(sel.getStatusSign())){
                    bol = Utils.cfcUtil.rsaP7VerifyDettach(sel.getGetInfowithsign(), sel.getSignInfo());
                }
                if(!sel.getSignInfo().equals(signedContent)) bol = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if(!bol){
                settle.setStatusSign(StatusSignEnum.NOSIGN.getValue());
                settle.setSignInfo("");
                settle.setPayInfowithsign("");
                settle.setGetInfowithsign("");
                settle.setPayOperator("");
                settle.setGetOperator("");
                return "签名原文与对方的内容不一致签名失败！";
            }else{
                settle.setStatusSign(StatusSignEnum.SIGN.getValue());
                if(PayGetTypeEnum.PAY.getValue().equals(Integer.valueOf(flag))){
                    settle.setPayInfowithsign(signature);
                    settle.setPayOperator(userId);
                }else{
                    settle.setGetInfowithsign(signature);
                    settle.setGetOperator(userId);
                }
            }
        }
        
        mtSettleMapper.updateByPrimaryKeySelective(settle);
        
        return "签章成功！";
    }
}
