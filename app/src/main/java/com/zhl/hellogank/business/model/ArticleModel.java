package com.zhl.hellogank.business.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.zhl.hellogank.business.model.bean.Article;
import com.zhl.hellogank.business.model.bean.HomeType;
import com.zhl.hellogank.common.utils.ScreenUtils;
import com.zhl.hellogank.net.NetManager;
import com.zhl.hellogank.net.RequestApi;
import com.zhl.hellogank.net.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by zhouhl on 2016/11/8.
 * ArticleModel
 */

public class ArticleModel {

    private RequestApi mApi;
    private Context mContext;

    public ArticleModel(Context context) {
        mApi = NetManager.getApi();
        mContext = context;
    }

    @Nullable
    public Observable<Response<List<Article>>> getArticleList(HomeType homeType, int count, int page) {
        Observable<Response<List<Article>>> observable = mApi.getArticleList(homeType.name, count, page);
        if (homeType == HomeType.FULI) {
            observable = observable.flatMap(new Func1<Response<List<Article>>, Observable<Response<List<Article>>>>() {
                @Override
                public Observable<Response<List<Article>>> call(final Response<List<Article>> listResponse) {
                    return Observable.create(new Observable.OnSubscribe<Response<List<Article>>>() {
                        @Override
                        public void call(Subscriber<? super Response<List<Article>>> subscriber) {
                            if (listResponse.results != null) {
                                List<Article> articles = new ArrayList<>();
                                for (Article article : listResponse.results) {
                                    try {
                                        Bitmap bitmap = Glide.with(mContext)
                                                .load(article.url)
                                                .asBitmap()
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                                .get();
                                        article.imgWidth = (ScreenUtils.getScreenWidth(mContext) - ScreenUtils.dp2px(mContext, 9)) / 2;
                                        article.imgHeight = bitmap.getHeight() * article.imgWidth / bitmap.getWidth();
                                        articles.add(article);
                                        listResponse.results = articles;
                                        System.out.println(article.imgWidth + "  " + article.imgHeight);
                                    } catch (InterruptedException | ExecutionException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            subscriber.onNext(listResponse);
                        }
                    });
                }
            });
        }
        return observable;
    }
}
