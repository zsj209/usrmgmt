/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.api;

import com.hrocloud.common.api.service.CommonService;
import com.hrocloud.common.exception.BusinessException;
import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.UserPermissionDTO;
import com.hrocloud.usrmgmt.model.UserInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zou_s on 2016/12/28.
 */
public interface UserService extends CommonService<UserInfo> {
    UserInfo getUser(String loginCode);

    UserInfo getUserById(Integer userId);

    List<UserInfo> getUserList(HashMap<String, String> dataMap, PageParameter pageInfo);

    boolean addOrModifyUser(String data) throws BusinessException;

    List<UserInfo> queryUser(String expr);

    boolean deleteUser(String ids);

    boolean modifyStatus(String ids, String status);

    UserPermissionDTO getUserPerm(int userId, String roletype) throws BusinessException;

	int modifyFinalRoleType(int userId, String roletype);
	
	List<Menu> getUserMenu(int userId, String roleType) throws BusinessException;
}
