package com.zhl.hellogank.common.baseui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhl.baserefreshview.IPlaceHolderView;
import com.zhl.hellogank.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouhl on 2016/11/8.
 * PlaceHolderView
 */

public class PlaceHolderView implements IPlaceHolderView {

    @BindView(R.id.img_empty)
    ImageView mImgEmpty;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    @BindView(R.id.img_error)
    ImageView mImgError;
    @BindView(R.id.tv_error)
    TextView mTvError;
    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;
    @BindView(R.id.tv_loading)
    TextView mTvLoading;
    @BindView(R.id.layout_empty)
    LinearLayout mLayoutEmpty;
    @BindView(R.id.layout_error)
    LinearLayout mLayoutError;
    @BindView(R.id.layout_loading)
    LinearLayout mLayoutLoading;

    private RelativeLayout mRootView;

    public PlaceHolderView(Context context) {
        mRootView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_place_holder_view, null);
        ButterKnife.bind(this, mRootView);
        showNothing();
    }

    @Override
    public void showLoading() {
        mRootView.setVisibility(View.VISIBLE);
        mLayoutLoading.setVisibility(View.VISIBLE);
        mLayoutLoading.setScaleX(0);
        mLayoutLoading.setScaleY(0);
        mLayoutLoading.animate().scaleX(1).scaleY(1).setDuration(300).start();
        mLayoutEmpty.setVisibility(View.GONE);
        mLayoutError.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mRootView.setVisibility(View.VISIBLE);
        mLayoutError.setVisibility(View.VISIBLE);
        mLayoutEmpty.setVisibility(View.GONE);
        mLayoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        mRootView.setVisibility(View.VISIBLE);
        mLayoutEmpty.setVisibility(View.VISIBLE);
        mLayoutError.setVisibility(View.GONE);
        mLayoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void showNothing() {
        mRootView.setVisibility(View.GONE);
    }

    @Override
    public View getView() {
        return mRootView;
    }

    public void setRetryListener(@NonNull final View.OnClickListener onClickListener) {
        mLayoutError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
            }
        });
    }
}
