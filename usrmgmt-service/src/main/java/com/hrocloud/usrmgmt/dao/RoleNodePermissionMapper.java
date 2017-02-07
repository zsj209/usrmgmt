package com.hrocloud.usrmgmt.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrocloud.common.dao.HroBaseMapper;
import com.hrocloud.usrmgmt.model.RoleNodePermission;

public interface RoleNodePermissionMapper extends HroBaseMapper<RoleNodePermission> {

	int addRoleNode(@Param("listRoleNode") ArrayList<RoleNodePermission> listRoleNode);

	List<RoleNodePermission> getRoleNodeById(@Param("roleId")int roleId);

	int delRoleNode(@Param("delArr")List<String> delArr, @Param("roleId")int roleId);

}