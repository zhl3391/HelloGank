package com.zhl.hellogank.common.base;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhouhl on 2016/11/1.
 * BasePresenter
 */

public abstract class BasePresenter implements BaseContract.Presenter {

    protected String TAG = getClass().getSimpleName();

    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.unsubscribe();
        mSubscriptions = new CompositeSubscription();
    }
}
