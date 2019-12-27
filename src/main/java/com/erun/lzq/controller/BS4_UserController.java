package com.erun.lzq.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erun.lzq.bean.BS4_User;
import com.erun.lzq.bean.Msg;
import com.erun.lzq.service.BS4_UserService;
import com.erun.lzq.utils.AppSet;

@Controller
@RequestMapping("/User")
public class BS4_UserController {
	
	@Autowired
	private BS4_UserService userService;

	@ResponseBody
	@RequestMapping(value="/query" , method=RequestMethod.GET)
	public Msg Login(BS4_User bs4_User , HttpSession session) {
		
		bs4_User.setPassword(AppSet.MD5(bs4_User.getPassword()));
		List<BS4_User> list = userService.QueryByCondition(bs4_User);
		
		if(!list.isEmpty()) {
			session.setAttribute("UserCode", bs4_User.getUsercode());
			session.setAttribute("UserName", bs4_User.getUsername());
			return Msg.success();
		}else {
			return Msg.fail();
		}
		
	}
	
	@RequestMapping(value="LoginOut" , method=RequestMethod.GET)
	public String LoginOut(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
}
