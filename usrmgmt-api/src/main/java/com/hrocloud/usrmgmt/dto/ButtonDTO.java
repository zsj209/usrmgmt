package com.hrocloud.usrmgmt.dto;

import java.io.Serializable;

import com.hrocloud.apigw.client.annoation.Description;
import com.hrocloud.usrmgmt.model.ButtonInfoAll;
/**
 * Created by tony.chen on 2017/1/06
 */
@Description("节点按钮信息")
public class ButtonDTO implements Serializable{

	private static final long serialVersionUID = 452054709096537482L;
	@Description("节点按钮id")
	public int id;
	@Description("节点按钮名称")
    public String buttonName;
	@Description("节点所属节点id")
    public int nodeId;
	@Description("所属节点名称")
    public String buttonNode;
	@Description("节点按钮id")
    public String buttonId;
	@Description("备注")
    public String memo;
	
	public ButtonDTO(ButtonInfoAll buttonInfoAll) {
		this.id = buttonInfoAll.getId();
		this.buttonName = buttonInfoAll.getButtonName();
		this.nodeId = buttonInfoAll.getNodeId();
		this.buttonNode = buttonInfoAll.getButtonNode();
		this.buttonId = buttonInfoAll.getButtonId();
		this.memo = buttonInfoAll.getMemo();
	}


}
