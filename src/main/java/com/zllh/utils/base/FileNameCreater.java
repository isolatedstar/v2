package com.zllh.utils.base;

import org.apache.commons.lang3.StringUtils;

/**
 * 实例节点文件名生成工具
 * @author luobo
 *
 */
public class FileNameCreater {

	/**
	 * 根据站点id作为目录，模板id作为文件名前缀生成固定的节点预览文件
	 * @param contents
	 * @param templateId
	 * @return
	 */
	public static String creatPreviewFileName(String contents,String templateId){
		StringBuffer url = new StringBuffer("/");
		if(!StringUtils.isEmpty(contents)&&!StringUtils.isEmpty(templateId)){
			url.append(contents);
			url.append("/");
			url.append(templateId);
			url.append(".html");
			return url.toString();
		}
		return null; 
	}
	
	/**
	 * 生成文件名
	 * @param contents
	 * @param templateId
	 * @return
	 */
	public static String creatFileName(String templateId){
		StringBuffer url = new StringBuffer("/");
		if(!StringUtils.isEmpty(templateId)){
			url.append(templateId);
			url.append(".html");
			return url.toString();
		}
		return null; 
	}
	
	public static void main(String[] args) {
//		System.out.println(new FileNameCreater().creatPreviewFileName("bj", "01"));
	}
}
