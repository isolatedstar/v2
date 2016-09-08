package com.zllh.payment.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.FundTransferDetailMapper;
import com.zllh.payment.model.FundTransferDetail;
import com.zllh.payment.server.service.FundTransferDetailService;

@Service
public class FundTransferDetailServiceImpl implements FundTransferDetailService {

	@Autowired
	private FundTransferDetailMapper fundTransferDetailMapper;
	
	public void updateTransferStatusByPrimaryKey(String bankFlowId, int transferStatus){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bankFlowId", bankFlowId);
		map.put("transferStatus", transferStatus);
		fundTransferDetailMapper.updateTransferStatusByPrimaryKey( map );
	}
	
	public FundTransferDetail findByPrimaryKey(String bankFlowId){
		return fundTransferDetailMapper.selectByPrimaryKey(bankFlowId);
	}
	
	public void save(Map<String, Object> map){
		fundTransferDetailMapper.insert(map);
	}
	
	public void saveBatch(Map<String, Object> map){
		fundTransferDetailMapper.insertBatch(map);
	}
	
}
