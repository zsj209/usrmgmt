/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.model;

import com.hrocloud.common.api.model.BaseObj;

import java.util.Date;

/**
 * Created by Stanley Zou on 2016/12/28
 * The entity of Captcha Image
 */
public class CaptchaImage extends BaseObj {

    private static final long serialVersionUID = -1426017163517988387L;

    private Integer id;

    private String value;

    private String path;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}