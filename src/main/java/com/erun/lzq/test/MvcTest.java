package com.erun.lzq.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.erun.lzq.bean.BS4_Prog;
import com.erun.lzq.bean.NavTree;
import com.erun.lzq.service.BS4_ProgService;


/**
 * 使用Spring测试模块提供的测试请求功能，测试curd请求的正确性
 * Spring4测试的时候，需要servlet3.0的支持
 * @author lfy
 * @param <E>
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:Spring/spring.xml",
		"classpath:Spring/springmvc-context.xml" })
public class MvcTest<E> {
	// 传入Springmvc的ioc
	@Autowired
	WebApplicationContext context;
	// 虚拟mvc请求，获取到处理结果。
	MockMvc mockMvc;

	@Autowired
	BS4_ProgService progService;
	
	@Before
	public void initMokcMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	
	@Test
	public void testPage() throws Exception {
		//模拟请求拿到返回值
		//MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "5")).andReturn();
		
		List<BS4_Prog> bs_GetProg = progService.BS_GetProg("master", "root");
		Map navMap = new HashMap<String,List<NavTree>>();
		NavTree navTree = new NavTree();
		for (BS4_Prog bs4_Prog : bs_GetProg) {
			System.out.println(bs4_Prog);
			//查询节点下级
			List<BS4_Prog> bs_GetProgSon = progService.BS_GetProg("master", bs4_Prog.getProgCode());
			if(!bs_GetProgSon.isEmpty()) {
				ArrayList<NavTree> arrayList = new ArrayList<NavTree>();
				for (BS4_Prog bs4_ProgSon : bs_GetProgSon) {
					System.out.print(bs4_ProgSon.toString() + "+");
					if(null!=bs4_ProgSon.getJSPFile()) {
						navTree.setText(bs4_ProgSon.getProgName());
						navTree.addAttributes("url", "javascript:void(0)");
						navTree.addAttributes("iframe", "iframe=\"0\"");
						navTree.addAttributes("data-link", bs4_ProgSon.getJSPFile());
						arrayList.add(navTree);
						navTree.clear();
					}
				}
				System.out.println();
				navMap.put(bs4_Prog.getProgName(), arrayList);
			}
		}
	}
	
	@Test
	public void testbsprog() throws Exception {
		//模拟请求拿到返回值
		//MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "5")).andReturn();
		
		List<BS4_Prog> bs_GetProg = progService.BS_GetProg("master", "root");
		for (BS4_Prog bs4_Prog : bs_GetProg) {
			System.out.println(bs4_Prog);
			List<BS4_Prog> bs_GetProgSon = progService.BS_GetProg("master", bs4_Prog.getProgCode());
			for (BS4_Prog bs4_ProgSon : bs_GetProgSon) {
				System.out.println(bs4_ProgSon);
			}
		}
	
	}
	
	@Test
	public void testlist() throws Exception {

		ArrayList<NavTree> arrayList = new ArrayList<NavTree>();
		NavTree navTree = new NavTree();
		navTree.addAttributes("url", "javascript:void(0)");
		arrayList.add(navTree);
		navTree.clear();
		
		for (NavTree navTree2 : arrayList) {
			System.out.println(navTree2);
		}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

	
