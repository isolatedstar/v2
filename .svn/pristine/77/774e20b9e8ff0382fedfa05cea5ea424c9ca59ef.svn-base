package com.zllh.mall.mmbmmanage.controller;

import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtMmbBankAccount;
import com.zllh.mall.mmbmmanage.service.IMmbBankAccountService;
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
 * Created by CFY on 2016/7/5.
 */

@Controller
@RequestMapping("/mmbBankAccount")
public class MmbBankAccountController extends BaseController{

    @Resource
    private IMmbBankAccountService mmbBankAccountService;



    /**
     * 查询 会员银行账号（收付款账号）
     * @return
     */
    @RequestMapping("/getMmbBankAccount")
    public String getMmbBankAccount(){

        return "mall/mmbBank/mmb_bankaccount";
    }

    @RequestMapping("/queryMmbBankAccount")
    @ResponseBody
    public Map<String,Object> queryMmbBankAccount(String accountname2){

        UserExtendBean userExtendBean = (UserExtendBean) session.getAttribute("user");//从session中取得当前登录的操作员对象
        String mmbId = userExtendBean.getMuser().getMmbId(); //根据操作员信息获取当前操作员的所属会员id

        Map<String,Object> paramMap = new HashMap();

        paramMap.put("mmbId",mmbId);
        paramMap.put("accountname2",accountname2);

        int total =  mmbBankAccountService.getMmbBankAccount(paramMap).size();

        // 分页数据初始化
        int pageNo = getPageNo();
        int pageSize = getPageSize();

        // 接收页面参数并传递给service
        paramMap.put("startFirst", pageNo);//当前页开始行数
        paramMap.put("startEnd", pageSize);

        List<MtMmbBankAccount>  mmbBankAccountList = mmbBankAccountService.getMmbBankAccount(paramMap);

        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap.put("rows", mmbBankAccountList);
        resultMap.put("total",total);

        return resultMap;
    }

    /**
     * 新增会员银行账号（收付款账号）
     * @param accountno  账户号码
     * @param accountname    账户全称
     * @param bankname   银行名称
     * @param contact   联系人名称
     * @param mobilephone   手机号码
     * @param phone     电话号码
     * @return map
     */
    @RequestMapping("/createMmbBankAccount")
    @ResponseBody
    public Map<String,Object> createMmbBankAccount(
                 @RequestParam(value = "accountno", required = false) String accountno,
                 @RequestParam(value = "accountname", required = false) String accountname,
                 @RequestParam(value = "bankname", required = false) String bankname,
                 @RequestParam(value = "contact", required = false) String contact,
                 @RequestParam(value = "mobilephone", required = false) String mobilephone,
                 @RequestParam(value = "phone", required = false) String phone ){

        Map<String,Object> resultMap = new HashMap<String,Object>();

        // uuid生成会员id
        String uuid = UUIDCreater.getUUID();

        UserExtendBean userExtendBean = (UserExtendBean) session.getAttribute("user");//从session中取得当前登录的操作员对象
        String mmbId = userExtendBean.getMuser().getMmbId(); //根据操作员信息获取当前操作员的所属会员id

        MtMmbBankAccount mtMmbBankAccount = new MtMmbBankAccount();

        mtMmbBankAccount.setId(uuid);
        mtMmbBankAccount.setMmbId(mmbId);
        mtMmbBankAccount.setAccountno(accountno);
        mtMmbBankAccount.setAccountname(accountname);
        mtMmbBankAccount.setBankname(bankname);
        mtMmbBankAccount.setContact(contact);
        mtMmbBankAccount.setMobilephone(mobilephone);
        mtMmbBankAccount.setPhone(phone);

        resultMap = mmbBankAccountService.createMmbBankAccount(mtMmbBankAccount);
        return resultMap;
    }


    @RequestMapping("/toEditMmbBankAccount")
    @ResponseBody
    public MtMmbBankAccount toEditMmbBankAccount(@RequestParam(value = "id",required = false) String id){

        MtMmbBankAccount MtMmbBankAccount = mmbBankAccountService.getMmbBankAccountById(id);

        return MtMmbBankAccount;
    }

    /**
     * 修改会员银行账号（收付款账号）
     * @param accountno  账户号码
     * @param accountname    账户全称
     * @param bankname   银行名称
     * @param contact   联系人名称
     * @param mobilephone   手机号码
     * @param phone     电话号码
     * @return map
     */
    @RequestMapping("/updateBankAccount")
    @ResponseBody
    public Map<String,Object> updateBankAccount(
            @RequestParam(value = "id1", required = false) String id,
            @RequestParam(value = "mmbId1", required = false) String mmbId,
            @RequestParam(value = "accountno1", required = false) String accountno,
            @RequestParam(value = "accountname1", required = false) String accountname,
            @RequestParam(value = "bankname1", required = false) String bankname,
            @RequestParam(value = "contact1", required = false) String contact,
            @RequestParam(value = "mobilephone1", required = false) String mobilephone,
            @RequestParam(value = "phone1", required = false) String phone ){

        Map<String,Object> resultMap = new HashMap<String,Object>();


        MtMmbBankAccount mtMmbBankAccount = new MtMmbBankAccount();
        mtMmbBankAccount.setId(id);
        mtMmbBankAccount.setMmbId(mmbId);
        mtMmbBankAccount.setAccountno(accountno);
        mtMmbBankAccount.setAccountname(accountname);
        mtMmbBankAccount.setBankname(bankname);
        mtMmbBankAccount.setContact(contact);
        mtMmbBankAccount.setMobilephone(mobilephone);
        mtMmbBankAccount.setPhone(phone);;

        resultMap = mmbBankAccountService.updateMmbBankAccountById(mtMmbBankAccount);
        return resultMap;
    }


    /**
     * 删除会员银行账号（收付款账号）
     * @param id
     * @return
     */
    @RequestMapping("/deleteMmbBankAccount")
    @ResponseBody
    public Map<String,Object> deleteMmbBankAccount(String id){

        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap = mmbBankAccountService.deleteMmbBankAccountById(id);

        return resultMap;
    }

    /**
     * 验证银行账号唯一性
     * @param accountno
     * @return
     */
    @RequestMapping("/checkAccountNo")
    @ResponseBody
    public int checkAccountNo(String accountno,String id){

        int num =   mmbBankAccountService.getNumByAccountNo(accountno,id) ;
        return num;
    }

}
