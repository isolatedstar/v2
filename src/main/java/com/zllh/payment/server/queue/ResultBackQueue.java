package com.zllh.payment.server.queue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.model.ResultBackBean;
import com.zllh.utils.redis.base.BaseRedisDaoImpl;

@Service
public class ResultBackQueue {

	private static final String QUEUE_NAME = "RESULT_BACK";
	@Autowired
	private BaseRedisDaoImpl<String, ResultBackBean> baseRedisDaoImpl;

	// 添加到队列
	public void addResultBackInQ(ResultBackBean resultBack) {
		baseRedisDaoImpl.in(QUEUE_NAME, resultBack);
	}

	// 获取下一个任务
	public ResultBackBean getNextResultBack() {
		return (ResultBackBean) baseRedisDaoImpl.index(QUEUE_NAME, 0);
	}
	
	//根据索引查询返回结果对象
	public ResultBackBean getNextResultBackByIndex(int index){
		return (ResultBackBean) baseRedisDaoImpl.index(QUEUE_NAME, index);
	}

	// 移除指定的任务
	public void removeTask(ResultBackBean resultBack) {
		baseRedisDaoImpl.remove(QUEUE_NAME, 1, resultBack);
	}

	// 清空任务队列
	public void clearResultBackQueue() {
		baseRedisDaoImpl.removeAll(QUEUE_NAME);
	}

	// 队列是否为空
	public boolean resultBackQisEmpty() {
		return baseRedisDaoImpl.length(QUEUE_NAME) == 0;
	}

	// 获取队列的长度
	public Long getQueueLength() {
		return baseRedisDaoImpl.length(QUEUE_NAME);
	}
	
	//获取队列所有taskbean
	public List<ResultBackBean> getQueueList() {
		return baseRedisDaoImpl.getListByName(QUEUE_NAME);
	}
}
