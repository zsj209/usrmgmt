/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.utils;

import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;

/**
 * Created by Stanley Zou on 2016/12/28
 */
public class VerifyHelper {

    /**
     * 是否合法密码
     *
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
    	return true;
    }

    /**
     * 是否合法身份证号码
     *
     * @return
     */
    public static boolean isValieIdCard(String idCardNo) {
        return true;
    }

    /**
     * 是否合法手机号码
     *
     * @param mobileNo
     * @return
     */
    public static boolean isValidMobileNo(String mobileNo) {
        return true;
    }

    /**
     * 密码验证
     *
     * @param pass1
     * @param pass2
     * @return
     */
    public static String verifiedPass(String pass1, String pass2) {
        if (!pass1.equals(pass2)) {
            // 请调整错误码，两个密码不一致
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_REPASSWORD_ERROR);
            return null;
        }

        if (!VerifyHelper.isValidPassword(pass1)) {
            // 请调整错误码，密码不符合要求
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_PASSWORDFORMAT_ERROR);
            return null;
        }

        return pass1;
    }

    /**
     * 是否合法的手机或邮箱验证码
     *
     * @param verifyCode
     * @return
     */
    public static boolean validVerifyCode(String verifyCode) {
        if (verifyCode.equals("2345"))
            return true;
        return false;
    }

    /**
     * 是否合法的图形动态验证码
     *
     * @param captchaCode
     * @return
     */
    public static boolean validCaptchCode(String captchaCode) {
        if (captchaCode.equals("6789"))
            return true;
        return false;
    }

    /**
     * 是否合法的邮箱地址
     *
     * @param mobileNo
     * @return
     */
    public static boolean isValidEmail(String mobileNo) {
        return true;
    }
}
