
package com.zllh.common.job.factoring;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.base.controller.BaseController;
import com.zllh.factoring.common.dao.FacMessageStatusMapper;
import com.zllh.factoring.common.fac_enum.MessageStatus;
import com.zllh.factoring.common.model.FacMessageStatus;
import com.zllh.factoring.repayment.service.RefundMgService;

/**
 * @ClassName: RefundJob
 * @Description: 还款定时任务
 * @author JW
 * @date 2016年4月20日 下午5:02:57
 */
@Service
public class RefundJob {
	
	@Autowired
	private FacMessageStatusMapper facMessageStatusMapper;
	
	@Autowired
	private RefundMgService refundMgService;
	
	private static final HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	public final Logger logger = LoggerFactory.getLogger(BaseController.class); 
	
	public void guaranteeRefund(){
		
		List<FacMessageStatus> facMessageStatus = facMessageStatusMapper.findFacMessageStatusAll();
		for(FacMessageStatus mess : facMessageStatus){
			if(mess.getStatus()==MessageStatus.ERR.getValue()){
				if(map.get(mess.getId())==null) map.put(mess.getId(), 0);
				if(map.get(mess.getId())<=2){
					map.put(mess.getId(), map.get(mess.getId())+1);
					logger.info("定时任务处理担保还款【"+mess.getId()+"】开始...");
					refundMgService.executeGuaranteeRefund(mess);
				}
			}
		}
	}
}
