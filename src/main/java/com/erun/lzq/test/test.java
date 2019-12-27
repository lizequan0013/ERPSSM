package com.erun.lzq.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erun.lzq.bean.BS4_Prog;
import com.erun.lzq.bean.BS4_User;
import com.erun.lzq.bean.NavTree;
import com.erun.lzq.service.BS4_ProgService;
import com.erun.lzq.service.BS4_UserService;

@Controller
@RequestMapping("test")
public class test {
	
	@Autowired
	private BS4_UserService userService;
	
	@Autowired
	private BS4_ProgService progService;
	

	//当前控制器绑定数据类型格式
//	@InitBinder
//	 public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		dateFormat.setLenient(false);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//	}
	
	
	@RequestMapping("hello")
	public String hello() {
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("queryuser")
	public List queryuser() {
		
		BS4_User bs4_User = new BS4_User();
		bs4_User.setDeptcode("GMD");
		List<BS4_User> list = userService.QueryByCondition(bs4_User);
		
		for (BS4_User User : list) {
			System.out.println(User);
		}
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping("querylikeuser")
	public List querylikeuser() {
		
		BS4_User bs4_User = new BS4_User();
		bs4_User.setUsercode("fc");
		bs4_User.setDeptcode("GMD.05.03.01");
		List<BS4_User> list = userService.QueryByLikeCondition(bs4_User);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("queryuserbytime")
	public List queryuserbytime(BS4_User user , 
			@RequestParam(value = "beginDate", required = false, defaultValue = "1999-03-13")Date beginDate ,
			@RequestParam(value = "endDate", required = false, defaultValue = "1999-03-13")Date endDate) {
		
		List<BS4_User> list = userService.QueryByDateCondition(user, beginDate, endDate);
		return list;
	}
	
	@RequestMapping("insert")
	public List insert() {
		BS4_User user = new BS4_User();
		user.setUsercode("LZQ");
		user.setMobilephone("13667046057");
		user.setLastonlinetime(new Date());
		return null;
	}
	
	@ResponseBody
	@RequestMapping("getmenuwihttwo")
	public Map getmenuwihttwo() {
		Map navMap = new HashMap<String,List<NavTree>>();
		List<BS4_Prog> bs_GetProg = progService.BS_GetProg("master", "root");
		for (BS4_Prog bs4_Prog : bs_GetProg) {
			System.out.println("父节点"+bs4_Prog);
			//查询节点下级
			List<BS4_Prog> bs_GetProgSon = progService.BS_GetProg("master", bs4_Prog.getProgCode());
			if(!bs_GetProgSon.isEmpty()) {
				ArrayList<NavTree> arrayList = new ArrayList<NavTree>();
				for (BS4_Prog bs4_ProgSon : bs_GetProgSon) {
					if(null!=bs4_ProgSon.getJSPFile()) {
						NavTree navTree = new NavTree();
						System.out.println(bs4_ProgSon);
						navTree.setText(bs4_ProgSon.getProgName());
						navTree.addAttributes("url", "javascript:void(0)");
						navTree.addAttributes("iframe", "iframe=\"0\"");
						navTree.addAttributes("data-link", bs4_ProgSon.getJSPFile());
						System.out.println("子节点查询"+navTree);
						System.out.println("子节点树"+navTree);
						arrayList.add(navTree);
					}
				}
				System.out.println();
				navMap.put(bs4_Prog.getProgName(), arrayList);
			}
		}
		return navMap;
	}
	
	@ResponseBody
	@RequestMapping("getmenu")
	public Map getmenu() {
		Map navMap = new HashMap<String,List<NavTree>>();
		List<BS4_Prog> bs_GetProg = progService.BS_GetProg("master", "root");
		for (BS4_Prog bs4_Prog : bs_GetProg) {
			navMap.put(bs4_Prog.getProgName(), progService.BS_GetProgList("master", bs4_Prog));
		}
		return navMap;
	}
	
}
