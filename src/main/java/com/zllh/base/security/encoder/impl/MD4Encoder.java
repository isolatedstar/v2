package com.zllh.base.security.encoder.impl;

import org.springframework.security.authentication.encoding.Md4PasswordEncoder;

import com.zllh.base.security.encoder.Encoder;

/**
 * @ClassName: MD4Encoder
 * @Description: MD4加密工具实现类(不支持逆转算法)
 * @author JW
 * @date 2015年11月24日 上午10:03:12
 */
public class MD4Encoder implements Encoder {

	public String decrypt(String password, String key) {
		//不支持该方法抛出UnsupportedOperationException异常
		throw new UnsupportedOperationException("Not supported the mehtod");
	}

	public String encrypt(String password) {
		//使用SPRING SECURITY里的MD4实现类
		return new Md4PasswordEncoder().encodePassword(password, null);
	}

	public String encrypt(String password, String salt) {
		//使用SPRING SECURITY里的MD4实现类
		return new Md4PasswordEncoder().encodePassword(password, salt);
	}

}
