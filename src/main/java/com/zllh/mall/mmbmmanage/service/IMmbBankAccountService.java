package com.zllh.mall.mmbmmanage.service;

import com.zllh.mall.common.model.MtMmbBankAccount;

import java.util.List;
import java.util.Map;

/**
 * Created by cfy on 2016/7/5.
 */
public interface IMmbBankAccountService {


    /**
     * 根据会员ID得到会员银行账号
     * @param param 登陆会员ID 
     * @return
     */
    public  List<MtMmbBankAccount> getMmbBankAccount(Map param);

    /**
     * 根据会员ID得到会员银行账号
     * @param mmbId 会员ID
     * @return
     */
    public  List<MtMmbBankAccount> getMmbBankAccountByMmbId(String mmbId);

    /**
     * 根据会员银行账号ID得到会员银行账号
     * @param mmbId 登陆会员ID
     * @return
     */
    public  MtMmbBankAccount getMmbBankAccountById(String mmbId);

    public  MtMmbBankAccount getBankAccountByAccountNo(String accountno);

    /**
     * 根据主键 删除会员银行账号
     * @param id
     * @return
     */
    public Map<String,Object> deleteMmbBankAccountById(String id);

    /**
     * 修改会员银行账号
     * @param mmbBankAccount
     * @return
     */
    public Map<String,Object> updateMmbBankAccountById(MtMmbBankAccount mmbBankAccount);

    /**
     * 新增会员银行账号
     * @param mmbBankAccount
     * @return
     */
    public Map<String,Object> createMmbBankAccount(MtMmbBankAccount mmbBankAccount);

    /**
     * 验证银行账号唯一性
     * @param accountno
     * @return
     */
    public int getNumByAccountNo(String accountno,String id);


}
