package com.zllh.payment.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

public class CalculateLimitAmount {
	
	public static Logger logger = Logger.getLogger(CalculateLimitAmount.class);

	public static void calcLimitAmt(String intoBankId, String outBankId, Map<String, Float> totalMoney,Map<String, Float> balanceMap, Float money) {
      
		Float intoBankMoney = totalMoney.get(intoBankId);
		Float outBankMoney = totalMoney.get(totalMoney);
		intoBankMoney = (null == intoBankMoney ? 0 : intoBankMoney) - money;
		outBankMoney = (null == outBankMoney ? 0 : outBankMoney) + money;
		totalMoney.put(intoBankId, intoBankMoney);
		totalMoney.put(outBankId, outBankMoney);
		Float intoBankBalanceMoney = balanceMap.get(intoBankId);
		intoBankBalanceMoney = (null == intoBankBalanceMoney ? 0 : intoBankBalanceMoney);
		balanceMap.put(intoBankId, (intoBankBalanceMoney < intoBankMoney ? intoBankMoney : intoBankBalanceMoney).floatValue());

		totalMoney.put(outBankId, totalMoney.get(outBankId) + money);
		totalMoney.put(intoBankId, totalMoney.get(intoBankId) - money);
		if ((totalMoney.get(intoBankId) - money) < 0 || totalMoney.get(intoBankId) + money > (0 - balanceMap.get(intoBankId))) {
			balanceMap.put(intoBankId, totalMoney.get(intoBankId) - money);
		}
	}
	
	/**
	 * @author dongll
	 * 
	 * @param totaLNos
	 * @param transfers
	 * @return
	 */
	public static List<Map<String, String>> calculatelLimitAmt(List<String> totaLNos, List<Map<String, String>> transfers){
		Assert.notNull(totaLNos);
		Assert.notNull(transfers);
		List<Map<String, String>> accountLimitAmtMaps = new ArrayList<Map<String, String>>();
		for (String account : totaLNos) {
			Map<String, String> accountLimitAmtMap = new HashMap<String, String>();
			BigDecimal limitAmt = BigDecimal.ZERO;
			BigDecimal transInAmt = BigDecimal.ZERO;
			for (Map<String, String> map : transfers) {
				String accountNo = map.get("payAccNo").toString();
				String recvAccNo = map.get("recvAccNo").toString();
				BigDecimal transferAmt = new BigDecimal( map.get("tranAmt").toString());
				if (account.equals(accountNo)){ 
					if (transInAmt.compareTo(BigDecimal.ZERO) == 0){
						limitAmt = limitAmt.add(transferAmt.subtract(transInAmt));
					}else{
						if (transferAmt.compareTo(transInAmt) >= 0){
							limitAmt = limitAmt.add(transferAmt.subtract(transInAmt));
							transInAmt = transferAmt.subtract(transInAmt);
						}else{
							transInAmt = transInAmt.subtract(transferAmt);
						}
					}
				}else if (account.equals(recvAccNo)){
					transInAmt = transInAmt.add(transferAmt);
				}
			}
			if (BigDecimal.ZERO.compareTo(limitAmt) == 0){
				continue;
			}
			accountLimitAmtMap.put("account", account);
			accountLimitAmtMap.put("limitAmt", limitAmt.toString());
			accountLimitAmtMaps.add(accountLimitAmtMap);
		}	
		logger.error("#####################################################极值计算结果："+accountLimitAmtMaps.toString());
		return accountLimitAmtMaps;
	}
	

}
