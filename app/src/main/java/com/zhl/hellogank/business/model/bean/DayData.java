package com.zhl.hellogank.business.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhouhl on 2016/11/1.
 * DayData 每日数据
 */

public class DayData {

    public List<String> category;
    @SerializedName("Android")
    public List<Article> android;
    @SerializedName("iOS")
    public List<Article> ios;
    @SerializedName("休息视频")
    public List<Article> relaxVideo;
    @SerializedName("拓展资源")
    public List<Article> otherRes;
    @SerializedName("瞎推荐")
    public List<Article> recommend;
    @SerializedName("福利")
    public List<Article> fuli;

}
