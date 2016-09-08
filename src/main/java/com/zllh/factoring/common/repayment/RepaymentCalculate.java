
package com.zllh.factoring.common.repayment;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.zllh.factoring.common.interest.InterestConfig;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.DateUtil;

/**
 * @ClassName: interestCalculate
 * @Description: 还款相关计算
 * @author JW
 * @date 2016年2月17日 下午2:29:27
 */
@Service
public class RepaymentCalculate {
	
	/**
	 * @Title: interestFreezeAmount 
	 * @author JW
	 * @Description: 根据给付金额计算冻结总额
	 * @param decimal
	 * @return BigDecimal
	 * @throws
	 */
	public BigDecimal calculateFreezeAmount(BigDecimal decimal){
		
		//算出给付金额的比例
		BigDecimal payRatio = new BigDecimal(String.valueOf(1-InterestConfig.FREEZE));
		return decimal.divide(payRatio);
	}
	
	public static void main(String[] args) {
		System.err.println(DateUtil.getDaySub("2016-02-26", "2015-02-26"));
	}
	
	/**
	 * @Title: repaymentInterestCalculate 
	 * @author JW
	 * @Description: 根据还款金额计算还本金额(即还款金额按比例计算出拿多少还本金额)
	 * @param decimal
	 * @return BigDecimal  还本金额
	 * @throws
	 */
	public BigDecimal repaymentCalculate(BigDecimal decimal, Date financingDate, Double rate){   
		
		//获取当前时间
		String nowDate = DateUtil.getNow();
		//计算融资到现在一共的天数
		int day = DateUtil.getDaySub(DateUtil.formatTime(financingDate), nowDate);
		//计算出当前利息率
		double interest = Utils.bigDecimalInterest.mul(rate, day);
		
		// X*利率 + X = 还款金额      X:还本金额     X = 还款金额/(1+利率)
		double rede = Utils.bigDecimalInterest.div(decimal.doubleValue(), 1+interest);

		return new BigDecimal(rede);
	}
	
	/**
	 * @Title: repaymentInterest 
	 * @author JW
	 * @Description: 根据还款金额计算还息金额(即还款金额按比例计算出拿多少还息金额)
	 * @param decimal
	 * @return BigDecimal
	 * @throws
	 */
	public BigDecimal repaymentInterest(BigDecimal decimal, Date financingDate, Double rate){
		
		//获取当前时间
		String nowDate = DateUtil.getNow();
		//计算融资到现在一共的天数
		int day = DateUtil.getDaySub(DateUtil.formatTime(financingDate), nowDate);
		//计算出当前利息率
		double interest = Utils.bigDecimalInterest.mul(rate, day);
		
		// X*利率 + X = 还款金额      X:还本金额     X = 还款金额/(1+利率)
		double rede = Utils.bigDecimalInterest.div(decimal.doubleValue(), 1+interest);
		
		return new BigDecimal(Utils.bigDecimalInterest.mul(rede, interest));
	}
	
	/**
	 * @Title: guaranteeInterest 
	 * @author JW
	 * @Description: 根据给付金额计算出还款利息(根据金额计算出利息)
	 * @param decimal            给付金额
	 * @param financingDate      融资日期
	 * @param expireRate         到期利率
	 * @param extendRate         超期利率
	 * @return BigDecimal
	 * @throws
	 */
	public BigDecimal guaranteeInterest(BigDecimal decimal, Date financingDate, Double expireRate, Double extendRate){
		
		//当前没有超期利率的计算逻辑 后面定了可以加上
		
		//获取当前时间
		String nowDate = DateUtil.getNow();
		//计算融资到现在一共的天数
		int day = DateUtil.getDaySub(DateUtil.formatTime(financingDate), nowDate);
		//计算出当前利息率
		double interest = Utils.bigDecimalInterest.mul(expireRate, day);
		
		return new BigDecimal(Utils.bigDecimalInterest.mul(decimal.doubleValue(), interest));
	}
	
	/**
	 * @Title: calculatorOverdueFine 
	 * @author JW
	 * @Description: 计算滞纳金
	 * @param rate
	 * @param predictRefundDate
	 * @return BigDecimal
	 * @throws
	 */
	public BigDecimal calculatorOverdueFine(Double rate, Date predictRefundDate){
		
		//获取当前时间
		String nowDate = DateUtil.getNow();
		//计算预计还款日期到现在一共的天数
		int day = DateUtil.getDaySub(DateUtil.formatTime(predictRefundDate), nowDate);
		if(day>0){
			return new BigDecimal(Utils.bigDecimalInterest.mul(day, rate));
		}
		
		return BigDecimal.ZERO;
	}
}
