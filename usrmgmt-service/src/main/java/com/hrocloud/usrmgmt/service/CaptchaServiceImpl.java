/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.service;

import com.hrocloud.common.api.model.CaptchaTemplate;
import com.hrocloud.usrmgmt.api.CaptchaService;
import com.hrocloud.usrmgmt.dao.CaptchaImageMapper;
import com.hrocloud.usrmgmt.model.CaptchaImage;
import com.hrocloud.util.CaptchaUtil;
import com.hrocloud.util.CaptchaValueType;
import com.hrocloud.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zou_s on 2016/12/27.
 */
@Service("captchaService")
public class CaptchaServiceImpl implements CaptchaService {
    @Value("${captchaimg.basedir}")
    private String imgBaseDir;

    @Autowired
    private CaptchaImageMapper capDao;

    public int genCaptchaImg(int length, CaptchaTemplate captchaTemplate, int number) {

        List<String> capList = CaptchaUtil.createCaptchaValues(length, CaptchaValueType.CHARACTER_AND_DIGIT, number);
        BufferedImage biImpage = null;

        CaptchaImage captchaImage = new CaptchaImage();
        String path;
        File file = new File(imgBaseDir);
        if (file.exists()) {
        } else {
            file.mkdirs();
        }
        for (String capCode : capList) {
            biImpage = CaptchaUtil.createCaptchaImage(captchaTemplate, capCode);

            try {
                path = new Date().getTime() + CommonUtil.getUUID() + ".jpg";
                ImageIO.write(biImpage, "jpg", new File(imgBaseDir + "/" + path));
                captchaImage.setPath(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            captchaImage.setValue(capCode);
            captchaImage.setCreateTime(new Date());
            capDao.insertSelective(captchaImage);
        }
        return number;
    }

    public CaptchaImage getCapById(int capId) {
        return capDao.selectByPrimaryKey(capId);
    }


    public int getCapCount() {
        return capDao.getCapCount();
    }


    public CaptchaImage getCaptchaImage() {
        int count = getCapCount();
        if (count == 0)
            return null;
        int capPos = (int) (Math.random() * (count - 1));
        CaptchaImage captchaImage = getCapByPos(capPos);
        return captchaImage;
    }

    public Map<String, String> getVerifyCode(String channelId) {
        Map map = new HashMap<String, String>();
        map.put("channelId",channelId);
        //TODO : 未来在此连接真实的手机网关或者邮箱验证
        map.put("verifyCode","2345");
        return map;
    }

    private CaptchaImage getCapByPos(int capPos) {
        return capDao.getCapByPos(capPos);
    }
}
