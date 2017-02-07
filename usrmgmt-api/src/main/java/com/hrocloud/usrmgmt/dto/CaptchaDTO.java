/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by zou_s on 2016/12/28.
 */
@Description("图形验证码")
public class CaptchaDTO implements Serializable {
    private static final long serialVersionUID = -4283134079252932420L;

    @Description("下发唯一标识")
    public String deliveryId;

    @Description("验证码值")
    public String value;

    @Description("验证码图形url")
    public String imgUrl;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
