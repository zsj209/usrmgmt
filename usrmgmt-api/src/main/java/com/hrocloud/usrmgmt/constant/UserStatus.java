package com.hrocloud.usrmgmt.constant;

import com.hrocloud.common.constant.ValueListEnum;

/**
 * 订单状态 *
 * 实现带有抽象方法的枚举 *
 *
 * @author Stanley Zou
 */
public enum UserStatus implements ValueListEnum {

    DISABLED(1, "DISABLED") {
        @Override
        public String getDesc() {
            // TODO Auto-generated method stub
            return "用户已停用";
        }

    },
    ACTIVE(2,"ACTIVE") {
        @Override
        public String getDesc() {
            // TODO Auto-generated method stub
            return "正常";
        }

    },
    EDITING(3,"EDITING") {
        @Override
        public String getDesc() {
            // TODO Auto-generated method stub
            return "用户编辑中";
        }

    };

    private int key;

    private String code;

    UserStatus(int key, String code) {
        this.setKey(key);
        this.setCode(code);
    }

    public static UserStatus keyOf(int key) {
        switch (key) {
            case 1:
                return DISABLED;
            case 2:
                return ACTIVE;
            case 3:
                return EDITING;
            default:
                return ACTIVE;
        }
    }

    public static UserStatus codeOf(String code) {
        switch (code) {
            case "DISABLED":
                return DISABLED;
            case "ACTIVE":
                return ACTIVE;
            case "EDITING":
                return EDITING;
            default:
                return ACTIVE;
        }
    }

    public String getCode(){
        return code;
    }
//    public abstract String getDesc();

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }


    public void setCode(String code) {
        this.code = code;
    }
}
