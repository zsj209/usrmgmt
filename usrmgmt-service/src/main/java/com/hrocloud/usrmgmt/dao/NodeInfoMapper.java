package com.hrocloud.usrmgmt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hrocloud.common.dao.HroBaseMapper;

import org.apache.ibatis.annotations.Param;

import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.model.NodeInfo;
import com.hrocloud.usrmgmt.model.NodeInfoAll;

public interface NodeInfoMapper extends HroBaseMapper<NodeInfo> {

    /**
	 * 获取指定角色下的节点信息
	 * 
	 * @param roleId 角色id
	 * @return
	 */
	List<NodeInfo> quaryAllNodeInfo(int roleId);
	
	List<NodeInfo> quaryAllNodeInfos(@Param("list")List<Integer> list);

	List<NodeInfoAll> nodePage(@Param("nodeInfo") NodeInfo nodeInfo,@Param("flag") String flag,@Param("page") PageParameter pageInfo);

	int deleteNode(ArrayList<String> list);

	NodeInfoAll getNodeById(int id);

	List<NodeInfo> getNodeTree(@Param("roleId")int roleId);

}