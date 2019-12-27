package com.erun.lzq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erun.lzq.bean.BS4_Prog;
import com.erun.lzq.bean.NavTree;
import com.erun.lzq.service.BS4_ProgService;

@Controller
@RequestMapping("navtree")
public class NavTreeController {
	
	@Autowired
	private BS4_ProgService progService;
	
	@ResponseBody
	@RequestMapping(value="/getmenu" , method = RequestMethod.GET)
	public Map<String, NavTree> getmenu(HttpSession session) {
		
		String userCode = (String) session.getAttribute("UserCode");
		Map<String, NavTree> navMap = new HashMap<String,NavTree>();
		List<BS4_Prog> bs_GetProg = progService.BS_GetProg(userCode, "root");
		for (BS4_Prog bs4_Prog : bs_GetProg) {
			navMap.put(bs4_Prog.getProgName(), progService.BS_GetProgTree("master", bs4_Prog));
		}
		
		
//		Map navMap = new HashMap<String,List<NavTree>>();
//		List<BS4_Prog> bs_GetProg = progService.BS_GetProg(userCode, "root");
//		for (BS4_Prog bs4_Prog : bs_GetProg) {
//			navMap.put(bs4_Prog.getProgName(), progService.BS_GetProgList("master", bs4_Prog));
//		}
		
		
		return navMap;
	}
	
}
