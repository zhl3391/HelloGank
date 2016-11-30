package com.zhl.hellogank.net;

/**
 * Created by zhouhl on 2016/11/1.
 * NetBaseSubscriber
 */

public class NetBaseSubscriber<T extends Response> extends rx.Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
