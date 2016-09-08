package com.zllh.payment.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zllh.payment.model.MsgBean;
import com.zllh.payment.server.service.DirectBankEnterpriceCommon;
import com.zllh.payment.server.thread.MsgQConsumerThread;

@Service
public class ZhongXinDirectBankEnterpriceImpl implements DirectBankEnterpriceCommon {

	public Logger logger = Logger.getLogger(ZhongXinDirectBankEnterpriceImpl.class);
	@Override
	public List<MsgBean> createQueryXml(List<Map<String, String>> queryMsgLstPara) {

		//保存生成的查询报文和转账报文
		List<MsgBean> queryMsgLst = new ArrayList<MsgBean>();
		//查询报文和转账报文的拼接
		//1.循环生成查询报文
		for (int qInt = 0; qInt < queryMsgLstPara.size(); qInt++) {
			StringBuffer sbQuery = new StringBuffer();
			MsgBean msgBean = new MsgBean();
			//查询报文的内容
			sbQuery.append("<?xml version=\"1.0\" encoding=\"GBK\"?><stream><action>");
			//
			sbQuery.append("DLSBALQR");
			sbQuery.append("</action><userName>");
			//
			sbQuery.append(queryMsgLstPara.get(qInt).get("userName"));
			sbQuery.append("</userName><accountNo>");
			//
			sbQuery.append(queryMsgLstPara.get(qInt).get("accountNo"));
			sbQuery.append("</accountNo><subAccNo>");
			//
			sbQuery.append(queryMsgLstPara.get(qInt).get("payAccNo"));
			sbQuery.append("</subAccNo></stream>");
			//设定报文id，报文内容，待查询账户，金额，报文类型(查询报文)
//			msgBean.setMsgId(queryMsgLstPara.get(qInt).get("clientID"));
			msgBean.setMsgContent(sbQuery.toString());
//			msgBean.setAccount(queryMsgLstPara.get(qInt).get("accountNo"));
//			msgBean.setUserName(queryMsgLstPara.get(qInt).get("userName"));
//			msgBean.setAccountIn(queryMsgLstPara.get(qInt).get("recvAccNo"));
			msgBean.setAccountOut(queryMsgLstPara.get(qInt).get("payAccNo"));
			msgBean.setAmt(Double.valueOf(queryMsgLstPara.get(qInt).get("tranAmt")));
//			msgBean.setFlowId(queryMsgLstPara.get(qInt).get("flowId"));
			msgBean.setTransferFlg(false);
			//时间间隔设定
			msgBean.setIntervalTime((long)2000);
			queryMsgLst.add(msgBean);
		}
		return queryMsgLst;
	}

	@Override
	public List<MsgBean> createTransferXml(List<Map<String, String>> actMsgLstPara) {

		//保存生成的转账报文
		List<MsgBean> actMsgLst = new ArrayList<MsgBean>();
		//循环生成转账报文
		for (int cInt = 0; cInt < actMsgLstPara.size(); cInt++) {
			StringBuffer sbTransfer = new StringBuffer();
			MsgBean msgBeanTra = new MsgBean();
			//生成强制转账报文
			if ("ZLMDTFER".equals(actMsgLstPara.get(cInt).get("action"))) {
				sbTransfer.append("<?xml version=\"1.0\" encoding=\"GBK\"?><stream>	");
				sbTransfer.append("<action>");
				//报文类型（强制转账）
				sbTransfer.append("DLMDETRN");
				sbTransfer.append("</action><userName>");
				//
				sbTransfer.append(actMsgLstPara.get(cInt).get("userName"));
		  		sbTransfer.append("</userName><clientID>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("clientID"));
		  		sbTransfer.append("</clientID><accountNo>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("accountNo"));
		  		sbTransfer.append("</accountNo><payAccNo>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("payAccNo"));
		  		sbTransfer.append("</payAccNo><tranType>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("tranType"));
		  		sbTransfer.append("</tranType><recvAccNo>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("recvAccNo"));
		  		sbTransfer.append("</recvAccNo><recvAccNm>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("recvAccNm"));
		  		sbTransfer.append("</recvAccNm><tranAmt>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("tranAmt"));
		  		sbTransfer.append("</tranAmt><freezeNo>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("freezeNo"));
		  		sbTransfer.append("</freezeNo><memo>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("memo"));
		  		sbTransfer.append("</memo><tranFlag>");	  		
		  		sbTransfer.append("0");
		  		sbTransfer.append("</tranFlag></stream>");
			} else if ("ZLFNDOUT".equals(actMsgLstPara.get(cInt).get("action"))) {
				sbTransfer.append("<?xml version=\"1.0\" encoding=\"GBK\"?><stream>	");
				sbTransfer.append("<action>");
				//报文类型（强制出金）
				sbTransfer.append("DLFCSOUT");
				sbTransfer.append("</action><userName>");
				//
				sbTransfer.append(actMsgLstPara.get(cInt).get("userName"));
		  		sbTransfer.append("</userName><clientID>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("clientID"));
		  		sbTransfer.append("</clientID><accountNo>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("payAccNo"));
		  		sbTransfer.append("</accountNo><recvAccNo>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("recvAccNo"));
		  		sbTransfer.append("</recvAccNo><recvAccNm>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("recvAccNm"));
		  		sbTransfer.append("</recvAccNm><tranAmt>");
		  		//
		  		sbTransfer.append(actMsgLstPara.get(cInt).get("tranAmt"));
		  		sbTransfer.append("</tranAmt><sameBank>");
		  		sbTransfer.append("0");
		  		sbTransfer.append("</sameBank><recvTgfi>");
		  		sbTransfer.append("</recvTgfi><recvBankNm>");
		  		sbTransfer.append("</recvBankNm><memo>");
		  		sbTransfer.append("</memo><preFlg>");
		  		sbTransfer.append("0");
		  		sbTransfer.append("</preFlg><preDate></preDate><preTime></preTime></stream>");
			}
			logger.debug("############生成报文类型" + actMsgLstPara.get(cInt).get("action") +
					"############生成报文内容" + sbTransfer.toString());
			//设定报文id，报文内容，待查询账户，金额，报文类型(转账报文)
			msgBeanTra.setMsgId(actMsgLstPara.get(cInt).get("clientID"));
			msgBeanTra.setMsgContent(sbTransfer.toString());
			msgBeanTra.setAccount(actMsgLstPara.get(cInt).get("accountNo"));
			msgBeanTra.setAccountIn(actMsgLstPara.get(cInt).get("recvAccNo"));
			msgBeanTra.setAccountOut(actMsgLstPara.get(cInt).get("payAccNo"));
			msgBeanTra.setUserName(actMsgLstPara.get(cInt).get("userName"));
			//时间间隔设定
			msgBeanTra.setIntervalTime((long)2000);
			msgBeanTra.setTransferFlg(true);
			msgBeanTra.setAmt(Double.valueOf(actMsgLstPara.get(cInt).get("tranAmt")));
			msgBeanTra.setFlowId(actMsgLstPara.get(cInt).get("flowId"));
			actMsgLst.add(msgBeanTra);
		}
		return actMsgLst;
	}
	
}
