package com.hrocloud.usrmgmt.api;

import java.util.List;

import com.hrocloud.apigw.client.annoation.ApiAutowired;
import com.hrocloud.apigw.client.annoation.ApiGroup;
import com.hrocloud.apigw.client.annoation.ApiParameter;
import com.hrocloud.apigw.client.annoation.DesignedErrorCode;
import com.hrocloud.apigw.client.annoation.HttpApi;
import com.hrocloud.apigw.client.define.CommonParameter;
import com.hrocloud.apigw.client.define.SecurityType;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.dto.NodeTreeDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;

/**
 * 
 * Created by Tony.chen on 2016年12月9日.
 */
@ApiGroup(name = "rolenode",codeDefine =UserServiceHttpCode.class ,minCode = 1000, maxCode = 1999)
public interface RoleNodeAgwService {

	/**
	 * 删除角色节点信息
	 * @param idList 删除的角色节点id数组
 	 * @return
	 */
	@HttpApi(name = "rolenode.deleterolenode", desc = "删除角色节点信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_NODE_DELINFO_ERROR})
	boolean deleteNode(@ApiParameter(required = true, name = "ids", desc = "删除的角色节点id数组") String ids);
	
	/**
	 * 添加角色节点信息
	 * @param userId
	 * @param roleId
	 * @param data
	 * @return
	 */
	@HttpApi(name = "rolenode.addORmodifyrolenode", desc = "添加或修改角色节点信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_NODE_ADDINFO_ERROR,UserServiceHttpCode.C_NODE_MODIFYINFO_ERROR})
	boolean addOrModifyRoleNode(@ApiAutowired(CommonParameter.userId) int userId,
			@ApiParameter(required = true, name = "roleId", desc = "角色id") int roleId,
			@ApiParameter(required = true, name = "data", desc = "角色所属节点的json字符串") String data);
	/**
	 * 根据id获取角色节点信息
	 * @param data 角色节点信息的json字符串
	 * @return 
	 */
	@HttpApi(name = "rolenode.getrolenodebyid", desc = "根据id获取角色节点信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_NODE_NONEXISTENT_ERROR})
	NodeDTO getNodeById(
			@ApiParameter(required = true, name = "id", desc = "角色节点id") int id);


	
	
	
	
	
	
	
	
	
	
}
