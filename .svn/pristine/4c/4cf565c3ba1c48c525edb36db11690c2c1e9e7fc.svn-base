/*
 * Created on 2005-5-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.zllh.utils.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: StringUtil
 * @Description: StringUtil
 * @author JW
 * @date 2015-12-18 下午4:13:55
 */
public class StringUtil {

	public static float toFloat(String s) {
		if(s==null||s=="")return 0;
		try {
			return Float.parseFloat(s);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String checkNull(String s){
		if(s == null || "null".equals(s)){
			s = "";
		}
		return s;
	}
	
	public static double toDouble(String s) {
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int StringToId(String strId) {
		int id = 0;
		if(strId != null && !strId.trim().equals("")){
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public static int toInt(String strId) {
		int id = -1;
		try {
			id = Integer.parseInt(strId);
		} catch (Exception e) {
		}
		return id;
	}

	public static long toLong(String strId) {
		long id = -1;
		try {
			id = Long.parseLong(strId);
		} catch (Exception e) {
		}
		return id;
	}

	public static String convertNull(String s) {
		if (s == null) {
			return "";
		} else {
			return s;
		}
	}

	public static int StringToId(HttpServletRequest request, String name) {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter(name));
		} catch (Exception e) {
		}
		return id;
	}

	public static String getFileExt(String filePath) {
		if (filePath == null) {
			return null;
		}

		String path = filePath.trim();

		if (path.equals("")) {
			return null;
		}

		return path.substring(path.lastIndexOf('.') + 1, path.length());
	}

	public static String getUrlAction(String url) {
		if (url == null) {
			return null;
		}

		String action = url.trim();

		if (action.equals("")) {
			return null;
		}
		if (!action.substring(action.length() - 3).equals(".do"))
			return "";
		return action.substring(action.lastIndexOf('/') + 1,
				action.length() - 3);
	}

	public static String getShortAction(String fullname) {
		if (fullname == null) {
			return null;
		}

		String action = fullname.trim();

		if (action.equals("")) {
			return null;
		}

		return action.substring(action.lastIndexOf('/') + 1, action.length());
	}

	public static String getUrlPerm(String url) {
		if (url == null) {
			return null;
		}
		if (url.startsWith("/")) {
			int pos = url.indexOf("-");
			if (pos < 0)
				return null;

			return url.substring(1, pos);
		}
		return null;
	}

	public static String getStringId2(int id) {
		if (stringId2 == null) {
			stringId2 = new String[100];
			for (int i = 0; i < 10; i++) {
				stringId2[i] = "0" + String.valueOf(i);
			}
			for (int i = 10; i < 100; i++) {
				stringId2[i] = String.valueOf(i);
			}
		}
		return stringId2[id];
	}

	private static String[] stringId2 = null;

	public static String getCode(boolean needreply, int corpId, int userId) {
		String code = "02";
		if (needreply) {
			return code + getStringId2(corpId) + String.valueOf(userId);
		} else {
			return code + getStringId2(corpId);
		}
	}

	public static String dealParam(String param) {
		if (param == null) {
			return param;
		}

		//param = param.replaceAll("'", "\"");
		param = param.replace("\\", "\\\\");
		param = param.replace("'", "\\'");
		param = param.trim();
		return param;
	}

	public static String toSql(String src) {
		src = src.replace("\\", "\\\\");
		src = src.replace("'", "\\'");
		return src;
	}

	public static String toSqlLike(String src) {
		src = src.replace("\\", "\\\\");
		src = src.replace("'", "\\'");
		src = src.replace("%", "\\%");
		src = src.replace("_", "\\_");
		return src;
	}

	public static String formatDouble(double d) {
		DecimalFormat df = new DecimalFormat("##########0.##");
		return df.format(d);
	}

	public static String formatDouble2(double d) {
		DecimalFormat df = new DecimalFormat("##########0.###");
		return df.format(d);
	}

	public static String formatFloat(float d) {
		DecimalFormat df = new DecimalFormat("##########0.##");
		return df.format(d);
	}

	public static String formatFloat2(float d) {
		DecimalFormat df = new DecimalFormat("##########0.###");
		return df.format(d);
	}

	public static String formatFloat3(float d) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}
	
	public static String toWml(String src) {
		if (src == null)
			return "";

		src = src.replaceAll("&", "&amp;");
		src = src.replaceAll("\\$", "");
		src = src.replaceAll("¤", "O");
		src = src.replaceAll("<", "&lt;");
		src = src.replaceAll(">", "&gt;");
		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll("", "");
		src = src.replace("\"", "&#34;");

		return src;
	}
	
	/**
	 * 将String中的'\r\n'和'\n' 都替换成""并返回
	 * @param src
	 * @return
	 */
	public static String removeChangeLine(String src) {
		if( src == null ) 
			return "";
		src = src.replaceAll("\r\n", " ");
		src = src.replaceAll("\n", " ");
		return src;
	}

	public static String dealLink(String link, HttpServletRequest request,
			HttpServletResponse response) {
		if (link == null) {
			return link;
		}

		String domain = request.getServerName();
		if (link.startsWith("/")) {
			link = "http://" + domain + link;
		}
		//        link = link.replaceAll("&", "&amp;");

		return link;//response.encodeURL(link);
	}

	static Pattern p = Pattern.compile("\\[img\\]([^\\[]*)\\[img\\]");

	static String urlRegex = "[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][%]&[)][(]]*";

	public static String toHtml(String src) {
		if (src == null)
			return "";


		src = src.replaceAll("&", "&amp;");
		src = src.replaceAll("\\$", "");
		src = src.replaceAll("¤", "O");
		src = src.replaceAll("<", "&lt;");
		src = src.replaceAll(">", "&gt;");
		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll("", "");
		src = src.replace("\"", "&#34;");

		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll(" ", "&nbsp;&nbsp;");
		src = src.replaceAll(urlRegex,
				"<a href=\"$0\" target=\"_blank\">$0</a>");
		Matcher m = p.matcher(src);
		while (m.find()) {
			String s = m.group(1);
			s = "<a href=\"" + s + "\" target=_blank>" + "<img src=\"" + s
					+ "\" width=\"400\" border=0 alt=\"点击查看大图\"/></a>";
			src = m.replaceFirst(s);
			m = p.matcher(src);
		}
		return src;
	}
	
	public static String toSecurityHtml(String src) {
		if (src == null)
			return "";

		src = src.replaceAll("&", "&amp;");
		src = src.replaceAll("\\$", "");
		src = src.replaceAll("¤", "O");
		src = src.replaceAll("<", "&lt;");
		src = src.replaceAll(">", "&gt;");
		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll("", "");
		src = src.replace("\"", "&#34;");
		src = src.replace("\'", "&#39;");
		src = src.replace("=", "&#61");
		src = src.replace(":", "&#58");
		src = src.replace("(", "&#40");
		src = src.replace(")", "&#41");
		src = src.replace("$", "&#36");
		src = src.replace(".", "&#46");
		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll(" ", "&nbsp;&nbsp;");
		src = src.replaceAll(urlRegex,"");
		return src;
	}

	public static String array2String(String[] strs, String split) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(strs.length * 10);
		for (int i = 0; i < strs.length; i++) {
			if (i != 0) {
				builder.append(split);
			}
			builder.append(strs[i]);
		}
		return builder.toString();
	}

	public static boolean isNull(String s) {
		if (s == null) {
			return true;
		}
		if ("".equals(s)) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(String s){
		boolean result = false;
		
		if(convertNull(s).trim().equals("")){
			result = true;
		}
		
		return result;
	}
	
	 public static String toUtf8String(String s){ 
	     StringBuffer sb = new StringBuffer(); 
	       for (int i=0;i<s.length();i++){ 
	          char c = s.charAt(i); 
	          if (c >= 0 && c <= 255){sb.append(c);} 
	        else{ 
	        byte[] b; 
	         try { b = Character.toString(c).getBytes("utf-8");} 
	         catch (Exception ex) { 
	             System.out.println(ex); 
	                  b = new byte[0]; 
	         } 
	            for (int j = 0; j < b.length; j++) { 
	             int k = b[j]; 
	              if (k < 0) k += 256; 
	              sb.append("%" + Integer.toHexString(k).toUpperCase()); 
	              } 
	     } 
	  } 
	  return sb.toString(); 
	}
	 
	/**
	 * @Title: toUtf8String
	 * @author JW
	 * @Description: 自适应浏览器返回中文文件xls名称的方法
	 * @param s
	 * @param request
	 * @return String
	 * @throws
	 */
	public static String toUtf8String(String s, HttpServletRequest request) {
		String browserType = request.getHeader("user-agent");
		if (browserType.indexOf("Firefox") > -1) {
			String result = "";
			try {
				result = new String(s.getBytes("utf-8"), "iso-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return result;
		} else {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;
					try {
						b = Character.toString(c).getBytes("utf-8");
					} catch (Exception ex) {
						b = new byte[0];
					}
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0)
							k += 256;
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				}
			}
			return sb.toString();
		}
	}


	 
	public static boolean isNumeric(String str) {
		if (str.matches("\\d*")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isMobile(String s) {
		if (s == null) {
			return false;
		}

		if (!s.startsWith("13") && !s.startsWith("15") && !s.startsWith("14") && !s.startsWith("18")) {
			return false;
		}

		if (s.length() != 11) {
			return false;
		}

		try {
			Long.parseLong(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static String cutString(String s, int count) {
		if (s == null) {
			return s;
		}
		if (s.length() < count) {
			return s;
		}
		s = s.substring(0, count);
		return s;
	}

	public static String cutString(String s, int start, int end) {
		if (s == null) {
			return s;
		}
		if (s.length() < start || s.length() < end) {
			return s;
		}
		s = s.substring(start, end);
		return s;
	}

	//input 字符 
	//index 需要截取的长度 
	public static String getString(String input, int index) {
		int temp = 0; //长度
		StringBuffer sb = new StringBuffer(""); // 构造一个字符串缓冲区，并将其内容初始化为指定的字符串内容。
		for (int i = 0; i < input.length(); i++) {
			//获取每个字符 
			String slice = input.substring(i, i + 1);//循环分解字符串
			//substring()返回一个新的字符串，它是此字符串的一个子字符串。 
			byte[] strByte = slice.getBytes();
			//getBytes()使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
			if (strByte.length == 1) {//长度为1，则为英文字符 
				sb.append(slice);

				if (++temp == index) {
					return sb.toString();
				}
			} else {//长度为2，应为中文字符 
				if (temp + 2 > index) {//如果长度再加2，超过需要截取的长度 
					return sb.toString();
				}
				if (temp + 2 == index) {//如果长度再加2等于需要截取的长度,加上中文字符，返回 
					return sb.append(slice).toString();
				} else {//未超过截取字符，附加上中文字符 
					sb.append(slice);
					temp += 2;
				}
			}
		}
		return sb.toString();
	}
	
	public static String getGetMethodName(String name){
		return "get"+name.substring(0,1).toUpperCase()+name.substring(1);
	}
	
	public static String getSetMethodName(String name){
		return "set"+name.substring(0,1).toUpperCase()+name.substring(1);
	}
	
	public static String convertNull(String s,String def) {
		if (isNull(s)) {
			return def;
		} else {
			return s;
		}
	}

	/**
	 * @Title: padLeft
	 * @author JW
	 * @Description: 补位左填充
	 * @param input
	 * @param c
	 * @param length
	 * @return String
	 * @throws
	 */
	public static String padLeft(String input, char c, int length) {
		String output = input;
		while (output.length() < length) {
			output = c + output;
		}
		return output;
	}
	
	public static boolean toBoolean(String s){
		boolean b = false;
		try{
			b = Boolean.parseBoolean(s);
		}catch (Exception e) {
			
		}
		return b;
	}
	
	//异常信息输出转换
    public static String getExceptionInfo(Throwable t) {   
        StringWriter stringWriter= new StringWriter();   
        PrintWriter writer= new PrintWriter(stringWriter);   
        t.printStackTrace(writer);   
        StringBuffer buffer= stringWriter.getBuffer();   
        return buffer.toString();   
    }   

    /**
     * 说明：查询字符串数据中，是否包含某字符串
     */
    public static boolean hasStrArray(String[] array, String s){
    	boolean result = false;
    	for(int i=0;i<array.length;i++){
    		if(array[i].equals(s)){
    			result = true;
    			break;
    		}
    	}
    	return result;
    }
    
    /**
     * @Title: changStrToXml
     * @author JW
     * @Description:  &lt; < 小于号 
	 * 	              &gt; > 大于号 
	 * 	              &amp; & 和 
	 * 	              &apos; ' 单引号 
	 * 	              &quot; " 双引号
     *                功能:需要转化为xml 的特殊字符变化为 转义字符
     * @param str
     * @return String
     * @throws
     */
    public static String changStrToXml(String str){
    	String xml="";
    	if(str==null || str.length()<1) return "";
    	
    	xml=str.replaceAll("<", "&lt;");
    	xml=xml.replaceAll(">", "&gt;");
    	xml=xml.replaceAll("&", "&&amp;");
    	xml=xml.replaceAll("'", "&apos;");
    	xml=xml.replaceAll("\"", "&quot;");
    	
    	return xml;
    }
    
    static Pattern pattern = Pattern.compile("\\d{0,9}");
    static Pattern pattern1 = Pattern.compile("\\[1-9]{1,9}");

	/**
	 * @Title: parstInt
	 * @author JW
	 * @Description: 转化成数字
	 * @param id
	 * @return int
	 * @throws
	 */
    public static int parstInt(String id){
    	if(convertNull(id).equals("")) return 0;
    	Matcher m = pattern.matcher(id);
		if(m.matches()){
			return Integer.parseInt(id);
		}else{
			return 0;
		}
    }
    
    /**
     * @Title: parstBackMinus
     * @author JW
     * @Description: 转化成数字, 返回负数
     * @param id
     * @return int
     * @throws
     */
    public static int parstBackMinus(String id){
    	if(convertNull(id).equals("")) return -1;
    	Matcher m = pattern.matcher(id);
		if(m.matches()){
			return Integer.parseInt(id);
		}else{
			return -1;
		}
    }

	private final static Locale locale = Locale.CHINA;
	
	/**
	 * @Title: toLowerCase
	 * @author JW
	 * @Description: 变成小写
	 * @param str
	 * @return String
	 * @throws
	 */
	public static String toLowerCase(String str){
		return str!=null?str.toLowerCase(locale):"";
	}
	
	/**
	 * @Title: removeStartAndEndSymbol
	 * @author JW
	 * @Description: 去掉字符串前后的 符号
	 * @param s
	 * @param symbol
	 * @return StringBuilder
	 * @throws
	 */
	public static StringBuilder removeStartAndEndSymbol(StringBuilder s , char symbol){
		if(s==null)return null;
    	if (s.length()>0&&s.charAt(0) == symbol) {
    		s.deleteCharAt(0);
		}
		if (s.length() > 0 && s.charAt(s.length() - 1) == symbol) {
			s.deleteCharAt(s.length() - 1);
		}
		return s ;
	}

	public static boolean inArray(int[] array, int value) {
		if(array != null && array.length > 0) {
			Arrays.sort(array);
			return Arrays.binarySearch(array, value) >= 0;
		}
		return false;
	}
	
	/**
	 * @Title: getPersent
	 * @author JW
	 * @Description: 获取百分比，小数点后留1位
	 * @param num
	 * @return String
	 * @throws
	 */
	public static String getPersent(float num) {
		num = num*100;
		String result = String.valueOf(num);
		if(result.length()-result.indexOf(".")>3) {
			result = result.substring(0, result.indexOf(".")+3);
		}
		return result+"%";
	}
	
	static Pattern ptc = Pattern.compile("[\\u4e00-\\u9fa5]+");
	public static boolean isChainese(String value){
		Matcher m = ptc.matcher(value);
		if(m.matches()){
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: transfer
	 * @author JW
	 * @Description: 将数字转化为汉字数字 例如：1---一，11--十一
	 * @param n
	 * @return String
	 * @throws
	 */
	public static String transfer(int n){
		Stack<Integer> st = new Stack<Integer>();
		int division = 0; // 余数
		while (n >= 10) {
			division = n % 10;
			st.push(division);
			n = n / 10;
		}
		st.push(n); // 将最高位压栈
		
		//数字与汉字的映射表
		HashMap<Integer, String> numberMap = new HashMap<Integer, String>();
		numberMap.put(0, "零");
		numberMap.put(1, "一");
		numberMap.put(2, "二");
		numberMap.put(3, "三");
		numberMap.put(4, "四");
		numberMap.put(5, "五");
		numberMap.put(6, "六");
		numberMap.put(7, "七");
		numberMap.put(8, "八");
		numberMap.put(9, "九");
		
		//位数与其对应的单位的映射表
		HashMap<Integer, String> unitMap = new HashMap<Integer, String>();
		unitMap.put(2, "十");
		unitMap.put(3, "百");
		unitMap.put(4, "千");
		unitMap.put(5, "万");
		unitMap.put(6, "十万");
		unitMap.put(7, "百万");
		unitMap.put(8, "千万");
		unitMap.put(9, "亿");
		
		String out = "";
		int count = 0;
		while (!st.isEmpty()) {
			//移除堆栈顶部的对象,并获取顶部对象的值
			int temp = st.pop();
			if (st.size() == 0) {
				if (temp != 0) {
					out = out + numberMap.get(temp);
				}
			} else {
				if (temp == 0) {
					count++;
					//处理这种2005--二千零五，而不是二千零零五
					if(count<2){
						out = out + numberMap.get(temp);
					}
				} else {
					out = out + numberMap.get(temp) + unitMap.get(st.size() + 1);
					//处理特殊的读法，10--十,11,十一而不是一十一
					if("一十".equals(out)){
						out = "十";
					}
				}
			}
		}
		return out;
	}
	
	/**
	 * 给数值的左边加0 以满足位数
	 * @param i   数值
	 * @param j   总位数值
	 * @return
	 */
	public static String addZeroLeft(int i, int j) {
		String formatRule = "%0"+j+"d";
		String b = String.format(formatRule, i);
		return b;
	}
	
	/**
	 * 将驼峰式命名的字符串转换为下划线小写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HelloWorld->hello_world
	 * @param name 转换前的驼峰式命名的字符串
	 * @return 转换后下划线小写方式命名的字符串
	 */
	public static String underscoreName(String name) {
	    StringBuilder result = new StringBuilder();
	    if (name != null && name.length() > 0) {
	        // 将第一个字符处理成大写
	        result.append(name.substring(0, 1).toUpperCase());
	        // 循环处理其余字符
	        for (int i = 1; i < name.length(); i++) {
	            String s = name.substring(i, i + 1);
	            // 在大写字母前添加下划线
	            if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
	                result.append("_");
	            }
	            // 其他字符直接拼接
	            result.append(s);
	        }
	    }
	    return result.toString().toLowerCase();
	}
	
	public static boolean isOK(int ret) {
		return ret == 200;
	}
	
	/**
	 * 把一个数组分割指定数量的多个数组
	 * @param ary 要分割的数组
	 * @param subSize 每多少个分成一组
	 * @return
	 */
	public static Object[] splitAry(String[] ary, int subSize) {
		List<List<String>> subAryList = new ArrayList<List<String>>();

		for (int i = 0; i < ary.length; i++) {
			int index = i * subSize;
			List<String> list = new ArrayList<String>();
			int j = 0;
			while (j < subSize && index < ary.length) {
				list.add(ary[index++]);
				j++;
			}
			subAryList.add(list);
		}

		Object[] subAry = new Object[subAryList.size()];

		for (int i = 0; i < subAryList.size(); i++) {
			List<String> subList = subAryList.get(i);
			String[] subAryItem = new String[subList.size()];
			for (int j = 0; j < subList.size(); j++) {
				subAryItem[j] = subList.get(j).toString();
			}
			subAry[i] = subAryItem;
		}

		return subAry;
	}
	
	public static String toUnicodeString(String s) {
		 StringBuffer sb = new StringBuffer();
		 for (int i = 0; i < s.length(); i++) {
			 char c = s.charAt(i);
			 if (c >= 0 && c <= 255) {
				 sb.append(c);
			 }
			 else {
				 sb.append("\\u"+Integer.toHexString(c));
			 }
		 }
		 return sb.toString();
     }
	
}
