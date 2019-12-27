package com.erun.lzq.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.erun.lzq.bean.BS4_User;

public interface BS4_UserMapper {
	
	//按条件查询
	List <BS4_User> QueryByCondition(@Param("condition")BS4_User bs4_User);
	
	//模糊查询
	List <BS4_User> QueryByLikeCondition(@Param("condition")BS4_User bs4_User);
	
	//按时间查询
	List <BS4_User> QueryByDateCondition(@Param("condition")  BS4_User bs4_User,@Param("beginDate") Date beginDate, @Param("endDate")  Date endDate);
	
	//新增
	Boolean Insert(@Param("record") BS4_User bs4_User);
	
	//删除
	Boolean Delete(int BillNo);
	
	//修改按主键
	Boolean Update(@Param("record")BS4_User bs4_User);
	
	//修改条件
	int UpdateWihtCondition(@Param("record") BS4_User bs4_User,@Param("condition")BS4_User bs4_UserCondition);
}