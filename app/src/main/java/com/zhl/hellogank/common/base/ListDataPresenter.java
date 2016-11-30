package com.zhl.hellogank.common.base;

import com.zhl.hellogank.net.Response;

import java.util.ArrayList;
import java.util.List;

import static com.zhl.hellogank.common.utils.CommonUtils.checkNotNull;

/**
 * Created by zhouhl on 2016/11/1.
 * ListDataPresenter
 */

public abstract class ListDataPresenter<V extends ListDataContract.View, M, D> extends BasePresenter
        implements ListDataContract.Presenter<D> {

    protected V mView;
    protected M mModel;

    protected List<D> mDataList = new ArrayList<>();

    protected int mPage;
    protected int mCount = 20;
    private boolean mIsRefresh;

    public ListDataPresenter(V view, M model) {
        mView = checkNotNull(view);
        mModel = checkNotNull(model);
        mView.setPresenter(this);
    }

    @Override
    public void refresh() {
        mIsRefresh = true;
        loadData();
    }

    @Override
    public void loadMore() {
        mIsRefresh = false;
        loadData();
    }

    @Override
    public List<D> getDataList() {
        return mDataList;
    }

    protected abstract void loadData();

    protected void onSuccess(Response<List<D>> response) {
        if (mIsRefresh) {
            mDataList.clear();
        }
        List<D> dataList = response.results;
        if (dataList != null && !dataList.isEmpty()) {
            boolean hasMore = dataList.size() >= mCount;
            mDataList.addAll(dataList);
            mView.showList(hasMore);
        } else if (mDataList.isEmpty()){
            mView.showEmpty();
        } else {
            mView.showList(false);
        }
    }
}
