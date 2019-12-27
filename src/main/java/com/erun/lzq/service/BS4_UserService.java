package com.erun.lzq.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erun.lzq.bean.BS4_User;
import com.erun.lzq.dao.BS4_UserMapper;

@Service
public class BS4_UserService {

	@Autowired
	private BS4_UserMapper bS4_UserMapper;
	
	public List<BS4_User> QueryByCondition( BS4_User bs4_User) {
		return bS4_UserMapper.QueryByCondition(bs4_User);
	}
	
	public List <BS4_User> QueryByLikeCondition(BS4_User bs4_User){
		return bS4_UserMapper.QueryByLikeCondition(bs4_User) ;
	}

	public List <BS4_User> QueryByDateCondition(BS4_User bs4_User,Date beginDate , Date endDate ){
		return bS4_UserMapper.QueryByDateCondition(bs4_User, beginDate, endDate);
	}
	
	public boolean Insert(BS4_User bs4_User) {
			return bS4_UserMapper.Insert(bs4_User);
	}
	
	public boolean Delete(int BillNo) {
		return bS4_UserMapper.Delete(BillNo);
	}
	
	//按主键更新
	public boolean Update(BS4_User bs4_User) {
		return bS4_UserMapper.Update(bs4_User);
	}
	
}
