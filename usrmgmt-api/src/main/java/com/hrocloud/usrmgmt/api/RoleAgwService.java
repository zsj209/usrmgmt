/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.api;

import com.hrocloud.apigw.client.annoation.*;
import com.hrocloud.apigw.client.define.CommonParameter;
import com.hrocloud.apigw.client.define.SecurityType;
import com.hrocloud.usrmgmt.dto.*;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;

import java.util.List;

/**
 * Created by Stanley Zou on 2016/12/28
 */
@ApiGroup(name = "role", minCode = 1000, maxCode = 1999, codeDefine = UserServiceHttpCode.class, owner = "Stanley")
public interface RoleAgwService {





    /**
     * 根据角色类型获取角色列表
     * @param data
     * @param rows
     * @param page
     * @return
     */
    //TODO:更新错误码
    @HttpApi(name = "role.rolepage", desc = "根据角色类型获取角色列表", security = SecurityType.UserLogin)
    PageDTO getRoleList(
    		//@ApiParameter(required = false, name = "data", desc = "查询条件") String data,
    		@ApiParameter(required = false, name = "roleName", desc = "角色名称") String roleName,
    		@ApiParameter(required = false, name = "enabled", desc = "是否有效") String enabled,
            @ApiParameter(required = true, name = "rows", desc = "每页显示条数") int rows,
            @ApiParameter(required = true, name = "page", desc = "当前页") int page);

    /**
     * 增加或修改角色信息
     * @param userId
     * @param data
     * @return
     */
    @HttpApi(name = "role.addOrModifyRole", desc = "新增或修改角色", security = SecurityType.UserLogin)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_MODIFYINFO_ERROR,
            UserServiceHttpCode.C_USER_ADDINFO_ERROR})
    boolean addOrModifyRole(@ApiAutowired(CommonParameter.userId) int userId,
            @ApiParameter(required = true, name = "data", desc = "json字符串") String data);
    /**
     * 更具角色id获取角色信息
     * @param id
     * @param purpose
     * @return
     */
    @HttpApi(name = "role.getrolebyid", desc = "根据角色id获取角色信息", security = SecurityType.UserLogin)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_NOT_FOUND})
    RoleDTO getRoleById(
    		@ApiParameter(required = true, name = "id", desc = "json字符串") int id);
    /**
     * 根据角色id批量删除角色
     * @param ids
     * @return
     */
    @HttpApi(name = "role.deleterole", desc = "根据id删除角色", security = SecurityType.UserLogin)
    boolean deleteRole(
    		@ApiParameter(required = true, name = "ids", desc = "角色id数组") String ids);
    /**
     * 根据角色id批量改变角色状态
     * @param ids
     * @param status
     * @return
     */
    @HttpApi(name = "role.modifyenabled", desc = "根据id改变角色状态", security = SecurityType.UserLogin)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_NOT_FOUND})
    boolean modifyEnabled(
    		@ApiParameter(required = true, name = "ids", desc = "角色id数组") String ids,
    		@ApiParameter(required = true, name = "status", desc = "状态") String status);


}
