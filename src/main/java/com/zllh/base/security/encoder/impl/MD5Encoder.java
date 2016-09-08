package com.zllh.base.security.encoder.impl;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.zllh.base.security.encoder.Encoder;


/**
 * @ClassName: MD5Encoder
 * @Description: MD5加密工具实现类(不支持逆转算法)
 * @author JW
 * @date 2015年11月24日 上午10:03:38
 */
public class MD5Encoder implements Encoder {

	public String decrypt(String password, String key) {
		// 不支持该方法抛出UnsupportedOperationException异常
		throw new UnsupportedOperationException("Not supported the mehtod");
	}

	public String encrypt(String password) {
		// 使用SPRING SECURITY里的MD5实现类
		return new Md5PasswordEncoder().encodePassword(password, null);
	}

	public String encrypt(String password, String salt) {
		// 使用SPRING SECURITY里的MD5实现类
		return new Md5PasswordEncoder().encodePassword(password, salt);
	}

	public static void main(String[] args) {
		System.out.println(new MD5Encoder().encrypt("asdf1234", "tmsh"));
	}
}
