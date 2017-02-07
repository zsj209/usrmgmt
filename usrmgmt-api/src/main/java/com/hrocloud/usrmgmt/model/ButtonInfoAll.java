package com.hrocloud.usrmgmt.model;

import java.util.Date;

import com.hrocloud.common.api.model.BaseObj;

/**
 * Created by tony.chen on 2017/1/03
 */
public class ButtonInfoAll extends BaseObj {

	private static final long serialVersionUID = -5801465521298798939L;

	private int id;

	private String buttonName;

	private int nodeId;

	private String buttonNode;

	private String buttonId;

	private String memo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getButtonNode() {
		return buttonNode;
	}

	public void setButtonNode(String buttonNode) {
		this.buttonNode = buttonNode;
	}

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId == null ? null : buttonId.trim();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}