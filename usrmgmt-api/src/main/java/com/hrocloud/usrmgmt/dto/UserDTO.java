/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;
import com.hrocloud.usrmgmt.model.UserInfo;
import com.hrocloud.util.DateFormatStr;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zou_s on 2016/12/29.
 */
@Description("用户信息")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 5554766592925147303L;
    @Description("参数列表属性")
    public String valuelistItems;
    @Description("用户ID")
    public int id;
    @Description("用户登录名")
    public String loginName;
    @Description("用户密码")
    public String password;
    @Description("真实姓名")
    public String name;
    @Description("证件类型")
    public String idType;
    @Description("证件类型描述")
    public String idTypeDesc;
    @Description("证件类型列表")
    public List<ParamValueDTO> idTypeList;
    @Description("证件号码")
    public String idNo;
    @Description("出生日期")
    public String birthDate;
    @Description("性别")
    public String gender;
    @Description("性别描述")
    public String genderDesc;
    @Description("性别列表")
    public List<ParamValueDTO> genderList;
    @Description("手机号码")
    public String mobileNo;
    @Description("邮箱地址")
    public String email;
    @Description("用户状态")
    public String status;
    @Description("用户状态描述")
    public String statusDesc;
    @Description("最后登录角色类型")
    public String finalRoleType;
    @Description("最后登录角色类型描述")
    public String finalRoleTypeDesc;
    @Description("用户状态列表")
    public List<ParamValueDTO> statusList;
    @Description("备注")
    public String memo;
    @Description("最后登录时间")
    public long lastLogin;
    @Description("创建时间")
    public long createTime;
    @Description("最后更新时间")
    public long updateTime;

    UserDTO(){
        this.valuelistItems = "idType:idtype,gender:gender,status:userstatus";
    }
    public UserDTO(UserInfo userInfo) {
        this();
        this.id = userInfo.getId();
        this.password = userInfo.getPassword();
        this.email = userInfo.getEmail();
        this.gender = userInfo.getGender();
        this.birthDate = userInfo.getBirthDate() == null ? null : DateFormatUtils.format(userInfo.getBirthDate(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
        this.idType = userInfo.getIdType();
        this.idNo = userInfo.getIdNo();
        this.loginName = userInfo.getLoginName();
        this.name = userInfo.getName();
        this.memo = userInfo.getMemo();
        this.mobileNo = userInfo.getMobileNo();
        this.status = userInfo.getStatus();
        this.finalRoleType = userInfo.getFinalRoleType() == null ? "":userInfo.getFinalRoleType();
        this.lastLogin = userInfo.getLastLogin() == null ? 0 : userInfo.getLastLogin().getTime();
        this.createTime = userInfo.getCreateTime() == null ? 0 : userInfo.getCreateTime().getTime();
        this.updateTime = userInfo.getUpdateTime() == null ? 0 : userInfo.getUpdateTime().getTime();
    }
}
