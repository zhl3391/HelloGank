package com.zhl.hellogank.business;

import android.content.Context;
import android.content.Intent;

import com.zhl.hellogank.business.activity.MainActivity;
import com.zhl.hellogank.business.activity.PreviewActivity;
import com.zhl.hellogank.business.activity.WebActivity;

import aptintent.lib.CreateIntent;
import aptintent.lib.Extra;

/**
 * Created by zhouhl on 2016/11/8.
 * IntentCreator
 */

public interface IntentCreator {

    String EXTRA_URL = "url";
    String EXTRA_TITLE = "title";
    String EXTRA_IMG_URL = "img_url";
    String EXTRA_WIDTH = "width";
    String EXTRA_HEIGHT = "height";

    @CreateIntent(MainActivity.class)
    Intent activityMain(Context context);

    @CreateIntent(WebActivity.class)
    Intent activityWeb(Context context, @Extra(EXTRA_URL) String url, @Extra(EXTRA_TITLE) String title);

    @CreateIntent(PreviewActivity.class)
    Intent activityPreview(Context context,
                           @Extra(EXTRA_IMG_URL) String imgUrl,
                           @Extra(EXTRA_WIDTH) int width,
                           @Extra(EXTRA_HEIGHT) int height);
}
