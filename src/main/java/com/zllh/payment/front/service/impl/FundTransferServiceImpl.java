package com.zllh.payment.front.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.FundTransferDetailMapper;
import com.zllh.payment.front.service.FundTransferService;
import com.zllh.payment.model.AccountTable;
import com.zllh.payment.model.FundTransferDetail;
import com.zllh.payment.utils.Util;

@Service
public class FundTransferServiceImpl implements FundTransferService {


	private static final String START_TIME = "startTime";

	private static final String START_TIME_FLG = "startTimeFlg";

	private static final String END_TIME = "endTime";
	
	private static final String END_TIME_FLG = "endTimeFlg";

	private static final String INTERFACE_TYPE = "interfaceType";

	private static final String OPERATOR = "operator";
	
	private static final String BUSINESS_TYPE = "businessType";

	private static final String BUSINESS_FROM = "businessFrom";

	private static final String TRANSFER_STATUS = "transferStatus";

	private static final String ACTION_TYPE = "actionType";

	private static final String PAGE = "page";

	private static final String ROWS = "rows";

	private static final String PAGE_INDEX = "pageIndex";

	private static final String PAGE_SIZE = "pageSize";

	private static final String ORDER = "order";

	private static final String SORT = "sort";

	String[] pageOrderColumn =new String[]{"bankFlowId","msgSource","tradeTimeStr","operator","outBankName","outMasterAcct","outAcct","inBankName",
			"inMasterAcct","inAcct","workTypeName","transferAmt","transferStatusName","transferContext","businessTypeName","interfaceStatusName"};

	String[] dbOrderColumn=new String[]{"BANK_FLOW_ID","MSG_SOURCE","TRADE_TIME","OPERATOR","OUT_BANK_ID","OUT_ACCT_TYPE","OUT_ACCT","IN_BANK_ID",
			"IN_MASTER_ACCT","IN_ACCT","WORK_TYPE","TRANSFER_AMT","TRANSFER_STATUS","TRANSFER_CONTEXT","BUSINESS_TYPE","INTERFACE_STATUS"};

	@Autowired
	private FundTransferDetailMapper transferMapper;

	@Autowired
	private FundTransferDetailMapper fundTransferDetailMapper;
	
	@Override
	public List<FundTransferDetail> queryAllTransferByParams(HttpServletRequest request) {

		List<FundTransferDetail> transferDetails = new ArrayList<FundTransferDetail>();
		//1.获取页面上的各个查询条件。

		//开始时间
		String startTimeStr = request.getParameter(START_TIME);
		//结束时间
		String endTimeStr = request.getParameter(END_TIME);
		//开始时间，结束时间不为空标志
		String endTimeFlg = "";
		String startTimeFlg = "";
		//将页面接收的开始结束时间转化为日期格式。
		List<Object> listTimeList = dateToStringList(startTimeStr, endTimeStr, startTimeFlg, endTimeFlg);  
		//接口类型
		String interfaceType = request.getParameter(INTERFACE_TYPE);
		//操作人
		String operator = request.getParameter(OPERATOR);
		//业务类型
		String businessType = request.getParameter(BUSINESS_TYPE);
		//业务来源
		String businessFrom = request.getParameter(BUSINESS_FROM);
		//资金划转状态
		String transferStatus = request.getParameter(TRANSFER_STATUS);
		//动作类型
		String actionType = request.getParameter(ACTION_TYPE);
		//第几页
		int page = Integer.parseInt(request.getParameter(PAGE));
		//每页显示几行
		int rows = Integer.parseInt(request.getParameter(ROWS));
		//排序
		String sort = request.getParameter(SORT);
		//升降
		String order = request.getParameter(ORDER);

		//2.将各个查询条件放入Map对象，作为查询参数。
		//查询资金划转明细的sql参数
		Map<String, Object> mapDetails = new HashMap<String, Object>();
		mapDetails.put(START_TIME, listTimeList.get(1));
		mapDetails.put(START_TIME_FLG, listTimeList.get(0));
		mapDetails.put(END_TIME, listTimeList.get(3));
		mapDetails.put(END_TIME_FLG, listTimeList.get(2));
		mapDetails.put(INTERFACE_TYPE, interfaceType);
		mapDetails.put(OPERATOR, operator);
		mapDetails.put(BUSINESS_TYPE, businessType);
		mapDetails.put(BUSINESS_FROM, businessFrom);
		mapDetails.put(TRANSFER_STATUS, transferStatus);
		mapDetails.put(ACTION_TYPE, actionType);
		//分页参数设定
		mapDetails.put(PAGE_INDEX, (page-1)*rows);
		mapDetails.put(PAGE_SIZE, rows);
		Map<String, Object> mapSortMap = Util.setMapColumn(pageOrderColumn, dbOrderColumn);
		if (sort != null && order != null) {
			//排序设定
		    mapDetails.put(SORT, mapSortMap.get(sort));
		    mapDetails.put(ORDER, order);
		} else {

			mapDetails.put(SORT, null);
			mapDetails.put(ORDER, null);
		}
		//3.查询
		String tableRowString = request.getParameter("tableRows").toString();
		List<AccountTable> listOut = new ArrayList<AccountTable>();
		List<AccountTable> listOutIn = new ArrayList<AccountTable>();
		int tableSize = Util.jsonToListMap(tableRowString).size();
		for (int i = 0; i < tableSize; i++ ) {
			Map<String, Object> m = Util.jsonToListMap(tableRowString).get(i);
			String relationBalance = m.get("masterRelation").toString();
			String outString = m.get("accountOut").toString();
			String inString = m.get("accountIn").toString();
			if("否".equals(relationBalance)){
				if (inString != null && (!"".equals(inString))) {
					//指定出金和入金
					AccountTable aTableOne = new AccountTable();
					aTableOne.setAccountIn(inString);
					aTableOne.setAccountOut(outString);
					listOut.add(aTableOne);
				} else {
					AccountTable aTableTwo = new AccountTable();
					//2.只指定出账户
					aTableTwo.setRelation("0");
					aTableTwo.setAccountOut(outString);
					listOutIn.add(aTableTwo);
				}
			} else {
				AccountTable aTableThree = new AccountTable();
				aTableThree.setAccountOut(outString);
				aTableThree.setRelation("1");
				listOutIn.add(aTableThree);
			}
		}

		mapDetails.put("listOut", listOut);
		mapDetails.put("listOutIn", listOutIn);
		//调用dao层查询银行转账明细。
		transferDetails = transferMapper.queryAllTransferByParams(mapDetails);

		//4.返回处理好的明细
		return collectTransferDetails(transferDetails);
	}

	@Override
	public void exportTransferInfo(List lists) {
		// TODO Auto-generated method stub

	}

	public static String DateToStr(Date date) {
		  
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String str = format.format(date);
		   return str;
		}

	@Override
	public int queryTransferCount(HttpServletRequest request) {

		int count = 0;
		//1.获取页面上的各个查询条件。
		//开始时间
		String startTimeStr = request.getParameter(START_TIME);
		//结束时间
		String endTimeStr = request.getParameter(END_TIME);
		//开始时间，结束时间不为空标志
		String endTimeFlg = "";
		String startTimeFlg = "";
		//将页面接收的开始结束时间转化为日期格式。
		List<Object> listTimeList = dateToStringList(startTimeStr, endTimeStr, startTimeFlg, endTimeFlg); 
		//接口类型
		String interfaceType = request.getParameter(INTERFACE_TYPE);
		//操作人
		String operator = request.getParameter(OPERATOR);
		//业务类型
		String businessType = request.getParameter(BUSINESS_TYPE);
		//业务来源
		String businessFrom = request.getParameter(BUSINESS_FROM);
		//资金划转状态
		String transferStatus = request.getParameter(TRANSFER_STATUS);
		//动作类型
		String actionType = request.getParameter(ACTION_TYPE);
		//第几页
		int page = Integer.parseInt(request.getParameter(PAGE));
		//每页显示几行
		int rows = Integer.parseInt(request.getParameter(ROWS));

		//2.将各个查询条件放入Map对象，作为查询参数。
		//查询资金划转明细的sql参数
		Map<String, Object> mapDetails = new HashMap<String, Object>();
		mapDetails.put(START_TIME, listTimeList.get(1));
		mapDetails.put(START_TIME_FLG, listTimeList.get(0));
		mapDetails.put(END_TIME, listTimeList.get(3));
		mapDetails.put(END_TIME_FLG, listTimeList.get(2));
		mapDetails.put(INTERFACE_TYPE, interfaceType);
		mapDetails.put(OPERATOR, operator);
		mapDetails.put(BUSINESS_TYPE, businessType);
		mapDetails.put(BUSINESS_FROM, businessFrom);
		mapDetails.put(TRANSFER_STATUS, transferStatus);
		mapDetails.put(ACTION_TYPE, actionType);
		//分页参数设定
		mapDetails.put(PAGE_INDEX, (page-1)*rows);
		mapDetails.put(PAGE_SIZE, rows);

		//3.查询
		String tableRowString = request.getParameter("tableRows").toString();
		List<AccountTable> listOut = new ArrayList<AccountTable>();
		List<AccountTable> listOutIn = new ArrayList<AccountTable>();
		int tableSize = Util.jsonToListMap(tableRowString).size();
		for (int i = 0; i < tableSize; i++ ) {
			Map<String, Object> m = Util.jsonToListMap(tableRowString).get(i);
			String relationBalance = m.get("masterRelation").toString();
			String outString = m.get("accountOut").toString();
			String inString = m.get("accountIn").toString();
			if("否".equals(relationBalance)){
				if (inString != null && (!"".equals(inString))) {
					//指定出金和入金
					AccountTable aTableOne = new AccountTable();
					aTableOne.setAccountIn(inString);
					aTableOne.setAccountOut(outString);
					listOut.add(aTableOne);
				} else {
					AccountTable aTableTwo = new AccountTable();
					//2.只指定出账户
					aTableTwo.setRelation("0");
					aTableTwo.setAccountOut(outString);
					listOutIn.add(aTableTwo);
				}
			} else {
				AccountTable aTableThree = new AccountTable();
				aTableThree.setAccountOut(outString);
				aTableThree.setRelation("1");
				listOutIn.add(aTableThree);
			}
		}

		mapDetails.put("listOut", listOut);
		mapDetails.put("listOutIn", listOutIn);

		//查询银行转账明细条数。
		count = transferMapper.queryTransferCount(mapDetails);

		return count;
	}

	//将返回数据整理
	public List<FundTransferDetail> collectTransferDetails(List<FundTransferDetail> transferDetails){
		if (transferDetails != null && transferDetails.size() > 0) {
			for (int i = 0; i < transferDetails.size(); i++) {
				//报文来源
				String msg = transferDetails.get(i).getMsgSource();
				//划转状态
				int transferString = transferDetails.get(i).getTransferStatus();
				//动作类型
				int action = transferDetails.get(i).getWorkType();
				//业务类型
				int business = transferDetails.get(i).getBusinessType();
				//接口类型
				int interfaceStatus = transferDetails.get(i).getInterfaceStatus();
				if (msg.equals("2")) {
					transferDetails.get(i).setMsgSource("商城");
				} else {
					transferDetails.get(i).setMsgSource("保理");					
				}
				if (transferString == 1) {
					transferDetails.get(i).setTransferStatusName("成功");
				} else if (transferString == 2) {
					transferDetails.get(i).setTransferStatusName("失败");
				}
				if (action == 2) {
					transferDetails.get(i).setWorkTypeName("转账");
				} else if (action == 1) {
					transferDetails.get(i).setWorkTypeName("出金");
				}
				if (business == 2) {
					transferDetails.get(i).setBusinessTypeName("融资");
				} else if (business == 1) {
					transferDetails.get(i).setBusinessTypeName("还款");
				} 
				if (interfaceStatus == 1) {
					transferDetails.get(i).setInterfaceStatusName("银企直联");
				} else if (interfaceStatus == 2) {
					transferDetails.get(i).setInterfaceStatusName("网银");
				}
				transferDetails.get(i).setTradeTimeStr(DateToStr(transferDetails.get(i).getTradeTime()));
			}
		}
			return transferDetails;
	}
	public static List<Object> dateToStringList(String startTimeStr, String endTimeStr, String startTimeFlg, String endTimeFlg){
		List<Object> listObject = new ArrayList<Object>();
		Date startTime = new Date();
		Date endTime = new Date();		         
		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");  
		try {
			if (startTimeStr != null && (!"".equals(startTimeStr))) {
				startTimeFlg = "1";
				startTime = (Date) df.parse(startTimeStr);
			}
			if (endTimeStr != null && (!"".equals(endTimeStr))) {
				endTimeFlg = "1";
				endTime = (Date) df.parse(endTimeStr);
				Calendar calendar = new GregorianCalendar(); 
			    calendar.setTime(endTime); 
			    calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
			    endTime=calendar.getTime();  
			}
			listObject.add(startTimeFlg);
			listObject.add(startTime);
			listObject.add(endTimeFlg);
			listObject.add(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return listObject;
		
	}
}