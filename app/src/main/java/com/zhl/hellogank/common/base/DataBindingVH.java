package com.zhl.hellogank.common.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhl.commonadapter.BaseViewHolder;

/**
 * Created by zhouhl on 2016/11/29.
 * DataBindingVH
 */

public abstract class DataBindingVH<T, B extends ViewDataBinding> extends BaseViewHolder<T> {

    protected B mBinding;

    @Override
    public void findView(View view) {

    }

    @Override
    public View getDataBindingRoot(Context context, ViewGroup parent) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutResId(), parent, false);
        return mBinding.getRoot();
    }
}
