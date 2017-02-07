package com.hrocloud.usrmgmt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.common.api.CommParamInfoService;
import com.hrocloud.common.page.PageParameter;
import com.hrocloud.company.api.CompanyAgwService;
import com.hrocloud.company.api.CompanyService;
import com.hrocloud.company.dto.CompanyDto;
import com.hrocloud.company.model.Company;
import com.hrocloud.usrmgmt.api.RoleAgwService;
import com.hrocloud.usrmgmt.api.RoleService;
import com.hrocloud.usrmgmt.constant.ValueListDefine;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.dto.RoleDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.RoleInfo;
import com.hrocloud.usrmgmt.utils.Utils;

@Service("roleAgwServiceImpl")
public class RoleAgwServiceImpl implements RoleAgwService {

	@Resource
	private RoleService roleService;

	@Resource
	private CompanyService companyService;

	@Resource
	private CommParamInfoService commParamInfoService;

	public PageDTO getRoleList(String roleName,String enabled ,int rows, int page) {
		//HashMap<String, String> dataMap = (HashMap<String, String>) JSON.parseObject(data, Map.class);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("roleName", roleName);
		dataMap.put("enabled", enabled);
		

		// TODO
		PageParameter pageInfo = new PageParameter();
		pageInfo.setPageSize(rows);
		pageInfo.setCurrentPage(page);
		List<RoleInfo> roleList = roleService.getRoleList(dataMap, pageInfo);

		List<RoleDTO> udList = new ArrayList<RoleDTO>();
		for (RoleInfo role : roleList) {
			RoleDTO roleDTO = new RoleDTO(role);
			// TODO idType
			roleDTO.enabledDesc = Utils.getParamValueDesc(role.getEnabled(), Utils.buildAgwParamList("pubstatus", ValueListDefine.class, commParamInfoService, "00", true));
			roleDTO.typeDesc = Utils.getParamValueDesc(role.getType(), Utils.buildAgwParamList("bustype", ValueListDefine.class, commParamInfoService, "00", true));
			Company selectById = null;
			try {
				selectById = companyService.selectById(roleDTO.companyId);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (selectById != null) {
				roleDTO.companyName = selectById.getCompName();
			}
			udList.add(roleDTO);
		}

		PageDTO pageDTO = new PageDTO();
		pageDTO.rows = udList;
		pageDTO.page = pageInfo.getCurrentPage();
		pageDTO.total = pageInfo.getTotalPage();
		pageDTO.records = pageInfo.getTotalCount();
		return pageDTO;
	}

	public boolean addOrModifyRole(int userId, String data) {
		boolean role = roleService.addOrModifyRole(userId, data);
		return role;
	}

	public RoleDTO getRoleById(int id) {
		RoleInfo roleInfo = roleService.getRoleById(id);
		if (roleInfo == null) {
			DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
			return null;
		}
		RoleDTO roleDTO = new RoleDTO(roleInfo);
		
		roleDTO.enabledDesc = Utils.getParamValueDesc(roleInfo.getEnabled(), Utils.buildAgwParamList("yesno", ValueListDefine.class, commParamInfoService, "00", true));
		Company selectById = companyService.selectById(roleDTO.companyId);
		if (selectById != null) {
			roleDTO.companyName = selectById.getCompName();
		}

		return roleDTO;
	}

	public boolean deleteRole(String ids) {
		boolean deleteRole = roleService.deleteRole(ids);
		return deleteRole;
	}

	public boolean modifyEnabled(String ids, String enabled) {
		boolean modifyEnabled = roleService.modifyEnabled(ids, enabled);
		return modifyEnabled;
	}

}
