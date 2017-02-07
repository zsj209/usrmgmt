package com.hrocloud.usrmgmt.dto;

import java.io.Serializable;

import com.hrocloud.apigw.client.annoation.Description;
@Description("节点树")
public class NodeTreeDTO implements Serializable{

	private static final long serialVersionUID = 8335498937470841627L;
	
	@Description("id")
	public int id; // 本表id, 如果是通过selectInvalidCityForSet查询，则是城市表id

	@Description("节点类型")
	public String type; // 节点类型

	@Description("节点id")
	public int nodeId; // 城市id

	@Description("节点编码")
	public String nodeCode; // 城市编码

	@Description("节点名称")
	public String nodeName; // 城市名称

	@Description("所属上级Id")
	public int parentId; // 所属上级Id

	@Description("所属上级Code")
	public String nodePcode; // 所属上级Code

	// 以下2个字段主要是为了构造设置节点树
	@Description("下级列表")
	public String sublist;

	@Description("设置样式")
	public String setstyle; // 设置样式
	
	@Description("是否选中")
	public String subvalidnums; // 是否选中
	

}
