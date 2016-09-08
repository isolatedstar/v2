package com.zllh.utils.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoCreateDir {
	
	
	/*
	 * 
	 * 以下savePicUrl必须与webapp--js-- kindeditor-- upload_json.jsp中变量保持一致
	 * 
	 * */
	
	
	
	//文件基础路径
	//public static final String baseFilePath="/";//2015-2-9
	public static final String baseFilePath="";//2015-3-9
	//项目路径
	public static final String basePath="http://106.2.221.174:2000\\v2\\";
	
	//ftl模板存放路径
	public static final String ftl="\\mall\\ftl\\";
	//日期后文件类型为文章的路径
	public static final String baseTypeFilePath="mmbhtml\\";//  HTML页面
	public static final String baseTypePicPath="\\mmbhtml\\img";//  原图片
	public static final String baseTypeComPicPath="\\CompressImg";// 压缩图片
	public static final String baseTypeMedioOrDocPath="\\medio";//  媒体和整个文档
	//二维码
	public static final String baseImgPath="mmbhtml\\imgCode\\";
	public static void createDir (String str) {
		
		 File file = new File(str);
        if(file!=null){
        	file.mkdirs();
        }
	}
	
	
	//获得任意相对路径+日期
	public static  String filePathPlusDate(String filepath) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy\\MM\\dd");//HHmmss
		String newPath = df.format(new Date());
		
		createDir(filepath+"\\"+newPath+"\\");
		return filepath+"\\"+newPath+"\\";
		
	}
	
	
	//消息文章html保存路径
	public static  String getbaseDirPlusDateNews() {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy\\MM\\dd");//HHmmss
		String newPath = df.format(new Date());
		
		createDir(baseFilePath+newPath+"\\"+baseTypeFilePath+"\\");
		System.out.println((baseFilePath+newPath+baseTypeFilePath+"/").replaceAll("\\\\", "/"));
		return (baseFilePath+newPath+baseTypeFilePath+"/").replaceAll("\\\\", "/");//.replaceAll("/[/]{2}/g","/");
		
	}
	//消息媒体文件和文档信息
		public static  String getMedioOrDocNews() {
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy\\MM\\dd");//HHmmss
			String newPath = df.format(new Date());
			
			createDir(baseFilePath+newPath+"\\"+baseTypeMedioOrDocPath+"\\");
			System.out.println((baseFilePath+newPath+baseTypeMedioOrDocPath+"/").replaceAll("\\\\", "/"));
			return (baseFilePath+newPath+baseTypeMedioOrDocPath+"/").replaceAll("\\\\", "/");//.replaceAll("/[/]{2}/g","/");
			
		}
	
	//图片的保存路径
		public static  String getbaseDirPlusPicNews() {
			SimpleDateFormat df = new SimpleDateFormat("yyyy\\MM\\dd");//HHmmss
			String newPath = df.format(new Date());
			
			createDir(baseFilePath+newPath+"\\"+baseTypePicPath+"\\");
			System.out.println((baseFilePath+newPath+baseTypePicPath+"/").replaceAll("\\\\", "/"));
			return (baseFilePath+newPath+baseTypePicPath+"/").replaceAll("\\\\", "/");//.replaceAll("/[/]{2}/g","/");
		}
	//图片压缩后的保存路径
		public static  String getCompressPicDirNews() {
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy\\MM\\dd");//HHmmss
			String newPath = df.format(new Date());
			
			createDir(baseFilePath+newPath+"\\"+baseTypeComPicPath+"\\");
			System.out.println((baseFilePath+newPath+baseTypeComPicPath+"/").replaceAll("\\\\", "/"));
			return (baseFilePath+newPath+baseTypeComPicPath+"/").replaceAll("\\\\", "/");//.replaceAll("/[/]{2}/g","/");
			
		}	
		
	
//	public static void main(String args[]){
//		
//		AutoCreateDir ac= new AutoCreateDir();
//		//ac.createFiles();
//		System.out.println(ac.getbaseDirPlusDate());
//		
//		
//		
//	}
	
	
	
	
}
