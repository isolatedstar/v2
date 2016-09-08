package com.zllh.utils.common;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class UUIDCreater {
	

	public static synchronized String getUUID() {
		UUID uuid =  UUID.randomUUID();
		return uuid.toString().replace("-", StringUtils.EMPTY);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(UUIDCreater.getUUID());
		}
	}


}
