package com.zllh.factoring.messagecheck.service;

import com.zllh.base.mybatis.Page;
import com.zllh.factoring.common.model.FacMessage;

/**
 * @ClassName MessageCheckService
 * @Description TODO
 * @author Administrator
 * @Date 2016年7月5日 上午11:34:43
 * @version 1.0.0
 */
public interface MessageCheckService {

    /**
     * @param nowPage 
     * @Title: findMessageCheckList 
     * @Description: 查询报文列表
     * @param @param createDate
     * @param @param refundDate
     * @param @return
     * @return Page<FacMessage>
     * @throws
     */
    Page<FacMessage> findMessageCheckList(String createDate, Integer messageSource, Integer nowPage);

    /**
     * @Title: findDetailMessage 
     * @Description: 查询报文描述明细
     * @param @param messId
     * @param @return
     * @return FacMessage
     * @throws
     */
    FacMessage findDetailMessage(String messId);

    /**
     * @Title: executeMessage 
     * @Description: 报文审核调用支付
     * @param @param messIds
     * @param @return
     * @return String
     * @throws
     */
    String executeMessage(String messIds);

}
