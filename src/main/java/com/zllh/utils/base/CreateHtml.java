package com.zllh.utils.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zllh.utils.common.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CreateHtml {

	protected static String template_path = "/WEB-INF/page/ftl/mall/mmb/";// "/upload/";
	protected static String hrml_path = "/WEB-INF/ftl/price/";// "/jsp/price/"
	protected static String ftp_html_dir = "/html/";

	/**
	 * 生成静态HTML页面
	 * @param data 生成html文件需要的数据
	 * @param servletContext  服务器参数
	 * @param ftlName FTL文件名称（包括目录）
	 * @param fileName 生成的html文件名称（不带.html）
	 * @param htmPath 生成文件的上一级目录
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean createHTML(Map<String, Object> data,
			ServletContext servletContext, String ftlName, String fileName,
			String htmPath) {
		boolean flag = false;
		//设置fremaker参数
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(servletContext, template_path);// ftl的基本路径
		cfg.setEncoding(Locale.getDefault(), "utf-8");// 设置编码
		Writer out = null;
		String rootPath = null;
		try {
			// 获取模板文件
			Template t = cfg.getTemplate(ftlName, "utf-8");
			t.setEncoding("utf-8");
			//判断根目录是否存在，不存在的话创建
			rootPath = servletContext.getRealPath(File.separator) + htmPath;
			File file = new File(rootPath);
			//文件不存在的话创建
            if(!file.exists()){
            	file.mkdirs();
            }
            //待生成的html文件
            File htmlFile = new File(rootPath,FileNameCreater.creatFileName(fileName));
			System.out.println(htmlFile.getAbsolutePath());
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "utf-8"));
			try {
				// 将数据写到静态页面
				t.process(data, out);
				flag = true;
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					flag = false;
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	@SuppressWarnings("deprecation")
	public static boolean addHTML(Map<String, Object> data,
			ServletContext servletContext, String ftlName, String fileName,
			String htmPath,String templatePath) {
		boolean flag = false;
		htmPath = StringUtil.toLinux(htmPath);
		templatePath = StringUtil.toLinux(templatePath);
		//设置fremaker参数
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(servletContext, templatePath);// ftl的基本路径
		cfg.setEncoding(Locale.getDefault(), "utf-8");// 设置编码
		Writer out = null;
		String rootPath = null;
		try {
			// 获取模板文件
			Template t = cfg.getTemplate(ftlName, "utf-8");
			t.setEncoding("utf-8");
			//判断根目录是否存在，不存在的话创建
			rootPath = servletContext.getRealPath(File.separator) + htmPath;
			rootPath = StringUtil.toLinux(rootPath);
			File file = new File(rootPath);
			//文件不存在的话创建
            if(!file.exists()){
            	file.mkdirs();
            }
            //待生成的html文件
            File htmlFile = new File(rootPath,FileNameCreater.creatFileName(fileName));
			System.out.println(htmlFile.getAbsolutePath());
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "utf-8"));
			try {
				// 将数据写到静态页面
				t.process(data, out);
				flag = true;
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					flag = false;
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	// /**
	// * 上传本地文件到ftp服务器
	// */
	// public static void uploadFile(String myFileFileName,String
	// myFileFilePath)throws Exception {
	//
	// FTPClient ftpClient = new FTPClient();//FTP客户端代理
	// FtpService.login(ftpClient);
	//
	// //说明：远程存储目录在FTP服务器上面修改，这里修改无效
	// FtpService.uploadFile(ftpClient, ftp_html_dir, myFileFileName,
	// myFileFilePath);
	//
	// // System.out.println( "文件上传成功..");
	// }

	public static void creatContentFile(HttpServletResponse response,
			String savePath, String saveUrl, String fileExt, FileItem item) {
		response.setContentType("text/html; charset=UTF-8");
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			// out.println(getError(uploadDir+"不存在"));
			// out.println(getError("上传目录不存在。"));
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			// out.println(getError("上传目录没有写权限。"));
			return;
		}
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + File.separator;
		saveUrl += ymd + File.separator;
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		try {
			File uploadedFile = new File(savePath, newFileName);
			item.write(uploadedFile);
		} catch (Exception e) {
			return;
		}
	}

	public static String getDatePath() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// HHmmss
		String newPath = df.format(new Date());
		return newPath + "\\";
	}

	// 目前采用

	public static void writeContent(String content, String basePath,
			String filePath) {

		try {
			// String content = "<html> 你好</html>";
			// test.html
			File file = new File(basePath, filePath);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean createFile(File fileName) throws Exception {
		boolean flag = false;
		try {
			if (!fileName.exists()) {
				fileName.createNewFile();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 追加内容到文件
	 * @param filePath
	 * @param content
	 */
	public static void contentToTxt(String filePath, String content) {
		String str = new String(); // 原有txt内容
		String s1 = new String();// 内容更新
		try {
			File f = new File(filePath);
			if (f.exists()) {
				System.out.print("文件存在");
			} else {
				System.out.print("文件不存在");
				f.createNewFile();// 不存在则创建
			}
			BufferedReader input = new BufferedReader(new FileReader(f));

			while ((str = input.readLine()) != null) {
				s1 += str + "\n";
			}
			System.out.println(s1);
			input.close();
			s1 += content;

			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 读TXT文件内容
	 * @param fileName
	 * @return
	 */
	public static String readTxtFile(File fileName) throws Exception {
		String result = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			try {
				String read = null;
				while ((read = bufferedReader.readLine()) != null) {
					result = result + read + "\r\n";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		}
		System.out.println("读取出来的文件内容是：" + "\r\n" + result);
		return result;
	}

	public static boolean writeTxtFile(String content, File fileName)
			throws Exception {
		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("utf-8"));
			o.close();
			// mm=new RandomAccessFile(fileName,"rw");
			// mm.writeBytes(content);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mm != null) {
				mm.close();
			}
		}
		return flag;
	}

}
