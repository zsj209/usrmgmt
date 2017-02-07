/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.utils;

import com.hrocloud.apigw.client.define.CallerInfo;
import com.hrocloud.apigw.client.utils.AESTokenHelper;
import com.hrocloud.apigw.client.utils.AesHelper;
import com.hrocloud.apigw.client.utils.Base64Util;
import com.hrocloud.apigw.client.utils.Md5Util;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Stanley Zou on 2016/12/28
 */
public abstract class TokenHelper {


    private static final String KEY = "eqHSs48SCL2VoGsW1lWvDWKQ8Vu71UZJyS7Dbf/e4zo=";

    private static AesHelper helper = new AesHelper(Base64Util.decode(KEY), null);

    static {
        new SecurityInit().init();
    }

    public static String generateUserToken(int domainId, int companyId, int userId, int appId, long deviceId, long expire) {
        StringBuilder token = new StringBuilder();
        token.append(domainId).append("|").append(companyId).append("|").append(userId).append("|").append(System.currentTimeMillis()).append("|").append(appId).append("|").append(expire).append("|").append(deviceId);
        System.out.println("token:" + token);
        return Base64Util.encodeToString(helper.encrypt(token.toString().getBytes()));
    }

    public static String generateWtk(String token) {
        return Md5Util.computeToHex((token + "wtk.hrocloud.com").getBytes());
    }

    public static CallerInfo parseCallerFromUserToken(String token) {

        CallerInfo caller = null;

        if (StringUtils.isNotBlank(token)) {
            AESTokenHelper aesTokenHelper = new AESTokenHelper(new AesHelper(Base64Util.decode(KEY), null));
            caller = aesTokenHelper.parseUserToken(token);
            return caller;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

//        String token = generateUserToken(1000l,10101l,1l,-111l,-32432l,System.currentTimeMillis()+5*3600);
//        System.out.println("token:"+token);
//        String[] sourceData = new String(helper.decrypt(Base64Util.decode(token)), "UTF-8").split("\\|");
//        for(String str:sourceData){
//            System.out.print(str+"\t");
//        }
        System.out.println(parseCallerFromUserToken("a1TuQhg2z/D0NIoYQctU32yVvS6e3JXzm7j3NxSahgwKmBFvCSp6n00p3xtEv7y2RcD7YqUc9KU9ElExGr04bA=="));

    }


}
