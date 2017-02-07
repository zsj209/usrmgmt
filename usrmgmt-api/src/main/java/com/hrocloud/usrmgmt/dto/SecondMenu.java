package com.hrocloud.usrmgmt.dto;

import java.io.Serializable;
import java.util.List;

import com.hrocloud.apigw.client.annoation.Description;
@Description("二级节点")
public class SecondMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2598893298820773923L;

	@Description("节点id")
	public int id;
	
	@Description("节点编码")
	public String nodeCode;
	
	@Description("节点名称")
	public String nodeName;
	
	@Description("节点名称")
	public String pictureCode;
	
	@Description("方法名")
	public String methodName;

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getPictureCode() {
		return pictureCode;
	}

	public void setPictureCode(String pictureCode) {
		this.pictureCode = pictureCode;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	@Override
	public String toString() {
		return "SecondMenu [id=" + id + ", nodeCode=" + nodeCode + ", nodeName=" + nodeName + ", pictureCode=" + pictureCode + ", methodName=" + methodName + "]";
	}


	

	
	
}
