package com.zllh.utils.common;

public class HtmlUtil {
	public static String replaceSpecialMark(String content){
		content=content.replaceAll("&amp;","&")
				.replaceAll("&lt;","<")
				.replaceAll("&gt;",">")
				.replaceAll("&quot;","\"")
				.replace("&quot;","\'");
		return content;
	}
}
