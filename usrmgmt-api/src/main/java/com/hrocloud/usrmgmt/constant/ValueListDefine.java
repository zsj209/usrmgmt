package com.hrocloud.usrmgmt.constant;

import com.hrocloud.common.constant.VlDefineEnum;

/**
 * Created by zou_s on 2016/12/30.
 */
public enum ValueListDefine implements VlDefineEnum {

    USERSTATUS("userstatus", 1) {
        @Override
        public String getDefinePath() {
            return "com.hrocloud.usrmgmt.constant.UserStatus";
        }
    };


    private String key;

    private int type;

    ValueListDefine(String key, int type) {
        this.setKey(key.toLowerCase());
        this.type = type;
    }

    public static ValueListDefine keyOf(String key) {
        if (key.equalsIgnoreCase("userstatus"))
            return USERSTATUS;

        return null;

    }


    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
