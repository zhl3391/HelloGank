package com.zhl.hellogank.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zhl.hellogank.R;
import com.zhl.hellogank.common.utils.ActivityUtils;
import com.zhl.hellogank.common.utils.CommonUtils;

/**
 * Created by zhouhl on 2016/11/1.
 * BaseFragmentActivity
 */

public abstract class BaseFragmentActivity<T extends Fragment> extends BaseActivity {

    protected T mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);

        mFragment = (T) getSupportFragmentManager().findFragmentById(R.id.layout_content);
        if (mFragment == null) {
            mFragment = createFragment();
            CommonUtils.checkNotNull(mFragment, "fragment is NULL");
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mFragment, R.id.layout_content);
        }
    }

    protected abstract T createFragment();
}
