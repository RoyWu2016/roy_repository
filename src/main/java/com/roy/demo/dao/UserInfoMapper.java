package com.roy.demo.dao;

import java.util.List;
import java.util.Map;

import com.roy.demo.model.UserInfo;

public interface UserInfoMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    int adderWithParameterMap(Map parms);
    
    List<UserInfo> selectAll();

}
