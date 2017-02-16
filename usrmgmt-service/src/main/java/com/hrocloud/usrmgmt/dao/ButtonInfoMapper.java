package com.hrocloud.usrmgmt.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrocloud.common.page.PageParameter;

import com.hrocloud.common.dao.HroBaseMapper;

import com.hrocloud.usrmgmt.model.ButtonInfo;
import com.hrocloud.usrmgmt.model.ButtonInfoAll;

public interface ButtonInfoMapper extends HroBaseMapper<ButtonInfo> {

	List<ButtonInfoAll> buttonPage(@Param("buttonInfo")ButtonInfo buttonInfo,@Param("page") PageParameter pageInfo);

	int deleteButton(ArrayList<String> list);

	ButtonInfoAll getButtonById(int id);

	List<ButtonInfoAll> getButtonByNodeId(@Param("nodeId")int nodeId,@Param("roleId") String roleId);

}
