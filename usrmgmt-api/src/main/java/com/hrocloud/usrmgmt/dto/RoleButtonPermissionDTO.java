package com.hrocloud.usrmgmt.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.http.client.utils.DateUtils;

import com.hrocloud.apigw.client.annoation.Description;
import com.hrocloud.common.api.model.BaseObj;
import com.hrocloud.usrmgmt.model.RoleButtonPermission;
/**
 * Created by tony.chen on 2017/1/19
 */

@Description("节点按钮")
public class RoleButtonPermissionDTO implements Serializable{
	
	private static final long serialVersionUID = 7638321264714289954L;
	@Description("节点按钮id")
	public int id;
	@Description("角色id")
    public int roleId;
	@Description("节点id")
    public int nodeId;
	@Description("按钮id")
    public int buttonId;
	public RoleButtonPermissionDTO(RoleButtonPermission rbp) {
		this.id = rbp.getId();
		this.roleId = rbp.getRoleId();
		this.nodeId = rbp.getNodeId();
		this.buttonId = rbp.getButtonId();
	}
	
	
	
	
	
}