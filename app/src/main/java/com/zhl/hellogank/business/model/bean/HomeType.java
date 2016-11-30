package com.zhl.hellogank.business.model.bean;

/**
 * Created by zhouhl on 2016/11/8.
 * HomeType
 */

public enum HomeType {
    ANDROID("Android"), IOS("iOS"), FRONT("前端"), FULI("福利"), OTHER_RES("拓展资源"),
    VIDEO("休息视频"), RECOMMEND("瞎推荐");

    public String name;

    HomeType(String name) {
        this.name = name;
    }
}
