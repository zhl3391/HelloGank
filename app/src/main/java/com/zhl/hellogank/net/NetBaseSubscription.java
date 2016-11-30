package com.zhl.hellogank.net;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.zhl.hellogank.common.utils.CommonUtils.checkNotNull;

/**
 * Created by zhouhl on 2016/11/1.
 * NetBaseSubscription
 */

public class NetBaseSubscription {

    @SuppressWarnings("unchecked")
    public static Subscription subscription(@NonNull Observable observable, @NonNull Subscriber subscriber){
        observable = checkNotNull(observable);
        subscriber = checkNotNull(subscriber);

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
