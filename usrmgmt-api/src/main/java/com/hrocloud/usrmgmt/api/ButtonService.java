package com.hrocloud.usrmgmt.api;

import java.util.List;

import com.hrocloud.common.page.PageParameter;
import com.hrocloud.usrmgmt.dto.ButtonDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.model.ButtonInfo;
import com.hrocloud.usrmgmt.model.ButtonInfoAll;

/**
 * Created by tony
 */

public interface ButtonService {
	
	public List<ButtonInfoAll> buttonPage(ButtonInfo buttonInfo, PageParameter pageInfo);

	public boolean deleteButton(String ids);

	public boolean addOrModifyButton(int userId, String data);

	public ButtonDTO getButtonById(int id);

	public List<ButtonInfoAll> getButtonByNodeId(int nodeId,String roleId);


}
