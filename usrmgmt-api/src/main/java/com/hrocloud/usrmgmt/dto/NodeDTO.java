package com.hrocloud.usrmgmt.dto;

import java.io.Serializable;
import java.util.Date;

import com.hrocloud.apigw.client.annoation.Description;
import com.hrocloud.common.api.model.BaseObj;
import com.hrocloud.usrmgmt.model.NodeInfo;
import com.hrocloud.usrmgmt.model.NodeInfoAll;

/**
 * Created by tony.chen on 2017/1/04
 */
@Description("分页")
public class NodeDTO implements Serializable {
	
	private static final long serialVersionUID = -5625498147096402505L;
	@Description("id")
	public int id;
	@Description("节点名称")
    public String nodeName;
	@Description("上级节点")
    public int parentId;
	@Description("上级节点名称")
	public String parentName;
	@Description("图片")
    public String pictureCode;
	@Description("方法名")
    public String methodName;
	@Description("备注")
    public String memo;
	@Description("类型")
	public String type;
	@Description("类型编码")
	public String typeDesc;
	@Description("节点标识编码")
	public String funstyle;
	@Description("节点标识描述")
	public String funstyleDesc;
	@Description("节点编码")
	public String nodeCode;
	public NodeDTO(NodeInfoAll nodeInfoAll) {
		this.id = nodeInfoAll.getId();
		this.nodeName = nodeInfoAll.getNodeName();
		this.parentId = nodeInfoAll.getParentId();
		this.parentName = nodeInfoAll.getParentName();
		this.pictureCode = nodeInfoAll.getPictureCode();
		this.methodName = nodeInfoAll.getMethodName();
		this.memo = nodeInfoAll.getMemo();
		this.type = nodeInfoAll.getType();
		this.funstyle = nodeInfoAll.getFunstyle();
		this.nodeCode = nodeInfoAll.getNodeCode();
	}
	
	
	
	
	

}