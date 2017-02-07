package com.hrocloud.usrmgmt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.api.ButtonService;
import com.hrocloud.usrmgmt.dao.ButtonInfoMapper;
import com.hrocloud.usrmgmt.dto.ButtonDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.ButtonInfo;
import com.hrocloud.usrmgmt.model.ButtonInfoAll;
/**
 * Created by tony.chen on 2017/1/06
 */
@Service("buttonServiceImpl")
public class ButtonServiceImpl implements ButtonService {

	@Autowired
	ButtonInfoMapper buttonInfoMapper;
	
	public List<ButtonInfoAll> buttonPage(ButtonInfo buttonInfo, PageParameter pageInfo) {

		List<ButtonInfoAll> buttonInfoAll = buttonInfoMapper.buttonPage(buttonInfo, pageInfo);
		return buttonInfoAll;
	
	}

	public boolean deleteButton(String ids) {
		try{
			ArrayList<String> list = new ArrayList<String>();
			String idstr[] = ids.split(",");
			Collections.addAll(list, idstr);
			int deleteResult = buttonInfoMapper.deleteButton(list);
			return true;
		} catch (Exception e) {
			//TODO
			 DubboExtProperty.setErrorCode(UserServiceHttpCode.BUTTON_DELINFO_ERROR);
		}
		return false;
	}

	public boolean addOrModifyButton(int userId, String data) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
		ButtonInfo buttonInfo = null;
		try {
			buttonInfo = objectMapper.readValue(data, ButtonInfo.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (buttonInfo != null) {
			if (buttonInfo.getId() != null) {// 节点id不为空,修改
				buttonInfo.setUpdateBy(userId);
				buttonInfo.setUpdateTime(new Date());
				int modifySelective = buttonInfoMapper.updateByPrimaryKeySelective(buttonInfo);
				if(modifySelective == 1){
					return true ;
				}else{
					//TODO
					 DubboExtProperty.setErrorCode(UserServiceHttpCode.BUTTON_MODIFYINFO_ERROR);
				}
			} else {// 节点id为空，新增
				buttonInfo.setCreateBy(userId);
				buttonInfo.setCreateTime(new Date());
				buttonInfo.setUpdateBy(userId);
				buttonInfo.setUpdateTime(new Date());
				int insertSelective = buttonInfoMapper.insertSelective(buttonInfo);
				if(insertSelective == 1){
					return true ;
				}else{
					//TODO
					 DubboExtProperty.setErrorCode(UserServiceHttpCode.BUTTON_ADDINFO_ERROR);
				}
			}
		} 
		
		return false;
	}

	public ButtonDTO getButtonById(int id) {
		ButtonInfoAll buttonById = buttonInfoMapper.getButtonById(id);
		if(buttonById != null){
			return new ButtonDTO(buttonById);
		}
		//对应的按钮不存在
		 DubboExtProperty.setErrorCode(UserServiceHttpCode.BUTTON_NONEXISTENT_ERROR);
		return null;
	}

	public List<ButtonInfoAll> getButtonByNodeId(int nodeId) {
		return buttonInfoMapper.getButtonByNodeId(nodeId);
	}



}
