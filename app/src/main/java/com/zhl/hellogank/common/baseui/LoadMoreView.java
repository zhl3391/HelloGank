package com.zhl.hellogank.common.baseui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhl.baserefreshview.ILoadMoreView;
import com.zhl.hellogank.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhouhl on 2016/11/8.
 * LoadMoreView
 */

public class LoadMoreView implements ILoadMoreView {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.tv_no_more)
    TextView mTvNoMore;

    private View mRootView;

    public LoadMoreView(Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.view_more_vertical, null);
        ButterKnife.bind(this, mRootView);
    }

    @NonNull
    @Override
    public View getView() {
        return mRootView;
    }

    @Override
    public void showLoading() {
        mTvNoMore.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoMore() {
        mTvNoMore.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mTvNoMore.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }
}
