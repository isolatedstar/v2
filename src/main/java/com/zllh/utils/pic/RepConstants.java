package com.zllh.utils.pic;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RepConstants {
	
	public static final String baseDir="D:/apache-tomcat-7.0.47/webapps/rep";//项目部署的地址
	public static String  uploadImgPath;//上传图片的路径
	public static final float ratio=0.5F;//压缩后的图片尺寸比例
	public static final float per=0.5F;//压缩后的图片所占空间比例
	public static final String repImgPath="http://127.0.0.1:8080/rep";//图片路径的绝对路径的前半部
	
	
	public static final int filePIC=1;//压缩后的图片所占空间比例
	public static final int fileDOC=2;//压缩后的图片所占空间比例
	public static final int fileVID=3;//压缩后的图片所占空间比例
	
	
	public static final String ffmpegexePath="D:/ffmpeg/ffmpeg.exe";//ffmpeg路径
	
	public static Set<String> allowFix=new HashSet<String>();  //文件格式
	 static{  
	     allowFix.add("pdf");  
	     allowFix.add("doc");  
	     allowFix.add("docx");  
	     allowFix.add("xlsx");
	     allowFix.add("xls");
	     allowFix.add("txt"); 
	     
	     allowFix.add("jpg");
	     allowFix.add("gif");  
	     allowFix.add("png");  
	     allowFix.add("jpeg"); 
	     
	     allowFix.add("wmv"); 
	     
	 }  
	   
	public static String getUploadimgpath(int type) {
	    if(type==1){
			uploadImgPath= "/upload/img/newImg";
		}
        if(type==2){
        	uploadImgPath= "/upload/vid";
		}
		return uploadImgPath;
	}
	//生成随机数字和字母,  
    public static String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
             
            //输出字母还是数字  
            
                //输出是大写字母还是小写字母   654  大写  97小写 
                val += (char)(random.nextInt(26) + 65);  
           
        }  
        return val;  
    }  
	
	
	
}
