package com.hrocloud.usrmgmt;


import com.hrocloud.usrmgmt.utils.SecurityInit;

public class UsrmgmtServer {

    public static void main(String[] args) {
        new SecurityInit();
        com.alibaba.dubbo.container.Main.main(args);
    }
}
