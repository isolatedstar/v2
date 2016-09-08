package com.zllh.mall.material.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtMaterialMapper;
import com.zllh.mall.common.model.MtMaterial;
import com.zllh.mall.material.service.MaterialService;
import com.zllh.utils.common.UUIDCreater;
import com.zllh.utils.pic.ImageUtils;
import com.zllh.utils.pic.RepConstants;
import com.zllh.utils.pic.UploadFileUtils;
import com.zllh.utils.pic.VideoUtils;
@Service
public class MaterialServiceImp implements MaterialService{
	@Autowired
	private MtMaterialMapper  matericalMapper;
	
	@Override
	public boolean addPic(File file,MtMaterial mmt) {
		
		String basedir=RepConstants.baseDir;
		//type 1 图片
		String path = RepConstants.getUploadimgpath(mmt.getType());  
		String minpath = "";
		//生成新的文件名称
		String newfileName=ImageUtils.createNewPictureName(mmt.getMaterialName(),mmt.getId());
		//图片上传的具体地址
		path=path+"/"+mmt.getDivId()+"/"+ImageUtils.gethash(newfileName);
		minpath=path+"/"+"min";
		//上传文件到服务器的特定目录下
		try {
			UploadFileUtils.upload4CopyFile(newfileName, basedir+path, file);
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String imgurl=path+"/"+newfileName;
		/*****************************************************第三步根据文件类别进行处理************************************************/
		//如果是图片资源，按照特定的比例压缩图片并上传到服务器的特定目录下
		if(mmt.getType()==RepConstants.filePIC){
    		ImageUtils.Tosmallerpic(basedir+path+"/"+newfileName,basedir+minpath+newfileName,RepConstants.ratio,RepConstants.per);
        }
		//图片上传位置
		mmt.setMaterialPath(imgurl);
		//压缩图片位置
		mmt.setPicPath(minpath+newfileName);
		boolean flag = false;
		int num = matericalMapper.insertSelective(mmt);
		if(num>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delPic(String base,MtMaterial ma) {
		
		String malPath = base+ma.getMaterialPath();
		malPath = malPath.replaceAll("\\\\", "/");
		System.out.println(malPath);
		File file=new File(malPath);
        file.delete();
        //删除缩略图
        String minPath =  base+ma.getPicPath();
        minPath = minPath.replaceAll("\\\\", "/");
        File file1=new File(minPath);
        file1.delete();
        boolean flag = false;
        int num = matericalMapper.delById(ma.getId());
        if(num>0){
			flag = true;
		}
		return flag ;
	}

	@Override
	public List<MtMaterial> showPic(Map<String, Object> map) {
		List<MtMaterial> list = null;
		list = matericalMapper.showByDivId(map);
		return list;
	}

	@Override
	public boolean updatePic(MtMaterial mmt) {
		boolean flag = false;
		int num = matericalMapper.updateSelective(mmt);
		if(num>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public Long countShowPic(Map<String, Object> map) {
		Long num = matericalMapper.countShowPic(map);
		return num;
	}
	@Override
	public String uploadMal(List<File> file,List<String> fileFileName,MtMaterial mal,String basedir){
		
		String path = RepConstants.getUploadimgpath(mal.getType());
		String minpath = "";
		for (int i = 0; i < file.size(); i++) {
			List<String> arrs = fileFileName;
			for (String fileName : arrs) {
				/*****************************************************第一步生成新的文件名称************************************************/
				//生成新的文件名称
				String newfileName=ImageUtils.createNewPictureName(fileName,mal.getDivId());
				//图片上传的具体地址
				path=path+"/"+mal.getDivId()+"/"+ImageUtils.gethash(newfileName);
				mal.setMaterialName(fileName);
				mal.setMaterialPath(path);
				minpath=path+"/"+"min";
				/*****************************************************第二步将文件上传到服务器上************************************************/
				//上传文件到服务器的特定目录下
				try {
					UploadFileUtils.upload4CopyFile(newfileName, basedir+path, file.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String imgurl=path+"/"+newfileName;
				//如果是图片资源，按照特定的比例压缩图片并上传到服务器的特定目录下
				if(mal.getType()==1){
		    		ImageUtils.Tosmallerpic(basedir+path+"/"+newfileName,basedir+minpath+"/min_"+newfileName,RepConstants.ratio,RepConstants.per);
		        }
				//如果是视频资源需要进行截图
				if(mal.getType()==2){
					
					String svapaht=basedir+minpath+"/min_nnnn.jpg";
					// 判断输出的文件夹路径是否存在，不存在则创建
					File svafile = new File(svapaht);
					if (!svafile.getParentFile().exists()) {
						svafile.getParentFile().mkdirs();
					}
					VideoUtils.processImg(basedir+path+"/"+newfileName,RepConstants.ffmpegexePath,svapaht);
				 }
				mal.setId(UUIDCreater.getUUID());
				matericalMapper.insertSelective(mal);
			}
			
		}
		return null;
		
	}
	public boolean uploadFile(String ftp_dir_temp,String ftp_dir,String myFileName,File myFile,String tempDir , String pictureType, String fileNewName){
		
		
		
		
		
		
		return false;
		
	}

	@Override
	public MtMaterial selectById(String id) {
		
		return matericalMapper.showById(id);
	}

	@Override
	public boolean createPic(MtMaterial mmt) {
		matericalMapper.insertSelective(mmt);
		return false;
	}

	@Override
	public boolean createMal(MtMaterial mal, String basepath,
			HttpServletRequest request) {
		//上传临时文件目录
		String path = RepConstants.getUploadimgpath(mal.getType());
		//获取项目路径
				
		basepath = basepath.substring(0,basepath.length()-1);
		basepath = basepath.replaceAll("\\\\", "/");
		// 设置临时文件存储位置
		String mp = "/"+RepConstants.getStringRandom(2);
		String base = basepath+path+mp;
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
					mal.setMaterialName(mal.getMaterialSize());
					mal.setId(UUIDCreater.getUUID());
					//获取两位随机字母
					String tt = item.getName().substring(item.getName().lastIndexOf("."));
					System.out.println("*&*8**********************====="+tt);
					fileName = base + File.separator + mal.getId()+tt;
					item.write(new File(fileName));
					malPath = path+mp+File.separator + mal.getId()+tt;
					malPath = malPath.replaceAll("\\\\", "/");
					mal.setMaterialPath(malPath);
					mal.setPicPath(malPath);
					if(mal.getType()==1){
						//生成缩略图
						fileName = base + File.separator + mal.getId()+tt;
						String  minfileName = base+"/"+"min" + File.separator + mal.getId()+tt;
						ImageUtils.Tosmallerpic(fileName,minfileName,RepConstants.ratio,RepConstants.per);
						malPath = path+mp+"/"+"min" +File.separator + mal.getId()+tt;
						malPath = malPath.replaceAll("\\\\", "/");
						mal.setPicPath(malPath);
					}
					createPic(mal);
					System.out.println("==============="+fileName);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public Map<String, Object> showCariusel(String imageIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isBlank(imageIds)){
			List<String> imgList = java.util.Arrays.asList(imageIds.split(","));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("imgList", imgList);
			List<String> path = matericalMapper.showCariusel(params);
			if(path!=null&&path.size()>0){
				map.put("path",path);
			}else{
				map.put("error", "获取轮播图失败！");
			}
			
		}else{
			map.put("error", "获取轮播图失败！");
		}
		
		return map;
	}

	@Override
	public List<String> searchMalByGood(String goodId) {
		
		return matericalMapper.searchByGood(goodId);
	}

	@Override
	public List<MtMaterial> searchMalByGoodId(String goodId) {
		
		return matericalMapper.searchByGoodId(goodId);
	}
}
