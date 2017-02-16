/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.exception;

import com.hrocloud.apigw.client.define.AbstractReturnCode;

/**
 * Created by Stanley Zou on 2016/12/23.
 */
public class UserServiceHttpCode extends AbstractReturnCode {

    public final static int C_PASSWORD_ERROR = 1000;
    public final static AbstractReturnCode PASSWORD_ERROR = new UserServiceHttpCode("密码错误", C_PASSWORD_ERROR);
    public final static int C_LOGIN_FAILED = 1004;
    public final static AbstractReturnCode LOGIN_FAILED = new UserServiceHttpCode("用户登录失败", C_LOGIN_FAILED);
    public final static int C_USER_NOT_FOUND = 1002;
    public final static AbstractReturnCode USER_NOT_FOUND = new UserServiceHttpCode("用户不存在", C_USER_NOT_FOUND);
    public final static int C_RENEW_USERTOKEN_FAILED = 1006;
    public final static AbstractReturnCode RENEW_USERTOKEN_FAILED = new UserServiceHttpCode("用户获取token失败", C_RENEW_USERTOKEN_FAILED);
    public final static int C_PARAMETER_ERROR = 1007;
    public final static AbstractReturnCode PARAMETER_ERROR = new UserServiceHttpCode("参数错误", C_PARAMETER_ERROR);
    public final static int C_USER_EDITING = 1008;
    public final static AbstractReturnCode USER_EDITING = new UserServiceHttpCode("用户编辑中", C_USER_EDITING);
    public final static int C_USER_STOP = 1009;
    public final static AbstractReturnCode USER_STOP = new UserServiceHttpCode("用户已停用", C_USER_STOP);
    public final static int C_UNAUTH_DEVICE = 1010;
    public final static AbstractReturnCode UNAUTH_DEVICE = new UserServiceHttpCode("非授信设备", C_UNAUTH_DEVICE);
    public final static int C_INACTIVE_DEVICE = 1011;
    public final static AbstractReturnCode INACTIVE_DEVICE = new UserServiceHttpCode("非活跃设备", C_INACTIVE_DEVICE);
    public final static int C_SESSION_TIMETOUT = 1012;
    public final static AbstractReturnCode SESSION_TIMETOUT = new UserServiceHttpCode("会话已过期", C_SESSION_TIMETOUT);
    public final static int C_IP_INVALID = 1013;
    public final static AbstractReturnCode IP_INVALID = new UserServiceHttpCode("无效的IP地址", C_IP_INVALID);
    
    public final static int C_USER_NEWPASSWORD_ERROR = 1014;
    public final static AbstractReturnCode USER_NEWPASSWORD_ERROR = new UserServiceHttpCode("新密码不能为空，或格式不正确", C_USER_NEWPASSWORD_ERROR);
    
    public final static int C_USER_REPASSWORD_ERROR = 1015;
    public final static AbstractReturnCode USER_REPASSWORD_ERROR = new UserServiceHttpCode("两次密码不一致", C_USER_REPASSWORD_ERROR);
    public final static int C_USER_PASSWORDFORMAT_ERROR = 1016;
    public final static AbstractReturnCode USER_PASSWORDFORMAT_ERROR = new UserServiceHttpCode("密码格式不正确", C_USER_PASSWORDFORMAT_ERROR);
    public final static int C_USER_AGREEMENT_ERROR = 1017;
    public final static AbstractReturnCode USER_AGREEMENT_ERROR = new UserServiceHttpCode("请同意用户服务协议", C_USER_AGREEMENT_ERROR);
    public final static int C_USER_MOBILENO_ERROR = 1018;
    public final static AbstractReturnCode USER_MOBILENO_ERROR = new UserServiceHttpCode("手机号码格式不正确", C_USER_MOBILENO_ERROR);
    public final static int C_USER_EMAIL_ERROR = 1019;
    public final static AbstractReturnCode USER_EMAIL_ERROR = new UserServiceHttpCode("邮箱格式不正确", C_USER_EMAIL_ERROR);
    
    public final static int C_USER_EMAIL_EXIST = 1020;
    public final static AbstractReturnCode USER_EMAIL_EXIST = new UserServiceHttpCode("邮箱已经被使用", C_USER_EMAIL_EXIST);
    public final static int C_USER_MOBILENO_EXIST = 1021;
    public final static AbstractReturnCode USER_MOBILENO_EXIST = new UserServiceHttpCode("手机号已经被注册", C_USER_MOBILENO_EXIST);
    public final static int C_USER_REGISTER_FAIL = 1022;
    public final static AbstractReturnCode USER_REGISTER_FAIL = new UserServiceHttpCode("注册失败",C_USER_REGISTER_FAIL);
    public final static int C_USER_VERIFYMOBILECODE_INVALID = 1023;
    public final static AbstractReturnCode USER_VERIFYMOBILECODE_INVALID = new UserServiceHttpCode("手机验证码已失效",C_USER_VERIFYMOBILECODE_INVALID);
    public final static int C_USER_VERIFYMOBILECODE_ERROR = 1024;
    public final static AbstractReturnCode USER_VERIFYMOBILECODE_ERROR = new UserServiceHttpCode("手机验证码错误",C_USER_VERIFYMOBILECODE_ERROR);
    
    public final static int C_USER_VERIFYCAPCODE_INVALID = 1025;
    public final static AbstractReturnCode USER_VERIFYCAPCODE_INVALID = new UserServiceHttpCode("图形验证码已失效",C_USER_VERIFYCAPCODE_INVALID);
    public final static int C_USER_VERIFYCAPCODE_ERROR = 1026;
    public final static AbstractReturnCode USER_VERIFYCAPCODE_ERROR = new UserServiceHttpCode("图形验证码错误",C_USER_VERIFYCAPCODE_ERROR);
    
    
    public final static int C_USER_UPDATE_FAIL = 1027;
    public final static AbstractReturnCode USER_UPDATE_FAIL = new UserServiceHttpCode("更新用户失败",C_USER_UPDATE_FAIL);
    
    
  
    public final static int C_NODE_DELINFO_ERROR = 1028;
    public final static AbstractReturnCode NODE_DELINFO_ERROR = new UserServiceHttpCode("删除节点信息失败", C_NODE_DELINFO_ERROR);
    public final static int C_NODE_ADDINFO_ERROR = 1029;
    public final static AbstractReturnCode NODE_ADDINFO_ERROR = new UserServiceHttpCode("新增节点信息失败", C_NODE_ADDINFO_ERROR);
    public final static int C_NODE_MODIFYINFO_ERROR = 1030;
    public final static AbstractReturnCode NODE_MODIFYINFO_ERROR = new UserServiceHttpCode("修改节点信息失败", C_NODE_MODIFYINFO_ERROR);
    public final static int C_NODE_NONEXISTENT_ERROR = 1031;
    public final static AbstractReturnCode NODE_NONEXISTENT_ERROR = new UserServiceHttpCode("节点信息不存在", C_NODE_NONEXISTENT_ERROR);

    
    public final static int C_USER_ADDINFO_ERROR = 1032;
    public final static AbstractReturnCode USER_ADDINFO_ERROR = new UserServiceHttpCode("新增用户信息信息失败", C_USER_ADDINFO_ERROR);
    public final static int C_USER_MODIFYINFO_ERROR = 1033;
    public final static AbstractReturnCode USER_MODIFYINFO_ERROR = new UserServiceHttpCode("修改用户信息信息失败", C_USER_MODIFYINFO_ERROR);
    public final static int C_USER_DELINFO_ERROR = 1034;
    public final static AbstractReturnCode USER_DELINFO_ERROR = new UserServiceHttpCode("删除用户信息失败", C_USER_DELINFO_ERROR);
    
    public final static int C_USER_GETPERM_ERROR = 1035;
    public final static AbstractReturnCode USER_GETPERM_ERROR = new UserServiceHttpCode("获取用户权限失败", C_USER_GETPERM_ERROR);
    
    public final static int C_BUTTON_DELINFO_ERROR = 1036;
    public final static AbstractReturnCode BUTTON_DELINFO_ERROR = new UserServiceHttpCode("按钮信息删除失败", C_BUTTON_DELINFO_ERROR);
    public final static int C_BUTTON_MODIFYINFO_ERROR = 1037;
    public final static AbstractReturnCode BUTTON_MODIFYINFO_ERROR = new UserServiceHttpCode("按钮信息修改失败", C_BUTTON_MODIFYINFO_ERROR);
    public final static int C_BUTTON_ADDINFO_ERROR = 1038;
    public final static AbstractReturnCode BUTTON_ADDINFO_ERROR = new UserServiceHttpCode("按钮信息新增失败", C_BUTTON_ADDINFO_ERROR);
    public final static int C_BUTTON_NONEXISTENT_ERROR = 1039;
    public final static AbstractReturnCode BUTTON_NONEXISTENT_ERROR = new UserServiceHttpCode("按钮信息不存在", C_BUTTON_NONEXISTENT_ERROR);
    
    public final static int C_ROLE_NODE_IS_NULL = 1040;
    public final static AbstractReturnCode ROLE_NODE_IS_NULL = new UserServiceHttpCode("该角色没有对应的节点信息", C_ROLE_NODE_IS_NULL);
    public final static int C_ROLE_BUTTON_IS_NULL = 1041;
    public final static AbstractReturnCode ROLE_BUTTON_IS_NULL = new UserServiceHttpCode("该节点没有对应的按钮", C_ROLE_BUTTON_IS_NULL);

    public final static int C_ROLE_BUTTON_ADDINFO_ERROR = 1042;
    public final static AbstractReturnCode ROLE_BUTTON_ADDINFO_ERROR = new UserServiceHttpCode("节点按钮权限分配失败", C_ROLE_BUTTON_ADDINFO_ERROR);
    public final static int C_ROLE_BUTTON_MODIFYINFO_ERROR = 1043;
    public final static AbstractReturnCode ROLE_BUTTON_MODIFYINFO_ERROR = new UserServiceHttpCode("节点按钮权限修改", C_ROLE_BUTTON_MODIFYINFO_ERROR);
    
    public final static int C_USER_NONPERM_ERROR = 1044;
    public final static AbstractReturnCode USER_NONPERM_ERROR = new UserServiceHttpCode("用户未分配合适的岗位或角色", C_USER_NONPERM_ERROR);
    
    public final static int C_USER_NONNODE_ERROR = 1045;
    public final static AbstractReturnCode USER_NONNODE_ERROR = new UserServiceHttpCode("角色未分配节点权限", C_USER_NONNODE_ERROR);
    
    public final static int C_BUTTON_ASSIGNED_ERROR = 1046;
    public final static AbstractReturnCode BUTTON_ASSIGNED_ERROR = new UserServiceHttpCode("按钮已分配权限不能删除",C_BUTTON_ASSIGNED_ERROR);
    public final static int C_NODE_ASSIGNED_ERROR = 1047;
    public final static AbstractReturnCode NODE_ASSIGNED_ERROR = new UserServiceHttpCode("节点已分配权限不能删除",C_NODE_ASSIGNED_ERROR);
    
    public UserServiceHttpCode(String desc, int code) {
        super(desc, code);
    }

}
