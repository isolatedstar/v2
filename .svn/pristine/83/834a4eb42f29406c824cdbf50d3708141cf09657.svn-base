package com.zllh.mall.mmbmmanage.controller;

import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.BusAreaTree;
import com.zllh.mall.common.model.MtMmbWarehouse;
import com.zllh.mall.mmbmmanage.service.IMmbWarehouseService;
import com.zllh.mall.quote.service.AreaTreeService;
import com.zllh.utils.common.StringUtil;
import com.zllh.utils.common.UUIDCreater;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CFY on 2016/7/5
 */

@Controller
@RequestMapping("/mmbWarehouse")
public class MmbWareHouseController extends BaseController{

    @Resource
    private IMmbWarehouseService mmbWarehouseService;

    @Resource
    private AreaTreeService areaService;


    @RequestMapping("/getMmbWarehouse")
    public String getMmbWarehouse(){

        return "mall/mmbWare/mmb_warehouse";
    }

    @RequestMapping("/queryMmbWareHouse")
    @ResponseBody
    public Map<String,Object> queryMmbWareHouse(String address2){

        UserExtendBean userExtendBean = (UserExtendBean) session.getAttribute("user");//从session中取得当前登录的操作员对象
        String mmbId = userExtendBean.getMuser().getMmbId(); //根据操作员信息获取当前操作员的所属会员id

        Map<String,Object> paramMap = new HashMap();

        paramMap.put("mmbId",mmbId);
        paramMap.put("address",address2);

        int total =  mmbWarehouseService.getMmbWareHouse(paramMap).size();

        // 分页数据初始化
        int pageNo = getPageNo();
        int pageSize = getPageSize();

        // 接收页面参数并传递给service
        paramMap.put("startFirst", pageNo);
        paramMap.put("startEnd", pageSize);

        List<MtMmbWarehouse>  mmbWarehouseList = mmbWarehouseService.getMmbWareHouse(paramMap);

        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap.put("rows", mmbWarehouseList);
        resultMap.put("total",total);

        return resultMap;
    }

    /**
     * 新增会员仓库（收发货地址）
     * @param address  地址
     * @param areaId    所属地域
     * @param zipcode   邮编
     * @param contact   联系人名称
     * @param mobilephone   手机号码
     * @param phone     电话号码
     * @return map
     */
    @RequestMapping("/createMmbWarehouse")
    @ResponseBody
    public Map<String,Object> createMmbWarehouse(
                 @RequestParam(value = "address", required = false) String address,
                 @RequestParam(value = "areaId", required = false) String areaId,
                 @RequestParam(value = "zipcode", required = false) String zipcode,
                 @RequestParam(value = "contact", required = false) String contact,
                 @RequestParam(value = "mobilephone", required = false) String mobilephone,
                 @RequestParam(value = "phone", required = false) String phone ){

        Map<String,Object> resultMap = new HashMap<String,Object>();

        // uuid生成会员id
        String uuid = UUIDCreater.getUUID();

        UserExtendBean userExtendBean = (UserExtendBean) session.getAttribute("user");//从session中取得当前登录的操作员对象
        String mmbId = userExtendBean.getMuser().getMmbId(); //根据操作员信息获取当前操作员的所属会员id

        MtMmbWarehouse mtMmbWarehouse = new MtMmbWarehouse();
        mtMmbWarehouse.setId(uuid);
        mtMmbWarehouse.setMmbId(mmbId);
        mtMmbWarehouse.setAddress(address);
        mtMmbWarehouse.setAreaId(areaId);
        mtMmbWarehouse.setZipcode(zipcode);
        mtMmbWarehouse.setContact(contact);
        mtMmbWarehouse.setMobilephone(mobilephone);
        mtMmbWarehouse.setPhone(phone);

        resultMap = mmbWarehouseService.createMmbWare(mtMmbWarehouse);
        return resultMap;
    }


    @RequestMapping("/toEditMmbWarehouse")
    @ResponseBody
    public MtMmbWarehouse toEditMmbWarehouse(@RequestParam(value = "id",required = false) String id){

        MtMmbWarehouse mtMmbWarehouse = mmbWarehouseService.getMmbWareHouseById(id);

        return mtMmbWarehouse;
    }

    /**
     * 修改会员仓库（收发货地址）
     * @param address  地址
     * @param areaId    所属地域
     * @param zipcode   邮编
     * @param contact   联系人名称
     * @param mobilephone   手机号码
     * @param phone     电话号码
     * @return map
     */
    @RequestMapping("/updateMmbWarehouse")
    @ResponseBody
    public Map<String,Object> updateMmbWarehouse(
            @RequestParam(value = "id1", required = false) String id,
            @RequestParam(value = "mmbId1", required = false) String mmbId,
            @RequestParam(value = "address1", required = false) String address,
            @RequestParam(value = "areaId1", required = false) String areaId,
            @RequestParam(value = "zipcode1", required = false) String zipcode,
            @RequestParam(value = "contact1", required = false) String contact,
            @RequestParam(value = "mobilephone1", required = false) String mobilephone,
            @RequestParam(value = "phone1", required = false) String phone ){

        Map<String,Object> resultMap = new HashMap<String,Object>();


        MtMmbWarehouse mtMmbWarehouse = new MtMmbWarehouse();
        mtMmbWarehouse.setId(id);
        mtMmbWarehouse.setMmbId(mmbId);
        mtMmbWarehouse.setAddress(address);
        mtMmbWarehouse.setAreaId(areaId);
        mtMmbWarehouse.setZipcode(zipcode);
        mtMmbWarehouse.setContact(contact);
        mtMmbWarehouse.setMobilephone(mobilephone);
        mtMmbWarehouse.setPhone(phone);

        resultMap = mmbWarehouseService.updateMmbWareById(mtMmbWarehouse);
        return resultMap;
    }


    /**
     * 删除会员仓库
     * @param id
     * @return
     */
    @RequestMapping("/deleteMmbWarehouse")
    @ResponseBody
    public Map<String,Object> deleteMmbWarehouse(@RequestParam(value = "id",required = false) String id){

        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap = mmbWarehouseService.deleteMmbWareById(id);

        return resultMap;
    }

    /**
     * 本方法用来查询所有节点树结构数据
     * @return
     * @throws Exception
     */
    @RequestMapping("/getTreeModal")
    @ResponseBody
    public List<BusAreaTree> getTreeModal(){
        List<BusAreaTree> list = areaService.searchChina();
        return list;
    }

    /**
     * 验证地址唯一性
     * @param address
     * @return
     */
    @RequestMapping("/checkAddress")
    @ResponseBody
    public int checkAddress(String address,String id){

        UserExtendBean userExtendBean = (UserExtendBean) session.getAttribute("user");//从session中取得当前登录的操作员对象
        String mmbId = userExtendBean.getMuser().getMmbId(); //根据操作员信息获取当前操作员的所属会员id

        int num =  mmbWarehouseService.getNumByAddressAndId(address, mmbId,id);

        return num;
    }

}
