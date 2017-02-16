package com.hrocloud.usrmgmt.api;

import java.util.ArrayList;
import java.util.List;

import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.model.NodeInfo;
import com.hrocloud.usrmgmt.model.RoleNodePermission;

/**
 * Created by tony
 */

public interface RoleNodeService {

	int addOrModifyRoleNode(ArrayList<RoleNodePermission> listRoleNode);

	List<RoleNodePermission> getRoleNodeById(int roleId);

	int delRoleNode(List<String> delArr, int roleId);

	int getCountByNodeIds(String ids);


}
