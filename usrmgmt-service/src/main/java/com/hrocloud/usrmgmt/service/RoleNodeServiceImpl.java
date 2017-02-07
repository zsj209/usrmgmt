package com.hrocloud.usrmgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrocloud.usrmgmt.api.RoleNodeService;
import com.hrocloud.usrmgmt.dao.RoleNodePermissionMapper;
import com.hrocloud.usrmgmt.model.RoleNodePermission;
@Service("roleNodeService")
public class RoleNodeServiceImpl implements RoleNodeService {
	@Autowired
	RoleNodePermissionMapper rnpm;

	public int addOrModifyRoleNode(ArrayList<RoleNodePermission> listRoleNode) {
		int addRoleNode = rnpm.addRoleNode(listRoleNode);
		return addRoleNode;
	}

	public List<RoleNodePermission> getRoleNodeById(int roleId) {
		List<RoleNodePermission> nodeList = rnpm.getRoleNodeById(roleId);
		return nodeList;
	}

	public int delRoleNode(List<String> delArr, int roleId) {
		int delResult = rnpm.delRoleNode(delArr,roleId);
		return delResult;
	}
	
	
	

}
