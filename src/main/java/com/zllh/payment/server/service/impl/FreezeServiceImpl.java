package com.zllh.payment.server.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.FreezeMapper;
import com.zllh.payment.model.Freeze;
import com.zllh.payment.server.service.FreezeService;
import com.zllh.payment.utils.SpringBeanFactoryUtils;

@Service
public class FreezeServiceImpl implements FreezeService {

    @Autowired
	public SpringBeanFactoryUtils sBeanFactoryUtils;

	private FreezeMapper freezeMapper = (FreezeMapper) sBeanFactoryUtils.getBean("freezeMapper");
	
	public Freeze findByBankAcct(String bankAcct){
		return freezeMapper.selectByPrimaryKey(bankAcct);
	}

	public void updateFreeze(Freeze freeze){
		freezeMapper.updateByPrimaryKey(freeze);
	}
	
	
}
