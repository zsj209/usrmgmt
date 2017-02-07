package com.hrocloud.usrmgmt.api;

import java.util.List;

import com.hrocloud.apigw.client.annoation.ApiGroup;
import com.hrocloud.apigw.client.annoation.ApiParameter;
import com.hrocloud.apigw.client.annoation.HttpApi;
import com.hrocloud.apigw.client.define.SecurityType;
import com.hrocloud.usrmgmt.dto.ParamValueDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;

@ApiGroup(name = "usrutils", minCode = 1000, maxCode = 1999, codeDefine = UserServiceHttpCode.class, owner = "Tony")
public interface UserAgwUtils {

	 @HttpApi(name = "usrutils.getnodetype", desc = "获取节点类型下拉框", security = SecurityType.UserLogin)
	 List<ParamValueDTO> getNodeType(
			 @ApiParameter(required = true, name = "roleType", desc = "用户角色类型") String userType);
	 


}
