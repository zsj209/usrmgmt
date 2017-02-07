/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.service;

import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.apigw.client.service.SecurityService;
import com.hrocloud.usrmgmt.api.SessionService;
import com.hrocloud.usrmgmt.api.UserService;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.UserInfo;
import com.hrocloud.usrmgmt.utils.Utils;
import com.hrocloud.util.AppDomain;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zou_s on 2016/12/28.
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    @Resource
    private SessionService sessionService;
    @Resource
    private UserService userService;

    @Value("${user.timeout}")
    private long timeout;

    /**
     * @param clientIp
     * @param deviceId
     * @param applicationId
     * @param userId
     * @return 0- 正常
     * 1- 用户不存在
     * 2- 非活跃设备
     * 3- session过期
     * 4- 非授信设备
     * 5- IP地址发生变化，请重新登录
     */
    public int checkUserStatus(String clientIp, Long deviceId, Integer applicationId, Integer userId) {
        Integer domainId = AppDomain.appOf(applicationId).getDomainId();
        String sessionKey = Utils.getSessionKey(domainId, userId);

        String origIpAddr = sessionService.getValue(sessionKey, "clientIp");
        if (StringUtils.isBlank(origIpAddr)) {
            DubboExtProperty
                    .setErrorCode(UserServiceHttpCode.SESSION_TIMETOUT);
            sessionService.invalidateSession(sessionKey);
            return 3;
        }

        if (!clientIp.equals(origIpAddr)) {
            DubboExtProperty
                    .setErrorCode(UserServiceHttpCode.IP_INVALID);
            return 5;
        }


        UserInfo user = userService.getUserById(userId);
        if (user == null) {
            DubboExtProperty
                    .setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
            return 1;
        }

      /*  long expireTime = Long.valueOf(sessionService.getValue(sessionKey, "expire"));
        long currentTime = System.currentTimeMillis();

        if (expireTime < currentTime) {
            DubboExtProperty
                    .setErrorCode(UserServiceHttpCode.SESSION_TIMETOUT);
            sessionService.invalidateSession(sessionKey);
            return 3;
        } else {
            sessionService.setValue(sessionKey, "expire", currentTime + timeout + "");
        }*/

        String did = sessionService.getValue(Utils.getSessionKey(user.getId()), "deviceId");
        if (StringUtils.isBlank(did)) {
            DubboExtProperty
                    .setErrorCode(UserServiceHttpCode.UNAUTH_DEVICE);
            return 2;
        }

        if (!StringUtils.equalsIgnoreCase(did, deviceId + "")) {
            DubboExtProperty
                    .setErrorCode(UserServiceHttpCode.INACTIVE_DEVICE);
            return 4;
        }

        sessionService.setSessionTimeout(sessionKey, (int) (timeout / 1000));
        return 0;
    }
}
