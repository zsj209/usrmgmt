package com.hrocloud.usrmgmt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.usrmgmt.api.NodeService;
import com.hrocloud.usrmgmt.api.RoleButtonService;
import com.hrocloud.usrmgmt.api.RoleNodeAgwService;
import com.hrocloud.usrmgmt.api.RoleNodeService;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.RoleNodePermission;
@Service("roleNodeAgwService")
public class RoleNodeAgwServiceImpl implements RoleNodeAgwService {
		
	@Autowired
	RoleNodeService roleNodeService;
	@Autowired
	RoleButtonService roleButtonService;

	public boolean deleteNode(String ids) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addOrModifyRoleNode(int userId, int roleId, String data) {
		
		try{
		ArrayList<String> list = new ArrayList<String>();
		String[] roleIdStr = null;
		if(!StringUtils.isBlank(data)){
			roleIdStr = data.split(",");
			Collections.addAll(list, roleIdStr);
		}
		
		ArrayList<RoleNodePermission> addListRoleNode = new ArrayList<RoleNodePermission>();
		RoleNodePermission rnp = null;
		ArrayList<String> delArr = null;
		ArrayList<String> addArr = null;
		
		//查询当前的角色下的所有所属节点
		List<RoleNodePermission> nodeList = roleNodeService.getRoleNodeById(roleId);
		if((list == null || list.size() == 0) && (nodeList == null || nodeList.size() == 0)){
			//数据库没记录，且没有插入数据
			return true;
		}else{
			ArrayList<String> arrList = new ArrayList<String>();
			if(nodeList != null && nodeList.size() > 0){
					//获得当前角色下的所有节点
					for (RoleNodePermission roleNode : nodeList) {
						arrList.add(roleNode.getNodeId()+"");
					}
					
					if(list != null && list.size()>0){
						//比较与现有节点的差异,确定要删除和新增的数据
						delArr = getArr(arrList,list);
						addArr = getArr(list,arrList);
					}else{
						delArr =arrList;
					}
			}else{
				addArr = list;
			}
		}
		
	/*	if(roleIdStr != null && roleIdStr.length > 0 ){
			
				
		}*/
		if(addArr != null && addArr.size() >0 ){
			//新增角色节点
			for(int i=0;i<addArr.size();i++){
				rnp = new RoleNodePermission();
				rnp.setCreateBy(userId);
				rnp.setCreateTime(new Date());
				rnp.setUpdateBy(userId);
				rnp.setUpdateTime(new Date());
				rnp.setRoleId(roleId);
				rnp.setNodeId(Integer.parseInt(addArr.get(i)));
				addListRoleNode.add(rnp);
			}
			int addResult = roleNodeService.addOrModifyRoleNode(addListRoleNode);
		}
		
		if(delArr != null && delArr.size() > 0 ){
			
			for (String nodeId : delArr) {
				//删除节点对应的角色下的角色节点按钮权限
				roleButtonService.delRoleNodeButton(null, roleId, Integer.parseInt(nodeId));
			}
			
			//删除角色节点
			int delResult = roleNodeService.delRoleNode(delArr,roleId);
			
			
			
		}
		System.out.println(1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			 DubboExtProperty.setErrorCode(UserServiceHttpCode.NODE_DELINFO_ERROR);
		}
		return false;
	}

	public NodeDTO getNodeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

/**
 * 筛选出list1 在list2中不存在的数据
 * @param list1
 * @param list2
 * @return
 */
   private ArrayList<String>	getArr(ArrayList<String> list1,ArrayList<String> list2){
	   ArrayList<String> arrayList = new ArrayList<String>();
		 for(String s2:list1) {  
	            boolean flag = false;  
	            for(String s1:list2) {  
	                if(s2.equals(s1)) {  
	                    flag = true;  
	                    break;  
	                }  
	            }  
	            if(!flag){  
	            	arrayList.add(s2);
	                System.out.println(s2);  
	            }  
	        }
		return arrayList; 
	}
	
	
	
	
	
	

}
