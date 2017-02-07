/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.service;

import com.hrocloud.redis.RedisClientTemplate;
import com.hrocloud.usrmgmt.api.SessionService;
import com.hrocloud.usrmgmt.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zou_s on 2016/12/28.
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    @Value("${user.timeout}")
    private long timeout;

    @Resource
    private RedisClientTemplate redisClient;

    public Map<String, String> retrieveSession(String sessionKey) {
        return redisClient.hgetAll(sessionKey);
    }

    public String getValue(String sessionKey, String key) {
        return redisClient.hget(sessionKey, key);
    }

    public void setValue(String sessionKey, String key, String value) {
        redisClient.hset(sessionKey, key, value);
    }


    public void setValues(String sessionKey, Map<String, String> values) {
        redisClient.hmset(sessionKey, values);
    }

    public void invalidateSession(String sessionKey) {
        redisClient.del(sessionKey);
    }

    public void invalidateSession(int domainId, int userId) {
        redisClient.del(Utils.getSessionKey(domainId, userId));
    }

    public void setSessionTimeout(String sessionKey, int seconds) {
        redisClient.expire(sessionKey, seconds);
    }

    /**
     * @param sessionKey
     * @param clientIp
     * @param deviceId
     * @param appId
     * @return 判断是否是合法访问
     */
    public boolean isValidAccess(String sessionKey, String clientIp, long deviceId, long appId) {
        if (!clientIp.equals(getValue(sessionKey, "clientIp")))
            return false;
        if (deviceId != Long.parseLong(getValue(sessionKey, "deviceId")))
            return false;
        if (appId != Long.parseLong(getValue(sessionKey, "applicationId")))
            return false;

        long expireTime = Long.valueOf(redisClient.hget(sessionKey, "expire"));
        long currentTime = System.currentTimeMillis();
        if (expireTime < currentTime) {
            invalidateSession(sessionKey);
            return true;
        }
        setValue(sessionKey, "expire", currentTime + timeout + "");
        return false;
    }

   /* public Object testBaseService() {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setSql("select * from user_info where id=?");
        List<Object> lstParams= new ArrayList<Object>();
        lstParams.add(1001);
        baseInfo.setParams(lstParams);
        Object obj = baseService.selectOne(baseInfo);
        return obj;

    }*/
}
