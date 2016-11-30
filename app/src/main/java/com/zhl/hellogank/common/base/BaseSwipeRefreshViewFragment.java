package com.zhl.hellogank.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhl.baserefreshview.IPlaceHolderView;
import com.zhl.baserefreshview.refreshView.BaseSwipeRefreshView;
import com.zhl.hellogank.R;
import com.zhl.hellogank.common.baseui.IListDataView;
import com.zhl.hellogank.common.baseui.PlaceHolderView;
import com.zhl.hellogank.common.utils.CommonUtils;

/**
 * Created by zhouhl on 2016/11/8.
 * BaseSwipeRefreshViewFragment
 */

public abstract class BaseSwipeRefreshViewFragment<T extends BaseContract.Presenter> extends BaseFragment<T>
        implements IListDataView {

    private BaseSwipeRefreshView mBaseSwipeRefreshView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_swipe_refresh_view, container, false);

        mBaseSwipeRefreshView = createSwipeRefreshView();
        CommonUtils.checkNotNull(mBaseSwipeRefreshView);

        mBaseSwipeRefreshView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ((ViewGroup)rootView).addView(mBaseSwipeRefreshView, params);

        mBaseSwipeRefreshView.setPlaceHolderView(createPlaceHolderView());
        return rootView;
    }

    protected abstract BaseSwipeRefreshView createSwipeRefreshView();

    protected abstract void onRetry();

    protected IPlaceHolderView createPlaceHolderView() {
        PlaceHolderView placeHolderView = new PlaceHolderView(mActivity);
        placeHolderView.setRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetry();
            }
        });
        return placeHolderView;
    }

    @Override
    public void showList(boolean isHasMore) {
        mBaseSwipeRefreshView.showList(isHasMore);
    }

    @Override
    public void showEmpty() {
        mBaseSwipeRefreshView.showEmpty();
    }

    @Override
    public void showError() {
        mBaseSwipeRefreshView.showError();
    }

    @Override
    public void showLoading() {
        mBaseSwipeRefreshView.showLoading();
    }
}
