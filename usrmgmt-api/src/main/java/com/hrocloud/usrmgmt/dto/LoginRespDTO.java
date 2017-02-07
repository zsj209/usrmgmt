/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;

import java.io.Serializable;

/**
 * Created by zou_s on 2016/12/28.
 */
@Description("登录结果")
public class LoginRespDTO implements Serializable {
    private static final long serialVersionUID = -4981409360799351710L;

    @Description("登录凭据")
    public String token;

    @Description("webToken,登陆用户签名用")
    public String webToken;

    @Description("设备id")
    public long deviceId;

    @Description("过期时间")
    public long expire;

    @Description("用户id")
    public int uid;

    @Override
    public String toString() {
        return "LoginResp{" +
                "token='" + token + '\'' +
                ", webToken=" + webToken +
                ", deviceId='" + deviceId + '\'' +
                ", expire=" + expire +
                ", uid=" + uid +
                '}';
    }
}
