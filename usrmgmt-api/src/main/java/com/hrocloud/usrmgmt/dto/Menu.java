package com.hrocloud.usrmgmt.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hrocloud.apigw.client.annoation.Description;
@Description("一级节点")
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7410515193792166197L;

	@Description("节点id")
	public int id;
	
	@Description("节点编码")
	public String nodeCode;
	
	@Description("节点名称")
	public String nodeName;
	
	@Description("图片名称")
	public String pictureCode;
	
	@Description("方法名")
	public String methodName;
	
	@Description("二级节点")
	public List<SecondMenu> firstChild;

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
		this.pictureCode = pictureCode == null ? "":pictureCode;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<SecondMenu> getFirstChild() {
		if(firstChild == null){
			firstChild = new ArrayList<SecondMenu>();
		}
		return firstChild;
	}

	public void setFirstChild(List<SecondMenu> firstChild) {
		this.firstChild = firstChild;
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
		return "Menu [id=" + id + ", nodeCode=" + nodeCode + ", nodeName=" + nodeName + ", pictureCode=" + pictureCode + ", methodName=" + methodName + ", firstChild=" + firstChild + "]";
	}





	
	
}
