/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.api;

import java.util.Map;

/**
 * Created by zou_s on 2016/12/28.
 */
public interface SessionService {
    /**
     * 获取sessionKe有的Map值
     *
     * @param sessionKey
     * @return
     */
    public Map<String, String> retrieveSession(String sessionKey);

    /**
     * 得到sessionKey中某个key的value
     *
     * @param sessionKey
     * @param key
     * @return
     */
    public String getValue(String sessionKey, String key);

    /**
     * 给sessionKey中某个key赋值
     *
     * @param sessionKey
     * @param key
     * @param value
     */
    public void setValue(String sessionKey, String key, String value);

    /**
     * 给sessionKey赋值
     *
     * @param sessionKey
     * @param values
     */
    public void setValues(String sessionKey, Map<String, String> values);

    /**
     * 使session作废
     *
     * @param sessionKey
     */
    public void invalidateSession(String sessionKey);

    /**
     * 使session作废
     *
     * @param domainId
     * @param userId
     */
    public void invalidateSession(int domainId, int userId);


    /**
     * 设置session过期时间
     *
     * @param sessionKey
     * @param seconds
     */
    public void setSessionTimeout(String sessionKey, int seconds);

    /**
     * 判断是否合法的访问
     *
     * @param sessionKey
     * @param clientIp
     * @param deviceId
     * @param appId
     * @return
     */
    public boolean isValidAccess(String sessionKey, String clientIp, long deviceId, long appId);

//    Object testBaseService();
}
