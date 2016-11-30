package com.zhl.hellogank.business.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhl.hellogank.business.IntentCreator;
import com.zhl.hellogank.common.base.BaseActivity;

import aptintent.lib.AptIntent;

/**
 * Created by zhouhl on 2016/11/8.
 * SplashActivity
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(AptIntent.create(IntentCreator.class).activityMain(this));
        finish();
    }
}
