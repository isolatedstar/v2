package com.zllh.payment.server.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.model.MsgBean;
import com.zllh.payment.model.TaskBean;
import com.zllh.payment.server.enumeration.CommonBehaviour;
import com.zllh.payment.server.service.BankMsgBuilder;
import com.zllh.payment.server.service.DirectBankEnterpriceCommon;
import com.zllh.payment.utils.ReflectUtils;
import com.zllh.payment.utils.SpringBeanFactoryUtils;
@Service
public class BankMsgBuilderImpl implements BankMsgBuilder {

	@Autowired
	SpringBeanFactoryUtils sBeanFactoryUtils;

	@Override
	public List<MsgBean> callInterfaceClassQuery(TaskBean taskBean, String interfaceClass) throws Exception{
		List<MsgBean> msgBn = null;
		try {
			String method = null;
			//动态指定到相应的银行处理类。
			DirectBankEnterpriceCommon bank = (DirectBankEnterpriceCommon) sBeanFactoryUtils.getBean(interfaceClass);
			//根据划转类型确定使用哪个方法（生成报文）
			String queryString = "CREATEQUERYXML";
			method = CommonBehaviour.valueOf(queryString).getYQZLMethodInfo();//相应业务处理方法名称
			//1.银企直联的情况
			//接收反射调用银行接口类生成的两种报文组
			List<Map<String, String>> queryMsgLstPara = taskBean.getCalculatedMsgs();
			//调用银行接口类生成报文方法
			msgBn = (List<MsgBean>) ReflectUtils.invokeMethod(bank, method,new Class<?>[]{List.class},new Object[]{ queryMsgLstPara}) ;		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return msgBn;
	}

	@Override
	public List<MsgBean> callInterfaceClassTransfer(TaskBean taskBean, String interfaceClass) throws Exception{
		List<MsgBean> msgBn = null;
		String method = null;
		try {
			//动态指定到相应的银行处理类。
			DirectBankEnterpriceCommon bank = (DirectBankEnterpriceCommon) SpringBeanFactoryUtils.getBean(interfaceClass);
			//根据划转类型确定使用哪个方法（生成报文）
			String queryString = "CREATETRANSFERXML";
			method = CommonBehaviour.valueOf(queryString).getYQZLMethodInfo();//相应业务处理方法名称
			//1.银企直联的情况
			List<Map<String, String>> transMsgLstPara = taskBean.getParsedMsgs();
			//调用银行接口类生成报文方法
			msgBn = ( List<MsgBean>) ReflectUtils.invokeMethod(bank, method,new Class<?>[]{List.class},new Object[]{ transMsgLstPara}) ;
			//将生成的往银行发送的报文放入任务Bean
		} catch (Exception e) {
			// TODO: handle exception
		}
		return msgBn;
	}

}
