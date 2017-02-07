package com.hrocloud.usrmgmt.api;

import java.util.List;

import com.hrocloud.apigw.client.annoation.ApiAutowired;
import com.hrocloud.apigw.client.annoation.ApiGroup;
import com.hrocloud.apigw.client.annoation.ApiParameter;
import com.hrocloud.apigw.client.annoation.DesignedErrorCode;
import com.hrocloud.apigw.client.annoation.HttpApi;
import com.hrocloud.apigw.client.define.CommonParameter;
import com.hrocloud.apigw.client.define.SecurityType;
import com.hrocloud.usrmgmt.dto.RoleButtonPermissionDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;

/**
 * 
 * Created by Tony.chen on 2016年12月9日.
 */
@ApiGroup(name = "rolebutton",codeDefine =UserServiceHttpCode.class ,minCode = 1000, maxCode = 1999)
public interface RoleButtonAgwService {
	/**
	 * 添加角色节点按钮信息
	 * @param userId
	 * @param roleId
	 * @param data
	 * @return
	 */
	@HttpApi(name = "rolebutton.addORmodifyrolenodebutton", desc = "添加或修改角色节点信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_ROLE_BUTTON_ADDINFO_ERROR,
		UserServiceHttpCode.C_ROLE_BUTTON_MODIFYINFO_ERROR})
	boolean addOrModifyRoleNodeButton(@ApiAutowired(CommonParameter.userId) int userId,
			@ApiParameter(required = true, name = "roleId", desc = "角色id") int roleId,
			@ApiParameter(required = true, name = "nodeId", desc = "节点id") int nodeId,
			@ApiParameter(required = true, name = "data", desc = "角色所属节点按钮字符串") String data);
	/**
	 * 根据角色获得节点按钮信息
	 * @param roleId
	 * @param nodeId
	 * @return
	 */
	@HttpApi(name = "rolebutton.getrolebutton", desc = "根据角色获得节点按钮信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({
		UserServiceHttpCode.C_ROLE_BUTTON_IS_NULL})
	List<RoleButtonPermissionDTO> getRoleButton(
			@ApiParameter(required = true, name = "roleId", desc = "角色id") int roleId,
			@ApiParameter(required = true, name = "nodeId", desc = "节点id") int nodeId);


	
	
	
	
	
	
	
	
	
	
}
