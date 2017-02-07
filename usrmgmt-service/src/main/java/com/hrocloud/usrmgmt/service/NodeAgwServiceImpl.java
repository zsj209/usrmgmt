package com.hrocloud.usrmgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.common.dto.CommCityInvalidDTO;
import com.hrocloud.common.exception.CommonServiceHttpCode;
import com.hrocloud.common.model.CommCityInvalid;
import com.hrocloud.usrmgmt.api.NodeAgwService;
import com.hrocloud.usrmgmt.api.NodeService;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.dto.NodeTreeDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.NodeInfo;
@Service("nodeAgwServiceImpl")
public class NodeAgwServiceImpl implements NodeAgwService {

	
	@Autowired
	NodeService nodeServiceImpl;
	
	public List<Menu> getMenu(int userId) {
		List<Menu> menu = nodeServiceImpl.getMenu(userId);
		return menu;
	}

	public PageDTO nodePage(String nodeName, String type, String flag, int rows, int page) {
		NodeInfo nodeInfo = new NodeInfo();
		if(!StringUtils.isBlank(nodeName)){
			nodeInfo.setNodeName(nodeName.trim());
		}
		if(!StringUtils.isBlank(type)){
			nodeInfo.setType(type.trim());
		}
		PageDTO nodePage = nodeServiceImpl.nodePage(nodeInfo,flag,rows,page);
		return nodePage;
	}

	public boolean deleteNode(String ids) {
		boolean deleteNode = nodeServiceImpl.deleteNode(ids);
		return deleteNode;
	}

	public boolean addOrModifyNode(int userId,String data) {
		boolean node= nodeServiceImpl.addOrModifyNode(userId,data);
		return node;
	}

	public NodeDTO getNodeById(int id) {
		NodeDTO nodeById = nodeServiceImpl.getNodeById(id);
		return nodeById;
	}

	public List<NodeTreeDTO> getNodeTree(int roleId) {
		List<NodeInfo> list = nodeServiceImpl.getNodeTree(roleId);
		
		if (list != null && list.size() > 0) {
			List<NodeTreeDTO> listdto = new ArrayList<NodeTreeDTO>();
			for (int i = 0; i < list.size(); i++) {
				NodeInfo nodeInfo = list.get(i);
				NodeTreeDTO nodeTreeDTO = new NodeTreeDTO();
				nodeTreeDTO.id = nodeInfo.getId();
				nodeTreeDTO.nodeCode = nodeInfo.getNodeCode();
				nodeTreeDTO.nodeId = nodeInfo.getNodeId();
				nodeTreeDTO.nodeName = nodeInfo.getNodeName();
				nodeTreeDTO.nodePcode = nodeInfo.getNodePcode();
				nodeTreeDTO.parentId = nodeInfo.getParentId();
				nodeTreeDTO.setstyle = nodeInfo.getSetstyle();

				nodeTreeDTO.sublist = nodeInfo.getSublist();
				nodeTreeDTO.type = nodeInfo.getType();
				nodeTreeDTO.subvalidnums = nodeInfo.getSubvalidnums()+"";
				
				listdto.add(nodeTreeDTO);
			}
			return listdto;
		} else {
			 DubboExtProperty.setErrorCode(UserServiceHttpCode.ROLE_NODE_IS_NULL);
			return null;
		}
	}

}
