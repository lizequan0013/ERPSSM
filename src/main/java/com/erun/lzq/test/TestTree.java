package com.erun.lzq.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.springframework.format.datetime.joda.LocalDateParser;

import com.erun.lzq.bean.NavTree;

public class TestTree {

	@Test
	public void Tree() {
		
		NavTree navTree = new NavTree();
		
		navTree.addAttributes("list", "mylist");;
		
		NavTree navTree2 = new NavTree();

		navTree2.addAttributes("list2", "mylist2");
		
		navTree.addChildren(navTree2);
		
		
		System.out.println(navTree);
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
		
		ArrayList<String> arrayList2 = new ArrayList<String>();
		
		String avc = "??????";
		arrayList2.add(avc);
		avc="123";
		
		
		for (String s : arrayList2) {
		System.out.println(s);
		}
		
		int[] list1 = new int[]{1,2,3};
		
		ArrayList<int[]> arrayList3 = new ArrayList<int[]>();
		
		arrayList3.add(list1);
		list1[1]=0;
		
		
		for (int[] s : arrayList3) {
			for (int i = 0; i < s.length; i++) {
				System.out.println(list1[i]);
			}
			
		}
		
		
	}
	
	@Test
	public void data() {
		Date date = new Date();
		System.out.println("当前时间" + date);
		System.out.println("当前年份" + date.getYear());
		System.out.println("当前time" + date.getTime());
		
		
		LocalDateTime ldate = LocalDateTime.now();
		
		System.out.println("当前时间" + ldate);
	}	
		
		
		
		
		
		
		
}
