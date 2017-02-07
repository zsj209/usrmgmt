package com.hrocloud.usrmgmt.api;

import java.util.List;

import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.model.NodeInfo;

/**
 * Created by tony
 */

public interface NodeService {
	
	public List<Menu> getMenu(int userId);
	/**
	 * 根据角色列表获取角色的菜单信息
	 * @param roleIds
	 * @return
	 */
	public List<Menu> getMenuByRoleIds(List<Integer> roleIds);

	public PageDTO nodePage(NodeInfo nodeInfo, String  flag, int rows, int page);

	public boolean deleteNode(String ids);

	public boolean addOrModifyNode(int userId,String data);

	public NodeDTO getNodeById(int id);

	public List<NodeInfo> getNodeTree(int roleId);


}
