package com.mashibin.spring.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.mashibin.spring.entity.Account;
import com.mashibin.spring.mapper.AccountExample;
import com.mashibin.spring.mapper.AccountMapper;

@Service
public class AccountService {
	
	@Autowired
	AccountMapper accountMapper;

	public Account findAccountByLoginNameAndPassword(String loginName, String password) {
		
		
		
		AccountExample example = new AccountExample();
		
		example.createCriteria()
		.andLoginNameEqualTo(loginName)
		.andPasswordEqualTo(password);
		
		List<Account> accounts = accountMapper.selectByExample(example );
		
		return accounts.size()==0?null:accounts.get(0);
	}


	public List<Account> getAccountListByPage(Integer pageNum, Integer pageSize) {
		
		System.out.println("service pageNum="+pageNum);
		System.out.println("service pageSize="+pageSize);
		PageHelper.startPage(pageNum, pageSize);
		AccountExample example = new AccountExample();
		return accountMapper.selectByExample(example);
	}


	public boolean save(Account account) {
		try {
			account.setPassword(MD5Encryption(account.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
		return accountMapper.insert(account)>0;
	}


	public String MD5Encryption(String password) throws NoSuchAlgorithmException
	{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] md5Arr = md5.digest(password.getBytes());
		return Base64.getEncoder().encodeToString(md5Arr);
	}

}
