package com.zllh.utils.common.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zllh.utils.common.FileUtil;

/**
 * @ClassName: FileUtilImpl
 * @Description: 文件操作工具类实现类
 * @author JW
 * @date 2015年11月24日 上午10:51:09
 */
public class FileUtilImpl implements FileUtil {

	protected final Logger logger = Logger.getLogger(getClass());

	public Document readXML(String filePath) {
		// Document可以看作是XML在内存中的一个镜像,那么一旦获取这个Document 就意味着可以通过对
		// 内存的操作来实现对XML的操作,首先第一步获取XML相关的Document
		// 很明显该类是一个单例,先获取产生DocumentBuilder工厂
		// 的工厂,在通过这个工厂产生一个DocumentBuilder,
		// DocumentBuilder就是用来产生Document的
		Document document = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			// 这个Document就是一个XML文件在内存中的镜像
			document = db.parse(new File(filePath));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	public NodeList getRootNode(Document document, String tagName) {
		return document.getElementsByTagName(tagName);
	}

	public String getContent(NodeList nodes, String fatherName, String childName) {
		// 该方法负责把XML文件的内容显示出来
		// 根据节点名取
		// 获取第一个父节点
		Node fatherNode = nodes.item(0);
		// 把父节点的属性拿出来
		NodeList childNodes = fatherNode.getChildNodes();
		// 遍历节点键值对
		for (int i = 0, len1 = childNodes.getLength(); i < len1; i++) {
			Node attribute = nodes.item(i);
			if (attribute.getNodeName().equals(fatherName)) {
				NodeList subChildNodes = attribute.getChildNodes();
				for (int j = 0, len2 = subChildNodes.getLength(); j < len2; j++) {
					Node childNode = subChildNodes.item(j);
					// 如果这个节点属于Element ,再进行取值
					if (childNode.getNodeName().equals(childName))
						return childNode.getFirstChild().getNodeValue();
				}
			}
		}
		return "";
	}

	public Properties readProperties(String filePath) {
		Properties properties = new Properties();
		InputStream stream = null;
		try {
			stream = this.getClass().getClassLoader().getResourceAsStream(
					filePath); // 读取文件
			if (stream != null)
				properties.load(stream); // 文件流转成property对象
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读取文件错误: " + e.getMessage());
		} finally {
			if (stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return properties;
	}
}
