package com.zllh.payment.utils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zllh.payment.front.service.UtilService;
import com.zllh.payment.model.DataDict;

@Controller
@RequestMapping("/utilController")
public class Util {

	@Autowired
	private UtilService utilService;

	@RequestMapping("/getDataDictByCode")
	@ResponseBody
	public List<DataDict> getDataDictByCode(HttpServletRequest request, HttpServletResponse response, String code ,String type){
		
		List<DataDict> dict = utilService.getDataDictByCode(code);
		if(type!=null&&type.equals("0")){
			DataDict all = new DataDict();
			all.setItemCode("0");
			all.setItemName("全部");
			dict.add(0, all);
		}
		
		return dict;
		
	}

	public static Map<String, Object> setMapColumn(String[] pageOrderColumn, String[] dbOrderColumn) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageOrderColumn.length == dbOrderColumn.length) {
			for (int i = 0; i < pageOrderColumn.length; i++) {
				map.put(pageOrderColumn[i], dbOrderColumn[i]);
			}
		}
		return map;
	}

	public static List<Map<String, Object>> jsonToListMap(String json) {
		return JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>() {
		});
	}

	public static Map<String, Object> jsonToMap(String json) {
		return JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
		});
	}

	/**
	 * 根据xml标签名，查找标签内的value值。
	 */
	public static String getXmlElementByName(String xmlContent, String tagName){
		String tagValueString = "";
		SAXReader saxReader = new SAXReader();
		Document xmlDoc;
		try {
			xmlDoc = (Document) saxReader.read(new ByteArrayInputStream(xmlContent.getBytes("GBK")));
			Element root = (Element) xmlDoc.getRootElement();
			   for(Iterator<Element> i = root.elementIterator();i.hasNext();){
		            Element element = (Element)i.next();
		            if (tagName.equals(element.getName())) {
		            	tagValueString = element.getText();
		            	break;
		            }

		        }
		} catch (UnsupportedEncodingException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		return tagValueString;
	}

	/**
	 * 根据xml标签名，查找查询余额报文结果中的余额等信息。
	 */
	public static String getXmlElementByOneName(String xmlContent, String tagName){
		String tagValueString = "";
		SAXReader saxReader = new SAXReader();
		try {
			Document xmlDoc = (Document) saxReader.read(new ByteArrayInputStream(xmlContent.getBytes("GBK")));
		    Element root = (Element) xmlDoc.getRootElement();
	        for(Iterator<Element> i = root.elementIterator();i.hasNext();){
	            Element element = (Element)i.next();
	            if ("list".equals(element.getName())) {
	            	//子循环
	            	for(Iterator<Element> j = element.elementIterator();j.hasNext();){
	            		Element elementChild = (Element)j.next();
		        		if ("row".equals(elementChild.getName())) {
		        			for(Iterator<Element> m = elementChild.elementIterator();m.hasNext();){
		                		Element elementTarget = (Element)m.next();
		                		if (tagName.equals(elementTarget.getName())) {
				                    tagValueString = elementTarget.getText();
				                 	break;
		                		}
		                 	}
		                }
	            	}
	            }
	        }
	      } catch (UnsupportedEncodingException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return tagValueString;
	}
	
	/**
	 * 根据xml标签名，查找标签内容信息。
	 */
	public static String getContentByLabel(String xml, String label){
		String tagValueString = "";
		SAXReader saxReader = new SAXReader();
		try {
			Document xmlDoc = (Document) saxReader.read(new ByteArrayInputStream(xml.getBytes("GBK")));
		    Element root = (Element) xmlDoc.getRootElement();
	        for(Iterator<Element> i = root.elementIterator();i.hasNext();){
	            Element element = (Element)i.next();
	            if (label.equals(element.getName())) {
                    tagValueString = element.getText();
                 	break;
	            }
	        }
	     } catch (UnsupportedEncodingException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return tagValueString;
	}

	public static Date stringToDate(String timeStr) {

		Date time = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (time != null && (!"".equals(time))) {
				time = (Date) df.parse(timeStr);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
}
