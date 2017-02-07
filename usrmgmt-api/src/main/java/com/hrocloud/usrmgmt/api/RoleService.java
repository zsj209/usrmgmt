/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.api;

import com.hrocloud.common.page.PageParameter;
import com.hrocloud.common.api.service.CommonService;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.model.RoleInfo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zou_s on 2016/12/28.
 */
public interface RoleService extends CommonService<RoleInfo> {

    RoleInfo getRoleById(int roleId);
    
    List<RoleInfo> getRoleList(HashMap<String, String> dataMap,PageParameter pageInfo) ;

	boolean addOrModifyRole(int userId,String data);

	boolean deleteRole(String ids);

	boolean modifyEnabled(String ids, String status);



}
