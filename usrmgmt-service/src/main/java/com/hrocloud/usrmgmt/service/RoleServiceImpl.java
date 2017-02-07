package com.hrocloud.usrmgmt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.api.RoleService;
import com.hrocloud.usrmgmt.dao.RoleInfoMapper;
import com.hrocloud.usrmgmt.dao.RoleInfoMapper;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.RoleInfo;
import com.hrocloud.usrmgmt.model.RoleInfo;
@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

	 @Autowired
	    RoleInfoMapper roleDao;

	public RoleInfo getRoleById(int roleId) {
		return roleDao.selectByPrimaryKey(roleId);
	}

	public List<RoleInfo> getRoleList(HashMap<String, String> dataMap, PageParameter pageInfo) {
		return roleDao.getAllRolePage(dataMap, pageInfo);
	}

	public boolean addOrModifyRole(int userId,String data) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
		RoleInfo roleInfo = null;
			try {
				roleInfo = objectMapper.readValue(data, RoleInfo.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (roleInfo != null) {
					if (roleInfo.getId() != null) {// 节点id不为空,修改
						roleInfo.setUpdateTime(new Date());
						roleInfo.setUpdateBy(userId);
						int modifySelective = roleDao.updateByPrimaryKeySelective(roleInfo);
						if(modifySelective == 1){
							return true ;
						}else{
							 DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_MODIFYINFO_ERROR);
						}
					} else {// 节点id为空，新增
						//roleInfo.setCreateTime(new Date());
						roleInfo.setUpdateTime(new Date());
						roleInfo.setCreateBy(userId);
						roleInfo.setUpdateBy(userId);
						int insertSelective = roleDao.insertSelective(roleInfo);
						if(insertSelective == 1){
							return true ;
						}else{
							 DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_ADDINFO_ERROR);
						}
					}
			}
			return false;
	}

	public boolean deleteRole(String ids) {
		try{
			ArrayList<String> list = new ArrayList<String>();
			String idstr[] = ids.split(",");
			Collections.addAll(list, idstr);
			int deleteUser = roleDao.deleteRole(list);
			return true;
		} catch (Exception e) {
			 DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_DELINFO_ERROR);
		}
		return false;
	}

	public boolean modifyEnabled(String ids, String status) {
		try{
			ArrayList<String> list = new ArrayList<String>();
			String idstr[] = ids.split(",");
			Collections.addAll(list, idstr);
			int modifyStatus;
			if("02valid".equals(status)){//生效
				modifyStatus = roleDao.modifyEnabled(list,status,null);
			}else{//失效，修改失效时间
				modifyStatus = roleDao.modifyEnabled(list,status,new Date());
			}
			if(modifyStatus == list.size()){
				return true;
			}
		} catch (Exception e) {
			 DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
		}
		return false;
	}

	public int deleteById(Integer arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public RoleInfo getById(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert(RoleInfo arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(RoleInfo arg0) {
		// TODO Auto-generated method stub
		return 0;
	}




}
