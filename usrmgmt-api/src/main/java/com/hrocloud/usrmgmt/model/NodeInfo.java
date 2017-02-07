package com.hrocloud.usrmgmt.model;

import java.util.Date;

import com.hrocloud.common.api.model.BaseObj;

public class NodeInfo extends BaseObj{

	private static final long serialVersionUID = 963453232054533091L;

	private Integer id;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String nodeCode;

    private String nodeName;

    private Integer parentId;

    private String pictureCode;

    private String methodName;

   private String type;
   
    private String funstyle;

    private String memo;
    
    
	public String getFunstyle() {
		return funstyle;
	}

	public void setFunstyle(String funstyle) {
		this.funstyle = funstyle;
	}

	// 以下4个字段主要是为了构造设置已选的节点树
	private String sublist = ""; // 下级列表
	private Integer subnums = 0; // 下级数量
	private Integer subvalidnums = 0; // 已开通的下级数量
	private String setstyle = ""; // 设置样式
	private String nodePcode = ""; // 上级code
	private Integer nodeId = 0; // 上级code
    


	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodePcode() {
		return nodePcode;
	}

	public void setNodePcode(String nodePcode) {
		this.nodePcode = nodePcode;
	}

	public String getSublist() {
		return sublist;
	}

	public void setSublist(String sublist) {
		this.sublist = sublist;
	}

	public Integer getSubnums() {
		return subnums;
	}

	public void setSubnums(Integer subnums) {
		this.subnums = subnums;
	}

	public Integer getSubvalidnums() {
		return subvalidnums;
	}

	public void setSubvalidnums(Integer subvalidnums) {
		this.subvalidnums = subvalidnums;
	}

	public String getSetstyle() {
		return setstyle;
	}

	public void setSetstyle(String setstyle) {
		this.setstyle = setstyle;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode == null ? null : nodeCode.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPictureCode() {
        return pictureCode;
    }

    public void setPictureCode(String pictureCode) {
        this.pictureCode = pictureCode == null ? null : pictureCode.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}