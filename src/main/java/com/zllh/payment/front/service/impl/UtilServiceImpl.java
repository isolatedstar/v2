package com.zllh.payment.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.UtilMapper;
import com.zllh.payment.front.service.UtilService;
import com.zllh.payment.model.DataDict;

@Service
public class UtilServiceImpl implements UtilService {

	@Autowired
	private UtilMapper utilMapper;

	@Override
	public List<DataDict> getDataDictByCode(String code) {
		// TODO Auto-generated method stub
		return utilMapper.selectDataDictByCode(code);
	}
	
	

	
}
