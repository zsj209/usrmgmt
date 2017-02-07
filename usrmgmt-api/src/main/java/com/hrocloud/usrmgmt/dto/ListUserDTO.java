/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;
import com.hrocloud.usrmgmt.model.UserInfo;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zou_s on 2016/12/29.
 */
@Description("用户列表信息")
public class ListUserDTO implements Serializable {

    private static final long serialVersionUID = 8007660898608030077L;

    @Description("用户对象列表")
    public List<UserDTO> userList;

    @Description("参数列表属性，格式为 属性名称：对应的参数类型")
    public String valuelistItems;

    @Description("用户状态列表")
    public List<ParamValueDTO> statusList;

    @Description("性别列表")
    public List<ParamValueDTO> genderList;

    @Description("证件类型列表")
    public List<ParamValueDTO> idTypeList;

    public ListUserDTO(){
        this.valuelistItems = "idType:idtype,gender:gender,status:userstatus";
    }

}
