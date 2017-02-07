package com.hrocloud.usrmgmt.api;

import java.util.ArrayList;
import java.util.List;

import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.dto.RoleButtonPermissionDTO;
import com.hrocloud.usrmgmt.model.NodeInfo;
import com.hrocloud.usrmgmt.model.RoleButtonPermission;
import com.hrocloud.usrmgmt.model.RoleNodePermission;

/**
 * Created by tony
 */

public interface RoleButtonService {

	List<RoleButtonPermission> getRoleButton(int roleId, int nodeId);

	int addOrModifyRoleNodeButton(ArrayList<RoleButtonPermission> addListRoleButton);

	int delRoleNodeButton(List<String> delArr, int roleId, int nodeId);




}
