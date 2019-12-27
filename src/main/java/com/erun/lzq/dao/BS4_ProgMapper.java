package com.erun.lzq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.erun.lzq.bean.BS4_Prog;

public interface BS4_ProgMapper {
	
	List<BS4_Prog> BS_GetProg(@Param("userCode") String userCode,@Param("ProgParent") String ProgParent);
}
