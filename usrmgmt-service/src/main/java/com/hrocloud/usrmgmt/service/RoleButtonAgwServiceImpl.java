package com.hrocloud.usrmgmt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.usrmgmt.api.ButtonService;
import com.hrocloud.usrmgmt.api.RoleButtonAgwService;
import com.hrocloud.usrmgmt.api.RoleButtonService;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.dto.RoleButtonPermissionDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.RoleButtonPermission;
import com.hrocloud.usrmgmt.model.RoleButtonPermission;

@Service("roleButtonAgwService")
public class RoleButtonAgwServiceImpl implements RoleButtonAgwService {

	@Autowired
	RoleButtonService roleButtonService;

	@Autowired
	ButtonService buttonService;

	public List<RoleButtonPermissionDTO> getRoleButton(int roleId, int nodeId) {

		List<RoleButtonPermission> resuleList = roleButtonService.getRoleButton(roleId, nodeId);
		if (resuleList.size() > 0) {
			ArrayList<RoleButtonPermissionDTO> list = new ArrayList<RoleButtonPermissionDTO>();
			for (int i = 0; i < resuleList.size(); i++) {
				list.add(new RoleButtonPermissionDTO(resuleList.get(i)));
			}
			return list;
		}
		DubboExtProperty.setErrorCode(UserServiceHttpCode.ROLE_BUTTON_IS_NULL);
		return null;
	}

	public boolean addOrModifyRoleNodeButton(int userId, int roleId, int nodeId, String data) {
		try {
			ArrayList<String> list = new ArrayList<String>();
			if (!StringUtils.isBlank(data)) {
				String[] roleIdStr = data.split(",");
				Collections.addAll(list, roleIdStr);
			}

			ArrayList<RoleButtonPermission> addListRoleButton = new ArrayList<RoleButtonPermission>();
			RoleButtonPermission rnp = null;
			List<String> delArr = null;
			List<String> addArr = null;
			// 查询当前的角色下的所有所属节点按钮
			List<RoleButtonPermission> buttonList = roleButtonService.getRoleButton(roleId, nodeId);
			ArrayList<String> arrList = new ArrayList<String>();

			if (buttonList != null && buttonList.size() > 0) {
				// 获得当前角色下的所有节点按钮
				for (RoleButtonPermission roleButton : buttonList) {
					arrList.add(roleButton.getButtonId() + "");
				}

				if (list != null && list.size() > 0) {
					// 比较与现有节点的差异,确定要删除和新增的数据
					delArr = getArr(arrList, list);
					addArr = getArr(list, arrList);

				} else {
					delArr = arrList;
				}

			} else {
				addArr = list;
			}

			if (addArr != null && addArr.size() > 0) {
				try {
					// 新增角色节点
					for (int i = 0; i < addArr.size(); i++) {
						rnp = new RoleButtonPermission();
						rnp.setCreateBy(userId);
						rnp.setCreateTime(new Date());
						rnp.setUpdateBy(userId);
						rnp.setUpdateTime(new Date());
						rnp.setRoleId(roleId);
						rnp.setNodeId(nodeId);
						rnp.setButtonId(Integer.parseInt(addArr.get(i)));
						addListRoleButton.add(rnp);
					}
					int addResult = roleButtonService.addOrModifyRoleNodeButton(addListRoleButton);
				} catch (Exception e) {
					DubboExtProperty.setErrorCode(UserServiceHttpCode.ROLE_BUTTON_ADDINFO_ERROR);
				}
			}

			if (delArr != null && (delArr.size()) > 0) {
				try {
					// 删除角色节点按钮
					int delResult = roleButtonService.delRoleNodeButton(delArr, roleId, nodeId);
				} catch (Exception e) {
					DubboExtProperty.setErrorCode(UserServiceHttpCode.ROLE_BUTTON_MODIFYINFO_ERROR);
				}
			}
			System.out.println(1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 筛选出list1 在list2中不存在的数据
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	private List<String> getArr(List<String> list1, List<String> list2) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (String s2 : list1) {
			boolean flag = false;
			for (String s1 : list2) {
				if (s2.equals(s1)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				arrayList.add(s2);
				System.out.println(s2);
			}
		}
		return arrayList;
	}
}
