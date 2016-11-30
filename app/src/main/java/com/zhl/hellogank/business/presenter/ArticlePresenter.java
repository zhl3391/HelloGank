package com.zhl.hellogank.business.presenter;

import com.zhl.hellogank.business.contract.ArticleContract;
import com.zhl.hellogank.business.model.ArticleModel;
import com.zhl.hellogank.business.model.bean.Article;
import com.zhl.hellogank.business.model.bean.HomeType;
import com.zhl.hellogank.common.base.ListDataPresenter;
import com.zhl.hellogank.net.NetBaseSubscriber;
import com.zhl.hellogank.net.NetBaseSubscription;
import com.zhl.hellogank.net.Response;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by zhouhl on 2016/11/8.
 * ArticlePresenter
 */

public class ArticlePresenter extends ListDataPresenter<ArticleContract.View, ArticleModel, Article>
        implements ArticleContract.Presenter {

    private HomeType mHomeType;

    public ArticlePresenter(ArticleContract.View view, ArticleModel model) {
        super(view, model);
        mPage = 1;
    }

    public void setHomeType(HomeType homeType) {
        mHomeType = homeType;
    }

    @Override
    public void refresh() {
        mPage = 1;
        super.refresh();
    }

    @Override
    public void loadMore() {
        mPage++;
        super.loadMore();
    }

    @Override
    protected void loadData() {
        Observable<Response<List<Article>>> observable = mModel.getArticleList(mHomeType, mCount, mPage);

        if (observable != null) {
            mSubscriptions.add(NetBaseSubscription.subscription(
                    observable.delay(500, TimeUnit.MILLISECONDS),
                    new NetBaseSubscriber<Response<List<Article>>>() {
                        @Override
                        public void onNext(final Response<List<Article>> listResponse) {
                            super.onNext(listResponse);
                            onSuccess(listResponse);
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            mView.showError();
                        }
                    }));

        } else {
            mView.showEmpty();
        }
    }

    @Override
    public HomeType getHomeType() {
        return mHomeType;
    }
}
