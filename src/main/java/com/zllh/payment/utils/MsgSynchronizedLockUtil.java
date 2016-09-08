package com.zllh.payment.utils;

import java.util.ArrayList;
import java.util.List;

public class MsgSynchronizedLockUtil {

	public static List<String> lockList = new ArrayList<String>();
	
	/**
	 *  加锁
	 * @param serialID
	 */
	public synchronized static void lock(String serialID){
		if(!checkExist(serialID)){
			lockList.add(serialID);
		}
	}
	
	/**
	 * 解锁
	 * @param serialID
	 */
	public static void removeLock(String serialID){
		if(lockList.contains(serialID)){
			lockList.remove(serialID);
		}
	}
	
	/**
	 * 检查serialID是否存在
	 * @param serialID
	 * @return
	 */
	public static boolean checkExist(String serialID){
		if (lockList.contains(serialID)){
			return true;
		}
		return false;
	}
}
