package com.zhl.hellogank.business.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhl.hellogank.business.view.MainFragment;
import com.zhl.hellogank.common.base.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity<MainFragment> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragment.setFragmentManager(getSupportFragmentManager());
    }

    @Override
    protected MainFragment createFragment() {
        return new MainFragment();
    }
}
