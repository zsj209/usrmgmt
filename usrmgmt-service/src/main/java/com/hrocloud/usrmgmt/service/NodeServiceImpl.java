package com.hrocloud.usrmgmt.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.common.api.CommParamInfoService;
import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.api.NodeService;
import com.hrocloud.usrmgmt.constant.ValueListDefine;
import com.hrocloud.usrmgmt.dao.NodeInfoMapper;
import com.hrocloud.usrmgmt.dao.RoleInfoMapper;
import com.hrocloud.usrmgmt.dao.RoleNodePermissionMapper;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.dto.SecondMenu;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.NodeInfo;
import com.hrocloud.usrmgmt.model.NodeInfoAll;
import com.hrocloud.usrmgmt.model.RoleInfo;
import com.hrocloud.usrmgmt.model.UserInfo;
import com.hrocloud.usrmgmt.utils.Utils;

@Service("nodeServiceImpl")
public class NodeServiceImpl implements NodeService {
	/*
	 * @Autowired UserInfoMapper userInfoMapper;
	 */
	@Autowired
	RoleInfoMapper roleInfoMapper;

	@Autowired
	NodeInfoMapper nodeInfoMapper;

	@Autowired
	RoleNodePermissionMapper nodePermissonMapper;
	
	@Resource
    private CommParamInfoService commParamInfoService;

/*	public List<Menu> getMenu(int userId) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		list.add(100);
		return getMenuByRoleIds(list);
		
	}*/
	public List<Menu> getMenu(int userId) {

		Menu menu = null;

		SecondMenu secondMenu = null;
		List<Menu> menuList = new ArrayList<Menu>();

		// UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		UserInfo userInfo = new UserInfo();
		if (null != userInfo) {
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(100);
			list.add(101);
			list.add(102);
			list.add(103);
			list.add(104);
			return getMenuByRoleIds(list);
			/*RoleInfo roleInfo = roleInfoMapper.selectByPrimaryKey(101);
			if (null != roleInfo) {
				//if (1 == roleInfo.getEnabled()) {
				if ("02valid".equals(roleInfo.getEnabled())) {
					// 获得角色对应的node
					List<NodeInfo> list = nodeInfoMapper.quaryAllNodeInfo(101);
					
					for (int i = 0; i < list.size(); i++) {
						// 获取一级节点
						menu = new Menu();
						NodeInfo nodeInfo = list.get(i);
						int id = nodeInfo.getId();
						if (nodeInfo.getParentId() == 0) {
							menu.setNodeName(nodeInfo.getNodeName());
							menu.setPictureCode(nodeInfo.getPictureCode() + "");
							menu.setMethodName(nodeInfo.getMethodName());
							menu.setNodeCode(nodeInfo.getNodeCode());
							menu.setId(nodeInfo.getId());
						} else {
							continue;
						}
						// 获取一级节点对应的二级节点
						for (int ii = 0; ii < list.size(); ii++) {
							NodeInfo dto2 = list.get(ii);
							if (dto2.getParentId() == id) {
								secondMenu = new SecondMenu();
								secondMenu.setNodeName(dto2.getNodeName());
								secondMenu.setPictureCode(dto2.getPictureCode() + "");
								secondMenu.setMethodName(dto2.getMethodName());
								secondMenu.setNodeCode(dto2.getNodeCode());
								secondMenu.setId(dto2.getId());
								List<SecondMenu> child = menu.getFirstChild();
								child.add(secondMenu);
							}
						}
						menuList.add(menu);
					}

				} else {
					// TODO 角色未启用
				}
			} else {
				// TODO 角色不存在
			}*/
		} else {
			// TODO 用户不存在
		}

		return menuList;
	}

	public PageDTO nodePage(NodeInfo nodeInfo, String flag, int rows, int page) {
		PageParameter pageInfo = new PageParameter();
		pageInfo.setPageSize(rows);
		pageInfo.setCurrentPage(page);

		List<NodeInfoAll> nodeInfoAll = nodeInfoMapper.nodePage(nodeInfo,flag, pageInfo);
		List<NodeDTO> datas = new ArrayList<NodeDTO>();
		
		for (int i = 0; i < nodeInfoAll.size(); i++) {
			NodeInfoAll infoAll = nodeInfoAll.get(i);
			
			NodeDTO nodeDTO = new NodeDTO(infoAll);
			nodeDTO.typeDesc = Utils.getParamValueDesc(infoAll.getType(),Utils.buildAgwParamList("bustype", ValueListDefine.class, commParamInfoService, "00", true));
			nodeDTO.funstyleDesc = Utils.getParamValueDesc(infoAll.getFunstyle(),Utils.buildAgwParamList("funstyle", ValueListDefine.class, commParamInfoService, "00", true));
			datas.add(nodeDTO);
			
		}
		PageDTO pageDTO = new PageDTO();
		pageDTO.rows = datas;
		pageDTO.page = pageInfo.getCurrentPage();
		pageDTO.total = pageInfo.getTotalPage();
		pageDTO.records = pageInfo.getTotalCount();
		return pageDTO;

	}

	public int deleteNode(String ids) {
			ArrayList<String> list = new ArrayList<String>();
			String idstr[] = ids.split(",");
			Collections.addAll(list, idstr);
			int deleteResult = nodeInfoMapper.deleteNode(list);
			return deleteResult;
	}

	public boolean addOrModifyNode(int userId, String data) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
		NodeInfo nodeInfo = null;
		try {
			nodeInfo = objectMapper.readValue(data, NodeInfo.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (nodeInfo != null) {
			if (nodeInfo.getId() != null) {// 节点id不为空,修改
				nodeInfo.setUpdateBy(userId);
				nodeInfo.setUpdateTime(new Date());
				int modifySelective = nodeInfoMapper.updateByPrimaryKeySelective(nodeInfo);
				if(modifySelective == 1){
					return true ;
				}else{
					 DubboExtProperty.setErrorCode(UserServiceHttpCode.NODE_MODIFYINFO_ERROR);
				}
			} else {// 节点id为空，新增
				nodeInfo.setCreateBy(userId);
				nodeInfo.setCreateTime(new Date());
				nodeInfo.setUpdateBy(userId);
				nodeInfo.setUpdateTime(new Date());
				int insertSelective = nodeInfoMapper.insertSelective(nodeInfo);
				if(insertSelective == 1){
					return true ;
				}else{
					 DubboExtProperty.setErrorCode(UserServiceHttpCode.NODE_ADDINFO_ERROR);
				}
			}
		} 
		
		return false;
	}

	public NodeDTO getNodeById(int id) {
		NodeInfoAll nodeById = nodeInfoMapper.getNodeById(id);
		if(nodeById != null){
			NodeDTO nodeDto = new NodeDTO(nodeById);
			nodeDto.funstyleDesc = Utils.getParamValueDesc(nodeDto.funstyle,Utils.buildAgwParamList("funstyle", ValueListDefine.class, commParamInfoService, "00", true));
			return nodeDto;
		}
		//对应的节点不存在
		 DubboExtProperty.setErrorCode(UserServiceHttpCode.NODE_NONEXISTENT_ERROR);
		return null;
	}

	public List<NodeInfo> getNodeTree(int roleId) {
		return nodeInfoMapper.getNodeTree(roleId);
		
	}

	public List<Menu> getMenuByRoleIds(List<Integer> roleIds) {
		Menu menu = null;

		SecondMenu secondMenu = null;
		List<Menu> menuList = new ArrayList<Menu>();

					// 获得角色对应的node
					List<NodeInfo> list = nodeInfoMapper.quaryAllNodeInfos(roleIds);
					
					for (int i = 0; i < list.size(); i++) {
						// 获取一级节点
						menu = new Menu();
						NodeInfo nodeInfo = list.get(i);
						int id = nodeInfo.getId();
						if (nodeInfo.getParentId() == 0) {
							menu.setNodeName(nodeInfo.getNodeName());
							menu.setPictureCode(nodeInfo.getPictureCode() + "");
							menu.setMethodName(nodeInfo.getMethodName());
							menu.setNodeCode(nodeInfo.getNodeCode());
							menu.setId(nodeInfo.getId());
						} else {
							continue;
						}
						// 获取一级节点对应的二级节点
						for (int ii = 0; ii < list.size(); ii++) {
							NodeInfo dto2 = list.get(ii);
							if (dto2.getParentId() == id) {
								secondMenu = new SecondMenu();
								secondMenu.setNodeName(dto2.getNodeName());
								secondMenu.setPictureCode(dto2.getPictureCode() + "");
								secondMenu.setMethodName(dto2.getMethodName());
								secondMenu.setNodeCode(dto2.getNodeCode());
								secondMenu.setId(dto2.getId());
								List<SecondMenu> child = menu.getFirstChild();
								child.add(secondMenu);
							}
						}
						menuList.add(menu);
					}
		return menuList;
	}

}
