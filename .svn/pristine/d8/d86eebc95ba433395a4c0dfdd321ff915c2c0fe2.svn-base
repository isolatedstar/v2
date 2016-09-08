package com.zllh.payment.server.queue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.model.MsgBean;
import com.zllh.utils.redis.base.BaseRedisDaoImpl;

@Service
public class AskBankQueue {
	private static final String QUEUE_NAME = "ASK_BANK";
	@Autowired
	private BaseRedisDaoImpl<String, MsgBean> baseRedisDaoImpl;

	// 添加到队列
	public void addTaskInQ(MsgBean task) {
		baseRedisDaoImpl.in(QUEUE_NAME, task);
	}

	// 获取下一个任务
	public MsgBean getNextTask() {
		return (MsgBean) baseRedisDaoImpl.index(QUEUE_NAME, 0);
	}

	public MsgBean getTaskByIndex(int taskId) {
		return (MsgBean) baseRedisDaoImpl.index(QUEUE_NAME, taskId);
	}

	// 移除指定的任务
	public void removeTask(MsgBean task) {
		baseRedisDaoImpl.remove(QUEUE_NAME, 1, task);
	}

	// 清空任务队列
	public void clearTaskQueue() {
		baseRedisDaoImpl.removeAll(QUEUE_NAME);
	}

	public static void resetStartTime(MsgBean task) {
	}

	// 队列是否为空
	public boolean taskQisEmpty(MsgBean task) {
		return baseRedisDaoImpl.length(QUEUE_NAME) == 0;
	}

	// 获取队列的长度
	public Long getQueueLength() {
		return baseRedisDaoImpl.length(QUEUE_NAME);
	}

	public List<MsgBean> getQueueList(){
		return baseRedisDaoImpl.getListByName(QUEUE_NAME);
	}
}
