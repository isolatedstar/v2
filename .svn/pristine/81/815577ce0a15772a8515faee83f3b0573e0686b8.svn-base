package com.zllh.utils.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;


/**
 * @ClassName: HttpUtils
 * @Description: HTTP链接工具
 * @author JW
 * @date 2016年1月6日 下午3:00:27
 */
public class HttpUtils {

	//http连接默认超时时间 10s
	private static final int DEFAULT_CONNECTION_TIMEOUT = 10000;
	//默认每个IP允许的最大连接数
	private static final int DEFAULT_MAX_CONNECTION_PER_HOST = 50;
	//默认总连接数
	private static final int DEFAULT_MAX_TOTAL_CONNECTION = 50;
	//默认空闲连接超时时间 60s
	private static final int DEFAULT_IDLE_CONNECTION_TIMEOUT = 60000;
	
	private static final int REQUEST_SUCCESS = 200;
	
	private static HttpConnectionManager connectionManager;

	static{
		//创建一个HTTP连接池
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(DEFAULT_MAX_CONNECTION_PER_HOST);
		connectionManager.getParams().setMaxTotalConnections(DEFAULT_MAX_TOTAL_CONNECTION);
		
		//创建空闲连接超时线程
		IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
		ict.addConnectionManager(connectionManager);
		ict.setConnectionTimeout(DEFAULT_IDLE_CONNECTION_TIMEOUT);
	}
	
	/**
	 * @Title: post 
	 * @author JW
	 * @Description: POST请求
	 * @param url
	 * @param paramMap
	 * @return String
	 * @throws
	 */
	public String post(String url,Map<String, String> paramMap){
		String resposeResult = "";
		HttpClient httpClient = new HttpClient(connectionManager);
		PostMethod method = new PostMethod(url);
		//设置请求参数
		Set<Entry<String, String>> entrySet = paramMap.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
			method.addParameter(entry.getKey(),entry.getValue());
		}
		try {
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			//执行post请求
			int statusCode = httpClient.executeMethod(method);
			//响应结果
			if(statusCode == REQUEST_SUCCESS){
				resposeResult = method.getResponseBodyAsString();
			}else {
				System.out.println("调用接口失败，response code："+statusCode);
				return "";
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			method.releaseConnection();
		}
		return resposeResult;
	}
	
	/**
	 * @Title: httpUrlConnection 
	 * @author JW
	 * @Description: POST请求
	 * @param postparam
	 * @param pathUrl
	 * @return String
	 * @throws
	 */
	public String httpUrlConnection(String postparam, String pathUrl) {
		StringBuffer sb = new StringBuffer();
		try {
			// 建立连接
			URL url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

			//设置连接属性
			httpConn.setRequestMethod("POST");//设置URL请求方法
			httpConn.setDoOutput(true);       //使用 URL连接进行输出
			httpConn.setDoInput(true);        //使用 URL连接进行输入
			httpConn.setUseCaches(false);     //忽略缓存

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			byte[] requestStringBytes = postparam.getBytes("utf-8");
			httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
			httpConn.setRequestProperty("Content-Type", "application/octet-stream");
			httpConn.setRequestProperty("Connection", "Keep-Alive");//维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");

			//建立输出流,并写入数据
			OutputStream outputStream = httpConn.getOutputStream();
			outputStream.write(requestStringBytes);
			outputStream.close();
			// 获得响应状态״̬
			int responseCode = httpConn.getResponseCode();
			//连接成功
			if (HttpURLConnection.HTTP_OK == responseCode) {
				//当正确响应时处理数据
				String readLine;
				BufferedReader responseReader;
				//处理响应流,必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * @Title: xmlIinputStream
	 * @author JW
	 * @Description: 获取请求参数
	 * @param request
	 * @param response void
	 * @throws
	 */
	public String xmlIinputStream(HttpServletRequest request){
		
		StringBuffer buffer = new StringBuffer();
		BufferedReader ain = null;
		InputStream  in = null;
		try {
			 in = request.getInputStream();
			ain = new BufferedReader(new InputStreamReader(in,"utf-8"));
		    String line = "";
		    while ((line = ain.readLine()) != null){
		      buffer.append(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				ain.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}
}
