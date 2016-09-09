package com.zllh.mall.mmbmmanage.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

























import com.zllh.mall.common.dao.MtMaterialMapper;
import com.zllh.mall.common.dao.MtMemberMapper;
import com.zllh.mall.common.dao.MtMemberRelationshipMapper;
import com.zllh.mall.common.dao.MtMmbHomepageMapper;
import com.zllh.mall.common.dao.MtMmbNodeMapper;
import com.zllh.mall.common.dao.MtMmbPicMapper;
import com.zllh.mall.common.dao.MtMmbWebsiteMapper;
import com.zllh.mall.common.model.MtMaterial;
import com.zllh.mall.common.model.MtMember;
import com.zllh.mall.common.model.MtMemberRelationship;
import com.zllh.mall.common.model.MtMmbHomepage;
import com.zllh.mall.common.model.MtMmbNode;
import com.zllh.mall.common.model.MtMmbPic;
import com.zllh.mall.common.model.MtMmbWebsite;
import com.zllh.mall.mmbmmanage.service.IMmbWebsiteService;
import com.zllh.utils.base.CreateHtml;
import com.zllh.utils.common.AutoCreateDir;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.DictionaryUtil;
import com.zllh.utils.common.HtmlUtil;
import com.zllh.utils.common.UUIDCreater;
import com.zllh.utils.pic.UploadFileUtils;
import com.zllh.utils.twodimensionalcode.QRCodeUtil;
@Service
public class MmbWebSiteServiceImpl implements IMmbWebsiteService {
	@Autowired
	private MtMmbWebsiteMapper mmbWeb;
	@Autowired
	private MtMmbHomepageMapper homepage;
	@Autowired
	private MtMmbPicMapper pic;
	@Autowired
	private MtMaterialMapper mal;
	@Autowired
	private MtMemberRelationshipMapper  relammb;
	@Autowired
	private MtMemberMapper mmb;
	@Autowired
	private MtMmbNodeMapper node;

	@Override
	public boolean addIntroduce(MtMmbWebsite mb) {
		MtMmbWebsite web = new MtMmbWebsite();
		 web = mmbWeb.searchBymmbId(mb.getMmbId());
		boolean flag = false;
		if(web!=null){
			//修改
			int num = mmbWeb.updateBymmbId(mb);
			if(num>0)
				flag = true;
		}else{
			int num = mmbWeb.insertSelective(mb);
			if(num>0)
				flag =true;
		}
		return flag;
	}

	@Override
	public boolean addMal(String mmbId, String malId, Integer type,String remark) {
		// type  0 homepage    1  pic
		boolean flag = false;
		if(type==0){
			MtMmbHomepage hp = new MtMmbHomepage();
			hp.setId(UUIDCreater.getUUID());
			hp.setMmbId(mmbId);
			hp.setMalId(malId);
			hp.setTitleRemark(remark);
			hp.setPicType(0);
			int num = homepage.insertSelective(hp);
			if(num>0)
				flag = true;
		}else if(type==1){
			MtMmbPic pc = new MtMmbPic();
			pc.setId(UUIDCreater.getUUID());
			pc.setMalId(malId);
			pc.setMmbId(mmbId);
			pc.setPicRemark(remark);
			pc.setPicType(0);
			int num = pic.insertSelective(pc);
			if(num>0)
				flag = true;
		}else if(type==2){
			MtMmbPic pc = new MtMmbPic();
			pc.setId(UUIDCreater.getUUID());
			pc.setMalId(malId);
			pc.setMmbId(mmbId);
			pc.setPicRemark(remark);
			pc.setPicType(1);
			int num = pic.insertSelective(pc);
			if(num>0)
				flag = true;
		}
		return flag;
	}

	@Override
	public boolean delMal(String mmbId, String malId, Integer type,Integer picType) {
		// type  0 homepage    1  pic
		boolean flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("malId", malId);
		map.put("mmbId", mmbId);
		map.put("picType", picType);
		if(type==0){
			int num = homepage.delById(map);
			if(num>0)
				flag = true;
		}else if(type==1){
			int num = pic.delById(map);
			if(num>0)
				flag = true;
		}
		return flag;
	}

	@Override
	public List<MtMaterial> showPic(Map<String, Object> map, Integer type) {
		List<MtMaterial> list = null;
		if(type==0){
			list = mal.searchByHomePage(map);
		}
		if(type==1){
			list = mal.searchByPic(map);
		}
		return list;
	}

	@Override
	public MtMmbWebsite searchBymmbId(String mmbId) {
		
		return mmbWeb.searchBymmbId(mmbId);
	}

	@Override
	public boolean previewInfo(String mmbId, ServletContext servletContext,String type) {
		boolean flag = false;
		//查询信息
		MtMmbWebsite  web = mmbWeb.searchBymmbId(mmbId);
		if(web==null){
    		return flag;
    	}
		String baseDir=servletContext.getRealPath(File.separator);//项目的根目录
		System.out.println(baseDir);
    	String dateDir= AutoCreateDir.baseTypeFilePath;//项目中存放生成的html文件的目录
    	dateDir = dateDir.replaceAll("\\\\", "/");
    	String fileName=web.getMmbName()+".html";//生成的静态文件名称（包含html）
    	//生成模板的数据
		Map<String,Object> data=new HashMap<String,Object>();
		//会员名称
		data.put("mmbName",web.getMmbName());
		data.put("mmbId",mmbId);
		//绝对路径
		String basePath11 = (AutoCreateDir.basePath +AutoCreateDir. baseTypeFilePath).replaceAll("\\\\", "/");
		data.put("basePath",basePath11);
		//路径
		data.put("rootUrl",servletContext.getContextPath());
		System.out.println(servletContext.getContextPath());
		String mmbContent = HtmlUtil.replaceSpecialMark(web.getMmbIntroduce());
		//简介
		data.put("mmbIntroduce", mmbContent);
		//homePage
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("mmbId", mmbId);
		List<MtMaterial> list1 = mal.searchByHomePage(map);
		data.put("homepage",list1 );
		//pic证书
		List<MtMaterial> list2 = mal.searchByPic(map);
		data.put("pic",list2 );
		//生成的二维码相对路径
		String  imgPath = AutoCreateDir.baseImgPath;
		String basepath = servletContext.getRealPath(File.separator);
		//二维码存放的绝对路径
		String baseImgPath = (basepath+imgPath).replaceAll("\\\\", "/");
		//生成二维码
		/**
		 * id:生成的二维码名称
		 * url:生成的静态页面的绝对路径
		 * ""
		 * baseImgPath  二维码生成的绝对路径
		 * 
		 */
		String url = (AutoCreateDir.basePath+dateDir+ fileName).replaceAll("\\\\", "/");
		try {
			QRCodeUtil.encode(mmbId,url, "", baseImgPath, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String imgPath1 = ("\\"+imgPath+mmbId+".jpg").replaceAll("\\\\", "/");
		data.put("imgPath",imgPath1);
		/**
		 * data 模板所用参数
		 * servletContext 服务器参数
		 * information_preview.ftl  模板文件名称（包含ftl文件下目录）
		 * fileName  文件名（不含html）
		 * dateDir   项目中存放生成的html文件的目录
		 */
		CreateHtml.createHTML(data, servletContext,"mmb_view.ftl", web.getMmbName(),File.separator+dateDir.substring(0,dateDir.length()-1));//生成模板
		web.setMmbPath(File.separator+dateDir+ fileName);//设置预览url
		String ssssss = web.getMmbPath();
		int num = mmbWeb.updateSelective(web);
		//修改mmb表的字段
		MtMember mb = new MtMember();
		mb.setId(mmbId);
		mb.setMmbHomepage(url);
		mmb.updatePubMmb(mb);
		//信息html生成成功，信息可用，更新该信息所在信息包所属的所有节点的html
    	if(num>0){
    		
    		
    		flag = true;
    	}
		return flag;
	}

	@Override
	public String addRelaMMb(String mmbId, String relaMmbId) {
		
		String json = "";
		
		if(mmbId.equals(relaMmbId)){
			return "4";
		}
	
			
			MtMemberRelationship mmb = new MtMemberRelationship();
			mmb.setMmbId(mmbId);
			mmb.setRelaMmbId(relaMmbId);
			mmb.setRelaType(0);
			mmb.setCreateTime(DateUtil.getNowDate());
			mmb.setRelaStatus(0);
			List<MtMemberRelationship> list = relammb.queryMmbRelationshipsByCondition(mmb);
			if(list!=null&&list.size()>0){
				json = "2";
			}else{
				mmb.setId(UUIDCreater.getUUID());
				mmb.setRelaGrade(1);
				int num = relammb.insertSelective(mmb);
				if(num>0){
					json = "0";
				}else{
					json = "1";
				}
			}
			
	
		return json;
	}

	@Override
	public boolean deleteMMbHome(String mmbName,ServletContext servletContext) {
		String baseDir=servletContext.getRealPath(File.separator);//项目的根目录
		baseDir = baseDir.replaceAll("\\\\", "/");
		boolean flag = UploadFileUtils.deleteFile(mmbName,baseDir);
		
		return flag;
	}

	@Override
	public List<MtMmbNode> getMmbNodes(Map<String, Object> params) {
		 List<MtMmbNode> list = new ArrayList<MtMmbNode>();
		 list = node.getMmbNodes(params);
		return list;
	}

	@Override
	public boolean previewMmbPage(String mmbId, ServletContext servletContext,
			String nodeId) {
		boolean flag = false;
		//查询信息
		MtMmbWebsite  web = mmbWeb.searchBymmbId(mmbId);
		if(web==null){
    		return flag;
    	}
		String basepath=servletContext.getRealPath(File.separator);//项目的根目录
		String fileName=web.getMmbName()+".html";//生成的静态文件名称（包含html）
		String dateDir= AutoCreateDir.baseTypeFilePath;//项目中存放生成的html文件的目录
		dateDir = dateDir.replaceAll("\\\\", "/");
		//生成二维码
		//生成的二维码相对路径
		String  imgPath = AutoCreateDir.baseImgPath;
		//二维码存放的绝对路径
		String baseImgPath = (basepath+imgPath).replaceAll("\\\\", "/");
		//生成二维码
		/**
		 * id:生成的二维码名称
		 * url:生成的静态页面的绝对路径
		 * ""
		 * baseImgPath  二维码生成的绝对路径
		 * 
		 */
		String url = (AutoCreateDir.basePath+dateDir+ fileName).replaceAll("\\\\", "/");
		try {
			QRCodeUtil.encode(mmbId,url, "", baseImgPath, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ftl 模板路径
		String templatePath = (AutoCreateDir.ftl+nodeId+"\\").replaceAll("\\\\", "/");
		//生成模板的数据
		Map<String,Object> data=new HashMap<String,Object>();
		//第一个模板所需参数为
		
		
		//相对路径
		data.put("rootUrl",servletContext.getContextPath());
		//绝对路径
		String basePath11 = (AutoCreateDir.basePath +AutoCreateDir.baseTypeFilePath).replaceAll("\\\\", "/");
		data.put("templatePath",templatePath);
		//ftl模板路径
		data.put("basePath",basePath11);
		//生成的二维码存放路径
		String imgPath1 = ("\\"+imgPath+mmbId+".jpg").replaceAll("\\\\", "/");
		data.put("imgPath",imgPath1);
		//会员信息
		MtMember mmb1 = mmb.queryMmbById(mmbId);
		data.put("mmb",mmb1);
		//介绍
		String mmbContent = HtmlUtil.replaceSpecialMark(web.getMmbIntroduce());
		data.put("mmbIntroduce",mmbContent);
		//企业logo
		data.put("mmbLogo",web.getMmbLogo());
		//生成子页面
		String contextName =  web.getMmbId()+"condext";//生成子页面名称
		String contextPath = File.separator+dateDir+contextName+".html";//生成子页面路径
		String showPath = File.separator+dateDir+fileName;//生成主页面路径
		data.put("contextPath",contextPath);
		data.put("showPath",showPath);
		/**
		 * data 模板所用参数
		 * servletContext 服务器参数
		 * information_preview.ftl  模板文件名称（包含ftl文件下目录）
		 * fileName  文件名（不含html）
		 * dateDir   项目中存放生成的html文件的目录
		 */
		String putPath = File.separator+dateDir.substring(0,dateDir.length()-1);
		CreateHtml.addHTML(data, servletContext,"mmb_context.ftl", contextName,putPath,templatePath);//生成模板
		
		
		
		
		
		
		//摘要
    	String title = web.getMmbTitle();
    	data.put("title",title );
		//homePage
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("mmbId", mmbId);
		map.put("type",0);
		List<MtMaterial> list1 = mal.searchByHomePage(map);
		data.put("homepage",list1 );
		//pic证书
		List<MtMaterial> list2 = mal.searchByPic(map);
		data.put("pic",list2 );
		//good图片
		map.put("type",1);
		List<MtMaterial> list3 = mal.searchByPic(map);
		data.put("goodPic",list3);
		//生成主模板
		
		/**
		 * data 模板所用参数
		 * servletContext 服务器参数
		 * information_preview.ftl  模板文件名称（包含ftl文件下目录）
		 * fileName  文件名（不含html）
		 * dateDir   项目中存放生成的html文件的目录
		 */
		CreateHtml.addHTML(data, servletContext,"mmb_index.ftl", web.getMmbName(),File.separator+dateDir.substring(0,dateDir.length()-1),templatePath);//生成模板
		web.setMmbPath("/"+dateDir+fileName);//设置预览url
		int num = mmbWeb.updateSelective(web);
		//修改mmb表的字段
		MtMember mb = new MtMember();
		mb.setId(mmbId);
		mb.setMmbHomepage(url);
		mmb.updatePubMmb(mb);
		//信息html生成成功，信息可用，更新该信息所在信息包所属的所有节点的html
    	if(num>0){
    		flag = true;
    	}	
		return flag;
	}
	

}
