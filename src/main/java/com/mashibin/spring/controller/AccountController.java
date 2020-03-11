package com.mashibin.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.mashibin.spring.RespStatus;
import com.mashibin.spring.entity.Account;
import com.mashibin.spring.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountSrv;
	
	@RequestMapping("/login")
	public String login()
	{
		return "account/login";
	}
	
	
	@RequestMapping("/validataAccount")
	@ResponseBody
	public String validataAccount(String loginName,String password,HttpServletRequest request)
	{
		System.out.println("loginName = "+loginName);
		System.out.println("password = "+password);

		Account account = accountSrv.findAccountByLoginNameAndPassword(loginName,password);
		
		if(null == account)
		{
			System.out.println("login failed");
			return "login failed";
		}else
		{
			request.getSession().setAttribute("account", account);
			System.out.println("login success");
			return "success";
		}
	}
	
	@RequestMapping("/logOut")
	public String logOut(HttpServletRequest request)
	{
		System.out.println("移除accont。。。。");
		request.getSession().removeAttribute("account");
		return "/index";
	}
	@RequestMapping("/list")
	public String pageList(@RequestParam(required=false,defaultValue="1") Integer pageNum, @RequestParam(required=false,defaultValue="5") Integer pageSize,Model map)
	{
		System.out.println("pageList");
		System.out.println(" page ");
		System.out.println("pageNum="+pageNum);
		System.out.println("pageSize="+pageSize);  
		PageInfo<Account> pageInfo = accountSrv.getAccountListByPage(pageNum,pageSize);
		map.addAttribute("page", pageInfo);
		return "account/list";
	}
	@RequestMapping("/register")
	public String register(Account account,Model map)
	{
		boolean registerFlg = accountSrv.save(account);
		map.addAttribute("registerFlg", registerFlg);
		return "/index";
	}
	@RequestMapping("/deleteById")
	@ResponseBody
	public RespStatus deleteById(int id)
	{
		
		//1.删除数据需要提示用户
		//2.软删除，statCd I／A
		RespStatus stat = accountSrv.deleteById(id);
		return stat;
	}
	
}
