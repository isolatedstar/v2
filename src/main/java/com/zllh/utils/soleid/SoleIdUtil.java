package com.zllh.utils.soleid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.zllh.common.enums.BillsType;

/**
 * @ClassName: SoleIdUtil
 * @Description: 单据号工具类
 * @author JW
 * @date 2016年5月25日 下午2:38:32
 */
public class SoleIdUtil {
	
	/** 配置文件名 */
	private static final String FILENAME = "serialnum.properties";
	
	/** 写入文件阀值 */
	private static final Integer THRESHOLDVALUE = 1000;
	
	/** 内部类单例类 */
	public static class SoleIdSingletion {
		
		private int soleId = 0;
		
		private int maxSole = 0;
		
		private static SoleIdSingletion single = new SoleIdSingletion();
		
		private SoleIdSingletion() {
			soleId = Integer.valueOf(readByKey("soleId"));
			maxSole = soleId + THRESHOLDVALUE;
			update("soleId", String.valueOf((maxSole)));
		}

		private synchronized Integer calculateSoleId(){
			
			int index = soleId++;
			if(maxSole==soleId){
				maxSole =+ maxSole + THRESHOLDVALUE;
				update("soleId", String.valueOf((maxSole)));
			}
			return index;
		}
		
		/**
		 * @Title: getIntSoleId 
		 * @author JW
		 * @Description: 获取INt单据号
		 * @param billsType
		 * @return String
		 * @throws
		 */
		public String getIntSoleId(Integer billsType){
			
			if(BillsType.ORDER.getValue().equals(billsType)){
				return String.valueOf(BillsType.ORDER.getValue()+calculateSoleId());
			}else if(BillsType.QUOTATION.getValue().equals(billsType)){
				return String.valueOf(BillsType.QUOTATION.getValue()+calculateSoleId());
			}else if(BillsType.JIEKUAN.getValue().equals(billsType)){
				return String.valueOf(BillsType.JIEKUAN.getValue()+calculateSoleId());
			}else if(BillsType.FINANCING.getValue().equals(billsType)){
				return String.valueOf(BillsType.FINANCING.getValue()+calculateSoleId());
			}else if(BillsType.OTHER.getValue().equals(billsType)){
				return String.valueOf(BillsType.OTHER.getValue()+calculateSoleId());
			}
			
			return null;
		}
		
		/**
		 * @Title: getStrSoleId 
		 * @author JW
		 * @Description: 获取String单据号
		 * @param billsType
		 * @return String
		 * @throws
		 */
		public String getStrSoleId(Integer billsType){
			
			if(BillsType.ORDER.getValue().equals(billsType)){
				return "OD" + String.valueOf(BillsType.ORDER.getValue()+calculateSoleId());
			}else if(BillsType.QUOTATION.getValue().equals(billsType)){
				return "QT" + String.valueOf(BillsType.QUOTATION.getValue()+calculateSoleId());
			}else if(BillsType.JIEKUAN.getValue().equals(billsType)){
				return "ST" + String.valueOf(BillsType.JIEKUAN.getValue()+calculateSoleId());
			}else if(BillsType.FINANCING.getValue().equals(billsType)){
				return "FI" + String.valueOf(BillsType.FINANCING.getValue()+calculateSoleId());
			}else if(BillsType.OTHER.getValue().equals(billsType)){
				return "OT" + String.valueOf(BillsType.OTHER.getValue()+calculateSoleId());
			}
			
			return null;
		}
		
		/** 根据key获取值 */
		private String readByKey(String key){
			String path = System.getProperty("catalina.home") + File.separatorChar + FILENAME ;
			Properties properties = new Properties();
			File file = new File(path);
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				properties.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return properties.getProperty(key);
		}

		/** 更新文件 */
		private void update(String key, String value){
		
			String path = System.getProperty("catalina.home") + File.separatorChar + FILENAME ;
			Properties properties = new Properties();
			File file = new File(path);
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(file);
				fos = new FileOutputStream(file);
				properties.load(fis);
				properties.setProperty(key, value);
				properties.store(fos, "更新文件");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					fos.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static SoleIdSingletion getSoleIdSingletion() {
		return SoleIdSingletion.single;
	}

	public static void test(){
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				SoleIdSingletion test = SoleIdUtil.getSoleIdSingletion();
				System.err.println(Thread.currentThread().getName() + "--" + test.getIntSoleId(BillsType.ORDER.getValue()));
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				SoleIdSingletion test = SoleIdUtil.getSoleIdSingletion();
				System.err.println(Thread.currentThread().getName() + "--" + test.getIntSoleId(BillsType.QUOTATION.getValue()));
			}
		},"t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				SoleIdSingletion test = SoleIdUtil.getSoleIdSingletion();
				System.err.println(Thread.currentThread().getName() + "--" + test.getIntSoleId(BillsType.ORDER.getValue()));
			}
		},"t3");
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				SoleIdSingletion test = SoleIdUtil.getSoleIdSingletion();
				System.err.println(Thread.currentThread().getName() + "--" + test.getStrSoleId(BillsType.ORDER.getValue()));
			}
		},"t4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
//		for(int i = 1; i <=1000; i ++){
//			new Thread("线程"+i){
//				public void run(){
//					SoleIdSingletion test = SoleIdUtil.getSoleIdSingletion();
//					System.err.println(Thread.currentThread().getName() + "--" + test.getIntSoleId(BillsType.ORDER.getValue()));
//				}
//			}.start();
//		}
		
	}
	
}
