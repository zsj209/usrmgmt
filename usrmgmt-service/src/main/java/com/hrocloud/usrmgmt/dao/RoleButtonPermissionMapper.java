package com.hrocloud.usrmgmt.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrocloud.common.dao.HroBaseMapper;
import com.hrocloud.usrmgmt.dto.RoleButtonPermissionDTO;
import com.hrocloud.usrmgmt.model.RoleButtonPermission;

public interface RoleButtonPermissionMapper extends HroBaseMapper<RoleButtonPermission> {

	List<RoleButtonPermission> getRoleButton(@Param("roleId")int roleId,@Param("nodeId") int nodeId);

	int addOrModifyRoleNodeButton(@Param("addListRoleButton")ArrayList<RoleButtonPermission> addListRoleButton);

	int delRoleNodeButton(@Param("delArr")List<String> delArr, @Param("roleId")int roleId, @Param("nodeId")int nodeId);


}