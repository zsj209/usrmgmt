/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrocloud.common.dao.HroBaseMapper;

import org.apache.ibatis.annotations.Param;

import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.dto.UserDTO;
import com.hrocloud.usrmgmt.model.UserInfo;

/**
 * Created by Stanley Zou on 2016/12/28
 */
public interface UserInfoMapper extends HroBaseMapper<UserInfo> {
    UserInfo selectByPrimaryKey(Integer id);

    /**
     * Get the UserInfo object by the given loginName, mobileNumber or emailAddress
     *
     * @param userCode
     * @return
     */
    UserInfo getUser(String userCode);
    
//	List<UserInfo> getAllUserPage(@Param("nameOrMobile")String nameOrMobile ,@Param("status")String status, @Param("page") PageParameter pageInfo);
	List<UserInfo> getAllUserPage(@Param("hashMap") HashMap<String, String> hashMap, @Param("page") PageParameter pageInfo);

    List<UserInfo> queryUser(@Param("expr") String expr);

	int deleteUser(ArrayList<String> list);

	int modifyStatus(@Param("list")ArrayList<String> list,@Param("status") String status);

	int modifyFinalRoleType(@Param("userId")int userId,@Param("roleType") String roleType);
}