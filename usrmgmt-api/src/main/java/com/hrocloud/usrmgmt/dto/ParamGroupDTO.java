/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;
import com.hrocloud.usrmgmt.model.UserInfo;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zou_s on 2016/12/29.
 */
@Description("参数列表组")
public class ParamGroupDTO implements Serializable {

    private static final long serialVersionUID = -7499230411406064692L;

    @Description("参数列表类型code")
    public String typeCode;

    @Description("参数列表")
    public List<ParamValueDTO> paramList;
}
