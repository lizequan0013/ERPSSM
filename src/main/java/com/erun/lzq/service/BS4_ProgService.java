package com.erun.lzq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erun.lzq.bean.BS4_Prog;
import com.erun.lzq.bean.NavTree;
import com.erun.lzq.dao.BS4_ProgMapper;

@Service
public class BS4_ProgService {
	
	@Autowired
	BS4_ProgMapper bS4_ProgMapper;
	
	public List<BS4_Prog> BS_GetProg(String userCode, String ProgParent) {
		
		return bS4_ProgMapper.BS_GetProg(userCode, ProgParent);
	}
	
	public List<NavTree> BS_GetProgList(String userCode,BS4_Prog bs4_Prog) {
		
		ArrayList<NavTree> progList = new ArrayList<NavTree>();
		
		List<BS4_Prog> list  = bS4_ProgMapper.BS_GetProg(userCode, bs4_Prog.getProgCode());
		for (BS4_Prog bs4_Progson : list) {
			if(bs4_Progson.getProgType().equals("M")) {
				progList.add(BS_GetProgTree(userCode,bs4_Progson));
			}else {
				if(null!=bs4_Progson.getJSPFile()) {
					NavTree navTree = new NavTree();
					navTree.setText(
							"<a href='javascript:void(0)'"+
							"data-link='pd/pd01_product_list.jsp'"+
							"iframe='0'>"+
							bs4_Progson.getProgName()+
							"</a>"
						);
					navTree.setStateClose();
					navTree.setChecked(false);
					progList.add(navTree);
				}
			}
		}
	
		return progList;
	}

	public NavTree BS_GetProgTree(String userCode , BS4_Prog bs4_Prog) {
		NavTree navTree = new NavTree();
		navTree.setText(bs4_Prog.getProgName());
		if(bs4_Prog.getProgType().equals("M")) {
			//存在下级
			List<BS4_Prog> proglist = BS_GetProg(userCode,bs4_Prog.getProgCode());
			//循环下级调用自身！
			for (BS4_Prog bs4_ProgSon : proglist) {
				navTree.addChildren(BS_GetProgTree(userCode,bs4_ProgSon));
			}
			
		}else{
			//不存在下级
			if(null!=bs4_Prog.getJSPFile()) {
				navTree.setText(
						"<a href='javascript:void(0)'"+
						"data-link='pd/pd01_product_list.jsp'"+
						"iframe='0'>"+
						bs4_Prog.getProgName()+
						"</a>"
					);
				navTree.setStateClose();
				navTree.setChecked(false);
			}
		}
		return navTree;
	}
	
	
	
	
	
	
	
	
	
	
	
}
