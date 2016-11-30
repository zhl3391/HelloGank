package com.zhl.hellogank.common.baseui;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhl.baserefreshview.MoreViewHolder;
import com.zhl.hellogank.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouhl on 2016/11/24.
 * LoadMoreVH
 */

public class LoadMoreVH extends MoreViewHolder {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.tv_no_more)
    TextView mTvNoMore;

    @Override
    public void bindView(View view) {
        super.bindView(view);
        if (view.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
    }

    @Override
    public void findView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void updateView(LoadMore data, int position) {
        switch (mLoadMore.showWhat) {
            case LoadMore.SHOW_LOADING:
                mProgressBar.setVisibility(View.VISIBLE);
                mTvNoMore.setVisibility(View.GONE);
                break;
            case LoadMore.SHOW_NO_MORE:
                mProgressBar.setVisibility(View.GONE);
                mTvNoMore.setVisibility(View.VISIBLE);
                break;
            case LoadMore.SHOW_ERROR:
                mProgressBar.setVisibility(View.GONE);
                mTvNoMore.setVisibility(View.VISIBLE);
                mTvNoMore.setText(R.string.place_hold_error);
                break;
            default:
                mProgressBar.setVisibility(View.GONE);
                mTvNoMore.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_more_vertical;
    }
}
