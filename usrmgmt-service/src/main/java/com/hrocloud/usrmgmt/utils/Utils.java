/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.utils;

import com.hrocloud.common.api.CommParamInfoService;
import com.hrocloud.common.api.model.ParamValue;
import com.hrocloud.usrmgmt.constant.UserStatus;
import com.hrocloud.usrmgmt.constant.ValueListDefine;
import com.hrocloud.usrmgmt.dto.ParamValueDTO;
import com.hrocloud.util.CommonUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Stanley Zou on 2016/12/28
 */
public class Utils {

    private static final long DEFAULT_DOMAIN_ID = 1000;

    public static long genDeviceId() {
        return -(System.currentTimeMillis() * 1000 + new Random().nextInt(999));
    }

    public static String getSessionKey(int domainId, int userId) {
        return domainId + "_" + userId;
    }

    public static long getDomainId(long appId) {
        return DEFAULT_DOMAIN_ID;
    }

    public static String getSessionKey(long userId) {
        return DEFAULT_DOMAIN_ID + "_" + userId;
    }

    /**
     * build the list according to the enum value
     *
     * @param listCode
     * @param definecls 定义枚举的类
     * @param t
     * @param allItem   check if get all the allItem, or just the checked item
     * @return
     */
    public static <T> List<ParamValueDTO> buildAgwParamList(String listCode, Class definecls, CommParamInfoService commParamInfoService, T t, boolean allItem) {
        List<ParamValueDTO> list = new ArrayList<ParamValueDTO>();
        List<ParamValue> listVL = null;
        if (t instanceof String)
            listVL = CommonUtil.getValueList(commParamInfoService, listCode, definecls, (String) t, allItem);
        else if (t instanceof Integer)
            listVL = CommonUtil.getValueList(listCode, definecls, (Integer) t, allItem);
        else
            return null;

        if (listVL == null)
            return null;
        for (ParamValue vl : listVL) {
            list.add(new ParamValueDTO(vl));
        }
        return list;
    }


    /**
     * @param paramValue
     * @param pvList
     * @return
     */
    public static String getParamValueDesc(String paramValue, List<ParamValueDTO> pvList) {
        if (paramValue == null || pvList == null)
            return null;
        for (ParamValueDTO pv : pvList) {
            if (pv.code.equalsIgnoreCase(paramValue)) {
                return pv.desc;
            }
        }
        return null;
    }


    public static void main(String[] args) {
     /*   System.out.println(CommonUtil.getUUID());
        for (UserStatus status : UserStatus.values()) {
            System.out.println(status.getKey());
            System.out.println(status.toString());Define
            System.out.println(status.getDesc());
        }
        List<ValueListDTO> list = CommonUtil.buildListFromEnum(UserStatus.class, 1, true);*/


        try {
            DateUtils.parseDate("1928-01-01", Locale.CHINESE, "yyyy-MM-dd");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserStatus.keyOf(1).getDesc();

        List<ParamValue> list = CommonUtil.getValueList("userstatus", ValueListDefine.class, 1, true);

        String userJson = "{\"name\":\"张\",\"gender\":\"2\",\"birthDate\":\"1991-09-08\"}";
        userJson = "{\"name\":\"超级管理员\",\"idNo\":\"340321199210013799\",\"gender\":\"1\",\"birthDate\":\"1325491232359\",\"email\":\"980044909@qq.com\",\"lastLogin\":\"1325491200000\"}";
//        JsonObject.fromOject
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            List<String> testList = new ArrayList<String>();
            testList.add("gender");
            testList.add("idtype");
            String json = objectMapper.writeValueAsString(testList);
            List<String> listInfo = objectMapper.readValue(json, List.class);
//            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            String str = objectMapper.writeValueAsString(listInfo);

            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("hello");
    }
}
