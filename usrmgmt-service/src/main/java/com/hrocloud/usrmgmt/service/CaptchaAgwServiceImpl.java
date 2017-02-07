/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.service;

import com.hrocloud.common.api.model.CaptchaTemplate;
import com.hrocloud.redis.RedisClientTemplate;
import com.hrocloud.usrmgmt.api.CaptchaAgwService;
import com.hrocloud.usrmgmt.api.CaptchaService;
import com.hrocloud.usrmgmt.dto.CaptchaDTO;
import com.hrocloud.usrmgmt.dto.VerifyCodeDTO;
import com.hrocloud.usrmgmt.model.CaptchaImage;
import com.hrocloud.util.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


/**
 * Created by zou_s on 2016/12/28.
 */
@Service("captchaAgwService")
public class CaptchaAgwServiceImpl implements CaptchaAgwService {
    @Value("${captchaimg.length}")
    private int length;
    @Value("${captchaimg.baseurl}")
    private String baseUrl;
    @Value("${captchaimg.timout}")
    private int capTimeout;
    @Resource
    private RedisClientTemplate redisClient;
    @Resource
    private CaptchaService captchaService;

    public int genCaptchaImg(int number) {
        CaptchaTemplate captchaTemplate = new CaptchaTemplate();
        captchaTemplate.setBgColor("#111111");
        captchaTemplate.setBorderColor("#ffffff");
        captchaTemplate.setFont("Californian FB");
        captchaTemplate.setFontHeight(28);
        captchaTemplate.setInterferingMax(6);
        captchaTemplate.setInterferingMin(0);
        captchaTemplate.setWidth(120);
        captchaTemplate.setHeight(30);

        int count = captchaService.genCaptchaImg(length, captchaTemplate, number);
        return count;
    }

    public CaptchaDTO getCaptchaImage() {
        CaptchaImage captchaImage = captchaService.getCaptchaImage();
        CaptchaDTO captchaDTO = new CaptchaDTO();
        captchaDTO.deliveryId = CommonUtil.getUUID();
        captchaDTO.value = captchaImage.getValue();
        captchaDTO.imgUrl = baseUrl + captchaImage.getPath();
        redisClient.setex(captchaDTO.deliveryId, capTimeout, captchaDTO.value);
        return captchaDTO;
    }

    public VerifyCodeDTO getVerifyCode(String channelId) {
        Map<String, String> map = captchaService.getVerifyCode(channelId);
        String deliveryId = CommonUtil.getUUID();
        redisClient.hmset(deliveryId, map);
        redisClient.expire(deliveryId, capTimeout);
        VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO();
        verifyCodeDTO.channelId = channelId;
        verifyCodeDTO.deliveryId = deliveryId;
        verifyCodeDTO.value = map.get("verifyCode");
        return verifyCodeDTO;
    }
}
