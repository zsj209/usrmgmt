package com.hrocloud.usrmgmt.model;

import java.util.Date;

import com.hrocloud.apigw.client.annoation.Description;
import com.hrocloud.common.api.model.BaseObj;

/**
 * Created by tony.chen on 2017/1/05
 */
public class NodeInfoAll extends BaseObj {
	

	private static final long serialVersionUID = 3073248066461735541L;
	private int id;
    private String nodeName;
    private int parentId;
	private String parentName;
    private String pictureCode;
    private String methodName;
    private String memo;
    private String nodeCode;
    private String type;
    private String funstyle;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFunstyle() {
		return funstyle;
	}
	public void setFunstyle(String funstyle) {
		this.funstyle = funstyle;
	}
    
	
}