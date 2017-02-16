package com.hrocloud.usrmgmt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrocloud.usrmgmt.api.RoleButtonService;
import com.hrocloud.usrmgmt.dao.RoleButtonPermissionMapper;
import com.hrocloud.usrmgmt.dto.RoleButtonPermissionDTO;
import com.hrocloud.usrmgmt.model.RoleButtonPermission;
@Service("roleButtonService")
public class RoleButtonServiceImpl implements RoleButtonService {

	@Autowired
	RoleButtonPermissionMapper rbpm;
	
	public List<RoleButtonPermission> getRoleButton(int roleId, int nodeId) {

		 List<RoleButtonPermission> resultList = rbpm.getRoleButton(roleId,nodeId);

		return resultList;
	}

	public int addOrModifyRoleNodeButton(ArrayList<RoleButtonPermission> addListRoleButton) {
		return rbpm.addOrModifyRoleNodeButton(addListRoleButton);
	}

	public int delRoleNodeButton(List<String> delArr, int roleId, int nodeId) {
		return rbpm.delRoleNodeButton( delArr, roleId,nodeId);
	}

	public List<RoleButtonPermission> getRoleNodeButton(int roleId, int nodeId) {
		return rbpm.getRoleButton(roleId, nodeId);
	}

	public int getCountByButtonIds(String ids) {

		ArrayList<String> list = new ArrayList<String>();
		String idstr[] = ids.split(",");
		Collections.addAll(list, idstr);
		
		int count = rbpm.getCountByButtonIds(list);
		
		return count;
	}

}
