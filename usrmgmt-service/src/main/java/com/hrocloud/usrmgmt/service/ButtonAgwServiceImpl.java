package com.hrocloud.usrmgmt.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.api.ButtonAgwService;
import com.hrocloud.usrmgmt.api.ButtonService;
import com.hrocloud.usrmgmt.api.RoleButtonService;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.ButtonDTO;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.ButtonInfo;
import com.hrocloud.usrmgmt.model.ButtonInfoAll;
@Service("buttonAgwServiceImpl")
public class ButtonAgwServiceImpl implements ButtonAgwService {
	
	@Autowired
	RoleButtonService roleButtonService;

	@Autowired
	ButtonService buttonServiceImpl;

	public PageDTO buttonPage(String buttonName, int rows, int page) {
		ButtonInfo buttonInfo = new ButtonInfo();
		if (!StringUtils.isBlank(buttonName)) {
			buttonInfo.setButtonName(buttonName.trim());
		}
		/*PageDTO buttonPage = buttonServiceImpl.buttonPage(buttonInfo, rows, page);*/
		
		PageParameter pageInfo = new PageParameter();
		pageInfo.setPageSize(rows);
		pageInfo.setCurrentPage(page);

		List<ButtonInfoAll> buttonInfoAll = buttonServiceImpl.buttonPage(buttonInfo, pageInfo);
		List<ButtonDTO> datas = new ArrayList<ButtonDTO>();
		
		for (int i = 0; i < buttonInfoAll.size(); i++) {
				datas.add(new ButtonDTO(buttonInfoAll.get(i)));
		}
		PageDTO pageDTO = new PageDTO();
		pageDTO.rows = datas;
		pageDTO.page = pageInfo.getCurrentPage();
		pageDTO.total = pageInfo.getTotalPage();
		pageDTO.records = pageInfo.getTotalCount();
		return pageDTO;
	}

	public boolean deleteButton(String ids) {
		
		try{
			//统计是否已经分配给某一角色
			int count  = roleButtonService.getCountByButtonIds(ids);
			if(count == 0){
				boolean deleteButton = buttonServiceImpl.deleteButton(ids);
				return deleteButton;
			}else{
				//不能删除，存在按钮已经分配给指定角色
				DubboExtProperty.setErrorCode(UserServiceHttpCode.BUTTON_ASSIGNED_ERROR);
			}
			
		} catch (Exception e) {
			 DubboExtProperty.setErrorCode(UserServiceHttpCode.BUTTON_DELINFO_ERROR);
		}
		return false;
	}

	public boolean addOrModifyButton(int userId, String data) {
		boolean button = buttonServiceImpl.addOrModifyButton(userId, data);
		return false;
	}

	public ButtonDTO getButtonById(int id) {
		ButtonDTO buttonById = buttonServiceImpl.getButtonById(id);
		return buttonById;
	}

	public List<ButtonDTO> getButtonByNodeId(int nodeId,String roleId) {
		List<ButtonInfoAll> buttonByNodeId = buttonServiceImpl.getButtonByNodeId(nodeId,roleId);
		List<ButtonDTO> ResultList = new ArrayList<ButtonDTO>();
		if(buttonByNodeId.size()>0){
			for(int i=0;i<buttonByNodeId.size();i++){
				ResultList.add(new ButtonDTO(buttonByNodeId.get(i)));
			}
			return ResultList;
		}
		
		return null;
		
	}

}
