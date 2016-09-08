package com.zllh.payment.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

public class PostBankMessage {

	public static final Logger logger = Logger.getLogger(PostBankMessage.class);

	public static String connectionExceptionString = "<?xml version=\"1.0\" encoding=\"GBK\"?><stream><status>4610000</status><statusText>服务器访问出错</statusText><list name=\"userDataList\"><row><status>AAAAAAA</status><statusText>交易成功</statusText><stt>0</stt></row></list></stream>";
	//向银行发送报文。
	/**
	 * 发送xml数据请求到server端
	 * 
	 * @param url
	 *            xml请求数据地址
	 * @param xmlInfo
	 *            发送的xml数据流
	 * @return null 发送失败，否则返回响应内容
	 */
	//@SuppressWarnings("deprecation")
	public static String post(String url, String xmlInfo) {
		// 关闭
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient","stdout");

		// 创建httpclient工具对象
		HttpClient client = new HttpClient();
		// 创建post请求方法
		PostMethod myPost = new PostMethod(url);
		// 设置请求超时时间
		client.setConnectionTimeout(300 * 1000);
		String responseString = null;
		try {
			// 设置请求头部类型
			myPost.setRequestHeader("Content-Type", "text/xml");
			myPost.setRequestHeader("charset", "GBK");

			System.out.println("转码前： " + xmlInfo);
			logger.error("转码前： " + xmlInfo);

			// 设置请求体，即xml文本内容，注：这里写了两种方式，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
			myPost.setRequestEntity(new StringRequestEntity(xmlInfo, "text/xml", "GBK"));
			//myPost.setRequestEntity(new StringRequestEntity(xmlString,"text/xml","utf-8"));
			int statusCode = client.executeMethod(myPost);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("~~~~~~~~~~~~~~~~~");
			if (statusCode == HttpStatus.SC_OK) {
				BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
				byte[] bytes = new byte[bis.available()];
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int count = 0;
				while ((count = bis.read(bytes)) != -1) {
					if(count == 0){
						break ;
					}
					bos.write(bytes, 0, count);
				}
				byte[] strByte = bos.toByteArray();
				responseString = new String(strByte, 0, strByte.length, "GBK");
				logger.error("前置机回传报文："+responseString);
				bos.close();
				bis.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
			return connectionExceptionString;
		}finally{
			if(myPost != null) myPost.releaseConnection();
		}
		
		return responseString;
	}
}
