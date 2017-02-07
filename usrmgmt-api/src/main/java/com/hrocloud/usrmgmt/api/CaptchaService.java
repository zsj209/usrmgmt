/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.api;

import com.hrocloud.common.api.model.CaptchaTemplate;
import com.hrocloud.usrmgmt.model.CaptchaImage;

import java.util.Map;

/**
 * Created by Stanley
 */
public interface CaptchaService {
    /**
     * @param length
     * @param capchaTemplate
     * @param number
     */
    int genCaptchaImg(int length, CaptchaTemplate captchaTemplate, int number);

    /**
     * @param capId
     * @return
     */
    CaptchaImage getCapById(int capId);

    /**
     * 获取验证码总数量
     *
     * @return
     */
    int getCapCount();

    /**
     * @return
     */
    CaptchaImage getCaptchaImage();

    /**
     * @return
     * @parm channelId 手机或者邮箱
     */
    Map<String, String> getVerifyCode(String channelId);

}
