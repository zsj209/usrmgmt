/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.api;

import com.hrocloud.common.api.model.BaseObj;

/**
 * Created by zou_s on 2017/1/6.
 */
public interface CommonModelService <T extends BaseObj>{
    int updateModel(int userId, T t);
    int insertModel(int userId, T t);

}
