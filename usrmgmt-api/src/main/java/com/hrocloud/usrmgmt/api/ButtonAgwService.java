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
import com.hrocloud.usrmgmt.dto.ButtonDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;

/**
 * 
 * Created by Tony.chen on 2016年12月9日.
 */
@ApiGroup(name = "button",codeDefine =UserServiceHttpCode.class ,minCode = 1000, maxCode = 1999)
public interface ButtonAgwService {
	
	/**
	 * 根据条件按钮信息
	 * @return
	 */
	//TODO　错误信息
	@HttpApi(name = "button.buttonpage", desc = "按钮信息分页", security = SecurityType.UserLogin)
	PageDTO buttonPage(
			@ApiParameter(required = false, name = "buttonName", desc = "按钮名称") String buttonName,
			@ApiParameter(required = true, name = "rows", desc = "每页显示条数") int rows,
			@ApiParameter(required = true, name = "page", desc = "当前页") int page);
	
	/**
	 * 删除按钮信息
	 * @param idList 删除的按钮id数组
 	 * @return
	 */
	@HttpApi(name = "button.deletebutton", desc = "删除按钮信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_BUTTON_ASSIGNED_ERROR,UserServiceHttpCode.C_BUTTON_DELINFO_ERROR})
	boolean deleteButton(@ApiParameter(required = true, name = "ids", desc = "删除的按钮id数组") String ids);
	
	/**
	 * 添加按钮信息
	 * @param data 按钮信息的json字符串
	 * @return
	 */
	@HttpApi(name = "button.addORmodifybutton", desc = "添加或修改按钮信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_NODE_ADDINFO_ERROR,UserServiceHttpCode.C_NODE_MODIFYINFO_ERROR})
	boolean addOrModifyButton(@ApiAutowired(CommonParameter.userId) int userId,
			@ApiParameter(required = true, name = "data", desc = "json字符串") String data);
	/**
	 * 根据id获取按钮信息
	 * @param data 按钮信息的json字符串
	 * @return 
	 */
	@HttpApi(name = "button.getbuttonbyid", desc = "根据id获取按钮信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_NODE_NONEXISTENT_ERROR})
	ButtonDTO getButtonById(
			@ApiParameter(required = true, name = "id", desc = "按钮id") int id);

	/**
	 * 根据用户角色的节点权限获取按钮信息
	 * @param nodeId
	 * @param roleId
	 * @return
	 */
	@HttpApi(name = "button.getbuttonbynodeid", desc = "根据用户角色的节点权限获取按钮信息", security = SecurityType.UserLogin)
	@DesignedErrorCode({UserServiceHttpCode.C_NODE_NONEXISTENT_ERROR})
	List<ButtonDTO> getButtonByNodeId(
			@ApiParameter(required = true, name = "nodeId", desc = "节点id") int nodeId,
			@ApiParameter(required = false, name = "roleId", desc = "角色id") String roleId);
	
	
}
