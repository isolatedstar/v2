package com.zllh.factoring.messagecheck.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.base.mybatis.Page;
import com.zllh.factoring.common.dao.FacMessageMapper;
import com.zllh.factoring.common.fac_enum.MessageStatus;
import com.zllh.factoring.common.model.FacMessage;
import com.zllh.factoring.common.model.message.common.PayMentCallBack;
import com.zllh.factoring.messagecheck.service.MessageCheckService;
import com.zllh.factoring.repayment.service.RefundMgService;

@Service
public class MessageCheckServiceImpl implements MessageCheckService{

    @Autowired
    private FacMessageMapper messageMapper;
    @Autowired
    private RefundMgService refundMgService;
    
    @Override
    public Page<FacMessage> findMessageCheckList(String createDate, Integer messageSource, Integer nowPage) {
        
        Page<FacMessage> page = new Page<FacMessage>();
        page.setNowPage(nowPage);
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startcreateDate", !"".equals(createDate)?createDate.substring(0, 10):"");
        paramMap.put("endcreateDate", !"".equals(createDate)?createDate.substring(13, 23):"");
        paramMap.put("messageSource", messageSource);
        //查询总条数用于计算页数
        int totalCount = messageMapper.findMessageCheckListCount(paramMap);
        page.setTotalCount(totalCount);
        paramMap.put("beginIndex", page.getBeginIndex());
        paramMap.put("pageSize", page.getPageSize());
        //查询列表信息
        List<FacMessage> messList = messageMapper.findMessageCheckList(paramMap);
        page.setTotalCount(totalCount);
        page.setResult(messList);
        return page;
    }

    @Override
    public FacMessage findDetailMessage(String messId) {
        return messageMapper.selectByPrimaryKey(messId);
    }

    @Override
    public String executeMessage(String messIdstr) {
       
        String[] messIds = messIdstr.trim().split(",");
        
        String reslse = "";
        String reslerr = "";
        String resl = "";
        
        //更新报文状态
        int index = 1;
        int code = 1;
        for(String messId : messIds){
            
            FacMessage message = messageMapper.selectByPrimaryKey(messId);
            if(MessageStatus.YES.getValue().equals(message.getStatus())){
                if(index == 1) reslerr += "流水号:";
                if(index > 1) reslerr += "|";
                reslerr += message.getSerialNo();
                index ++;
                continue;
            }
            
            PayMentCallBack call = new PayMentCallBack();
            call.setSerialID(message.getSerialNo());
            call.setStatus("200");
            call.setFactoringUrl(message.getSource().toString());
            
            //回调处理
            String res = refundMgService.disposeFacRefundCallback(JSONObject.fromObject(call));
            if(!"200".equals(res)) return "操作失败，请联系客服！";
            FacMessage mess = new FacMessage();
            mess.setId(messId);
            mess.setStatus(MessageStatus.YES.getValue());
            messageMapper.updateByPrimaryKeySelective(mess);
            
            if(code == 1) reslse += "流水号:";
            if(code > 1) reslse += "|";
            reslse += message.getSerialNo();
            code ++;
        }
        
        String err = "操作失败！";
        String secc = "操作成功！";
        
        reslerr += err;
        reslse += secc;
        
        if(!reslerr.equals(err)&&!reslse.equals(secc)){
            resl = reslse + "&" + reslerr;
        }else if(reslerr.equals(err)&&!reslse.equals(secc)){
            resl = reslse;
        }else if(!reslerr.equals(err)&&reslse.equals(secc)){
            resl = reslerr;
        }
        
        return resl;
    }

}
