/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.api;

import com.hrocloud.apigw.client.annoation.ApiGroup;
import com.hrocloud.apigw.client.annoation.ApiParameter;
import com.hrocloud.apigw.client.annoation.HttpApi;
import com.hrocloud.apigw.client.define.SecurityType;
import com.hrocloud.usrmgmt.dto.CaptchaDTO;
import com.hrocloud.usrmgmt.dto.VerifyCodeDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;

/**
 * Created by Stanley Zou on 2016/12/28
 */

@ApiGroup(name = "usrmgmt", minCode = 1000, maxCode = 1999, codeDefine = UserServiceHttpCode.class, owner = "Stanley")
public interface CaptchaAgwService {
    /**
     * 批量生成验证码
     *
     * @param number
     */
    @HttpApi(name = "usrmgmt.gencaptchaimg", desc = "批量产生图形验证码", security = SecurityType.None)
    int genCaptchaImg(@ApiParameter(required = true, name = "number", desc = "生成数量") int number);

    /**
     * 得到图形验证码信息
     *
     * @return
     */
    @HttpApi(name = "usrmgmt.getcaptcha", desc = "得到图形验证码", security = SecurityType.None)
    CaptchaDTO getCaptchaImage();

    /**
     * 得到手机/邮箱验证码信息
     *
     * @param channelId 手机号或邮箱
     * @return
     */
    @HttpApi(name = "usrmgmt.getvericode", desc = "得到手机/邮箱验证码", security = SecurityType.None)
    VerifyCodeDTO getVerifyCode(@ApiParameter(required = true, name = "channelId", desc = "手机号或邮箱") String channelId);

}
