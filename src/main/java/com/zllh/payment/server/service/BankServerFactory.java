package com.zllh.payment.server.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.BankMapper;
import com.zllh.payment.front.dao.BankServerMapper;
import com.zllh.payment.front.dao.InterfaceMgtMapper;
import com.zllh.payment.model.Bank;
import com.zllh.payment.model.BankBean;
import com.zllh.payment.model.BankServer;
import com.zllh.payment.model.InterfaceMgt;
import com.zllh.payment.model.MsgBean;
import com.zllh.payment.model.RegularView;
import com.zllh.payment.model.ResultBackBean;
import com.zllh.payment.model.SendBankMsg;
import com.zllh.payment.model.TaskBean;
import com.zllh.payment.server.dao.RegularViewMapper;
import com.zllh.payment.server.queue.ResultBackQueue;
import com.zllh.payment.server.queue.TaskQueue;
import com.zllh.payment.server.thread.ResultBackThread;
import com.zllh.payment.utils.SpringBeanFactoryUtils;

@Service
public class BankServerFactory {

	@Autowired
	private RegularViewMapper regularViewMapper;
	@Autowired
	private BankMsgBuilder bankMsgBuilder;
	@Autowired
	private TaskQueue taskQueue;
	@Autowired
	private InterfaceMgtMapper interfaceMgtMapper;
	@Autowired
	private BankServerMapper bankServerMapper;
	@Autowired
	private FactoringMsgService factoringMsgService;
	@Autowired
	ResultBackQueue resultBackQueue;
    @Autowired
	public SpringBeanFactoryUtils sBeanFactoryUtils;
    @Autowired
    public FundTransferDetailService fundTransferDetailService;
    @Autowired
    public SendBankMsgService sendBankMsgService;
    @Autowired
    public BankMapper bankMapper;

	public TaskBean findBankInfoByAcct(TaskBean taskBeanPara){
		//从任务对象中提取到第一条转账报文里的出账户
		//1.查询该转账账户的银行，接口类和服务器信息
		String accountNoString = taskBeanPara.getParsedMsgs().get(0).get("payAccNo");
		BankBean bkB = findBankByAcctRule(accountNoString);
		String bankId;
		if (bkB.getBankZhiId() != null) {
			bankId = bkB.getBankZhiId();
		} else if (bkB.getBankFenId() != null) {
			bankId = bkB.getBankFenId();
		} else if (bkB.getBankZongId() != null) {
			bankId = bkB.getBankZongId();
		} else {
			//银行不存在，修改报文状态。(1.刚接收（保存时默认值）。2.接口类找不到。3.服务器找不到。4.报文生成错误。5.银行找不到。6.已生成报文。)
			factoringMsgService.updateMsgStatus(taskBeanPara.getSerialID(),  new Byte((byte) 5));
			//回调保理
			ResultBackQueue resultBackQueue = (ResultBackQueue) sBeanFactoryUtils.getBean("resultBackQueue");
			resultBackQueue.addResultBackInQ(setResultBackQueue(taskBeanPara,"440","没有找到账户对应银行"));
			ResultBackThread resultBackThread = new ResultBackThread();
			Thread thread=new Thread(resultBackThread); 
			thread.start();
			return null;
		}
		String interfaceClass = findInterfaceByBankId(bankId).get(0).getInterfaceClass();
		if (interfaceClass == null) {
			//接口类不存在，修改报文状态。
			factoringMsgService.updateMsgStatus(taskBeanPara.getSerialID(),  new Byte((byte) 2));
			//回调保理
			ResultBackQueue resultBackQueue = (ResultBackQueue) sBeanFactoryUtils.getBean("resultBackQueue");
			resultBackQueue.addResultBackInQ(setResultBackQueue(taskBeanPara,"441","没有找到接口类"));
			ResultBackThread resultBackThread = new ResultBackThread();
			Thread thread=new Thread(resultBackThread); 
			thread.start();
			return null;
		}
		String serverIp = "";
		List<BankServer> bkServers = findServerInfoByBankId(bkB.getBankZongId(),bkB.getBankFenId(),bkB.getBankZhiId());
		//如果服务器存在
		if(bkServers.size() > 0){
			serverIp = bkServers.get(0).getServerAddress();
		} else {
			//服务器不存在，修改报文状态。
			factoringMsgService.updateMsgStatus(taskBeanPara.getSerialID(),  new Byte((byte) 3));
			//回调保理
			ResultBackQueue resultBackQueue = (ResultBackQueue) sBeanFactoryUtils.getBean("resultBackQueue");
			resultBackQueue.addResultBackInQ(setResultBackQueue(taskBeanPara,"442","服务器不存在"));
			ResultBackThread resultBackThread = new ResultBackThread();
			Thread thread=new Thread(resultBackThread); 
			thread.start();
			return null;
		}
		taskBeanPara.setServerUrl(serverIp);
		//2.调用生成报文接口，在相应接口类里生成报文
		List<MsgBean> queryList = null;
		List<MsgBean> transferList = null;
		try {
			queryList = bankMsgBuilder.callInterfaceClassQuery(taskBeanPara,interfaceClass);
			transferList = bankMsgBuilder.callInterfaceClassTransfer(taskBeanPara, interfaceClass);
			if (queryList == null || transferList == null) {
				//全部报文生成有错误，修改报文状态。
				factoringMsgService.updateMsgStatus(taskBeanPara.getSerialID(), new Byte((byte) 4));
				ResultBackQueue resultBackQueue = (ResultBackQueue) sBeanFactoryUtils.getBean("resultBackQueue");
				resultBackQueue.addResultBackInQ(setResultBackQueue(taskBeanPara,"441","接口类生成的报文错误，或无该接口类"));
				ResultBackThread resultBackThread = new ResultBackThread();
				Thread thread=new Thread(resultBackThread); 
				thread.start();
				return null;
			}
		} catch (Exception e) {
			//全部报文生成有错误，修改报文状态。
			factoringMsgService.updateMsgStatus(taskBeanPara.getSerialID(), new Byte((byte) 4));
			//报文生成有误，加入到返回队列
			ResultBackQueue resultBackQueue = (ResultBackQueue) sBeanFactoryUtils.getBean("resultBackQueue");
			resultBackQueue.addResultBackInQ(setResultBackQueue(taskBeanPara,"441","接口类生成的报文错误，或无该接口类"));
			ResultBackThread resultBackThread = new ResultBackThread();
			Thread thread=new Thread(resultBackThread); 
			thread.start();
			return null;
		}
		taskBeanPara.setQueryMsgLst(queryList);
		taskBeanPara.setActMsgLst(transferList);
		//查询报文接收方的银行名称
		Bank bank = bankMapper.selectByPrimaryKey(bankId);
		//记录信息到银行报文发送列表
		List<SendBankMsg> sendBankList = createSendBackLists(taskBeanPara, bank.getText());
		Map<String, Object> map = new HashMap<String, Object>();  
	    map.put("sendBankList", sendBankList);
		sendBankMsgService.saveBatch(map);
		//修改原始报文状态6：已生成报文。
		factoringMsgService.updateMsgStatus(taskBeanPara.getSerialID(), new Byte((byte) 6));
		return taskBeanPara;
	}

	//根据转账报文，生成向银行发送报文明细表的内容
	public List<SendBankMsg> createSendBackLists(TaskBean taskBean, String bankName){
		List<SendBankMsg> sendLists = new ArrayList<SendBankMsg>();
		List<MsgBean> msgLists = taskBean.getActMsgLst();
		for (int i = 0; i < msgLists.size(); i++) {

			SendBankMsg sBankMsg = new SendBankMsg();
			sBankMsg.setBankAcct(msgLists.get(i).getAccountOut());
			sBankMsg.setBankFlowId(msgLists.get(i).getMsgId());
			//总serialNo
			sBankMsg.setSerialNo(taskBean.getSerialID());
			sBankMsg.setBankFlowId(msgLists.get(i).getMsgId());
			sBankMsg.setLockAmt(new BigDecimal(msgLists.get(i).getAmt()));
			//未锁定
			sBankMsg.setLockFlag("0");
			sBankMsg.setMsgContext(msgLists.get(i).getMsgContent());
			sBankMsg.setMsgFlowId(msgLists.get(i).getFlowId());
			sBankMsg.setMsgRecevicer(msgLists.get(i).getAccountIn());
			sBankMsg.setMsgSender("保理");
			//报文类型
			sBankMsg.setMsgType("2");
			sBankMsg.setMsgTypeName("转账");
			sBankMsg.setSendMsgTime(new Date());
			//状态报文做成
			sBankMsg.setStatus("1");
			sBankMsg.setStatusName("已做成");
			sBankMsg.setSerialNo(taskBean.getSerialID());
			sendLists.add(sBankMsg);
		}
		return sendLists;
	}


	//根据出账户和账号规则查找银行
	public  BankBean findBankByAcctRule(String  accountNo){
		//银行账号作为参数查询账号规则视图
		List<RegularView> bBeanList = new ArrayList<RegularView>();
		BankBean bBean = new BankBean();
		if (regularViewMapper.findBankByAcctRule(accountNo).size() != 0){
			RegularView	regularView = regularViewMapper.findBankByAcctRule(accountNo).get(0);
			bBean.setBankZongId(regularView.getZongId());
			bBean.setBankFenId(regularView.getFenId());
			bBean.setBankZhiId(regularView.getZhiId());
		}
		return bBean;
	}

	//查找接口类
	public List<InterfaceMgt> findInterfaceByBankId(String zongId){
		//根据总行id查出接口类
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bankId", zongId);
		return interfaceMgtMapper.selectByBankid(map);
	}

	//查找服务器信息
	public List<BankServer> findServerInfoByBankId(String zongId, String fenId, String zhiId){
		//根据银行id查找服务器信息

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zong_Id", zongId);
		map.put("fen_Id", fenId);
		map.put("zhi_Id", zhiId);
		return bankServerMapper.selectByBankids(map);
	}
	
	//根据taskBean对象生成回调对象resultBackBean
	public ResultBackBean getResultBean(TaskBean taskBean){
		ResultBackBean resultBackBean = new ResultBackBean();
		resultBackBean.setFactoringUrl("");
		resultBackBean.setFlowId(taskBean.getFlowId());
		resultBackBean.setIntervalTime(new Date().getTime());
		resultBackBean.setMallUrl("");
		resultBackBean.setResultContent("");
		//暂时未设定
		resultBackBean.setStartTime(new Date().getTime());
		return resultBackBean;
		
	}
	//设置回调Bean的内容
	public ResultBackBean setResultBackQueue(TaskBean taskBean, String status,String resultContent){
		ResultBackBean reBackBean = new ResultBackBean();
		reBackBean.setSerialID(taskBean.getSerialID());
		reBackBean.setFactoringUrl(taskBean.getFactoringUrl());
		reBackBean.setFlowId(taskBean.getFlowId());
		reBackBean.setIntervalTime(taskBean.getIntervalTime());
		reBackBean.setMallUrl(taskBean.getMallUrl());
		reBackBean.setResultContent(resultContent);
		reBackBean.setStatus(status);
		reBackBean.setStartTime(taskBean.getStartTime());
		return reBackBean;
	}
}
