package com.zllh.base.security.encoder.impl;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.zllh.base.security.encoder.Encoder;

/**
 * @ClassName: SHAEncoder
 * @Description: SHA加密工具实现类(不支持逆转算法)
 * @author JW
 * @date 2015年11月24日 上午10:04:40
 */
public class SHAEncoder implements Encoder {

	public String decrypt(String password, String key) {
		//不支持该方法抛出UnsupportedOperationException异常
		throw new UnsupportedOperationException("Not supported the mehtod");
	}

	/**
	 * 不添加盐值加密
	 */
	public String encrypt(String password) {
		//使用SPRING SECURITY里的SHA实现类
		return new ShaPasswordEncoder().encodePassword(password, null);
	}

	/**
	 * 添加盐值加密
	 */
	public String encrypt(String password, String salt) {
		//使用SPRING SECURITY里的SHA实现类
		return new ShaPasswordEncoder().encodePassword(password, salt);
	}

}
