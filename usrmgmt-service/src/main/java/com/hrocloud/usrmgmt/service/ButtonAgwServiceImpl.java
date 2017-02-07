package com.hrocloud.usrmgmt.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.api.ButtonAgwService;
import com.hrocloud.usrmgmt.api.ButtonService;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.ButtonDTO;
import com.hrocloud.usrmgmt.dto.NodeDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.model.ButtonInfo;
import com.hrocloud.usrmgmt.model.ButtonInfoAll;
@Service("buttonAgwServiceImpl")
public class ButtonAgwServiceImpl implements ButtonAgwService {

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
		boolean deleteButton = buttonServiceImpl.deleteButton(ids);
		return deleteButton;
	}

	public boolean addOrModifyButton(int userId, String data) {
		boolean button = buttonServiceImpl.addOrModifyButton(userId, data);
		return false;
	}

	public ButtonDTO getButtonById(int id) {
		ButtonDTO buttonById = buttonServiceImpl.getButtonById(id);
		return buttonById;
	}

	public List<ButtonDTO> getButtonByNodeId(int nodeId) {
		List<ButtonInfoAll> buttonByNodeId = buttonServiceImpl.getButtonByNodeId(nodeId);
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
