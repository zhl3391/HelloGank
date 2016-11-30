package com.zhl.hellogank.business.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhl.baserefreshview.refreshView.BaseSwipeRefreshView;
import com.zhl.baserefreshview.refreshView.RefreshListener;
import com.zhl.baserefreshview.refreshView.SwipeRefreshRecyclerView;
import com.zhl.commonadapter.BaseViewHolder;
import com.zhl.commonadapter.CommonRecyclerAdapter;
import com.zhl.hellogank.R;
import com.zhl.hellogank.business.IntentCreator;
import com.zhl.hellogank.business.contract.ArticleContract;
import com.zhl.hellogank.business.model.bean.Article;
import com.zhl.hellogank.business.model.bean.HomeType;
import com.zhl.hellogank.common.base.BaseSwipeRefreshViewFragment;
import com.zhl.hellogank.common.baseui.GridMarginDecoration;
import com.zhl.hellogank.common.baseui.LoadMoreVH;
import com.zhl.hellogank.common.utils.ScreenUtils;

import aptintent.lib.AptIntent;

import static com.zhl.hellogank.R.string.img;

/**
 * Created by zhouhl on 2016/11/8.
 * ArticleFragment
 */

public class ArticleFragment extends BaseSwipeRefreshViewFragment<ArticleContract.Presenter> implements ArticleContract.View {

    private CommonRecyclerAdapter<Article> mAdapter;
    private SwipeRefreshRecyclerView mSwipeRefreshRecyclerView;

    private IntentCreator mIntentCreator;

    private boolean mIsViewCreated = false;
    private boolean mIsFetchData = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        mIntentCreator = AptIntent.create(IntentCreator.class);

        mAdapter = new CommonRecyclerAdapter<Article>(mPresenter.getDataList()) {
            @Override
            public BaseViewHolder<Article> createViewHolder(int type) {
                if (mPresenter.getHomeType() == HomeType.FULI) {
                    return new FuLiViewHolder();
                } else {
                    return new ArticleViewHolder();
                }
            }
        };

        mAdapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Article article = mAdapter.getItem(position);
                if (mPresenter.getHomeType() == HomeType.FULI) {
                    View imgView = view.findViewById(R.id.img_fuli);
                    imgView.setDrawingCacheEnabled(true);
                    Pair<View, String> imagePair = Pair.create(imgView, getString(img));
                    ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, imagePair);
                    ActivityCompat.startActivity(mActivity,
                            mIntentCreator.activityPreview(mActivity, article.url, article.imgWidth, article.imgHeight),
                            compat.toBundle());
                } else {
                    startActivity(mIntentCreator.activityWeb(mActivity, article.url, article.desc));
                }
            }
        });

        if (mPresenter.getHomeType() == HomeType.FULI) {
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mSwipeRefreshRecyclerView.setLayoutManager(layoutManager);
            int space = getResources().getDimensionPixelSize(R.dimen.space_fuli_item);
            mSwipeRefreshRecyclerView.getRecyclerView().addItemDecoration(new GridMarginDecoration(space));
            mSwipeRefreshRecyclerView.getRecyclerView().setHasFixedSize(true);
            mSwipeRefreshRecyclerView.getRecyclerView().setPadding(space, space, space, space);
        } else {
            mSwipeRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        }
        mSwipeRefreshRecyclerView.setMoreViewHolder(new LoadMoreVH());
        mSwipeRefreshRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshRecyclerView.setRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }

            @Override
            public void onLoadMore() {
                mPresenter.loadMore();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreated = true;
        lazyFetchDataIfPrepared();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsViewCreated = false;
        mIsFetchData = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    @Override
    protected BaseSwipeRefreshView createSwipeRefreshView() {
        mSwipeRefreshRecyclerView = new SwipeRefreshRecyclerView(mActivity);
        return mSwipeRefreshRecyclerView;
    }

    @Override
    protected void onRetry() {
        lazyFetchData();
    }

    private void lazyFetchData() {
        showLoading();
        mIsFetchData = true;
        mPresenter.refresh();
    }

    private void lazyFetchDataIfPrepared() {
        if (getUserVisibleHint() && !mIsFetchData && mIsViewCreated) {
            lazyFetchData();
        }
    }

    @Override
    public void showList(boolean isHasMore) {
        super.showList(isHasMore);
//        int start = 0;
//        if (mAdapter.getItemCount() != 0) {
//            start = mAdapter.getItemCount() - 20;
//        }
//        mAdapter.notifyItemRangeInserted(start, mAdapter.getItemCount());
//        mAdapter.notifyDataSetChanged();
    }
}
