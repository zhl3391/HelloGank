package com.zhl.hellogank.business.model.bean;

import java.util.List;

/**
 * Created by zhouhl on 2016/11/1.
 * Article
 */

public class Article {

    public String _id;

    public String createdAt;

    public String desc;

    public String publishedAt;

    public List<String> images;

    public String source;

    public String type;

    public String url;

    public String who;

    public String getCreatedAt() {
        return createdAt.substring(0, createdAt.indexOf("T"));
    }

    public int imgWidth;
    public int imgHeight;
}
