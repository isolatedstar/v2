package com.zllh.mall.material.controller;

import java.awt.AlphaComposite;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;














import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtMaterial;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.material.service.MaterialService;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.UUIDCreater;
import com.zllh.utils.pic.ImageUtils;
import com.zllh.utils.pic.RepConstants;


@Controller
@RequestMapping("/material")
public class MaterialController extends BaseController {
	
	
	//资源
	@Autowired
	private MaterialService materialService;
	// 上传文件集合
	private List<File> file;
	// 上传文件名集合
	private List<String> fileFileName;
	
	
	public List<File> getFile() {
		return file;
	}


	public void setFile(List<File> file) {
		this.file = file;
	}


	public List<String> getFileFileName() {
		return fileFileName;
	}


	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}


	/**
	 * 显示图片
	 * @param model
	 * @param divId  所属的目录ID
	 * @param pageNo1  分页
	 * @param pageSize
	 * @return  当前页数据  总数
	 */
	@RequestMapping("/showPic")
	@ResponseBody
	public Map<String, Object> showPic(Model model,
			@RequestParam(value = "materialName", required = false) String materialName,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "divId", required = false) String divId,
			@RequestParam(value = "pageNo", required = false) String pageNo1,
			@RequestParam(value = "pageSize", required = false) Integer pageSize){
		logger.info("===显示图片====");
		Long pageNo = StringUtils.isBlank(pageNo1) ? 1 : Long.valueOf(pageNo1);
		pageSize = (pageSize == null && pageSize < 1) ? 2 : pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("divId", divId);
		if(!StringUtils.isBlank(materialName)){
			params.put("materialName", materialName);
		}
		if(type!=null){
			params.put("type", type);
		}
		long totalCount = materialService.countShowPic(params);
		// 当前页数不能超过总页数
		pageNo = (pageNo < 1) ? 1 : pageNo;
		long totalPageCount = (totalCount + pageSize - 1) / pageSize;
		if (pageNo > totalPageCount) {
			pageNo = totalPageCount;
		}
		params.put("startFirst", pageNo > 0 ? (pageNo - 1) * pageSize : 0);
		params.put("startEnd", pageSize);
		List<MtMaterial> list = materialService.showPic(params);
		Map<String, Object> returnmap = new HashMap<String, Object>();
		returnmap.put("picCount", totalCount);
		returnmap.put("picList", list);
		return returnmap;
	}
	//新增图片
	@RequestMapping("/addMal1")
	@ResponseBody
	public String addMal(){
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		String divId = this.request.getParameter("showDivId");
		//描述
		String des = this.request.getParameter("imgdes");
		String type = this.request.getParameter("imgType");
		List<String> arrs = this.getFileFileName();
		System.out.println("----------------------------------------------->>>"+fileFileName+file);
		MtMaterial mal = new MtMaterial();
		int i =0;
		for (String fileName : fileFileName) {
			mal.setId(UUIDCreater.getUUID());
			mal.setCreateDate(DateUtil.getNowDate());
			mal.setCreateId(user.getId());
			mal.setCreateName(user.getName());
			mal.setMmbId(user.getMmbId());
			mal.setMaterialName(fileName);
			mal.setDivId(divId);
			mal.setType(Integer.parseInt(type));
			//状态 0启用
			mal.setMaterialStatus(0);
			
			//判断类型
			materialService.addPic(file.get(i), mal);
			i++;
		}
		return null;
	}
	//上传图片
	@RequestMapping("/addMal")
	@ResponseBody
	public String uploadMal(){
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		String divId = this.request.getParameter("showDivId");
		//描述
		String des = this.request.getParameter("imgdes");
		String type = this.request.getParameter("imgType");
		List<String> arrs = this.getFileFileName();
		
		MtMaterial mal = new MtMaterial();
		mal.setCreateDate(DateUtil.getNowDate());
		mal.setCreateId(user.getId());
		mal.setCreateName(user.getName());
		mal.setMmbId(user.getMmbId());
		mal.setDivId(divId);
		mal.setType(Integer.parseInt(type));
		//状态 0启用
		mal.setMaterialStatus(0);
		String path = request.getSession().getServletContext().getRealPath("/");
		materialService.uploadMal(file, fileFileName, mal, path);
		return null;
	}
	String ftp_dir = "/image/";
	String ftp_dir_temp = "/image/temp/";
	@RequestMapping("/uploadMal")
	@ResponseBody
	public String uploadMal1(){
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		String divId = this.request.getParameter("showDivId");
		//描述
		String des = this.request.getParameter("imgdes");
		String type = this.request.getParameter("imgType");
		
		MtMaterial mal = new MtMaterial();
		mal.setCreateDate(DateUtil.getNowDate());
		mal.setCreateId(user.getId());
		mal.setCreateName(user.getName());
		mal.setMmbId(user.getMmbId());
		mal.setDivId(divId);
		mal.setType(Integer.parseInt(type));
		//状态 0启用
		mal.setMaterialStatus(0);
		//上传临时文件目录
		String path = RepConstants.getUploadimgpath(mal.getType());
		for (int i = 0; i < file.size(); i++) {
			List<String> arrs = this.getFileFileName();
			for (String fileName : arrs) {
				//创建上传图片名称
				String newfileName=ImageUtils.createNewPictureName(fileName,mal.getId());
				
			}
		}
		return null;
	}
	//通过malId  type 获取图片的缩略图路径或路径   type=0 缩略图  type=1 预览
	@RequestMapping("/getPath")
	@ResponseBody
	public String getPath(Model model,
			@RequestParam(value = "malId", required = false) String malId,
			@RequestParam(value = "type", required = false) Integer type){
		//通过Id查找对象
		MtMaterial mal = materialService.selectById(malId);
		String path="";
		if(type==0){
			path= mal.getPicPath();
		}
		if(type==1){
			path = mal.getMaterialPath();
		}
		return path;
	}
	
	//swfupload
	@RequestMapping("/swfupload1")
	@ResponseBody
	public String swfupload(){
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		String divId = this.request.getParameter("showDivId");
		//描述
		//String des = this.request.getParameter("imgdes");
		String type = this.request.getParameter("imgType");
		
		MtMaterial mal = new MtMaterial();
		mal.setCreateDate(DateUtil.getNowDate());
		mal.setCreateId(user.getId());
		mal.setCreateName(user.getName());
		mal.setMmbId(user.getMmbId());
		mal.setDivId(divId);
		//mal.setType(Integer.parseInt(type));
		mal.setType(1);
		//状态 0启用
		mal.setMaterialStatus(0);
		
		//上传临时文件目录
		String path = RepConstants.getUploadimgpath(mal.getType());
		//获取项目路径
		String basepath =request.getSession().getServletContext().getRealPath("/");
		basepath = basepath.substring(0,basepath.length()-1);
		basepath = basepath.replaceAll("\\\\", "/");
		// 设置临时文件存储位置
		String base = basepath+path;
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000);
		
		File file = new File(base);
		if(!file.exists())
			file.mkdirs();
		factory.setRepository(file);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		upload.setFileSizeMax(10002400000l);
		// 设置整个request的最大值
		upload.setSizeMax(10002400000l);
		upload.setHeaderEncoding("UTF-8");
		String mm =request.getSession().getServletContext().getRealPath("/");
		System.out.println("==================="+mm);
		try {
			List<?> items = upload.parseRequest(request);
			System.out.println(items.toString());
			FileItem item = null;
			String fileName = null;
			String name = null;
			String malPath = null;
			for (int i = 0 ;i < items.size(); i++){
				item = (FileItem) items.get(i);
				// 保存文件
				if (!item.isFormField() && item.getName().length() > 0) {
					//获取上传的文件名称
					 name =  item.getName().substring(0, item.getName().lastIndexOf("."));
					mal.setMaterialName(name);
					mal.setId(UUIDCreater.getUUID());
					fileName = base + File.separator + mal.getId()+item.getName();
					item.write(new File(fileName));
					malPath = path+File.separator + mal.getId()+item.getName();
					malPath = malPath.replaceAll("\\\\", "/");
					mal.setMaterialPath(malPath);
					mal.setPicPath(malPath);
					materialService.createPic(mal);
					System.out.println("==============="+fileName);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/swfupload")
	@ResponseBody
	public String swfupload1(){
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		String divId = this.request.getParameter("showDivId");
		//描述
		String des = this.request.getParameter("imgdes");
		String type = this.request.getParameter("imgType");
		MtMaterial mal = new MtMaterial();
		mal.setCreateDate(DateUtil.getNowDate());
		mal.setCreateId(user.getId());
		mal.setCreateName(user.getName());
		mal.setMmbId(user.getMmbId());
		mal.setDivId(divId);
		if(!StringUtils.isBlank(des)){
			mal.setMaterialSize(des);
		}
		mal.setType(Integer.parseInt(type));
		//mal.setType(1);
		//状态 0启用
		mal.setMaterialStatus(0);
		//获取项目路径
		String basepath = request.getSession().getServletContext().getRealPath("/");
		System.out.println(basepath);
		materialService.createMal(mal, basepath, request);
		return null;
		
		
	}
	//获取轮播图路径的集合
	@RequestMapping("/showCarousel")
	@ResponseBody
	public Map<String, Object>  showCarousel(){
		String imageIds = this.request.getParameter("imageIds");
		return materialService.showCariusel(imageIds);
	}
	//删除图片
	@RequestMapping("/delMal")
	@ResponseBody
	public String delMal(Model model,
			@RequestParam(value = "malId", required = false) String malId){
		//查询出信息
		MtMaterial ma = materialService.selectById(malId);
		String base = request.getRealPath("");
		System.out.println(request.getRealPath(""));
		boolean flag = materialService.delPic(base, ma);
		String data = "1";
		if(flag){
			data ="0";
		}
		return data;
	}
	
}
