package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.MtMmbBankAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MtMmbBankAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(MtMmbBankAccount record);

    int insertSelective(MtMmbBankAccount record);

    MtMmbBankAccount selectByPrimaryKey(String id);

    MtMmbBankAccount getBankAccountByAccountNo(String accountno);

    int updateByPrimaryKeySelective(MtMmbBankAccount record);

    int updateByPrimaryKey(MtMmbBankAccount record);

    List<MtMmbBankAccount> getMmbBankAccount(Map param);

    List<MtMmbBankAccount> getMmbBankAccountByMmbId(String mmbId);

    int getNumByAccountNo(@Param("accountno")String accountno,@Param("id")String id);
}