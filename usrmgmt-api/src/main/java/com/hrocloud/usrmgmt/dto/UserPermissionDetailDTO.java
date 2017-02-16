/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;

import java.io.Serializable;

/**
 * Created by darren on 2017/01/25.
 */
@Description("用户权限信息")
public class UserPermissionDetailDTO implements Serializable {

	private static final long serialVersionUID = 6695335727981551104L;

	@Description("公司ID")
	public int companyId;
	
	@Description("公司名称")
	public String companyName;

	@Description("岗位ID")
	public int positionId;

	@Description("角色ID")
	public int roleId;

	@Description("角色类型")
	public String roleType;
	
	@Description("角色类型描述")
	public String roleTypeDesc;
}
