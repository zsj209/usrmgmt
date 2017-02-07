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
@ApiGroup(name = "node",codeDefine =UserServiceHttpCode.class ,minCode = 1000, maxCode = 1999)
public interface NodeAgwService {
	//TODO　错误信息
	@HttpApi(name = "node.getmenu", desc = "获取用户菜单", security = SecurityType.UserLogin)
	List<Menu> getMenu( @ApiAutowired(CommonParameter.userId) int userId);
	
	
	/**
	 * 根据条件节点信息
	 * @return
	 */
	//TODO　错误信息
	@HttpApi(name = "node.nodepage", desc = "节点信息分页", security = SecurityType.UserLogin)
	PageDTO nodePage(
			@ApiParameter(required = false, name = "nodeName", desc = "节点名称") String nodeName,
			@ApiParameter(required = false, name = "type", desc = "节点类型") String type,
			@ApiParameter(required = false, name = "flag", desc = "是否只要二级节点") String flag,
			@ApiParameter(required = true, name = "rows", desc = "每页显示条数") int rows,
			@ApiParameter(required = true, name = "page", desc = "当前页") int page);
	
	/**
	 * 删除节点信息
	 * @param idList 删除的节点id数组
 	 * @return
	 */
	@HttpApi(name = "node.deletenode", desc = "删除节点信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_NODE_DELINFO_ERROR})
	boolean deleteNode(@ApiParameter(required = true, name = "ids", desc = "删除的节点id数组") String ids);
	
	/**
	 * 添加节点信息
	 * @param data 节点信息的json字符串
	 * @return
	 */
	@HttpApi(name = "node.addORmodifynode", desc = "添加或修改节点信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_NODE_ADDINFO_ERROR,UserServiceHttpCode.C_NODE_MODIFYINFO_ERROR})
	boolean addOrModifyNode(@ApiAutowired(CommonParameter.userId) int userId,
			@ApiParameter(required = true, name = "data", desc = "json字符串") String data);
	/**
	 * 根据id获取节点信息
	 * @param data 节点信息的json字符串
	 * @return 
	 */
	@HttpApi(name = "node.getnodebyid", desc = "根据id获取节点信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_NODE_NONEXISTENT_ERROR})
	NodeDTO getNodeById(
			@ApiParameter(required = true, name = "id", desc = "节点id") int id);
	/**
	 * 获取节点树
	 * @param data 节点信息的json字符串
	 * @return 
	 */
	@HttpApi(name = "node.getnodetree", desc = "获取节点树", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_ROLE_NODE_IS_NULL})
	List<NodeTreeDTO> getNodeTree(
			@ApiParameter(required = true, name = "roleId", desc = "角色id") int roleId);

	
	
	
	
	
}
