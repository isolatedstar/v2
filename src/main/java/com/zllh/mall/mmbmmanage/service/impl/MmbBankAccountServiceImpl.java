package com.zllh.mall.mmbmmanage.service.impl;

import com.zllh.mall.common.dao.MtMmbBankAccountMapper;
import com.zllh.mall.common.model.MtMmbBankAccount;
import com.zllh.mall.mmbmmanage.service.IMmbBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cfy on 2016/7/5.
 */

@Service
public class MmbBankAccountServiceImpl implements IMmbBankAccountService{

    @Autowired
    private MtMmbBankAccountMapper mtMmbBankAccountMapper;


    //查询会员银行账号（收付款账号）
    @Override
    public List<MtMmbBankAccount> getMmbBankAccount(Map param) {

        List<MtMmbBankAccount> mmbBankAccountList = new ArrayList<>();
        mmbBankAccountList = mtMmbBankAccountMapper.getMmbBankAccount(param);

        return mmbBankAccountList;
    }

    @Override
    public List<MtMmbBankAccount> getMmbBankAccountByMmbId(String mmbId) {

        return mtMmbBankAccountMapper.getMmbBankAccountByMmbId(mmbId);
    }

    //新增会员银行账号（收付款账号）
    @Override
    public Map<String, Object> createMmbBankAccount(MtMmbBankAccount mmbBankAccount) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        int result = mtMmbBankAccountMapper.insert(mmbBankAccount);

        if(result > 0){
            resultMap.put("successMsg","创建会员银行账号（收付款账号）成功!");
        }else{
            resultMap.put("errorMsg","创建会员银行账号（收付款账号）失败!");
        }
        return resultMap;
    }

    @Override
    public int getNumByAccountNo(String accountno,String mmbId) {
        return mtMmbBankAccountMapper.getNumByAccountNo(accountno,mmbId);
    }

    @Override
    public MtMmbBankAccount getMmbBankAccountById(String id) {
        return mtMmbBankAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public MtMmbBankAccount getBankAccountByAccountNo(String accountno) {
        return     mtMmbBankAccountMapper.getBankAccountByAccountNo(accountno);
    }

    //删除会员银行账号（收付款账号）
    @Override
    public Map<String, Object> deleteMmbBankAccountById(String id) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        int result = mtMmbBankAccountMapper.deleteByPrimaryKey(id);
        if(result > 0){
            resultMap.put("successMsg","删除会员银行账号（收付款账号）成功!");
        }else{
            resultMap.put("errorMsg","删除会员银行账号（收付款账号）失败!");
        }

        return resultMap;
    }

    //修改会员银行账号（收付款账号）
    @Override
    public Map<String, Object> updateMmbBankAccountById(MtMmbBankAccount mmbBankAccount) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        int result = mtMmbBankAccountMapper.updateByPrimaryKey(mmbBankAccount);

        if(result > 0){
            resultMap.put("successMsg","修改会员银行账号（收付款账号）成功!");
        }else{
            resultMap.put("errorMsg","修改会员银行账号（收付款账号）失败!");
        }

        return resultMap;
    }


}
