package com.hrocloud.usrmgmt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrocloud.common.dao.HroBaseMapper;
import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.model.RoleInfo;

public interface RoleInfoMapper extends HroBaseMapper<RoleInfo> {

	List<RoleInfo> getAllRolePage(@Param("dataMap")HashMap<String, String> dataMap,@Param("page") PageParameter pageInfo);
	
	int deleteRole(ArrayList<String> list);

	int modifyEnabled(@Param("list")ArrayList<String> list,@Param("enabled") String enabled,@Param("terminationTime") Date terminationTime);

}