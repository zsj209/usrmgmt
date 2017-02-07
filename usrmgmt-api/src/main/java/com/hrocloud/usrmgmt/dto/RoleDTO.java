/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;
import com.hrocloud.usrmgmt.model.RoleInfo;
import com.hrocloud.usrmgmt.model.UserInfo;
import com.hrocloud.util.DateFormatStr;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zou_s on 2016/12/29.
 */
@Description("角色信息")
public class RoleDTO implements Serializable {
	public RoleDTO() {

	}

	private static final long serialVersionUID = -1554040389789365075L;
	@Description("用户id")
	public int id;
	@Description("创建者")
	public int createBy;
	@Description("更新者")
	public int updateBy;
	@Description("创建时间")
	public String createTime;
	@Description("更新时间")
	public String updateTime;
	@Description("终止时间")
	public String terminationTime;
	@Description("所属公司id")
	public int companyId;
	@Description("所属公司名称")
	public String companyName;
	@Description("角色名称")
	public String roleName;
	@Description("是否有效")
	public String enabled;
	@Description("是否有效描述")
	public String enabledDesc;
	@Description("角色类型")
	public String type;
	@Description("角色类型的描述")
	public String typeDesc;
	@Description("备注")
	public String memo;

	public RoleDTO(RoleInfo roleInfo) {
		this.id = roleInfo.getId();
		this.createBy = roleInfo.getCreateBy();
		this.updateBy = roleInfo.getUpdateBy();
		this.createTime = roleInfo.getCreateTime() != null ? DateUtils.formatDate(roleInfo.getCreateTime(),DateFormatStr. date_sdf):"";
		this.updateTime = roleInfo.getUpdateTime() != null ?  DateUtils.formatDate(roleInfo.getUpdateTime(),DateFormatStr. date_sdf):"";
		this.terminationTime = roleInfo.getTerminationTime() != null ? DateUtils.formatDate(roleInfo.getTerminationTime(),DateFormatStr. date_sdf):"";
		this.companyId = roleInfo.getCompanyId();
		this.roleName = roleInfo.getRoleName();
		this.enabled = roleInfo.getEnabled();
		this.type = roleInfo.getType();
		this.memo = roleInfo.getMemo();
	}
	
    
    
    

}
