/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.api;

import com.hrocloud.apigw.client.annoation.*;
import com.hrocloud.apigw.client.define.CommonParameter;
import com.hrocloud.apigw.client.define.SecurityType;
import com.hrocloud.usrmgmt.dto.*;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;

import java.util.List;

/**
 * Created by Stanley Zou on 2016/12/28
 */
@ApiGroup(name = "usrmgmt", minCode = 1000, maxCode = 1999, codeDefine = UserServiceHttpCode.class, owner = "Stanley")
public interface UserAgwService {
    /**
     * @param clientIp
     * @param applicationId 接入端口号
     * @param loginCode     登录名
     * @param password      密码
     * @param captchaCode   输入的验证码
     * @param capid         验证码下发的Id
     * @return
     */
    @HttpApi(name = "usrmgmt.login", desc = "用户登录", security = SecurityType.None)
    @DesignedErrorCode({UserServiceHttpCode.C_PASSWORD_ERROR,
            UserServiceHttpCode.C_LOGIN_FAILED,
            UserServiceHttpCode.C_USER_NOT_FOUND,
            UserServiceHttpCode.C_PARAMETER_ERROR,
            UserServiceHttpCode._C_USER_LOCKED,
            UserServiceHttpCode.C_USER_EDITING,
            UserServiceHttpCode.C_USER_STOP,
            UserServiceHttpCode.C_USER_VERIFYCAPCODE_INVALID,
            UserServiceHttpCode.C_USER_VERIFYCAPCODE_ERROR,
    })
    LoginRespDTO login(
            @ApiAutowired(CommonParameter.clientIp) String clientIp,
            @ApiParameter(required = true, name = "applicationId", desc = "接入端编号") int applicationId,
            @ApiParameter(required = true, name = "loginCode", desc = "登录名") String loginCode,
            @ApiParameter(required = true, name = "password", desc = "密码") String password,
            @ApiParameter(required = true, name = "captchaCode", desc = "图形验证码") String captchaCode,
            @ApiParameter(required = true, name = "captid", desc = "图形验证码id") String capid
    );

    /**
     * 用户登录后更改密码
     *
     * @param userId
     * @param password
     * @param newpwd1
     * @param newpwd2
     * @return
     */
    @HttpApi(name = "usrmgmt.changepass", desc = "更改密码", security = SecurityType.UserLogin)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_NEWPASSWORD_ERROR,
            UserServiceHttpCode.C_USER_NOT_FOUND,
            UserServiceHttpCode.C_USER_REPASSWORD_ERROR,
            UserServiceHttpCode.C_USER_PASSWORDFORMAT_ERROR
           })
    boolean changePassword(@ApiAutowired(CommonParameter.userId) int userId,
                           @ApiParameter(required = true, name = "password", desc = "原密码") String password,
                           @ApiParameter(required = true, name = "newpwd1", desc = "新密码第一次输入") String newpwd1,
                           @ApiParameter(required = true, name = "newpwd2", desc = "新密码第二次输入") String newpwd2);

    /**
     * @param mobileNo
     * @param email
     * @param verifyCode
     * @param verifyId
     * @param captchaCode
     * @param capid       下发的图形验证码的id
     * @param name
     * @param termAgreed  0-不同意 1-同意
     * @param newpwd1
     * @param newpwd2
     * @return
     */
    @HttpApi(name = "usrmgmt.register", desc = "注册用户", security = SecurityType.None)
    @DesignedErrorCode({UserServiceHttpCode.C_PASSWORD_ERROR,
    	UserServiceHttpCode.C_USER_AGREEMENT_ERROR,
    	UserServiceHttpCode.C_USER_MOBILENO_ERROR,
    	UserServiceHttpCode.C_USER_EMAIL_ERROR,
    	UserServiceHttpCode.C_USER_MOBILENO_EXIST,
    	UserServiceHttpCode.C_USER_EMAIL_EXIST,
    	UserServiceHttpCode.C_USER_REGISTER_FAIL,
    	UserServiceHttpCode.C_USER_VERIFYMOBILECODE_INVALID,
    	UserServiceHttpCode.C_USER_VERIFYMOBILECODE_ERROR
    })
    boolean registerUser(@ApiParameter(required = true, name = "mobileNo", desc = "手机号") String mobileNo,
                         @ApiParameter(required = true, name = "email", desc = "邮箱") String email,
                         @ApiParameter(required = true, name = "verifyCode", desc = "手机/或者邮箱验证码") String verifyCode,
                         @ApiParameter(required = true, name = "verifyid", desc = "下发的手机验证码的id") String verifyId,
                         @ApiParameter(required = true, name = "captchaCode", desc = "图形验证码") String captchaCode,
                         @ApiParameter(required = true, name = "captid", desc = "图形验证码id") String capid,
                         @ApiParameter(required = true, name = "name", desc = "真实姓名") String name,
                         @ApiParameter(required = true, name = "termAgreed", desc = "是否同意条款") int termAgreed,
                         @ApiParameter(required = true, name = "newpwd1", desc = "新密码第一次输入") String newpwd1,
                         @ApiParameter(required = true, name = "newpwd2", desc = "新密码第二次输入") String newpwd2);

    /**
     * 找回密码
     * @param loginCode
     * @param verifyCode
     * @param verifyId
     * @param captchaCode 图形验证码
     * @param capid       下发的图形验证码的id
     * @param newpwd1
     * @param newpwd2
     * @return
     */
    @HttpApi(name = "usrmgmt.getbackpwd", desc = "找回密码", security = SecurityType.None)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_VERIFYMOBILECODE_INVALID,
    	UserServiceHttpCode.C_USER_VERIFYMOBILECODE_ERROR,
    	UserServiceHttpCode.C_USER_VERIFYCAPCODE_INVALID,
    	UserServiceHttpCode.C_USER_VERIFYCAPCODE_ERROR,
    	UserServiceHttpCode.C_USER_REPASSWORD_ERROR,
    	UserServiceHttpCode.C_USER_PASSWORDFORMAT_ERROR,
    	UserServiceHttpCode.C_USER_NOT_FOUND})
    boolean getBackPassword(@ApiParameter(required = true, name = "loginCode", desc = "手机/邮箱/登录名") String loginCode,
                            @ApiParameter(required = true, name = "verifyCode", desc = "手机/或者邮箱验证码") String verifyCode,
                            @ApiParameter(required = true, name = "verifyid", desc = "下发的手机验证码的id") String verifyId,
                            @ApiParameter(required = true, name = "captchaCode", desc = "图形验证码") String captchaCode,
                            @ApiParameter(required = true, name = "captid", desc = "图形验证码id") String capid,
                            @ApiParameter(required = true, name = "newpwd1", desc = "新密码第一次输入") String newpwd1,
                            @ApiParameter(required = true, name = "newpwd2", desc = "新密码第二次输入") String newpwd2
    );

    /**
     * 更改手机号
     * @param userId
     * @param origMobileNo
     * @param newMobileNo
     * @param verifyCode
     * @param verifyId
     * @param captchaCode
     * @param capid        下发的图形验证码的id
     * @param password
     * @return
     */
    @HttpApi(name = "usrmgmt.changemobile", desc = "更改手机号", security = SecurityType.UserLogin)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_VERIFYMOBILECODE_INVALID,
    	UserServiceHttpCode.C_USER_VERIFYMOBILECODE_ERROR,
    	UserServiceHttpCode.C_USER_VERIFYCAPCODE_INVALID,
    	UserServiceHttpCode.C_USER_VERIFYCAPCODE_ERROR,
    	UserServiceHttpCode.C_USER_PASSWORDFORMAT_ERROR,
    	UserServiceHttpCode.C_USER_NOT_FOUND,
    	UserServiceHttpCode.C_USER_MOBILENO_ERROR,
    	UserServiceHttpCode.C_PASSWORD_ERROR
    })
    boolean changeMobile(@ApiAutowired(CommonParameter.userId) int userId,
                         @ApiParameter(required = true, name = "mobileno", desc = "原手机号") String origMobileNo,
                         @ApiParameter(required = true, name = "newMobileNo", desc = "新手机号") String newMobileNo,
                         @ApiParameter(required = true, name = "verifyCode", desc = "手机验证码") String verifyCode,
                         @ApiParameter(required = true, name = "verifyid", desc = "下发的手机验证码的id") String verifyId,
                         @ApiParameter(required = true, name = "captchaCode", desc = "图形验证码") String captchaCode,
                         @ApiParameter(required = true, name = "captid", desc = "下发的图形验证码id") String capid,
                         @ApiParameter(required = true, name = "password", desc = "密码") String password
    );

    /**
     * 登陆注销
     * @param clientIp
     * @param applicationId
     * @param userId
     * @return
     */
    @HttpApi(name = "usrmgmt.logout", desc = "登录注销", security = SecurityType.UserLogin)
    boolean logout(@ApiAutowired(CommonParameter.clientIp) String clientIp,
                   @ApiAutowired(CommonParameter.applicationId) int applicationId,
                   @ApiAutowired(CommonParameter.userId) int userId,
                   @ApiAutowired(CommonParameter.domainId) int domainId);

    /**
     * @param userId
     * @param data   json格式的用户对象
     * @return
     */
    @HttpApi(name = "usrmgmt.updateuser", desc = "更新用户信息", security = SecurityType.UserLogin)
    @DesignedErrorCode({
            UserServiceHttpCode.C_USER_UPDATE_FAIL,
            UserServiceHttpCode.C_PARAMETER_ERROR
    })
    boolean updateUser(@ApiAutowired(CommonParameter.userId) int userId,
                       @ApiParameter(required = true, name = "data", desc = "json格式的用户对象") String data);

    /**
     * @param userId
     * @param purpose 获取用户的用途 0 - 仅获取表格内容 1 - 编辑用途 2 - 显示用途
     * @return
     */
    @HttpApi(name = "usrmgmt.getloginuser", desc = "得到当前登录用户的信息", security = SecurityType.UserLogin)
    @DesignedErrorCode({
            UserServiceHttpCode.C_USER_NOT_FOUND})
    UserDTO getLoginUser(@ApiAutowired(CommonParameter.userId) int userId,
                         @ApiParameter(required = true, name = "purpose", desc = "获取方式, 0-显示内容 1-编辑 2-显示") int purpose);

    /**
     * @param userId
     * @param purpose 获取用户的方式 0 - 仅获取表格原始数据 1 - 获取参数列表详细数据
     * @return
     */
    @HttpApi(name = "usrmgmt.allusers", desc = "得到所有用户", security = SecurityType.UserLogin)
    @DesignedErrorCode({
            UserServiceHttpCode.C_USER_NOT_FOUND
            })
    ListUserDTO getAllUser(@ApiAutowired(CommonParameter.userId) int userId, @ApiParameter(required = true, name = "method", desc = "获取方式, 0 - 仅获取表格原始数据 1 - 获取参数列表详细数据") int purpose);

    /**
     * @return
     */
    //TODO:更新错误码
    @HttpApi(name = "usrmgmt.getparamlist", desc = "根据参数类型获取参数列表", security = SecurityType.None)
    @DesignedErrorCode({UserServiceHttpCode.C_PASSWORD_ERROR,
            UserServiceHttpCode.C_LOGIN_FAILED,
            UserServiceHttpCode.C_USER_NOT_FOUND,
            UserServiceHttpCode.C_PARAMETER_ERROR,
            UserServiceHttpCode._C_USER_LOCKED,
            UserServiceHttpCode.C_USER_EDITING,
            UserServiceHttpCode.C_USER_STOP})
    List<ParamValueDTO> getParamList(@ApiParameter(required = true, name = "typecode", desc = "值列表的编码标识") String typeCode);

    //TODO:更新错误码
    @HttpApi(name = "usrmgmt.getparamlistgroup", desc = "根据参数类型列表获取参数列表组", security = SecurityType.None)
    @DesignedErrorCode({UserServiceHttpCode.C_PASSWORD_ERROR,
            UserServiceHttpCode.C_LOGIN_FAILED,
            UserServiceHttpCode.C_USER_NOT_FOUND,
            UserServiceHttpCode.C_PARAMETER_ERROR,
            UserServiceHttpCode._C_USER_LOCKED,
            UserServiceHttpCode.C_USER_EDITING,
            UserServiceHttpCode.C_USER_STOP})
    List<ParamGroupDTO> getParamList(@ApiParameter(required = true, name = "paramtypelist", desc = "值列表的编码标识列表") List<String> paramTypeList);

    /**
     * 根据用户类型获取用户列表
     *
     * @param rows 用户类型
     * @return
     */
    //TODO:更新错误码
    @HttpApi(name = "usrmgmt.userpage", desc = "根据用户类型获取用户列表", security = SecurityType.UserLogin)
    @DesignedErrorCode({
        UserServiceHttpCode.C_USER_NOT_FOUND})
    PageDTO getUserList(
    		//@ApiParameter(required = false, name = "data", desc = "查询条件") String data,
    		@ApiParameter(required = false, name = "status", desc = "用户状态") String status,
    		@ApiParameter(required = false, name = "nameOrMobile", desc = "用户名称或手机号") String nameOrMobile,
            @ApiParameter(required = true, name = "rows", desc = "每页显示条数") int rows,
            @ApiParameter(required = true, name = "page", desc = "当前页") int page);

    /**
     * 增加或修改用户信息
     * @param data
     * @return
     */
    @HttpApi(name = "usrmgmt.addOrModifyUser", desc = "新增或修改用户", security = SecurityType.UserLogin)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_MODIFYINFO_ERROR,
            UserServiceHttpCode.C_USER_ADDINFO_ERROR,
            UserServiceHttpCode.C_PARAMETER_ERROR        
    })
    boolean addOrModifyUser(
            @ApiParameter(required = true, name = "data", desc = "json字符串") String data);
    /**
     * 更具用户id获取用户信息
     * @param id
     * @param prurpose 
     * @return
     */
    @HttpApi(name = "usrmgmt.getuserbyid", desc = "根据用户id获取用户信息", security = SecurityType.UserLogin)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_NOT_FOUND})
    UserDTO getUserById(
    		@ApiParameter(required = true, name = "id", desc = "用户id") int id,
    		@ApiParameter(required = true, name = "purpose", desc = "json字符串") int prurpose);
    /**
     * 根据用户id删除用户
     * @return
     */
    @HttpApi(name = "usrmgmt.deleteuser", desc = "根据id删除用户", security = SecurityType.UserLogin)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_DELINFO_ERROR})
    boolean deleteUser(
    		@ApiParameter(required = true, name = "ids", desc = "用户id数组") String ids);
    /**
     * 根据用户id批量改变用户状态    
     * @param ids
     * @param status
     * @return
     */
    @HttpApi(name = "usrmgmt.modifystatus", desc = "根据id改变用户状态", security = SecurityType.UserLogin)
    @DesignedErrorCode({UserServiceHttpCode.C_USER_MODIFYINFO_ERROR})
    boolean modifyStatus(
    		@ApiParameter(required = true, name = "ids", desc = "用户id数组") String ids,
    		@ApiParameter(required = true, name = "status", desc = "状态") String status);

    /**
	 * @param userId 用户ID
	 * @param ruletype 角色类型，如果不为空，则获取指定角色类型的权限
	 * @return
	 */
	@HttpApi(name = "usrmgmt.getuserperm", desc = "获得用户权限", security = SecurityType.UserLogin)
	@DesignedErrorCode({ UserServiceHttpCode.C_USER_GETPERM_ERROR,
			UserServiceHttpCode.C_USER_NONPERM_ERROR })
	UserPermissionDTO getUserPerm(
			@ApiAutowired(CommonParameter.userId) int userId,
			@ApiParameter(required = false, name = "roletype", desc = "角色类型") String roleType);
	/**
	 * 修改用户最后登录的用户角色类型
	 * @param userId
	 * @param roleType
	 * @return
	 */
	@HttpApi(name = "usrmgmt.updfianlroletpye", desc = "更新最后登录的用户角色类型", security = SecurityType.UserLogin)
	int updateFianlRoleTpye(
			@ApiAutowired(CommonParameter.userId) int userId,
			@ApiParameter(required = true, name = "roletype", desc = "角色类型") String roleType);
	
	/**
	 * @param userId 用户ID
	 * @param ruletype 角色类型
	 * @return
	 */
	@HttpApi(name = "usrmgmt.getusermenu", desc = "获得用户功能菜单", security = SecurityType.UserLogin)
	@DesignedErrorCode({ UserServiceHttpCode.C_USER_GETPERM_ERROR,
			UserServiceHttpCode.C_USER_NONNODE_ERROR })
	List<Menu> getUserMenu(
			@ApiAutowired(CommonParameter.userId) int userId,
			@ApiParameter(required = true, name = "roletype", desc = "角色类型") String roleType);

}
