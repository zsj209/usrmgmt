/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dao;

import com.hrocloud.common.dao.HroBaseMapper;
import com.hrocloud.usrmgmt.model.CaptchaImage;

/**
 * Generate captcha image and store the url and the meaning
 */
public interface CaptchaImageMapper extends HroBaseMapper<CaptchaImage> {

    int getCapCount();

    CaptchaImage getCapByPos(int capPos);
}