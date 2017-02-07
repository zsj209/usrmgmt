/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.service;


import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.common.api.CommParamInfoService;
import com.hrocloud.common.exception.BusinessException;
import com.hrocloud.common.page.PageParameter;
import com.hrocloud.redis.RedisClientTemplate;
import com.hrocloud.usrmgmt.api.SessionService;
import com.hrocloud.usrmgmt.api.UserAgwService;
import com.hrocloud.usrmgmt.api.UserService;
import com.hrocloud.usrmgmt.constant.UserStatus;
import com.hrocloud.usrmgmt.constant.ValueListDefine;
import com.hrocloud.usrmgmt.dto.*;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.UserInfo;
import com.hrocloud.usrmgmt.utils.TokenHelper;
import com.hrocloud.usrmgmt.utils.Utils;
import com.hrocloud.usrmgmt.utils.VerifyHelper;
import com.hrocloud.util.AppDomain;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.*;

/**
 * Created by Stanley Zou on 2016/12/28
 */
@Service("userAgwService")
public class UserAgwServiceImpl implements UserAgwService {
    private Logger logger = LoggerFactory.getLogger(UserAgwServiceImpl.class);
    @Value("${user.defaultCompany}")
    private int defaultCompanyId;
    @Value("${user.timeout}")
    private long timeout;
    @Resource
    private RedisClientTemplate redisClient;
    @Resource
    private UserService userService;
    @Resource
    private SessionService sessionService;

    @Resource
    private CommParamInfoService commParamInfoService;

    /**
     * @param clientIp
     * @param applicationId 接入端口号
     * @param loginCode     登录名
     * @param password      密码
     * @param captchaCode   输入的验证码
     * @param capId         验证码下发的Id
     * @return
     */
    public LoginRespDTO login(String clientIp, int applicationId, String loginCode, String password, String captchaCode, String capId) {
        if (StringUtils.isBlank(loginCode) || StringUtils.isBlank(password)) {
            logger.error(
                    "parameter(s) cannot be null, login code was {}, password was {}.",
                    loginCode, password);
            DubboExtProperty.setErrorCode(UserServiceHttpCode.PARAMETER_ERROR);
            return null;
        }

        if (!verifyCapCode(capId, captchaCode)) {
            return null;
        }

        UserInfo userInfo = userService.getUser(loginCode);
        if (userInfo == null) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
            return null;
        }

        if (!password.equals(userInfo.getPassword())) {
            logger.error("password error");
            DubboExtProperty.setErrorCode(UserServiceHttpCode.PASSWORD_ERROR);
            return null;
        }

        if (userInfo.getStatus().equalsIgnoreCase(UserStatus.EDITING.getCode())) { // 用户编辑中
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_EDITING);
            return null;
        }
        if (userInfo.getStatus().equalsIgnoreCase(UserStatus.DISABLED.getCode())) { // 用户已停用
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_STOP);
            return null;
        }

        LoginRespDTO loginResp = new LoginRespDTO();
        int domainId = AppDomain.appOf(applicationId).getDomainId();

        loginResp.expire = System.currentTimeMillis() + timeout;
        loginResp.uid = userInfo.getId();
        loginResp.deviceId = Utils.genDeviceId();
        loginResp.token = TokenHelper.generateUserToken(domainId, defaultCompanyId,
                loginResp.uid, applicationId, loginResp.deviceId,
                loginResp.expire);
        loginResp.webToken = TokenHelper.generateWtk(loginResp.token);


        Map<String, String> sessionMap = new HashMap<String, String>();
        sessionMap.put("domainId", domainId + "");
        sessionMap.put("userId", loginResp.uid + "");
        sessionMap.put("expire", loginResp.expire + "");
        sessionMap.put("deviceId", loginResp.deviceId + "");
        sessionMap.put("clientIp", clientIp);
        sessionMap.put("applicationId", applicationId + "");

        userInfo = new UserInfo();
        userInfo.setId(loginResp.uid);
        userInfo.setLastLogin(new Date());
        userService.update(userInfo);

        String sessionKey = Utils.getSessionKey(domainId, userInfo.getId());
        sessionService.setValues(sessionKey, sessionMap);
        sessionService.setSessionTimeout(sessionKey, (int) (timeout / 1000));
        DubboExtProperty.setCookieToken(loginResp.token);
        return loginResp;
    }

    /**
     * 登录后重置密码
     *
     * @param userId
     * @param password
     * @param newpwd1
     * @param newpwd2
     * @return
     */
    public boolean changePassword(int userId, String password, String newpwd1, String newpwd2) {
        if (StringUtils.isBlank(newpwd1) || StringUtils.isBlank(newpwd2)) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NEWPASSWORD_ERROR);
            return false;
        }
        String newpassword = VerifyHelper.verifiedPass(newpwd1, newpwd2);
        UserInfo userInfo = userService.getUserById(userId);
        if (userInfo == null) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
            return false;
        }
        userInfo.setPassword(newpassword);
        userService.update(userInfo);
        return true;
    }

    /**
     * @param mobileNo
     * @param email
     * @param verifyCode
     * @param captchaCode
     * @param capId
     * @param name
     * @param termAgreed  0-不同意 1-同意
     * @param newpwd1
     * @param newpwd2
     * @return
     */
    public boolean registerUser(String mobileNo, String email, String verifyCode, String verifyId, String captchaCode, String capId, String name, int termAgreed, String newpwd1, String newpwd2) {
        if (termAgreed == 0) {
            // 需要同意条款
            //添加并修改错误码
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_AGREEMENT_ERROR);
            return false;
        }
        String password = VerifyHelper.verifiedPass(newpwd1, newpwd2);
        if (password == null)
            return false;
        if (!VerifyHelper.isValidMobileNo(mobileNo)) {
            //添加并修改错误码
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_MOBILENO_ERROR);
            return false;
        }
        if (!VerifyHelper.isValidEmail(email)) {
            //TODO
            //添加并修改错误码
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_EMAIL_ERROR);
            return false;
        }

        if (!verifyMobileCode(verifyId, verifyCode))
            return false;

      /*  if (!VerifyHelper.validCaptchCode(captchaCode)) {
            //TODO
            //添加并修改错误码
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
            return false;
        }*/

        if (!verifyCapCode(capId, captchaCode))
            return false;

        //判断用户是否已经注册完成
        UserInfo user = userService.getUser(mobileNo);
        if (user != null) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_MOBILENO_EXIST);
            return false;
        }

        user = userService.getUser(email);

        if (user != null) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_EMAIL_EXIST);
            return false;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setStatus("02valid");
        // 证件类型 - 默认为身份证
        userInfo.setIdType("01id");
        userInfo.setPassword(password);
        userInfo.setCreateTime(new Date());
        userInfo.setEmail(email);
        userInfo.setMobileNo(mobileNo);
        userInfo.setLoginName(mobileNo);
        userInfo.setUpdateTime(userInfo.getCreateTime());
        userInfo.setName(name);
        int result = userService.insert(userInfo);
        if (result != 1) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_REGISTER_FAIL);
            return false;
        }
        return true;
    }

    /**
     * 未登录找回密码
     *
     * @param loginCode   登录用手机号/邮箱/登录名
     * @param verifyCode  通过手机/邮箱获取的验证码
     * @param verifyId
     * @param captchaCode 图形验证码
     * @param capId
     * @param newpwd1
     * @param newpwd2
     * @return
     */
    public boolean getBackPassword(String loginCode, String verifyCode, String verifyId, String captchaCode, String capId, String newpwd1, String newpwd2) {
        if (!verifyMobileCode(verifyId, verifyCode))
            return false;

        if (!verifyCapCode(capId, captchaCode))
            return false;

        String password = VerifyHelper.verifiedPass(newpwd1, newpwd2);
        if (password == null)
            return false;

        UserInfo userInfo = userService.getUser(loginCode);
        if (userInfo == null) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
            return false;
        }

        userInfo.setPassword(password);
        userService.update(userInfo);
        return true;
    }

    /**
     * 更改手机号
     *
     * @param userId
     * @param origMobileNo
     * @param newMobileNo
     * @param captchaCode
     * @param capId
     * @param password
     * @return
     */
    public boolean changeMobile(int userId, String origMobileNo, String newMobileNo, String verifyCode, String verifyId, String captchaCode, String capId, String password) {
        if (!VerifyHelper.isValidMobileNo(newMobileNo) || !VerifyHelper.isValidMobileNo(origMobileNo)) {
            //添加并修改错误码
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_MOBILENO_ERROR);
            return false;
        }

        if (!verifyMobileCode(verifyId, verifyCode))
            return false;

        if (!verifyCapCode(capId, captchaCode))
            return false;

        UserInfo userInfo = userService.getUserById(userId);
        if (userInfo == null) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
            return false;
        }
        if (!password.equals(userInfo.getPassword())) {
            logger.error("password error");
            DubboExtProperty.setErrorCode(UserServiceHttpCode.PASSWORD_ERROR);
            return false;
        }
        userInfo.setMobileNo(newMobileNo);
        userService.update(userInfo);
        return true;
    }


    /**
     * @param clientIp
     * @param applicationId
     * @param userId
     * @param domainId
     * @return
     */
    public boolean logout(String clientIp, int applicationId, int userId, int domainId) {
        DubboExtProperty.setCookieToken("");
        sessionService.invalidateSession(domainId, userId);
        return true;
    }

    public boolean updateUser(int userId, String userObj) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
        UserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(userObj, UserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            DubboExtProperty.setErrorCode(UserServiceHttpCode.PARAMETER_ERROR);
        }
        if (userInfo != null) {
            userInfo.setId(userId);
            userInfo.setUpdateTime(new Date());
            int update = userService.update(userInfo);
            if (update != 1) {
                DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_UPDATE_FAIL);
                return false;
            }
            return true;
        }
        return false;
    }

    public UserDTO getLoginUser(int userId, int purpose) {
        UserInfo userInfo = userService.getUserById(userId);
        if (userInfo == null) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
            return null;
        }
        UserDTO userDTO = new UserDTO(userInfo);


        if (purpose == 1 || purpose == 2) {
            boolean allItem = true;
            if (purpose == 2)
                allItem = false;
//            List<UserStatus> list = Utils.buildListFromEnum(UserStatus.class);
            userDTO.statusList = Utils.buildAgwParamList("pubstatus", ValueListDefine.class, commParamInfoService, userDTO.status, allItem);
            userDTO.idTypeList = Utils.buildAgwParamList("idtype", ValueListDefine.class, commParamInfoService, userDTO.status, allItem);
            userDTO.genderList = Utils.buildAgwParamList("gender", ValueListDefine.class, commParamInfoService, userDTO.gender, allItem);

            userDTO.idTypeDesc = Utils.getParamValueDesc(userInfo.getIdType(), userDTO.idTypeList);
            userDTO.genderDesc = Utils.getParamValueDesc(userInfo.getGender(), userDTO.genderList);
            userDTO.statusDesc = Utils.getParamValueDesc(userInfo.getStatus(), userDTO.statusList);
            if(userInfo.getFinalRoleType() != null && userInfo.getFinalRoleType() != ""){
            	userDTO.finalRoleTypeDesc = Utils.getParamValueDesc(userInfo.getFinalRoleType(), 
            			Utils.buildAgwParamList("bustype", ValueListDefine.class, commParamInfoService, userDTO.status, allItem)
            			);
            }
        }
        return userDTO;
    }


    public ListUserDTO getAllUser(int userId, int method) {

        List<UserInfo> userList = userService.queryUser("1=1");
        if (CollectionUtils.isEmpty(userList)) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
            return null;
        }

        ListUserDTO listUserDTO = new ListUserDTO();
        if (method == 1) {
//            List<UserStatus> list = Utils.buildListFromEnum(UserStatus.class);
            listUserDTO.statusList = Utils.buildAgwParamList("userstatus", ValueListDefine.class, commParamInfoService, "00", true);
            listUserDTO.idTypeList = Utils.buildAgwParamList("idtype", ValueListDefine.class, commParamInfoService, "00", true);
            listUserDTO.genderList = Utils.buildAgwParamList("gender", ValueListDefine.class, commParamInfoService, "00", true);
//            List<Map<String, Object>>
//            for
        }

        List<UserDTO> udList = new ArrayList<UserDTO>();
        for (UserInfo user : userList) {
            UserDTO userDTO = new UserDTO(user);
            if (method == 1) {
                userDTO.idTypeDesc = Utils.getParamValueDesc(user.getIdType(), listUserDTO.idTypeList);
                userDTO.genderDesc = Utils.getParamValueDesc(user.getGender(), listUserDTO.genderList);
                userDTO.statusDesc = Utils.getParamValueDesc(user.getStatus(), listUserDTO.statusList);
            }
            udList.add(userDTO);
        }
        listUserDTO.userList = udList;


        return listUserDTO;
    }

    public List<ParamValueDTO> getParamList(String paramTypeCode) {


        List<ParamValueDTO> list = Utils.buildAgwParamList(paramTypeCode, ValueListDefine.class, commParamInfoService, "", true);

        return list;
    }

    public List<ParamGroupDTO> getParamList(List<String> paramTypeList) {
        List<ParamGroupDTO> result = new ArrayList<ParamGroupDTO>();
        for (String paramTypeCode : paramTypeList) {
            List<ParamValueDTO> list = Utils.buildAgwParamList(paramTypeCode, ValueListDefine.class, commParamInfoService, "", true);
            ParamGroupDTO paramGroup = new ParamGroupDTO();
            paramGroup.typeCode = paramTypeCode;
            paramGroup.paramList = list;
            result.add(paramGroup);
        }
        return result;
    }

    /**
     * 校验图形验证码
     *
     * @param capId
     * @param captchaCode
     * @return
     */
    private boolean verifyCapCode(String capId, String captchaCode) {
        String capValue = redisClient.get(capId);
        if (StringUtils.isEmpty(capValue)) {
            // 验证码已过期
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_VERIFYCAPCODE_INVALID);
            return false;
        }
        if (!capValue.equalsIgnoreCase(captchaCode)) {
            // 验证码不正确
            // 添加并修改错误码
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_VERIFYCAPCODE_ERROR);
            return false;
        }
        return true;
    }

    /**
     * 校验手机或邮箱验证码
     *
     * @param codeKey
     * @param inputCode
     * @return
     */
    private boolean verifyMobileCode(String codeKey, String inputCode) {
        String codeValue = redisClient.hget(codeKey, "verifyCode");
        if (StringUtils.isEmpty(codeValue)) {
            // 验证码已过期
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_VERIFYMOBILECODE_INVALID);
            return false;
        }
        if (!inputCode.equalsIgnoreCase(codeValue)) {
            //验证码不正确
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_VERIFYMOBILECODE_ERROR);
            return false;
        }
        return true;
    }


    @SuppressWarnings("unchecked")
    public PageDTO getUserList(String status, String nameOrMobile, int rows, int page) {

        //HashMap<String, String> dataMap = (HashMap<String, String>) JSON.parseObject(data, Map.class);
        HashMap<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("nameOrMobile", nameOrMobile);
        dataMap.put("status", status);

        PageParameter pageInfo = new PageParameter();
        pageInfo.setPageSize(rows);
        pageInfo.setCurrentPage(page);
        List<UserInfo> userList = userService.getUserList(dataMap, pageInfo);

        if (CollectionUtils.isEmpty(userList)) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_NOT_FOUND);
            return null;
        }

        List<UserDTO> udList = new ArrayList<UserDTO>();
        for (UserInfo user : userList) {
            UserDTO userDTO = new UserDTO(user);
            userDTO.idTypeDesc = Utils.getParamValueDesc(user.getIdType(), Utils.buildAgwParamList("idtype", ValueListDefine.class, commParamInfoService, "00", true));
            userDTO.genderDesc = Utils.getParamValueDesc(user.getGender(), Utils.buildAgwParamList("gender", ValueListDefine.class, commParamInfoService, "00", true));
            userDTO.statusDesc = Utils.getParamValueDesc(user.getStatus(), Utils.buildAgwParamList("pubstatus", ValueListDefine.class, commParamInfoService, "00", true));
            udList.add(userDTO);
        }

        PageDTO pageDTO = new PageDTO();
        pageDTO.rows = udList;
        pageDTO.page = pageInfo.getCurrentPage();
        pageDTO.total = pageInfo.getTotalPage();
        pageDTO.records = pageInfo.getTotalCount();
        return pageDTO;


    }

    public boolean addOrModifyUser(String data) {
        boolean user = userService.addOrModifyUser(data);
        return user;
    }


    public boolean deleteUser(String ids) {
        boolean deleteUser = userService.deleteUser(ids);
        return deleteUser;
    }

    public boolean modifyStatus(String ids, String status) {
        boolean modifyStatus = userService.modifyStatus(ids, status);
        return modifyStatus;
    }


    public UserDTO getUserById(int id, int prurpose) {
        return this.getLoginUser(id, prurpose);

    }

    public UserPermissionDTO getUserPerm(int userId, String roleType) {
        try {
            logger.info("------------------>getUserPerm");
            UserPermissionDTO uspmdto = userService.getUserPerm(userId, roleType);
            if (uspmdto == null) {
                DubboExtProperty
                        .setErrorCode(UserServiceHttpCode.USER_NONPERM_ERROR);
                return null;
            }
            return uspmdto;
        } catch (BusinessException e) {
            DubboExtProperty
                    .setErrorCode(UserServiceHttpCode.USER_GETPERM_ERROR);
            return null;
        }
    }

	public int updateFianlRoleTpye(int userId, String roletype) {
		return userService.modifyFinalRoleType(userId,roletype);
	}
	
	public List<Menu> getUserMenu(int userId, String roleType) {
		try {
			logger.info("------------------>getUserMenu");
			List<Menu> nodelist = userService.getUserMenu(userId, roleType);
			if (nodelist == null) {
				DubboExtProperty
						.setErrorCode(UserServiceHttpCode.USER_NONNODE_ERROR);
				return null;
			}
			return nodelist;
		} catch (BusinessException e) {
			DubboExtProperty
					.setErrorCode(UserServiceHttpCode.USER_GETPERM_ERROR);
			return null;
		}
	}
}
