package com.zllh.payment.front.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zllh.payment.model.FundTransferDetail;
public interface FundTransferService {

	//流水明细查询
	public List<FundTransferDetail> queryAllTransferByParams (HttpServletRequest request);
	//导出全部数据
	public void exportTransferInfo(List lists);
	//查询符合查询条件的总件数
	public int queryTransferCount(HttpServletRequest request);
	
}
