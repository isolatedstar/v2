package com.zllh.payment.server.queue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.model.TaskBean;
import com.zllh.utils.redis.base.BaseRedisDaoImpl;

@Service
public class ManulQueue {

	private static final String QUEUE_NAME = "MANUL_TASK";
	@Autowired
	private BaseRedisDaoImpl<String, TaskBean> baseRedisDaoImpl;

	// 添加到队列
	public void addTaskInQ(TaskBean task) {
		baseRedisDaoImpl.in(QUEUE_NAME, task);
	}

	// 获取下一个任务
	public TaskBean getNextTask() {
		return (TaskBean) baseRedisDaoImpl.index(QUEUE_NAME, 0);
	}

	public TaskBean getTaskByIndex(int taskId) {

		return (TaskBean) baseRedisDaoImpl.index(QUEUE_NAME, taskId);
	}

	// 移除指定的任务
	public void removeTask(TaskBean task) {
		baseRedisDaoImpl.remove(QUEUE_NAME, 1, task);
	}
	
	// 更新指定的任务
	public void updateTask(long index,TaskBean task) {
		baseRedisDaoImpl.set(QUEUE_NAME, index, task);
	}

	// 清空任务队列
	public void clearTaskQueue() {
		baseRedisDaoImpl.removeAll(QUEUE_NAME);
	}

	public static void resetStartTime(TaskBean task) {
	}

	//队列是否为空
	public boolean taskQisEmpty() {
		return baseRedisDaoImpl.length(QUEUE_NAME) == 0;
	}

	//获取队列的长度
	public Long getQueueLength() {
		return baseRedisDaoImpl.length(QUEUE_NAME);
	}
	
	//获取队列所有taskbean
	public List<TaskBean> getQueueList() {
		return baseRedisDaoImpl.getListByName(QUEUE_NAME);
	}
}
