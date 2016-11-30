package com.zhl.hellogank;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.zhl.hellogank.common.utils.LogUtils;

/**
 * Created by zhouhl on 2016/11/8.
 * GankApplication
 */

public class GankApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init(BuildConfig.DEBUG);
        Stetho.initializeWithDefaults(this);
    }
}
