/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darren on 2017/01/25.
 */
@Description("用户权限信息")
public class UserPermissionDTO implements Serializable {

	private static final long serialVersionUID = -147044014088136187L;

	@Description("用户ID")
	public int userId;

	@Description("用户ID")
	public List<UserPermissionDetailDTO> permDlist;

	public UserPermissionDTO() {
		permDlist = new ArrayList<UserPermissionDetailDTO>();
	}

}
