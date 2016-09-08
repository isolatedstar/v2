package com.zllh.common.authority.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.common.authority.service.UserService;
import com.zllh.common.common.dao.PubUserMapper;
import com.zllh.common.common.model.PubUser;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.factoring.common.dao.FacMessageStatusMapper;
import com.zllh.factoring.common.dao.FacTenderMapper;
import com.zllh.factoring.common.model.FacMessageStatus;
import com.zllh.factoring.common.model.FacTender;
import com.zllh.payment.front.dao.FreezeMapper;
import com.zllh.payment.model.Freeze;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PubUserMapper userMapper;
	@Autowired
	private FacMessageStatusMapper messageStatusMapper;
	@Autowired
	private FacTenderMapper  tenderMapper;
	@Autowired
	private FreezeMapper freezeMapper;
	
	@Override
	public UserExtendBean findByUserName(String username) {
		
		return userMapper.findByUserName(username);
	}

	@Override
	public UserExtendBean getUserById(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserExtendBean> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserExtendBean> getAll2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserExtendBean> getAll3() {
		// TODO Auto-generated method stub
		return null;
	}

	public PubUser getByName(String username){
		PubUser user = userMapper.selectByName(username);
		return user;
	}

	@Override
	public void executeTest(HttpServletRequest request) {
		
		saveUser();
		createFreeze();
	}
	
	@Override
	public void test(HttpServletRequest request) {
		saveUser();
		createFreeze();
	}
	
	public void saveUser(){
		
		PubUser user = new PubUser();
		user.setUserId("aaaaaaaaaaaa");
		user.setUserName("测试");
		userMapper.insertSelective(user);
	}
	
	public void createMessageStatus(){
		FacMessageStatus mess = new FacMessageStatus();
		mess.setId("aaaaaaaaaaaa");
		mess.setMessage("测试");
		messageStatusMapper.insertSelective(mess);
	}
	
	public void tender(){
		FacTender ten = new FacTender();
		ten.setId("aaaaaaaaaaa");
		ten.setMinInterestRate(BigDecimal.ZERO);
		tenderMapper.insertSelective(ten);
	}
	
	public void createFreeze(){
		createMessageStatus();
		tender();
		PubUser user = userMapper.selectByPrimaryKey("aaaaaaaaaaaa");
		System.err.println(user.getUserId());
		Freeze  free = new Freeze();
		free.setBankAcct("aaaaaaaa");
		free.setBalance(BigDecimal.ZERO);
		free.setLockAmt(BigDecimal.ZERO);
		freezeMapper.insertSelective(free);
	}
	
}
