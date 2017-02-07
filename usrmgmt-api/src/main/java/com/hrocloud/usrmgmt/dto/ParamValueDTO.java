/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;
import com.hrocloud.common.api.model.ParamValue;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by zou_s on 2016/12/28.
 */
@Description("参数对象")
public class ParamValueDTO implements Serializable {
    private static final long serialVersionUID = -4283134079252932420L;

    @Description("id")
    public int listId;

    @Description("编码")
    public String code;

    @Description("描述")
    public String desc;

    @Description("是否已选上")
    public byte checked;


    public ParamValueDTO(ParamValue vl) {
        this.listId = vl.getListId();
        this.code = vl.getCode();
        this.desc = vl.getDesc();
        this.checked = vl.getChecked();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
