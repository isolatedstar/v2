package com.zllh.mall.material.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zllh.mall.common.model.MtMaterial;

public interface MaterialService {
	
	//创建图片
	public boolean addPic(File file,MtMaterial mmt);
	//删除图片
	public boolean delPic(String base,MtMaterial ma);
	//修改图片
	public boolean updatePic(MtMaterial mmt);
	//显示该目录下的图片
	public List<MtMaterial> showPic(Map<String, Object> map);
	//查询该目录下的图片数量
	Long countShowPic(Map<String, Object> map);
	public String uploadMal(List<File> file,List<String> fileFileName,MtMaterial mal,String basedir);
	//通过Id查询图片对象
	public MtMaterial selectById(String id);
	//创建图片
	public boolean createPic(MtMaterial mmt);
	//创建图片
	public boolean createMal(MtMaterial mal,String basepath,HttpServletRequest request);
	//轮播图
	public Map<String, Object> showCariusel(String imageIds);
	//通过商品Id 查询出相关联的图片的集合
	public List<String> searchMalByGood(String goodId);
	//通过商品Id 查询出相关联的图片的集合
	public List<MtMaterial> searchMalByGoodId(String goodId);
	
}
