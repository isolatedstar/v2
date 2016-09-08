package com.zllh.payment.server.dao;
import java.util.List;

import com.zllh.payment.model.RegularView;

public interface RegularViewMapper {
    int insert(RegularView record);

    int insertSelective(RegularView record);
    //根据银行账号查找相关银行信息
    public List<RegularView> findBankByAcctRule(String accountNo);
    //
    public String findInterfaceByBankId(String bankId);
    
}